package com.ether.data.service.impl;

import com.ether.data.dao.TransactionErc20Mapper;
import com.ether.data.dao.TransactionMapper;
import com.ether.data.dto.ApiLogDto;
import com.ether.data.dto.TopicOperate;
import com.ether.data.service.ApiService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Service
public class ApiServiceImpl implements ApiService {
    @Autowired
    private TransactionErc20Mapper transactionErc20Mapper;

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public PageInfo<Map> apiLog(ApiLogDto dto) {
        String fromNumber = dto.getFromNumber();
        String toNumber = dto.getToNumber();
        Integer limitRowCount = dto.getLimit();
        String contractAddress = dto.getContractAddress();

        String numberSql = "";
        if (toNumber.equals("latest")) {
            numberSql = "(blockNumber >" + fromNumber + ") ";
        } else {
            numberSql = "(blockNumber >= '" + fromNumber + "' and  blockNumber <= '" + toNumber + "')";
        }

        if (contractAddress.startsWith("0x000000000000000000000")) {
            String topicSql = getTopicSql(dto.getTopics(), false);
            if (limitRowCount > 0) {
                List<Map> list = transactionMapper.selectPlatformInternalTransactionLog(numberSql, topicSql, limitRowCount, dto.getOrder());
                for (Map<String, String> map : list) {
                    if (map.get("index").equals("-1")) {
                        String fromAddress = map.get("topic1").replace("0x", "0x000000000000000000000000");
                        map.put("topic1", fromAddress);
                        String toAddress = map.get("topic2").replace("0x", "0x000000000000000000000000");
                        map.put("topic2", toAddress);
                        String strHexData = new BigInteger(map.get("data")).toString(16);
                        map.put("data", toFormatHex(strHexData));
                    }
                }
                return new PageInfo<>(list);
            } else {
                PageHelper.startPage(dto.getPage(), dto.getPageSize());
                List<Map> list = transactionMapper.selectPlatformInternalTransactionLog(numberSql, topicSql, limitRowCount, dto.getOrder());
                return new PageInfo<>(list);
            }
        } else {
            String topicSql = getTopicSql(dto.getTopics(), true);
            if (limitRowCount > 0) {
                List<Map> list = transactionErc20Mapper.getTransactionLog(contractAddress, numberSql, topicSql, limitRowCount, dto.getOrder());
                return new PageInfo<>(list);
            } else {
                PageHelper.startPage(dto.getPage(), dto.getPageSize());
                List<Map> list = transactionErc20Mapper.getTransactionLog(contractAddress, numberSql, topicSql, limitRowCount, dto.getOrder());
                return new PageInfo<>(list);
            }
        }
    }
    public static String toFormatHex(String hex) {
        Integer hexLength = hex.length();
        for (int i = hexLength; i < 64; i++) {
            hex = "0" + hex;
        }
        return "0x" + hex;
    }

    public static String bigDecimalToHex(BigDecimal bigDecimal) {
        // 将BigDecimal转换为二进制字符串
        String binaryString = bigDecimal.unscaledValue().toString(2);
        String hex = binaryToHex(binaryString);
        Integer hexLength = hex.length();
        for (int i = hexLength; i < 64; i++) {
            hex = "0" + hex;
        }
        return "0x" + hex;
    }

    private static String binaryToHex(String binary) {
        int length = binary.length();
        StringBuilder hex = new StringBuilder();
        for (int i = 0; i < length; i += 4) {
            String substring = binary.substring(i, Math.min(i + 4, length));
            int value = Integer.parseInt(substring, 2);
            hex.append(Integer.toHexString(value));
        }
        return hex.toString();
    }

    private String getTopicSql(List<TopicOperate> topicOperates, boolean isToken) {
        String topicSql = "";
        for (int k = 0; k < topicOperates.size(); k++) {
            if (k > 0) {
                topicSql += " or ";
            }

            topicSql += "(";
            for (int i = 0; i < topicOperates.get(k).getTopic().size(); i++) {
                TopicOperate topicOperate = topicOperates.get(k);
                if (i > 0) {
                    topicSql += " " + topicOperate.getOperate()[i - 1] + " ";
                }
                if (!isToken) {
                    String topicName = topicOperate.getTopic().get(i).getName();
                    if (topicName.equals("topic1") || topicName.equals("topic2")) {
                        topicSql += " (";
                        topicSql += topicName + "='" + topicOperate.getTopic().get(i).getValue() + "'";
                        topicSql += " or " + topicName + "='" + topicOperate.getTopic().get(i).getValue().replace("0x000000000000000000000000", "0x") + "'";
                        topicSql += " )";
                    } else {
                        topicSql += topicOperate.getTopic().get(i).getName() + "='" + topicOperate.getTopic().get(i).getValue() + "'";
                    }
                } else {
                    topicSql += topicOperate.getTopic().get(i).getName() + "='" + topicOperate.getTopic().get(i).getValue() + "'";
                }
            }
            topicSql += ")";
        }
        topicSql = "( " + topicSql.replace("topic0", "A.methodHash").replace("topic1", "A.`from`").replace("topic2", "A.`to`") + " )";
        return topicSql;
    }
}

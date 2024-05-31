package com.ether.data.dto;

import java.util.List;

public class ApiLogDto {
    private String fromNumber;
    private String toNumber;
    private Integer limit;
    private String contractAddress;
    private String order;
    private Integer page;
    private Integer pageSize;

    private List<TopicOperate> topics;

    public String getFromNumber() {
        return fromNumber;
    }

    public void setFromNumber(String fromNumber) {
        this.fromNumber = fromNumber;
    }

    public String getToNumber() {
        return toNumber;
    }

    public void setToNumber(String toNumber) {
        this.toNumber = toNumber;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public List<TopicOperate> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicOperate> topics) {
        this.topics = topics;
    }
}


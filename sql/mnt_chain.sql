/*
 Navicat Premium Data Transfer

 Source Server         : 18.162.51.194
 Source Server Type    : MySQL
 Source Server Version : 80036
 Source Host           : localhost:3306
 Source Schema         : mnt_chain

 Target Server Type    : MySQL
 Target Server Version : 80036
 File Encoding         : 65001

 Date: 08/05/2024 11:26:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for block
-- ----------------------------
DROP TABLE IF EXISTS `block`;
CREATE TABLE `block`  (
  `number` decimal(65, 0) NOT NULL,
  `hash` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `parentHash` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `timestamp` bigint(0) NULL DEFAULT NULL,
  `transactionCount` int(0) NULL DEFAULT NULL,
  `nonce` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `difficulty` bigint(0) NULL DEFAULT NULL,
  `gasLimit` decimal(65, 0) NULL DEFAULT NULL,
  `gasUsed` decimal(65, 0) NULL DEFAULT NULL,
  `gasPrice` decimal(65, 0) NULL DEFAULT NULL,
  `miner` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `extraData` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `baseFeePerGas` decimal(65, 0) NULL DEFAULT NULL,
  PRIMARY KEY (`number`) USING BTREE,
  INDEX `hash`(`hash`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for contract
-- ----------------------------
DROP TABLE IF EXISTS `contract`;
CREATE TABLE `contract`  (
  `contractAddress` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `blockHash` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `transactionHash` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `blockNumber` decimal(65, 0) NULL DEFAULT NULL,
  `abi` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `creator` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `createTime` datetime(0) NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `totalSupply` decimal(65, 0) NULL DEFAULT NULL,
  `ercName` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ercSymbol` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `decimals` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`contractAddress`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for contract_verfity
-- ----------------------------
DROP TABLE IF EXISTS `contract_verfity`;
CREATE TABLE `contract_verfity`  (
  `contractAddress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `contractName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `compilerVersion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `optimization` int(0) NULL DEFAULT NULL COMMENT '1是  0否',
  `compileType` int(0) NULL DEFAULT NULL,
  `licenseType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `other` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `abi` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `bytecode` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `deployedBytecode` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `verifyStatus` int(0) NULL DEFAULT NULL COMMENT '1 通过  0不通过',
  PRIMARY KEY (`contractAddress`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for contract_verfity_code
-- ----------------------------
DROP TABLE IF EXISTS `contract_verfity_code`;
CREATE TABLE `contract_verfity_code`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `contractAddress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `fileName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `filePath` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for erc20_balance
-- ----------------------------
DROP TABLE IF EXISTS `erc20_balance`;
CREATE TABLE `erc20_balance`  (
  `address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `contractAddress` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `balance` decimal(65, 0) NULL DEFAULT NULL,
  `updateTime` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`address`, `contractAddress`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for except_block
-- ----------------------------
DROP TABLE IF EXISTS `except_block`;
CREATE TABLE `except_block`  (
  `blockNumber` decimal(65, 0) NOT NULL,
  `sync_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`blockNumber`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for method_hash
-- ----------------------------
DROP TABLE IF EXISTS `method_hash`;
CREATE TABLE `method_hash`  (
  `hash` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`hash`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mnt_price
-- ----------------------------
DROP TABLE IF EXISTS `mnt_price`;
CREATE TABLE `mnt_price`  (
  `id` int(0) NOT NULL,
  `price` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for platform_balance
-- ----------------------------
DROP TABLE IF EXISTS `platform_balance`;
CREATE TABLE `platform_balance`  (
  `address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `balance` decimal(65, 0) NULL DEFAULT NULL,
  `updateTime` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`address`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for platform_internal_transaction
-- ----------------------------
DROP TABLE IF EXISTS `platform_internal_transaction`;
CREATE TABLE `platform_internal_transaction`  (
  `transactionHash` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `blockHash` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `contractAddress` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `index` int(0) NOT NULL,
  `blockNumber` bigint(0) NULL DEFAULT NULL,
  `methodHash` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `from` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `to` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`transactionHash`, `blockHash`, `contractAddress`, `index`) USING BTREE,
  INDEX `methodHash`(`methodHash`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for transaction
-- ----------------------------
DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction`  (
  `hash` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` tinyint(0) NULL DEFAULT NULL COMMENT '0 平台交易 1创建合约 2合约交易',
  `blockHash` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `blockNumber` bigint(0) NULL DEFAULT NULL,
  `transactionIndex` tinyint(0) NULL DEFAULT NULL,
  `confirmations` int(0) NULL DEFAULT NULL,
  `from` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gasPrice` decimal(65, 0) NULL DEFAULT NULL,
  `maxPriorityFeePerGas` decimal(65, 0) NULL DEFAULT NULL,
  `maxFeePerGas` decimal(65, 0) NULL DEFAULT NULL,
  `gasLimit` decimal(65, 0) NULL DEFAULT NULL,
  `to` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `value` decimal(65, 0) NULL DEFAULT NULL,
  `nonce` int(0) NULL DEFAULT NULL,
  `data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `methodHash` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `r` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `s` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `v` int(0) NULL DEFAULT NULL,
  `creates` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `chainId` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`hash`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for transaction_count_per_day
-- ----------------------------
DROP TABLE IF EXISTS `transaction_count_per_day`;
CREATE TABLE `transaction_count_per_day`  (
  `date` date NOT NULL,
  `count` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for transaction_erc20
-- ----------------------------
DROP TABLE IF EXISTS `transaction_erc20`;
CREATE TABLE `transaction_erc20`  (
  `transactionHash` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `blockHash` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `contractAddress` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `index` int(0) NOT NULL,
  `blockNumber` bigint(0) NULL DEFAULT NULL,
  `methodHash` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `from` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `to` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`transactionHash`, `blockHash`, `contractAddress`, `index`) USING BTREE,
  INDEX `methodHash`(`methodHash`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for transaction_platform
-- ----------------------------
DROP TABLE IF EXISTS `transaction_platform`;
CREATE TABLE `transaction_platform`  (
  `transactionHash` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `index` int(0) NOT NULL,
  `from` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `to` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `value` decimal(65, 18) NULL DEFAULT NULL,
  `utc` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`transactionHash`, `index`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for transaction_receipt
-- ----------------------------
DROP TABLE IF EXISTS `transaction_receipt`;
CREATE TABLE `transaction_receipt`  (
  `transactionHash` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `from` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `to` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `contractAddress` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `transactionIndex` tinyint(0) NULL DEFAULT NULL,
  `gasUsed` decimal(65, 0) NULL DEFAULT NULL,
  `logsBloom` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `blockHash` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `blockNumber` bigint(0) NULL DEFAULT NULL,
  `confirmations` int(0) NULL DEFAULT NULL,
  `cumulativeGasUsed` decimal(65, 0) NULL DEFAULT NULL,
  `effectiveGasPrice` decimal(65, 0) NULL DEFAULT NULL,
  `status` tinyint(0) NULL DEFAULT NULL COMMENT '0 失败  1成功',
  `type` tinyint(0) NULL DEFAULT NULL,
  `byzantium` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`transactionHash`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

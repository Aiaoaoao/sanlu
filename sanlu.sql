/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50505
Source Host           : 127.0.0.1:3306
Source Database       : sanlu

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2019-05-08 12:48:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for accounts_payable
-- ----------------------------
DROP TABLE IF EXISTS `accounts_payable`;
CREATE TABLE `accounts_payable` (
  `id` int(11) DEFAULT NULL COMMENT '账款--应付账款冲账',
  `rush_date` datetime DEFAULT NULL COMMENT '冲账日期',
  `order_no` varchar(255) DEFAULT NULL COMMENT '冲账编号',
  `early_order` tinyint(4) DEFAULT NULL COMMENT '早起单据',
  `customer_no` varchar(255) DEFAULT NULL COMMENT '客户编号',
  `customer_no_str` varchar(255) DEFAULT NULL COMMENT '客户编号描述',
  `currency` int(11) DEFAULT NULL COMMENT '币别',
  `rush_currency` int(11) DEFAULT NULL COMMENT '被冲但觉币别',
  `exchange_rate` varchar(255) DEFAULT NULL COMMENT '汇率',
  `process_people` bigint(20) DEFAULT NULL COMMENT '经办人',
  `process_people_str` varchar(255) DEFAULT NULL COMMENT '经办人描述',
  `made_poeple` varchar(255) DEFAULT NULL COMMENT '制单人',
  `audit` varchar(255) DEFAULT NULL COMMENT '审核人',
  `audit_str` varchar(255) DEFAULT NULL COMMENT '审核人描述',
  `last_update` varchar(255) DEFAULT NULL COMMENT '最后修改人',
  `last_update_str` varchar(255) DEFAULT NULL COMMENT '最后修改人描述',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  `order_audit` tinyint(1) DEFAULT '0' COMMENT '订单审核状态'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账款--应付账款冲账';

-- ----------------------------
-- Records of accounts_payable
-- ----------------------------

-- ----------------------------
-- Table structure for account_coast_accounting
-- ----------------------------
DROP TABLE IF EXISTS `account_coast_accounting`;
CREATE TABLE `account_coast_accounting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账款--成本核算',
  `storage_in_no` varchar(255) DEFAULT NULL COMMENT '入库单号',
  `install_no` varchar(255) DEFAULT NULL COMMENT '装箱单号',
  `audit` varchar(50) DEFAULT NULL COMMENT '审核人',
  `audit_date` datetime DEFAULT NULL COMMENT '审核人描述',
  `storage_in_remark` varchar(255) DEFAULT NULL COMMENT '入库备注',
  `verify_people` varchar(50) DEFAULT NULL COMMENT '核算人',
  `verify_people_str` varchar(255) DEFAULT NULL COMMENT '核算人描述',
  `invoice_no` varchar(50) DEFAULT NULL COMMENT '发票号码',
  `currency` int(11) DEFAULT NULL,
  `customs_fee` varchar(255) DEFAULT NULL COMMENT '报关费',
  `proxy_fee` varchar(255) DEFAULT NULL COMMENT '代理费',
  `carriage_fee` varchar(255) DEFAULT NULL COMMENT '运费',
  `poundage_fee` varchar(255) DEFAULT NULL COMMENT '手续费',
  `other_fee` varchar(255) DEFAULT NULL COMMENT '其它费用',
  `loan_total` varchar(255) DEFAULT NULL COMMENT '贷款合计',
  `tax_total` varchar(255) DEFAULT NULL COMMENT '税款合计',
  `price_tax_total` varchar(255) DEFAULT NULL COMMENT '价税合计',
  `total` varchar(255) DEFAULT NULL COMMENT '合计',
  `exchange_rate` varchar(255) DEFAULT NULL COMMENT '当天汇率',
  `exchange_currency` varchar(255) DEFAULT NULL COMMENT '当天汇率描述',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  `order_audit` tinyint(1) DEFAULT '0' COMMENT '订单审核状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='账款--成本核算';

-- ----------------------------
-- Records of account_coast_accounting
-- ----------------------------
INSERT INTO `account_coast_accounting` VALUES ('12', 'A1904280001', '66666666', '仓库测试人员', '2019-04-28 00:00:00', '', 'Administrator', '2019-04-28', '13123123', '0', '20', '20', '30', '30', '0', '6000.0', '0', '6000.0', '6100.0', '', '', '2019-04-28 23:58:54', null);

-- ----------------------------
-- Table structure for account_coast_purchase
-- ----------------------------
DROP TABLE IF EXISTS `account_coast_purchase`;
CREATE TABLE `account_coast_purchase` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '成本核算-采购成本',
  `otherid` bigint(20) DEFAULT NULL COMMENT '成本核算id',
  `product_no` varchar(255) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `warehouse_position` varchar(255) DEFAULT NULL COMMENT '仓库库位',
  `warehouse_num` int(11) DEFAULT NULL COMMENT '入库数量',
  `price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `dollar` decimal(10,2) DEFAULT NULL COMMENT '美金',
  `order_no` varchar(255) DEFAULT NULL COMMENT '订单编号',
  `rmb_money` decimal(10,2) DEFAULT NULL COMMENT '人民币金额',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_coast_purchase
-- ----------------------------

-- ----------------------------
-- Table structure for account_input_invoice
-- ----------------------------
DROP TABLE IF EXISTS `account_input_invoice`;
CREATE TABLE `account_input_invoice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账款--进项发票',
  `create_date` datetime DEFAULT NULL COMMENT '制单日期',
  `order_no` varchar(100) DEFAULT NULL COMMENT '单号',
  `invoice_type` varchar(50) DEFAULT NULL COMMENT '发票类型',
  `invoice_date` datetime DEFAULT NULL COMMENT '发票日期',
  `invoce_no` varchar(100) DEFAULT NULL COMMENT '发票号码',
  `made_people` varchar(50) DEFAULT NULL COMMENT '制单人',
  `supplier_no` varchar(100) DEFAULT NULL COMMENT '供应商编号',
  `supplier_no_str` varchar(255) DEFAULT NULL COMMENT '供应商编号描述',
  `payment_date` datetime DEFAULT NULL COMMENT '结算日期',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `audit` varchar(255) DEFAULT NULL COMMENT '审核人',
  `audit_str` varchar(255) DEFAULT NULL COMMENT '审核人描述',
  `last_update` varchar(255) DEFAULT NULL COMMENT '最后修改人',
  `last_update_str` varchar(255) DEFAULT NULL COMMENT '最后修改人描述',
  `order_audit` tinyint(1) DEFAULT NULL COMMENT '订单审核状态',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  `money` decimal(10,2) DEFAULT '0.00' COMMENT '应收金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_input_invoice
-- ----------------------------
INSERT INTO `account_input_invoice` VALUES ('5', '2019-04-29 00:00:00', 'A1904290001', '气动工具', '2019-04-29 00:00:00', '3124123123', 'Administrator', 'A001', '上海三禄贸易刀具', '2019-05-09 00:00:00', '', '', '', 'Administrator', '2019-04-29', '1', '2019-04-29 00:15:43', '12000.00');

-- ----------------------------
-- Table structure for account_input_invoice_info
-- ----------------------------
DROP TABLE IF EXISTS `account_input_invoice_info`;
CREATE TABLE `account_input_invoice_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账款--进项发票详情',
  `otherid` bigint(20) DEFAULT NULL COMMENT '进项发票id',
  `order_source` varchar(255) DEFAULT NULL COMMENT '来源单据',
  `order_no` varchar(255) DEFAULT NULL COMMENT '订单编号',
  `order_num` varchar(255) DEFAULT NULL COMMENT '序号',
  `product_no` varchar(255) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `invoice_name` varchar(255) DEFAULT NULL COMMENT '发票品名',
  `unit` varchar(255) DEFAULT NULL COMMENT '单位',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `money` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `tax` varchar(255) DEFAULT NULL COMMENT '税率',
  `tax_money` decimal(10,2) DEFAULT NULL COMMENT '税额',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_input_invoice_info
-- ----------------------------
INSERT INTO `account_input_invoice_info` VALUES ('5', '5', '采购入库单', 'A1904280001', null, 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', '1234', '斤', '100', '60.00', '6000.00', '0.16', '960.00', null, '2019-04-29 00:15:43');

-- ----------------------------
-- Table structure for account_payable
-- ----------------------------
DROP TABLE IF EXISTS `account_payable`;
CREATE TABLE `account_payable` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '账款--应付账款冲账',
  `rush_date` datetime DEFAULT NULL COMMENT '冲账日期',
  `order_no` varchar(255) DEFAULT NULL COMMENT '冲账编号',
  `early_order` tinyint(1) DEFAULT NULL COMMENT '早起单据',
  `supplier_no` varchar(255) DEFAULT NULL COMMENT '供应商编号',
  `supplier_no_str` varchar(255) DEFAULT NULL COMMENT '供应商编号',
  `currency` varchar(50) DEFAULT NULL COMMENT '币别',
  `rush_currency` varchar(50) DEFAULT NULL COMMENT '被冲单据币别',
  `exchange_rate` varchar(255) DEFAULT NULL COMMENT '汇率',
  `process_people` varchar(50) DEFAULT NULL COMMENT '经办人',
  `process_people_str` varchar(255) DEFAULT NULL COMMENT '经办人描述',
  `made_poeple` varchar(255) DEFAULT NULL COMMENT '制单人',
  `audit` varchar(255) DEFAULT NULL COMMENT '审核人',
  `audit_str` varchar(255) DEFAULT NULL COMMENT '审核人描述',
  `last_update` varchar(255) DEFAULT NULL COMMENT '最后修改人',
  `last_update_str` varchar(255) DEFAULT NULL COMMENT '最后修改人描述',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  `order_audit` tinyint(1) DEFAULT '0' COMMENT '订单审核状态',
  `money` decimal(10,2) DEFAULT NULL COMMENT '账款累计',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='账款--应付账款冲账';

-- ----------------------------
-- Records of account_payable
-- ----------------------------
INSERT INTO `account_payable` VALUES ('11', '2019-04-29 00:00:00', 'A1904290001', '0', 'A001', '上海三禄贸易刀具', 'RMB', 'RMB', '', '(A005)财务测试人员', '财务测试人员', 'Administrator', '', '', 'Administrator', '2019-04-29', '', '2019-04-29 00:25:16', null, '12000.00');

-- ----------------------------
-- Table structure for account_payable_info
-- ----------------------------
DROP TABLE IF EXISTS `account_payable_info`;
CREATE TABLE `account_payable_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '应付账款冲账--收款方式详情',
  `otherid` bigint(20) DEFAULT NULL COMMENT '应付账款冲账id',
  `payment_method` varchar(50) DEFAULT NULL COMMENT '付款方式',
  `payment_money` decimal(10,2) DEFAULT NULL COMMENT '付款金额',
  `supplier` varchar(100) DEFAULT NULL COMMENT '供应商',
  `invoice_no` varchar(100) DEFAULT NULL COMMENT '发票号码',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_payable_info
-- ----------------------------

-- ----------------------------
-- Table structure for account_prepayment
-- ----------------------------
DROP TABLE IF EXISTS `account_prepayment`;
CREATE TABLE `account_prepayment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账款--预付账款',
  `create_date` datetime DEFAULT NULL COMMENT '付款日期',
  `order_no` varchar(255) DEFAULT NULL COMMENT '付款编号',
  `customer_no` varchar(255) DEFAULT NULL COMMENT '客户编号',
  `customer_no_str` varchar(255) DEFAULT NULL COMMENT '客户编号描述',
  `currency` varchar(255) DEFAULT NULL COMMENT '付款币别',
  `currency_two` varchar(255) DEFAULT NULL COMMENT '被转换的付款币别',
  `rate` varchar(255) DEFAULT NULL COMMENT '汇率',
  `payment_type` varchar(255) DEFAULT NULL COMMENT '付款方式',
  `payment_money` decimal(10,2) DEFAULT NULL COMMENT '付款金额',
  `process_people` varchar(255) DEFAULT NULL COMMENT '经办人',
  `process_people_str` varchar(255) DEFAULT NULL COMMENT '经办人描述',
  `token_no` varchar(255) DEFAULT NULL COMMENT '凭证编号',
  `made_people` varchar(255) DEFAULT NULL COMMENT '制单人',
  `prepayment_money` decimal(10,2) DEFAULT NULL COMMENT '预付金额',
  `invoice_no` varchar(255) DEFAULT NULL COMMENT '发票号码',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `last_update` varchar(255) DEFAULT NULL COMMENT '最后修改人',
  `last_update_str` varchar(255) DEFAULT NULL COMMENT '最后修改人描述',
  `audit` varchar(255) DEFAULT NULL COMMENT '审核人',
  `audit_str` varchar(255) DEFAULT NULL COMMENT '审核人描述',
  `order_audit` tinyint(1) DEFAULT NULL COMMENT '单据审核状态',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_prepayment
-- ----------------------------
INSERT INTO `account_prepayment` VALUES ('3', '2019-04-29 00:00:00', 'A1904290001', 'A001', '上海三禄贸易刀具', 'RMB', 'RMB', '0.73', '转账', '10000.00', '(A005)财务测试人员', '财务测试人员', '', 'Administrator', '10000.00', '', '', 'Administrator', '2019-04-29', '', '', '1', '2019-04-29 00:03:04');

-- ----------------------------
-- Table structure for account_prepayment_info
-- ----------------------------
DROP TABLE IF EXISTS `account_prepayment_info`;
CREATE TABLE `account_prepayment_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账款--预付款',
  `otherid` bigint(20) DEFAULT NULL COMMENT '预付款id',
  `prepayment_account` varchar(255) DEFAULT NULL COMMENT '预付款账号',
  `supplier_short` varchar(255) DEFAULT NULL COMMENT '供应商简称',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `enter_box` varchar(255) DEFAULT NULL COMMENT '装箱单号',
  `order_audit` tinyint(1) DEFAULT NULL COMMENT '单据审核状态',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_prepayment_info
-- ----------------------------

-- ----------------------------
-- Table structure for account_receipt
-- ----------------------------
DROP TABLE IF EXISTS `account_receipt`;
CREATE TABLE `account_receipt` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账款-收款单',
  `create_date` datetime DEFAULT NULL COMMENT '收款日期',
  `order_no` varchar(255) DEFAULT NULL COMMENT '收款编号',
  `customer_no` varchar(255) DEFAULT NULL COMMENT '客户编号',
  `customer_no_str` varchar(255) DEFAULT NULL COMMENT '客户编号描述',
  `currency` varchar(255) DEFAULT NULL COMMENT '收款币别',
  `currency_type` varchar(255) DEFAULT NULL COMMENT '交易类型',
  `rate` varchar(20) DEFAULT NULL COMMENT '汇率',
  `receipt_method` varchar(255) DEFAULT NULL COMMENT '收款方式（支票、现金）',
  `receipt_type` varchar(255) DEFAULT NULL COMMENT '收款类型（收款、退款）',
  `receipt_money` varchar(255) DEFAULT NULL COMMENT '收款金额',
  `money` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `receipt_people` varchar(255) DEFAULT NULL COMMENT '收款人',
  `receipt_people_str` varchar(255) DEFAULT NULL COMMENT '收款人描述',
  `token_no` varchar(255) DEFAULT NULL COMMENT '凭证编号',
  `made_people` varchar(255) DEFAULT NULL COMMENT '制单人',
  `token_date` datetime DEFAULT NULL COMMENT '凭证日期',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `audit` varchar(255) DEFAULT NULL COMMENT '审核人',
  `audit_str` varchar(255) DEFAULT NULL COMMENT '审核人描述',
  `last_update` varchar(255) DEFAULT NULL COMMENT '最后修改人',
  `last_update_str` varchar(255) DEFAULT NULL COMMENT '最后修改人描述',
  `not_receipt_money` tinyint(1) DEFAULT NULL COMMENT '未收款冲账',
  `order_audit` tinyint(1) DEFAULT NULL COMMENT '订单审核状态',
  `addtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_receipt
-- ----------------------------
INSERT INTO `account_receipt` VALUES ('6', '2019-04-28 00:00:00', 'A1904280001', '20190428215126', '上海君之道信息有限公司', 'RMB', 'RMB', '0.73', '转账', '收款', null, '10000.00', '(A005)财务测试人员', '财务测试人员', '', 'Administrator', '2019-04-28 00:00:00', '', 'Administrator', '2019-04-28', 'Administrator', '2019-04-28', '0', '1', '2019-04-28 23:50:24');

-- ----------------------------
-- Table structure for account_receipt_info
-- ----------------------------
DROP TABLE IF EXISTS `account_receipt_info`;
CREATE TABLE `account_receipt_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账款-收款单-详情',
  `otherid` bigint(20) DEFAULT NULL COMMENT '收款单id',
  `no` varchar(255) DEFAULT NULL COMMENT '编号',
  `customer` varchar(255) DEFAULT NULL COMMENT '客户',
  `receipt_not` varchar(255) DEFAULT NULL COMMENT '未收款冲款',
  `addtime` datetime DEFAULT NULL COMMENT '日期',
  `price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `dollar` varchar(50) DEFAULT NULL COMMENT '美元',
  `order_no` varchar(50) DEFAULT NULL COMMENT '订单编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_receipt_info
-- ----------------------------

-- ----------------------------
-- Table structure for account_receivable
-- ----------------------------
DROP TABLE IF EXISTS `account_receivable`;
CREATE TABLE `account_receivable` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账款-应收账款冲款',
  `rush_date` datetime DEFAULT NULL COMMENT '冲账日期',
  `order_no` varchar(255) DEFAULT NULL COMMENT '冲账编号',
  `early_order` tinyint(1) DEFAULT NULL COMMENT '前期单据',
  `early_balance` varchar(255) DEFAULT NULL COMMENT '前期余额',
  `customer_no` varchar(255) DEFAULT NULL COMMENT '客户编号',
  `customer_str` varchar(255) DEFAULT NULL COMMENT '客户编号描述',
  `currency` int(11) DEFAULT NULL COMMENT '币别',
  `rush_currency` int(11) DEFAULT NULL COMMENT '被冲款币别',
  `exchange_rate` varchar(255) DEFAULT NULL COMMENT '汇率',
  `receive_people` bigint(20) DEFAULT NULL COMMENT '收款人',
  `receive_people_str` varchar(255) DEFAULT NULL COMMENT '收款人描述',
  `made_people` varchar(255) DEFAULT NULL COMMENT '制单人',
  `receive_method` int(11) DEFAULT NULL COMMENT '收款方式',
  `receive_money` decimal(10,2) DEFAULT NULL COMMENT '收款金额',
  `audit` varchar(255) DEFAULT NULL COMMENT '审核人',
  `audit_str` varchar(255) DEFAULT NULL COMMENT '审核人描述',
  `last_update` varchar(255) DEFAULT NULL COMMENT '最后修改人',
  `last_update_str` varchar(255) DEFAULT NULL COMMENT '最后修改人描述',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  `can_rush` decimal(10,2) DEFAULT NULL COMMENT '可冲金额',
  `order_audit` tinyint(1) DEFAULT '0' COMMENT '订单审核状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='账款-应收账款冲款';

-- ----------------------------
-- Records of account_receivable
-- ----------------------------
INSERT INTO `account_receivable` VALUES ('11', '2019-04-29 00:00:00', 'A1904290001', '0', '1000', '20190428215126', '上海君之道信息有限公司', '0', '0', '', '0', '财务测试人员', 'Administrator', '0', '1000.00', '', '', 'Administrator', '2019-04-29', '', '2019-04-29 00:55:50', '-9800.00', '0');

-- ----------------------------
-- Table structure for account_receivable_fee
-- ----------------------------
DROP TABLE IF EXISTS `account_receivable_fee`;
CREATE TABLE `account_receivable_fee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '应收账款冲款-收款方式及收费金额',
  `otherid` bigint(20) DEFAULT NULL COMMENT '应收账款冲账id',
  `receive_no` varchar(255) DEFAULT NULL COMMENT '收款单号',
  `rush_money_can` decimal(10,2) DEFAULT NULL COMMENT '可冲金额',
  `rush_money_now` decimal(10,2) DEFAULT NULL COMMENT '本次冲减',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应收账款冲款-收款方式及收费金额';

-- ----------------------------
-- Records of account_receivable_fee
-- ----------------------------

-- ----------------------------
-- Table structure for account_receivable_rush
-- ----------------------------
DROP TABLE IF EXISTS `account_receivable_rush`;
CREATE TABLE `account_receivable_rush` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '应收账款冲账--单据详情',
  `otherid` bigint(20) DEFAULT NULL COMMENT '应收账款冲账id',
  `sale_no` varchar(255) DEFAULT NULL COMMENT '销项发票编号',
  `invoice_no` varchar(255) DEFAULT NULL COMMENT '发票号码',
  `account_date` datetime DEFAULT NULL COMMENT '账款日期',
  `total_money` decimal(10,2) DEFAULT NULL COMMENT '单据总金额',
  `receive` decimal(10,2) DEFAULT NULL COMMENT '本次应收',
  `discount` varchar(50) DEFAULT NULL COMMENT '本次折让',
  `rush_money` decimal(10,2) DEFAULT NULL COMMENT '已冲减金',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应收账款冲账--单据详情';

-- ----------------------------
-- Records of account_receivable_rush
-- ----------------------------

-- ----------------------------
-- Table structure for account_sale_invoice
-- ----------------------------
DROP TABLE IF EXISTS `account_sale_invoice`;
CREATE TABLE `account_sale_invoice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账款--销项发票',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `sale_goods_no` varchar(255) DEFAULT NULL COMMENT '销售单单号',
  `order_no` varchar(50) DEFAULT NULL COMMENT '单号',
  `invoice_type` varchar(255) DEFAULT NULL COMMENT '发票类型',
  `invoice_date` datetime DEFAULT NULL COMMENT '发票日期',
  `invoice_no` varchar(255) DEFAULT NULL COMMENT '发票号码',
  `made_people` varchar(255) DEFAULT NULL COMMENT '制单人',
  `customer_no` varchar(255) DEFAULT NULL COMMENT '客户编号',
  `customer_no_str` varchar(255) DEFAULT NULL COMMENT '客户编号描述',
  `invoice_title` varchar(255) DEFAULT NULL COMMENT '发票抬头',
  `invoice_address` varchar(255) DEFAULT NULL COMMENT '发票地址',
  `currency` varchar(255) DEFAULT NULL COMMENT '币别',
  `taxpayer_no` varchar(255) DEFAULT NULL COMMENT '纳税人登记号',
  `bank` varchar(255) DEFAULT NULL COMMENT '开户银行',
  `bank_no` varchar(255) DEFAULT NULL COMMENT '银行账号',
  `payment_method` varchar(255) DEFAULT NULL COMMENT '结算方式',
  `payment_date` datetime DEFAULT NULL COMMENT '结算日期',
  `company` varchar(255) DEFAULT NULL COMMENT '所属公司',
  `audit` varchar(255) DEFAULT NULL COMMENT '审核人',
  `audit_str` varchar(255) DEFAULT NULL COMMENT '审核人描述',
  `last_update` varchar(255) DEFAULT NULL COMMENT '最后修改人',
  `last_update_str` varchar(255) DEFAULT NULL COMMENT '最后修改人描述',
  `order_audit` tinyint(1) DEFAULT NULL COMMENT '订单审核状态',
  `addtime` datetime DEFAULT NULL,
  `order_cancel` tinyint(1) DEFAULT '0' COMMENT '单据作废',
  `money` decimal(10,2) DEFAULT '0.00' COMMENT '发票金额',
  `receive_money` decimal(10,2) DEFAULT '0.00' COMMENT '冲账金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='账款--销项发票';

-- ----------------------------
-- Records of account_sale_invoice
-- ----------------------------
INSERT INTO `account_sale_invoice` VALUES ('9', '2019-04-29 00:00:00', null, 'A1904290001', '气动工具', '2019-04-29 00:00:00', '1231', 'Administrator', '20190428215126', '上海君之道信息有限公司', '上海君之道信息有限公司', '上海市普陀区中江路638号天地软件园27号207', 'RMB', null, null, null, '先款后货', '2019-05-09 00:00:00', '三禄', 'Administrator', '2019-04-29', 'Administrator', '2019-04-29', '0', '2019-04-29 00:45:43', '0', '0.00', '0.00');
INSERT INTO `account_sale_invoice` VALUES ('10', '2019-04-29 00:00:00', null, 'A1904290002', '气动工具', null, '', 'Administrator', '', '上海君之道信息有限公司', '', '', 'RMB', '', '', '', '转账', '2019-05-09 00:00:00', '', 'Administrator', '2019-04-29', 'Administrator', '2019-04-29', '1', '2019-04-29 11:00:23', '0', '0.00', '0.00');

-- ----------------------------
-- Table structure for account_sale_invoice_info
-- ----------------------------
DROP TABLE IF EXISTS `account_sale_invoice_info`;
CREATE TABLE `account_sale_invoice_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账款-销项发票-发票明细',
  `otherid` bigint(20) DEFAULT NULL COMMENT '销项发票id',
  `order_soruce` varchar(255) DEFAULT NULL COMMENT '单据来源',
  `order_no` varchar(255) DEFAULT NULL COMMENT '订单号',
  `no` varchar(255) DEFAULT NULL COMMENT '序号',
  `customer_no` varchar(255) DEFAULT NULL COMMENT '客户编号',
  `customer_str` varchar(50) DEFAULT NULL COMMENT '客户简称',
  `product_no` varchar(255) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(100) DEFAULT NULL COMMENT '产品名称',
  `invoce_name` varchar(100) DEFAULT NULL COMMENT '发票品名',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  `unit` varchar(255) DEFAULT NULL COMMENT '单位',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `price` decimal(12,2) DEFAULT NULL COMMENT '单价',
  `money` decimal(12,2) DEFAULT NULL COMMENT '金额',
  `rate` varchar(255) DEFAULT NULL COMMENT '税率',
  `rate_money` decimal(12,2) DEFAULT NULL COMMENT '税额',
  `tax` varchar(255) DEFAULT NULL COMMENT '税别',
  `rate_not` decimal(12,2) DEFAULT NULL COMMENT '未税小计',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='账款-销项发票-发票明细';

-- ----------------------------
-- Records of account_sale_invoice_info
-- ----------------------------
INSERT INTO `account_sale_invoice_info` VALUES ('14', '9', '采购出库单', 'A1904280001', '1', '20190428215126', '上海君之道信息有限公司', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', '123', '2019-04-29 00:45:44', '斤', '20', '100.00', '2000.00', '0.16', '320.00', '外加', null, null);
INSERT INTO `account_sale_invoice_info` VALUES ('15', '10', '销货单', 'A1904290002', '1', '20190428215126', '上海君之道信息有限公司', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', null, '2019-04-29 11:00:23', '斤', '20', '80.00', '64.00', '0.16', '10.24', '外加', '64.00', null);
INSERT INTO `account_sale_invoice_info` VALUES ('16', '10', '销售退货单', 'A1904280001', '1', '20190428215126', '上海君之道信息有限公司', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', null, '2019-04-29 11:00:23', '斤', '2', '100.00', '200.00', '0.16', '32.00', '外加', '200.00', null);
INSERT INTO `account_sale_invoice_info` VALUES ('17', '10', '分角调整', null, null, null, null, null, null, null, '2019-04-29 11:00:23', null, null, null, '0.76', null, null, null, null, null);
INSERT INTO `account_sale_invoice_info` VALUES ('18', '10', '分角调整', null, null, null, null, null, null, null, '2019-04-29 11:00:23', null, null, null, '1.00', null, null, null, null, null);
INSERT INTO `account_sale_invoice_info` VALUES ('19', '10', '分角调整', null, null, null, null, null, null, null, '2019-04-29 11:00:23', null, null, null, '-90.00', null, null, null, null, null);

-- ----------------------------
-- Table structure for area
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
  `areaid` int(20) NOT NULL AUTO_INCREMENT COMMENT '地区Id',
  `areacode` varchar(50) NOT NULL COMMENT '地区编码',
  `areaname` varchar(20) NOT NULL COMMENT '地区名',
  `level` tinyint(4) DEFAULT '-1' COMMENT '地区级别（1:省份province,2:市city,3:区县district,4:街道street）',
  `citycode` varchar(50) DEFAULT NULL COMMENT '城市编码',
  `center` varchar(50) DEFAULT NULL COMMENT '城市中心点（即：经纬度坐标）',
  `parentid` int(20) DEFAULT '-1' COMMENT '地区父节点',
  PRIMARY KEY (`areaid`),
  KEY `areaCode` (`areacode`),
  KEY `parentId` (`parentid`),
  KEY `level` (`level`),
  KEY `areaName` (`areaname`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='地区码表';

-- ----------------------------
-- Records of area
-- ----------------------------
INSERT INTO `area` VALUES ('1', '110000', '北京市', '1', '010', '116.407394,39.904211', '-1');
INSERT INTO `area` VALUES ('2', '110100', '北京城区', '2', '010', '116.407394,39.904211', '1');
INSERT INTO `area` VALUES ('3', '110101', '东城区', '3', '010', '116.41649,39.928341', '2');
INSERT INTO `area` VALUES ('4', '110102', '西城区', '3', '010', '116.365873,39.912235', '2');
INSERT INTO `area` VALUES ('5', '110105', '朝阳区', '3', '010', '116.443205,39.921506', '2');
INSERT INTO `area` VALUES ('6', '110106', '丰台区', '3', '010', '116.287039,39.858421', '2');
INSERT INTO `area` VALUES ('7', '110107', '石景山区', '3', '010', '116.222933,39.906611', '2');
INSERT INTO `area` VALUES ('8', '110108', '海淀区', '3', '010', '116.298262,39.95993', '2');
INSERT INTO `area` VALUES ('9', '110109', '门头沟区', '3', '010', '116.101719,39.940338', '2');
INSERT INTO `area` VALUES ('10', '110111', '房山区', '3', '010', '116.143486,39.748823', '2');
INSERT INTO `area` VALUES ('11', '110112', '通州区', '3', '010', '116.656434,39.909946', '2');
INSERT INTO `area` VALUES ('12', '110113', '顺义区', '3', '010', '116.654642,40.130211', '2');
INSERT INTO `area` VALUES ('13', '110114', '昌平区', '3', '010', '116.231254,40.220804', '2');
INSERT INTO `area` VALUES ('14', '110115', '大兴区', '3', '010', '116.341483,39.726917', '2');
INSERT INTO `area` VALUES ('15', '110116', '怀柔区', '3', '010', '116.631931,40.316053', '2');
INSERT INTO `area` VALUES ('16', '110117', '平谷区', '3', '010', '117.121351,40.140595', '2');
INSERT INTO `area` VALUES ('17', '110118', '密云区', '3', '010', '116.843047,40.376894', '2');
INSERT INTO `area` VALUES ('18', '110119', '延庆区', '3', '010', '115.974981,40.456591', '2');
INSERT INTO `area` VALUES ('19', '120000', '天津市', '1', '022', '117.200983,39.084158', '-1');
INSERT INTO `area` VALUES ('20', '120100', '天津城区', '2', '022', '117.200983,39.084158', '19');
INSERT INTO `area` VALUES ('21', '120101', '和平区', '3', '022', '117.214699,39.117196', '20');
INSERT INTO `area` VALUES ('22', '120102', '河东区', '3', '022', '117.251584,39.128294', '20');
INSERT INTO `area` VALUES ('23', '120103', '河西区', '3', '022', '117.223371,39.109563', '20');
INSERT INTO `area` VALUES ('24', '120104', '南开区', '3', '022', '117.150738,39.138205', '20');
INSERT INTO `area` VALUES ('25', '120105', '河北区', '3', '022', '117.196648,39.147869', '20');
INSERT INTO `area` VALUES ('26', '120106', '红桥区', '3', '022', '117.151533,39.167345', '20');
INSERT INTO `area` VALUES ('27', '120110', '东丽区', '3', '022', '117.31362,39.086802', '20');
INSERT INTO `area` VALUES ('28', '120111', '西青区', '3', '022', '117.008826,39.141152', '20');
INSERT INTO `area` VALUES ('29', '120112', '津南区', '3', '022', '117.35726,38.937928', '20');
INSERT INTO `area` VALUES ('30', '120113', '北辰区', '3', '022', '117.135488,39.224791', '20');
INSERT INTO `area` VALUES ('31', '120114', '武清区', '3', '022', '117.044387,39.384119', '20');
INSERT INTO `area` VALUES ('32', '120115', '宝坻区', '3', '022', '117.309874,39.717564', '20');
INSERT INTO `area` VALUES ('33', '120116', '滨海新区', '3', '022', '117.698407,39.01727', '20');
INSERT INTO `area` VALUES ('34', '120117', '宁河区', '3', '022', '117.826724,39.330087', '20');
INSERT INTO `area` VALUES ('35', '120118', '静海区', '3', '022', '116.974232,38.94745', '20');
INSERT INTO `area` VALUES ('36', '120119', '蓟州区', '3', '022', '117.408296,40.045851', '20');
INSERT INTO `area` VALUES ('37', '130000', '河北省', '1', '[]', '114.530235,38.037433', '-1');
INSERT INTO `area` VALUES ('38', '130100', '石家庄市', '2', '0311', '114.514793,38.042228', '37');
INSERT INTO `area` VALUES ('39', '130102', '长安区', '3', '0311', '114.539395,38.036347', '38');
INSERT INTO `area` VALUES ('40', '130104', '桥西区', '3', '0311', '114.461088,38.004193', '38');
INSERT INTO `area` VALUES ('41', '130105', '新华区', '3', '0311', '114.463377,38.05095', '38');
INSERT INTO `area` VALUES ('42', '130107', '井陉矿区', '3', '0311', '114.062062,38.065151', '38');

-- ----------------------------
-- Table structure for basic_unit
-- ----------------------------
DROP TABLE IF EXISTS `basic_unit`;
CREATE TABLE `basic_unit` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `title` varchar(255) DEFAULT '' COMMENT '标题',
  `remarks` varchar(255) DEFAULT '' COMMENT '描述',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of basic_unit
-- ----------------------------
INSERT INTO `basic_unit` VALUES ('1', '0', '斤', '', '2018-09-20 17:26:44');
INSERT INTO `basic_unit` VALUES ('2', '0', '公斤', '', '2018-09-20 17:26:44');
INSERT INTO `basic_unit` VALUES ('3', '0', '件', '', '2018-09-20 17:26:44');
INSERT INTO `basic_unit` VALUES ('4', '0', '片', '', '2018-09-20 17:26:44');
INSERT INTO `basic_unit` VALUES ('5', '0', '台', '', '2018-09-20 17:26:44');
INSERT INTO `basic_unit` VALUES ('6', '0', '箱', '', '2018-09-20 17:26:44');
INSERT INTO `basic_unit` VALUES ('7', '0', '只', '', '2018-09-20 17:26:44');
INSERT INTO `basic_unit` VALUES ('8', '0', '支', '', '2018-09-20 17:26:44');
INSERT INTO `basic_unit` VALUES ('9', '0', '组', '', '2018-09-20 17:26:44');
INSERT INTO `basic_unit` VALUES ('10', '0', '根', '', '2018-09-20 17:26:44');

-- ----------------------------
-- Table structure for check_data
-- ----------------------------
DROP TABLE IF EXISTS `check_data`;
CREATE TABLE `check_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orders` varchar(255) DEFAULT '' COMMENT '考勤编号',
  `depot` varchar(255) DEFAULT '' COMMENT '考勤部门',
  `starttime` date DEFAULT NULL COMMENT '考勤开始时间',
  `endtime` date DEFAULT NULL COMMENT '考勤结束时间',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `createpeople` varchar(255) DEFAULT '' COMMENT '建档人',
  `createdate` varchar(255) DEFAULT '' COMMENT '建档日期',
  `shpeople` varchar(255) DEFAULT '' COMMENT '审核人',
  `shdate` varchar(255) DEFAULT '' COMMENT '审核日期',
  `updatepeople` varchar(255) DEFAULT '' COMMENT '最后更新人',
  `updatedate` varchar(255) DEFAULT '' COMMENT '最后更新日期',
  `shstatus` int(11) DEFAULT '0' COMMENT '审核状态 默认0 1、已审核',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考勤资料结转';

-- ----------------------------
-- Records of check_data
-- ----------------------------

-- ----------------------------
-- Table structure for check_data_employee
-- ----------------------------
DROP TABLE IF EXISTS `check_data_employee`;
CREATE TABLE `check_data_employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `jobnumber` varchar(255) DEFAULT '' COMMENT '工号',
  `workname` varchar(255) DEFAULT '' COMMENT '姓名',
  `workday` varchar(255) DEFAULT '' COMMENT '工作日',
  `checkday` varchar(255) DEFAULT '' COMMENT '出勤天数',
  `normalovertime` varchar(255) DEFAULT '' COMMENT '平时加班天数',
  `weekendovertime` varchar(255) DEFAULT '' COMMENT '周末加班天数',
  `festivalovertime` varchar(255) DEFAULT '' COMMENT '节日加班天数',
  `outworkday` varchar(255) DEFAULT '' COMMENT '出差天数',
  `thingvacationday` varchar(255) DEFAULT '' COMMENT '事假天数',
  `illnessvacationday` varchar(255) DEFAULT '' COMMENT '病假天数',
  `paidvacationday` varchar(255) DEFAULT '' COMMENT '带薪假天数',
  `absenteeismday` varchar(255) DEFAULT '' COMMENT '旷工天数',
  `latetime` varchar(255) DEFAULT '' COMMENT '迟到次数',
  `lateminute` varchar(255) DEFAULT '' COMMENT '迟到分钟',
  `leaveearlytime` varchar(255) DEFAULT '' COMMENT '早退次数',
  `leaveearlyminute` varchar(255) DEFAULT '' COMMENT '早退分钟',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `checkdataid` bigint(20) DEFAULT '0' COMMENT '考勤资料转结编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考勤资料结转员工情况';

-- ----------------------------
-- Records of check_data_employee
-- ----------------------------

-- ----------------------------
-- Table structure for check_data_leaveearly
-- ----------------------------
DROP TABLE IF EXISTS `check_data_leaveearly`;
CREATE TABLE `check_data_leaveearly` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `jobnumber` varchar(255) DEFAULT '' COMMENT '工号',
  `workname` varchar(255) DEFAULT '' COMMENT '姓名',
  `addtime` varchar(255) DEFAULT '' COMMENT '添加时间',
  `leaveearlytime` varchar(255) DEFAULT '' COMMENT '早退次数',
  `leaveearlyminute` varchar(255) DEFAULT '' COMMENT '早退分钟',
  `checkdataid` bigint(20) DEFAULT '0' COMMENT '考勤资料编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='早退';

-- ----------------------------
-- Records of check_data_leaveearly
-- ----------------------------

-- ----------------------------
-- Table structure for check_order
-- ----------------------------
DROP TABLE IF EXISTS `check_order`;
CREATE TABLE `check_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `checkdate` date DEFAULT NULL COMMENT '制单日期',
  `checkorder` varchar(255) DEFAULT '' COMMENT '申请单号',
  `depotorder` varchar(255) DEFAULT '' COMMENT '部门编号',
  `depotname` varchar(255) DEFAULT '' COMMENT '部门名称',
  `applicantorder` varchar(255) DEFAULT '' COMMENT '申请人编号',
  `applicantname` varchar(255) DEFAULT '' COMMENT '申请人名称',
  `applytype` varchar(255) DEFAULT '' COMMENT '申请人类型',
  `applydes` varchar(255) DEFAULT '' COMMENT '申请人明细',
  `startdate` datetime DEFAULT NULL COMMENT '开始时间',
  `enddate` datetime DEFAULT NULL COMMENT '结束时间',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `createpeople` varchar(255) DEFAULT '' COMMENT '制单人',
  `shpeople` varchar(255) DEFAULT '' COMMENT '审核人',
  `shdate` varchar(255) DEFAULT '' COMMENT '审核日期',
  `updatepeople` varchar(255) DEFAULT '' COMMENT '最后修改人',
  `updatedate` varchar(255) DEFAULT '' COMMENT '最后修改日期',
  `shstatus` int(11) DEFAULT NULL COMMENT '审核状态0、默认未审核 1 、已审核',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of check_order
-- ----------------------------

-- ----------------------------
-- Table structure for check_order_employee
-- ----------------------------
DROP TABLE IF EXISTS `check_order_employee`;
CREATE TABLE `check_order_employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `employeeorder` varchar(255) DEFAULT '' COMMENT '员工编号',
  `employeename` varchar(255) DEFAULT '' COMMENT '员工姓名',
  `checkid` bigint(20) DEFAULT '0' COMMENT '请假/加班/出差申请单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of check_order_employee
-- ----------------------------

-- ----------------------------
-- Table structure for check_stock
-- ----------------------------
DROP TABLE IF EXISTS `check_stock`;
CREATE TABLE `check_stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `checkdate` date DEFAULT NULL COMMENT '盘点日期',
  `checkorder` varchar(255) DEFAULT '' COMMENT '盘点单号',
  `depotorder` varchar(255) DEFAULT '' COMMENT '盘点仓库编号',
  `depotname` varchar(255) DEFAULT '' COMMENT '盘点仓库名称',
  `createpeople` varchar(255) DEFAULT '' COMMENT '制单人',
  `shpeople` varchar(255) DEFAULT '' COMMENT '审核人',
  `shdate` varchar(255) DEFAULT '' COMMENT '审核时间',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `shstatus` int(11) DEFAULT '0' COMMENT '审核状态  0、未审核  1、已审核',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='盘库作业';

-- ----------------------------
-- Records of check_stock
-- ----------------------------
INSERT INTO `check_stock` VALUES ('4', '2019-04-28', 'A1904280001', '309', 'A区仓库', 'Administrator', '', '', '', '1');
INSERT INTO `check_stock` VALUES ('5', '2019-04-29', 'A1904290001', '309', 'A区仓库', 'Administrator', 'Administrator', '2019-04-29', '', '1');
INSERT INTO `check_stock` VALUES ('6', '2019-05-05', 'A1905050001', '310', 'B区仓库', 'Administrator', 'Administrator', '2019-05-05', '', '1');

-- ----------------------------
-- Table structure for check_stock_product
-- ----------------------------
DROP TABLE IF EXISTS `check_stock_product`;
CREATE TABLE `check_stock_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `productorder` varchar(255) DEFAULT '' COMMENT '产品编号',
  `productname` varchar(255) DEFAULT '' COMMENT '产品名称',
  `depotorder` varchar(255) DEFAULT '' COMMENT '库位编号',
  `depotname` varchar(255) DEFAULT '' COMMENT '库位名称',
  `depotnum` int(11) DEFAULT '0' COMMENT '库存数量',
  `nownum` int(11) DEFAULT '0' COMMENT '实盘数量',
  `productunit` varchar(255) DEFAULT '' COMMENT '单位',
  `profitandloss` varchar(255) DEFAULT '' COMMENT '盘盈/盘输',
  `productprice` double DEFAULT '0' COMMENT '单价',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `checkid` bigint(20) DEFAULT '0' COMMENT '盘库作业编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='盘库作业产品';

-- ----------------------------
-- Records of check_stock_product
-- ----------------------------
INSERT INTO `check_stock_product` VALUES ('5', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', 'A001', '4楼库位', '100', '110', '斤', '盘盈', '100', '', '4');
INSERT INTO `check_stock_product` VALUES ('6', 'SGSDS', 'SGSDS', 'A001', '4楼库位', '30', '40', '斤', '盘盈', '100', '', '5');
INSERT INTO `check_stock_product` VALUES ('7', 'KANDAK 16LGH89', 'KANDAK 16LGH89', 'A002', 'A002', '300', '301', '斤', '盘盈', '0', '', '6');

-- ----------------------------
-- Table structure for citys
-- ----------------------------
DROP TABLE IF EXISTS `citys`;
CREATE TABLE `citys` (
  `districts_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一编号',
  `addr_code` varchar(30) NOT NULL COMMENT '地区编号',
  `addr_name` varchar(512) NOT NULL COMMENT '地区名称',
  `father_code` varchar(30) NOT NULL COMMENT '父编号',
  `type` varchar(2) NOT NULL COMMENT '类型，01：省，02：市，03：区县',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`districts_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3657 DEFAULT CHARSET=utf8 COMMENT='区县级数据字典';

-- ----------------------------
-- Records of citys
-- ----------------------------
INSERT INTO `citys` VALUES ('1', '110101', '东城区', '110100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2', '110102', '西城区', '110100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('3', '110105', '朝阳区', '110100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('4', '110106', '丰台区', '110100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('5', '110107', '石景山区', '110100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('6', '110108', '海淀区', '110100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('7', '110109', '门头沟区', '110100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('8', '110111', '房山区', '110100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('9', '110112', '通州区', '110100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('10', '110113', '顺义区', '110100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('11', '110114', '昌平区', '110100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('12', '110115', '大兴区', '110100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('13', '110116', '怀柔区', '110100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('14', '110117', '平谷区', '110100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('15', '110118', '密云区', '110100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('16', '110119', '延庆区', '110100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('17', '110100', '北京市', '100001', '02', '2017-05-26 17:44:09', '2017-06-06 16:53:51', 'B');
INSERT INTO `citys` VALUES ('18', '120101', '和平区', '120100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('19', '120102', '河东区', '120100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('20', '120103', '河西区', '120100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('21', '120104', '南开区', '120100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('22', '120105', '河北区', '120100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('23', '120106', '红桥区', '120100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('24', '120110', '东丽区', '120100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('25', '120111', '西青区', '120100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('26', '120112', '津南区', '120100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('27', '120113', '北辰区', '120100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('28', '120114', '武清区', '120100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('29', '120115', '宝坻区', '120100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('30', '120116', '滨海新区', '120100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('31', '120117', '宁河区', '120100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('32', '120118', '静海区', '120100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('33', '120119', '蓟州区', '120100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('34', '120100', '天津市', '100002', '02', '2017-05-26 17:44:09', '2017-06-06 16:54:05', 'T');
INSERT INTO `citys` VALUES ('35', '130101', '市辖区', '130100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('36', '130102', '长安区', '130100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('37', '130104', '桥西区', '130100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('38', '130105', '新华区', '130100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('39', '130107', '井陉矿区', '130100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('40', '130108', '裕华区', '130100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('41', '130109', '藁城区', '130100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('42', '130110', '鹿泉区', '130100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('43', '130111', '栾城区', '130100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('44', '130121', '井陉县', '130100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('45', '130123', '正定县', '130100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('46', '130125', '行唐县', '130100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('47', '130126', '灵寿县', '130100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('48', '130127', '高邑县', '130100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('49', '130128', '深泽县', '130100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('50', '130129', '赞皇县', '130100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('51', '130130', '无极县', '130100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('52', '130131', '平山县', '130100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('53', '130132', '元氏县', '130100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('54', '130133', '赵县', '130100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('55', '130183', '晋州市', '130100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('56', '130184', '新乐市', '130100', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('57', '130201', '市辖区', '130200', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('58', '130202', '路南区', '130200', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('59', '130203', '路北区', '130200', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('60', '130204', '古冶区', '130200', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('61', '130205', '开平区', '130200', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('62', '130207', '丰南区', '130200', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('63', '130208', '丰润区', '130200', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('64', '130209', '曹妃甸区', '130200', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('65', '130223', '滦县', '130200', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('66', '130224', '滦南县', '130200', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('67', '130225', '乐亭县', '130200', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('68', '130227', '迁西县', '130200', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('69', '130229', '玉田县', '130200', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('70', '130281', '遵化市', '130200', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('71', '130283', '迁安市', '130200', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('72', '130301', '市辖区', '130300', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('73', '130302', '海港区', '130300', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('74', '130303', '山海关区', '130300', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('75', '130304', '北戴河区', '130300', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('76', '130306', '抚宁区', '130300', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('77', '130321', '青龙满族自治县', '130300', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('78', '130322', '昌黎县', '130300', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('79', '130324', '卢龙县', '130300', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('80', '130401', '市辖区', '130400', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('81', '130402', '邯山区', '130400', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('82', '130403', '丛台区', '130400', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('83', '130404', '复兴区', '130400', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('84', '130406', '峰峰矿区', '130400', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('85', '130421', '邯郸县', '130400', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('86', '130423', '临漳县', '130400', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('87', '130424', '成安县', '130400', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('88', '130425', '大名县', '130400', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('89', '130426', '涉县', '130400', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('90', '130427', '磁县', '130400', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('91', '130428', '肥乡县', '130400', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('92', '130429', '永年县', '130400', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('93', '130430', '邱县', '130400', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('94', '130431', '鸡泽县', '130400', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('95', '130432', '广平县', '130400', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('96', '130433', '馆陶县', '130400', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('97', '130434', '魏县', '130400', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('98', '130435', '曲周县', '130400', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('99', '130481', '武安市', '130400', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('100', '130501', '市辖区', '130500', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('101', '130502', '桥东区', '130500', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('102', '130503', '桥西区', '130500', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('103', '130521', '邢台县', '130500', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('104', '130522', '临城县', '130500', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('105', '130523', '内丘县', '130500', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('106', '130524', '柏乡县', '130500', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('107', '130525', '隆尧县', '130500', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('108', '130526', '任县', '130500', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('109', '130527', '南和县', '130500', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('110', '130528', '宁晋县', '130500', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('111', '130529', '巨鹿县', '130500', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('112', '130530', '新河县', '130500', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('113', '130531', '广宗县', '130500', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('114', '130532', '平乡县', '130500', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('115', '130533', '威县', '130500', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('116', '130534', '清河县', '130500', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('117', '130535', '临西县', '130500', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('118', '130581', '南宫市', '130500', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('119', '130582', '沙河市', '130500', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('120', '130601', '市辖区', '130600', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('121', '130602', '竞秀区', '130600', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('122', '130606', '莲池区', '130600', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('123', '130607', '满城区', '130600', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('124', '130608', '清苑区', '130600', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('125', '130609', '徐水区', '130600', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('126', '130623', '涞水县', '130600', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('127', '130624', '阜平县', '130600', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('128', '130626', '定兴县', '130600', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('129', '130627', '唐县', '130600', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('130', '130628', '高阳县', '130600', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('131', '130629', '容城县', '130600', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('132', '130630', '涞源县', '130600', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('133', '130631', '望都县', '130600', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('134', '130632', '安新县', '130600', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('135', '130633', '易县', '130600', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('136', '130634', '曲阳县', '130600', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('137', '130635', '蠡县', '130600', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('138', '130636', '顺平县', '130600', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('139', '130637', '博野县', '130600', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('140', '130638', '雄县', '130600', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('141', '130681', '涿州市', '130600', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('142', '130683', '安国市', '130600', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('143', '130684', '高碑店市', '130600', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('144', '130701', '市辖区', '130700', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('145', '130702', '桥东区', '130700', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('146', '130703', '桥西区', '130700', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('147', '130705', '宣化区', '130700', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('148', '130706', '下花园区', '130700', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('149', '130708', '万全区', '130700', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('150', '130709', '崇礼区', '130700', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('151', '130722', '张北县', '130700', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('152', '130723', '康保县', '130700', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('153', '130724', '沽源县', '130700', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('154', '130725', '尚义县', '130700', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('155', '130726', '蔚县', '130700', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('156', '130727', '阳原县', '130700', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('157', '130728', '怀安县', '130700', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('158', '130730', '怀来县', '130700', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('159', '130731', '涿鹿县', '130700', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('160', '130732', '赤城县', '130700', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('161', '130801', '市辖区', '130800', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('162', '130802', '双桥区', '130800', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('163', '130803', '双滦区', '130800', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('164', '130804', '鹰手营子矿区', '130800', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('165', '130821', '承德县', '130800', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('166', '130822', '兴隆县', '130800', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('167', '130823', '平泉县', '130800', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('168', '130824', '滦平县', '130800', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('169', '130825', '隆化县', '130800', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('170', '130826', '丰宁满族自治县', '130800', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('171', '130827', '宽城满族自治县', '130800', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('172', '130828', '围场满族蒙古族自治县', '130800', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('173', '130901', '市辖区', '130900', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('174', '130902', '新华区', '130900', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('175', '130903', '运河区', '130900', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('176', '130921', '沧县', '130900', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('177', '130922', '青县', '130900', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('178', '130923', '东光县', '130900', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('179', '130924', '海兴县', '130900', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('180', '130925', '盐山县', '130900', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('181', '130926', '肃宁县', '130900', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('182', '130927', '南皮县', '130900', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('183', '130928', '吴桥县', '130900', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('184', '130929', '献县', '130900', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('185', '130930', '孟村回族自治县', '130900', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('186', '130981', '泊头市', '130900', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('187', '130982', '任丘市', '130900', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('188', '130983', '黄骅市', '130900', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('189', '130984', '河间市', '130900', '03', '2017-05-26 17:44:10', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('190', '131001', '市辖区', '131000', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('191', '131002', '安次区', '131000', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('192', '131003', '广阳区', '131000', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('193', '131022', '固安县', '131000', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('194', '131023', '永清县', '131000', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('195', '131024', '香河县', '131000', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('196', '131025', '大城县', '131000', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('197', '131026', '文安县', '131000', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('198', '131028', '大厂回族自治县', '131000', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('199', '131081', '霸州市', '131000', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('200', '131082', '三河市', '131000', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('201', '131101', '市辖区', '131100', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('202', '131102', '桃城区', '131100', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('203', '131103', '冀州区', '131100', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('204', '131121', '枣强县', '131100', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('205', '131122', '武邑县', '131100', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('206', '131123', '武强县', '131100', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('207', '131124', '饶阳县', '131100', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('208', '131125', '安平县', '131100', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('209', '131126', '故城县', '131100', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('210', '131127', '景县', '131100', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('211', '131128', '阜城县', '131100', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('212', '131182', '深州市', '131100', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('213', '139001', '定州市', '139000', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('214', '139002', '辛集市', '139000', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('215', '130100', '石家庄市', '100003', '02', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('216', '130200', '唐山市', '100003', '02', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('217', '130300', '秦皇岛市', '100003', '02', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('218', '130400', '邯郸市', '100003', '02', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('219', '130500', '邢台市', '100003', '02', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('220', '130600', '保定市', '100003', '02', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('221', '130700', '张家口市', '100003', '02', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('222', '130800', '承德市', '100003', '02', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('223', '130900', '沧州市', '100003', '02', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('224', '131000', '廊坊市', '100003', '02', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('225', '131100', '衡水市', '100003', '02', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('226', '139000', '省直辖县级行政区划', '100003', '02', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('227', '140101', '市辖区', '140100', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('228', '140105', '小店区', '140100', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('229', '140106', '迎泽区', '140100', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('230', '140107', '杏花岭区', '140100', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('231', '140108', '尖草坪区', '140100', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('232', '140109', '万柏林区', '140100', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('233', '140110', '晋源区', '140100', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('234', '140121', '清徐县', '140100', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('235', '140122', '阳曲县', '140100', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('236', '140123', '娄烦县', '140100', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('237', '140181', '古交市', '140100', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('238', '140201', '市辖区', '140200', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('239', '140202', '城区', '140200', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('240', '140203', '矿区', '140200', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('241', '140211', '南郊区', '140200', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('242', '140212', '新荣区', '140200', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('243', '140221', '阳高县', '140200', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('244', '140222', '天镇县', '140200', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('245', '140223', '广灵县', '140200', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('246', '140224', '灵丘县', '140200', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('247', '140225', '浑源县', '140200', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('248', '140226', '左云县', '140200', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('249', '140227', '大同县', '140200', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('250', '140301', '市辖区', '140300', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('251', '140302', '城区', '140300', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('252', '140303', '矿区', '140300', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('253', '140311', '郊区', '140300', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('254', '140321', '平定县', '140300', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('255', '140322', '盂县', '140300', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('256', '140401', '市辖区', '140400', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('257', '140402', '城区', '140400', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('258', '140411', '郊区', '140400', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('259', '140421', '长治县', '140400', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('260', '140423', '襄垣县', '140400', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('261', '140424', '屯留县', '140400', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('262', '140425', '平顺县', '140400', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('263', '140426', '黎城县', '140400', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('264', '140427', '壶关县', '140400', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('265', '140428', '长子县', '140400', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('266', '140429', '武乡县', '140400', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('267', '140430', '沁县', '140400', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('268', '140431', '沁源县', '140400', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('269', '140481', '潞城市', '140400', '03', '2017-05-26 17:44:11', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('270', '140501', '市辖区', '140500', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('271', '140502', '城区', '140500', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('272', '140521', '沁水县', '140500', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('273', '140522', '阳城县', '140500', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('274', '140524', '陵川县', '140500', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('275', '140525', '泽州县', '140500', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('276', '140581', '高平市', '140500', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('277', '140601', '市辖区', '140600', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('278', '140602', '朔城区', '140600', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('279', '140603', '平鲁区', '140600', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('280', '140621', '山阴县', '140600', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('281', '140622', '应县', '140600', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('282', '140623', '右玉县', '140600', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('283', '140624', '怀仁县', '140600', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('284', '140701', '市辖区', '140700', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('285', '140702', '榆次区', '140700', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('286', '140721', '榆社县', '140700', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('287', '140722', '左权县', '140700', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('288', '140723', '和顺县', '140700', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('289', '140724', '昔阳县', '140700', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('290', '140725', '寿阳县', '140700', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('291', '140726', '太谷县', '140700', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('292', '140727', '祁县', '140700', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('293', '140728', '平遥县', '140700', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('294', '140729', '灵石县', '140700', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('295', '140781', '介休市', '140700', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('296', '140801', '市辖区', '140800', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('297', '140802', '盐湖区', '140800', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('298', '140821', '临猗县', '140800', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('299', '140822', '万荣县', '140800', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('300', '140823', '闻喜县', '140800', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('301', '140824', '稷山县', '140800', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('302', '140825', '新绛县', '140800', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('303', '140826', '绛县', '140800', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('304', '140827', '垣曲县', '140800', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('305', '140828', '夏县', '140800', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('306', '140829', '平陆县', '140800', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('307', '140830', '芮城县', '140800', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('308', '140881', '永济市', '140800', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('309', '140882', '河津市', '140800', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('310', '140901', '市辖区', '140900', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('311', '140902', '忻府区', '140900', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('312', '140921', '定襄县', '140900', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('313', '140922', '五台县', '140900', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('314', '140923', '代县', '140900', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('315', '140924', '繁峙县', '140900', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('316', '140925', '宁武县', '140900', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('317', '140926', '静乐县', '140900', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('318', '140927', '神池县', '140900', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('319', '140928', '五寨县', '140900', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('320', '140929', '岢岚县', '140900', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('321', '140930', '河曲县', '140900', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('322', '140931', '保德县', '140900', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('323', '140932', '偏关县', '140900', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('324', '140981', '原平市', '140900', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('325', '141001', '市辖区', '141000', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('326', '141002', '尧都区', '141000', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('327', '141021', '曲沃县', '141000', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('328', '141022', '翼城县', '141000', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('329', '141023', '襄汾县', '141000', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('330', '141024', '洪洞县', '141000', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('331', '141025', '古县', '141000', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('332', '141026', '安泽县', '141000', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('333', '141027', '浮山县', '141000', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('334', '141028', '吉县', '141000', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('335', '141029', '乡宁县', '141000', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('336', '141030', '大宁县', '141000', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('337', '141031', '隰县', '141000', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('338', '141032', '永和县', '141000', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('339', '141033', '蒲县', '141000', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('340', '141034', '汾西县', '141000', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('341', '141081', '侯马市', '141000', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('342', '141082', '霍州市', '141000', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('343', '141101', '市辖区', '141100', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('344', '141102', '离石区', '141100', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('345', '141121', '文水县', '141100', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('346', '141122', '交城县', '141100', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('347', '141123', '兴县', '141100', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('348', '141124', '临县', '141100', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('349', '141125', '柳林县', '141100', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('350', '141126', '石楼县', '141100', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('351', '141127', '岚县', '141100', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('352', '141128', '方山县', '141100', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('353', '141129', '中阳县', '141100', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('354', '141130', '交口县', '141100', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('355', '141181', '孝义市', '141100', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('356', '141182', '汾阳市', '141100', '03', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('357', '140100', '太原市', '100004', '02', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('358', '140200', '大同市', '100004', '02', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('359', '140300', '阳泉市', '100004', '02', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('360', '140400', '长治市', '100004', '02', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('361', '140500', '晋城市', '100004', '02', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('362', '140600', '朔州市', '100004', '02', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('363', '140700', '晋中市', '100004', '02', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('364', '140800', '运城市', '100004', '02', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('365', '140900', '忻州市', '100004', '02', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('366', '141000', '临汾市', '100004', '02', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('367', '141100', '吕梁市', '100004', '02', '2017-05-26 17:44:12', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('368', '150101', '市辖区', '150100', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('369', '150102', '新城区', '150100', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('370', '150103', '回民区', '150100', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('371', '150104', '玉泉区', '150100', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('372', '150105', '赛罕区', '150100', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('373', '150121', '土默特左旗', '150100', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('374', '150122', '托克托县', '150100', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('375', '150123', '和林格尔县', '150100', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('376', '150124', '清水河县', '150100', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('377', '150125', '武川县', '150100', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('378', '150201', '市辖区', '150200', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('379', '150202', '东河区', '150200', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('380', '150203', '昆都仑区', '150200', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('381', '150204', '青山区', '150200', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('382', '150205', '石拐区', '150200', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('383', '150206', '白云鄂博矿区', '150200', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('384', '150207', '九原区', '150200', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('385', '150221', '土默特右旗', '150200', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('386', '150222', '固阳县', '150200', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('387', '150223', '达尔罕茂明安联合旗', '150200', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('388', '150301', '市辖区', '150300', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('389', '150302', '海勃湾区', '150300', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('390', '150303', '海南区', '150300', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('391', '150304', '乌达区', '150300', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('392', '150401', '市辖区', '150400', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('393', '150402', '红山区', '150400', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('394', '150403', '元宝山区', '150400', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('395', '150404', '松山区', '150400', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('396', '150421', '阿鲁科尔沁旗', '150400', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('397', '150422', '巴林左旗', '150400', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('398', '150423', '巴林右旗', '150400', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('399', '150424', '林西县', '150400', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('400', '150425', '克什克腾旗', '150400', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('401', '150426', '翁牛特旗', '150400', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('402', '150428', '喀喇沁旗', '150400', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('403', '150429', '宁城县', '150400', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('404', '150430', '敖汉旗', '150400', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('405', '150501', '市辖区', '150500', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('406', '150502', '科尔沁区', '150500', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('407', '150521', '科尔沁左翼中旗', '150500', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('408', '150522', '科尔沁左翼后旗', '150500', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('409', '150523', '开鲁县', '150500', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('410', '150524', '库伦旗', '150500', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('411', '150525', '奈曼旗', '150500', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('412', '150526', '扎鲁特旗', '150500', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('413', '150581', '霍林郭勒市', '150500', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('414', '150601', '市辖区', '150600', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('415', '150602', '东胜区', '150600', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('416', '150603', '康巴什区', '150600', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('417', '150621', '达拉特旗', '150600', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('418', '150622', '准格尔旗', '150600', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('419', '150623', '鄂托克前旗', '150600', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'E');
INSERT INTO `citys` VALUES ('420', '150624', '鄂托克旗', '150600', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'E');
INSERT INTO `citys` VALUES ('421', '150625', '杭锦旗', '150600', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('422', '150626', '乌审旗', '150600', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('423', '150627', '伊金霍洛旗', '150600', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('424', '150701', '市辖区', '150700', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('425', '150702', '海拉尔区', '150700', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('426', '150703', '扎赉诺尔区', '150700', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('427', '150721', '阿荣旗', '150700', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('428', '150722', '莫力达瓦达斡尔族自治旗', '150700', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('429', '150723', '鄂伦春自治旗', '150700', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'E');
INSERT INTO `citys` VALUES ('430', '150724', '鄂温克族自治旗', '150700', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'E');
INSERT INTO `citys` VALUES ('431', '150725', '陈巴尔虎旗', '150700', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('432', '150726', '新巴尔虎左旗', '150700', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('433', '150727', '新巴尔虎右旗', '150700', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('434', '150781', '满洲里市', '150700', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('435', '150782', '牙克石市', '150700', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('436', '150783', '扎兰屯市', '150700', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('437', '150784', '额尔古纳市', '150700', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'E');
INSERT INTO `citys` VALUES ('438', '150785', '根河市', '150700', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('439', '150801', '市辖区', '150800', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('440', '150802', '临河区', '150800', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('441', '150821', '五原县', '150800', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('442', '150822', '磴口县', '150800', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('443', '150823', '乌拉特前旗', '150800', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('444', '150824', '乌拉特中旗', '150800', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('445', '150825', '乌拉特后旗', '150800', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('446', '150826', '杭锦后旗', '150800', '03', '2017-05-26 17:44:13', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('447', '150901', '市辖区', '150900', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('448', '150902', '集宁区', '150900', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('449', '150921', '卓资县', '150900', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('450', '150922', '化德县', '150900', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('451', '150923', '商都县', '150900', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('452', '150924', '兴和县', '150900', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('453', '150925', '凉城县', '150900', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('454', '150926', '察哈尔右翼前旗', '150900', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('455', '150927', '察哈尔右翼中旗', '150900', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('456', '150928', '察哈尔右翼后旗', '150900', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('457', '150929', '四子王旗', '150900', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('458', '150981', '丰镇市', '150900', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('459', '152201', '乌兰浩特市', '152200', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('460', '152202', '阿尔山市', '152200', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('461', '152221', '科尔沁右翼前旗', '152200', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('462', '152222', '科尔沁右翼中旗', '152200', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('463', '152223', '扎赉特旗', '152200', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('464', '152224', '突泉县', '152200', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('465', '152501', '二连浩特市', '152500', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'E');
INSERT INTO `citys` VALUES ('466', '152502', '锡林浩特市', '152500', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('467', '152522', '阿巴嘎旗', '152500', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('468', '152523', '苏尼特左旗', '152500', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('469', '152524', '苏尼特右旗', '152500', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('470', '152525', '东乌珠穆沁旗', '152500', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('471', '152526', '西乌珠穆沁旗', '152500', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('472', '152527', '太仆寺旗', '152500', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('473', '152528', '镶黄旗', '152500', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('474', '152529', '正镶白旗', '152500', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('475', '152530', '正蓝旗', '152500', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('476', '152531', '多伦县', '152500', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('477', '152921', '阿拉善左旗', '152900', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('478', '152922', '阿拉善右旗', '152900', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('479', '152923', '额济纳旗', '152900', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'E');
INSERT INTO `citys` VALUES ('480', '150100', '呼和浩特市', '100005', '02', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('481', '150200', '包头市', '100005', '02', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('482', '150300', '乌海市', '100005', '02', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('483', '150400', '赤峰市', '100005', '02', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('484', '150500', '通辽市', '100005', '02', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('485', '150600', '鄂尔多斯市', '100005', '02', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'E');
INSERT INTO `citys` VALUES ('486', '150700', '呼伦贝尔市', '100005', '02', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('487', '150800', '巴彦淖尔市', '100005', '02', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('488', '150900', '乌兰察布市', '100005', '02', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('489', '152200', '兴安盟', '100005', '02', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('490', '152500', '锡林郭勒盟', '100005', '02', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('491', '152900', '阿拉善盟', '100005', '02', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('492', '210101', '市辖区', '210100', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('493', '210102', '和平区', '210100', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('494', '210103', '沈河区', '210100', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('495', '210104', '大东区', '210100', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('496', '210105', '皇姑区', '210100', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('497', '210106', '铁西区', '210100', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('498', '210111', '苏家屯区', '210100', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('499', '210112', '浑南区', '210100', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('500', '210113', '沈北新区', '210100', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('501', '210114', '于洪区', '210100', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('502', '210115', '辽中区', '210100', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('503', '210123', '康平县', '210100', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('504', '210124', '法库县', '210100', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('505', '210181', '新民市', '210100', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('506', '210201', '市辖区', '210200', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('507', '210202', '中山区', '210200', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('508', '210203', '西岗区', '210200', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('509', '210204', '沙河口区', '210200', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('510', '210211', '甘井子区', '210200', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('511', '210212', '旅顺口区', '210200', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('512', '210213', '金州区', '210200', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('513', '210214', '普兰店区', '210200', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('514', '210224', '长海县', '210200', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('515', '210281', '瓦房店市', '210200', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('516', '210283', '庄河市', '210200', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('517', '210301', '市辖区', '210300', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('518', '210302', '铁东区', '210300', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('519', '210303', '铁西区', '210300', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('520', '210304', '立山区', '210300', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('521', '210311', '千山区', '210300', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('522', '210321', '台安县', '210300', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('523', '210323', '岫岩满族自治县', '210300', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('524', '210381', '海城市', '210300', '03', '2017-05-26 17:44:14', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('525', '210401', '市辖区', '210400', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('526', '210402', '新抚区', '210400', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('527', '210403', '东洲区', '210400', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('528', '210404', '望花区', '210400', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('529', '210411', '顺城区', '210400', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('530', '210421', '抚顺县', '210400', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('531', '210422', '新宾满族自治县', '210400', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('532', '210423', '清原满族自治县', '210400', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('533', '210501', '市辖区', '210500', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('534', '210502', '平山区', '210500', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('535', '210503', '溪湖区', '210500', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('536', '210504', '明山区', '210500', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('537', '210505', '南芬区', '210500', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('538', '210521', '本溪满族自治县', '210500', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('539', '210522', '桓仁满族自治县', '210500', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('540', '210601', '市辖区', '210600', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('541', '210602', '元宝区', '210600', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('542', '210603', '振兴区', '210600', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('543', '210604', '振安区', '210600', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('544', '210624', '宽甸满族自治县', '210600', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('545', '210681', '东港市', '210600', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('546', '210682', '凤城市', '210600', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('547', '210701', '市辖区', '210700', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('548', '210702', '古塔区', '210700', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('549', '210703', '凌河区', '210700', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('550', '210711', '太和区', '210700', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('551', '210726', '黑山县', '210700', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('552', '210727', '义县', '210700', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('553', '210781', '凌海市', '210700', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('554', '210782', '北镇市', '210700', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('555', '210801', '市辖区', '210800', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('556', '210802', '站前区', '210800', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('557', '210803', '西市区', '210800', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('558', '210804', '鲅鱼圈区', '210800', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('559', '210811', '老边区', '210800', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('560', '210881', '盖州市', '210800', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('561', '210882', '大石桥市', '210800', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('562', '210901', '市辖区', '210900', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('563', '210902', '海州区', '210900', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('564', '210903', '新邱区', '210900', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('565', '210904', '太平区', '210900', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('566', '210905', '清河门区', '210900', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('567', '210911', '细河区', '210900', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('568', '210921', '阜新蒙古族自治县', '210900', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('569', '210922', '彰武县', '210900', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('570', '211001', '市辖区', '211000', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('571', '211002', '白塔区', '211000', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('572', '211003', '文圣区', '211000', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('573', '211004', '宏伟区', '211000', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('574', '211005', '弓长岭区', '211000', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('575', '211011', '太子河区', '211000', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('576', '211021', '辽阳县', '211000', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('577', '211081', '灯塔市', '211000', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('578', '211101', '市辖区', '211100', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('579', '211102', '双台子区', '211100', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('580', '211103', '兴隆台区', '211100', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('581', '211104', '大洼区', '211100', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('582', '211122', '盘山县', '211100', '03', '2017-05-26 17:44:15', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('583', '211201', '市辖区', '211200', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('584', '211202', '银州区', '211200', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('585', '211204', '清河区', '211200', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('586', '211221', '铁岭县', '211200', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('587', '211223', '西丰县', '211200', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('588', '211224', '昌图县', '211200', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('589', '211281', '调兵山市', '211200', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('590', '211282', '开原市', '211200', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('591', '211301', '市辖区', '211300', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('592', '211302', '双塔区', '211300', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('593', '211303', '龙城区', '211300', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('594', '211321', '朝阳县', '211300', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('595', '211322', '建平县', '211300', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('596', '211324', '喀喇沁左翼蒙古族自治县', '211300', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('597', '211381', '北票市', '211300', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('598', '211382', '凌源市', '211300', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('599', '211401', '市辖区', '211400', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('600', '211402', '连山区', '211400', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('601', '211403', '龙港区', '211400', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('602', '211404', '南票区', '211400', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('603', '211421', '绥中县', '211400', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('604', '211422', '建昌县', '211400', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('605', '211481', '兴城市', '211400', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('606', '210100', '沈阳市', '100006', '02', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('607', '210200', '大连市', '100006', '02', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('608', '210300', '鞍山市', '100006', '02', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('609', '210400', '抚顺市', '100006', '02', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('610', '210500', '本溪市', '100006', '02', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('611', '210600', '丹东市', '100006', '02', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('612', '210700', '锦州市', '100006', '02', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('613', '210800', '营口市', '100006', '02', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('614', '210900', '阜新市', '100006', '02', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('615', '211000', '辽阳市', '100006', '02', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('616', '211100', '盘锦市', '100006', '02', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('617', '211200', '铁岭市', '100006', '02', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('618', '211300', '朝阳市', '100006', '02', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('619', '211400', '葫芦岛市', '100006', '02', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('620', '220101', '市辖区', '220100', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('621', '220102', '南关区', '220100', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('622', '220103', '宽城区', '220100', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('623', '220104', '朝阳区', '220100', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('624', '220105', '二道区', '220100', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'E');
INSERT INTO `citys` VALUES ('625', '220106', '绿园区', '220100', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('626', '220112', '双阳区', '220100', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('627', '220113', '九台区', '220100', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('628', '220122', '农安县', '220100', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('629', '220182', '榆树市', '220100', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('630', '220183', '德惠市', '220100', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('631', '220201', '市辖区', '220200', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('632', '220202', '昌邑区', '220200', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('633', '220203', '龙潭区', '220200', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('634', '220204', '船营区', '220200', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('635', '220211', '丰满区', '220200', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('636', '220221', '永吉县', '220200', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('637', '220281', '蛟河市', '220200', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('638', '220282', '桦甸市', '220200', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('639', '220283', '舒兰市', '220200', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('640', '220284', '磐石市', '220200', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('641', '220301', '市辖区', '220300', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('642', '220302', '铁西区', '220300', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('643', '220303', '铁东区', '220300', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('644', '220322', '梨树县', '220300', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('645', '220323', '伊通满族自治县', '220300', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('646', '220381', '公主岭市', '220300', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('647', '220382', '双辽市', '220300', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('648', '220401', '市辖区', '220400', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('649', '220402', '龙山区', '220400', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('650', '220403', '西安区', '220400', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('651', '220421', '东丰县', '220400', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('652', '220422', '东辽县', '220400', '03', '2017-05-26 17:44:16', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('653', '220501', '市辖区', '220500', '03', '2017-05-26 17:44:17', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('654', '220502', '东昌区', '220500', '03', '2017-05-26 17:44:17', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('655', '220503', '二道江区', '220500', '03', '2017-05-26 17:44:17', '2017-05-26 18:07:03', 'E');
INSERT INTO `citys` VALUES ('656', '220521', '通化县', '220500', '03', '2017-05-26 17:44:17', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('657', '220523', '辉南县', '220500', '03', '2017-05-26 17:44:17', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('658', '220524', '柳河县', '220500', '03', '2017-05-26 17:44:17', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('659', '220581', '梅河口市', '220500', '03', '2017-05-26 17:44:17', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('660', '220582', '集安市', '220500', '03', '2017-05-26 17:44:17', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('661', '220601', '市辖区', '220600', '03', '2017-05-26 17:44:17', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('662', '220602', '浑江区', '220600', '03', '2017-05-26 17:44:17', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('663', '220605', '江源区', '220600', '03', '2017-05-26 17:44:17', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('664', '220621', '抚松县', '220600', '03', '2017-05-26 17:44:17', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('665', '220622', '靖宇县', '220600', '03', '2017-05-26 17:44:17', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('666', '220623', '长白朝鲜族自治县', '220600', '03', '2017-05-26 17:44:17', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('667', '220681', '临江市', '220600', '03', '2017-05-26 17:44:17', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('668', '220701', '市辖区', '220700', '03', '2017-05-26 17:44:17', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('669', '220702', '宁江区', '220700', '03', '2017-05-26 17:44:17', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('670', '220721', '前郭尔罗斯蒙古族自治县', '220700', '03', '2017-05-26 17:44:17', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('671', '220722', '长岭县', '220700', '03', '2017-05-26 17:44:17', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('672', '220723', '乾安县', '220700', '03', '2017-05-26 17:44:17', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('673', '220781', '扶余市', '220700', '03', '2017-05-26 17:44:17', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('674', '220801', '市辖区', '220800', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('675', '220802', '洮北区', '220800', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('676', '220821', '镇赉县', '220800', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('677', '220822', '通榆县', '220800', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('678', '220881', '洮南市', '220800', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('679', '220882', '大安市', '220800', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('680', '222401', '延吉市', '222400', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('681', '222402', '图们市', '222400', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('682', '222403', '敦化市', '222400', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('683', '222404', '珲春市', '222400', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('684', '222405', '龙井市', '222400', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('685', '222406', '和龙市', '222400', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('686', '222424', '汪清县', '222400', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('687', '222426', '安图县', '222400', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('688', '220100', '长春市', '100007', '02', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('689', '220200', '吉林市', '100007', '02', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('690', '220300', '四平市', '100007', '02', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('691', '220400', '辽源市', '100007', '02', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('692', '220500', '通化市', '100007', '02', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('693', '220600', '白山市', '100007', '02', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('694', '220700', '松原市', '100007', '02', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('695', '220800', '白城市', '100007', '02', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('696', '222400', '延边朝鲜族自治州', '100007', '02', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('697', '230101', '市辖区', '230100', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('698', '230102', '道里区', '230100', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('699', '230103', '南岗区', '230100', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('700', '230104', '道外区', '230100', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('701', '230108', '平房区', '230100', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('702', '230109', '松北区', '230100', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('703', '230110', '香坊区', '230100', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('704', '230111', '呼兰区', '230100', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('705', '230112', '阿城区', '230100', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('706', '230113', '双城区', '230100', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('707', '230123', '依兰县', '230100', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('708', '230124', '方正县', '230100', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('709', '230125', '宾县', '230100', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('710', '230126', '巴彦县', '230100', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('711', '230127', '木兰县', '230100', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('712', '230128', '通河县', '230100', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('713', '230129', '延寿县', '230100', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('714', '230183', '尚志市', '230100', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('715', '230184', '五常市', '230100', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('716', '230201', '市辖区', '230200', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('717', '230202', '龙沙区', '230200', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('718', '230203', '建华区', '230200', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('719', '230204', '铁锋区', '230200', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('720', '230205', '昂昂溪区', '230200', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('721', '230206', '富拉尔基区', '230200', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('722', '230207', '碾子山区', '230200', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('723', '230208', '梅里斯达斡尔族区', '230200', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('724', '230221', '龙江县', '230200', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('725', '230223', '依安县', '230200', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('726', '230224', '泰来县', '230200', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('727', '230225', '甘南县', '230200', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('728', '230227', '富裕县', '230200', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('729', '230229', '克山县', '230200', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('730', '230230', '克东县', '230200', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('731', '230231', '拜泉县', '230200', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('732', '230281', '讷河市', '230200', '03', '2017-05-26 17:44:48', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('733', '230301', '市辖区', '230300', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('734', '230302', '鸡冠区', '230300', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('735', '230303', '恒山区', '230300', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('736', '230304', '滴道区', '230300', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('737', '230305', '梨树区', '230300', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('738', '230306', '城子河区', '230300', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('739', '230307', '麻山区', '230300', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('740', '230321', '鸡东县', '230300', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('741', '230381', '虎林市', '230300', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('742', '230382', '密山市', '230300', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('743', '230401', '市辖区', '230400', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('744', '230402', '向阳区', '230400', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('745', '230403', '工农区', '230400', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('746', '230404', '南山区', '230400', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('747', '230405', '兴安区', '230400', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('748', '230406', '东山区', '230400', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('749', '230407', '兴山区', '230400', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('750', '230421', '萝北县', '230400', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('751', '230422', '绥滨县', '230400', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('752', '230501', '市辖区', '230500', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('753', '230502', '尖山区', '230500', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('754', '230503', '岭东区', '230500', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('755', '230505', '四方台区', '230500', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('756', '230506', '宝山区', '230500', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('757', '230521', '集贤县', '230500', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('758', '230522', '友谊县', '230500', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('759', '230523', '宝清县', '230500', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('760', '230524', '饶河县', '230500', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('761', '230601', '市辖区', '230600', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('762', '230602', '萨尔图区', '230600', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('763', '230603', '龙凤区', '230600', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('764', '230604', '让胡路区', '230600', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('765', '230605', '红岗区', '230600', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('766', '230606', '大同区', '230600', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('767', '230621', '肇州县', '230600', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('768', '230622', '肇源县', '230600', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('769', '230623', '林甸县', '230600', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('770', '230624', '杜尔伯特蒙古族自治县', '230600', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('771', '230701', '市辖区', '230700', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('772', '230702', '伊春区', '230700', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('773', '230703', '南岔区', '230700', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('774', '230704', '友好区', '230700', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('775', '230705', '西林区', '230700', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('776', '230706', '翠峦区', '230700', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('777', '230707', '新青区', '230700', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('778', '230708', '美溪区', '230700', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('779', '230709', '金山屯区', '230700', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('780', '230710', '五营区', '230700', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('781', '230711', '乌马河区', '230700', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('782', '230712', '汤旺河区', '230700', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('783', '230713', '带岭区', '230700', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('784', '230714', '乌伊岭区', '230700', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('785', '230715', '红星区', '230700', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('786', '230716', '上甘岭区', '230700', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('787', '230722', '嘉荫县', '230700', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('788', '230781', '铁力市', '230700', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('789', '230801', '市辖区', '230800', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('790', '230803', '向阳区', '230800', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('791', '230804', '前进区', '230800', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('792', '230805', '东风区', '230800', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('793', '230811', '郊区', '230800', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('794', '230822', '桦南县', '230800', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('795', '230826', '桦川县', '230800', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('796', '230828', '汤原县', '230800', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('797', '230881', '同江市', '230800', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('798', '230882', '富锦市', '230800', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('799', '230883', '抚远市', '230800', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('800', '230901', '市辖区', '230900', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('801', '230902', '新兴区', '230900', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('802', '230903', '桃山区', '230900', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('803', '230904', '茄子河区', '230900', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('804', '230921', '勃利县', '230900', '03', '2017-05-26 17:44:49', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('805', '231001', '市辖区', '231000', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('806', '231002', '东安区', '231000', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('807', '231003', '阳明区', '231000', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('808', '231004', '爱民区', '231000', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('809', '231005', '西安区', '231000', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('810', '231025', '林口县', '231000', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('811', '231081', '绥芬河市', '231000', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('812', '231083', '海林市', '231000', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('813', '231084', '宁安市', '231000', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('814', '231085', '穆棱市', '231000', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('815', '231086', '东宁市', '231000', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('816', '231101', '市辖区', '231100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('817', '231102', '爱辉区', '231100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('818', '231121', '嫩江县', '231100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('819', '231123', '逊克县', '231100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('820', '231124', '孙吴县', '231100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('821', '231181', '北安市', '231100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('822', '231182', '五大连池市', '231100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('823', '231201', '市辖区', '231200', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('824', '231202', '北林区', '231200', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('825', '231221', '望奎县', '231200', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('826', '231222', '兰西县', '231200', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('827', '231223', '青冈县', '231200', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('828', '231224', '庆安县', '231200', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('829', '231225', '明水县', '231200', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('830', '231226', '绥棱县', '231200', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('831', '231281', '安达市', '231200', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('832', '231282', '肇东市', '231200', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('833', '231283', '海伦市', '231200', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('834', '232721', '呼玛县', '232700', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('835', '232722', '塔河县', '232700', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('836', '232723', '漠河县', '232700', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('837', '230100', '哈尔滨市', '100008', '02', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('838', '230200', '齐齐哈尔市', '100008', '02', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('839', '230300', '鸡西市', '100008', '02', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('840', '230400', '鹤岗市', '100008', '02', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('841', '230500', '双鸭山市', '100008', '02', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('842', '230600', '大庆市', '100008', '02', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('843', '230700', '伊春市', '100008', '02', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('844', '230800', '佳木斯市', '100008', '02', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('845', '230900', '七台河市', '100008', '02', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('846', '231000', '牡丹江市', '100008', '02', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('847', '231100', '黑河市', '100008', '02', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('848', '231200', '绥化市', '100008', '02', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('849', '232700', '大兴安岭地区', '100008', '02', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('850', '310101', '黄浦区', '310100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('851', '310104', '徐汇区', '310100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('852', '310105', '长宁区', '310100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('853', '310106', '静安区', '310100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('854', '310107', '普陀区', '310100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('855', '310109', '虹口区', '310100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('856', '310110', '杨浦区', '310100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('857', '310112', '闵行区', '310100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('858', '310113', '宝山区', '310100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('859', '310114', '嘉定区', '310100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('860', '310115', '浦东新区', '310100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('861', '310116', '金山区', '310100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('862', '310117', '松江区', '310100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('863', '310118', '青浦区', '310100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('864', '310120', '奉贤区', '310100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('865', '310151', '崇明区', '310100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('866', '310100', '上海市', '100009', '02', '2017-05-26 17:44:50', '2017-06-06 16:54:16', 'S');
INSERT INTO `citys` VALUES ('867', '320101', '市辖区', '320100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('868', '320102', '玄武区', '320100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('869', '320104', '秦淮区', '320100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('870', '320105', '建邺区', '320100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('871', '320106', '鼓楼区', '320100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('872', '320111', '浦口区', '320100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('873', '320113', '栖霞区', '320100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('874', '320114', '雨花台区', '320100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('875', '320115', '江宁区', '320100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('876', '320116', '六合区', '320100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('877', '320117', '溧水区', '320100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('878', '320118', '高淳区', '320100', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('879', '320201', '市辖区', '320200', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('880', '320205', '锡山区', '320200', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('881', '320206', '惠山区', '320200', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('882', '320211', '滨湖区', '320200', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('883', '320213', '梁溪区', '320200', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('884', '320214', '新吴区', '320200', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('885', '320281', '江阴市', '320200', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('886', '320282', '宜兴市', '320200', '03', '2017-05-26 17:44:50', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('887', '320301', '市辖区', '320300', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('888', '320302', '鼓楼区', '320300', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('889', '320303', '云龙区', '320300', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('890', '320305', '贾汪区', '320300', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('891', '320311', '泉山区', '320300', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('892', '320312', '铜山区', '320300', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('893', '320321', '丰县', '320300', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('894', '320322', '沛县', '320300', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('895', '320324', '睢宁县', '320300', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('896', '320381', '新沂市', '320300', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('897', '320382', '邳州市', '320300', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('898', '320401', '市辖区', '320400', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('899', '320402', '天宁区', '320400', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('900', '320404', '钟楼区', '320400', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('901', '320411', '新北区', '320400', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('902', '320412', '武进区', '320400', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('903', '320413', '金坛区', '320400', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('904', '320481', '溧阳市', '320400', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('905', '320501', '市辖区', '320500', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('906', '320505', '虎丘区', '320500', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('907', '320506', '吴中区', '320500', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('908', '320507', '相城区', '320500', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('909', '320508', '姑苏区', '320500', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('910', '320509', '吴江区', '320500', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('911', '320581', '常熟市', '320500', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('912', '320582', '张家港市', '320500', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('913', '320583', '昆山市', '320500', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('914', '320585', '太仓市', '320500', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('915', '320601', '市辖区', '320600', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('916', '320602', '崇川区', '320600', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('917', '320611', '港闸区', '320600', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('918', '320612', '通州区', '320600', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('919', '320621', '海安县', '320600', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('920', '320623', '如东县', '320600', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('921', '320681', '启东市', '320600', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('922', '320682', '如皋市', '320600', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('923', '320684', '海门市', '320600', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('924', '320701', '市辖区', '320700', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('925', '320703', '连云区', '320700', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('926', '320706', '海州区', '320700', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('927', '320707', '赣榆区', '320700', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('928', '320722', '东海县', '320700', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('929', '320723', '灌云县', '320700', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('930', '320724', '灌南县', '320700', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('931', '320801', '市辖区', '320800', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('932', '320803', '淮安区', '320800', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('933', '320804', '淮阴区', '320800', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('934', '320812', '清江浦区', '320800', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('935', '320813', '洪泽区', '320800', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('936', '320826', '涟水县', '320800', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('937', '320830', '盱眙县', '320800', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('938', '320831', '金湖县', '320800', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('939', '320901', '市辖区', '320900', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('940', '320902', '亭湖区', '320900', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('941', '320903', '盐都区', '320900', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('942', '320904', '大丰区', '320900', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('943', '320921', '响水县', '320900', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('944', '320922', '滨海县', '320900', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('945', '320923', '阜宁县', '320900', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('946', '320924', '射阳县', '320900', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('947', '320925', '建湖县', '320900', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('948', '320981', '东台市', '320900', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('949', '321001', '市辖区', '321000', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('950', '321002', '广陵区', '321000', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('951', '321003', '邗江区', '321000', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('952', '321012', '江都区', '321000', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('953', '321023', '宝应县', '321000', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('954', '321081', '仪征市', '321000', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('955', '321084', '高邮市', '321000', '03', '2017-05-26 17:44:51', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('956', '321101', '市辖区', '321100', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('957', '321102', '京口区', '321100', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('958', '321111', '润州区', '321100', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('959', '321112', '丹徒区', '321100', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('960', '321181', '丹阳市', '321100', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('961', '321182', '扬中市', '321100', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('962', '321183', '句容市', '321100', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('963', '321201', '市辖区', '321200', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('964', '321202', '海陵区', '321200', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('965', '321203', '高港区', '321200', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('966', '321204', '姜堰区', '321200', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('967', '321281', '兴化市', '321200', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('968', '321282', '靖江市', '321200', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('969', '321283', '泰兴市', '321200', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('970', '321301', '市辖区', '321300', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('971', '321302', '宿城区', '321300', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('972', '321311', '宿豫区', '321300', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('973', '321322', '沭阳县', '321300', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('974', '321323', '泗阳县', '321300', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('975', '321324', '泗洪县', '321300', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('976', '320100', '南京市', '100010', '02', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('977', '320200', '无锡市', '100010', '02', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('978', '320300', '徐州市', '100010', '02', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('979', '320400', '常州市', '100010', '02', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('980', '320500', '苏州市', '100010', '02', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('981', '320600', '南通市', '100010', '02', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('982', '320700', '连云港市', '100010', '02', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('983', '320800', '淮安市', '100010', '02', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('984', '320900', '盐城市', '100010', '02', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('985', '321000', '扬州市', '100010', '02', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('986', '321100', '镇江市', '100010', '02', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('987', '321200', '泰州市', '100010', '02', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('988', '321300', '宿迁市', '100010', '02', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('989', '330101', '市辖区', '330100', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('990', '330102', '上城区', '330100', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('991', '330103', '下城区', '330100', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('992', '330104', '江干区', '330100', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('993', '330105', '拱墅区', '330100', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('994', '330106', '西湖区', '330100', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('995', '330108', '滨江区', '330100', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('996', '330109', '萧山区', '330100', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('997', '330110', '余杭区', '330100', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('998', '330111', '富阳区', '330100', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('999', '330122', '桐庐县', '330100', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1000', '330127', '淳安县', '330100', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1001', '330182', '建德市', '330100', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1002', '330185', '临安市', '330100', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1003', '330201', '市辖区', '330200', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1004', '330203', '海曙区', '330200', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1005', '330204', '江东区', '330200', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1006', '330205', '江北区', '330200', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1007', '330206', '北仑区', '330200', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('1008', '330211', '镇海区', '330200', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1009', '330212', '鄞州区', '330200', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1010', '330225', '象山县', '330200', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1011', '330226', '宁海县', '330200', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1012', '330281', '余姚市', '330200', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1013', '330282', '慈溪市', '330200', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1014', '330283', '奉化市', '330200', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1015', '330301', '市辖区', '330300', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1016', '330302', '鹿城区', '330300', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1017', '330303', '龙湾区', '330300', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1018', '330304', '瓯海区', '330300', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1019', '330305', '洞头区', '330300', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1020', '330324', '永嘉县', '330300', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1021', '330326', '平阳县', '330300', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('1022', '330327', '苍南县', '330300', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1023', '330328', '文成县', '330300', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1024', '330329', '泰顺县', '330300', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1025', '330381', '瑞安市', '330300', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('1026', '330382', '乐清市', '330300', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1027', '330401', '市辖区', '330400', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1028', '330402', '南湖区', '330400', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1029', '330411', '秀洲区', '330400', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1030', '330421', '嘉善县', '330400', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1031', '330424', '海盐县', '330400', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1032', '330481', '海宁市', '330400', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1033', '330482', '平湖市', '330400', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('1034', '330483', '桐乡市', '330400', '03', '2017-05-26 17:44:52', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1035', '330501', '市辖区', '330500', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1036', '330502', '吴兴区', '330500', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1037', '330503', '南浔区', '330500', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1038', '330521', '德清县', '330500', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1039', '330522', '长兴县', '330500', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1040', '330523', '安吉县', '330500', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('1041', '330601', '市辖区', '330600', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1042', '330602', '越城区', '330600', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1043', '330603', '柯桥区', '330600', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('1044', '330604', '上虞区', '330600', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1045', '330624', '新昌县', '330600', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1046', '330681', '诸暨市', '330600', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1047', '330683', '嵊州市', '330600', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1048', '330701', '市辖区', '330700', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1049', '330702', '婺城区', '330700', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1050', '330703', '金东区', '330700', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1051', '330723', '武义县', '330700', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1052', '330726', '浦江县', '330700', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('1053', '330727', '磐安县', '330700', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('1054', '330781', '兰溪市', '330700', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1055', '330782', '义乌市', '330700', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1056', '330783', '东阳市', '330700', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1057', '330784', '永康市', '330700', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1058', '330801', '市辖区', '330800', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1059', '330802', '柯城区', '330800', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('1060', '330803', '衢江区', '330800', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1061', '330822', '常山县', '330800', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1062', '330824', '开化县', '330800', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('1063', '330825', '龙游县', '330800', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1064', '330881', '江山市', '330800', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1065', '330901', '市辖区', '330900', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1066', '330902', '定海区', '330900', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1067', '330903', '普陀区', '330900', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('1068', '330921', '岱山县', '330900', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1069', '330922', '嵊泗县', '330900', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1070', '331001', '市辖区', '331000', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1071', '331002', '椒江区', '331000', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1072', '331003', '黄岩区', '331000', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1073', '331004', '路桥区', '331000', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1074', '331021', '玉环县', '331000', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1075', '331022', '三门县', '331000', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1076', '331023', '天台县', '331000', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1077', '331024', '仙居县', '331000', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1078', '331081', '温岭市', '331000', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1079', '331082', '临海市', '331000', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1080', '331101', '市辖区', '331100', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1081', '331102', '莲都区', '331100', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1082', '331121', '青田县', '331100', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('1083', '331122', '缙云县', '331100', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1084', '331123', '遂昌县', '331100', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1085', '331124', '松阳县', '331100', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1086', '331125', '云和县', '331100', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1087', '331126', '庆元县', '331100', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('1088', '331127', '景宁畲族自治县', '331100', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1089', '331181', '龙泉市', '331100', '03', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1090', '330100', '杭州市', '100011', '02', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1091', '330200', '宁波市', '100011', '02', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1092', '330300', '温州市', '100011', '02', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1093', '330400', '嘉兴市', '100011', '02', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1094', '330500', '湖州市', '100011', '02', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1095', '330600', '绍兴市', '100011', '02', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1096', '330700', '金华市', '100011', '02', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1097', '330800', '衢州市', '100011', '02', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1098', '330900', '舟山市', '100011', '02', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1099', '331000', '台州市', '100011', '02', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1100', '331100', '丽水市', '100011', '02', '2017-05-26 17:44:53', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1101', '340101', '市辖区', '340100', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1102', '340102', '瑶海区', '340100', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1103', '340103', '庐阳区', '340100', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1104', '340104', '蜀山区', '340100', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1105', '340111', '包河区', '340100', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('1106', '340121', '长丰县', '340100', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1107', '340122', '肥东县', '340100', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1108', '340123', '肥西县', '340100', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1109', '340124', '庐江县', '340100', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1110', '340181', '巢湖市', '340100', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1111', '340201', '市辖区', '340200', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1112', '340202', '镜湖区', '340200', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1113', '340203', '弋江区', '340200', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1114', '340207', '鸠江区', '340200', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1115', '340208', '三山区', '340200', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1116', '340221', '芜湖县', '340200', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1117', '340222', '繁昌县', '340200', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1118', '340223', '南陵县', '340200', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1119', '340225', '无为县', '340200', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1120', '340301', '市辖区', '340300', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1121', '340302', '龙子湖区', '340300', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1122', '340303', '蚌山区', '340300', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('1123', '340304', '禹会区', '340300', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1124', '340311', '淮上区', '340300', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1125', '340321', '怀远县', '340300', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1126', '340322', '五河县', '340300', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1127', '340323', '固镇县', '340300', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('1128', '340401', '市辖区', '340400', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1129', '340402', '大通区', '340400', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1130', '340403', '田家庵区', '340400', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1131', '340404', '谢家集区', '340400', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1132', '340405', '八公山区', '340400', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('1133', '340406', '潘集区', '340400', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('1134', '340421', '凤台县', '340400', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1135', '340422', '寿县', '340400', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1136', '340501', '市辖区', '340500', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1137', '340503', '花山区', '340500', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1138', '340504', '雨山区', '340500', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1139', '340506', '博望区', '340500', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('1140', '340521', '当涂县', '340500', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1141', '340522', '含山县', '340500', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1142', '340523', '和县', '340500', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1143', '340601', '市辖区', '340600', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1144', '340602', '杜集区', '340600', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1145', '340603', '相山区', '340600', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1146', '340604', '烈山区', '340600', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1147', '340621', '濉溪县', '340600', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1148', '340701', '市辖区', '340700', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1149', '340705', '铜官区', '340700', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1150', '340706', '义安区', '340700', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1151', '340711', '郊区', '340700', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1152', '340722', '枞阳县', '340700', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1153', '340801', '市辖区', '340800', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1154', '340802', '迎江区', '340800', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1155', '340803', '大观区', '340800', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1156', '340811', '宜秀区', '340800', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1157', '340822', '怀宁县', '340800', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1158', '340824', '潜山县', '340800', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('1159', '340825', '太湖县', '340800', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1160', '340826', '宿松县', '340800', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1161', '340827', '望江县', '340800', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1162', '340828', '岳西县', '340800', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1163', '340881', '桐城市', '340800', '03', '2017-05-26 17:44:54', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1164', '341001', '市辖区', '341000', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1165', '341002', '屯溪区', '341000', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1166', '341003', '黄山区', '341000', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1167', '341004', '徽州区', '341000', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1168', '341021', '歙县', '341000', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1169', '341022', '休宁县', '341000', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1170', '341023', '黟县', '341000', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1171', '341024', '祁门县', '341000', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('1172', '341101', '市辖区', '341100', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1173', '341102', '琅琊区', '341100', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1174', '341103', '南谯区', '341100', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1175', '341122', '来安县', '341100', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1176', '341124', '全椒县', '341100', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('1177', '341125', '定远县', '341100', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1178', '341126', '凤阳县', '341100', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1179', '341181', '天长市', '341100', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1180', '341182', '明光市', '341100', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('1181', '341201', '市辖区', '341200', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1182', '341202', '颍州区', '341200', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1183', '341203', '颍东区', '341200', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1184', '341204', '颍泉区', '341200', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1185', '341221', '临泉县', '341200', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1186', '341222', '太和县', '341200', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1187', '341225', '阜南县', '341200', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1188', '341226', '颍上县', '341200', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1189', '341282', '界首市', '341200', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1190', '341301', '市辖区', '341300', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1191', '341302', '埇桥区', '341300', '03', '2017-05-26 17:44:55', '2017-11-20 20:55:24', 'Y');
INSERT INTO `citys` VALUES ('1192', '341321', '砀山县', '341300', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1193', '341322', '萧县', '341300', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1194', '341323', '灵璧县', '341300', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1195', '341324', '泗县', '341300', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1196', '341501', '市辖区', '341500', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1197', '341502', '金安区', '341500', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1198', '341503', '裕安区', '341500', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1199', '341504', '叶集区', '341500', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1200', '341522', '霍邱县', '341500', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1201', '341523', '舒城县', '341500', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1202', '341524', '金寨县', '341500', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1203', '341525', '霍山县', '341500', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1204', '341601', '市辖区', '341600', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1205', '341602', '谯城区', '341600', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1206', '341621', '涡阳县', '341600', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1207', '341622', '蒙城县', '341600', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('1208', '341623', '利辛县', '341600', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1209', '341701', '市辖区', '341700', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1210', '341702', '贵池区', '341700', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('1211', '341721', '东至县', '341700', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1212', '341722', '石台县', '341700', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1213', '341723', '青阳县', '341700', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('1214', '341801', '市辖区', '341800', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1215', '341802', '宣州区', '341800', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1216', '341821', '郎溪县', '341800', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1217', '341822', '广德县', '341800', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('1218', '341823', '泾县', '341800', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1219', '341824', '绩溪县', '341800', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1220', '341825', '旌德县', '341800', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1221', '341881', '宁国市', '341800', '03', '2017-05-26 17:44:55', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1222', '340100', '合肥市', '100012', '02', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1223', '340200', '芜湖市', '100012', '02', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1224', '340300', '蚌埠市', '100012', '02', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('1225', '340400', '淮南市', '100012', '02', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1226', '340500', '马鞍山市', '100012', '02', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('1227', '340600', '淮北市', '100012', '02', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1228', '340700', '铜陵市', '100012', '02', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1229', '340800', '安庆市', '100012', '02', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('1230', '341000', '黄山市', '100012', '02', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1231', '341100', '滁州市', '100012', '02', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1232', '341200', '阜阳市', '100012', '02', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1233', '341300', '宿州市', '100012', '02', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1234', '341500', '六安市', '100012', '02', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1235', '341600', '亳州市', '100012', '02', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1236', '341700', '池州市', '100012', '02', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1237', '341800', '宣城市', '100012', '02', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1238', '350101', '市辖区', '350100', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1239', '350102', '鼓楼区', '350100', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('1240', '350103', '台江区', '350100', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1241', '350104', '仓山区', '350100', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1242', '350105', '马尾区', '350100', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('1243', '350111', '晋安区', '350100', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1244', '350121', '闽侯县', '350100', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('1245', '350122', '连江县', '350100', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1246', '350123', '罗源县', '350100', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1247', '350124', '闽清县', '350100', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('1248', '350125', '永泰县', '350100', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1249', '350128', '平潭县', '350100', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('1250', '350181', '福清市', '350100', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1251', '350182', '长乐市', '350100', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1252', '350201', '市辖区', '350200', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1253', '350203', '思明区', '350200', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1254', '350205', '海沧区', '350200', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1255', '350206', '湖里区', '350200', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1256', '350211', '集美区', '350200', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1257', '350212', '同安区', '350200', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1258', '350213', '翔安区', '350200', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1259', '350301', '市辖区', '350300', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1260', '350302', '城厢区', '350300', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1261', '350303', '涵江区', '350300', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1262', '350304', '荔城区', '350300', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1263', '350305', '秀屿区', '350300', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1264', '350322', '仙游县', '350300', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1265', '350401', '市辖区', '350400', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1266', '350402', '梅列区', '350400', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('1267', '350403', '三元区', '350400', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1268', '350421', '明溪县', '350400', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('1269', '350423', '清流县', '350400', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('1270', '350424', '宁化县', '350400', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1271', '350425', '大田县', '350400', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1272', '350426', '尤溪县', '350400', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1273', '350427', '沙县', '350400', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1274', '350428', '将乐县', '350400', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1275', '350429', '泰宁县', '350400', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1276', '350430', '建宁县', '350400', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1277', '350481', '永安市', '350400', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1278', '350501', '市辖区', '350500', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1279', '350502', '鲤城区', '350500', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1280', '350503', '丰泽区', '350500', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1281', '350504', '洛江区', '350500', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1282', '350505', '泉港区', '350500', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('1283', '350521', '惠安县', '350500', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1284', '350524', '安溪县', '350500', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('1285', '350525', '永春县', '350500', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1286', '350526', '德化县', '350500', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1287', '350527', '金门县', '350500', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1288', '350581', '石狮市', '350500', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1289', '350582', '晋江市', '350500', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1290', '350583', '南安市', '350500', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1291', '350601', '市辖区', '350600', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1292', '350602', '芗城区', '350600', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1293', '350603', '龙文区', '350600', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1294', '350622', '云霄县', '350600', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1295', '350623', '漳浦县', '350600', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1296', '350624', '诏安县', '350600', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1297', '350625', '长泰县', '350600', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1298', '350626', '东山县', '350600', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1299', '350627', '南靖县', '350600', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1300', '350628', '平和县', '350600', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('1301', '350629', '华安县', '350600', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1302', '350681', '龙海市', '350600', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1303', '350701', '市辖区', '350700', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1304', '350702', '延平区', '350700', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1305', '350703', '建阳区', '350700', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1306', '350721', '顺昌县', '350700', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1307', '350722', '浦城县', '350700', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('1308', '350723', '光泽县', '350700', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('1309', '350724', '松溪县', '350700', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1310', '350725', '政和县', '350700', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1311', '350781', '邵武市', '350700', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1312', '350782', '武夷山市', '350700', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1313', '350783', '建瓯市', '350700', '03', '2017-05-26 17:44:56', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1314', '350801', '市辖区', '350800', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1315', '350802', '新罗区', '350800', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1316', '350803', '永定区', '350800', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1317', '350821', '长汀县', '350800', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1318', '350823', '上杭县', '350800', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1319', '350824', '武平县', '350800', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1320', '350825', '连城县', '350800', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1321', '350881', '漳平市', '350800', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1322', '350901', '市辖区', '350900', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1323', '350902', '蕉城区', '350900', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1324', '350921', '霞浦县', '350900', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1325', '350922', '古田县', '350900', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('1326', '350923', '屏南县', '350900', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('1327', '350924', '寿宁县', '350900', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1328', '350925', '周宁县', '350900', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1329', '350926', '柘荣县', '350900', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1330', '350981', '福安市', '350900', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1331', '350982', '福鼎市', '350900', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1332', '350100', '福州市', '100013', '02', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1333', '350200', '厦门市', '100013', '02', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1334', '350300', '莆田市', '100013', '02', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('1335', '350400', '三明市', '100013', '02', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1336', '350500', '泉州市', '100013', '02', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('1337', '350600', '漳州市', '100013', '02', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1338', '350700', '南平市', '100013', '02', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1339', '350800', '龙岩市', '100013', '02', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1340', '350900', '宁德市', '100013', '02', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1341', '360101', '市辖区', '360100', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1342', '360102', '东湖区', '360100', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1343', '360103', '西湖区', '360100', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1344', '360104', '青云谱区', '360100', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('1345', '360105', '湾里区', '360100', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1346', '360111', '青山湖区', '360100', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('1347', '360112', '新建区', '360100', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1348', '360121', '南昌县', '360100', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1349', '360123', '安义县', '360100', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('1350', '360124', '进贤县', '360100', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1351', '360201', '市辖区', '360200', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1352', '360202', '昌江区', '360200', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1353', '360203', '珠山区', '360200', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1354', '360222', '浮梁县', '360200', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1355', '360281', '乐平市', '360200', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1356', '360301', '市辖区', '360300', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1357', '360302', '安源区', '360300', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('1358', '360313', '湘东区', '360300', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1359', '360321', '莲花县', '360300', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1360', '360322', '上栗县', '360300', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1361', '360323', '芦溪县', '360300', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1362', '360401', '市辖区', '360400', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1363', '360402', '濂溪区', '360400', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1364', '360403', '浔阳区', '360400', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1365', '360421', '九江县', '360400', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1366', '360423', '武宁县', '360400', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1367', '360424', '修水县', '360400', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1368', '360425', '永修县', '360400', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1369', '360426', '德安县', '360400', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1370', '360428', '都昌县', '360400', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1371', '360429', '湖口县', '360400', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1372', '360430', '彭泽县', '360400', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('1373', '360481', '瑞昌市', '360400', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('1374', '360482', '共青城市', '360400', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('1375', '360483', '庐山市', '360400', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1376', '360501', '市辖区', '360500', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1377', '360502', '渝水区', '360500', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1378', '360521', '分宜县', '360500', '03', '2017-05-26 17:44:57', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1379', '360601', '市辖区', '360600', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1380', '360602', '月湖区', '360600', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1381', '360622', '余江县', '360600', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1382', '360681', '贵溪市', '360600', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('1383', '360701', '市辖区', '360700', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1384', '360702', '章贡区', '360700', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1385', '360703', '南康区', '360700', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1386', '360721', '赣县', '360700', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('1387', '360722', '信丰县', '360700', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1388', '360723', '大余县', '360700', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1389', '360724', '上犹县', '360700', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1390', '360725', '崇义县', '360700', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1391', '360726', '安远县', '360700', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('1392', '360727', '龙南县', '360700', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1393', '360728', '定南县', '360700', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1394', '360729', '全南县', '360700', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('1395', '360730', '宁都县', '360700', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1396', '360731', '于都县', '360700', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1397', '360732', '兴国县', '360700', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1398', '360733', '会昌县', '360700', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1399', '360734', '寻乌县', '360700', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1400', '360735', '石城县', '360700', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1401', '360781', '瑞金市', '360700', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('1402', '360801', '市辖区', '360800', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1403', '360802', '吉州区', '360800', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1404', '360803', '青原区', '360800', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('1405', '360821', '吉安县', '360800', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1406', '360822', '吉水县', '360800', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1407', '360823', '峡江县', '360800', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1408', '360824', '新干县', '360800', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1409', '360825', '永丰县', '360800', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1410', '360826', '泰和县', '360800', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1411', '360827', '遂川县', '360800', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1412', '360828', '万安县', '360800', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1413', '360829', '安福县', '360800', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('1414', '360830', '永新县', '360800', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1415', '360881', '井冈山市', '360800', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1416', '360901', '市辖区', '360900', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1417', '360902', '袁州区', '360900', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1418', '360921', '奉新县', '360900', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1419', '360922', '万载县', '360900', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1420', '360923', '上高县', '360900', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1421', '360924', '宜丰县', '360900', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1422', '360925', '靖安县', '360900', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1423', '360926', '铜鼓县', '360900', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1424', '360981', '丰城市', '360900', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1425', '360982', '樟树市', '360900', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1426', '360983', '高安市', '360900', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('1427', '361001', '市辖区', '361000', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1428', '361002', '临川区', '361000', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1429', '361021', '南城县', '361000', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1430', '361022', '黎川县', '361000', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1431', '361023', '南丰县', '361000', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1432', '361024', '崇仁县', '361000', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1433', '361025', '乐安县', '361000', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1434', '361026', '宜黄县', '361000', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1435', '361027', '金溪县', '361000', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1436', '361028', '资溪县', '361000', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1437', '361029', '东乡县', '361000', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1438', '361030', '广昌县', '361000', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('1439', '361101', '市辖区', '361100', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1440', '361102', '信州区', '361100', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1441', '361103', '广丰区', '361100', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('1442', '361121', '上饶县', '361100', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1443', '361123', '玉山县', '361100', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1444', '361124', '铅山县', '361100', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('1445', '361125', '横峰县', '361100', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1446', '361126', '弋阳县', '361100', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1447', '361127', '余干县', '361100', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1448', '361128', '鄱阳县', '361100', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1449', '361129', '万年县', '361100', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1450', '361130', '婺源县', '361100', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1451', '361181', '德兴市', '361100', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1452', '360100', '南昌市', '100014', '02', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1453', '360200', '景德镇市', '100014', '02', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1454', '360300', '萍乡市', '100014', '02', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('1455', '360400', '九江市', '100014', '02', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1456', '360500', '新余市', '100014', '02', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1457', '360600', '鹰潭市', '100014', '02', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1458', '360700', '赣州市', '100014', '02', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('1459', '360800', '吉安市', '100014', '02', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1460', '360900', '宜春市', '100014', '02', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1461', '361000', '抚州市', '100014', '02', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1462', '361100', '上饶市', '100014', '02', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1463', '370101', '市辖区', '370100', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1464', '370102', '历下区', '370100', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1465', '370103', '市中区', '370100', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1466', '370104', '槐荫区', '370100', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1467', '370105', '天桥区', '370100', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1468', '370112', '历城区', '370100', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1469', '370113', '长清区', '370100', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1470', '370124', '平阴县', '370100', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('1471', '370125', '济阳县', '370100', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1472', '370126', '商河县', '370100', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1473', '370181', '章丘市', '370100', '03', '2017-05-26 17:44:58', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1474', '370201', '市辖区', '370200', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1475', '370202', '市南区', '370200', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1476', '370203', '市北区', '370200', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1477', '370211', '黄岛区', '370200', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1478', '370212', '崂山区', '370200', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1479', '370213', '李沧区', '370200', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1480', '370214', '城阳区', '370200', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1481', '370281', '胶州市', '370200', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1482', '370282', '即墨市', '370200', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1483', '370283', '平度市', '370200', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('1484', '370285', '莱西市', '370200', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1485', '370301', '市辖区', '370300', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1486', '370302', '淄川区', '370300', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1487', '370303', '张店区', '370300', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1488', '370304', '博山区', '370300', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('1489', '370305', '临淄区', '370300', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1490', '370306', '周村区', '370300', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1491', '370321', '桓台县', '370300', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1492', '370322', '高青县', '370300', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('1493', '370323', '沂源县', '370300', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1494', '370401', '市辖区', '370400', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1495', '370402', '市中区', '370400', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1496', '370403', '薛城区', '370400', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1497', '370404', '峄城区', '370400', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1498', '370405', '台儿庄区', '370400', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1499', '370406', '山亭区', '370400', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1500', '370481', '滕州市', '370400', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1501', '370501', '市辖区', '370500', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1502', '370502', '东营区', '370500', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1503', '370503', '河口区', '370500', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1504', '370505', '垦利区', '370500', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('1505', '370522', '利津县', '370500', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1506', '370523', '广饶县', '370500', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('1507', '370601', '市辖区', '370600', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1508', '370602', '芝罘区', '370600', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1509', '370611', '福山区', '370600', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1510', '370612', '牟平区', '370600', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('1511', '370613', '莱山区', '370600', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1512', '370634', '长岛县', '370600', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1513', '370681', '龙口市', '370600', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1514', '370682', '莱阳市', '370600', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1515', '370683', '莱州市', '370600', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1516', '370684', '蓬莱市', '370600', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('1517', '370685', '招远市', '370600', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1518', '370686', '栖霞市', '370600', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('1519', '370687', '海阳市', '370600', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1520', '370701', '市辖区', '370700', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1521', '370702', '潍城区', '370700', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1522', '370703', '寒亭区', '370700', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1523', '370704', '坊子区', '370700', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1524', '370705', '奎文区', '370700', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('1525', '370724', '临朐县', '370700', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1526', '370725', '昌乐县', '370700', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1527', '370781', '青州市', '370700', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('1528', '370782', '诸城市', '370700', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1529', '370783', '寿光市', '370700', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1530', '370784', '安丘市', '370700', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('1531', '370785', '高密市', '370700', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('1532', '370786', '昌邑市', '370700', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1533', '370801', '市辖区', '370800', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1534', '370811', '任城区', '370800', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('1535', '370812', '兖州区', '370800', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1536', '370826', '微山县', '370800', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1537', '370827', '鱼台县', '370800', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1538', '370828', '金乡县', '370800', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1539', '370829', '嘉祥县', '370800', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1540', '370830', '汶上县', '370800', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1541', '370831', '泗水县', '370800', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1542', '370832', '梁山县', '370800', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1543', '370881', '曲阜市', '370800', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('1544', '370883', '邹城市', '370800', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1545', '370901', '市辖区', '370900', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1546', '370902', '泰山区', '370900', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1547', '370911', '岱岳区', '370900', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1548', '370921', '宁阳县', '370900', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1549', '370923', '东平县', '370900', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1550', '370982', '新泰市', '370900', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1551', '370983', '肥城市', '370900', '03', '2017-05-26 17:44:59', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1552', '371001', '市辖区', '371000', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1553', '371002', '环翠区', '371000', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1554', '371003', '文登区', '371000', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1555', '371082', '荣成市', '371000', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('1556', '371083', '乳山市', '371000', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('1557', '371101', '市辖区', '371100', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1558', '371102', '东港区', '371100', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1559', '371103', '岚山区', '371100', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1560', '371121', '五莲县', '371100', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1561', '371122', '莒县', '371100', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1562', '371201', '市辖区', '371200', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1563', '371202', '莱城区', '371200', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1564', '371203', '钢城区', '371200', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('1565', '371301', '市辖区', '371300', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1566', '371302', '兰山区', '371300', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1567', '371311', '罗庄区', '371300', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1568', '371312', '河东区', '371300', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1569', '371321', '沂南县', '371300', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1570', '371322', '郯城县', '371300', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1571', '371323', '沂水县', '371300', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1572', '371324', '兰陵县', '371300', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1573', '371325', '费县', '371300', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1574', '371326', '平邑县', '371300', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('1575', '371327', '莒南县', '371300', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1576', '371328', '蒙阴县', '371300', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('1577', '371329', '临沭县', '371300', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1578', '371401', '市辖区', '371400', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1579', '371402', '德城区', '371400', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1580', '371403', '陵城区', '371400', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1581', '371422', '宁津县', '371400', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1582', '371423', '庆云县', '371400', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('1583', '371424', '临邑县', '371400', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1584', '371425', '齐河县', '371400', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('1585', '371426', '平原县', '371400', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('1586', '371427', '夏津县', '371400', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1587', '371428', '武城县', '371400', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1588', '371481', '乐陵市', '371400', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1589', '371482', '禹城市', '371400', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1590', '371501', '市辖区', '371500', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1591', '371502', '东昌府区', '371500', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1592', '371521', '阳谷县', '371500', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1593', '371522', '莘县', '371500', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1594', '371523', '茌平县', '371500', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1595', '371524', '东阿县', '371500', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1596', '371525', '冠县', '371500', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('1597', '371526', '高唐县', '371500', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('1598', '371581', '临清市', '371500', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1599', '371601', '市辖区', '371600', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1600', '371602', '滨城区', '371600', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('1601', '371603', '沾化区', '371600', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1602', '371621', '惠民县', '371600', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1603', '371622', '阳信县', '371600', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1604', '371623', '无棣县', '371600', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1605', '371625', '博兴县', '371600', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('1606', '371626', '邹平县', '371600', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1607', '371701', '市辖区', '371700', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1608', '371702', '牡丹区', '371700', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('1609', '371703', '定陶区', '371700', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1610', '371721', '曹县', '371700', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1611', '371722', '单县', '371700', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1612', '371723', '成武县', '371700', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1613', '371724', '巨野县', '371700', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1614', '371725', '郓城县', '371700', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1615', '371726', '鄄城县', '371700', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1616', '371728', '东明县', '371700', '03', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1617', '370100', '济南市', '100015', '02', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1618', '370200', '青岛市', '100015', '02', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('1619', '370300', '淄博市', '100015', '02', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1620', '370400', '枣庄市', '100015', '02', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1621', '370500', '东营市', '100015', '02', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1622', '370600', '烟台市', '100015', '02', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1623', '370700', '潍坊市', '100015', '02', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1624', '370800', '济宁市', '100015', '02', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1625', '370900', '泰安市', '100015', '02', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1626', '371000', '威海市', '100015', '02', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1627', '371100', '日照市', '100015', '02', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('1628', '371200', '莱芜市', '100015', '02', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1629', '371300', '临沂市', '100015', '02', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1630', '371400', '德州市', '100015', '02', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1631', '371500', '聊城市', '100015', '02', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1632', '371600', '滨州市', '100015', '02', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('1633', '371700', '菏泽市', '100015', '02', '2017-05-26 17:45:00', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1634', '410101', '市辖区', '410100', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1635', '410102', '中原区', '410100', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1636', '410103', '二七区', '410100', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'E');
INSERT INTO `citys` VALUES ('1637', '410104', '管城回族区', '410100', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('1638', '410105', '金水区', '410100', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1639', '410106', '上街区', '410100', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1640', '410108', '惠济区', '410100', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1641', '410122', '中牟县', '410100', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1642', '410181', '巩义市', '410100', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('1643', '410182', '荥阳市', '410100', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1644', '410183', '新密市', '410100', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1645', '410184', '新郑市', '410100', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1646', '410185', '登封市', '410100', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1647', '410201', '市辖区', '410200', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1648', '410202', '龙亭区', '410200', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1649', '410203', '顺河回族区', '410200', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1650', '410204', '鼓楼区', '410200', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('1651', '410205', '禹王台区', '410200', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1652', '410211', '金明区', '410200', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1653', '410212', '祥符区', '410200', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1654', '410221', '杞县', '410200', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1655', '410222', '通许县', '410200', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1656', '410223', '尉氏县', '410200', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1657', '410225', '兰考县', '410200', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1658', '410301', '市辖区', '410300', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1659', '410302', '老城区', '410300', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1660', '410303', '西工区', '410300', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1661', '410304', '瀍河回族区', '410300', '03', '2017-05-26 17:45:01', '2017-11-20 20:57:21', 'C');
INSERT INTO `citys` VALUES ('1662', '410305', '涧西区', '410300', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1663', '410306', '吉利区', '410300', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1664', '410311', '洛龙区', '410300', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1665', '410322', '孟津县', '410300', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('1666', '410323', '新安县', '410300', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1667', '410324', '栾川县', '410300', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1668', '410325', '嵩县', '410300', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1669', '410326', '汝阳县', '410300', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('1670', '410327', '宜阳县', '410300', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1671', '410328', '洛宁县', '410300', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1672', '410329', '伊川县', '410300', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1673', '410381', '偃师市', '410300', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1674', '410401', '市辖区', '410400', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1675', '410402', '新华区', '410400', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1676', '410403', '卫东区', '410400', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1677', '410404', '石龙区', '410400', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1678', '410411', '湛河区', '410400', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1679', '410421', '宝丰县', '410400', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('1680', '410422', '叶县', '410400', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1681', '410423', '鲁山县', '410400', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1682', '410425', '郏县', '410400', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1683', '410481', '舞钢市', '410400', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1684', '410482', '汝州市', '410400', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('1685', '410501', '市辖区', '410500', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1686', '410502', '文峰区', '410500', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1687', '410503', '北关区', '410500', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('1688', '410505', '殷都区', '410500', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1689', '410506', '龙安区', '410500', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1690', '410522', '安阳县', '410500', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('1691', '410523', '汤阴县', '410500', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1692', '410526', '滑县', '410500', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1693', '410527', '内黄县', '410500', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1694', '410581', '林州市', '410500', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1695', '410601', '市辖区', '410600', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1696', '410602', '鹤山区', '410600', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1697', '410603', '山城区', '410600', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1698', '410611', '淇滨区', '410600', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1699', '410621', '浚县', '410600', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1700', '410622', '淇县', '410600', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1701', '410701', '市辖区', '410700', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1702', '410702', '红旗区', '410700', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1703', '410703', '卫滨区', '410700', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1704', '410704', '凤泉区', '410700', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1705', '410711', '牧野区', '410700', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('1706', '410721', '新乡县', '410700', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1707', '410724', '获嘉县', '410700', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1708', '410725', '原阳县', '410700', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1709', '410726', '延津县', '410700', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1710', '410727', '封丘县', '410700', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1711', '410728', '长垣县', '410700', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1712', '410781', '卫辉市', '410700', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1713', '410782', '辉县市', '410700', '03', '2017-05-26 17:45:01', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1714', '410801', '市辖区', '410800', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1715', '410802', '解放区', '410800', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1716', '410803', '中站区', '410800', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1717', '410804', '马村区', '410800', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('1718', '410811', '山阳区', '410800', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1719', '410821', '修武县', '410800', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1720', '410822', '博爱县', '410800', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('1721', '410823', '武陟县', '410800', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1722', '410825', '温县', '410800', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1723', '410882', '沁阳市', '410800', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('1724', '410883', '孟州市', '410800', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('1725', '410901', '市辖区', '410900', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1726', '410902', '华龙区', '410900', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1727', '410922', '清丰县', '410900', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('1728', '410923', '南乐县', '410900', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1729', '410926', '范县', '410900', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1730', '410927', '台前县', '410900', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1731', '410928', '濮阳县', '410900', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1732', '411001', '市辖区', '411000', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1733', '411002', '魏都区', '411000', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1734', '411023', '许昌县', '411000', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1735', '411024', '鄢陵县', '411000', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1736', '411025', '襄城县', '411000', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1737', '411081', '禹州市', '411000', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1738', '411082', '长葛市', '411000', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1739', '411101', '市辖区', '411100', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1740', '411102', '源汇区', '411100', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1741', '411103', '郾城区', '411100', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1742', '411104', '召陵区', '411100', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1743', '411121', '舞阳县', '411100', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1744', '411122', '临颍县', '411100', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1745', '411201', '市辖区', '411200', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1746', '411202', '湖滨区', '411200', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1747', '411203', '陕州区', '411200', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1748', '411221', '渑池县', '411200', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1749', '411224', '卢氏县', '411200', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1750', '411281', '义马市', '411200', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1751', '411282', '灵宝市', '411200', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1752', '411301', '市辖区', '411300', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1753', '411302', '宛城区', '411300', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1754', '411303', '卧龙区', '411300', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1755', '411321', '南召县', '411300', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1756', '411322', '方城县', '411300', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1757', '411323', '西峡县', '411300', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1758', '411324', '镇平县', '411300', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1759', '411325', '内乡县', '411300', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1760', '411326', '淅川县', '411300', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1761', '411327', '社旗县', '411300', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1762', '411328', '唐河县', '411300', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1763', '411329', '新野县', '411300', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1764', '411330', '桐柏县', '411300', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1765', '411381', '邓州市', '411300', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1766', '411401', '市辖区', '411400', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1767', '411402', '梁园区', '411400', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1768', '411403', '睢阳区', '411400', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1769', '411421', '民权县', '411400', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('1770', '411422', '睢县', '411400', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1771', '411423', '宁陵县', '411400', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1772', '411424', '柘城县', '411400', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1773', '411425', '虞城县', '411400', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1774', '411426', '夏邑县', '411400', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1775', '411481', '永城市', '411400', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1776', '411501', '市辖区', '411500', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1777', '411502', '浉河区', '411500', '03', '2017-05-26 17:45:02', '2017-11-20 20:56:47', 'S');
INSERT INTO `citys` VALUES ('1778', '411503', '平桥区', '411500', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('1779', '411521', '罗山县', '411500', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1780', '411522', '光山县', '411500', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('1781', '411523', '新县', '411500', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1782', '411524', '商城县', '411500', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1783', '411525', '固始县', '411500', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('1784', '411526', '潢川县', '411500', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1785', '411527', '淮滨县', '411500', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1786', '411528', '息县', '411500', '03', '2017-05-26 17:45:02', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1787', '411601', '市辖区', '411600', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1788', '411602', '川汇区', '411600', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1789', '411621', '扶沟县', '411600', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1790', '411622', '西华县', '411600', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1791', '411623', '商水县', '411600', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1792', '411624', '沈丘县', '411600', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1793', '411625', '郸城县', '411600', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1794', '411626', '淮阳县', '411600', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1795', '411627', '太康县', '411600', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1796', '411628', '鹿邑县', '411600', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1797', '411681', '项城市', '411600', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1798', '411701', '市辖区', '411700', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1799', '411702', '驿城区', '411700', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1800', '411721', '西平县', '411700', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1801', '411722', '上蔡县', '411700', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1802', '411723', '平舆县', '411700', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('1803', '411724', '正阳县', '411700', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1804', '411725', '确山县', '411700', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('1805', '411726', '泌阳县', '411700', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('1806', '411727', '汝南县', '411700', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('1807', '411728', '遂平县', '411700', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1808', '411729', '新蔡县', '411700', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1809', '419001', '济源市', '419000', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1810', '410100', '郑州市', '100016', '02', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1811', '410200', '开封市', '100016', '02', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('1812', '410300', '洛阳市', '100016', '02', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1813', '410400', '平顶山市', '100016', '02', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('1814', '410500', '安阳市', '100016', '02', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('1815', '410600', '鹤壁市', '100016', '02', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1816', '410700', '新乡市', '100016', '02', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1817', '410800', '焦作市', '100016', '02', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1818', '410900', '濮阳市', '100016', '02', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1819', '411000', '许昌市', '100016', '02', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1820', '411100', '漯河市', '100016', '02', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1821', '411200', '三门峡市', '100016', '02', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1822', '411300', '南阳市', '100016', '02', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1823', '411400', '商丘市', '100016', '02', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1824', '411500', '信阳市', '100016', '02', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1825', '411600', '周口市', '100016', '02', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1826', '411700', '驻马店市', '100016', '02', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1827', '419000', '省直辖县级行政区划', '100016', '02', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1828', '420101', '市辖区', '420100', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1829', '420102', '江岸区', '420100', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1830', '420103', '江汉区', '420100', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1831', '420104', '硚口区', '420100', '03', '2017-05-26 17:45:03', '2017-11-20 20:58:20', 'Q');
INSERT INTO `citys` VALUES ('1832', '420105', '汉阳区', '420100', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1833', '420106', '武昌区', '420100', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1834', '420107', '青山区', '420100', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('1835', '420111', '洪山区', '420100', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1836', '420112', '东西湖区', '420100', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1837', '420113', '汉南区', '420100', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1838', '420114', '蔡甸区', '420100', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1839', '420115', '江夏区', '420100', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1840', '420116', '黄陂区', '420100', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1841', '420117', '新洲区', '420100', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1842', '420201', '市辖区', '420200', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1843', '420202', '黄石港区', '420200', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1844', '420203', '西塞山区', '420200', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1845', '420204', '下陆区', '420200', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1846', '420205', '铁山区', '420200', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1847', '420222', '阳新县', '420200', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1848', '420281', '大冶市', '420200', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1849', '420301', '市辖区', '420300', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1850', '420302', '茅箭区', '420300', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('1851', '420303', '张湾区', '420300', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1852', '420304', '郧阳区', '420300', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1853', '420322', '郧西县', '420300', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1854', '420323', '竹山县', '420300', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1855', '420324', '竹溪县', '420300', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1856', '420325', '房县', '420300', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1857', '420381', '丹江口市', '420300', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1858', '420501', '市辖区', '420500', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1859', '420502', '西陵区', '420500', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1860', '420503', '伍家岗区', '420500', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1861', '420504', '点军区', '420500', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1862', '420505', '猇亭区', '420500', '03', '2017-05-26 17:45:03', '2017-11-20 20:57:54', 'X');
INSERT INTO `citys` VALUES ('1863', '420506', '夷陵区', '420500', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1864', '420525', '远安县', '420500', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1865', '420526', '兴山县', '420500', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1866', '420527', '秭归县', '420500', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1867', '420528', '长阳土家族自治县', '420500', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1868', '420529', '五峰土家族自治县', '420500', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1869', '420581', '宜都市', '420500', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1870', '420582', '当阳市', '420500', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1871', '420583', '枝江市', '420500', '03', '2017-05-26 17:45:03', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1872', '420601', '市辖区', '420600', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1873', '420602', '襄城区', '420600', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1874', '420606', '樊城区', '420600', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('1875', '420607', '襄州区', '420600', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1876', '420624', '南漳县', '420600', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1877', '420625', '谷城县', '420600', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('1878', '420626', '保康县', '420600', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('1879', '420682', '老河口市', '420600', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1880', '420683', '枣阳市', '420600', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1881', '420684', '宜城市', '420600', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1882', '420701', '市辖区', '420700', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1883', '420702', '梁子湖区', '420700', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1884', '420703', '华容区', '420700', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1885', '420704', '鄂城区', '420700', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'E');
INSERT INTO `citys` VALUES ('1886', '420801', '市辖区', '420800', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1887', '420802', '东宝区', '420800', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1888', '420804', '掇刀区', '420800', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1889', '420821', '京山县', '420800', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1890', '420822', '沙洋县', '420800', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1891', '420881', '钟祥市', '420800', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1892', '420901', '市辖区', '420900', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1893', '420902', '孝南区', '420900', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1894', '420921', '孝昌县', '420900', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1895', '420922', '大悟县', '420900', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1896', '420923', '云梦县', '420900', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1897', '420981', '应城市', '420900', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1898', '420982', '安陆市', '420900', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('1899', '420984', '汉川市', '420900', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1900', '421001', '市辖区', '421000', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1901', '421002', '沙市区', '421000', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1902', '421003', '荆州区', '421000', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1903', '421022', '公安县', '421000', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('1904', '421023', '监利县', '421000', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1905', '421024', '江陵县', '421000', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1906', '421081', '石首市', '421000', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1907', '421083', '洪湖市', '421000', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1908', '421087', '松滋市', '421000', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1909', '421101', '市辖区', '421100', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1910', '421102', '黄州区', '421100', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1911', '421121', '团风县', '421100', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1912', '421122', '红安县', '421100', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1913', '421123', '罗田县', '421100', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1914', '421124', '英山县', '421100', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1915', '421125', '浠水县', '421100', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1916', '421126', '蕲春县', '421100', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1917', '421127', '黄梅县', '421100', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1918', '421181', '麻城市', '421100', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('1919', '421182', '武穴市', '421100', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1920', '421201', '市辖区', '421200', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1921', '421202', '咸安区', '421200', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1922', '421221', '嘉鱼县', '421200', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1923', '421222', '通城县', '421200', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1924', '421223', '崇阳县', '421200', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1925', '421224', '通山县', '421200', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1926', '421281', '赤壁市', '421200', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1927', '421301', '市辖区', '421300', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1928', '421303', '曾都区', '421300', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1929', '421321', '随县', '421300', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1930', '421381', '广水市', '421300', '03', '2017-05-26 17:45:04', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('1931', '422801', '恩施市', '422800', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'E');
INSERT INTO `citys` VALUES ('1932', '422802', '利川市', '422800', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1933', '422822', '建始县', '422800', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1934', '422823', '巴东县', '422800', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('1935', '422825', '宣恩县', '422800', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1936', '422826', '咸丰县', '422800', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1937', '422827', '来凤县', '422800', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1938', '422828', '鹤峰县', '422800', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1939', '429004', '仙桃市', '429000', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1940', '429005', '潜江市', '429000', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('1941', '429006', '天门市', '429000', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1942', '429021', '神农架林区', '429000', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1943', '420100', '武汉市', '100017', '02', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1944', '420200', '黄石市', '100017', '02', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1945', '420300', '十堰市', '100017', '02', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1946', '420500', '宜昌市', '100017', '02', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1947', '420600', '襄阳市', '100017', '02', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1948', '420700', '鄂州市', '100017', '02', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'E');
INSERT INTO `citys` VALUES ('1949', '420800', '荆门市', '100017', '02', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1950', '420900', '孝感市', '100017', '02', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1951', '421000', '荆州市', '100017', '02', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('1952', '421100', '黄冈市', '100017', '02', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1953', '421200', '咸宁市', '100017', '02', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1954', '421300', '随州市', '100017', '02', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1955', '422800', '恩施土家族苗族自治州', '100017', '02', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'E');
INSERT INTO `citys` VALUES ('1956', '429000', '省直辖县级行政区划', '100017', '02', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1957', '430101', '市辖区', '430100', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1958', '430102', '芙蓉区', '430100', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1959', '430103', '天心区', '430100', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1960', '430104', '岳麓区', '430100', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1961', '430105', '开福区', '430100', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('1962', '430111', '雨花区', '430100', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1963', '430112', '望城区', '430100', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('1964', '430121', '长沙县', '430100', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1965', '430124', '宁乡县', '430100', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1966', '430181', '浏阳市', '430100', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1967', '430201', '市辖区', '430200', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1968', '430202', '荷塘区', '430200', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1969', '430203', '芦淞区', '430200', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('1970', '430204', '石峰区', '430200', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1971', '430211', '天元区', '430200', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('1972', '430221', '株洲县', '430200', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1973', '430223', '攸县', '430200', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1974', '430224', '茶陵县', '430200', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1975', '430225', '炎陵县', '430200', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1976', '430281', '醴陵市', '430200', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1977', '430301', '市辖区', '430300', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1978', '430302', '雨湖区', '430300', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1979', '430304', '岳塘区', '430300', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1980', '430321', '湘潭县', '430300', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1981', '430381', '湘乡市', '430300', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('1982', '430382', '韶山市', '430300', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1983', '430401', '市辖区', '430400', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1984', '430405', '珠晖区', '430400', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1985', '430406', '雁峰区', '430400', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('1986', '430407', '石鼓区', '430400', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1987', '430408', '蒸湘区', '430400', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1988', '430412', '南岳区', '430400', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('1989', '430421', '衡阳县', '430400', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1990', '430422', '衡南县', '430400', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1991', '430423', '衡山县', '430400', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1992', '430424', '衡东县', '430400', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('1993', '430426', '祁东县', '430400', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('1994', '430481', '耒阳市', '430400', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('1995', '430482', '常宁市', '430400', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('1996', '430501', '市辖区', '430500', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1997', '430502', '双清区', '430500', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('1998', '430503', '大祥区', '430500', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('1999', '430511', '北塔区', '430500', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2000', '430521', '邵东县', '430500', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2001', '430522', '新邵县', '430500', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2002', '430523', '邵阳县', '430500', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2003', '430524', '隆回县', '430500', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2004', '430525', '洞口县', '430500', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2005', '430527', '绥宁县', '430500', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2006', '430528', '新宁县', '430500', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2007', '430529', '城步苗族自治县', '430500', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2008', '430581', '武冈市', '430500', '03', '2017-05-26 17:45:05', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2009', '430601', '市辖区', '430600', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2010', '430602', '岳阳楼区', '430600', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2011', '430603', '云溪区', '430600', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2012', '430611', '君山区', '430600', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2013', '430621', '岳阳县', '430600', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2014', '430623', '华容县', '430600', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2015', '430624', '湘阴县', '430600', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2016', '430626', '平江县', '430600', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2017', '430681', '汨罗市', '430600', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2018', '430682', '临湘市', '430600', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2019', '430701', '市辖区', '430700', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2020', '430702', '武陵区', '430700', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2021', '430703', '鼎城区', '430700', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2022', '430721', '安乡县', '430700', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('2023', '430722', '汉寿县', '430700', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2024', '430723', '澧县', '430700', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2025', '430724', '临澧县', '430700', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2026', '430725', '桃源县', '430700', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('2027', '430726', '石门县', '430700', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2028', '430781', '津市市', '430700', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2029', '430801', '市辖区', '430800', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2030', '430802', '永定区', '430800', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2031', '430811', '武陵源区', '430800', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2032', '430821', '慈利县', '430800', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2033', '430822', '桑植县', '430800', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2034', '430901', '市辖区', '430900', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2035', '430902', '资阳区', '430900', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2036', '430903', '赫山区', '430900', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2037', '430921', '南县', '430900', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2038', '430922', '桃江县', '430900', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('2039', '430923', '安化县', '430900', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('2040', '430981', '沅江市', '430900', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2041', '431001', '市辖区', '431000', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2042', '431002', '北湖区', '431000', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2043', '431003', '苏仙区', '431000', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2044', '431021', '桂阳县', '431000', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2045', '431022', '宜章县', '431000', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2046', '431023', '永兴县', '431000', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2047', '431024', '嘉禾县', '431000', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2048', '431025', '临武县', '431000', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2049', '431026', '汝城县', '431000', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('2050', '431027', '桂东县', '431000', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2051', '431028', '安仁县', '431000', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('2052', '431081', '资兴市', '431000', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2053', '431101', '市辖区', '431100', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2054', '431102', '零陵区', '431100', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2055', '431103', '冷水滩区', '431100', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2056', '431121', '祁阳县', '431100', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2057', '431122', '东安县', '431100', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2058', '431123', '双牌县', '431100', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2059', '431124', '道县', '431100', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2060', '431125', '江永县', '431100', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2061', '431126', '宁远县', '431100', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2062', '431127', '蓝山县', '431100', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2063', '431128', '新田县', '431100', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2064', '431129', '江华瑶族自治县', '431100', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2065', '431201', '市辖区', '431200', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2066', '431202', '鹤城区', '431200', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2067', '431221', '中方县', '431200', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2068', '431222', '沅陵县', '431200', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2069', '431223', '辰溪县', '431200', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2070', '431224', '溆浦县', '431200', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2071', '431225', '会同县', '431200', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2072', '431226', '麻阳苗族自治县', '431200', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2073', '431227', '新晃侗族自治县', '431200', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2074', '431228', '芷江侗族自治县', '431200', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2075', '431229', '靖州苗族侗族自治县', '431200', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2076', '431230', '通道侗族自治县', '431200', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('2077', '431281', '洪江市', '431200', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2078', '431301', '市辖区', '431300', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2079', '431302', '娄星区', '431300', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2080', '431321', '双峰县', '431300', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2081', '431322', '新化县', '431300', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2082', '431381', '冷水江市', '431300', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2083', '431382', '涟源市', '431300', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2084', '433101', '吉首市', '433100', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2085', '433122', '泸溪县', '433100', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2086', '433123', '凤凰县', '433100', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('2087', '433124', '花垣县', '433100', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2088', '433125', '保靖县', '433100', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2089', '433126', '古丈县', '433100', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2090', '433127', '永顺县', '433100', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2091', '433130', '龙山县', '433100', '03', '2017-05-26 17:45:06', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2092', '430100', '长沙市', '100018', '02', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2093', '430200', '株洲市', '100018', '02', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2094', '430300', '湘潭市', '100018', '02', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2095', '430400', '衡阳市', '100018', '02', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2096', '430500', '邵阳市', '100018', '02', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2097', '430600', '岳阳市', '100018', '02', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2098', '430700', '常德市', '100018', '02', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2099', '430800', '张家界市', '100018', '02', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2100', '430900', '益阳市', '100018', '02', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2101', '431000', '郴州市', '100018', '02', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2102', '431100', '永州市', '100018', '02', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2103', '431200', '怀化市', '100018', '02', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2104', '431300', '娄底市', '100018', '02', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2105', '433100', '湘西土家族苗族自治州', '100018', '02', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2106', '440101', '市辖区', '440100', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2107', '440103', '荔湾区', '440100', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2108', '440104', '越秀区', '440100', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2109', '440105', '海珠区', '440100', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2110', '440106', '天河区', '440100', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('2111', '440111', '白云区', '440100', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2112', '440112', '黄埔区', '440100', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2113', '440113', '番禺区', '440100', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('2114', '440114', '花都区', '440100', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2115', '440115', '南沙区', '440100', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2116', '440117', '从化区', '440100', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2117', '440118', '增城区', '440100', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2118', '440201', '市辖区', '440200', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2119', '440203', '武江区', '440200', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2120', '440204', '浈江区', '440200', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2121', '440205', '曲江区', '440200', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2122', '440222', '始兴县', '440200', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2123', '440224', '仁化县', '440200', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('2124', '440229', '翁源县', '440200', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2125', '440232', '乳源瑶族自治县', '440200', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('2126', '440233', '新丰县', '440200', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2127', '440281', '乐昌市', '440200', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2128', '440282', '南雄市', '440200', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2129', '440301', '市辖区', '440300', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2130', '440303', '罗湖区', '440300', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2131', '440304', '福田区', '440300', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('2132', '440305', '南山区', '440300', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2133', '440306', '宝安区', '440300', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2134', '440307', '龙岗区', '440300', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2135', '440308', '盐田区', '440300', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2136', '440401', '市辖区', '440400', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2137', '440402', '香洲区', '440400', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2138', '440403', '斗门区', '440400', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2139', '440404', '金湾区', '440400', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2140', '440501', '市辖区', '440500', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2141', '440507', '龙湖区', '440500', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2142', '440511', '金平区', '440500', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2143', '440512', '濠江区', '440500', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2144', '440513', '潮阳区', '440500', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2145', '440514', '潮南区', '440500', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2146', '440515', '澄海区', '440500', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2147', '440523', '南澳县', '440500', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2148', '440601', '市辖区', '440600', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2149', '440604', '禅城区', '440600', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2150', '440605', '南海区', '440600', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2151', '440606', '顺德区', '440600', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2152', '440607', '三水区', '440600', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2153', '440608', '高明区', '440600', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2154', '440701', '市辖区', '440700', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2155', '440703', '蓬江区', '440700', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2156', '440704', '江海区', '440700', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2157', '440705', '新会区', '440700', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2158', '440781', '台山市', '440700', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('2159', '440783', '开平市', '440700', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('2160', '440784', '鹤山市', '440700', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2161', '440785', '恩平市', '440700', '03', '2017-05-26 17:45:07', '2017-05-26 18:07:03', 'E');
INSERT INTO `citys` VALUES ('2162', '440801', '市辖区', '440800', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2163', '440802', '赤坎区', '440800', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2164', '440803', '霞山区', '440800', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2165', '440804', '坡头区', '440800', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2166', '440811', '麻章区', '440800', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2167', '440823', '遂溪县', '440800', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2168', '440825', '徐闻县', '440800', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2169', '440881', '廉江市', '440800', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2170', '440882', '雷州市', '440800', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2171', '440883', '吴川市', '440800', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2172', '440901', '市辖区', '440900', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2173', '440902', '茂南区', '440900', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2174', '440904', '电白区', '440900', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2175', '440981', '高州市', '440900', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2176', '440982', '化州市', '440900', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2177', '440983', '信宜市', '440900', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2178', '441201', '市辖区', '441200', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2179', '441202', '端州区', '441200', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2180', '441203', '鼎湖区', '441200', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2181', '441204', '高要区', '441200', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2182', '441223', '广宁县', '441200', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2183', '441224', '怀集县', '441200', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2184', '441225', '封开县', '441200', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('2185', '441226', '德庆县', '441200', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2186', '441284', '四会市', '441200', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2187', '441301', '市辖区', '441300', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2188', '441302', '惠城区', '441300', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2189', '441303', '惠阳区', '441300', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2190', '441322', '博罗县', '441300', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2191', '441323', '惠东县', '441300', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2192', '441324', '龙门县', '441300', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2193', '441401', '市辖区', '441400', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2194', '441402', '梅江区', '441400', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2195', '441403', '梅县区', '441400', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2196', '441422', '大埔县', '441400', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2197', '441423', '丰顺县', '441400', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('2198', '441424', '五华县', '441400', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2199', '441426', '平远县', '441400', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2200', '441427', '蕉岭县', '441400', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2201', '441481', '兴宁市', '441400', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2202', '441501', '市辖区', '441500', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2203', '441502', '城区', '441500', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2204', '441521', '海丰县', '441500', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2205', '441523', '陆河县', '441500', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2206', '441581', '陆丰市', '441500', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2207', '441601', '市辖区', '441600', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2208', '441602', '源城区', '441600', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2209', '441621', '紫金县', '441600', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2210', '441622', '龙川县', '441600', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2211', '441623', '连平县', '441600', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2212', '441624', '和平县', '441600', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2213', '441625', '东源县', '441600', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2214', '441701', '市辖区', '441700', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2215', '441702', '江城区', '441700', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2216', '441704', '阳东区', '441700', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2217', '441721', '阳西县', '441700', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2218', '441781', '阳春市', '441700', '03', '2017-05-26 17:45:08', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2219', '441801', '市辖区', '441800', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2220', '441802', '清城区', '441800', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2221', '441803', '清新区', '441800', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2222', '441821', '佛冈县', '441800', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('2223', '441823', '阳山县', '441800', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2224', '441825', '连山壮族瑶族自治县', '441800', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2225', '441826', '连南瑶族自治县', '441800', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2226', '441881', '英德市', '441800', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2227', '441882', '连州市', '441800', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2228', '450101', '市辖区', '450100', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2229', '450102', '兴宁区', '450100', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2230', '450103', '青秀区', '450100', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2231', '450105', '江南区', '450100', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2232', '450107', '西乡塘区', '450100', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2233', '450108', '良庆区', '450100', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2234', '450109', '邕宁区', '450100', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2235', '450110', '武鸣区', '450100', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2236', '450123', '隆安县', '450100', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2237', '450124', '马山县', '450100', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2238', '450125', '上林县', '450100', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2239', '450126', '宾阳县', '450100', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2240', '450127', '横县', '450100', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2241', '450201', '市辖区', '450200', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2242', '450202', '城中区', '450200', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2243', '450203', '鱼峰区', '450200', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2244', '450204', '柳南区', '450200', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2245', '450205', '柳北区', '450200', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2246', '450206', '柳江区', '450200', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2247', '450222', '柳城县', '450200', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2248', '450223', '鹿寨县', '450200', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2249', '450224', '融安县', '450200', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('2250', '450225', '融水苗族自治县', '450200', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('2251', '450226', '三江侗族自治县', '450200', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2252', '450301', '市辖区', '450300', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2253', '450302', '秀峰区', '450300', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2254', '450303', '叠彩区', '450300', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2255', '450304', '象山区', '450300', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2256', '450305', '七星区', '450300', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2257', '450311', '雁山区', '450300', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2258', '450312', '临桂区', '450300', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2259', '450321', '阳朔县', '450300', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2260', '450323', '灵川县', '450300', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2261', '450324', '全州县', '450300', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2262', '450325', '兴安县', '450300', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2263', '450326', '永福县', '450300', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2264', '450327', '灌阳县', '450300', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2265', '450328', '龙胜各族自治县', '450300', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2266', '450329', '资源县', '450300', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2267', '450330', '平乐县', '450300', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2268', '450331', '荔浦县', '450300', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2269', '450332', '恭城瑶族自治县', '450300', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2270', '450401', '市辖区', '450400', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2271', '450403', '万秀区', '450400', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2272', '450405', '长洲区', '450400', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2273', '450406', '龙圩区', '450400', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2274', '450421', '苍梧县', '450400', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2275', '450422', '藤县', '450400', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('2276', '450423', '蒙山县', '450400', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2277', '450481', '岑溪市', '450400', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2278', '450501', '市辖区', '450500', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2279', '450502', '海城区', '450500', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2280', '450503', '银海区', '450500', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2281', '450512', '铁山港区', '450500', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('2282', '450521', '合浦县', '450500', '03', '2017-05-26 17:45:09', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2283', '450601', '市辖区', '450600', '03', '2017-05-26 17:45:10', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2284', '450602', '港口区', '450600', '03', '2017-05-26 17:45:10', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2285', '450603', '防城区', '450600', '03', '2017-05-26 17:45:10', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('2286', '450621', '上思县', '450600', '03', '2017-05-26 17:45:10', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2287', '450681', '东兴市', '450600', '03', '2017-05-26 17:45:10', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2288', '450701', '市辖区', '450700', '03', '2017-05-26 17:45:10', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2289', '450702', '钦南区', '450700', '03', '2017-05-26 17:45:10', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2290', '450703', '钦北区', '450700', '03', '2017-05-26 17:45:10', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2291', '450721', '灵山县', '450700', '03', '2017-05-26 17:45:10', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2292', '450722', '浦北县', '450700', '03', '2017-05-26 17:45:10', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2293', '450801', '市辖区', '450800', '03', '2017-05-26 17:45:10', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2294', '450802', '港北区', '450800', '03', '2017-05-26 17:45:10', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2295', '450803', '港南区', '450800', '03', '2017-05-26 17:45:10', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2296', '450804', '覃塘区', '450800', '03', '2017-05-26 17:45:10', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2297', '450821', '平南县', '450800', '03', '2017-05-26 17:45:10', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2298', '450881', '桂平市', '450800', '03', '2017-05-26 17:45:10', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2299', '450901', '市辖区', '450900', '03', '2017-05-26 17:45:10', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2300', '450902', '玉州区', '450900', '03', '2017-05-26 17:45:10', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2301', '450903', '福绵区', '450900', '03', '2017-05-26 17:45:10', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('2302', '450921', '容县', '450900', '03', '2017-05-26 17:45:10', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('2303', '450922', '陆川县', '450900', '03', '2017-05-26 17:45:10', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2304', '450923', '博白县', '450900', '03', '2017-05-26 17:45:10', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2305', '450924', '兴业县', '450900', '03', '2017-05-26 17:45:10', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2306', '450981', '北流市', '450900', '03', '2017-05-26 17:45:10', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2307', '451001', '市辖区', '451000', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2308', '451002', '右江区', '451000', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2309', '451021', '田阳县', '451000', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('2310', '451022', '田东县', '451000', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('2311', '451023', '平果县', '451000', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2312', '451024', '德保县', '451000', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2313', '451026', '那坡县', '451000', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2314', '451027', '凌云县', '451000', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2315', '451028', '乐业县', '451000', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2316', '451029', '田林县', '451000', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('2317', '451030', '西林县', '451000', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2318', '451031', '隆林各族自治县', '451000', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2319', '451081', '靖西市', '451000', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2320', '451101', '市辖区', '451100', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2321', '451102', '八步区', '451100', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2322', '451103', '平桂区', '451100', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2323', '451121', '昭平县', '451100', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2324', '451122', '钟山县', '451100', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2325', '451123', '富川瑶族自治县', '451100', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('2326', '451201', '市辖区', '451200', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2327', '451202', '金城江区', '451200', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2328', '451221', '南丹县', '451200', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2329', '451222', '天峨县', '451200', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('2330', '451223', '凤山县', '451200', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('2331', '451224', '东兰县', '451200', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2332', '451225', '罗城仫佬族自治县', '451200', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2333', '451226', '环江毛南族自治县', '451200', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2334', '451227', '巴马瑶族自治县', '451200', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2335', '451228', '都安瑶族自治县', '451200', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2336', '451229', '大化瑶族自治县', '451200', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2337', '451281', '宜州市', '451200', '03', '2017-05-26 17:45:41', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2338', '451301', '市辖区', '451300', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2339', '451302', '兴宾区', '451300', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2340', '451321', '忻城县', '451300', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2341', '451322', '象州县', '451300', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2342', '451323', '武宣县', '451300', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2343', '451324', '金秀瑶族自治县', '451300', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2344', '451381', '合山市', '451300', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2345', '451401', '市辖区', '451400', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2346', '451402', '江州区', '451400', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2347', '451421', '扶绥县', '451400', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('2348', '451422', '宁明县', '451400', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2349', '451423', '龙州县', '451400', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2350', '451424', '大新县', '451400', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2351', '451425', '天等县', '451400', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('2352', '451481', '凭祥市', '451400', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2353', '450100', '南宁市', '100020', '02', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2354', '450200', '柳州市', '100020', '02', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2355', '450300', '桂林市', '100020', '02', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2356', '450400', '梧州市', '100020', '02', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2357', '450500', '北海市', '100020', '02', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2358', '450600', '防城港市', '100020', '02', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('2359', '450700', '钦州市', '100020', '02', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2360', '450800', '贵港市', '100020', '02', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2361', '450900', '玉林市', '100020', '02', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2362', '451000', '百色市', '100020', '02', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2363', '451100', '贺州市', '100020', '02', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2364', '451200', '河池市', '100020', '02', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2365', '451300', '来宾市', '100020', '02', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2366', '451400', '崇左市', '100020', '02', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2367', '460101', '市辖区', '460100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2368', '460105', '秀英区', '460100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2369', '460106', '龙华区', '460100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2370', '460107', '琼山区', '460100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2371', '460108', '美兰区', '460100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2372', '460201', '市辖区', '460200', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2373', '460202', '海棠区', '460200', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2374', '460203', '吉阳区', '460200', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2375', '460204', '天涯区', '460200', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('2376', '460205', '崖州区', '460200', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2377', '460321', '西沙群岛', '460300', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2378', '460322', '南沙群岛', '460300', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2379', '460323', '中沙群岛的岛礁及其海域', '460300', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2380', '500101', '万州区', '500100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2381', '500102', '涪陵区', '500100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('2382', '500103', '渝中区', '500100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2383', '500104', '大渡口区', '500100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2384', '500105', '江北区', '500100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2385', '500106', '沙坪坝区', '500100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2386', '500107', '九龙坡区', '500100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2387', '500108', '南岸区', '500100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2388', '500109', '北碚区', '500100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2389', '500110', '綦江区', '500100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2390', '500111', '大足区', '500100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2391', '500112', '渝北区', '500100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2392', '500113', '巴南区', '500100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2393', '500114', '黔江区', '500100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2394', '500115', '长寿区', '500100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2395', '500116', '江津区', '500100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2396', '500117', '合川区', '500100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2397', '500118', '永川区', '500100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2398', '500119', '南川区', '500100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2399', '500120', '璧山区', '500100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2400', '500151', '铜梁区', '500100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('2401', '500152', '潼南区', '500100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2402', '500153', '荣昌区', '500100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('2403', '500154', '开州区', '500100', '03', '2017-05-26 17:45:42', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('2404', '500228', '梁平县', '500200', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2405', '500229', '城口县', '500200', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2406', '500230', '丰都县', '500200', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('2407', '500231', '垫江县', '500200', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2408', '500232', '武隆县', '500200', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2409', '500233', '忠县', '500200', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2410', '500235', '云阳县', '500200', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2411', '500236', '奉节县', '500200', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('2412', '500237', '巫山县', '500200', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2413', '500238', '巫溪县', '500200', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2414', '500240', '石柱土家族自治县', '500200', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2415', '500241', '秀山土家族苗族自治县', '500200', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2416', '500242', '酉阳土家族苗族自治县', '500200', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2417', '500243', '彭水苗族土家族自治县', '500200', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2418', '500100', '重庆市', '100022', '02', '2017-05-26 17:45:43', '2017-06-06 16:54:24', 'Z');
INSERT INTO `citys` VALUES ('2419', '500200', '县', '100022', '02', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2420', '510101', '市辖区', '510100', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2421', '510104', '锦江区', '510100', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2422', '510105', '青羊区', '510100', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2423', '510106', '金牛区', '510100', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2424', '510107', '武侯区', '510100', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2425', '510108', '成华区', '510100', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2426', '510112', '龙泉驿区', '510100', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2427', '510113', '青白江区', '510100', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2428', '510114', '新都区', '510100', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2429', '510115', '温江区', '510100', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2430', '510116', '双流区', '510100', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2431', '510121', '金堂县', '510100', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2432', '510124', '郫县', '510100', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2433', '510129', '大邑县', '510100', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2434', '510131', '蒲江县', '510100', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2435', '510132', '新津县', '510100', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2436', '510181', '都江堰市', '510100', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2437', '510182', '彭州市', '510100', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2438', '510183', '邛崃市', '510100', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2439', '510184', '崇州市', '510100', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2440', '510185', '简阳市', '510100', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2441', '510301', '市辖区', '510300', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2442', '510302', '自流井区', '510300', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2443', '510303', '贡井区', '510300', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2444', '510304', '大安区', '510300', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2445', '510311', '沿滩区', '510300', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2446', '510321', '荣县', '510300', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('2447', '510322', '富顺县', '510300', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('2448', '510401', '市辖区', '510400', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2449', '510402', '东区', '510400', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2450', '510403', '西区', '510400', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2451', '510411', '仁和区', '510400', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('2452', '510421', '米易县', '510400', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2453', '510422', '盐边县', '510400', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2454', '510501', '市辖区', '510500', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2455', '510502', '江阳区', '510500', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2456', '510503', '纳溪区', '510500', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2457', '510504', '龙马潭区', '510500', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2458', '510521', '泸县', '510500', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2459', '510522', '合江县', '510500', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2460', '510524', '叙永县', '510500', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2461', '510525', '古蔺县', '510500', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2462', '510601', '市辖区', '510600', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2463', '510603', '旌阳区', '510600', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2464', '510623', '中江县', '510600', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2465', '510626', '罗江县', '510600', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2466', '510681', '广汉市', '510600', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2467', '510682', '什邡市', '510600', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2468', '510683', '绵竹市', '510600', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2469', '510701', '市辖区', '510700', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2470', '510703', '涪城区', '510700', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('2471', '510704', '游仙区', '510700', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2472', '510705', '安州区', '510700', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('2473', '510722', '三台县', '510700', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2474', '510723', '盐亭县', '510700', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2475', '510725', '梓潼县', '510700', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2476', '510726', '北川羌族自治县', '510700', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2477', '510727', '平武县', '510700', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2478', '510781', '江油市', '510700', '03', '2017-05-26 17:45:43', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2479', '510801', '市辖区', '510800', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2480', '510802', '利州区', '510800', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2481', '510811', '昭化区', '510800', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2482', '510812', '朝天区', '510800', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2483', '510821', '旺苍县', '510800', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2484', '510822', '青川县', '510800', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2485', '510823', '剑阁县', '510800', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2486', '510824', '苍溪县', '510800', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2487', '510901', '市辖区', '510900', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2488', '510903', '船山区', '510900', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2489', '510904', '安居区', '510900', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('2490', '510921', '蓬溪县', '510900', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2491', '510922', '射洪县', '510900', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2492', '510923', '大英县', '510900', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2493', '511001', '市辖区', '511000', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2494', '511002', '市中区', '511000', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2495', '511011', '东兴区', '511000', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2496', '511024', '威远县', '511000', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2497', '511025', '资中县', '511000', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2498', '511028', '隆昌县', '511000', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2499', '511101', '市辖区', '511100', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2500', '511102', '市中区', '511100', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2501', '511111', '沙湾区', '511100', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2502', '511112', '五通桥区', '511100', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2503', '511113', '金口河区', '511100', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2504', '511123', '犍为县', '511100', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2505', '511124', '井研县', '511100', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2506', '511126', '夹江县', '511100', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2507', '511129', '沐川县', '511100', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2508', '511132', '峨边彝族自治县', '511100', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'E');
INSERT INTO `citys` VALUES ('2509', '511133', '马边彝族自治县', '511100', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2510', '511181', '峨眉山市', '511100', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'E');
INSERT INTO `citys` VALUES ('2511', '511301', '市辖区', '511300', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2512', '511302', '顺庆区', '511300', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2513', '511303', '高坪区', '511300', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2514', '511304', '嘉陵区', '511300', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2515', '511321', '南部县', '511300', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2516', '511322', '营山县', '511300', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2517', '511323', '蓬安县', '511300', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2518', '511324', '仪陇县', '511300', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2519', '511325', '西充县', '511300', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2520', '511381', '阆中市', '511300', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2521', '511401', '市辖区', '511400', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2522', '511402', '东坡区', '511400', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2523', '511403', '彭山区', '511400', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2524', '511421', '仁寿县', '511400', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('2525', '511423', '洪雅县', '511400', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2526', '511424', '丹棱县', '511400', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2527', '511425', '青神县', '511400', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2528', '511501', '市辖区', '511500', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2529', '511502', '翠屏区', '511500', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2530', '511503', '南溪区', '511500', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2531', '511521', '宜宾县', '511500', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2532', '511523', '江安县', '511500', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2533', '511524', '长宁县', '511500', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2534', '511525', '高县', '511500', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2535', '511526', '珙县', '511500', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2536', '511527', '筠连县', '511500', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2537', '511528', '兴文县', '511500', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2538', '511529', '屏山县', '511500', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2539', '511601', '市辖区', '511600', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2540', '511602', '广安区', '511600', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2541', '511603', '前锋区', '511600', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2542', '511621', '岳池县', '511600', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2543', '511622', '武胜县', '511600', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2544', '511623', '邻水县', '511600', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2545', '511681', '华蓥市', '511600', '03', '2017-05-26 17:45:44', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2546', '511701', '市辖区', '511700', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2547', '511702', '通川区', '511700', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('2548', '511703', '达川区', '511700', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2549', '511722', '宣汉县', '511700', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2550', '511723', '开江县', '511700', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('2551', '511724', '大竹县', '511700', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2552', '511725', '渠县', '511700', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2553', '511781', '万源市', '511700', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2554', '511801', '市辖区', '511800', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2555', '511802', '雨城区', '511800', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2556', '511803', '名山区', '511800', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2557', '511822', '荥经县', '511800', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2558', '511823', '汉源县', '511800', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2559', '511824', '石棉县', '511800', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2560', '511825', '天全县', '511800', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('2561', '511826', '芦山县', '511800', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2562', '511827', '宝兴县', '511800', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2563', '511901', '市辖区', '511900', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2564', '511902', '巴州区', '511900', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2565', '511903', '恩阳区', '511900', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'E');
INSERT INTO `citys` VALUES ('2566', '511921', '通江县', '511900', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('2567', '511922', '南江县', '511900', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2568', '511923', '平昌县', '511900', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2569', '512001', '市辖区', '512000', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2570', '512002', '雁江区', '512000', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2571', '512021', '安岳县', '512000', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('2572', '512022', '乐至县', '512000', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2573', '513201', '马尔康市', '513200', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2574', '513221', '汶川县', '513200', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2575', '513222', '理县', '513200', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2576', '513223', '茂县', '513200', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2577', '513224', '松潘县', '513200', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2578', '513225', '九寨沟县', '513200', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2579', '513226', '金川县', '513200', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2580', '513227', '小金县', '513200', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2581', '513228', '黑水县', '513200', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2582', '513230', '壤塘县', '513200', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('2583', '513231', '阿坝县', '513200', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('2584', '513232', '若尔盖县', '513200', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('2585', '513233', '红原县', '513200', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2586', '513301', '康定市', '513300', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('2587', '513322', '泸定县', '513300', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2588', '513323', '丹巴县', '513300', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2589', '513324', '九龙县', '513300', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2590', '513325', '雅江县', '513300', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2591', '513326', '道孚县', '513300', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2592', '513327', '炉霍县', '513300', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2593', '513328', '甘孜县', '513300', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2594', '513329', '新龙县', '513300', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2595', '513330', '德格县', '513300', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2596', '513331', '白玉县', '513300', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2597', '513332', '石渠县', '513300', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2598', '513333', '色达县', '513300', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2599', '513334', '理塘县', '513300', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2600', '513335', '巴塘县', '513300', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2601', '513336', '乡城县', '513300', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2602', '513337', '稻城县', '513300', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2603', '513338', '得荣县', '513300', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2604', '513401', '西昌市', '513400', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2605', '513422', '木里藏族自治县', '513400', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2606', '513423', '盐源县', '513400', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2607', '513424', '德昌县', '513400', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2608', '513425', '会理县', '513400', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2609', '513426', '会东县', '513400', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2610', '513427', '宁南县', '513400', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2611', '513428', '普格县', '513400', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2612', '513429', '布拖县', '513400', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2613', '513430', '金阳县', '513400', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2614', '513431', '昭觉县', '513400', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2615', '513432', '喜德县', '513400', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2616', '513433', '冕宁县', '513400', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2617', '513434', '越西县', '513400', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2618', '513435', '甘洛县', '513400', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2619', '513436', '美姑县', '513400', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2620', '513437', '雷波县', '513400', '03', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2621', '510100', '成都市', '100023', '02', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2622', '510300', '自贡市', '100023', '02', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2623', '510400', '攀枝花市', '100023', '02', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2624', '510500', '泸州市', '100023', '02', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2625', '510600', '德阳市', '100023', '02', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2626', '510700', '绵阳市', '100023', '02', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2627', '510800', '广元市', '100023', '02', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2628', '510900', '遂宁市', '100023', '02', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2629', '511000', '内江市', '100023', '02', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2630', '511100', '乐山市', '100023', '02', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2631', '511300', '南充市', '100023', '02', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2632', '511400', '眉山市', '100023', '02', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2633', '511500', '宜宾市', '100023', '02', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2634', '511600', '广安市', '100023', '02', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2635', '511700', '达州市', '100023', '02', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2636', '511800', '雅安市', '100023', '02', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2637', '511900', '巴中市', '100023', '02', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2638', '512000', '资阳市', '100023', '02', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2639', '513200', '阿坝藏族羌族自治州', '100023', '02', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('2640', '513300', '甘孜藏族自治州', '100023', '02', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2641', '513400', '凉山彝族自治州', '100023', '02', '2017-05-26 17:45:45', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2642', '520101', '市辖区', '520100', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2643', '520102', '南明区', '520100', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2644', '520103', '云岩区', '520100', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2645', '520111', '花溪区', '520100', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2646', '520112', '乌当区', '520100', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2647', '520113', '白云区', '520100', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2648', '520115', '观山湖区', '520100', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2649', '520121', '开阳县', '520100', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('2650', '520122', '息烽县', '520100', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2651', '520123', '修文县', '520100', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2652', '520181', '清镇市', '520100', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2653', '520201', '钟山区', '520200', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2654', '520203', '六枝特区', '520200', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2655', '520221', '水城县', '520200', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2656', '520222', '盘县', '520200', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2657', '520301', '市辖区', '520300', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2658', '520302', '红花岗区', '520300', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2659', '520303', '汇川区', '520300', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2660', '520304', '播州区', '520300', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2661', '520322', '桐梓县', '520300', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('2662', '520323', '绥阳县', '520300', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2663', '520324', '正安县', '520300', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2664', '520325', '道真仡佬族苗族自治县', '520300', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2665', '520326', '务川仡佬族苗族自治县', '520300', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2666', '520327', '凤冈县', '520300', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('2667', '520328', '湄潭县', '520300', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2668', '520329', '余庆县', '520300', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2669', '520330', '习水县', '520300', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2670', '520381', '赤水市', '520300', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2671', '520382', '仁怀市', '520300', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('2672', '520401', '市辖区', '520400', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2673', '520402', '西秀区', '520400', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2674', '520403', '平坝区', '520400', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2675', '520422', '普定县', '520400', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2676', '520423', '镇宁布依族苗族自治县', '520400', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2677', '520424', '关岭布依族苗族自治县', '520400', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2678', '520425', '紫云苗族布依族自治县', '520400', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2679', '520501', '市辖区', '520500', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2680', '520502', '七星关区', '520500', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2681', '520521', '大方县', '520500', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2682', '520522', '黔西县', '520500', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2683', '520523', '金沙县', '520500', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2684', '520524', '织金县', '520500', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2685', '520525', '纳雍县', '520500', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2686', '520526', '威宁彝族回族苗族自治县', '520500', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2687', '520527', '赫章县', '520500', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2688', '520601', '市辖区', '520600', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2689', '520602', '碧江区', '520600', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2690', '520603', '万山区', '520600', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2691', '520621', '江口县', '520600', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2692', '520622', '玉屏侗族自治县', '520600', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2693', '520623', '石阡县', '520600', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2694', '520624', '思南县', '520600', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2695', '520625', '印江土家族苗族自治县', '520600', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2696', '520626', '德江县', '520600', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2697', '520627', '沿河土家族自治县', '520600', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2698', '520628', '松桃苗族自治县', '520600', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2699', '522301', '兴义市', '522300', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2700', '522322', '兴仁县', '522300', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2701', '522323', '普安县', '522300', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2702', '522324', '晴隆县', '522300', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2703', '522325', '贞丰县', '522300', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2704', '522326', '望谟县', '522300', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2705', '522327', '册亨县', '522300', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2706', '522328', '安龙县', '522300', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('2707', '522601', '凯里市', '522600', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('2708', '522622', '黄平县', '522600', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2709', '522623', '施秉县', '522600', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2710', '522624', '三穗县', '522600', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2711', '522625', '镇远县', '522600', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2712', '522626', '岑巩县', '522600', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2713', '522627', '天柱县', '522600', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('2714', '522628', '锦屏县', '522600', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2715', '522629', '剑河县', '522600', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2716', '522630', '台江县', '522600', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('2717', '522631', '黎平县', '522600', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2718', '522632', '榕江县', '522600', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2719', '522633', '从江县', '522600', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2720', '522634', '雷山县', '522600', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2721', '522635', '麻江县', '522600', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2722', '522636', '丹寨县', '522600', '03', '2017-05-26 17:45:46', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2723', '522701', '都匀市', '522700', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2724', '522702', '福泉市', '522700', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('2725', '522722', '荔波县', '522700', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2726', '522723', '贵定县', '522700', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2727', '522725', '瓮安县', '522700', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2728', '522726', '独山县', '522700', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2729', '522727', '平塘县', '522700', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2730', '522728', '罗甸县', '522700', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2731', '522729', '长顺县', '522700', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2732', '522730', '龙里县', '522700', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2733', '522731', '惠水县', '522700', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2734', '522732', '三都水族自治县', '522700', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2735', '520100', '贵阳市', '100024', '02', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2736', '520200', '六盘水市', '100024', '02', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2737', '520300', '遵义市', '100024', '02', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2738', '520400', '安顺市', '100024', '02', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('2739', '520500', '毕节市', '100024', '02', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2740', '520600', '铜仁市', '100024', '02', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('2741', '522300', '黔西南布依族苗族自治州', '100024', '02', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2742', '522600', '黔东南苗族侗族自治州', '100024', '02', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2743', '522700', '黔南布依族苗族自治州', '100024', '02', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2744', '530101', '市辖区', '530100', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2745', '530102', '五华区', '530100', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2746', '530103', '盘龙区', '530100', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2747', '530111', '官渡区', '530100', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2748', '530112', '西山区', '530100', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2749', '530113', '东川区', '530100', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2750', '530114', '呈贡区', '530100', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2751', '530122', '晋宁县', '530100', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2752', '530124', '富民县', '530100', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('2753', '530125', '宜良县', '530100', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2754', '530126', '石林彝族自治县', '530100', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2755', '530127', '嵩明县', '530100', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2756', '530128', '禄劝彝族苗族自治县', '530100', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2757', '530129', '寻甸回族彝族自治县', '530100', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2758', '530181', '安宁市', '530100', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('2759', '530301', '市辖区', '530300', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2760', '530302', '麒麟区', '530300', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2761', '530303', '沾益区', '530300', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2762', '530321', '马龙县', '530300', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2763', '530322', '陆良县', '530300', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2764', '530323', '师宗县', '530300', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2765', '530324', '罗平县', '530300', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2766', '530325', '富源县', '530300', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('2767', '530326', '会泽县', '530300', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2768', '530381', '宣威市', '530300', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2769', '530401', '市辖区', '530400', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2770', '530402', '红塔区', '530400', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2771', '530403', '江川区', '530400', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2772', '530422', '澄江县', '530400', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2773', '530423', '通海县', '530400', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('2774', '530424', '华宁县', '530400', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2775', '530425', '易门县', '530400', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2776', '530426', '峨山彝族自治县', '530400', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'E');
INSERT INTO `citys` VALUES ('2777', '530427', '新平彝族傣族自治县', '530400', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2778', '530428', '元江哈尼族彝族傣族自治县', '530400', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2779', '530501', '市辖区', '530500', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2780', '530502', '隆阳区', '530500', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2781', '530521', '施甸县', '530500', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2782', '530523', '龙陵县', '530500', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2783', '530524', '昌宁县', '530500', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2784', '530581', '腾冲市', '530500', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('2785', '530601', '市辖区', '530600', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2786', '530602', '昭阳区', '530600', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2787', '530621', '鲁甸县', '530600', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2788', '530622', '巧家县', '530600', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2789', '530623', '盐津县', '530600', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2790', '530624', '大关县', '530600', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2791', '530625', '永善县', '530600', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2792', '530626', '绥江县', '530600', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2793', '530627', '镇雄县', '530600', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2794', '530628', '彝良县', '530600', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2795', '530629', '威信县', '530600', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2796', '530630', '水富县', '530600', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2797', '530701', '市辖区', '530700', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2798', '530702', '古城区', '530700', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2799', '530721', '玉龙纳西族自治县', '530700', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2800', '530722', '永胜县', '530700', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2801', '530723', '华坪县', '530700', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2802', '530724', '宁蒗彝族自治县', '530700', '03', '2017-05-26 17:45:47', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2803', '530801', '市辖区', '530800', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2804', '530802', '思茅区', '530800', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2805', '530821', '宁洱哈尼族彝族自治县', '530800', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2806', '530822', '墨江哈尼族自治县', '530800', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2807', '530823', '景东彝族自治县', '530800', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2808', '530824', '景谷傣族彝族自治县', '530800', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2809', '530825', '镇沅彝族哈尼族拉祜族自治县', '530800', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2810', '530826', '江城哈尼族彝族自治县', '530800', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2811', '530827', '孟连傣族拉祜族佤族自治县', '530800', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2812', '530828', '澜沧拉祜族自治县', '530800', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2813', '530829', '西盟佤族自治县', '530800', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2814', '530901', '市辖区', '530900', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2815', '530902', '临翔区', '530900', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2816', '530921', '凤庆县', '530900', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('2817', '530922', '云县', '530900', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2818', '530923', '永德县', '530900', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2819', '530924', '镇康县', '530900', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2820', '530925', '双江拉祜族佤族布朗族傣族自治县', '530900', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2821', '530926', '耿马傣族佤族自治县', '530900', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2822', '530927', '沧源佤族自治县', '530900', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2823', '532301', '楚雄市', '532300', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2824', '532322', '双柏县', '532300', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2825', '532323', '牟定县', '532300', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2826', '532324', '南华县', '532300', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2827', '532325', '姚安县', '532300', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2828', '532326', '大姚县', '532300', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2829', '532327', '永仁县', '532300', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2830', '532328', '元谋县', '532300', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2831', '532329', '武定县', '532300', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2832', '532331', '禄丰县', '532300', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2833', '532501', '个旧市', '532500', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2834', '532502', '开远市', '532500', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('2835', '532503', '蒙自市', '532500', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2836', '532504', '弥勒市', '532500', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2837', '532523', '屏边苗族自治县', '532500', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2838', '532524', '建水县', '532500', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2839', '532525', '石屏县', '532500', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2840', '532527', '泸西县', '532500', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2841', '532528', '元阳县', '532500', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2842', '532529', '红河县', '532500', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2843', '532530', '金平苗族瑶族傣族自治县', '532500', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2844', '532531', '绿春县', '532500', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2845', '532532', '河口瑶族自治县', '532500', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2846', '532601', '文山市', '532600', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2847', '532622', '砚山县', '532600', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2848', '532623', '西畴县', '532600', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2849', '532624', '麻栗坡县', '532600', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2850', '532625', '马关县', '532600', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2851', '532626', '丘北县', '532600', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2852', '532627', '广南县', '532600', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2853', '532628', '富宁县', '532600', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('2854', '532801', '景洪市', '532800', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2855', '532822', '勐海县', '532800', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2856', '532823', '勐腊县', '532800', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2857', '532901', '大理市', '532900', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2858', '532922', '漾濞彝族自治县', '532900', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2859', '532923', '祥云县', '532900', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2860', '532924', '宾川县', '532900', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2861', '532925', '弥渡县', '532900', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2862', '532926', '南涧彝族自治县', '532900', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2863', '532927', '巍山彝族回族自治县', '532900', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2864', '532928', '永平县', '532900', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2865', '532929', '云龙县', '532900', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2866', '532930', '洱源县', '532900', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'E');
INSERT INTO `citys` VALUES ('2867', '532931', '剑川县', '532900', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2868', '532932', '鹤庆县', '532900', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2869', '533102', '瑞丽市', '533100', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('2870', '533103', '芒市', '533100', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2871', '533122', '梁河县', '533100', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2872', '533123', '盈江县', '533100', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2873', '533124', '陇川县', '533100', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2874', '533301', '泸水市', '533300', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2875', '533323', '福贡县', '533300', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('2876', '533324', '贡山独龙族怒族自治县', '533300', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2877', '533325', '兰坪白族普米族自治县', '533300', '03', '2017-05-26 17:45:48', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2878', '533401', '香格里拉市', '533400', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2879', '533422', '德钦县', '533400', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2880', '533423', '维西傈僳族自治县', '533400', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2881', '530100', '昆明市', '100025', '02', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('2882', '530300', '曲靖市', '100025', '02', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2883', '530400', '玉溪市', '100025', '02', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2884', '530500', '保山市', '100025', '02', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2885', '530600', '昭通市', '100025', '02', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2886', '530700', '丽江市', '100025', '02', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2887', '530800', '普洱市', '100025', '02', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2888', '530900', '临沧市', '100025', '02', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2889', '532300', '楚雄彝族自治州', '100025', '02', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2890', '532500', '红河哈尼族彝族自治州', '100025', '02', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2891', '532600', '文山壮族苗族自治州', '100025', '02', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2892', '532800', '西双版纳傣族自治州', '100025', '02', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2893', '532900', '大理白族自治州', '100025', '02', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2894', '533100', '德宏傣族景颇族自治州', '100025', '02', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2895', '533300', '怒江傈僳族自治州', '100025', '02', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2896', '533400', '迪庆藏族自治州', '100025', '02', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2897', '540101', '市辖区', '540100', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2898', '540102', '城关区', '540100', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2899', '540103', '堆龙德庆区', '540100', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2900', '540121', '林周县', '540100', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2901', '540122', '当雄县', '540100', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2902', '540123', '尼木县', '540100', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2903', '540124', '曲水县', '540100', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2904', '540126', '达孜县', '540100', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2905', '540127', '墨竹工卡县', '540100', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2906', '540202', '桑珠孜区', '540200', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2907', '540221', '南木林县', '540200', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2908', '540222', '江孜县', '540200', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2909', '540223', '定日县', '540200', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2910', '540224', '萨迦县', '540200', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2911', '540225', '拉孜县', '540200', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2912', '540226', '昂仁县', '540200', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('2913', '540227', '谢通门县', '540200', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2914', '540228', '白朗县', '540200', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2915', '540229', '仁布县', '540200', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('2916', '540230', '康马县', '540200', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('2917', '540231', '定结县', '540200', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2918', '540232', '仲巴县', '540200', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2919', '540233', '亚东县', '540200', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2920', '540234', '吉隆县', '540200', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2921', '540235', '聂拉木县', '540200', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2922', '540236', '萨嘎县', '540200', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2923', '540237', '岗巴县', '540200', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2924', '540302', '卡若区', '540300', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('2925', '540321', '江达县', '540300', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2926', '540322', '贡觉县', '540300', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2927', '540323', '类乌齐县', '540300', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2928', '540324', '丁青县', '540300', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('2929', '540325', '察雅县', '540300', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2930', '540326', '八宿县', '540300', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2931', '540327', '左贡县', '540300', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2932', '540328', '芒康县', '540300', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2933', '540329', '洛隆县', '540300', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2934', '540330', '边坝县', '540300', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2935', '540402', '巴宜区', '540400', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2936', '540421', '工布江达县', '540400', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2937', '540422', '米林县', '540400', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2938', '540423', '墨脱县', '540400', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('2939', '540424', '波密县', '540400', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2940', '540425', '察隅县', '540400', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2941', '540426', '朗县', '540400', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2942', '540501', '市辖区', '540500', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2943', '540502', '乃东区', '540500', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2944', '540521', '扎囊县', '540500', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2945', '540522', '贡嘎县', '540500', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2946', '540523', '桑日县', '540500', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2947', '540524', '琼结县', '540500', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2948', '540525', '曲松县', '540500', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('2949', '540526', '措美县', '540500', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2950', '540527', '洛扎县', '540500', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2951', '540528', '加查县', '540500', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2952', '540529', '隆子县', '540500', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2953', '540530', '错那县', '540500', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2954', '540531', '浪卡子县', '540500', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2955', '542421', '那曲县', '542400', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2956', '542422', '嘉黎县', '542400', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('2957', '542423', '比如县', '542400', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2958', '542424', '聂荣县', '542400', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2959', '542425', '安多县', '542400', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('2960', '542426', '申扎县', '542400', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2961', '542427', '索县', '542400', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2962', '542428', '班戈县', '542400', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2963', '542429', '巴青县', '542400', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2964', '542430', '尼玛县', '542400', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2965', '542431', '双湖县', '542400', '03', '2017-05-26 17:45:49', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2966', '542521', '普兰县', '542500', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('2967', '542522', '札达县', '542500', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2968', '542523', '噶尔县', '542500', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2969', '542524', '日土县', '542500', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('2970', '542525', '革吉县', '542500', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2971', '542526', '改则县', '542500', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2972', '542527', '措勤县', '542500', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2973', '540100', '拉萨市', '100026', '02', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2974', '540200', '日喀则市', '100026', '02', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('2975', '540300', '昌都市', '100026', '02', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2976', '540400', '林芝市', '100026', '02', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2977', '540500', '山南市', '100026', '02', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2978', '542400', '那曲地区', '100026', '02', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('2979', '542500', '阿里地区', '100026', '02', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('2980', '610101', '市辖区', '610100', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2981', '610102', '新城区', '610100', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('2982', '610103', '碑林区', '610100', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('2983', '610104', '莲湖区', '610100', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2984', '610111', '灞桥区', '610100', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2985', '610112', '未央区', '610100', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2986', '610113', '雁塔区', '610100', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2987', '610114', '阎良区', '610100', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2988', '610115', '临潼区', '610100', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2989', '610116', '长安区', '610100', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('2990', '610117', '高陵区', '610100', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('2991', '610122', '蓝田县', '610100', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('2992', '610124', '周至县', '610100', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('2993', '610125', '户县', '610100', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('2994', '610201', '市辖区', '610200', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('2995', '610202', '王益区', '610200', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('2996', '610203', '印台区', '610200', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2997', '610204', '耀州区', '610200', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2998', '610222', '宜君县', '610200', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('2999', '610301', '市辖区', '610300', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3000', '610302', '渭滨区', '610300', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('3001', '610303', '金台区', '610300', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('3002', '610304', '陈仓区', '610300', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('3003', '610322', '凤翔县', '610300', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('3004', '610323', '岐山县', '610300', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3005', '610324', '扶风县', '610300', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('3006', '610326', '眉县', '610300', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('3007', '610327', '陇县', '610300', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3008', '610328', '千阳县', '610300', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3009', '610329', '麟游县', '610300', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3010', '610330', '凤县', '610300', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('3011', '610331', '太白县', '610300', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('3012', '610401', '市辖区', '610400', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3013', '610402', '秦都区', '610400', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3014', '610403', '杨陵区', '610400', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3015', '610404', '渭城区', '610400', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('3016', '610422', '三原县', '610400', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3017', '610423', '泾阳县', '610400', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3018', '610424', '乾县', '610400', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3019', '610425', '礼泉县', '610400', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3020', '610426', '永寿县', '610400', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3021', '610427', '彬县', '610400', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('3022', '610428', '长武县', '610400', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('3023', '610429', '旬邑县', '610400', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('3024', '610430', '淳化县', '610400', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('3025', '610431', '武功县', '610400', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('3026', '610481', '兴平市', '610400', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('3027', '610501', '市辖区', '610500', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3028', '610502', '临渭区', '610500', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3029', '610503', '华州区', '610500', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3030', '610522', '潼关县', '610500', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3031', '610523', '大荔县', '610500', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('3032', '610524', '合阳县', '610500', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3033', '610525', '澄城县', '610500', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('3034', '610526', '蒲城县', '610500', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('3035', '610527', '白水县', '610500', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('3036', '610528', '富平县', '610500', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('3037', '610581', '韩城市', '610500', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3038', '610582', '华阴市', '610500', '03', '2017-05-26 17:45:50', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3039', '620101', '市辖区', '620100', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3040', '620102', '城关区', '620100', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('3041', '620103', '七里河区', '620100', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3042', '620104', '西固区', '620100', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('3043', '620105', '安宁区', '620100', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('3044', '620111', '红古区', '620100', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3045', '620121', '永登县', '620100', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3046', '620122', '皋兰县', '620100', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('3047', '620123', '榆中县', '620100', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3048', '620201', '市辖区', '620200', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3049', '620301', '市辖区', '620300', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3050', '620302', '金川区', '620300', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('3051', '620321', '永昌县', '620300', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3052', '620401', '市辖区', '620400', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3053', '620402', '白银区', '620400', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('3054', '620403', '平川区', '620400', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('3055', '620421', '靖远县', '620400', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('3056', '620422', '会宁县', '620400', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3057', '620423', '景泰县', '620400', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('3058', '620501', '市辖区', '620500', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3059', '620502', '秦州区', '620500', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3060', '620503', '麦积区', '620500', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('3061', '620521', '清水县', '620500', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3062', '620522', '秦安县', '620500', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3063', '620523', '甘谷县', '620500', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('3064', '620524', '武山县', '620500', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('3065', '620525', '张家川回族自治县', '620500', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3066', '620601', '市辖区', '620600', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3067', '620602', '凉州区', '620600', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3068', '620621', '民勤县', '620600', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('3069', '620622', '古浪县', '620600', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('3070', '620623', '天祝藏族自治县', '620600', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('3071', '620701', '市辖区', '620700', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3072', '620702', '甘州区', '620700', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('3073', '620721', '肃南裕固族自治县', '620700', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3074', '620722', '民乐县', '620700', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('3075', '620723', '临泽县', '620700', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3076', '620724', '高台县', '620700', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('3077', '620725', '山丹县', '620700', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3078', '620801', '市辖区', '620800', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3079', '620802', '崆峒区', '620800', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3080', '620821', '泾川县', '620800', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3081', '620822', '灵台县', '620800', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3082', '620823', '崇信县', '620800', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('3083', '620824', '华亭县', '620800', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3084', '620825', '庄浪县', '620800', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3085', '620826', '静宁县', '620800', '03', '2017-05-26 17:47:06', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('3086', '620901', '市辖区', '620900', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3087', '620902', '肃州区', '620900', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3088', '620921', '金塔县', '620900', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('3089', '620922', '瓜州县', '620900', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('3090', '620923', '肃北蒙古族自治县', '620900', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3091', '620924', '阿克塞哈萨克族自治县', '620900', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('3092', '620981', '玉门市', '620900', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3093', '620982', '敦煌市', '620900', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('3094', '621001', '市辖区', '621000', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3095', '621002', '西峰区', '621000', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('3096', '621021', '庆城县', '621000', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3097', '621022', '环县', '621000', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3098', '621023', '华池县', '621000', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3099', '621024', '合水县', '621000', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3100', '621025', '正宁县', '621000', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3101', '621026', '宁县', '621000', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('3102', '621027', '镇原县', '621000', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3103', '621101', '市辖区', '621100', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3104', '621102', '安定区', '621100', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('3105', '621121', '通渭县', '621100', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('3106', '621122', '陇西县', '621100', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3107', '621123', '渭源县', '621100', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('3108', '621124', '临洮县', '621100', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3109', '621125', '漳县', '621100', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3110', '621126', '岷县', '621100', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3111', '621201', '市辖区', '621200', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3112', '621202', '武都区', '621200', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('3113', '621221', '成县', '621200', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('3114', '621222', '文县', '621200', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('3115', '621223', '宕昌县', '621200', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3116', '621224', '康县', '621200', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('3117', '621225', '西和县', '621200', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('3118', '621226', '礼县', '621200', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3119', '621227', '徽县', '621200', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3120', '621228', '两当县', '621200', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3121', '622901', '临夏市', '622900', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3122', '622921', '临夏县', '622900', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3123', '622922', '康乐县', '622900', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('3124', '622923', '永靖县', '622900', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3125', '622924', '广河县', '622900', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('3126', '622925', '和政县', '622900', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3127', '622926', '东乡族自治县', '622900', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('3128', '622927', '积石山保安族东乡族撒拉族自治县', '622900', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('3129', '623001', '合作市', '623000', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3130', '623021', '临潭县', '623000', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3131', '623022', '卓尼县', '623000', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3132', '623023', '舟曲县', '623000', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3133', '623024', '迭部县', '623000', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('3134', '623025', '玛曲县', '623000', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('3135', '623026', '碌曲县', '623000', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3136', '623027', '夏河县', '623000', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('3137', '620100', '兰州市', '100028', '02', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3138', '620200', '嘉峪关市', '100028', '02', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('3139', '620300', '金昌市', '100028', '02', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('3140', '620400', '白银市', '100028', '02', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('3141', '620500', '天水市', '100028', '02', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('3142', '620600', '武威市', '100028', '02', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('3143', '620700', '张掖市', '100028', '02', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3144', '620800', '平凉市', '100028', '02', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('3145', '620900', '酒泉市', '100028', '02', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('3146', '621000', '庆阳市', '100028', '02', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3147', '621100', '定西市', '100028', '02', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('3148', '621200', '陇南市', '100028', '02', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3149', '622900', '临夏回族自治州', '100028', '02', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3150', '623000', '甘南藏族自治州', '100028', '02', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('3151', '630101', '市辖区', '630100', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3152', '630102', '城东区', '630100', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('3153', '630103', '城中区', '630100', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('3154', '630104', '城西区', '630100', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('3155', '630105', '城北区', '630100', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('3156', '630121', '大通回族土族自治县', '630100', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('3157', '630122', '湟中县', '630100', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3158', '630123', '湟源县', '630100', '03', '2017-05-26 17:47:07', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3159', '630202', '乐都区', '630200', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3160', '630203', '平安区', '630200', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('3161', '630222', '民和回族土族自治县', '630200', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('3162', '630223', '互助土族自治县', '630200', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3163', '630224', '化隆回族自治县', '630200', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3164', '630225', '循化撒拉族自治县', '630200', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('3165', '632221', '门源回族自治县', '632200', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('3166', '632222', '祁连县', '632200', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3167', '632223', '海晏县', '632200', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3168', '632224', '刚察县', '632200', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('3169', '632321', '同仁县', '632300', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('3170', '632322', '尖扎县', '632300', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('3171', '632323', '泽库县', '632300', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3172', '632324', '河南蒙古族自治县', '632300', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3173', '632521', '共和县', '632500', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('3174', '632522', '同德县', '632500', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('3175', '632523', '贵德县', '632500', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('3176', '632524', '兴海县', '632500', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('3177', '632525', '贵南县', '632500', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('3178', '632621', '玛沁县', '632600', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('3179', '632622', '班玛县', '632600', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('3180', '632623', '甘德县', '632600', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('3181', '632624', '达日县', '632600', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('3182', '632625', '久治县', '632600', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('3183', '632626', '玛多县', '632600', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('3184', '632701', '玉树市', '632700', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3185', '632722', '杂多县', '632700', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3186', '632723', '称多县', '632700', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('3187', '632724', '治多县', '632700', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3188', '632725', '囊谦县', '632700', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('3189', '632726', '曲麻莱县', '632700', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3190', '632801', '格尔木市', '632800', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('3191', '632802', '德令哈市', '632800', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('3192', '632821', '乌兰县', '632800', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('3193', '632822', '都兰县', '632800', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('3194', '632823', '天峻县', '632800', '03', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('3195', '630100', '西宁市', '100029', '02', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('3196', '630200', '海东市', '100029', '02', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3197', '632200', '海北藏族自治州', '100029', '02', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3198', '632300', '黄南藏族自治州', '100029', '02', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3199', '632500', '海南藏族自治州', '100029', '02', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3200', '632600', '果洛藏族自治州', '100029', '02', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('3201', '632700', '玉树藏族自治州', '100029', '02', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3202', '632800', '海西蒙古族藏族自治州', '100029', '02', '2017-05-26 17:47:08', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3203', '640101', '市辖区', '640100', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3204', '640104', '兴庆区', '640100', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('3205', '640105', '西夏区', '640100', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('3206', '640106', '金凤区', '640100', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('3207', '640121', '永宁县', '640100', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3208', '640122', '贺兰县', '640100', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3209', '640181', '灵武市', '640100', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3210', '640201', '市辖区', '640200', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3211', '640202', '大武口区', '640200', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('3212', '640205', '惠农区', '640200', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3213', '640221', '平罗县', '640200', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('3214', '640301', '市辖区', '640300', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3215', '640302', '利通区', '640300', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3216', '640303', '红寺堡区', '640300', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3217', '640323', '盐池县', '640300', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3218', '640324', '同心县', '640300', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('3219', '640381', '青铜峡市', '640300', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3220', '640401', '市辖区', '640400', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3221', '640402', '原州区', '640400', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3222', '640422', '西吉县', '640400', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('3223', '640423', '隆德县', '640400', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3224', '640424', '泾源县', '640400', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3225', '640425', '彭阳县', '640400', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('3226', '640501', '市辖区', '640500', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3227', '640502', '沙坡头区', '640500', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3228', '640521', '中宁县', '640500', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3229', '640522', '海原县', '640500', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3230', '640100', '银川市', '100030', '02', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3231', '640200', '石嘴山市', '100030', '02', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3232', '640300', '吴忠市', '100030', '02', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('3233', '640400', '固原市', '100030', '02', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('3234', '640500', '中卫市', '100030', '02', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3235', '650101', '市辖区', '650100', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3236', '650102', '天山区', '650100', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('3237', '650103', '沙依巴克区', '650100', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3238', '650104', '新市区', '650100', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('3239', '650105', '水磨沟区', '650100', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3240', '650106', '头屯河区', '650100', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('3241', '650107', '达坂城区', '650100', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('3242', '650109', '米东区', '650100', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('3243', '650121', '乌鲁木齐县', '650100', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('3244', '650201', '市辖区', '650200', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3245', '650202', '独山子区', '650200', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('3246', '650203', '克拉玛依区', '650200', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('3247', '650204', '白碱滩区', '650200', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('3248', '650205', '乌尔禾区', '650200', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('3249', '650402', '高昌区', '650400', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('3250', '650421', '鄯善县', '650400', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3251', '650422', '托克逊县', '650400', '03', '2017-05-26 17:47:09', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('3252', '650502', '伊州区', '650500', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3253', '650521', '巴里坤哈萨克自治县', '650500', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('3254', '650522', '伊吾县', '650500', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3255', '652301', '昌吉市', '652300', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('3256', '652302', '阜康市', '652300', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('3257', '652323', '呼图壁县', '652300', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3258', '652324', '玛纳斯县', '652300', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('3259', '652325', '奇台县', '652300', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3260', '652327', '吉木萨尔县', '652300', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('3261', '652328', '木垒哈萨克自治县', '652300', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('3262', '652701', '博乐市', '652700', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('3263', '652702', '阿拉山口市', '652700', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('3264', '652722', '精河县', '652700', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('3265', '652723', '温泉县', '652700', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('3266', '652801', '库尔勒市', '652800', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('3267', '652822', '轮台县', '652800', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3268', '652823', '尉犁县', '652800', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('3269', '652824', '若羌县', '652800', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('3270', '652825', '且末县', '652800', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3271', '652826', '焉耆回族自治县', '652800', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3272', '652827', '和静县', '652800', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3273', '652828', '和硕县', '652800', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3274', '652829', '博湖县', '652800', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('3275', '652901', '阿克苏市', '652900', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('3276', '652922', '温宿县', '652900', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('3277', '652923', '库车县', '652900', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('3278', '652924', '沙雅县', '652900', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3279', '652925', '新和县', '652900', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('3280', '652926', '拜城县', '652900', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('3281', '652927', '乌什县', '652900', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('3282', '652928', '阿瓦提县', '652900', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('3283', '652929', '柯坪县', '652900', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('3284', '653001', '阿图什市', '653000', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('3285', '653022', '阿克陶县', '653000', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('3286', '653023', '阿合奇县', '653000', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('3287', '653024', '乌恰县', '653000', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('3288', '653101', '喀什市', '653100', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('3289', '653121', '疏附县', '653100', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3290', '653122', '疏勒县', '653100', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3291', '653123', '英吉沙县', '653100', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3292', '653124', '泽普县', '653100', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3293', '653125', '莎车县', '653100', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3294', '653126', '叶城县', '653100', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3295', '653127', '麦盖提县', '653100', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('3296', '653128', '岳普湖县', '653100', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3297', '653129', '伽师县', '653100', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3298', '653130', '巴楚县', '653100', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('3299', '653131', '塔什库尔干塔吉克自治县', '653100', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('3300', '653201', '和田市', '653200', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3301', '653221', '和田县', '653200', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3302', '653222', '墨玉县', '653200', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('3303', '653223', '皮山县', '653200', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('3304', '653224', '洛浦县', '653200', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3305', '653225', '策勒县', '653200', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('3306', '653226', '于田县', '653200', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3307', '653227', '民丰县', '653200', '03', '2017-05-26 17:47:10', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('3308', '654002', '伊宁市', '654000', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3309', '654003', '奎屯市', '654000', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('3310', '654004', '霍尔果斯市', '654000', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3311', '654021', '伊宁县', '654000', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3312', '654022', '察布查尔锡伯自治县', '654000', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('3313', '654023', '霍城县', '654000', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3314', '654024', '巩留县', '654000', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('3315', '654025', '新源县', '654000', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('3316', '654026', '昭苏县', '654000', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3317', '654027', '特克斯县', '654000', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('3318', '654028', '尼勒克县', '654000', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('3319', '654201', '塔城市', '654200', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('3320', '654202', '乌苏市', '654200', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('3321', '654221', '额敏县', '654200', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'E');
INSERT INTO `citys` VALUES ('3322', '654223', '沙湾县', '654200', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3323', '654224', '托里县', '654200', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('3324', '654225', '裕民县', '654200', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3325', '654226', '和布克赛尔蒙古自治县', '654200', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3326', '654301', '阿勒泰市', '654300', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('3327', '654321', '布尔津县', '654300', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('3328', '654322', '富蕴县', '654300', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('3329', '654323', '福海县', '654300', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('3330', '654324', '哈巴河县', '654300', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3331', '654325', '青河县', '654300', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3332', '654326', '吉木乃县', '654300', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('3333', '659001', '石河子市', '659000', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3334', '659002', '阿拉尔市', '659000', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('3335', '659003', '图木舒克市', '659000', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('3336', '659004', '五家渠市', '659000', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('3337', '659006', '铁门关市', '659000', '03', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('3338', '650100', '乌鲁木齐市', '100031', '02', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('3339', '650200', '克拉玛依市', '100031', '02', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('3340', '650400', '吐鲁番市', '100031', '02', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('3341', '650500', '哈密市', '100031', '02', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3342', '652300', '昌吉回族自治州', '100031', '02', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('3343', '652700', '博尔塔拉蒙古自治州', '100031', '02', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('3344', '652800', '巴音郭楞蒙古自治州', '100031', '02', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('3345', '652900', '阿克苏地区', '100031', '02', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('3346', '653000', '克孜勒苏柯尔克孜自治州', '100031', '02', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('3347', '653100', '喀什地区', '100031', '02', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('3348', '653200', '和田地区', '100031', '02', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3349', '654000', '伊犁哈萨克自治州', '100031', '02', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3350', '654200', '塔城地区', '100031', '02', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('3351', '654300', '阿勒泰地区', '100031', '02', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'A');
INSERT INTO `citys` VALUES ('3352', '659000', '自治区直辖县级行政区划', '100031', '02', '2017-05-26 17:47:11', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3353', '100001', '北京市', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'B');
INSERT INTO `citys` VALUES ('3354', '100002', '天津市', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'T');
INSERT INTO `citys` VALUES ('3355', '100003', '河北省', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'H');
INSERT INTO `citys` VALUES ('3356', '100004', '山西省', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'S');
INSERT INTO `citys` VALUES ('3357', '100005', '内蒙古自治区', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'N');
INSERT INTO `citys` VALUES ('3358', '100006', '辽宁省', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'L');
INSERT INTO `citys` VALUES ('3359', '100007', '吉林省', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'J');
INSERT INTO `citys` VALUES ('3360', '100008', '黑龙江省', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'H');
INSERT INTO `citys` VALUES ('3361', '100009', '上海市', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'S');
INSERT INTO `citys` VALUES ('3362', '100010', '江苏省', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'J');
INSERT INTO `citys` VALUES ('3363', '100011', '浙江省', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'Z');
INSERT INTO `citys` VALUES ('3364', '100012', '安徽省', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'A');
INSERT INTO `citys` VALUES ('3365', '100013', '福建省', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'F');
INSERT INTO `citys` VALUES ('3366', '100014', '江西省', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'J');
INSERT INTO `citys` VALUES ('3367', '100015', '山东省', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'S');
INSERT INTO `citys` VALUES ('3368', '100016', '河南省', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'H');
INSERT INTO `citys` VALUES ('3369', '100017', '湖北省', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'H');
INSERT INTO `citys` VALUES ('3370', '100018', '湖南省', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'H');
INSERT INTO `citys` VALUES ('3371', '100019', '广东省', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'G');
INSERT INTO `citys` VALUES ('3372', '100020', '广西壮族自治区', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'G');
INSERT INTO `citys` VALUES ('3373', '100021', '海南省', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'H');
INSERT INTO `citys` VALUES ('3374', '100022', '重庆市', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'Z');
INSERT INTO `citys` VALUES ('3375', '100023', '四川省', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'S');
INSERT INTO `citys` VALUES ('3376', '100024', '贵州省', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'G');
INSERT INTO `citys` VALUES ('3377', '100025', '云南省', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'Y');
INSERT INTO `citys` VALUES ('3378', '100026', '西藏自治区', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'X');
INSERT INTO `citys` VALUES ('3379', '100027', '陕西省', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'S');
INSERT INTO `citys` VALUES ('3380', '100028', '甘肃省', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'G');
INSERT INTO `citys` VALUES ('3381', '100029', '青海省', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'Q');
INSERT INTO `citys` VALUES ('3382', '100030', '宁夏回族自治区', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'N');
INSERT INTO `citys` VALUES ('3383', '100031', '新疆维吾尔自治区', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'X');
INSERT INTO `citys` VALUES ('3384', '100032', '香港', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'X');
INSERT INTO `citys` VALUES ('3385', '100033', '澳门', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'A');
INSERT INTO `citys` VALUES ('3386', '100034', '台湾', '0000', '01', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'T');
INSERT INTO `citys` VALUES ('3387', '710100', '特别行政区', '100032', '02', '2017-05-26 17:47:11', '2017-07-10 17:07:47', 'T');
INSERT INTO `citys` VALUES ('3388', '710101', '中西区', '710100', '03', '2017-05-26 17:47:11', '2017-07-10 17:07:58', 'Z');
INSERT INTO `citys` VALUES ('3389', '710102', '湾仔区', '710100', '03', '2017-05-26 17:47:11', '2017-07-10 17:08:03', 'W');
INSERT INTO `citys` VALUES ('3390', '710103', '东区', '710100', '03', '2017-05-26 17:47:11', '2017-07-10 17:08:06', 'D');
INSERT INTO `citys` VALUES ('3391', '710104', '南区', '710100', '03', '2017-05-26 17:47:11', '2017-07-10 17:08:09', 'N');
INSERT INTO `citys` VALUES ('3392', '710105', '观塘区', '710100', '03', '2017-05-26 17:47:11', '2017-07-10 17:08:13', 'G');
INSERT INTO `citys` VALUES ('3393', '710106', '油尖旺区', '710100', '03', '2017-05-26 17:47:11', '2017-07-10 17:08:15', 'Y');
INSERT INTO `citys` VALUES ('3394', '710107', '九龙城区', '710100', '03', '2017-05-26 17:47:11', '2017-07-10 17:08:18', 'J');
INSERT INTO `citys` VALUES ('3395', '710108', '深水埗区', '710100', '03', '2017-05-26 17:47:11', '2017-07-10 17:08:21', 'S');
INSERT INTO `citys` VALUES ('3396', '710109', '黄大仙区', '710100', '03', '2017-05-26 17:47:11', '2017-07-10 17:08:23', 'H');
INSERT INTO `citys` VALUES ('3397', '710110', '葵青区', '710100', '03', '2017-05-26 17:47:11', '2017-07-10 17:08:25', 'K');
INSERT INTO `citys` VALUES ('3398', '710111', '元朗区', '710100', '03', '2017-05-26 17:47:11', '2017-07-10 17:08:27', 'Y');
INSERT INTO `citys` VALUES ('3399', '710112', '离岛区', '710100', '03', '2017-05-26 17:47:11', '2017-07-10 17:08:28', 'L');
INSERT INTO `citys` VALUES ('3400', '710113', '北区', '710100', '03', '2017-05-26 17:47:11', '2017-07-10 17:08:31', 'B');
INSERT INTO `citys` VALUES ('3401', '710114', '西贡区', '710100', '03', '2017-05-26 17:47:11', '2017-07-10 17:08:32', 'X');
INSERT INTO `citys` VALUES ('3402', '710115', '沙田区', '710100', '03', '2017-05-26 17:47:11', '2017-07-10 17:08:35', 'S');
INSERT INTO `citys` VALUES ('3403', '710116', '大埔区', '710100', '03', '2017-05-26 17:47:11', '2017-07-10 17:08:37', 'D');
INSERT INTO `citys` VALUES ('3404', '710117', '荃湾区', '710100', '03', '2017-05-26 17:47:11', '2017-07-10 17:08:39', 'Z');
INSERT INTO `citys` VALUES ('3405', '710118', '屯门区', '710100', '03', '2017-05-26 17:47:11', '2017-07-10 17:08:43', 'T');
INSERT INTO `citys` VALUES ('3406', '710119', '其他', '710100', '03', '2017-05-26 17:47:11', '2017-07-10 17:08:45', 'Q');
INSERT INTO `citys` VALUES ('3407', '810100', '特别行政区', '100033', '02', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'T');
INSERT INTO `citys` VALUES ('3408', '810101', '花地玛堂区', '810100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'H');
INSERT INTO `citys` VALUES ('3409', '810102', '圣安多尼堂区', '810100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'S');
INSERT INTO `citys` VALUES ('3410', '810103', '大堂区', '810100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'D');
INSERT INTO `citys` VALUES ('3411', '810104', '望德堂区', '810100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'W');
INSERT INTO `citys` VALUES ('3412', '810105', '风顺堂区', '810100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'F');
INSERT INTO `citys` VALUES ('3413', '810106', '嘉模堂区', '810100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'J');
INSERT INTO `citys` VALUES ('3414', '810107', '圣方济各堂区', '810100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'S');
INSERT INTO `citys` VALUES ('3415', '810108', '路氹城', '810100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'L');
INSERT INTO `citys` VALUES ('3416', '810109', '其他', '810100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'Q');
INSERT INTO `citys` VALUES ('3417', '910100', '台湾省', '100034', '02', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'T');
INSERT INTO `citys` VALUES ('3418', '910101', '台北市', '910100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'T');
INSERT INTO `citys` VALUES ('3419', '910102', '新北市', '910100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'X');
INSERT INTO `citys` VALUES ('3420', '910103', '台中市', '910100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'T');
INSERT INTO `citys` VALUES ('3421', '910104', '台南市', '910100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'T');
INSERT INTO `citys` VALUES ('3422', '910105', '高雄市', '910100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'G');
INSERT INTO `citys` VALUES ('3423', '910106', '桃园县', '910100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'T');
INSERT INTO `citys` VALUES ('3424', '910107', '新竹县', '910100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'X');
INSERT INTO `citys` VALUES ('3425', '910108', '苗栗县', '910100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'M');
INSERT INTO `citys` VALUES ('3426', '910109', '彰化县', '910100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'Z');
INSERT INTO `citys` VALUES ('3427', '910110', '南投县', '910100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'N');
INSERT INTO `citys` VALUES ('3428', '910111', '云林县', '910100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'Y');
INSERT INTO `citys` VALUES ('3429', '910112', '嘉义县', '910100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'J');
INSERT INTO `citys` VALUES ('3430', '910113', '屏东县', '910100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'P');
INSERT INTO `citys` VALUES ('3431', '910114', '宜兰县', '910100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'Y');
INSERT INTO `citys` VALUES ('3432', '910115', '花莲县', '910100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'H');
INSERT INTO `citys` VALUES ('3433', '910116', '台东县', '910100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'T');
INSERT INTO `citys` VALUES ('3434', '910117', '金门县', '910100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'J');
INSERT INTO `citys` VALUES ('3435', '910118', '连江县', '910100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'L');
INSERT INTO `citys` VALUES ('3436', '910119', '基隆市 ', '910100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'J');
INSERT INTO `citys` VALUES ('3437', '910120', '新竹市', '910100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'X');
INSERT INTO `citys` VALUES ('3438', '910121', '嘉义市', '910100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'J');
INSERT INTO `citys` VALUES ('3439', '910122', '其他', '910100', '03', '2017-05-26 17:47:11', '2017-05-26 17:47:11', 'Q');
INSERT INTO `citys` VALUES ('3440', '920101', '广州市', '100019', '02', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('3441', '920102', '深圳市', '100019', '02', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3442', '920105', '珠海市', '100019', '02', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3443', '920106', '汕头市', '100019', '02', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3444', '920107', '韶关市', '100019', '02', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3445', '920108', '佛山市', '100019', '02', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('3446', '920109', '江门市', '100019', '02', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('3447', '920111', '湛江市', '100019', '02', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3448', '920112', '茂名市', '100019', '02', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('3449', '920113', '肇庆市', '100019', '02', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3450', '920114', '惠州市', '100019', '02', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3451', '920115', '梅州市', '100019', '02', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('3452', '920116', '汕尾市', '100019', '02', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3453', '920117', '河源市', '100019', '02', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3454', '920118', '阳江市', '100019', '02', '2017-05-26 17:44:09', '2017-08-11 15:34:09', 'Y');
INSERT INTO `citys` VALUES ('3455', '920119', '清远市', '100019', '02', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3456', '920120', '东莞市', '100019', '02', '2017-05-26 17:44:09', '2017-06-06 16:53:51', 'D');
INSERT INTO `citys` VALUES ('3457', '920121', '中山市', '100019', '02', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3458', '920122', '潮州市', '100019', '02', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('3459', '920123', '揭阳市', '100019', '02', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('3460', '920124', '云浮市', '100019', '02', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3461', '921001', '越秀区', '920101', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3462', '921001', '广州市', '920101', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('3463', '921002', '荔湾区', '920101', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3464', '921005', '海珠区', '920101', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3465', '921006', '天河区', '920101', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('3466', '921007', '白云区', '920101', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('3467', '921008', '黄埔区', '920101', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3468', '921009', '番禺区', '920101', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('3469', '921011', '花都区', '000019', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3470', '921012', '南沙区', '920101', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('3471', '921013', '萝岗区', '920101', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3472', '921014', '增城市', '920101', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3473', '921015', '从化市', '920101', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('3474', '921016', '其他', '920101', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3475', '922017', '福田区', '920102', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('3476', '922018', '罗湖区', '920102', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3477', '922019', '南山区', '920102', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('3478', '922020', '宝安区', '920102', '03', '2017-05-26 17:44:09', '2017-06-06 16:53:51', 'B');
INSERT INTO `citys` VALUES ('3479', '922021', '龙岗区', '920102', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3480', '922022', '盐田区', '920102', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3481', '922023', '其他', '920102', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3482', '923017', '香洲区', '920105', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('3483', '923018', '斗门区', '920105', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('3484', '923019', '金湾区', '920105', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('3485', '923020', '其他', '920105', '03', '2017-05-26 17:44:09', '2017-06-06 16:53:51', 'Q');
INSERT INTO `citys` VALUES ('3486', '924021', '金平区', '920106', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('3487', '924022', '濠江区', '920106', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3488', '924023', '龙湖区', '920106', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3489', '924024', '潮阳区', '920106', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('3490', '924025', '潮南区', '920106', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('3491', '924026', '澄海区', '920106', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('3492', '924027', '南澳县', '920106', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('3493', '924028', '其他', '920106', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3494', '924030', '浈江区', '920107', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3495', '924031', '武江区', '920107', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('3496', '924032', '曲江区', '920107', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3497', '924033', '乐昌市', '920107', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3498', '924034', '南雄市', '920107', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('3499', '924035', '始兴县', '920107', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3500', '924036', '仁化县', '920107', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('3501', '924037', '翁源县', '920107', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('3502', '924038', '新丰县', '920107', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('3503', '924039', '乳源瑶族自治县', '920107', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('3504', '924040', '其他', '920107', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3505', '924041', '禅城区', '920108', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3506', '924042', '南海区', '920108', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('3507', '924043', '顺德区', '920108', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3508', '924044', '三水区', '920108', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3509', '924045', '高明区', '920108', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('3510', '924046', '其他', '920108', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3511', '924047', '蓬江区', '920109', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('3512', '924048', '江海区', '920109', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('3513', '924049', '新会区', '920109', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('3514', '924050', '恩平市', '920109', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'E');
INSERT INTO `citys` VALUES ('3515', '924051', '台山市', '920109', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'T');
INSERT INTO `citys` VALUES ('3516', '924052', '开平市', '920109', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'K');
INSERT INTO `citys` VALUES ('3517', '924053', '鹤山市', '920109', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3518', '924054', '其他', '920109', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3519', '924055', '赤坎区', '920111', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('3520', '924056', '霞山区', '920111', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('3521', '924057', '坡头区', '920111', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('3522', '924058', '麻章区', '920111', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('3523', '924059', '吴川市', '920111', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('3524', '924060', '廉江市', '920111', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3525', '924061', '雷州市', '920111', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3526', '924062', '遂溪县', '920111', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3527', '924063', '徐闻县', '920111', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('3528', '924064', '其他', '920111', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3529', '925017', '茂南区', '920112', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('3530', '925018', '茂港区', '920112', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('3531', '925019', '化州市', '920112', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3532', '925020', '信宜市', '920112', '03', '2017-05-26 17:44:09', '2017-06-06 16:53:51', 'X');
INSERT INTO `citys` VALUES ('3533', '925021', '高州市', '920112', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('3534', '925022', '电白县', '920112', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('3535', '925023', '其他', '920112', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3536', '926017', '端州区', '920113', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('3537', '926018', '鼎湖区', '920113', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('3538', '926019', '高要市', '920113', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('3539', '926020', '四会市', '920113', '03', '2017-05-26 17:44:09', '2017-06-06 16:53:51', 'S');
INSERT INTO `citys` VALUES ('3540', '926021', '广宁县', '920113', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'G');
INSERT INTO `citys` VALUES ('3541', '926022', '怀集县', '920113', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3542', '926023', '封开县', '920113', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('3543', '926024', '德庆县', '920113', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('3544', '926025', '其他', '920113', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3545', '926026', '惠城区', '920114', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3546', '926027', '惠阳区', '920114', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3547', '926028', '博罗县', '920114', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'B');
INSERT INTO `citys` VALUES ('3548', '926030', '惠东县', '920114', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3549', '926031', '龙门县', '920114', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3550', '926032', '其他', '920114', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3551', '926033', '梅江区', '920115', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('3552', '926034', '兴宁市', '920115', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('3553', '926035', '梅县', '920115', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'M');
INSERT INTO `citys` VALUES ('3554', '926036', '大埔县', '920115', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('3555', '926037', '丰顺县', '920115', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('3556', '926038', '五华县', '920115', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('3557', '926039', '平远县', '920115', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('3558', '926040', '蕉岭县', '920115', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('3559', '926041', '城区', '920116', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('3560', '926042', '陆丰市', '920116', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3561', '926043', '海丰县', '920116', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3562', '926044', '陆河县', '920116', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3563', '926045', '源城区', '920117', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3564', '926046', '紫金县', '920117', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3565', '926047', '龙川县', '920117', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3566', '926048', '连平县', '920117', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3567', '926049', '和平县', '920117', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3568', '926050', '东源县', '920117', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('3569', '926051', '江城区', '920118', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('3570', '926052', '阳春市', '920118', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3571', '926053', '阳西县', '920118', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3572', '926054', '阳东县', '920118', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3573', '926055', '清城区', '920119', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3574', '926056', '英德市', '920119', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3575', '926057', '连州市', '920119', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3576', '926058', '佛冈县', '920119', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'F');
INSERT INTO `citys` VALUES ('3577', '926059', '阳山县', '920119', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3578', '926060', '清新县', '920119', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Q');
INSERT INTO `citys` VALUES ('3579', '926061', '连山壮族瑶族自治县', '920119', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3580', '926062', '连南瑶族自治县', '920119', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3581', '926063', '莞城街道', '920120', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3582', '926064', '南城街道', '920120', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'N');
INSERT INTO `citys` VALUES ('3583', '926065', '东城街道', '920120', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('3584', '926066', '万江街道', '920120', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('3629', '927017', '石岐区', '920121', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'S');
INSERT INTO `citys` VALUES ('3630', '927018', '东区', '920121', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'D');
INSERT INTO `citys` VALUES ('3631', '927019', '西区', '920121', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('3632', '927020', '南区', '920121', '03', '2017-05-26 17:44:09', '2017-06-06 16:53:51', 'N');
INSERT INTO `citys` VALUES ('3633', '927021', '五桂山区', '920121', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'W');
INSERT INTO `citys` VALUES ('3634', '927022', '湘桥区', '920122', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('3635', '927023', '潮安县', '920122', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'C');
INSERT INTO `citys` VALUES ('3645', '927026', '饶平县', '920122', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'R');
INSERT INTO `citys` VALUES ('3646', '927027', '榕城区', '920123', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Z');
INSERT INTO `citys` VALUES ('3647', '927028', '普宁市', '920123', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'P');
INSERT INTO `citys` VALUES ('3648', '927030', '揭东县', '920123', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('3649', '927031', '揭西县', '920123', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'J');
INSERT INTO `citys` VALUES ('3650', '927032', '惠来县', '920123', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'H');
INSERT INTO `citys` VALUES ('3651', '927033', '云城区', '920124', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3652', '927034', '罗定市', '920124', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'L');
INSERT INTO `citys` VALUES ('3653', '927035', '云安县', '920124', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3654', '927036', '新兴县', '920124', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'X');
INSERT INTO `citys` VALUES ('3655', '927037', '郁南县', '920124', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Y');
INSERT INTO `citys` VALUES ('3656', '927038', '其他', '920124', '03', '2017-05-26 17:44:09', '2017-05-26 18:07:03', 'Q');

-- ----------------------------
-- Table structure for company_basic
-- ----------------------------
DROP TABLE IF EXISTS `company_basic`;
CREATE TABLE `company_basic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `idnum` varchar(255) DEFAULT '' COMMENT '编号',
  `comdes` varchar(255) DEFAULT '' COMMENT '公司简称',
  `comname` varchar(255) DEFAULT '' COMMENT '公司名称',
  `regadd` varchar(255) DEFAULT '' COMMENT '注册地址',
  `country` int(11) DEFAULT '0' COMMENT '国家类型',
  `area` varchar(255) DEFAULT '' COMMENT '地区',
  `postalcode` varchar(255) DEFAULT '' COMMENT '邮政编码',
  `phone` varchar(255) DEFAULT '' COMMENT '电话',
  `fax` varchar(255) DEFAULT '' COMMENT '传真',
  `address` varchar(255) DEFAULT '' COMMENT '送货地址',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `addpeople` varchar(255) DEFAULT '' COMMENT '建档人',
  `adddate` varchar(255) DEFAULT NULL COMMENT '建档日期',
  `updatepeople` varchar(255) DEFAULT '' COMMENT '最后修改人',
  `updatedate` varchar(255) DEFAULT NULL COMMENT '最后修改时间',
  `isstop` int(11) DEFAULT '0' COMMENT '是否暂停使用',
  `stopdes` varchar(255) DEFAULT '' COMMENT '暂停描述',
  `openbank` varchar(255) DEFAULT '' COMMENT '开户银行',
  `bankaccount` varchar(255) DEFAULT '' COMMENT '银行账号',
  `taxaccount` varchar(255) DEFAULT '' COMMENT '税务登记号',
  `invoicehead` varchar(255) DEFAULT '' COMMENT '发票抬头',
  `invoiceadd` varchar(255) DEFAULT '' COMMENT '发票地址',
  `comeandgo` int(11) DEFAULT '0' COMMENT '禁止来往',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of company_basic
-- ----------------------------
INSERT INTO `company_basic` VALUES ('4', 'A003', '百度', '百度科技有限公司', '深圳', '1', '中国', '00010', '2135841', '', '深圳', '', 'Admin', '2018-08-31', 'Admin', '2018-11-27', '0', '', '中国银行', '', '', '', '', '0');
INSERT INTO `company_basic` VALUES ('5', 'A004', 'Tencent', 'Tencent腾讯', '深圳', '1', '中国', '518057', '0755-86013388', '0755-86013399', '深圳市南山区高新科技园中区一路腾讯大厦', 'Email：Thomastang@tencent.com', 'Admin', '2018-08-31', 'Admin', '2018-10-11', '0', '', '中国银行', '', '', '', '', '0');
INSERT INTO `company_basic` VALUES ('6', 'A005', 'alibaba', '阿里巴巴集团控股有限公司', '中国杭州市余杭区文一西路969号 ', '1', '上海', '311121', '(+86) 571-8502-2088', '(+86) 571-8526-9066', '中国杭州市余杭区', '阿里巴巴集团的使命是让天下没有难做的生意。', 'Admin', '2018-08-31', '瞿张景', '2019-04-18', '1', '2019-04-03', '中国银行', '4567913136456', '3564255255', '', '', '0');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_code` varchar(255) NOT NULL COMMENT '编号',
  `short_name` varchar(255) DEFAULT NULL COMMENT '简称',
  `name` varchar(255) DEFAULT NULL COMMENT '名字',
  `register_address` varchar(255) DEFAULT NULL COMMENT '注册地址',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  `disabling` int(11) DEFAULT NULL,
  `general_customer` tinyint(1) DEFAULT '0' COMMENT '一般客户',
  `useable_money` decimal(10,2) DEFAULT '0.00' COMMENT '客户可用余额',
  `delete_is` tinyint(1) DEFAULT NULL COMMENT '客户删除状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('34', '20190428215126', '上海君之道信息有限公司', '上海君之道信息有限公司', '上海市', '2019-04-29 10:48:34', null, null, '-9800.00', '0');

-- ----------------------------
-- Table structure for customer_basic
-- ----------------------------
DROP TABLE IF EXISTS `customer_basic`;
CREATE TABLE `customer_basic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '客户基本资料-基本信息',
  `customer_id` bigint(20) NOT NULL COMMENT '客户id',
  `country_id` bigint(20) DEFAULT NULL COMMENT '国家id',
  `phone` varchar(50) DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL COMMENT '结算方式',
  `initial_quota` decimal(10,2) DEFAULT NULL COMMENT '期初额度调整金额',
  `initial_quota_money_type` varchar(50) DEFAULT NULL COMMENT '期初额度调整金额类型（人民币、美元）',
  `rush_money_date` datetime DEFAULT NULL COMMENT '催款日期',
  `shipping_address` varchar(50) DEFAULT NULL COMMENT '送货地址',
  `shipping_address_remark` varchar(500) DEFAULT NULL COMMENT '送货地址备注',
  `primary_contact` varchar(20) DEFAULT NULL COMMENT '主要联系人',
  `archivist` varchar(20) DEFAULT NULL COMMENT '建档人',
  `last_modifier` varchar(20) DEFAULT NULL COMMENT '最后修改人',
  `establish_date` datetime DEFAULT NULL COMMENT '建立日期',
  `zip_code` varchar(20) DEFAULT NULL COMMENT '邮政编码',
  `fax` varchar(50) DEFAULT NULL COMMENT '传真',
  `credit_line` decimal(10,2) DEFAULT NULL COMMENT '信用额度',
  `credit_line_money_type` int(20) DEFAULT NULL COMMENT '信用额度类型（人民币、美元）',
  `credit_line_remark` varchar(255) DEFAULT NULL COMMENT '授信额度备注',
  `minimum_discount` double DEFAULT NULL COMMENT '最低折扣',
  `contact_number` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `document_date` datetime DEFAULT NULL COMMENT '建档日期',
  `last_modified_date` datetime DEFAULT NULL COMMENT '最后修改日期',
  `stop_use` tinyint(1) DEFAULT '0' COMMENT '暂停使用 （1暂停使用）',
  `stop_use_str` varchar(255) DEFAULT NULL COMMENT '暂停使用描述',
  `addtime` datetime DEFAULT NULL COMMENT '系统添加时间',
  PRIMARY KEY (`id`,`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_basic
-- ----------------------------
INSERT INTO `customer_basic` VALUES ('24', '34', '1', '17521286225', '先款后货', '93040.00', 'RMB', '2019-04-28 00:00:00', '上海市普陀区中江路638号天地软件园27号207', '测试', '陈先生', 'Administrator', 'Administrator', '2019-04-28 00:00:00', '42100', '09191212', '100000.00', '1', '测试人员', '90', '17521286225', '2019-04-28 00:00:00', '2019-04-29 00:00:00', '0', null, '2019-04-28 21:53:48');

-- ----------------------------
-- Table structure for customer_business_leader
-- ----------------------------
DROP TABLE IF EXISTS `customer_business_leader`;
CREATE TABLE `customer_business_leader` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '客户基本资料-业务负责人',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '客户id',
  `employee_code` varchar(50) DEFAULT NULL COMMENT '员工编号',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `primary_people` varchar(50) DEFAULT NULL COMMENT '主要负责人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  `assistant` varchar(100) DEFAULT NULL COMMENT '业务助理',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_business_leader
-- ----------------------------
INSERT INTO `customer_business_leader` VALUES ('19', '34', 'A003', '采购测试人员', '采购测试人员', null, '2019-04-28 21:54:34', null);

-- ----------------------------
-- Table structure for customer_contacts
-- ----------------------------
DROP TABLE IF EXISTS `customer_contacts`;
CREATE TABLE `customer_contacts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '客户基本资料-联系人',
  `customer_id` bigint(20) NOT NULL COMMENT '客户id',
  `primary_contacts` tinyint(1) DEFAULT '0' COMMENT '主要联系人',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `department` varchar(50) DEFAULT NULL COMMENT '部门',
  `position` varchar(50) DEFAULT NULL COMMENT '职务',
  `telephone` varchar(20) DEFAULT NULL COMMENT '电话',
  `fax` varchar(50) DEFAULT NULL COMMENT '传真',
  `mobile_phone` varchar(20) DEFAULT NULL COMMENT '移动电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_contacts
-- ----------------------------
INSERT INTO `customer_contacts` VALUES ('16', '34', null, '陈先生', '销售部', '主管', '17521282266', '652412', '17521282266', null, null, '2019-04-28 21:54:34');

-- ----------------------------
-- Table structure for customer_data_maintain
-- ----------------------------
DROP TABLE IF EXISTS `customer_data_maintain`;
CREATE TABLE `customer_data_maintain` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '客户资料维护',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '客户id',
  `customer_no` varchar(50) DEFAULT NULL COMMENT '客户编号',
  `customer_no_str` varchar(100) DEFAULT NULL COMMENT '客户编号后面的文本框',
  `create_no` varchar(50) DEFAULT NULL COMMENT '建档编号',
  `customer_name` varchar(20) DEFAULT NULL COMMENT '客户名称',
  `create_date` datetime DEFAULT NULL COMMENT '建档日期',
  `addtime` datetime DEFAULT NULL COMMENT '系统添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_data_maintain
-- ----------------------------
INSERT INTO `customer_data_maintain` VALUES ('4', null, '20190428215126', '上海市', '1556470744795', '上海君之道信息有限公司', '2019-04-29 00:00:00', '2019-04-29 00:59:27');

-- ----------------------------
-- Table structure for customer_data_maintain_basic
-- ----------------------------
DROP TABLE IF EXISTS `customer_data_maintain_basic`;
CREATE TABLE `customer_data_maintain_basic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '客户基本资料维护-基本信息',
  `maintain_id` bigint(20) DEFAULT NULL COMMENT '资料维护id',
  `leader_people` varchar(20) DEFAULT NULL COMMENT '负责人',
  `contacts` varchar(20) DEFAULT NULL COMMENT '联系人',
  `fax` varchar(20) DEFAULT NULL COMMENT '传真',
  `delivery_address` varchar(50) DEFAULT NULL COMMENT '送货地址',
  `invoice_address` varchar(50) DEFAULT NULL COMMENT '发票地址',
  `purchase` varchar(20) DEFAULT NULL COMMENT '采购人',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `create_people` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_people_str` varchar(50) DEFAULT NULL COMMENT '创建人后面的文本框',
  `update_people` varchar(20) DEFAULT NULL COMMENT '最后修改人',
  `update_people_str` varchar(50) DEFAULT NULL COMMENT '最后修改人后面的文本框',
  `addtime` datetime DEFAULT NULL COMMENT '系统添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_data_maintain_basic
-- ----------------------------
INSERT INTO `customer_data_maintain_basic` VALUES ('4', '4', '马先生', null, null, null, null, null, null, null, null, 'Administrator', '2019-04-29', '2019-04-29 00:59:27');

-- ----------------------------
-- Table structure for customer_data_maintain_car
-- ----------------------------
DROP TABLE IF EXISTS `customer_data_maintain_car`;
CREATE TABLE `customer_data_maintain_car` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '客户资料维护-车床',
  `maintain_id` bigint(20) DEFAULT NULL COMMENT '客户资料维护id',
  `lathe_type` varchar(255) DEFAULT NULL COMMENT '车床类型',
  `brand` varchar(255) DEFAULT NULL COMMENT '厂牌',
  `models` varchar(255) DEFAULT NULL COMMENT '型号',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_data_maintain_car
-- ----------------------------

-- ----------------------------
-- Table structure for customer_data_maintain_info
-- ----------------------------
DROP TABLE IF EXISTS `customer_data_maintain_info`;
CREATE TABLE `customer_data_maintain_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `maintain_id` bigint(20) DEFAULT NULL COMMENT '客户资料id',
  `start_year` varchar(50) DEFAULT NULL COMMENT '创业年度',
  `annual_turnover` varchar(20) DEFAULT NULL COMMENT '年营业额',
  `fax` varchar(20) DEFAULT NULL COMMENT '传真',
  `employee_num` int(11) DEFAULT NULL COMMENT '员工数量',
  `budget` varchar(20) DEFAULT NULL COMMENT '今年预算',
  `industry` varchar(255) DEFAULT NULL COMMENT '工业形态',
  `addtime` datetime DEFAULT NULL COMMENT '系统添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_data_maintain_info
-- ----------------------------
INSERT INTO `customer_data_maintain_info` VALUES ('5', '4', null, null, null, null, null, null, '2019-04-29 00:59:27');
INSERT INTO `customer_data_maintain_info` VALUES ('6', '4', null, null, null, null, null, null, '2019-04-29 00:59:44');

-- ----------------------------
-- Table structure for customer_data_maintain_production
-- ----------------------------
DROP TABLE IF EXISTS `customer_data_maintain_production`;
CREATE TABLE `customer_data_maintain_production` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '客户资料维护-生产资料',
  `maintain_id` bigint(20) DEFAULT NULL COMMENT '客户资料维护id',
  `production_type` varchar(255) DEFAULT NULL COMMENT '主要生产类别',
  `factory` varchar(255) DEFAULT NULL COMMENT '微型工厂',
  `vendor` varchar(255) DEFAULT NULL COMMENT '上游厂商',
  `addtime` datetime DEFAULT NULL COMMENT '系统添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_data_maintain_production
-- ----------------------------

-- ----------------------------
-- Table structure for customer_data_maintain_question
-- ----------------------------
DROP TABLE IF EXISTS `customer_data_maintain_question`;
CREATE TABLE `customer_data_maintain_question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '客户资料维护-现有问题及意见',
  `maintain_id` bigint(20) DEFAULT NULL COMMENT '客户资料维护id',
  `type` int(11) DEFAULT NULL COMMENT '问题类型（1现有问题、2其他问题）',
  `content` varchar(255) DEFAULT NULL COMMENT '正文',
  `addtime` datetime DEFAULT NULL COMMENT '系统添加时时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_data_maintain_question
-- ----------------------------

-- ----------------------------
-- Table structure for customer_data_maintenance
-- ----------------------------
DROP TABLE IF EXISTS `customer_data_maintenance`;
CREATE TABLE `customer_data_maintenance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '客户基本资料-资料维护',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '客户id',
  `document_no` varchar(255) DEFAULT NULL COMMENT '建档编号',
  `create_date` datetime DEFAULT NULL COMMENT '建档时间',
  `leader_people` varchar(255) DEFAULT NULL COMMENT '负责人',
  `purchase_people` varchar(255) DEFAULT NULL COMMENT '采购人',
  `contact` varchar(255) DEFAULT NULL COMMENT '联络人',
  `telephone` varchar(255) DEFAULT NULL COMMENT '电话',
  `fax` varchar(255) DEFAULT NULL COMMENT '传真',
  `startup_year` datetime DEFAULT NULL COMMENT '创业年度',
  `employee_number` int(11) DEFAULT NULL COMMENT '员工人数',
  `last_year_business` varchar(255) DEFAULT NULL COMMENT '去年营业',
  `year_plan` varchar(255) DEFAULT NULL COMMENT '今年计划',
  `industry` varchar(255) DEFAULT NULL COMMENT '工业形态',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_data_maintenance
-- ----------------------------

-- ----------------------------
-- Table structure for customer_demand_goods
-- ----------------------------
DROP TABLE IF EXISTS `customer_demand_goods`;
CREATE TABLE `customer_demand_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '客户需求商品',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '客户id',
  `customer_no` varchar(255) DEFAULT NULL COMMENT '客户编号',
  `customer_no_str` varchar(255) DEFAULT NULL COMMENT '客户编号备注',
  `customer_name` varchar(20) DEFAULT NULL COMMENT '客户姓名',
  `create_date` datetime DEFAULT NULL COMMENT '建档日期',
  `create_no` varchar(20) DEFAULT NULL COMMENT '建档编号',
  `customer_type` varchar(50) DEFAULT NULL COMMENT '客户类别',
  `zip` varchar(20) DEFAULT NULL COMMENT '邮政编码',
  `contacts` varchar(255) DEFAULT NULL COMMENT '联系人（客户基本资料中的联系人id）',
  `phone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `fax` varchar(50) DEFAULT NULL COMMENT '传真',
  `create_people` varchar(50) DEFAULT NULL COMMENT '创建人',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_demand_goods
-- ----------------------------
INSERT INTO `customer_demand_goods` VALUES ('16', '34', '20190428215126', null, '上海君之道信息有限公司', '2019-04-29 00:00:00', '15564707309911667', '目标客户', null, '陈先生', '17521282266', '652412', 'Administrator', '上海市普陀区中江路638号天地软件园27号207', '测试');

-- ----------------------------
-- Table structure for customer_demand_goods_info
-- ----------------------------
DROP TABLE IF EXISTS `customer_demand_goods_info`;
CREATE TABLE `customer_demand_goods_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '客户需求产品-商品需求详情',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  `product_no` varchar(50) DEFAULT NULL COMMENT '厂牌编号',
  `product_name` varchar(50) DEFAULT NULL COMMENT '产品名称',
  `brand` varchar(50) DEFAULT NULL COMMENT '厂牌',
  `quantity_demand` int(11) DEFAULT NULL COMMENT '需求量',
  `unit` varchar(50) DEFAULT NULL COMMENT '单位',
  `price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `product_categories` varchar(50) DEFAULT NULL COMMENT '产品大类',
  `product_nature` varchar(50) DEFAULT NULL COMMENT '产品性质',
  `manufacture_method` varchar(50) DEFAULT NULL COMMENT '制作方式',
  `process_method` varchar(50) DEFAULT NULL COMMENT '加工方法',
  `process_material` varchar(255) DEFAULT NULL COMMENT '加工材料',
  `material` varchar(255) DEFAULT NULL COMMENT '材质',
  `continuity` varchar(50) DEFAULT NULL COMMENT '连续性',
  `cut_oil` varchar(50) DEFAULT NULL COMMENT '切削油',
  `use_amount` int(11) DEFAULT NULL COMMENT '使用量',
  `cut_speed` varchar(50) DEFAULT NULL COMMENT '切削速度',
  `customer_demand_goods_id` bigint(20) DEFAULT NULL COMMENT '客户需求商品',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_demand_goods_info
-- ----------------------------

-- ----------------------------
-- Table structure for customer_detail_info
-- ----------------------------
DROP TABLE IF EXISTS `customer_detail_info`;
CREATE TABLE `customer_detail_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '客户基本资料-详情',
  `customer_id` bigint(20) NOT NULL COMMENT '客户id',
  `customer_category` int(20) DEFAULT NULL COMMENT '客户类别',
  `industry` int(20) DEFAULT NULL COMMENT '行业',
  `customer_source` int(20) DEFAULT NULL COMMENT '客户来源',
  `bank` varchar(50) DEFAULT NULL COMMENT '开户银行',
  `bank_account` varchar(20) DEFAULT NULL COMMENT '银行账号',
  `tax_register` varchar(50) DEFAULT NULL COMMENT '税务登记号',
  `area` varchar(50) DEFAULT NULL COMMENT '所属区域',
  `area_info` varchar(255) DEFAULT NULL COMMENT '所属区域详细',
  `sent_back` tinyint(1) DEFAULT '0' COMMENT '销售单需回传（1需回传）',
  `region` varchar(50) DEFAULT NULL COMMENT '地区',
  `customer_level` int(50) DEFAULT NULL COMMENT '客户等级',
  `Company_affiliation` varchar(50) DEFAULT NULL COMMENT '所属公司',
  `account_remark` varchar(255) DEFAULT NULL COMMENT '账款备注',
  `customer_property` varchar(255) DEFAULT NULL COMMENT '客户属性-新加字段',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`,`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_detail_info
-- ----------------------------
INSERT INTO `customer_detail_info` VALUES ('25', '34', '0', '0', '0', null, null, null, '上海', '上海', '0', 'H 湖南', '0', '三禄', null, null, '2019-04-28 21:53:48');

-- ----------------------------
-- Table structure for customer_shipping_address
-- ----------------------------
DROP TABLE IF EXISTS `customer_shipping_address`;
CREATE TABLE `customer_shipping_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customerid` bigint(20) DEFAULT NULL COMMENT '客户id',
  `type` varchar(50) DEFAULT NULL COMMENT '类型',
  `title` varchar(255) DEFAULT NULL COMMENT '名称 （小区、公司名称）',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `contact` varchar(50) DEFAULT NULL COMMENT '联系人',
  `setting` tinyint(1) DEFAULT '0' COMMENT '设置默认',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_shipping_address
-- ----------------------------
INSERT INTO `customer_shipping_address` VALUES ('57', '34', '公司', null, '上海市普陀区中江路638号天地软件园27号207', null, null, '1', '2019-04-28 21:53:48');

-- ----------------------------
-- Table structure for customer_source
-- ----------------------------
DROP TABLE IF EXISTS `customer_source`;
CREATE TABLE `customer_source` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '客户来源',
  `title` varchar(50) DEFAULT NULL COMMENT '客户来源名称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_source
-- ----------------------------

-- ----------------------------
-- Table structure for data_setting
-- ----------------------------
DROP TABLE IF EXISTS `data_setting`;
CREATE TABLE `data_setting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `title` varchar(255) DEFAULT '' COMMENT '名称',
  `isdel` int(11) DEFAULT '0' COMMENT '是否删除',
  `remarks` varchar(255) DEFAULT '' COMMENT '描述',
  `previd` bigint(20) DEFAULT '0' COMMENT '仓库父级',
  `parentid` bigint(20) DEFAULT '0' COMMENT '父级id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=326 DEFAULT CHARSET=utf8 COMMENT='资料设定';

-- ----------------------------
-- Records of data_setting
-- ----------------------------
INSERT INTO `data_setting` VALUES ('1', '0', '发货状态', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('2', '0', '业务地区', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('3', '0', '货运方式', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('4', '0', '开户银行', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('5', '0', '基本单位', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('6', '0', '产品大类', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('7', '0', '货币单位', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('8', '0', '产品性质', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('9', '0', '产品来源', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('10', '0', '客户类别', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('11', '0', '行业', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('12', '0', '地区', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('13', '0', '客户来源', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('14', '0', '客户等级', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('15', '0', '部门', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('16', '0', '职务', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('17', '0', '出生地', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('18', '0', '民族', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('19', '0', '退货原因', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('20', '0', '收款方式', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('21', '0', '电话', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('22', '0', '传真', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('23', '0', '备注及说明', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('24', '0', '发货状态', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('25', '0', '销退备注', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('26', '0', '收款备注', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('27', '0', '所属公司', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('28', '0', '国家', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('29', '0', '出货仓库', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('30', '0', '运输方式', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('31', '0', '所属区域', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('32', '0', '楼层', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('33', '0', '仓库', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('34', '0', '地址类型', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('35', '0', '生产类别', '0', '', '0', '0');
INSERT INTO `data_setting` VALUES ('200', '1', '正常发货', '0', '', '0', '1');
INSERT INTO `data_setting` VALUES ('201', '2', '暂缓发货', '0', '', '0', '1');
INSERT INTO `data_setting` VALUES ('202', '3', '当天件', '0', '', '0', '1');
INSERT INTO `data_setting` VALUES ('203', '4', '限时当天件', '0', '', '0', '1');
INSERT INTO `data_setting` VALUES ('204', '5', '暂未出', '0', '', '0', '1');
INSERT INTO `data_setting` VALUES ('205', '6', '已作废', '0', '', '0', '1');
INSERT INTO `data_setting` VALUES ('206', '7', '客户自提', '0', '', '0', '1');
INSERT INTO `data_setting` VALUES ('207', '8', '留货', '0', '', '0', '1');
INSERT INTO `data_setting` VALUES ('208', '9', '寄客户', '0', '', '0', '1');
INSERT INTO `data_setting` VALUES ('209', '10', '业务自提', '0', '', '0', '1');
INSERT INTO `data_setting` VALUES ('210', '1', '斤', '0', '', '0', '5');
INSERT INTO `data_setting` VALUES ('211', '2', '公斤', '0', '', '0', '5');
INSERT INTO `data_setting` VALUES ('212', '3', '件', '0', '', '0', '5');
INSERT INTO `data_setting` VALUES ('213', '4', '片', '0', '', '0', '5');
INSERT INTO `data_setting` VALUES ('214', '5', '台', '0', '', '0', '5');
INSERT INTO `data_setting` VALUES ('215', '6', '箱', '0', '', '0', '5');
INSERT INTO `data_setting` VALUES ('216', '7', '只', '0', '', '0', '5');
INSERT INTO `data_setting` VALUES ('217', '8', '支', '0', '', '0', '5');
INSERT INTO `data_setting` VALUES ('218', '9', '组', '0', '', '0', '5');
INSERT INTO `data_setting` VALUES ('219', '10', '根', '0', '', '0', '5');
INSERT INTO `data_setting` VALUES ('220', '1', 'A-替换式', '0', '', '0', '6');
INSERT INTO `data_setting` VALUES ('221', '2', 'H-小径搪刀', '0', '', '0', '6');
INSERT INTO `data_setting` VALUES ('222', '3', 'B-精搪头刀杆', '0', '', '0', '6');
INSERT INTO `data_setting` VALUES ('223', '1', 'RMB', '0', '人民币', '0', '7');
INSERT INTO `data_setting` VALUES ('224', '2', 'USD', '0', '美金', '0', '7');
INSERT INTO `data_setting` VALUES ('225', '3', 'EUR', '0', '欧元', '0', '7');
INSERT INTO `data_setting` VALUES ('226', '4', 'CAD', '0', '加币', '0', '7');
INSERT INTO `data_setting` VALUES ('227', '5', 'JPY', '0', '日币', '0', '7');
INSERT INTO `data_setting` VALUES ('228', '1', '中国银行', '0', '大银行（首选）', '0', '4');
INSERT INTO `data_setting` VALUES ('229', '2', '工商银行', '0', '', '0', '4');
INSERT INTO `data_setting` VALUES ('230', '3', '招商银行', '0', '范围广', '0', '4');
INSERT INTO `data_setting` VALUES ('231', '4', '上海银行', '0', '可靠性高', '0', '4');
INSERT INTO `data_setting` VALUES ('232', '1', '一级客户', '0', '', '0', '14');
INSERT INTO `data_setting` VALUES ('233', '2', '二级客户', '0', '', '0', '14');
INSERT INTO `data_setting` VALUES ('234', '3', '普通客户', '0', '', '0', '14');
INSERT INTO `data_setting` VALUES ('235', '4', 'VIP客户', '0', '', '0', '14');
INSERT INTO `data_setting` VALUES ('236', '1', '中国', '0', 'Chain', '0', '28');
INSERT INTO `data_setting` VALUES ('237', '2', '日本', '0', '', '0', '28');
INSERT INTO `data_setting` VALUES ('238', '3', '美国', '0', '', '0', '28');
INSERT INTO `data_setting` VALUES ('239', '4', '印度尼西亚', '0', '', '0', '28');
INSERT INTO `data_setting` VALUES ('240', '5', '新加坡', '0', '', '0', '28');
INSERT INTO `data_setting` VALUES ('241', '6', '瑞士', '0', '', '0', '28');
INSERT INTO `data_setting` VALUES ('242', '7', '荷兰', '0', '', '0', '28');
INSERT INTO `data_setting` VALUES ('243', '1', '供应商', '0', '', '0', '9');
INSERT INTO `data_setting` VALUES ('244', '2', '自制', '0', '', '0', '9');
INSERT INTO `data_setting` VALUES ('245', '1', '加工品', '0', '', '0', '8');
INSERT INTO `data_setting` VALUES ('246', '2', '性质2', '0', '', '0', '8');
INSERT INTO `data_setting` VALUES ('247', '3', '性质3', '0', '', '0', '8');
INSERT INTO `data_setting` VALUES ('248', '1', '中国', '0', '', '0', '17');
INSERT INTO `data_setting` VALUES ('249', '1', '汉族', '0', '', '0', '18');
INSERT INTO `data_setting` VALUES ('250', '1', '人事部', '0', '', '0', '15');
INSERT INTO `data_setting` VALUES ('251', '2', '采购部', '0', '', '0', '15');
INSERT INTO `data_setting` VALUES ('252', '3', '销售部', '0', '', '0', '15');
INSERT INTO `data_setting` VALUES ('253', '4', '财务部', '0', '', '0', '15');
INSERT INTO `data_setting` VALUES ('254', '1', '经理', '0', '', '0', '16');
INSERT INTO `data_setting` VALUES ('255', '2', '副经理', '0', '', '0', '16');
INSERT INTO `data_setting` VALUES ('256', '3', '主管', '0', '', '0', '16');
INSERT INTO `data_setting` VALUES ('257', '4', '组长', '0', '', '0', '16');
INSERT INTO `data_setting` VALUES ('258', '5', '员工', '0', '', '0', '16');
INSERT INTO `data_setting` VALUES ('259', '1', '转账', '0', '', '0', '20');
INSERT INTO `data_setting` VALUES ('260', '2', '现金', '0', '', '0', '20');
INSERT INTO `data_setting` VALUES ('261', '2', '陆家嘴一号仓', '0', '', '0', '29');
INSERT INTO `data_setting` VALUES ('262', '1', '武汉仓', '0', '', '0', '29');
INSERT INTO `data_setting` VALUES ('263', '3', '昆山仓', '0', '', '0', '29');
INSERT INTO `data_setting` VALUES ('264', '1', '顺丰标准件', '0', '', '0', '30');
INSERT INTO `data_setting` VALUES ('265', '2', '顺丰特惠件', '0', '', '0', '30');
INSERT INTO `data_setting` VALUES ('266', '3', '顺丰次日晨', '0', '', '0', '30');
INSERT INTO `data_setting` VALUES ('267', '1', '质量问题', '0', '', '0', '19');
INSERT INTO `data_setting` VALUES ('268', '2', '拍错', '0', '', '0', '19');
INSERT INTO `data_setting` VALUES ('269', '3', '个人原因', '0', '', '0', '19');
INSERT INTO `data_setting` VALUES ('270', '1', '目标客户', '0', '', '0', '10');
INSERT INTO `data_setting` VALUES ('271', '2', '潜在客户', '0', '', '0', '10');
INSERT INTO `data_setting` VALUES ('272', '3', '普通客户', '0', '', '0', '10');
INSERT INTO `data_setting` VALUES ('273', '1', '制造业', '0', '', '0', '11');
INSERT INTO `data_setting` VALUES ('274', '2', '纺织业', '0', '', '0', '11');
INSERT INTO `data_setting` VALUES ('275', '3', '化工业', '0', '', '0', '11');
INSERT INTO `data_setting` VALUES ('276', '1', '朋友介绍', '0', '', '0', '13');
INSERT INTO `data_setting` VALUES ('277', '2', '广告', '0', '', '0', '13');
INSERT INTO `data_setting` VALUES ('278', '3', '运营积累', '0', '', '0', '13');
INSERT INTO `data_setting` VALUES ('279', '2', '三禄', '0', '三禄', '0', '27');
INSERT INTO `data_setting` VALUES ('281', '3', '三上', '0', '三上', '0', '27');
INSERT INTO `data_setting` VALUES ('283', '1', '上海', '0', '', '0', '31');
INSERT INTO `data_setting` VALUES ('284', '2', '华北', '0', '', '0', '31');
INSERT INTO `data_setting` VALUES ('285', '3', '华东', '0', '', '0', '31');
INSERT INTO `data_setting` VALUES ('298', '1', '电话1', '0', '', '0', '21');
INSERT INTO `data_setting` VALUES ('299', '2', '电话2', '0', '', '0', '21');
INSERT INTO `data_setting` VALUES ('300', '3', '电话3', '0', '', '0', '21');
INSERT INTO `data_setting` VALUES ('301', '1', '传真1', '0', '', '0', '22');
INSERT INTO `data_setting` VALUES ('302', '2', '传真2', '0', '', '0', '22');
INSERT INTO `data_setting` VALUES ('303', '3', '传真3', '0', '', '0', '22');
INSERT INTO `data_setting` VALUES ('304', '1', '一楼', '0', '', '0', '32');
INSERT INTO `data_setting` VALUES ('305', '2', '四楼', '0', '', '0', '32');
INSERT INTO `data_setting` VALUES ('306', '3', '五楼', '0', '', '0', '32');
INSERT INTO `data_setting` VALUES ('307', '4', '六楼', '0', '', '0', '32');
INSERT INTO `data_setting` VALUES ('308', '5', '七楼', '0', '', '0', '32');
INSERT INTO `data_setting` VALUES ('309', '1', 'A区仓库', '0', '测试仓库', '0', '33');
INSERT INTO `data_setting` VALUES ('310', '2', 'B区仓库', '0', '测试仓库', '0', '33');
INSERT INTO `data_setting` VALUES ('311', '1', '已收', null, null, '0', '26');
INSERT INTO `data_setting` VALUES ('312', '1', '已退', null, null, '0', '25');
INSERT INTO `data_setting` VALUES ('313', '1', '已发货', null, null, '0', '24');
INSERT INTO `data_setting` VALUES ('316', '1', '公司', '0', '', '0', '34');
INSERT INTO `data_setting` VALUES ('317', '2', '住宅', '0', '', '0', '34');
INSERT INTO `data_setting` VALUES ('318', '3', '酒店', '0', '', '0', '34');
INSERT INTO `data_setting` VALUES ('320', '1', 'H 湖南', null, null, '0', '12');
INSERT INTO `data_setting` VALUES ('321', '2', 'S 上海', null, null, '0', '12');
INSERT INTO `data_setting` VALUES ('322', '1', '气动工具', null, null, '0', '35');
INSERT INTO `data_setting` VALUES ('323', '2', '刀具', null, null, '0', '35');
INSERT INTO `data_setting` VALUES ('324', '3', '合金钻头', null, null, '0', '35');
INSERT INTO `data_setting` VALUES ('325', '3', 'C区', null, null, '310', '33');

-- ----------------------------
-- Table structure for department_basic
-- ----------------------------
DROP TABLE IF EXISTS `department_basic`;
CREATE TABLE `department_basic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `idnum` varchar(255) DEFAULT '' COMMENT '编号',
  `depname` varchar(255) DEFAULT '' COMMENT '部门名称',
  `parentid` bigint(20) DEFAULT '0' COMMENT '隶属部门',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `addpeople` varchar(255) DEFAULT '' COMMENT '建档人',
  `adddate` varchar(255) DEFAULT NULL COMMENT '建档时间',
  `updatepeople` varchar(255) DEFAULT '' COMMENT '最后修改人',
  `updatedate` varchar(255) DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department_basic
-- ----------------------------
INSERT INTO `department_basic` VALUES ('1', 'A001', '上海三禄贸易有限公司', '0', '全球最大的贸易公司', '', '2018-09-04', 'Admin', '2018-11-30');
INSERT INTO `department_basic` VALUES ('20', 'A002', '采购部', '1', '测试', 'Administrator', '2019-04-28', '', '');
INSERT INTO `department_basic` VALUES ('21', 'A003', '销售部', '1', '测试', 'Administrator', '2019-04-28', '', '');
INSERT INTO `department_basic` VALUES ('22', 'A004', '仓库部', '1', '测试', 'Administrator', '2019-04-28', '', '');
INSERT INTO `department_basic` VALUES ('23', 'A005', '财务部', '1', '测试', 'Administrator', '2019-04-28', '', '');

-- ----------------------------
-- Table structure for depot_basic
-- ----------------------------
DROP TABLE IF EXISTS `depot_basic`;
CREATE TABLE `depot_basic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `isnum` varchar(255) DEFAULT '' COMMENT '编号',
  `depname` varchar(255) DEFAULT '' COMMENT '仓库名称',
  `depfloor` varchar(255) DEFAULT '' COMMENT '楼层',
  `parentid` bigint(20) DEFAULT '0' COMMENT '隶属仓库',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `addpeople` varchar(255) DEFAULT '' COMMENT '建档人',
  `adddate` varchar(255) DEFAULT NULL COMMENT '建档日期',
  `updatepeople` varchar(255) DEFAULT '' COMMENT '最后更新人',
  `updatedate` varchar(255) DEFAULT NULL COMMENT '最后修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of depot_basic
-- ----------------------------
INSERT INTO `depot_basic` VALUES ('6', 'A001', '4楼库位', '四楼', '309', '测试', 'Administrator', '2019-04-28', 'Administrator', '2019-05-04');
INSERT INTO `depot_basic` VALUES ('7', 'A002(停用)', 'A002', '五楼', '310', '测试', 'Administrator', '2019-04-28', 'Administrator', '2019-05-04');
INSERT INTO `depot_basic` VALUES ('8', 'A003', '测试库位', '一楼', '325', '测试', 'Administrator', '2019-04-29', 'Administrator', '2019-04-29');
INSERT INTO `depot_basic` VALUES ('9', 'A004(停用)', 'A004', '五楼', '310', '测试', 'Administrator', '2019-04-29', 'Administrator', '2019-05-04');
INSERT INTO `depot_basic` VALUES ('11', 'A002', 'A002', '五楼', '310', '测试', 'Administrator', '2019-05-04', '', '');
INSERT INTO `depot_basic` VALUES ('13', 'A008', 'A008', '四楼', '325', '测试', 'Administrator', '2019-05-07', '', '');

-- ----------------------------
-- Table structure for employee_basic
-- ----------------------------
DROP TABLE IF EXISTS `employee_basic`;
CREATE TABLE `employee_basic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `idnum` varchar(255) DEFAULT '' COMMENT '员工编号',
  `jobnum` varchar(255) DEFAULT '' COMMENT '员工工号',
  `empname` varchar(255) DEFAULT '' COMMENT '员工姓名',
  `empsex` int(11) DEFAULT '1' COMMENT '员工性别1、男  2、女',
  `empcard` varchar(255) DEFAULT '' COMMENT '员工身份证',
  `englishname` varchar(255) DEFAULT '' COMMENT '英文名称',
  `country` int(11) DEFAULT '1' COMMENT '国家',
  `area` varchar(255) DEFAULT '' COMMENT '城市',
  `passportnum` varchar(255) DEFAULT '' COMMENT '护照编号',
  `birthplace` int(11) DEFAULT '0' COMMENT '出生地',
  `nation` int(11) DEFAULT '1' COMMENT '民族',
  `marital` int(11) DEFAULT '1' COMMENT '婚姻状态',
  `phone` varchar(255) DEFAULT '' COMMENT '联系电话',
  `address` varchar(255) DEFAULT '' COMMENT '地址',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `email` varchar(255) DEFAULT '' COMMENT '邮箱',
  `addpeople` varchar(255) DEFAULT '' COMMENT '建档人',
  `adddate` varchar(255) DEFAULT '' COMMENT '建档日期',
  `updatepeople` varchar(255) DEFAULT '' COMMENT '最后修改人',
  `updatedate` varchar(255) DEFAULT '' COMMENT '最后修改日期',
  `isstop` int(11) DEFAULT '0' COMMENT '暂停使用',
  `stopdes` varchar(255) DEFAULT '' COMMENT '暂停描述',
  `department` bigint(20) DEFAULT '1' COMMENT '部门编号',
  `duty` int(11) DEFAULT '1' COMMENT '职位',
  `usedate` int(11) DEFAULT '0' COMMENT '使用时长',
  `comedate` date DEFAULT NULL COMMENT '到职日期',
  `okdate` date DEFAULT NULL COMMENT '转正日期',
  `dutystatus` int(11) DEFAULT '1' COMMENT '任职状态',
  `outdate` varchar(255) DEFAULT NULL COMMENT '停职日期',
  `checknum` varchar(255) DEFAULT '' COMMENT '考勤卡号',
  `subsdiy` double DEFAULT '0' COMMENT '职位津贴',
  `grade` int(11) DEFAULT '0' COMMENT '工资等级',
  `insurance` int(11) DEFAULT '0' COMMENT '缴纳保险',
  `password` varchar(255) DEFAULT '000000' COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee_basic
-- ----------------------------
INSERT INTO `employee_basic` VALUES ('1', 'A001', 'Admin', 'Administrator', '1', '', 'admin', '1', '上海', '', '1', '1', '1', '17521286225', '', '', 'quzhangjinghaha@163.com', '', '', 'Administrator', '2019-04-28', '0', '2019-04-03', '1', '3', '3', '2018-08-31', '2018-08-31', '1', '', '', '0', '1', '1', '1');
INSERT INTO `employee_basic` VALUES ('8', 'A002', 'ss', '销售测试账号', '1', '', '', '1', '上海', '', '1', '1', '1', '17521286225', '上海', '测试人员', '', 'Administrator', '2019-04-28', 'Administrator', '2019-04-28', '0', '', '1', '1', '0', '2018-08-31', '2018-08-31', '1', '', '', '0', '1', '1', '000000');
INSERT INTO `employee_basic` VALUES ('9', 'A003', 'cg', '采购测试人员', '1', '', '', '1', '上海', '', '1', '1', '1', '17521286225', '上海', '测试人员', '', 'Administrator', '2019-04-28', 'Administrator', '2019-04-28', '0', '', '1', '1', '0', '2018-08-31', '2018-08-31', '1', '', '', '0', '1', '1', '000000');
INSERT INTO `employee_basic` VALUES ('10', 'A004', 'ck', '仓库测试人员', '1', '', '', '1', '上海', '', '1', '1', '1', '17521286225', '上海', '测试人员', '', 'Administrator', '2019-04-28', 'Administrator', '2019-04-28', '0', '', '1', '1', '0', '2018-08-31', '2018-08-31', '1', '', '', '0', '1', '1', '000000');
INSERT INTO `employee_basic` VALUES ('11', 'A005', 'cw', '财务测试人员', '1', '', '', '1', '上海', '', '1', '1', '1', '17521286225', '上海', '测试人员', '', 'Administrator', '2019-04-28', 'Administrator', '2019-04-28', '0', '', '1', '1', '0', '2018-08-31', '2018-08-31', '1', '', '', '0', '1', '1', '000000');

-- ----------------------------
-- Table structure for express_collect
-- ----------------------------
DROP TABLE IF EXISTS `express_collect`;
CREATE TABLE `express_collect` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `collectdate` date DEFAULT NULL COMMENT '收件日期',
  `collectorder` varchar(255) DEFAULT '' COMMENT '快递单号',
  `seeorder` varchar(255) DEFAULT '' COMMENT '入库参考单据',
  `stockorder` varchar(255) DEFAULT '' COMMENT '入库单号',
  `company` varchar(255) DEFAULT '' COMMENT '快递公司',
  `name` varchar(255) DEFAULT '' COMMENT '寄件人名称',
  `address` varchar(255) DEFAULT '' COMMENT '寄件人地址',
  `freighttype` int(11) DEFAULT '0' COMMENT '本次运费类型',
  `freightprice` double DEFAULT '0' COMMENT '本次运费',
  `ispay` int(11) DEFAULT '0' COMMENT '是否制定时已付 0、未付  1、已付',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `createpeople` varchar(255) DEFAULT '' COMMENT '建档人',
  `createdate` varchar(255) DEFAULT '' COMMENT '建档日期',
  `updatepeople` varchar(255) DEFAULT '' COMMENT '最后修改人',
  `updatedate` varchar(255) DEFAULT '' COMMENT '最后修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='快递收件\r\n';

-- ----------------------------
-- Records of express_collect
-- ----------------------------
INSERT INTO `express_collect` VALUES ('2', '2019-04-28', '42387125352521', '库存异动作业', 'A1904280001', '顺丰快递', '瞿先生', '上海市', '0', '1000', '0', '', 'Administrator', '2019-04-28', '', '');

-- ----------------------------
-- Table structure for express_mail
-- ----------------------------
DROP TABLE IF EXISTS `express_mail`;
CREATE TABLE `express_mail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `maildate` date DEFAULT NULL COMMENT '寄件日期',
  `mailorder` varchar(255) DEFAULT '' COMMENT '快递单号',
  `company` varchar(255) DEFAULT '' COMMENT '快递公司',
  `mailnum` int(11) DEFAULT NULL COMMENT '数量(件数)',
  `mailweight` double DEFAULT '0' COMMENT '重量(KG)',
  `contenttype` int(11) DEFAULT '0' COMMENT '寄托内容',
  `content` varchar(255) DEFAULT '' COMMENT '内容',
  `paymethodtype` int(11) DEFAULT '0' COMMENT '支付方式',
  `paymethod` varchar(255) DEFAULT '' COMMENT '支付方法',
  `freighttype` int(11) DEFAULT '0' COMMENT '本次运费类型',
  `freightprice` double DEFAULT '0' COMMENT '本次运费',
  `ispay` int(11) DEFAULT '0' COMMENT '是否制单时已付',
  `ismonth` int(11) DEFAULT '0' COMMENT '是否月付',
  `account` varchar(255) DEFAULT '' COMMENT '账号',
  `ensure` double DEFAULT '0' COMMENT '保洁费',
  `ensurepoint` double DEFAULT '0' COMMENT '保洁费',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `createpeople` varchar(255) DEFAULT '' COMMENT '建档人',
  `createdate` varchar(255) DEFAULT '' COMMENT '建档日期',
  `updatepeople` varchar(255) NOT NULL DEFAULT '' COMMENT '最后更新人',
  `updatedate` varchar(255) DEFAULT '' COMMENT '最后更新日期',
  `shpeople` varchar(255) DEFAULT '' COMMENT '审核人',
  `shdate` varchar(255) DEFAULT '' COMMENT '审核日期',
  `shstatus` int(11) DEFAULT '0' COMMENT '审核状态',
  `mailid` varchar(255) DEFAULT '' COMMENT '寄件公司编号',
  `mailcompany` varchar(255) DEFAULT '' COMMENT '寄件公司',
  `mailprovince` varchar(255) DEFAULT '' COMMENT '寄件公司省',
  `mailcity` varchar(255) DEFAULT '' COMMENT '寄件公司市',
  `mailarea` varchar(255) DEFAULT '' COMMENT '寄件公司区',
  `mailaddress` varchar(255) DEFAULT '' COMMENT '寄件公司地址',
  `mailpeople` varchar(255) DEFAULT '' COMMENT '寄件人',
  `mailphone` varchar(255) DEFAULT '' COMMENT '寄件人联系方式',
  `collectid` varchar(255) DEFAULT '' COMMENT '客户编号',
  `collectdes` varchar(255) DEFAULT '' COMMENT '客户名称',
  `collectcompany` varchar(255) DEFAULT '' COMMENT '收件公司',
  `collectprovince` varchar(255) DEFAULT '' COMMENT '收件省',
  `collectcity` varchar(255) DEFAULT '' COMMENT '收件市',
  `collectarea` varchar(255) DEFAULT '' COMMENT '收件区',
  `collectaddress` varchar(255) DEFAULT '' COMMENT '收件地址',
  `collectpeople` varchar(255) DEFAULT '' COMMENT '收件人',
  `collectphone` varchar(255) DEFAULT '' COMMENT '联络方式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='快递寄件';

-- ----------------------------
-- Records of express_mail
-- ----------------------------
INSERT INTO `express_mail` VALUES ('3', '2019-04-28', 'A1904280001', '顺丰快递', '100', '100', '1', '测试', '1', '转账', '1', '1000', '0', '0', '12938123', '0', '0', '', 'Administrator', '2019-04-28', '', '', '', '', '0', 'A004', 'Tencent腾讯', '', '', '', '深圳市南山区高新科技园中区一路腾讯大厦', '', '0755-86013388', '20190428215126', '上海君之道信息有限公司', '上海君之道信息有限公司', '', '', '', '', '', '');

-- ----------------------------
-- Table structure for express_mail_outorder
-- ----------------------------
DROP TABLE IF EXISTS `express_mail_outorder`;
CREATE TABLE `express_mail_outorder` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `outorder` varchar(255) DEFAULT NULL,
  `orders` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `mailid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of express_mail_outorder
-- ----------------------------
INSERT INTO `express_mail_outorder` VALUES ('2', 'A1904280001', 'A1904280001', '', null);

-- ----------------------------
-- Table structure for force_order
-- ----------------------------
DROP TABLE IF EXISTS `force_order`;
CREATE TABLE `force_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `purchaseorder` varchar(255) DEFAULT '' COMMENT '采购订单',
  `purchasedate` date DEFAULT NULL COMMENT '订单日期',
  `planorder` varchar(255) DEFAULT '' COMMENT '订货单号',
  `comedate` date DEFAULT NULL COMMENT '预计到货日期',
  `suppliernum` varchar(255) DEFAULT '' COMMENT '供应商编号',
  `suppliername` varchar(255) DEFAULT '' COMMENT '供应商名称',
  `shpeople` varchar(255) DEFAULT '' COMMENT '审核人',
  `shdate` varchar(255) DEFAULT '' COMMENT '审核时间',
  `remarks` varchar(255) DEFAULT '' COMMENT '结案备注',
  `isdel` int(11) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='强制结案';

-- ----------------------------
-- Records of force_order
-- ----------------------------
INSERT INTO `force_order` VALUES ('16', 'A1904290008', '2019-04-29', '', '2019-05-09', 'A001', '上海三禄贸易刀具', 'Administrator', '2019-04-30', '', '0');
INSERT INTO `force_order` VALUES ('17', 'A1905040002', '2019-05-04', '', '2019-05-14', 'A001', '上海三禄贸易刀具', 'Administrator', '2019-05-04', '', '0');
INSERT INTO `force_order` VALUES ('18', 'A1905050001', '2019-05-05', '', '2019-05-15', 'A001', '上海三禄贸易刀具', 'Administrator', '2019-05-05', '', '0');
INSERT INTO `force_order` VALUES ('19', 'A1905070002', '2019-05-07', '', '2019-05-17', 'A001', '上海三禄贸易刀具', 'Administrator', '2019-05-07', '', '0');
INSERT INTO `force_order` VALUES ('20', 'A1905070002', '2019-05-07', '', '2019-05-17', 'A001', '上海三禄贸易刀具', 'Administrator', '2019-05-07', '', '0');

-- ----------------------------
-- Table structure for force_product
-- ----------------------------
DROP TABLE IF EXISTS `force_product`;
CREATE TABLE `force_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `forceid` bigint(20) DEFAULT '0' COMMENT '强制结案编号',
  `pronum` varchar(255) DEFAULT '' COMMENT '产品编号',
  `proname` varchar(255) DEFAULT '' COMMENT '产品名称',
  `ordernum` int(11) DEFAULT '0' COMMENT '订单数量',
  `forcenum` int(11) DEFAULT '0' COMMENT '强制结案数量',
  `forcedate` varchar(255) DEFAULT '' COMMENT '强制结案日期',
  `forceover` int(11) DEFAULT '0' COMMENT '已强制结案',
  `stockover` int(11) DEFAULT '0' COMMENT '已入库',
  `ontheway` int(11) DEFAULT '0' COMMENT '在途数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='强制结案--采购产品';

-- ----------------------------
-- Records of force_product
-- ----------------------------
INSERT INTO `force_product` VALUES ('12', '14', 'HTSEGN532', 'HTSEGN532', '50', '50', '2019-04-29', '50', '0', '50');
INSERT INTO `force_product` VALUES ('13', '15', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', '20', '10', '2019-04-30', '10', '0', '5');
INSERT INTO `force_product` VALUES ('14', '15', 'HTSEGN532', 'HTSEGN532', '30', '15', '2019-04-30', '15', '0', '10');
INSERT INTO `force_product` VALUES ('15', '15', 'SGSDS', 'SGSDS', '40', '20', '2019-04-30', '20', '0', '10');
INSERT INTO `force_product` VALUES ('16', '16', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', '20', '5', '2019-04-30', '15', '0', '5');
INSERT INTO `force_product` VALUES ('17', '16', 'HTSEGN532', 'HTSEGN532', '30', '5', '2019-04-30', '20', '0', '10');
INSERT INTO `force_product` VALUES ('18', '16', 'SGSDS', 'SGSDS', '40', '10', '2019-04-30', '30', '0', '10');
INSERT INTO `force_product` VALUES ('19', '17', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', '100', '50', '2019-05-04', '50', '0', '50');
INSERT INTO `force_product` VALUES ('20', '18', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', '300', '100', '2019-05-05', '100', '0', '100');
INSERT INTO `force_product` VALUES ('21', '18', 'HTSEGN532', 'HTSEGN532', '300', '100', '2019-05-05', '100', '0', '100');
INSERT INTO `force_product` VALUES ('22', '19', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', '500', '100', '2019-05-07', '100', '100', '100');
INSERT INTO `force_product` VALUES ('23', '20', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', '500', '100', '2019-05-07', '200', '100', '100');

-- ----------------------------
-- Table structure for industry
-- ----------------------------
DROP TABLE IF EXISTS `industry`;
CREATE TABLE `industry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '行业',
  `title` varchar(50) DEFAULT NULL COMMENT '行业名称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of industry
-- ----------------------------
INSERT INTO `industry` VALUES ('1', '汽车业', '100', '2018-08-24 14:52:34', null);
INSERT INTO `industry` VALUES ('2', '林业', '50', '2018-08-24 14:52:55', null);
INSERT INTO `industry` VALUES ('3', '银行业', '97', '2018-08-24 14:52:53', null);
INSERT INTO `industry` VALUES ('4', '保险业', '98', '2018-08-24 14:52:53', null);
INSERT INTO `industry` VALUES ('5', '采矿', '96', '2018-08-24 14:52:53', null);
INSERT INTO `industry` VALUES ('6', '餐饮', '95', '2018-08-24 14:52:53', null);
INSERT INTO `industry` VALUES ('7', '房地产', '94', '2018-08-24 14:52:53', null);
INSERT INTO `industry` VALUES ('8', '计算机', '99', '2018-08-24 14:52:53', null);

-- ----------------------------
-- Table structure for inquiry_description
-- ----------------------------
DROP TABLE IF EXISTS `inquiry_description`;
CREATE TABLE `inquiry_description` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `des` varchar(255) DEFAULT '' COMMENT '描述和说明',
  `inquiryid` bigint(20) DEFAULT '0' COMMENT '询价单编号',
  `type` int(11) DEFAULT '1' COMMENT '1、备注及说明   2、报表备注(打印至报表) 默认为1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inquiry_description
-- ----------------------------

-- ----------------------------
-- Table structure for inquiry_order
-- ----------------------------
DROP TABLE IF EXISTS `inquiry_order`;
CREATE TABLE `inquiry_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdate` date DEFAULT NULL COMMENT '制单日期',
  `orders` varchar(255) DEFAULT '' COMMENT '询价单号',
  `suppliernum` varchar(255) DEFAULT '' COMMENT '订单编号',
  `suppliername` varchar(255) DEFAULT '' COMMENT '供应商简称',
  `taxs` int(11) DEFAULT '0' COMMENT '税别',
  `currencys` int(11) DEFAULT '0' COMMENT '币别',
  `replydate` date DEFAULT NULL COMMENT '回复日期',
  `validdate` date DEFAULT NULL COMMENT '有效日期',
  `purpeopletype` int(11) DEFAULT '0' COMMENT '采购负责人类型',
  `purpeople` varchar(255) DEFAULT '' COMMENT '采购负责人',
  `createpeople` varchar(255) DEFAULT '' COMMENT '制单人',
  `shpeople` varchar(255) DEFAULT '' COMMENT '审核人',
  `shdate` varchar(255) DEFAULT '' COMMENT '审核日期',
  `updatepeople` varchar(255) DEFAULT '' COMMENT '最后修改人',
  `updatedate` varchar(255) DEFAULT '' COMMENT '最后修改日期',
  `supplierinfo` varchar(255) DEFAULT '' COMMENT '供应商名称',
  `supplierconcat` varchar(255) DEFAULT '' COMMENT '供应商联系人',
  `supplierphone` varchar(255) DEFAULT '' COMMENT '联系电话',
  `supplierfax` varchar(255) DEFAULT '' COMMENT '供应商传真',
  `supplieraddress` varchar(255) DEFAULT '' COMMENT '供应商默认地址',
  `shstatus` int(11) DEFAULT '0' COMMENT '是否审核',
  `isdel` int(11) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='询价单';

-- ----------------------------
-- Records of inquiry_order
-- ----------------------------
INSERT INTO `inquiry_order` VALUES ('5', '2019-04-28', 'A1904280001', 'A001', '三禄刀具', '1', '1', '2019-05-08', '2019-05-08', '3', '采购测试人员', '采购测试人员', '采购测试人员', '2019-04-28', '', '', '上海三禄贸易刀具', '瞿先生', '17521286225', '9298121', '上海市浦东新区', '1', '0');
INSERT INTO `inquiry_order` VALUES ('6', '2019-04-29', 'A1904290001', 'A001', '三禄刀具', '1', '0', '2019-05-09', '2019-05-09', '3', '采购测试人员', 'Administrator', 'Administrator', '2019-04-29', '', '', '上海三禄贸易刀具', '瞿先生', '17521286225', '9298121', '上海市浦东新区', '1', '0');
INSERT INTO `inquiry_order` VALUES ('7', '2019-04-29', 'A1904290002', 'A001', '三禄刀具', '1', '1', '2019-05-09', '2019-05-09', '2', '仓库测试人员', 'Administrator', 'Administrator', '2019-04-29', '', '', '上海三禄贸易刀具', '瞿先生', '17521286225', '9298121', '上海市浦东新区', '1', '0');
INSERT INTO `inquiry_order` VALUES ('8', '2019-04-29', 'A1904290003', 'A001', '三禄刀具', '1', '1', '2019-05-09', '2019-05-09', '3', '采购测试人员', 'Administrator', 'Administrator', '2019-04-29', '', '', '上海三禄贸易刀具', '瞿先生', '17521286225', '9298121', '上海市浦东新区', '1', '0');
INSERT INTO `inquiry_order` VALUES ('9', '2019-04-29', 'A1904290004', 'A001', '三禄刀具', '1', '1', '2019-05-09', '2019-05-09', '2', '仓库测试人员', 'Administrator', 'Administrator', '2019-04-29', '', '', '上海三禄贸易刀具', '瞿先生', '17521286225', '9298121', '上海市浦东新区', '1', '0');
INSERT INTO `inquiry_order` VALUES ('10', '2019-04-29', 'A1904290005', 'A001', '三禄刀具', '1', '1', '2019-05-09', '2019-05-09', '3', '采购测试人员', 'Administrator', '', '', '', '', '上海三禄贸易刀具', '瞿先生', '17521286225', '9298121', '上海市浦东新区', '0', '0');
INSERT INTO `inquiry_order` VALUES ('13', '2019-04-29', 'A1904290006', 'A001', '三禄刀具', '1', '1', '2019-05-09', '2019-05-09', '2', '仓库测试人员', 'Administrator', '', '', '', '', '上海三禄贸易刀具', '瞿先生', '17521286225', '9298121', '上海市浦东新区', '0', '0');
INSERT INTO `inquiry_order` VALUES ('14', '2019-04-29', 'A1904290007', 'A001', '三禄刀具', '1', '1', '2019-05-09', '2019-05-09', '5', 'Administrator', 'Administrator', 'Administrator', '2019-04-30', 'Administrator', '2019-04-29', '上海三禄贸易刀具', '瞿先生', '17521286225', '9298121', '上海市浦东新区', '1', '0');

-- ----------------------------
-- Table structure for inquiry_product
-- ----------------------------
DROP TABLE IF EXISTS `inquiry_product`;
CREATE TABLE `inquiry_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `proisnum` varchar(255) DEFAULT '' COMMENT '产品编号',
  `proname` varchar(255) DEFAULT '' COMMENT '产品名称',
  `protype` varchar(255) DEFAULT '' COMMENT '品类',
  `pronum` int(11) DEFAULT '0' COMMENT '产品数量',
  `prounit` varchar(255) DEFAULT '' COMMENT '产品单位',
  `proprice` double DEFAULT '0' COMMENT '产品价格',
  `totalprice` double DEFAULT '0' COMMENT '金额',
  `expecteddate` varchar(255) DEFAULT '' COMMENT '期望交易',
  `proremark` varchar(255) DEFAULT '' COMMENT '备注',
  `inquiryid` bigint(20) DEFAULT '0' COMMENT '询价单编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COMMENT='询价产品';

-- ----------------------------
-- Records of inquiry_product
-- ----------------------------
INSERT INTO `inquiry_product` VALUES ('34', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', 'ABH替换式', '100', '斤', '60', '6000', '2019-05-08', '', '5');
INSERT INTO `inquiry_product` VALUES ('35', 'HTSEGN532', 'HTSEGN532', 'ABH替换式', '100', '斤', '80', '8000', '2019-05-09', '', '6');
INSERT INTO `inquiry_product` VALUES ('36', 'HTSEGN532', 'HTSEGN532', 'ABH替换式', '30', '斤', '0', '0', '2019-05-09', '', '7');
INSERT INTO `inquiry_product` VALUES ('37', 'SGSDS', 'SGSDS', 'ABH替换式', '30', '斤', '100', '3000', '2019-05-09', '', '8');
INSERT INTO `inquiry_product` VALUES ('38', 'SGSDS', 'SGSDS', 'ABH替换式', '30', '斤', '100', '3000', '2019-05-09', '', '9');
INSERT INTO `inquiry_product` VALUES ('39', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', 'ABH替换式', '20', '斤', '100', '2000', '2019-05-09', '', '10');
INSERT INTO `inquiry_product` VALUES ('42', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', 'ABH替换式', '20', '斤', '100', '2000', '2019-05-09', null, '13');
INSERT INTO `inquiry_product` VALUES ('43', 'SGSDS', 'SGSDS', 'ABH替换式', '30', '斤', '90', '2700', '2019-05-09', '', '13');
INSERT INTO `inquiry_product` VALUES ('44', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', 'ABH替换式', '100', '斤', '50', '5000', '2019-05-09', '测试1', '14');
INSERT INTO `inquiry_product` VALUES ('45', 'SGSDS', 'SGSDS', 'ABH替换式', '100', '斤', '70', '7000', '2019-05-09', '测试2', '14');
INSERT INTO `inquiry_product` VALUES ('46', 'HTSEGN532', 'HTSEGN532', 'ABH替换式', '100', '斤', '60', '6000', '2019-05-09', '测试3', '14');

-- ----------------------------
-- Table structure for invoice
-- ----------------------------
DROP TABLE IF EXISTS `invoice`;
CREATE TABLE `invoice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '发票明细',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '客户编号',
  `usual` tinyint(1) DEFAULT NULL COMMENT '常用',
  `title` varchar(100) DEFAULT NULL COMMENT '发票抬头',
  `address` varchar(255) DEFAULT NULL COMMENT '发票地址',
  `phone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `contact` varchar(50) DEFAULT NULL COMMENT '联络人',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '停用',
  `disdate` datetime DEFAULT NULL COMMENT '停用日期',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of invoice
-- ----------------------------

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `codes` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT '' COMMENT '权限名称',
  `parent` bigint(20) DEFAULT '0' COMMENT '父级编号',
  `types` int(11) DEFAULT '0' COMMENT '类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1024 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of permissions
-- ----------------------------
INSERT INTO `permissions` VALUES ('1', '1', '编码规则设定', '0', '9');
INSERT INTO `permissions` VALUES ('2', '2', '询价单', '0', '1');
INSERT INTO `permissions` VALUES ('3', '3', '应付账款', '0', '1');
INSERT INTO `permissions` VALUES ('4', '4', '供应商基本资料', '0', '1');
INSERT INTO `permissions` VALUES ('5', '5', '资料设定', '0', '1');
INSERT INTO `permissions` VALUES ('6', '6', '功能参数设定', '0', '1');
INSERT INTO `permissions` VALUES ('7', '7', '产品基本资料', '0', '1');
INSERT INTO `permissions` VALUES ('8', '8', '产品基本资料-成本', '0', '1');
INSERT INTO `permissions` VALUES ('9', '9', '采购订单', '0', '1');
INSERT INTO `permissions` VALUES ('10', '10', '采购未入库', '0', '1');
INSERT INTO `permissions` VALUES ('11', '11', '强制结案', '0', '1');
INSERT INTO `permissions` VALUES ('12', '12', '产品库存查询', '0', '1');
INSERT INTO `permissions` VALUES ('13', '13', '在途库存', '0', '1');
INSERT INTO `permissions` VALUES ('14', '14', '最新采购查询', '0', '1');
INSERT INTO `permissions` VALUES ('15', '15', '报价单', '0', '2');
INSERT INTO `permissions` VALUES ('16', '16', '报价单-最低报价', '0', '2');
INSERT INTO `permissions` VALUES ('17', '17', '销售合同', '0', '2');
INSERT INTO `permissions` VALUES ('18', '18', '网上订单', '0', '2');
INSERT INTO `permissions` VALUES ('19', '19', '客户基本资料', '0', '2');
INSERT INTO `permissions` VALUES ('20', '20', '客户基本资料-账款备注', '0', '2');
INSERT INTO `permissions` VALUES ('21', '21', '客户基本资料-最低折扣', '0', '2');
INSERT INTO `permissions` VALUES ('22', '22', '客户基本资料-信用额度', '0', '2');
INSERT INTO `permissions` VALUES ('24', '24', '客户基本资料-催款日期', '0', '2');
INSERT INTO `permissions` VALUES ('25', '25', '客户基本资料-期初余额调整', '0', '2');
INSERT INTO `permissions` VALUES ('26', '26', '客户基本资料-备注', '0', '2');
INSERT INTO `permissions` VALUES ('27', '27', '客户需求商品', '0', '2');
INSERT INTO `permissions` VALUES ('28', '28', '订货单', '0', '2');
INSERT INTO `permissions` VALUES ('29', '29', '订货单-特价单', '0', '2');
INSERT INTO `permissions` VALUES ('30', '30', '应收账款账龄分析', '0', '2');
INSERT INTO `permissions` VALUES ('31', '31', '订货未销', '0', '2');
INSERT INTO `permissions` VALUES ('32', '32', '销货单', '0', '2');
INSERT INTO `permissions` VALUES ('33', '33', '销货单-特价单', '0', '2');
INSERT INTO `permissions` VALUES ('34', '34', '销货单-特批单', '0', '2');
INSERT INTO `permissions` VALUES ('35', '35', '销货明细表', '0', '2');
INSERT INTO `permissions` VALUES ('36', '36', '销货未出库', '0', '2');
INSERT INTO `permissions` VALUES ('37', '37', '销售退货单', '0', '2');
INSERT INTO `permissions` VALUES ('38', '38', '销退未入库', '0', '2');
INSERT INTO `permissions` VALUES ('39', '39', '销货日报表', '0', '2');
INSERT INTO `permissions` VALUES ('40', '40', '产品销售统计表', '0', '2');
INSERT INTO `permissions` VALUES ('41', '41', '销货单状态更新', '0', '2');
INSERT INTO `permissions` VALUES ('42', '42', '销货统计表', '0', '2');
INSERT INTO `permissions` VALUES ('43', '43', '销货发货跟踪', '0', '2');
INSERT INTO `permissions` VALUES ('44', '44', '销货月报表', '0', '2');
INSERT INTO `permissions` VALUES ('45', '45', '客户需求商品查询', '0', '2');
INSERT INTO `permissions` VALUES ('46', '46', '销售产品折扣查询', '0', '2');
INSERT INTO `permissions` VALUES ('47', '47', '销退统计表', '0', '2');
INSERT INTO `permissions` VALUES ('48', '48', '年度销售统计', '0', '2');
INSERT INTO `permissions` VALUES ('49', '49', '月度销售统计', '0', '2');
INSERT INTO `permissions` VALUES ('50', '50', '请假/加班/出差申请单', '0', '3');
INSERT INTO `permissions` VALUES ('51', '51', '法定节假日设定', '0', '3');
INSERT INTO `permissions` VALUES ('52', '52', '原始考勤资料', '0', '3');
INSERT INTO `permissions` VALUES ('53', '53', '考勤班次设定', '0', '3');
INSERT INTO `permissions` VALUES ('54', '54', '薪资设定', '0', '3');
INSERT INTO `permissions` VALUES ('55', '55', '考勤报告', '0', '3');
INSERT INTO `permissions` VALUES ('56', '56', '考勤资料结转', '0', '3');
INSERT INTO `permissions` VALUES ('57', '57', '薪资计算', '0', '3');
INSERT INTO `permissions` VALUES ('58', '58', '采购入库单', '0', '4');
INSERT INTO `permissions` VALUES ('59', '59', '异动查询', '0', '4');
INSERT INTO `permissions` VALUES ('60', '60', '盘点查询', '0', '4');
INSERT INTO `permissions` VALUES ('61', '61', '销货出库单', '0', '4');
INSERT INTO `permissions` VALUES ('62', '62', '销货出库单-成本', '0', '4');
INSERT INTO `permissions` VALUES ('63', '63', '库存异动作业', '0', '4');
INSERT INTO `permissions` VALUES ('64', '64', '库存异动作业-成本及总价', '0', '4');
INSERT INTO `permissions` VALUES ('65', '65', '仓库库位设置', '0', '4');
INSERT INTO `permissions` VALUES ('66', '66', '库存异动汇总', '0', '4');
INSERT INTO `permissions` VALUES ('67', '67', '库存异动汇总-成本及总价', '0', '4');
INSERT INTO `permissions` VALUES ('68', '68', '库存异动查询', '0', '4');
INSERT INTO `permissions` VALUES ('69', '69', '盘库作业', '0', '4');
INSERT INTO `permissions` VALUES ('70', '70', '盘库作业-单价', '0', '4');
INSERT INTO `permissions` VALUES ('71', '71', '快递寄件', '0', '4');
INSERT INTO `permissions` VALUES ('72', '72', '进销货统计', '0', '4');
INSERT INTO `permissions` VALUES ('73', '73', '快递收件', '0', '4');
INSERT INTO `permissions` VALUES ('74', '74', '包装打印', '0', '4');
INSERT INTO `permissions` VALUES ('75', '75', '运输商基本资料', '0', '5');
INSERT INTO `permissions` VALUES ('76', '76', '公司基本资料', '0', '5');
INSERT INTO `permissions` VALUES ('77', '77', '员工档案', '0', '5');
INSERT INTO `permissions` VALUES ('78', '78', '公司部门设置', '0', '5');
INSERT INTO `permissions` VALUES ('80', '80', '资料编码维护', '0', '5');
INSERT INTO `permissions` VALUES ('81', '81', '业务区域设定', '0', '5');
INSERT INTO `permissions` VALUES ('82', '82', '客户资料维护', '0', '6');
INSERT INTO `permissions` VALUES ('83', '83', '应收款明细', '0', '7');
INSERT INTO `permissions` VALUES ('84', '84', '应收款统计', '0', '7');
INSERT INTO `permissions` VALUES ('85', '85', '未冲发票明细', '0', '7');
INSERT INTO `permissions` VALUES ('86', '86', '业绩奖金统计表', '0', '7');
INSERT INTO `permissions` VALUES ('87', '87', '成本核算', '0', '8');
INSERT INTO `permissions` VALUES ('88', '88', '销货成本明细', '0', '8');
INSERT INTO `permissions` VALUES ('89', '89', '库存成本查询', '0', '8');
INSERT INTO `permissions` VALUES ('90', '90', '应收账款冲账', '0', '8');
INSERT INTO `permissions` VALUES ('91', '91', '应付账款冲账', '0', '8');
INSERT INTO `permissions` VALUES ('92', '92', '销项发票', '0', '8');
INSERT INTO `permissions` VALUES ('93', '93', '进项发票', '0', '8');
INSERT INTO `permissions` VALUES ('94', '94', '销货未开票', '0', '8');
INSERT INTO `permissions` VALUES ('95', '95', '收款单', '0', '8');
INSERT INTO `permissions` VALUES ('96', '96', '预付账款', '0', '8');
INSERT INTO `permissions` VALUES ('97', '97', '账款期初余额设定', '0', '9');
INSERT INTO `permissions` VALUES ('98', '98', '产品库存维护', '0', '9');
INSERT INTO `permissions` VALUES ('99', '99', '库存初始化', '0', '9');
INSERT INTO `permissions` VALUES ('100', '100', '库存开账作业', '0', '9');
INSERT INTO `permissions` VALUES ('101', '101', '关联单据', '0', '9');
INSERT INTO `permissions` VALUES ('102', '1_102_3', '修改', '1', '9');
INSERT INTO `permissions` VALUES ('103', '1_103_4', '查看', '1', '9');
INSERT INTO `permissions` VALUES ('104', '1_104_7', '打印', '1', '9');
INSERT INTO `permissions` VALUES ('105', '1_105_8', '配置', '1', '9');
INSERT INTO `permissions` VALUES ('106', '2_106_1', '新增', '2', '1');
INSERT INTO `permissions` VALUES ('107', '2_107_2', '删除', '2', '1');
INSERT INTO `permissions` VALUES ('108', '2_108_3', '修改', '2', '1');
INSERT INTO `permissions` VALUES ('109', '2_109_4', '查看', '2', '1');
INSERT INTO `permissions` VALUES ('110', '2_110_5', '审核', '2', '1');
INSERT INTO `permissions` VALUES ('111', '2_111_6', '取消审核', '2', '1');
INSERT INTO `permissions` VALUES ('112', '2_112_7', '打印', '2', '1');
INSERT INTO `permissions` VALUES ('113', '2_113_8', '配置', '2', '1');
INSERT INTO `permissions` VALUES ('115', '3_105_4', '查看', '3', '1');
INSERT INTO `permissions` VALUES ('116', '3_116_7', '打印', '3', '1');
INSERT INTO `permissions` VALUES ('117', '3_117_8', '配置', '3', '1');
INSERT INTO `permissions` VALUES ('118', '4_118_1', '新增', '4', '1');
INSERT INTO `permissions` VALUES ('119', '4_119_2', '删除', '4', '1');
INSERT INTO `permissions` VALUES ('120', '4_120_3', '修改', '4', '1');
INSERT INTO `permissions` VALUES ('121', '4_121_4', '查看', '4', '1');
INSERT INTO `permissions` VALUES ('122', '4_122_8', '配置', '4', '1');
INSERT INTO `permissions` VALUES ('123', '4_123_7', '打印', '4', '1');
INSERT INTO `permissions` VALUES ('124', '5_124_1', '新增', '5', '1');
INSERT INTO `permissions` VALUES ('125', '5_125_3', '修改', '5', '1');
INSERT INTO `permissions` VALUES ('126', '5_126_4', '查看', '5', '1');
INSERT INTO `permissions` VALUES ('127', '5_127_8', '配置', '5', '1');
INSERT INTO `permissions` VALUES ('128', '6_128_3', '修改', '6', '1');
INSERT INTO `permissions` VALUES ('129', '6_129_4', '查看', '6', '1');
INSERT INTO `permissions` VALUES ('130', '6_130_5', '审核', '6', '1');
INSERT INTO `permissions` VALUES ('131', '6_131_8', '配置', '6', '1');
INSERT INTO `permissions` VALUES ('132', '7_132_1', '新增', '7', '1');
INSERT INTO `permissions` VALUES ('133', '7_133_2', '删除', '7', '1');
INSERT INTO `permissions` VALUES ('134', '7_134_3', '修改', '7', '1');
INSERT INTO `permissions` VALUES ('135', '7_135_4', '查看', '7', '1');
INSERT INTO `permissions` VALUES ('136', '7_136_7', '打印', '7', '1');
INSERT INTO `permissions` VALUES ('137', '7_137_8', '配置', '7', '1');
INSERT INTO `permissions` VALUES ('138', '8_138_4', '查看', '8', '1');
INSERT INTO `permissions` VALUES ('139', '9_139_1', '新增', '9', '1');
INSERT INTO `permissions` VALUES ('140', '9_140_2', '删除', '9', '1');
INSERT INTO `permissions` VALUES ('141', '9_141_3', '修改', '9', '1');
INSERT INTO `permissions` VALUES ('142', '9_142_4', '查看', '9', '1');
INSERT INTO `permissions` VALUES ('143', '9_143_5', '审核', '9', '1');
INSERT INTO `permissions` VALUES ('145', '9_145_6', '取消审核', '9', '1');
INSERT INTO `permissions` VALUES ('146', '9_146_7', '打印', '9', '1');
INSERT INTO `permissions` VALUES ('147', '9_147_8', '配置', '9', '1');
INSERT INTO `permissions` VALUES ('148', '10_148_4', '查看', '10', '1');
INSERT INTO `permissions` VALUES ('149', '10_149_7', '打印', '10', '1');
INSERT INTO `permissions` VALUES ('150', '10_150_8', '配置', '10', '1');
INSERT INTO `permissions` VALUES ('151', '11_151_1', '新增', '11', '1');
INSERT INTO `permissions` VALUES ('152', '11_152_4', '查看', '11', '1');
INSERT INTO `permissions` VALUES ('153', '11_153_7', '打印', '11', '1');
INSERT INTO `permissions` VALUES ('154', '11_154_8', '配置', '11', '1');
INSERT INTO `permissions` VALUES ('155', '12_155_4', '查看', '12', '1');
INSERT INTO `permissions` VALUES ('156', '12_156_7', '打印', '12', '1');
INSERT INTO `permissions` VALUES ('157', '12_157_8', '配置', '12', '1');
INSERT INTO `permissions` VALUES ('158', '13_158_1', '新增', '13', '1');
INSERT INTO `permissions` VALUES ('159', '13_159_2', '删除', '13', '1');
INSERT INTO `permissions` VALUES ('160', '13_160_3', '修改', '13', '1');
INSERT INTO `permissions` VALUES ('161', '13_161_4', '查看', '13', '1');
INSERT INTO `permissions` VALUES ('162', '13_162_7', '打印', '13', '1');
INSERT INTO `permissions` VALUES ('163', '13_163_8', '配置', '13', '1');
INSERT INTO `permissions` VALUES ('164', '14_164_4', '查看', '14', '1');
INSERT INTO `permissions` VALUES ('165', '14_165_7', '打印', '14', '1');
INSERT INTO `permissions` VALUES ('166', '14_166_8', '配置', '14', '1');
INSERT INTO `permissions` VALUES ('167', '15_167_1', '新增', '15', '2');
INSERT INTO `permissions` VALUES ('168', '15_168_2', '删除', '15', '2');
INSERT INTO `permissions` VALUES ('169', '15_169_3', '修改', '15', '2');
INSERT INTO `permissions` VALUES ('170', '15_170_4', '查看', '15', '2');
INSERT INTO `permissions` VALUES ('171', '15_171_5', '审核', '15', '2');
INSERT INTO `permissions` VALUES ('172', '15_172_6', '取消审核', '15', '2');
INSERT INTO `permissions` VALUES ('173', '15_173_7', '打印', '15', '2');
INSERT INTO `permissions` VALUES ('174', '15_174_8', '配置', '15', '2');
INSERT INTO `permissions` VALUES ('175', '16_175_3', '修改', '16', '2');
INSERT INTO `permissions` VALUES ('176', '16_176_4', '查看', '16', '2');
INSERT INTO `permissions` VALUES ('177', '17_177_1', '新增', '17', '2');
INSERT INTO `permissions` VALUES ('178', '17_178_4', '查看', '17', '2');
INSERT INTO `permissions` VALUES ('179', '17_179_7', '打印', '17', '2');
INSERT INTO `permissions` VALUES ('180', '17_180_8', '配置', '17', '2');
INSERT INTO `permissions` VALUES ('181', '18_181_2', '删除', '18', '2');
INSERT INTO `permissions` VALUES ('182', '18_182_3', '修改', '18', '2');
INSERT INTO `permissions` VALUES ('183', '18_183_4', '查看', '18', '2');
INSERT INTO `permissions` VALUES ('184', '18_184_5', '审核', '18', '2');
INSERT INTO `permissions` VALUES ('185', '18_185_7', '打印', '18', '2');
INSERT INTO `permissions` VALUES ('186', '18_186_8', '配置', '18', '2');
INSERT INTO `permissions` VALUES ('187', '19_187_1', '新增', '19', '2');
INSERT INTO `permissions` VALUES ('188', '19_188_2', '删除', '19', '2');
INSERT INTO `permissions` VALUES ('189', '19_189_3', '修改', '19', '2');
INSERT INTO `permissions` VALUES ('190', '19_190_4', '查看', '19', '2');
INSERT INTO `permissions` VALUES ('191', '19_191_7', '打印', '19', '2');
INSERT INTO `permissions` VALUES ('192', '19_192_8', '配置', '19', '2');
INSERT INTO `permissions` VALUES ('193', '20_193_3', '修改', '20', '2');
INSERT INTO `permissions` VALUES ('194', '21_194_3', '修改', '21', '2');
INSERT INTO `permissions` VALUES ('195', '22_195_3', '修改', '22', '2');
INSERT INTO `permissions` VALUES ('196', '23_196_3', '修改', '23', '2');
INSERT INTO `permissions` VALUES ('197', '24_197_3', '修改', '24', '2');
INSERT INTO `permissions` VALUES ('198', '25_198_3', '修改', '25', '2');
INSERT INTO `permissions` VALUES ('199', '26_199_3', '修改', '26', '2');
INSERT INTO `permissions` VALUES ('200', '27_200_1', '新增', '27', '2');
INSERT INTO `permissions` VALUES ('201', '27_201_2', '删除', '27', '2');
INSERT INTO `permissions` VALUES ('202', '27_202_3', '修改', '27', '2');
INSERT INTO `permissions` VALUES ('203', '27_203_4', '查看', '27', '2');
INSERT INTO `permissions` VALUES ('204', '27_204_7', '打印', '27', '2');
INSERT INTO `permissions` VALUES ('205', '27_205_8', '配置', '27', '2');
INSERT INTO `permissions` VALUES ('206', '28_206_1', '新增', '28', '2');
INSERT INTO `permissions` VALUES ('207', '28_207_2', '删除', '28', '2');
INSERT INTO `permissions` VALUES ('208', '28_208_3', '修改', '28', '2');
INSERT INTO `permissions` VALUES ('209', '28_209_4', '查看', '28', '2');
INSERT INTO `permissions` VALUES ('210', '28_210_5', '审核', '28', '2');
INSERT INTO `permissions` VALUES ('211', '28_211_6', '取消审核', '28', '2');
INSERT INTO `permissions` VALUES ('212', '28_212_7', '打印', '28', '2');
INSERT INTO `permissions` VALUES ('213', '28_213_8', '配置', '28', '2');
INSERT INTO `permissions` VALUES ('214', '29_214_3', '修改', '29', '2');
INSERT INTO `permissions` VALUES ('215', '29_215_4', '查看', '29', '2');
INSERT INTO `permissions` VALUES ('216', '30_216_4', '查看', '30', '2');
INSERT INTO `permissions` VALUES ('217', '30_217_7', '打印', '30', '2');
INSERT INTO `permissions` VALUES ('218', '30_218_8', '配置', '30', '2');
INSERT INTO `permissions` VALUES ('219', '31_2169_4', '查看', '31', '2');
INSERT INTO `permissions` VALUES ('230', '31_230_7', '打印', '31', '2');
INSERT INTO `permissions` VALUES ('231', '31_232_8', '配置', '31', '2');
INSERT INTO `permissions` VALUES ('232', '32_232_1', '新增', '32', '2');
INSERT INTO `permissions` VALUES ('233', '32_233_2', '删除', '32', '2');
INSERT INTO `permissions` VALUES ('234', '32_234_3', '修改', '32', '2');
INSERT INTO `permissions` VALUES ('235', '32_235_4', '查看', '22', '2');
INSERT INTO `permissions` VALUES ('236', '32_236_5', '审核', '32', '2');
INSERT INTO `permissions` VALUES ('237', '32_237_6', '取消审核', '32', '2');
INSERT INTO `permissions` VALUES ('238', '32_238_7', '打印', '32', '2');
INSERT INTO `permissions` VALUES ('239', '32_239_8', '配置', '32', '2');
INSERT INTO `permissions` VALUES ('242', '33_242_3', '修改', '33', '2');
INSERT INTO `permissions` VALUES ('243', '33_243_4', '查看', '33', '2');
INSERT INTO `permissions` VALUES ('248', '34_248_3', '修改', '34', '2');
INSERT INTO `permissions` VALUES ('249', '34_249_4', '查看', '34', '2');
INSERT INTO `permissions` VALUES ('250', '35_250_4', '查看', '35', '2');
INSERT INTO `permissions` VALUES ('251', '35_251_7', '打印', '35', '2');
INSERT INTO `permissions` VALUES ('252', '35_252_8', '配置', '35', '2');
INSERT INTO `permissions` VALUES ('253', '36_253_4', '查看', '36', '2');
INSERT INTO `permissions` VALUES ('254', '36_254_7', '打印', '36', '2');
INSERT INTO `permissions` VALUES ('255', '36_255_8', '配置', '36', '2');
INSERT INTO `permissions` VALUES ('256', '37_256_1', '新增', '37', '2');
INSERT INTO `permissions` VALUES ('257', '37_257_2', '删除', '37', '2');
INSERT INTO `permissions` VALUES ('258', '37_258_3', '修改', '37', '2');
INSERT INTO `permissions` VALUES ('259', '37_259_4', '查看', '37', '2');
INSERT INTO `permissions` VALUES ('260', '37_260_5', '审核', '37', '2');
INSERT INTO `permissions` VALUES ('261', '37_261_6', '取消审核', '37', '2');
INSERT INTO `permissions` VALUES ('262', '37_262_7', '打印', '37', '2');
INSERT INTO `permissions` VALUES ('263', '37_263_8', '配置', '37', '2');
INSERT INTO `permissions` VALUES ('264', '38_264_4', '查看', '38', '2');
INSERT INTO `permissions` VALUES ('265', '38_265_7', '打印', '38', '2');
INSERT INTO `permissions` VALUES ('266', '38_266_8', '配置', '38', '2');
INSERT INTO `permissions` VALUES ('267', '39_267_4', '查看', '39', '2');
INSERT INTO `permissions` VALUES ('268', '39_268_7', '打印', '39', '2');
INSERT INTO `permissions` VALUES ('269', '39_269_8', '配置', '39', '2');
INSERT INTO `permissions` VALUES ('270', '40_270_4', '查看', '40', '2');
INSERT INTO `permissions` VALUES ('271', '40_271_7', '打印', '40', '2');
INSERT INTO `permissions` VALUES ('272', '40_272_8', '配置', '40', '2');
INSERT INTO `permissions` VALUES ('273', '41_273_3', '修改', '41', '2');
INSERT INTO `permissions` VALUES ('274', '41_274_4', '查看', '41', '2');
INSERT INTO `permissions` VALUES ('275', '41_275_7', '打印', '41', '2');
INSERT INTO `permissions` VALUES ('276', '41_276_8', '配置', '41', '2');
INSERT INTO `permissions` VALUES ('277', '42_277_4', '查看', '42', '2');
INSERT INTO `permissions` VALUES ('278', '42_278_7', '打印', '42', '2');
INSERT INTO `permissions` VALUES ('279', '42_279_8', '配置', '42', '2');
INSERT INTO `permissions` VALUES ('280', '43_280_4', '查看', '43', '2');
INSERT INTO `permissions` VALUES ('281', '43_281_7', '打印', '43', '2');
INSERT INTO `permissions` VALUES ('282', '43_282_8', '配置', '43', '2');
INSERT INTO `permissions` VALUES ('283', '44_283_4', '查看', '44', '2');
INSERT INTO `permissions` VALUES ('284', '44_284_7', '打印', '44', '2');
INSERT INTO `permissions` VALUES ('285', '44_285_8', '配置', '44', '2');
INSERT INTO `permissions` VALUES ('286', '45_286_4', '查看', '45', '2');
INSERT INTO `permissions` VALUES ('287', '45_287_7', '打印', '45', '2');
INSERT INTO `permissions` VALUES ('288', '45_288_8', '配置', '45', '2');
INSERT INTO `permissions` VALUES ('289', '46_289_4', '查看', '46', '2');
INSERT INTO `permissions` VALUES ('290', '46_290_7', '打印', '46', '2');
INSERT INTO `permissions` VALUES ('291', '46_291_8', '配置', '46', '2');
INSERT INTO `permissions` VALUES ('292', '47_292_4', '查看', '47', '2');
INSERT INTO `permissions` VALUES ('293', '47_293_7', '打印', '47', '2');
INSERT INTO `permissions` VALUES ('294', '47_294_8', '配置', '47', '2');
INSERT INTO `permissions` VALUES ('295', '49_295_4', '查看', '49', '2');
INSERT INTO `permissions` VALUES ('296', '49_296_7', '打印', '49', '2');
INSERT INTO `permissions` VALUES ('297', '49_297_8', '配置', '49', '2');
INSERT INTO `permissions` VALUES ('298', '50_298_1', '新增', '50', '3');
INSERT INTO `permissions` VALUES ('299', '50_299_2', '删除', '50', '3');
INSERT INTO `permissions` VALUES ('300', '50_300_3', '修改', '50', '3');
INSERT INTO `permissions` VALUES ('301', '50_301_4', '查看', '50', '3');
INSERT INTO `permissions` VALUES ('302', '50_302_5', '审核', '50', '3');
INSERT INTO `permissions` VALUES ('303', '50_303_6', '取消审核', '50', '3');
INSERT INTO `permissions` VALUES ('304', '50_304_7', '打印', '50', '3');
INSERT INTO `permissions` VALUES ('305', '50_305_8', '配置', '50', '3');
INSERT INTO `permissions` VALUES ('306', '51_306_1', '新增', '51', '3');
INSERT INTO `permissions` VALUES ('307', '51_307_2', '删除', '51', '3');
INSERT INTO `permissions` VALUES ('308', '51_308_3', '修改', '51', '3');
INSERT INTO `permissions` VALUES ('309', '51_309_4', '查看', '51', '3');
INSERT INTO `permissions` VALUES ('310', '51_310_5', '审核', '51', '3');
INSERT INTO `permissions` VALUES ('311', '51_311_6', '取消审核', '51', '3');
INSERT INTO `permissions` VALUES ('312', '51_312_7', '打印', '51', '3');
INSERT INTO `permissions` VALUES ('313', '51_313_8', '配置', '51', '3');
INSERT INTO `permissions` VALUES ('314', '52_314_1', '新增', '52', '3');
INSERT INTO `permissions` VALUES ('315', '52_315_2', '删除', '52', '3');
INSERT INTO `permissions` VALUES ('316', '52_316_3', '修改', '52', '3');
INSERT INTO `permissions` VALUES ('317', '52_317_4', '查看', '52', '3');
INSERT INTO `permissions` VALUES ('318', '52_318_5', '审核', '52', '3');
INSERT INTO `permissions` VALUES ('319', '52_319_6', '取消审核', '52', '3');
INSERT INTO `permissions` VALUES ('320', '52_320_7', '打印', '52', '3');
INSERT INTO `permissions` VALUES ('321', '52_321_8', '配置', '52', '3');
INSERT INTO `permissions` VALUES ('322', '53_322_1', '新增', '53', '3');
INSERT INTO `permissions` VALUES ('323', '53_323_2', '删除', '53', '3');
INSERT INTO `permissions` VALUES ('324', '53_324_3', '修改', '53', '3');
INSERT INTO `permissions` VALUES ('325', '53_325_4', '查看', '53', '3');
INSERT INTO `permissions` VALUES ('326', '53_326_5', '审核', '53', '3');
INSERT INTO `permissions` VALUES ('327', '53_327_6', '取消审核', '53', '3');
INSERT INTO `permissions` VALUES ('328', '53_328_7', '打印', '53', '3');
INSERT INTO `permissions` VALUES ('329', '53_329_8', '配置', '53', '3');
INSERT INTO `permissions` VALUES ('330', '54_330_1', '新增', '54', '3');
INSERT INTO `permissions` VALUES ('331', '54_331_3', '修改', '54', '3');
INSERT INTO `permissions` VALUES ('332', '54_332_4', '查看', '54', '3');
INSERT INTO `permissions` VALUES ('333', '54_333_7', '打印', '54', '3');
INSERT INTO `permissions` VALUES ('334', '54_334_8', '配置', '54', '3');
INSERT INTO `permissions` VALUES ('335', '55_335_1', '新增', '55', '3');
INSERT INTO `permissions` VALUES ('336', '55_336_2', '删除', '55', '3');
INSERT INTO `permissions` VALUES ('337', '55_337_3', '修改', '55', '3');
INSERT INTO `permissions` VALUES ('338', '55_338_4', '查看', '55', '3');
INSERT INTO `permissions` VALUES ('339', '55_339_5', '审核', '55', '3');
INSERT INTO `permissions` VALUES ('340', '55_340_6', '取消审核', '55', '3');
INSERT INTO `permissions` VALUES ('341', '55_341_7', '打印', '55', '3');
INSERT INTO `permissions` VALUES ('342', '55_342_8', '配置', '55', '3');
INSERT INTO `permissions` VALUES ('343', '56_343_1', '新增', '56', '3');
INSERT INTO `permissions` VALUES ('344', '56_344_2', '删除', '56', '3');
INSERT INTO `permissions` VALUES ('345', '56_345_3', '修改', '56', '3');
INSERT INTO `permissions` VALUES ('346', '56_346_4', '查看', '56', '3');
INSERT INTO `permissions` VALUES ('347', '56_347_5', '审核', '56', '3');
INSERT INTO `permissions` VALUES ('348', '56_348_6', '取消审核', '56', '3');
INSERT INTO `permissions` VALUES ('350', '56_350_8', '配置', '56', '3');
INSERT INTO `permissions` VALUES ('351', '57_351_1', '新增', '57', '3');
INSERT INTO `permissions` VALUES ('352', '57_352_2', '删除', '57', '3');
INSERT INTO `permissions` VALUES ('353', '57_353_3', '修改', '57', '3');
INSERT INTO `permissions` VALUES ('354', '57_354_4', '查看', '57', '3');
INSERT INTO `permissions` VALUES ('355', '57_355_5', '审核', '57', '3');
INSERT INTO `permissions` VALUES ('356', '57_356_6', '取消审核', '57', '3');
INSERT INTO `permissions` VALUES ('357', '57_357_7', '打印', '57', '3');
INSERT INTO `permissions` VALUES ('358', '57_358_8', '配置', '57', '3');
INSERT INTO `permissions` VALUES ('359', '58_359_1', '新增', '58', '4');
INSERT INTO `permissions` VALUES ('360', '58_360_2', '删除', '58', '4');
INSERT INTO `permissions` VALUES ('361', '58_361_3', '修改', '58', '4');
INSERT INTO `permissions` VALUES ('362', '58_362_4', '查看', '58', '4');
INSERT INTO `permissions` VALUES ('363', '58_363_5', '审核', '58', '4');
INSERT INTO `permissions` VALUES ('364', '58_364_6', '取消审核', '58', '4');
INSERT INTO `permissions` VALUES ('365', '58_365_7', '打印', '58', '4');
INSERT INTO `permissions` VALUES ('366', '58_366_8', '配置', '58', '4');
INSERT INTO `permissions` VALUES ('367', '59_367_4', '查看', '59', '4');
INSERT INTO `permissions` VALUES ('368', '59_368_7', '打印', '59', '4');
INSERT INTO `permissions` VALUES ('369', '59_369_8', '配置', '59', '4');
INSERT INTO `permissions` VALUES ('370', '60_370_4', '查看', '60', '4');
INSERT INTO `permissions` VALUES ('371', '60_371_7', '打印', '60', '4');
INSERT INTO `permissions` VALUES ('372', '60_378_8', '配置', '60', '4');
INSERT INTO `permissions` VALUES ('373', '61_373_1', '新增', '61', '4');
INSERT INTO `permissions` VALUES ('374', '61_374_2', '删除', '61', '4');
INSERT INTO `permissions` VALUES ('375', '61_375_3', '修改', '61', '4');
INSERT INTO `permissions` VALUES ('376', '61_376_4', '查看', '61', '4');
INSERT INTO `permissions` VALUES ('377', '61_377_5', '审核', '61', '4');
INSERT INTO `permissions` VALUES ('378', '61_378_6', '取消审核', '61', '4');
INSERT INTO `permissions` VALUES ('379', '61_379_7', '打印', '61', '4');
INSERT INTO `permissions` VALUES ('380', '61_380_8', '配置', '61', '4');
INSERT INTO `permissions` VALUES ('381', '62_381_4', '查看', '62', '4');
INSERT INTO `permissions` VALUES ('382', '63_382_1', '新增', '63', '4');
INSERT INTO `permissions` VALUES ('383', '63_383_2', '删除', '63', '4');
INSERT INTO `permissions` VALUES ('384', '63_384_3', '修改', '63', '4');
INSERT INTO `permissions` VALUES ('385', '63_385_4', '查看', '63', '4');
INSERT INTO `permissions` VALUES ('386', '63_386_5', '审核', '63', '4');
INSERT INTO `permissions` VALUES ('387', '63_387_6', '取消审核', '63', '4');
INSERT INTO `permissions` VALUES ('388', '63_388_7', '打印', '63', '4');
INSERT INTO `permissions` VALUES ('389', '63_389_8', '配置', '63', '4');
INSERT INTO `permissions` VALUES ('390', '64_390_4', '查看', '64', '4');
INSERT INTO `permissions` VALUES ('391', '65_391_1', '新增', '65', '4');
INSERT INTO `permissions` VALUES ('392', '65_392_2', '删除', '65', '4');
INSERT INTO `permissions` VALUES ('393', '65_393_3', '修改', '65', '4');
INSERT INTO `permissions` VALUES ('394', '65_394_4', '查看', '65', '4');
INSERT INTO `permissions` VALUES ('395', '65_395_7', '打印', '65', '4');
INSERT INTO `permissions` VALUES ('396', '65_396_8', '配置', '65', '4');
INSERT INTO `permissions` VALUES ('397', '66_397_4', '查看', '66', '4');
INSERT INTO `permissions` VALUES ('398', '66_398_7', '打印', '66', '4');
INSERT INTO `permissions` VALUES ('399', '66_399_8', '配置', '66', '4');
INSERT INTO `permissions` VALUES ('400', '67_400_4', '查看', '67', '4');
INSERT INTO `permissions` VALUES ('401', '68_401_4', '查看', '68', '4');
INSERT INTO `permissions` VALUES ('402', '69_402_1', '新增', '69', '4');
INSERT INTO `permissions` VALUES ('403', '69_403_2', '删除', '69', '4');
INSERT INTO `permissions` VALUES ('404', '69_404_3', '修改', '69', '4');
INSERT INTO `permissions` VALUES ('405', '69_405_4', '查看', '69', '4');
INSERT INTO `permissions` VALUES ('406', '69_406_5', '审核', '69', '4');
INSERT INTO `permissions` VALUES ('407', '69_407_6', '取消审核', '69', '4');
INSERT INTO `permissions` VALUES ('408', '69_408_7', '打印', '69', '4');
INSERT INTO `permissions` VALUES ('409', '69_409_8', '配置', '69', '4');
INSERT INTO `permissions` VALUES ('410', '70_410_4', '查看', '70', '4');
INSERT INTO `permissions` VALUES ('411', '71_411_1', '新增', '71', '4');
INSERT INTO `permissions` VALUES ('412', '71_412_2', '删除', '71', '4');
INSERT INTO `permissions` VALUES ('413', '71_413_3', '修改', '71', '4');
INSERT INTO `permissions` VALUES ('414', '71_414_4', '查看', '71', '4');
INSERT INTO `permissions` VALUES ('415', '71_415_7', '打印', '71', '4');
INSERT INTO `permissions` VALUES ('416', '71_416_8', '配置', '71', '4');
INSERT INTO `permissions` VALUES ('417', '72_417_4', '查看', '72', '4');
INSERT INTO `permissions` VALUES ('418', '72_418_8', '配置', '72', '4');
INSERT INTO `permissions` VALUES ('419', '73_419_1', '新增', '73', '4');
INSERT INTO `permissions` VALUES ('420', '73_420_2', '删除', '73', '4');
INSERT INTO `permissions` VALUES ('421', '73_421_3', '修改', '73', '4');
INSERT INTO `permissions` VALUES ('422', '73_422_4', '查看', '73', '4');
INSERT INTO `permissions` VALUES ('423', '73_423_7', '打印', '73', '4');
INSERT INTO `permissions` VALUES ('424', '73_424_8', '配置', '73', '4');
INSERT INTO `permissions` VALUES ('425', '74_425_4', '查看', '74', '4');
INSERT INTO `permissions` VALUES ('426', '74_426_7', '打印', '74', '4');
INSERT INTO `permissions` VALUES ('427', '74_427_8', '配置', '74', '4');
INSERT INTO `permissions` VALUES ('428', '75_428_1', '新增', '75', '5');
INSERT INTO `permissions` VALUES ('429', '75_429_2', '删除', '75', '5');
INSERT INTO `permissions` VALUES ('430', '75_430_3', '修改', '75', '5');
INSERT INTO `permissions` VALUES ('431', '75_431_4', '查看', '75', '5');
INSERT INTO `permissions` VALUES ('432', '75_432_7', '打印', '75', '5');
INSERT INTO `permissions` VALUES ('433', '75_433_8', '配置', '75', '5');
INSERT INTO `permissions` VALUES ('434', '76_434_1', '新增', '76', '5');
INSERT INTO `permissions` VALUES ('435', '76_435_2', '删除', '76', '5');
INSERT INTO `permissions` VALUES ('436', '76_436_3', '修改', '76', '5');
INSERT INTO `permissions` VALUES ('437', '76_437_4', '查看', '76', '5');
INSERT INTO `permissions` VALUES ('438', '76_438_7', '打印', '76', '5');
INSERT INTO `permissions` VALUES ('439', '76_439_8', '配置', '76', '5');
INSERT INTO `permissions` VALUES ('440', '77_440_1', '新增', '77', '5');
INSERT INTO `permissions` VALUES ('441', '77_441_2', '删除', '77', '5');
INSERT INTO `permissions` VALUES ('442', '77_442_3', '修改', '77', '5');
INSERT INTO `permissions` VALUES ('443', '77_443_4', '查看', '77', '5');
INSERT INTO `permissions` VALUES ('444', '77_444_7', '打印', '77', '5');
INSERT INTO `permissions` VALUES ('445', '77_445_8', '配置', '77', '5');
INSERT INTO `permissions` VALUES ('446', '78_446_1', '新增', '78', '5');
INSERT INTO `permissions` VALUES ('447', '78_447_2', '删除', '78', '5');
INSERT INTO `permissions` VALUES ('448', '78_448_3', '修改', '78', '5');
INSERT INTO `permissions` VALUES ('449', '78_449_4', '查看', '78', '5');
INSERT INTO `permissions` VALUES ('450', '78_450_7', '打印', '78', '5');
INSERT INTO `permissions` VALUES ('451', '78_451_8', '配置', '78', '5');
INSERT INTO `permissions` VALUES ('452', '79_452_3', '修改', '79', '5');
INSERT INTO `permissions` VALUES ('453', '79_453_4', '查看', '79', '5');
INSERT INTO `permissions` VALUES ('454', '79_454_7', '打印', '79', '5');
INSERT INTO `permissions` VALUES ('455', '79_455_8', '配置', '79', '5');
INSERT INTO `permissions` VALUES ('456', '80_456_3', '修改', '80', '5');
INSERT INTO `permissions` VALUES ('457', '80_457_4', '查看', '80', '5');
INSERT INTO `permissions` VALUES ('458', '80_458_7', '打印', '80', '5');
INSERT INTO `permissions` VALUES ('459', '80_459_8', '配置', '80', '5');
INSERT INTO `permissions` VALUES ('460', '81_460_1', '新增', '81', '5');
INSERT INTO `permissions` VALUES ('461', '81_461_2', '删除', '81', '5');
INSERT INTO `permissions` VALUES ('462', '81_462_3', '修改', '81', '5');
INSERT INTO `permissions` VALUES ('463', '81_463_4', '查看', '81', '5');
INSERT INTO `permissions` VALUES ('464', '81_464_7', '打印', '81', '5');
INSERT INTO `permissions` VALUES ('465', '81_465_8', '配置', '81', '5');
INSERT INTO `permissions` VALUES ('466', '82_466_1', '新增', '82', '6');
INSERT INTO `permissions` VALUES ('467', '82_467_2', '删除', '82', '6');
INSERT INTO `permissions` VALUES ('468', '82_468_3', '修改', '82', '6');
INSERT INTO `permissions` VALUES ('469', '82_469_4', '查看', '82', '6');
INSERT INTO `permissions` VALUES ('470', '82_470_7', '打印', '82', '6');
INSERT INTO `permissions` VALUES ('471', '82_471_8', '配置', '82', '6');
INSERT INTO `permissions` VALUES ('475', '83_475_4', '查看', '83', '7');
INSERT INTO `permissions` VALUES ('476', '83_476_7', '打印', '83', '7');
INSERT INTO `permissions` VALUES ('477', '83_477_8', '配置', '83', '7');
INSERT INTO `permissions` VALUES ('478', '84_478_4', '查看', '84', '7');
INSERT INTO `permissions` VALUES ('479', '84_479_7', '打印', '84', '7');
INSERT INTO `permissions` VALUES ('480', '84_480_8', '配置', '84', '7');
INSERT INTO `permissions` VALUES ('481', '85_481_4', '查看', '85', '7');
INSERT INTO `permissions` VALUES ('482', '85_482_7', '打印', '85', '7');
INSERT INTO `permissions` VALUES ('483', '85_483_8', '配置', '85', '7');
INSERT INTO `permissions` VALUES ('484', '86_484_4', '查看', '86', '7');
INSERT INTO `permissions` VALUES ('485', '86_485_7', '打印', '86', '7');
INSERT INTO `permissions` VALUES ('486', '86_486_8', '配置', '86', '7');
INSERT INTO `permissions` VALUES ('487', '87_487_1', '新增', '87', '8');
INSERT INTO `permissions` VALUES ('488', '87_488_4', '查看', '87', '8');
INSERT INTO `permissions` VALUES ('489', '67_489_5', '审核', '87', '8');
INSERT INTO `permissions` VALUES ('490', '87_490_7', '打印', '87', '8');
INSERT INTO `permissions` VALUES ('491', '87_491_8', '配置', '87', '8');
INSERT INTO `permissions` VALUES ('492', '88_492_4', '查看', '88', '8');
INSERT INTO `permissions` VALUES ('493', '88_493_7', '打印', '88', '8');
INSERT INTO `permissions` VALUES ('494', '88_494_8', '配置', '88', '8');
INSERT INTO `permissions` VALUES ('495', '89_495_4', '查看', '89', '8');
INSERT INTO `permissions` VALUES ('496', '89_496_7', '打印', '89', '8');
INSERT INTO `permissions` VALUES ('497', '89_497_8', '配置', '89', '8');
INSERT INTO `permissions` VALUES ('498', '90_498_1', '新增', '90', '8');
INSERT INTO `permissions` VALUES ('499', '90_499_2', '删除', '90', '8');
INSERT INTO `permissions` VALUES ('500', '90_500_3', '修改', '90', '6');
INSERT INTO `permissions` VALUES ('501', '90_501_4', '查看', '90', '8');
INSERT INTO `permissions` VALUES ('502', '90_502_5', '审核', '90', '8');
INSERT INTO `permissions` VALUES ('503', '90_503_6', '取消审核', '90', '8');
INSERT INTO `permissions` VALUES ('504', '90_504_7', '打印', '90', '8');
INSERT INTO `permissions` VALUES ('505', '90_505_8', '配置', '90', '8');
INSERT INTO `permissions` VALUES ('506', '91_506_1', '新增', '91', '8');
INSERT INTO `permissions` VALUES ('507', '91_507_2', '删除', '91', '8');
INSERT INTO `permissions` VALUES ('508', '91_508_3', '修改', '91', '6');
INSERT INTO `permissions` VALUES ('509', '91_509_4', '查看', '91', '8');
INSERT INTO `permissions` VALUES ('510', '91_510_5', '审核', '91', '8');
INSERT INTO `permissions` VALUES ('511', '91_511_6', '取消审核', '91', '8');
INSERT INTO `permissions` VALUES ('512', '91_512_7', '打印', '91', '8');
INSERT INTO `permissions` VALUES ('513', '91_513_8', '配置', '91', '8');
INSERT INTO `permissions` VALUES ('514', '92_514_1', '新增', '92', '8');
INSERT INTO `permissions` VALUES ('515', '92_515_2', '删除', '92', '8');
INSERT INTO `permissions` VALUES ('516', '92_516_3', '修改', '92', '6');
INSERT INTO `permissions` VALUES ('517', '92_517_4', '查看', '92', '8');
INSERT INTO `permissions` VALUES ('518', '92_518_5', '审核', '92', '8');
INSERT INTO `permissions` VALUES ('519', '92_519_6', '取消审核', '92', '8');
INSERT INTO `permissions` VALUES ('520', '92_520_7', '打印', '92', '8');
INSERT INTO `permissions` VALUES ('521', '92_521_8', '配置', '92', '8');
INSERT INTO `permissions` VALUES ('522', '93_522_1', '新增', '93', '8');
INSERT INTO `permissions` VALUES ('523', '93_523_2', '删除', '93', '8');
INSERT INTO `permissions` VALUES ('524', '93_524_3', '修改', '93', '6');
INSERT INTO `permissions` VALUES ('525', '93_525_4', '查看', '93', '8');
INSERT INTO `permissions` VALUES ('526', '93_526_5', '审核', '93', '8');
INSERT INTO `permissions` VALUES ('527', '93_527_6', '取消审核', '93', '8');
INSERT INTO `permissions` VALUES ('528', '93_528_7', '打印', '93', '8');
INSERT INTO `permissions` VALUES ('529', '93_529_8', '配置', '93', '8');
INSERT INTO `permissions` VALUES ('530', '94_530_4', '查看', '94', '8');
INSERT INTO `permissions` VALUES ('531', '94_531_7', '打印', '94', '8');
INSERT INTO `permissions` VALUES ('532', '94_532_8', '配置', '94', '8');
INSERT INTO `permissions` VALUES ('533', '95_533_1', '新增', '95', '8');
INSERT INTO `permissions` VALUES ('534', '95_534_2', '删除', '95', '8');
INSERT INTO `permissions` VALUES ('535', '95_535_3', '修改', '95', '6');
INSERT INTO `permissions` VALUES ('536', '95_536_4', '查看', '95', '8');
INSERT INTO `permissions` VALUES ('537', '95_537_5', '审核', '95', '8');
INSERT INTO `permissions` VALUES ('538', '95_538_6', '取消审核', '95', '8');
INSERT INTO `permissions` VALUES ('539', '95_539_7', '打印', '95', '8');
INSERT INTO `permissions` VALUES ('540', '95_540_8', '配置', '95', '8');
INSERT INTO `permissions` VALUES ('541', '96_541_1', '新增', '96', '8');
INSERT INTO `permissions` VALUES ('542', '96_542_2', '删除', '96', '8');
INSERT INTO `permissions` VALUES ('543', '96_543_3', '修改', '96', '8');
INSERT INTO `permissions` VALUES ('544', '96_544_4', '查看', '96', '8');
INSERT INTO `permissions` VALUES ('545', '96_545_5', '审核', '96', '8');
INSERT INTO `permissions` VALUES ('546', '96_546_6', '取消审核', '96', '8');
INSERT INTO `permissions` VALUES ('547', '96_547_7', '打印', '96', '8');
INSERT INTO `permissions` VALUES ('548', '96_548_8', '配置', '96', '8');
INSERT INTO `permissions` VALUES ('549', '97_549_3', '修改', '97', '9');
INSERT INTO `permissions` VALUES ('550', '97_550_4', '查看', '97', '9');
INSERT INTO `permissions` VALUES ('551', '97_551_7', '打印', '97', '9');
INSERT INTO `permissions` VALUES ('552', '97_552_8', '配置', '97', '9');
INSERT INTO `permissions` VALUES ('554', '98_554_8', '配置', '98', '9');
INSERT INTO `permissions` VALUES ('555', '99_555_3', '修改', '99', '9');
INSERT INTO `permissions` VALUES ('556', '99_556_4', '查看', '99', '9');
INSERT INTO `permissions` VALUES ('557', '99_557_8', '配置', '99', '9');
INSERT INTO `permissions` VALUES ('558', '100_558_1', '新增', '100', '8');
INSERT INTO `permissions` VALUES ('559', '100_559_4', '查看', '100', '9');
INSERT INTO `permissions` VALUES ('560', '100_560_8', '配置', '100', '9');
INSERT INTO `permissions` VALUES ('561', '101_565_1', '新增', '101', '9');
INSERT INTO `permissions` VALUES ('562', '101_563_4', '查看', '101', '9');
INSERT INTO `permissions` VALUES ('563', '101_563_8', '配置', '101', '9');
INSERT INTO `permissions` VALUES ('564', '1000_564_4', '查看', '1000', '9');
INSERT INTO `permissions` VALUES ('565', '1000_565_7', '打印', '1000', '9');
INSERT INTO `permissions` VALUES ('566', '1000_566_8', '配置', '1000', '9');
INSERT INTO `permissions` VALUES ('568', '1001_568_7', '打印', '1001', '9');
INSERT INTO `permissions` VALUES ('569', '1001_578_8', '配置', '1001', '9');
INSERT INTO `permissions` VALUES ('570', '1002_570_4', '查看', '1002', '9');
INSERT INTO `permissions` VALUES ('571', '1002_571_8', '配置', '1002', '9');
INSERT INTO `permissions` VALUES ('572', '1003_572_4', '查看', '1003', '9');
INSERT INTO `permissions` VALUES ('573', '1003_573_8', '配置', '1003', '9');
INSERT INTO `permissions` VALUES ('574', '1004_574_1', '新增', '1004', '9');
INSERT INTO `permissions` VALUES ('575', '1004_572_4', '查看', '1004', '9');
INSERT INTO `permissions` VALUES ('576', '1004_576_8', '配置', '1004', '9');
INSERT INTO `permissions` VALUES ('577', '1005_577_4', '查看', '1005', '9');
INSERT INTO `permissions` VALUES ('578', '1005_578_8', '配置', '1005', '9');
INSERT INTO `permissions` VALUES ('579', '1006_579_3', '修改', '1006', '9');
INSERT INTO `permissions` VALUES ('580', '1006_580_4', '查看', '1006', '9');
INSERT INTO `permissions` VALUES ('581', '1006_581_8', '配置', '1006', '9');
INSERT INTO `permissions` VALUES ('582', '1007_582_4', '查看', '1007', '9');
INSERT INTO `permissions` VALUES ('583', '1007_583_8', '配置', '1007', '9');
INSERT INTO `permissions` VALUES ('584', '1008_584_3', '修改', '1008', '9');
INSERT INTO `permissions` VALUES ('585', '1008_585_4', '查看', '1008', '9');
INSERT INTO `permissions` VALUES ('586', '1008_586_8', '配置', '1008', '9');
INSERT INTO `permissions` VALUES ('587', '1009_587_3', '修改', '1009', '9');
INSERT INTO `permissions` VALUES ('588', '1009_588_4', '查看', '1009', '9');
INSERT INTO `permissions` VALUES ('589', '1009_589_5', '审核', '1009', '9');
INSERT INTO `permissions` VALUES ('590', '1009_590_8', '配置', '1009', '9');
INSERT INTO `permissions` VALUES ('591', '1010_591_3', '修改', '1010', '9');
INSERT INTO `permissions` VALUES ('592', '1010_592_4', '查看', '1010', '9');
INSERT INTO `permissions` VALUES ('593', '1010_593_8', '配置', '1010', '9');
INSERT INTO `permissions` VALUES ('1000', '1000', '查看Log记录', '0', '9');
INSERT INTO `permissions` VALUES ('1001', '1001', '库存预警', '0', '9');
INSERT INTO `permissions` VALUES ('1002', '1002', '待审核单据', '0', '9');
INSERT INTO `permissions` VALUES ('1003', '1003', '单据重整', '0', '9');
INSERT INTO `permissions` VALUES ('1004', '1004', '库存关账作业', '0', '9');
INSERT INTO `permissions` VALUES ('1005', '1005', '库存重整', '0', '9');
INSERT INTO `permissions` VALUES ('1006', '1006', '用户权限设定', '0', '9');
INSERT INTO `permissions` VALUES ('1007', '1007', '库存查询', '0', '9');
INSERT INTO `permissions` VALUES ('1008', '1008', '用户密码修改', '0', '9');
INSERT INTO `permissions` VALUES ('1009', '1009', '系统启用参数', '0', '9');
INSERT INTO `permissions` VALUES ('1010', '1010', '期初成本设置', '0', '9');
INSERT INTO `permissions` VALUES ('1011', '1001_1011_4', '查看', '1001', '9');
INSERT INTO `permissions` VALUES ('1012', '98_1012_4', '查看', '98', '9');
INSERT INTO `permissions` VALUES ('1013', '48_1013_4', '查看', '48', '2');
INSERT INTO `permissions` VALUES ('1014', '48_1014_7', '打印', '48', '2');
INSERT INTO `permissions` VALUES ('1015', '48_1015_8', '配置', '48', '2');
INSERT INTO `permissions` VALUES ('1016', '32_1016_4', '查看', '32', '2');
INSERT INTO `permissions` VALUES ('1017', '1017', '产品价格设定', '0', '1');
INSERT INTO `permissions` VALUES ('1018', '1017_1018_4', '查看', '1017', '1');
INSERT INTO `permissions` VALUES ('1019', '1017_1019_7', '打印', '1017', '1');
INSERT INTO `permissions` VALUES ('1020', '1017_1020_8', '配置', '1017', '1');
INSERT INTO `permissions` VALUES ('1021', '1017_1021_3', '修改', '1017', '1');
INSERT INTO `permissions` VALUES ('1022', '1022', '产品基本资料-入库单单价', '0', '1');
INSERT INTO `permissions` VALUES ('1023', '1022_1023_4', '查看', '1022', '1');

-- ----------------------------
-- Table structure for permissions_employee
-- ----------------------------
DROP TABLE IF EXISTS `permissions_employee`;
CREATE TABLE `permissions_employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) DEFAULT '0' COMMENT '员工编号',
  `idnum` varchar(255) DEFAULT '' COMMENT '员工编号',
  `uname` varchar(255) DEFAULT '' COMMENT '员工名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='权限用户';

-- ----------------------------
-- Records of permissions_employee
-- ----------------------------
INSERT INTO `permissions_employee` VALUES ('9', '1', 'A001', 'Administrator');

-- ----------------------------
-- Table structure for product_basic
-- ----------------------------
DROP TABLE IF EXISTS `product_basic`;
CREATE TABLE `product_basic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `isnum` varchar(255) DEFAULT '' COMMENT '编号',
  `proname` varchar(255) DEFAULT '' COMMENT '产品名称',
  `pronum` varchar(255) DEFAULT '' COMMENT '产品条码',
  `invoicenum` varchar(255) DEFAULT '' COMMENT '发票品名',
  `basicunit` bigint(20) DEFAULT '1' COMMENT '基本单位',
  `protype` bigint(20) DEFAULT '1' COMMENT '产品大类',
  `normpricetype` bigint(20) DEFAULT '1' COMMENT '标准售价类型',
  `normprice` double(22,2) DEFAULT '0.00' COMMENT '标准售价',
  `lowpricetype` bigint(20) DEFAULT '1' COMMENT '最低售价类型',
  `lowprice` double(22,2) DEFAULT '0.00' COMMENT '最低售价',
  `pronature` bigint(20) DEFAULT '1' COMMENT '产品性质',
  `prosource` bigint(20) DEFAULT '1' COMMENT '产品来源',
  `packnum` int(11) DEFAULT '0' COMMENT '包装数量',
  `minnum` int(11) DEFAULT '0' COMMENT '最小起订数',
  `businesstype` bigint(20) DEFAULT '1' COMMENT '负责业务类型',
  `business` varchar(255) DEFAULT '' COMMENT '负责业务',
  `purchasetype` bigint(20) DEFAULT '1' COMMENT '负责采购类型',
  `purchase` varchar(255) DEFAULT '' COMMENT '负责采购',
  `maxstock` int(11) DEFAULT '0' COMMENT '最大库存量',
  `safetystock` int(11) DEFAULT '0' COMMENT '安全库存量',
  `weights` double(22,2) DEFAULT '0.00' COMMENT '重量',
  `inventoryplace` varchar(255) DEFAULT '' COMMENT '库位',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `addpeople` varchar(255) DEFAULT '' COMMENT '建档人',
  `adddate` varchar(255) DEFAULT NULL COMMENT '建档日期',
  `updatepeople` varchar(255) DEFAULT '' COMMENT '最后修改人',
  `updatedate` varchar(255) DEFAULT NULL COMMENT '最后修改日期',
  `costtype` bigint(20) DEFAULT '1' COMMENT '成本类型',
  `cost` double(22,2) DEFAULT '0.00' COMMENT '成本',
  `usdcost` double(22,2) DEFAULT '0.00' COMMENT '美金',
  `isstop` int(11) DEFAULT '0' COMMENT '暂停使用',
  `stopdes` varchar(255) DEFAULT '' COMMENT '暂停描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_basic
-- ----------------------------
INSERT INTO `product_basic` VALUES ('11', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', '988272622', '1', '1', '1', '100.00', '1', '80.00', '1', '1', '100', '20', '4', '销售测试账号', '3', '采购测试人员', '1000', '30', '0.00', 'A001,A002', '', 'Administrator', '2019-04-28', 'Administrator', '2019-04-28', '1', '61.00', '61.00', '0', '');
INSERT INTO `product_basic` VALUES ('12', 'HTSEGN532', 'HTSEGN532', 'HTSEGN532', '34123', '1', '1', '1', '0.00', '1', '0.00', '1', '1', '1000', '30', '3', '采购测试人员', '3', '采购测试人员', '100', '20', '0.00', 'A003,A002', '', 'Administrator', '2019-04-29', '', '', '1', '0.00', '0.00', '0', '');
INSERT INTO `product_basic` VALUES ('13', 'SGSDS', 'SGSDS', 'SGSDS', '23333', '1', '1', '1', '100.00', '1', '80.00', '1', '1', '1000', '30', '3', '采购测试人员', '4', '销售测试账号', '100', '30', '0.00', 'A001,A002', '', 'Administrator', '2019-04-29', 'Administrator', '2019-04-29', '1', '0.00', '0.00', '0', '');
INSERT INTO `product_basic` VALUES ('14', 'KANDAK 16LGH89', 'KANDAK 16LGH89', 'KANDAK 16LGH89', '21312412412', '1', '1', '1', '25.00', '1', '20.00', '1', '1', '100', '20', '4', '销售测试账号', '3', '采购测试人员', '1000', '300', '60.00', 'A002,A003', '测试', 'Administrator', '2019-05-05', '', '', '1', '0.00', '0.00', '0', '');
INSERT INTO `product_basic` VALUES ('15', 'UCHD-627 JGUDH', 'UCHD-627 JGUDH', 'UCHD-627 JGUDH', 'UCHD-627 JGUDH', '1', '1', '1', '65.00', '1', '60.00', '1', '1', '0', '0', '4', '销售测试账号', '3', '采购测试人员', '0', '0', '0.00', 'A008', '', 'Administrator', '2019-05-07', '', '', '1', '0.00', '0.00', '0', '');

-- ----------------------------
-- Table structure for product_details
-- ----------------------------
DROP TABLE IF EXISTS `product_details`;
CREATE TABLE `product_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `proid` bigint(20) DEFAULT '0' COMMENT '产品id',
  `declares` varchar(255) DEFAULT '' COMMENT '说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_details
-- ----------------------------

-- ----------------------------
-- Table structure for product_stock
-- ----------------------------
DROP TABLE IF EXISTS `product_stock`;
CREATE TABLE `product_stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `productorder` varchar(255) DEFAULT '' COMMENT '产品编号',
  `productname` varchar(255) DEFAULT '' COMMENT '产品名称',
  `usablenum` int(11) DEFAULT '0' COMMENT '可用数量',
  `stocknum` int(11) DEFAULT '0' COMMENT '库存数量',
  `onthewaynum` int(11) DEFAULT '0' COMMENT '在途数量',
  `salenum` int(11) DEFAULT '0' COMMENT '销售未出',
  `backnum` int(11) DEFAULT '0' COMMENT '销退未入',
  `purchasenum` int(11) DEFAULT '0' COMMENT '采购未入',
  `parprice` double(12,2) DEFAULT '0.00' COMMENT '标准售价',
  `purchaseprice` double(12,2) DEFAULT '0.00' COMMENT '进价',
  `depot` varchar(255) DEFAULT '' COMMENT '库位编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='产评库存表';

-- ----------------------------
-- Records of product_stock
-- ----------------------------
INSERT INTO `product_stock` VALUES ('14', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', '92', '92', '5', '0', '2', '-75', '100.00', '100.00', 'A001');
INSERT INTO `product_stock` VALUES ('15', 'HTSEGN532', 'HTSEGN532', '30', '30', '60', '0', '0', '190', '0.00', '0.00', 'A003');
INSERT INTO `product_stock` VALUES ('16', 'SGSDS', 'SGSDS', '40', '40', '0', '0', '0', '0', '100.00', '100.00', 'A001');
INSERT INTO `product_stock` VALUES ('17', 'SGSDS', 'SGSDS', '80', '80', '0', '0', '0', '0', '0.00', '0.00', 'A002');
INSERT INTO `product_stock` VALUES ('25', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', '520', '520', '200', '0', '0', '700', '0.00', '100.00', 'A002');
INSERT INTO `product_stock` VALUES ('26', 'HTSEGN532', 'HTSEGN532', '200', '200', '0', '0', '0', '0', '0.00', '0.00', 'A002');
INSERT INTO `product_stock` VALUES ('27', 'KANDAK 16LGH89', 'KANDAK 16LGH89', '301', '301', '0', '0', '0', '200', '0.00', '0.00', 'A002');
INSERT INTO `product_stock` VALUES ('28', 'KANDAK 16LGH89', 'KANDAK 16LGH89', '280', '280', '0', '0', '0', '0', '0.00', '25.00', 'A003');
INSERT INTO `product_stock` VALUES ('29', 'UCHD-627 JGUDH', 'UCHD-627 JGUDH', '30', '30', '0', '0', '0', '0', '65.00', '65.00', 'A008');

-- ----------------------------
-- Table structure for product_supplier
-- ----------------------------
DROP TABLE IF EXISTS `product_supplier`;
CREATE TABLE `product_supplier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `proid` bigint(20) DEFAULT '0' COMMENT '产品id',
  `supplierid` varchar(255) DEFAULT '' COMMENT '供应商编号',
  `supdes` varchar(255) DEFAULT '' COMMENT '供应商简称',
  `supply` varchar(255) DEFAULT '' COMMENT '主要供应',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_supplier
-- ----------------------------
INSERT INTO `product_supplier` VALUES ('15', '11', 'A001', '三禄刀具', null, '测试');
INSERT INTO `product_supplier` VALUES ('16', '12', 'A001', '三禄刀具', null, '测试');
INSERT INTO `product_supplier` VALUES ('17', '13', 'A001', '三禄刀具', null, '测试');
INSERT INTO `product_supplier` VALUES ('18', '14', 'A001', '三禄刀具', null, '测试');

-- ----------------------------
-- Table structure for product_type
-- ----------------------------
DROP TABLE IF EXISTS `product_type`;
CREATE TABLE `product_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `title` varchar(255) DEFAULT '' COMMENT '标题',
  `remarks` varchar(255) DEFAULT '' COMMENT '描述',
  `parent` int(11) DEFAULT '0' COMMENT '父级id',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_type
-- ----------------------------
INSERT INTO `product_type` VALUES ('1', '0', 'ABH替换式', '', '0', '2018-09-20 17:31:32');
INSERT INTO `product_type` VALUES ('2', '0', 'ABS小径搪刀', '', '0', '2018-09-20 17:31:32');
INSERT INTO `product_type` VALUES ('3', '0', 'ABBS精搪头刀杆', '', '0', '2018-09-20 17:31:32');

-- ----------------------------
-- Table structure for purchase_description
-- ----------------------------
DROP TABLE IF EXISTS `purchase_description`;
CREATE TABLE `purchase_description` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `des` varchar(255) DEFAULT '' COMMENT '描述和备注',
  `purchaseid` bigint(20) DEFAULT '0' COMMENT '采购订单编号',
  `type` int(11) DEFAULT '1' COMMENT '1、备注及说明   2、报表备注(打印至报表) 默认为1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采购订单描述及备注 报表';

-- ----------------------------
-- Records of purchase_description
-- ----------------------------

-- ----------------------------
-- Table structure for purchase_invoice
-- ----------------------------
DROP TABLE IF EXISTS `purchase_invoice`;
CREATE TABLE `purchase_invoice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `supplierorder` varchar(255) DEFAULT '' COMMENT '供应商编号',
  `supplierdes` varchar(255) DEFAULT '' COMMENT '供应商简称',
  `invoicenumber` varchar(255) DEFAULT '' COMMENT '发票号码',
  `invoicedata` varchar(255) DEFAULT '' COMMENT '发票日期',
  `invoiceprice` double(22,2) DEFAULT '0.00' COMMENT '开票金额',
  `offsetprice` double(22,2) DEFAULT '0.00' COMMENT '已冲金额',
  `meetprice` double(22,2) DEFAULT '0.00' COMMENT '应付金额',
  `purchaseorder` varchar(255) DEFAULT '' COMMENT '采购订单编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='采购账款';

-- ----------------------------
-- Records of purchase_invoice
-- ----------------------------
INSERT INTO `purchase_invoice` VALUES ('1', 'A001', 'jzdsh', '000012', '2018-12-10', '3166.80', '0.00', '3166.80', 'A1810240001');

-- ----------------------------
-- Table structure for purchase_orders
-- ----------------------------
DROP TABLE IF EXISTS `purchase_orders`;
CREATE TABLE `purchase_orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdate` date DEFAULT NULL COMMENT '制单日期',
  `orders` varchar(255) DEFAULT '' COMMENT '采购单号',
  `suppliernum` varchar(255) DEFAULT '' COMMENT '供应商编号',
  `supplierdes` varchar(255) DEFAULT '' COMMENT '供应商简称',
  `warehouseid` varchar(255) DEFAULT '' COMMENT '产库编号',
  `warehousename` varchar(255) DEFAULT '' COMMENT '产库名称',
  `seeorders` varchar(255) DEFAULT '' COMMENT '参考单号',
  `comedate` date DEFAULT NULL COMMENT '到货日期',
  `ptax` int(11) DEFAULT '0' COMMENT '税别',
  `pcurrency` int(11) DEFAULT '0' COMMENT '币别',
  `purpeopletype` int(11) DEFAULT '0' COMMENT '采购负责类型',
  `purpeople` varchar(255) DEFAULT '' COMMENT '采购人',
  `createpeople` varchar(255) DEFAULT '' COMMENT '制单人',
  `shpeople` varchar(255) DEFAULT '' COMMENT '审核人',
  `shdate` varchar(255) DEFAULT '' COMMENT '审核日期',
  `updatepeople` varchar(255) DEFAULT '' COMMENT '最后修改人',
  `updatedate` varchar(255) DEFAULT '' COMMENT '最后修改日期',
  `suppliername` varchar(255) DEFAULT '' COMMENT '供应商名称',
  `supplierconcat` varchar(255) DEFAULT '' COMMENT '联系人',
  `supplierphone` varchar(255) DEFAULT '' COMMENT '电话',
  `supplierfax` varchar(255) DEFAULT '' COMMENT '传真',
  `supplieraddress` varchar(255) DEFAULT '' COMMENT '地址',
  `shstatus` int(11) DEFAULT '0' COMMENT '是否审核',
  `orderstatus` int(11) DEFAULT '0' COMMENT '订单状态 0、进行中 1、已完结',
  `isdel` int(11) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='采购订单';

-- ----------------------------
-- Records of purchase_orders
-- ----------------------------
INSERT INTO `purchase_orders` VALUES ('11', '2019-04-28', 'A1904280001', 'A001', '三禄刀具', '309', 'A区仓库', 'A1904280001', '2019-05-08', '1', '1', '3', '采购测试人员', '采购测试人员', '', '', '', '', '上海三禄贸易刀具', '瞿先生', '17521286225', '9298121', '上海市浦东新区', '0', '0', '0');
INSERT INTO `purchase_orders` VALUES ('12', '2019-04-29', 'A1904290001', 'A001', '上海三禄贸易刀具', '325', 'C区', 'A1904290001', '2019-05-09', '1', '1', '3', '采购测试人员', 'Administrator', 'Administrator', '2019-04-29', 'Administrator', '2019-04-29', '上海三禄贸易刀具', '瞿先生', '17521286225', '9298121', '上海市浦东新区', '1', '0', '0');
INSERT INTO `purchase_orders` VALUES ('14', '2019-04-29', 'A1904290002', 'A001', '三禄刀具', '310', 'B区仓库', 'A1904290002', '2019-05-09', '1', '1', '2', '仓库测试人员', 'Administrator', 'Administrator', '2019-04-29', '', '', '上海三禄贸易刀具', '瞿先生', '17521286225', '9298121', '上海市浦东新区', '1', '0', '0');
INSERT INTO `purchase_orders` VALUES ('17', '2019-04-29', 'A1904290004', 'A001', '三禄刀具', '309', 'A区仓库', 'A1904290003', '2019-05-09', '1', '1', '3', '采购测试人员', 'Administrator', 'Administrator', '2019-04-29', '', '', '上海三禄贸易刀具', '瞿先生', '17521286225', '9298121', '上海市浦东新区', '1', '0', '0');
INSERT INTO `purchase_orders` VALUES ('18', '2019-04-29', 'A1904290005', 'A001', '上海三禄贸易刀具', '310', 'B区仓库', 'A1904290004', '2019-05-09', '1', '1', '2', '仓库测试人员', 'Administrator', 'Administrator', '2019-05-07', 'Administrator', '2019-05-07', '上海三禄贸易刀具', '瞿先生', '17521286225', '9298121', '上海市浦东新区', '1', '0', '0');
INSERT INTO `purchase_orders` VALUES ('25', '2019-04-29', 'A1904290006', 'A001', '三禄刀具', '310', 'B区仓库', '', '2019-05-09', '1', '1', '2', '仓库测试人员', 'Administrator', 'Administrator', '2019-04-29', '', '', '上海三禄贸易刀具', '瞿先生', '17521286225', '9298121', '上海市浦东新区', '1', '0', '0');
INSERT INTO `purchase_orders` VALUES ('27', '2019-04-29', 'A1904290008', 'A001', '三禄刀具', '309', 'A区仓库', '', '2019-05-09', '1', '1', '5', 'Administrator', 'Administrator', 'Administrator', '2019-04-30', '', '', '上海三禄贸易刀具', '瞿先生', '17521286225', '9298121', '上海市浦东新区', '1', '0', '0');
INSERT INTO `purchase_orders` VALUES ('29', '2019-05-03', 'A1905030001', 'A001', '上海三禄贸易刀具', '310', 'B区仓库', '', '2019-05-13', '1', '1', '5', 'Administrator', 'Administrator', 'Administrator', '2019-05-03', 'Administrator', '2019-05-03', '上海三禄贸易刀具', '瞿先生', '17521286225', '9298121', '上海市浦东新区', '1', '0', '0');
INSERT INTO `purchase_orders` VALUES ('30', '2019-05-04', 'A1905040001', 'A001', '三禄刀具', '309', 'A区仓库', '', '2019-05-14', '1', '1', '5', 'Administrator', 'Administrator', 'Administrator', '2019-05-04', '', '', '上海三禄贸易刀具', '瞿先生', '17521286225', '9298121', '上海市浦东新区', '1', '0', '0');
INSERT INTO `purchase_orders` VALUES ('31', '2019-05-04', 'A1905040002', 'A001', '三禄刀具', '309', '', '', '2019-05-14', '1', '1', '5', 'Administrator', 'Administrator', 'Administrator', '2019-05-04', '', '', '上海三禄贸易刀具', '瞿先生', '17521286225', '9298121', '上海市浦东新区', '1', '0', '0');
INSERT INTO `purchase_orders` VALUES ('32', '2019-05-05', 'A1905050001', 'A001', '三禄刀具', '309', '', '', '2019-05-15', '1', '1', '5', 'Administrator', 'Administrator', 'Administrator', '2019-05-05', '', '', '上海三禄贸易刀具', '瞿先生', '17521286225', '9298121', '上海市浦东新区', '1', '0', '0');
INSERT INTO `purchase_orders` VALUES ('33', '2019-05-05', 'A1905050002', 'A002', '测试', '309', '', '', '2019-05-15', '1', '1', '3', '采购测试人员', 'Administrator', 'Administrator', '2019-05-05', 'Administrator', '2019-05-05', '测试', '采购先生', '123444123', '4123123', '上海', '1', '0', '0');
INSERT INTO `purchase_orders` VALUES ('34', '2019-05-05', 'A1905050003', 'A002', '测试', '309', '', '', '2019-05-15', '1', '1', '3', '采购测试人员', 'Administrator', 'Administrator', '2019-05-05', 'Administrator', '2019-05-05', '测试', '采购先生', '123444123', '4123123', '上海', '1', '0', '0');
INSERT INTO `purchase_orders` VALUES ('35', '2019-05-06', 'A1905060001', 'A001', '三禄刀具', '309', '', '', '2019-05-16', '1', '1', '5', 'Administrator', 'Administrator', 'Administrator', '2019-05-06', '', '', '上海三禄贸易刀具', '瞿先生', '17521286225', '9298121', '上海市浦东新区', '1', '0', '0');
INSERT INTO `purchase_orders` VALUES ('36', '2019-05-07', 'A1905070001', 'A001', '上海三禄贸易刀具', '309', '', '', '2019-05-17', '1', '1', '5', 'Administrator', 'Administrator', 'Administrator', '2019-05-07', 'Administrator', '2019-05-07', '上海三禄贸易刀具', '瞿先生', '17521286225', '9298121', '上海市浦东新区', '1', '0', '0');
INSERT INTO `purchase_orders` VALUES ('37', '2019-05-07', 'A1905070002', 'A001', '三禄刀具', '309', '', '', '2019-05-17', '1', '1', '5', 'Administrator', 'Administrator', 'Administrator', '2019-05-07', '', '', '上海三禄贸易刀具', '瞿先生', '17521286225', '9298121', '上海市浦东新区', '1', '0', '0');

-- ----------------------------
-- Table structure for purchase_product
-- ----------------------------
DROP TABLE IF EXISTS `purchase_product`;
CREATE TABLE `purchase_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sourseorder` varchar(255) DEFAULT '' COMMENT '来源单据',
  `orders` varchar(255) DEFAULT '' COMMENT '单据编号',
  `sort` int(11) DEFAULT '0' COMMENT '序号',
  `proorders` varchar(255) DEFAULT '' COMMENT '产品编号',
  `proname` varchar(255) DEFAULT '' COMMENT '产品名称',
  `supname` varchar(255) DEFAULT '' COMMENT '供应商名称',
  `num` int(11) DEFAULT '0' COMMENT '数量',
  `unit` varchar(255) DEFAULT '' COMMENT '单位',
  `price` double DEFAULT '0' COMMENT '单价',
  `totalprice` double DEFAULT '0' COMMENT '金额',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `forcenum` int(11) DEFAULT '0' COMMENT '已强制结案数量',
  `stockover` int(11) DEFAULT '0' COMMENT '已入库数量',
  `ontheway` int(11) DEFAULT '0' COMMENT '在途数量',
  `forcedate` varchar(255) DEFAULT '' COMMENT '强制结案日期',
  `purchaseid` bigint(20) DEFAULT '0' COMMENT '采购订单id',
  `expecteddate` varchar(255) DEFAULT NULL COMMENT '期望交期',
  `depotnum` varchar(255) DEFAULT NULL COMMENT '产库编号',
  `depotname` varchar(255) DEFAULT NULL COMMENT '仓库名称',
  `floor` varchar(255) DEFAULT NULL COMMENT '楼层',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of purchase_product
-- ----------------------------
INSERT INTO `purchase_product` VALUES ('15', '询价单', 'A1904280001', '0', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', '三禄刀具', '100', '斤', '60', '6000', '', '0', '100', '0', null, '11', '2019-05-08', 'A001', '4楼库位', '四楼');
INSERT INTO `purchase_product` VALUES ('16', '报价单', 'A1904290001', '1', 'HTSEGN532', 'HTSEGN532', '', '50', '斤', '80', '4000', '', '50', '0', '50', '2019-04-29', '12', '2019-05-09', '', '', '');
INSERT INTO `purchase_product` VALUES ('17', '询价单', 'A1904290002', '0', 'HTSEGN532', 'HTSEGN532', '三禄刀具', '30', '斤', '0', '0', '', '0', '0', '0', null, '14', '2019-05-09', '', '', '');
INSERT INTO `purchase_product` VALUES ('18', '询价单', 'A1904290003', '0', 'SGSDS', 'SGSDS', '三禄刀具', '30', '斤', '100', '3000', '', '0', '0', '0', null, '17', '2019-05-09', '', '', '');
INSERT INTO `purchase_product` VALUES ('19', '询价单', 'A1904290004', '0', 'SGSDS', 'SGSDS', '三禄刀具', '20', '斤', '100', '2000', '', '0', '0', '0', null, '18', '2019-05-09', '', '', '');
INSERT INTO `purchase_product` VALUES ('22', null, null, '1', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', null, '20', '斤', '100', '2000', '', '0', '20', '0', null, '25', '2019-05-09', '', '', '');
INSERT INTO `purchase_product` VALUES ('26', '', '', '1', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', null, '20', '斤', '100', '2000', '', '15', '0', '5', '2019-04-30', '27', '2019-05-09', 'A001', '4楼库位', '四楼');
INSERT INTO `purchase_product` VALUES ('27', '', '', '2', 'HTSEGN532', 'HTSEGN532', null, '30', '斤', '50', '1500', '', '20', '0', '10', '2019-04-30', '27', '2019-05-09', 'A003', '测试库位', '一楼');
INSERT INTO `purchase_product` VALUES ('28', '', '', '3', 'SGSDS', 'SGSDS', null, '40', '斤', '100', '4000', '', '30', '0', '10', '2019-04-30', '27', '2019-05-09', '', '', '');
INSERT INTO `purchase_product` VALUES ('30', '', '', '1', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', null, '100', '斤', '100', '10000', '', '0', '100', '0', null, '29', '2019-05-13', 'A002', 'A002', '五楼');
INSERT INTO `purchase_product` VALUES ('31', '', '', '1', 'HTSEGN532', 'HTSEGN532', null, '200', '斤', '30', '6000', '', '0', '200', '0', null, '30', '2019-05-14', 'A004', 'A002', '五楼');
INSERT INTO `purchase_product` VALUES ('32', '', '', '1', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', null, '100', '斤', '100', '10000', '', '50', '0', '50', '2019-05-04', '31', '2019-05-14', '', '', '');
INSERT INTO `purchase_product` VALUES ('33', '', '', '1', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', null, '300', '斤', '100', '30000', '', '100', '200', '0', '2019-05-05', '32', '2019-05-15', 'A002', 'A002', '五楼');
INSERT INTO `purchase_product` VALUES ('34', '', '', '2', 'HTSEGN532', 'HTSEGN532', null, '300', '斤', '0', '0', '', '100', '200', '0', '2019-05-05', '32', '2019-05-15', 'A002', 'A002', '五楼');
INSERT INTO `purchase_product` VALUES ('35', '', '', '1', 'KANDAK 16LGH89', 'KANDAK 16LGH89', null, '300', '斤', '25', '7500', '测试', '0', '300', '0', null, '33', '2019-05-15', '', '', '');
INSERT INTO `purchase_product` VALUES ('36', '', '', '2', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', null, '100', '斤', '100', '10000', '', '0', '100', '0', null, '33', '2019-05-15', 'A002', 'A002', '五楼');
INSERT INTO `purchase_product` VALUES ('37', '', '', '1', 'KANDAK 16LGH89', 'KANDAK 16LGH89', null, '200', '斤', '25', '5000', '测试', '0', '200', '0', null, '34', '2019-05-15', 'A002', 'A002', '五楼');
INSERT INTO `purchase_product` VALUES ('38', '', '', '1', 'KANDAK 16LGH89', 'KANDAK 16LGH89', null, '80', '斤', '25', '2000', '测试', '0', '80', '0', null, '35', '2019-05-16', 'A003', '测试库位', '一楼');
INSERT INTO `purchase_product` VALUES ('39', '', '', '2', 'SGSDS', 'SGSDS', null, '80', '斤', '100', '8000', '', '0', '80', '0', null, '35', '2019-05-16', 'A002', 'A002', '五楼');
INSERT INTO `purchase_product` VALUES ('40', '', '', '1', 'UCHD-627 JGUDH', 'UCHD-627 JGUDH', null, '30', '斤', '65', '1950', '', '0', '30', '0', null, '36', '2019-05-17', '', '', '');
INSERT INTO `purchase_product` VALUES ('41', '', '', '1', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', null, '500', '斤', '30', '15000', '', '200', '100', '100', '2019-05-07', '37', '2019-05-17', 'A002', 'A002', '五楼');

-- ----------------------------
-- Table structure for purchase_stock
-- ----------------------------
DROP TABLE IF EXISTS `purchase_stock`;
CREATE TABLE `purchase_stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdate` date DEFAULT NULL COMMENT '制单日期',
  `stockorder` varchar(255) DEFAULT '' COMMENT '入库单号',
  `depotnum` varchar(255) DEFAULT '' COMMENT '入库仓库编号',
  `depotname` varchar(255) DEFAULT '' COMMENT '入库仓库名称',
  `depotfloor` varchar(255) DEFAULT '' COMMENT '入库仓库楼层',
  `suppliernum` varchar(255) DEFAULT '' COMMENT '供应商编号',
  `suppliername` varchar(255) DEFAULT '' COMMENT '供应商名称',
  `boxnum` varchar(255) DEFAULT '' COMMENT '装箱单号',
  `inspectnum` varchar(255) DEFAULT '' COMMENT '质检人编号',
  `inspectname` varchar(255) DEFAULT '' COMMENT '质检人名称',
  `createname` varchar(255) DEFAULT '' COMMENT '制单人',
  `shpeople` varchar(255) DEFAULT '' COMMENT '审核人',
  `shdate` varchar(255) DEFAULT '' COMMENT '审核日期',
  `updatepeople` varchar(255) DEFAULT '' COMMENT '最后修改人',
  `updatedate` varchar(255) DEFAULT '' COMMENT '最后修改日期',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `shstatus` int(11) DEFAULT '0' COMMENT '审核状态  0 、 默认   1、已审核',
  `invoiceno` varchar(255) DEFAULT NULL COMMENT '发票号码',
  `invoicedate` varchar(255) DEFAULT NULL COMMENT '开票日期',
  `cost` tinyint(1) DEFAULT '0' COMMENT '成本核算',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of purchase_stock
-- ----------------------------
INSERT INTO `purchase_stock` VALUES ('11', '2019-04-28', 'A1904280001', '309', 'A区仓库', '', 'A001', '上海三禄贸易刀具', '66666666', 'A004', '仓库测试人员', '采购测试人员', '仓库测试人员', '2019-04-28', '', '', '', '1', '13123123', '2019-04-28', '0', '2019-05-05 21:50:44');
INSERT INTO `purchase_stock` VALUES ('17', '2019-04-29', 'A1904290004', '310', 'B区仓库', '', 'A001', '上海三禄贸易刀具', '', 'A004', '仓库测试人员', 'Administrator', '', '', '', '', '', '0', null, null, '0', '2019-05-05 21:50:39');
INSERT INTO `purchase_stock` VALUES ('18', '2019-04-29', 'A1904290005', '310', 'B区仓库', '', 'A001', '上海三禄贸易刀具', '', 'A004', '仓库测试人员', 'Administrator', '', '', '', '', '', '0', null, null, '0', '2019-05-05 21:50:35');
INSERT INTO `purchase_stock` VALUES ('19', '2019-04-29', 'A1904290006', '310', 'B区仓库', '', 'A001', '上海三禄贸易刀具', '', 'A004', '仓库测试人员', 'Administrator', 'Administrator', '2019-04-29', '', '', '', '1', null, null, '0', '2019-05-05 21:50:37');
INSERT INTO `purchase_stock` VALUES ('20', '2019-04-29', 'A1904290007', '309', 'A区仓库', '', 'A001', '上海三禄贸易刀具', '', 'A004', '仓库测试人员', 'Administrator', 'Administrator', '2019-04-29', '', '', '', '1', null, null, '0', '2019-05-05 21:50:32');
INSERT INTO `purchase_stock` VALUES ('21', '2019-04-29', 'A1904290008', '310', 'B区仓库', '', 'A001', '上海三禄贸易刀具', '', 'A003', '采购测试人员', 'Administrator', 'Administrator', '2019-04-29', '', '', '', '1', null, null, '0', '2019-05-05 21:50:31');
INSERT INTO `purchase_stock` VALUES ('22', '2019-04-29', 'A1904290009', '325', 'C区', '', 'A001', '上海三禄贸易刀具', '', 'A004', '仓库测试人员', 'Administrator', 'Administrator', '2019-04-29', '', '', '', '1', null, null, '0', '2019-05-05 21:50:28');
INSERT INTO `purchase_stock` VALUES ('32', '2019-05-03', 'A1905030001', '310', 'B区仓库', '', 'A001', '上海三禄贸易刀具', '67676767', 'A004', '仓库测试人员', 'Administrator', 'Administrator', '2019-05-04', '', '', '', '1', null, null, '0', '2019-05-05 21:50:25');
INSERT INTO `purchase_stock` VALUES ('33', '2019-05-04', 'A1905040001', '310', 'B区仓库', '', 'A001', '上海三禄贸易刀具', '67676767', 'A004', '仓库测试人员', 'Administrator', 'Administrator', '2019-05-04', '', '', '', '1', null, null, '0', '2019-05-05 21:50:23');
INSERT INTO `purchase_stock` VALUES ('34', '2019-05-04', 'A1905040002', '310', 'B区仓库', '', 'A001', '上海三禄贸易刀具', '', 'A004', '仓库测试人员', 'Administrator', 'Administrator', '2019-05-04', '', '', '', '1', null, null, '0', '2019-05-05 21:50:16');
INSERT INTO `purchase_stock` VALUES ('37', '2019-05-04', 'A1905040003', '309', 'A区仓库', '', 'A001', '上海三禄贸易刀具', '', 'A004', '仓库测试人员', 'Administrator', 'Administrator', '2019-05-04', '', '', '', '1', null, null, '0', '2019-05-05 21:50:20');
INSERT INTO `purchase_stock` VALUES ('42', '2019-05-05', 'A1905050001', '309', '', '', 'A001', '上海三禄贸易刀具', '44444321', 'A004', '仓库测试人员', 'Administrator', 'Administrator', '2019-05-05', '', '', '', '1', null, null, '0', null);
INSERT INTO `purchase_stock` VALUES ('43', '2019-05-05', 'A1905050002', '309', '', '', 'A001', '上海三禄贸易刀具', '44444321', 'A004', '仓库测试人员', 'Administrator', 'Administrator', '2019-05-05', '', '', '', '1', null, null, '0', null);
INSERT INTO `purchase_stock` VALUES ('44', '2019-05-05', 'A1905050003', '309', '', '', 'A001', '上海三禄贸易刀具', '', 'A004', '仓库测试人员', 'Administrator', 'Administrator', '2019-05-05', '', '', '', '1', null, null, '0', null);
INSERT INTO `purchase_stock` VALUES ('45', '2019-05-05', 'A1905050004', '309', '', '', 'A002', '测试', '', 'A004', '仓库测试人员', 'Administrator', 'Administrator', '2019-05-05', '', '', '', '1', null, null, '0', null);
INSERT INTO `purchase_stock` VALUES ('46', '2019-05-05', 'A1905050005', '309', '', '', 'A002', '测试', '', 'A004', '仓库测试人员', 'Administrator', 'Administrator', '2019-05-05', '', '', '', '1', null, null, '0', null);
INSERT INTO `purchase_stock` VALUES ('47', '2019-05-06', 'A1905060001', '309', '', '', 'A001', '上海三禄贸易刀具', '', 'A005', '仓库测试人员', 'Administrator', 'Administrator', '2019-05-08', 'Administrator', '2019-05-08', '', '1', null, null, '0', '2019-05-06 09:36:17');
INSERT INTO `purchase_stock` VALUES ('48', '2019-05-07', 'A1905070001', '309', '', '', 'A001', '上海三禄贸易刀具', '', 'A004', '仓库测试人员', 'Administrator', 'Administrator', '2019-05-07', 'Administrator', '2019-05-07', '', '1', null, null, '0', '2019-05-07 13:09:50');
INSERT INTO `purchase_stock` VALUES ('49', '2019-05-07', 'A1905070002', '309', '', '', 'A001', '上海三禄贸易刀具', '', 'A004', '仓库测试人员', 'Administrator', 'Administrator', '2019-05-07', '', '', '', '1', null, null, '0', '2019-05-07 13:52:13');

-- ----------------------------
-- Table structure for purchase_stock_product
-- ----------------------------
DROP TABLE IF EXISTS `purchase_stock_product`;
CREATE TABLE `purchase_stock_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sourseorder` varchar(255) DEFAULT '' COMMENT '来源单据',
  `orders` varchar(255) DEFAULT '' COMMENT '单号',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `seeorder` varchar(255) DEFAULT '' COMMENT '参考单号',
  `pronum` varchar(255) DEFAULT '' COMMENT '产品名称',
  `proname` varchar(255) DEFAULT '' COMMENT '产品名称',
  `stocknum` int(11) DEFAULT '0' COMMENT '入库数量',
  `price` double(22,2) DEFAULT '0.00' COMMENT '单价',
  `units` varchar(255) DEFAULT '' COMMENT '单位',
  `depotnum` varchar(255) DEFAULT '' COMMENT '产库编号',
  `depotname` varchar(255) DEFAULT '' COMMENT '仓库名称',
  `floor` varchar(255) DEFAULT '' COMMENT '楼层',
  `product_boxnum` varchar(255) DEFAULT '' COMMENT '箱号',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `purchasestockid` bigint(20) DEFAULT '0' COMMENT '入库单编号',
  `outnum` int(11) DEFAULT '0' COMMENT '出库数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of purchase_stock_product
-- ----------------------------
INSERT INTO `purchase_stock_product` VALUES ('12', '在途库存', '66666666', '0', 'A1904280001', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', '100', '60.00', '斤', 'A001', '4楼库位', '四楼', '3432555', '', '11', '0');
INSERT INTO `purchase_stock_product` VALUES ('14', '采购订单', 'A1904290002', '0', 'A1904290002', 'HTSEGN532', 'HTSEGN532', '30', '0.00', '斤', 'A002', '5楼库位', '五楼', '88653', '', '19', '0');
INSERT INTO `purchase_stock_product` VALUES ('15', '采购订单', 'A1904290004', '0', 'A1904290003', 'SGSDS', 'SGSDS', '30', '100.00', '斤', 'A001', '4楼库位', '四楼', '35677', '', '20', '0');
INSERT INTO `purchase_stock_product` VALUES ('16', '采购订单', 'A1904290005', '0', 'A1904290004', 'SGSDS', 'SGSDS', '30', '100.00', '斤', 'A002', '5楼库位', '五楼', '255534', '', '21', '0');
INSERT INTO `purchase_stock_product` VALUES ('17', '采购订单', 'A1904290006', '1', '', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', '20', '100.00', '斤', 'A003', '测试库位', '一楼', '412344', '', '22', '0');
INSERT INTO `purchase_stock_product` VALUES ('19', '在途库存', '67676767', '1', '', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', '50', '100.00', '斤', 'A002', 'A002', '五楼', '4123123', '', '32', '0');
INSERT INTO `purchase_stock_product` VALUES ('20', '在途库存', '67676767', '1', '', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', '50', '100.00', '斤', 'A002', 'A002', '五楼', '234123', '', '33', '0');
INSERT INTO `purchase_stock_product` VALUES ('21', '采购订单', 'A1904290006', '1', '', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', '20', '100.00', '斤', 'A002', 'A002', '五楼', '123123', '', '34', '0');
INSERT INTO `purchase_stock_product` VALUES ('22', '采购订单', 'A1905040001', '1', '', 'HTSEGN532', 'HTSEGN532', '200', '30.00', '斤', 'A004', 'A002', '五楼', '4123', '', '37', '0');
INSERT INTO `purchase_stock_product` VALUES ('24', '在途库存', '44444321', '1', '', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', '50', '100.00', '斤', 'A002', 'A002', '五楼', '4124555', '', '42', '0');
INSERT INTO `purchase_stock_product` VALUES ('25', '在途库存', '44444321', '2', '', 'HTSEGN532', 'HTSEGN532', '50', '0.00', '斤', 'A002', 'A002', '五楼', '4123', '', '42', '0');
INSERT INTO `purchase_stock_product` VALUES ('26', '在途库存', '44444321', '1', '', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', '50', '100.00', '斤', 'A002', 'A002', '五楼', '412312', '', '43', '0');
INSERT INTO `purchase_stock_product` VALUES ('27', '在途库存', '44444321', '2', '', 'HTSEGN532', 'HTSEGN532', '50', '0.00', '斤', 'A002', 'A002', '五楼', '4124', '', '43', '0');
INSERT INTO `purchase_stock_product` VALUES ('28', '采购订单', 'A1905050001', '1', '', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', '100', '100.00', '斤', 'A002', 'A002', '五楼', '33213', '', '44', '0');
INSERT INTO `purchase_stock_product` VALUES ('29', '采购订单', 'A1905050001', '2', '', 'HTSEGN532', 'HTSEGN532', '100', '0.00', '斤', 'A002', 'A002', '五楼', '444123', '', '44', '0');
INSERT INTO `purchase_stock_product` VALUES ('30', '采购订单', 'A1905050002', '1', '', 'KANDAK 16LGH89', 'KANDAK 16LGH89', '300', '25.00', '斤', 'A002', 'A002', '五楼', '364634', '测试', '45', '0');
INSERT INTO `purchase_stock_product` VALUES ('31', '采购订单', 'A1905050002', '2', '', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', '100', '100.00', '斤', 'A002', 'A002', '五楼', '1361345', '', '45', '0');
INSERT INTO `purchase_stock_product` VALUES ('32', '采购订单', 'A1905050003', '1', '', 'KANDAK 16LGH89', 'KANDAK 16LGH89', '200', '25.00', '斤', 'A003', '测试库位', '一楼', '61345345', '测试', '46', '0');
INSERT INTO `purchase_stock_product` VALUES ('33', '采购订单', 'A1905060001', '1', '', 'KANDAK 16LGH89', 'KANDAK 16LGH89', '80', '25.00', '斤', 'A003', '测试库位', '一楼', '6145435', '测试', '47', '0');
INSERT INTO `purchase_stock_product` VALUES ('34', '采购订单', 'A1905060001', '2', '', 'SGSDS', 'SGSDS', '80', '100.00', '斤', 'A002', 'A002', '五楼', '6145566', '', '47', '0');
INSERT INTO `purchase_stock_product` VALUES ('35', '采购订单', 'A1905070001', '1', '', 'UCHD-627 JGUDH', 'UCHD-627 JGUDH', '30', '65.00', '斤', 'A008', 'A008', '四楼', '43534566', '', '48', '0');
INSERT INTO `purchase_stock_product` VALUES ('36', '采购订单', 'A1905070002', '1', '', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', '100', '30.00', '斤', 'A002', 'A002', '五楼', '435666', '', '49', '0');

-- ----------------------------
-- Table structure for region_basic
-- ----------------------------
DROP TABLE IF EXISTS `region_basic`;
CREATE TABLE `region_basic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `isnum` varchar(255) DEFAULT '' COMMENT '区域编码',
  `area` varchar(255) DEFAULT '' COMMENT '区域设定',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of region_basic
-- ----------------------------
INSERT INTO `region_basic` VALUES ('8', '98227361', '华东地区');

-- ----------------------------
-- Table structure for region_employee
-- ----------------------------
DROP TABLE IF EXISTS `region_employee`;
CREATE TABLE `region_employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `regionid` bigint(20) DEFAULT '0' COMMENT '区域编号',
  `empisnum` varchar(255) DEFAULT '' COMMENT '员工编号',
  `empname` varchar(255) DEFAULT '' COMMENT '员工姓名',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of region_employee
-- ----------------------------
INSERT INTO `region_employee` VALUES ('13', '8', 'A003', '采购测试人员', '测试人员');

-- ----------------------------
-- Table structure for relation
-- ----------------------------
DROP TABLE IF EXISTS `relation`;
CREATE TABLE `relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `relation_name` varchar(255) DEFAULT '' COMMENT '关联单据名称',
  `relation_id` bigint(20) DEFAULT '0' COMMENT '关联单据编号',
  `be_relation_name` varchar(255) DEFAULT '' COMMENT '被关联单据名称',
  `be_relation_id` bigint(20) DEFAULT '0' COMMENT '被关联单据编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8 COMMENT='单据关联关系表';

-- ----------------------------
-- Records of relation
-- ----------------------------
INSERT INTO `relation` VALUES ('36', '采购入库单', '37', '采购订单', '30');
INSERT INTO `relation` VALUES ('40', '采购入库单', '41', '采购订单', '31');
INSERT INTO `relation` VALUES ('52', '销货单', '33', '订货单', '28');
INSERT INTO `relation` VALUES ('56', '采购入库单', '22', '采购订单', '25');
INSERT INTO `relation` VALUES ('57', '询价单', '13', '报价单', '20');
INSERT INTO `relation` VALUES ('59', '在途库存', '12', '采购订单', '27');
INSERT INTO `relation` VALUES ('67', '在途库存', '19', '采购订单', '29');
INSERT INTO `relation` VALUES ('68', '在途库存', '20', '采购订单', '29');
INSERT INTO `relation` VALUES ('69', '采购入库单', '32', '在途库存', '20');
INSERT INTO `relation` VALUES ('70', '采购入库单', '33', '在途库存', '20');
INSERT INTO `relation` VALUES ('71', '采购入库单', '34', '采购订单', '25');
INSERT INTO `relation` VALUES ('72', '采购入库单', '36', '采购订单', '30');
INSERT INTO `relation` VALUES ('75', '采购入库单', '40', '采购订单', '31');
INSERT INTO `relation` VALUES ('76', '在途库存', '22', '采购订单', '31');
INSERT INTO `relation` VALUES ('77', '在途库存', '23', '采购订单', '32');
INSERT INTO `relation` VALUES ('78', '采购入库单', '42', '在途库存', '23');
INSERT INTO `relation` VALUES ('79', '采购入库单', '43', '在途库存', '23');
INSERT INTO `relation` VALUES ('80', '采购入库单', '44', '采购订单', '32');
INSERT INTO `relation` VALUES ('81', '采购入库单', '45', '采购订单', '33');
INSERT INTO `relation` VALUES ('82', '采购入库单', '46', '采购订单', '34');
INSERT INTO `relation` VALUES ('83', '库存异动作业', '15', '盘库作业', '6');
INSERT INTO `relation` VALUES ('84', '采购入库单', '47', '采购订单', '35');
INSERT INTO `relation` VALUES ('85', '采购入库单', '48', '采购订单', '36');
INSERT INTO `relation` VALUES ('86', '采购入库单', '49', '采购订单', '37');
INSERT INTO `relation` VALUES ('87', '在途库存', '24', '采购订单', '37');

-- ----------------------------
-- Table structure for remark
-- ----------------------------
DROP TABLE IF EXISTS `remark`;
CREATE TABLE `remark` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '备注及说明',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注及说明',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  `typeid` int(11) DEFAULT NULL COMMENT '类型（1、客户基本资料，2、报价单，3、订货单，4、销货单，5、销货出库单）',
  `otherid` bigint(20) DEFAULT NULL COMMENT '外键id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of remark
-- ----------------------------

-- ----------------------------
-- Table structure for report_remark
-- ----------------------------
DROP TABLE IF EXISTS `report_remark`;
CREATE TABLE `report_remark` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '报表备注',
  `content` varchar(500) DEFAULT NULL COMMENT '正文',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  `typeid` int(11) DEFAULT NULL COMMENT '类型（1、报价单，2、订货单，3、销货单，4、销货出库单）',
  `otherid` bigint(20) DEFAULT NULL COMMENT '外键id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of report_remark
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(255) DEFAULT '' COMMENT '角色名称',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('1', '经理', '2019-02-27 17:17:03');
INSERT INTO `roles` VALUES ('2', '业务', '2019-02-27 17:17:15');
INSERT INTO `roles` VALUES ('4', '系统管理员', '2019-02-27 17:17:43');
INSERT INTO `roles` VALUES ('5', '财务', '2019-02-27 17:17:51');
INSERT INTO `roles` VALUES ('6', '业助', '2019-02-27 17:18:02');
INSERT INTO `roles` VALUES ('7', '仓库', '2019-02-27 17:18:23');
INSERT INTO `roles` VALUES ('8', '快递', '2019-02-27 17:18:32');
INSERT INTO `roles` VALUES ('9', '采购', '2019-04-28 22:01:50');

-- ----------------------------
-- Table structure for role_employee
-- ----------------------------
DROP TABLE IF EXISTS `role_employee`;
CREATE TABLE `role_employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) DEFAULT '0' COMMENT '用户id',
  `idnum` varchar(255) DEFAULT '' COMMENT '用户编号',
  `roleid` bigint(20) DEFAULT '0' COMMENT '角色id',
  `rolename` varchar(255) DEFAULT '' COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_employee
-- ----------------------------
INSERT INTO `role_employee` VALUES ('7', '1', 'A001', '4', '系统管理员');
INSERT INTO `role_employee` VALUES ('33', '10', 'A004', '7', '仓库');
INSERT INTO `role_employee` VALUES ('34', '11', 'A005', '5', '财务');
INSERT INTO `role_employee` VALUES ('35', '8', 'A002', '2', '业务');
INSERT INTO `role_employee` VALUES ('36', '9', 'A003', '9', '采购');

-- ----------------------------
-- Table structure for role_permissions
-- ----------------------------
DROP TABLE IF EXISTS `role_permissions`;
CREATE TABLE `role_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `roleid` bigint(20) DEFAULT '0' COMMENT '角色编号',
  `rolename` varchar(255) DEFAULT '' COMMENT '角色名称',
  `permissionscodes` varchar(255) DEFAULT '' COMMENT '权限编码',
  `permissionsid` bigint(20) DEFAULT '0' COMMENT '权限id',
  `uid` bigint(20) DEFAULT '0' COMMENT '用户编号',
  `idnum` varchar(255) DEFAULT '' COMMENT '用户唯一编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1087 DEFAULT CHARSET=utf8 COMMENT='角色-权限';

-- ----------------------------
-- Records of role_permissions
-- ----------------------------
INSERT INTO `role_permissions` VALUES ('333', '4', '系统管理员', '1_102_3', '102', null, null);
INSERT INTO `role_permissions` VALUES ('334', '4', '系统管理员', '1_103_4', '103', null, null);
INSERT INTO `role_permissions` VALUES ('335', '4', '系统管理员', '1_104_7', '104', null, null);
INSERT INTO `role_permissions` VALUES ('336', '4', '系统管理员', '1_105_8', '105', null, null);
INSERT INTO `role_permissions` VALUES ('337', '4', '系统管理员', '2_106_1', '106', null, null);
INSERT INTO `role_permissions` VALUES ('338', '4', '系统管理员', '2_107_2', '107', null, null);
INSERT INTO `role_permissions` VALUES ('339', '4', '系统管理员', '2_108_3', '108', null, null);
INSERT INTO `role_permissions` VALUES ('340', '4', '系统管理员', '2_109_4', '109', null, null);
INSERT INTO `role_permissions` VALUES ('341', '4', '系统管理员', '2_110_5', '110', null, null);
INSERT INTO `role_permissions` VALUES ('342', '4', '系统管理员', '2_111_6', '111', null, null);
INSERT INTO `role_permissions` VALUES ('343', '4', '系统管理员', '2_112_7', '112', null, null);
INSERT INTO `role_permissions` VALUES ('344', '4', '系统管理员', '2_113_8', '113', null, null);
INSERT INTO `role_permissions` VALUES ('345', '4', '系统管理员', '3_105_4', '115', null, null);
INSERT INTO `role_permissions` VALUES ('346', '4', '系统管理员', '3_116_7', '116', null, null);
INSERT INTO `role_permissions` VALUES ('347', '4', '系统管理员', '3_117_8', '117', null, null);
INSERT INTO `role_permissions` VALUES ('348', '4', '系统管理员', '4_118_1', '118', null, null);
INSERT INTO `role_permissions` VALUES ('349', '4', '系统管理员', '4_119_2', '119', null, null);
INSERT INTO `role_permissions` VALUES ('350', '4', '系统管理员', '4_120_3', '120', null, null);
INSERT INTO `role_permissions` VALUES ('351', '4', '系统管理员', '4_121_4', '121', null, null);
INSERT INTO `role_permissions` VALUES ('352', '4', '系统管理员', '4_123_7', '123', null, null);
INSERT INTO `role_permissions` VALUES ('353', '4', '系统管理员', '4_122_8', '122', null, null);
INSERT INTO `role_permissions` VALUES ('354', '4', '系统管理员', '5_124_1', '124', null, null);
INSERT INTO `role_permissions` VALUES ('355', '4', '系统管理员', '5_125_3', '125', null, null);
INSERT INTO `role_permissions` VALUES ('356', '4', '系统管理员', '5_126_4', '126', null, null);
INSERT INTO `role_permissions` VALUES ('357', '4', '系统管理员', '5_127_8', '127', null, null);
INSERT INTO `role_permissions` VALUES ('358', '4', '系统管理员', '6_128_3', '128', null, null);
INSERT INTO `role_permissions` VALUES ('359', '4', '系统管理员', '6_129_4', '129', null, null);
INSERT INTO `role_permissions` VALUES ('360', '4', '系统管理员', '6_130_5', '130', null, null);
INSERT INTO `role_permissions` VALUES ('361', '4', '系统管理员', '6_131_8', '131', null, null);
INSERT INTO `role_permissions` VALUES ('362', '4', '系统管理员', '7_132_1', '132', null, null);
INSERT INTO `role_permissions` VALUES ('363', '4', '系统管理员', '7_133_2', '133', null, null);
INSERT INTO `role_permissions` VALUES ('364', '4', '系统管理员', '7_134_3', '134', null, null);
INSERT INTO `role_permissions` VALUES ('365', '4', '系统管理员', '7_135_4', '135', null, null);
INSERT INTO `role_permissions` VALUES ('366', '4', '系统管理员', '7_136_7', '136', null, null);
INSERT INTO `role_permissions` VALUES ('367', '4', '系统管理员', '7_137_8', '137', null, null);
INSERT INTO `role_permissions` VALUES ('369', '4', '系统管理员', '9_139_1', '139', null, null);
INSERT INTO `role_permissions` VALUES ('370', '4', '系统管理员', '9_140_2', '140', null, null);
INSERT INTO `role_permissions` VALUES ('371', '4', '系统管理员', '9_141_3', '141', null, null);
INSERT INTO `role_permissions` VALUES ('372', '4', '系统管理员', '9_142_4', '142', null, null);
INSERT INTO `role_permissions` VALUES ('373', '4', '系统管理员', '9_143_5', '143', null, null);
INSERT INTO `role_permissions` VALUES ('374', '4', '系统管理员', '9_145_6', '145', null, null);
INSERT INTO `role_permissions` VALUES ('375', '4', '系统管理员', '9_146_7', '146', null, null);
INSERT INTO `role_permissions` VALUES ('376', '4', '系统管理员', '9_147_8', '147', null, null);
INSERT INTO `role_permissions` VALUES ('377', '4', '系统管理员', '10_148_4', '148', null, null);
INSERT INTO `role_permissions` VALUES ('378', '4', '系统管理员', '10_149_7', '149', null, null);
INSERT INTO `role_permissions` VALUES ('379', '4', '系统管理员', '10_150_8', '150', null, null);
INSERT INTO `role_permissions` VALUES ('380', '4', '系统管理员', '11_151_1', '151', null, null);
INSERT INTO `role_permissions` VALUES ('381', '4', '系统管理员', '11_152_4', '152', null, null);
INSERT INTO `role_permissions` VALUES ('382', '4', '系统管理员', '11_153_7', '153', null, null);
INSERT INTO `role_permissions` VALUES ('383', '4', '系统管理员', '11_154_8', '154', null, null);
INSERT INTO `role_permissions` VALUES ('384', '4', '系统管理员', '12_155_4', '155', null, null);
INSERT INTO `role_permissions` VALUES ('385', '4', '系统管理员', '12_156_7', '156', null, null);
INSERT INTO `role_permissions` VALUES ('386', '4', '系统管理员', '12_157_8', '157', null, null);
INSERT INTO `role_permissions` VALUES ('387', '4', '系统管理员', '13_158_1', '158', null, null);
INSERT INTO `role_permissions` VALUES ('388', '4', '系统管理员', '13_159_2', '159', null, null);
INSERT INTO `role_permissions` VALUES ('389', '4', '系统管理员', '13_160_3', '160', null, null);
INSERT INTO `role_permissions` VALUES ('390', '4', '系统管理员', '13_161_4', '161', null, null);
INSERT INTO `role_permissions` VALUES ('391', '4', '系统管理员', '13_162_7', '162', null, null);
INSERT INTO `role_permissions` VALUES ('392', '4', '系统管理员', '13_163_8', '163', null, null);
INSERT INTO `role_permissions` VALUES ('393', '4', '系统管理员', '14_164_4', '164', null, null);
INSERT INTO `role_permissions` VALUES ('394', '4', '系统管理员', '14_165_7', '165', null, null);
INSERT INTO `role_permissions` VALUES ('395', '4', '系统管理员', '14_166_8', '166', null, null);
INSERT INTO `role_permissions` VALUES ('396', '4', '系统管理员', '15_167_1', '167', null, null);
INSERT INTO `role_permissions` VALUES ('397', '4', '系统管理员', '15_168_2', '168', null, null);
INSERT INTO `role_permissions` VALUES ('398', '4', '系统管理员', '15_169_3', '169', null, null);
INSERT INTO `role_permissions` VALUES ('399', '4', '系统管理员', '15_170_4', '170', null, null);
INSERT INTO `role_permissions` VALUES ('400', '4', '系统管理员', '15_171_5', '171', null, null);
INSERT INTO `role_permissions` VALUES ('401', '4', '系统管理员', '15_172_6', '172', null, null);
INSERT INTO `role_permissions` VALUES ('402', '4', '系统管理员', '15_173_7', '173', null, null);
INSERT INTO `role_permissions` VALUES ('403', '4', '系统管理员', '15_174_8', '174', null, null);
INSERT INTO `role_permissions` VALUES ('404', '4', '系统管理员', '16_175_3', '175', null, null);
INSERT INTO `role_permissions` VALUES ('405', '4', '系统管理员', '16_176_4', '176', null, null);
INSERT INTO `role_permissions` VALUES ('406', '4', '系统管理员', '17_177_1', '177', null, null);
INSERT INTO `role_permissions` VALUES ('407', '4', '系统管理员', '17_178_4', '178', null, null);
INSERT INTO `role_permissions` VALUES ('408', '4', '系统管理员', '17_179_7', '179', null, null);
INSERT INTO `role_permissions` VALUES ('409', '4', '系统管理员', '17_180_8', '180', null, null);
INSERT INTO `role_permissions` VALUES ('410', '4', '系统管理员', '18_181_2', '181', null, null);
INSERT INTO `role_permissions` VALUES ('411', '4', '系统管理员', '18_182_3', '182', null, null);
INSERT INTO `role_permissions` VALUES ('412', '4', '系统管理员', '18_183_4', '183', null, null);
INSERT INTO `role_permissions` VALUES ('413', '4', '系统管理员', '18_184_5', '184', null, null);
INSERT INTO `role_permissions` VALUES ('414', '4', '系统管理员', '18_185_7', '185', null, null);
INSERT INTO `role_permissions` VALUES ('415', '4', '系统管理员', '18_186_8', '186', null, null);
INSERT INTO `role_permissions` VALUES ('416', '4', '系统管理员', '19_187_1', '187', null, null);
INSERT INTO `role_permissions` VALUES ('417', '4', '系统管理员', '19_188_2', '188', null, null);
INSERT INTO `role_permissions` VALUES ('418', '4', '系统管理员', '19_189_3', '189', null, null);
INSERT INTO `role_permissions` VALUES ('419', '4', '系统管理员', '19_190_4', '190', null, null);
INSERT INTO `role_permissions` VALUES ('420', '4', '系统管理员', '19_191_7', '191', null, null);
INSERT INTO `role_permissions` VALUES ('421', '4', '系统管理员', '19_192_8', '192', null, null);
INSERT INTO `role_permissions` VALUES ('422', '4', '系统管理员', '20_193_3', '193', null, null);
INSERT INTO `role_permissions` VALUES ('423', '4', '系统管理员', '21_194_3', '194', null, null);
INSERT INTO `role_permissions` VALUES ('424', '4', '系统管理员', '22_195_3', '195', null, null);
INSERT INTO `role_permissions` VALUES ('426', '4', '系统管理员', '24_197_3', '197', null, null);
INSERT INTO `role_permissions` VALUES ('427', '4', '系统管理员', '25_198_3', '198', null, null);
INSERT INTO `role_permissions` VALUES ('428', '4', '系统管理员', '26_199_3', '199', null, null);
INSERT INTO `role_permissions` VALUES ('429', '4', '系统管理员', '27_200_1', '200', null, null);
INSERT INTO `role_permissions` VALUES ('430', '4', '系统管理员', '27_201_2', '201', null, null);
INSERT INTO `role_permissions` VALUES ('431', '4', '系统管理员', '27_202_3', '202', null, null);
INSERT INTO `role_permissions` VALUES ('432', '4', '系统管理员', '27_203_4', '203', null, null);
INSERT INTO `role_permissions` VALUES ('433', '4', '系统管理员', '27_204_7', '204', null, null);
INSERT INTO `role_permissions` VALUES ('434', '4', '系统管理员', '27_205_8', '205', null, null);
INSERT INTO `role_permissions` VALUES ('435', '4', '系统管理员', '28_206_1', '206', null, null);
INSERT INTO `role_permissions` VALUES ('436', '4', '系统管理员', '28_207_2', '207', null, null);
INSERT INTO `role_permissions` VALUES ('437', '4', '系统管理员', '28_208_3', '208', null, null);
INSERT INTO `role_permissions` VALUES ('438', '4', '系统管理员', '28_209_4', '209', null, null);
INSERT INTO `role_permissions` VALUES ('439', '4', '系统管理员', '28_210_5', '210', null, null);
INSERT INTO `role_permissions` VALUES ('440', '4', '系统管理员', '28_211_6', '211', null, null);
INSERT INTO `role_permissions` VALUES ('441', '4', '系统管理员', '28_212_7', '212', null, null);
INSERT INTO `role_permissions` VALUES ('442', '4', '系统管理员', '28_213_8', '213', null, null);
INSERT INTO `role_permissions` VALUES ('443', '4', '系统管理员', '29_214_3', '214', null, null);
INSERT INTO `role_permissions` VALUES ('444', '4', '系统管理员', '29_215_4', '215', null, null);
INSERT INTO `role_permissions` VALUES ('445', '4', '系统管理员', '30_216_4', '216', null, null);
INSERT INTO `role_permissions` VALUES ('446', '4', '系统管理员', '30_217_7', '217', null, null);
INSERT INTO `role_permissions` VALUES ('447', '4', '系统管理员', '30_218_8', '218', null, null);
INSERT INTO `role_permissions` VALUES ('448', '4', '系统管理员', '31_2169_4', '219', null, null);
INSERT INTO `role_permissions` VALUES ('449', '4', '系统管理员', '31_230_7', '230', null, null);
INSERT INTO `role_permissions` VALUES ('450', '4', '系统管理员', '31_232_8', '231', null, null);
INSERT INTO `role_permissions` VALUES ('451', '4', '系统管理员', '32_232_1', '232', null, null);
INSERT INTO `role_permissions` VALUES ('452', '4', '系统管理员', '32_233_2', '233', null, null);
INSERT INTO `role_permissions` VALUES ('453', '4', '系统管理员', '32_234_3', '234', null, null);
INSERT INTO `role_permissions` VALUES ('454', '4', '系统管理员', '32_236_5', '236', null, null);
INSERT INTO `role_permissions` VALUES ('455', '4', '系统管理员', '32_237_6', '237', null, null);
INSERT INTO `role_permissions` VALUES ('456', '4', '系统管理员', '32_238_7', '238', null, null);
INSERT INTO `role_permissions` VALUES ('457', '4', '系统管理员', '32_239_8', '239', null, null);
INSERT INTO `role_permissions` VALUES ('458', '4', '系统管理员', '33_242_3', '242', null, null);
INSERT INTO `role_permissions` VALUES ('459', '4', '系统管理员', '33_243_4', '243', null, null);
INSERT INTO `role_permissions` VALUES ('460', '4', '系统管理员', '34_248_3', '248', null, null);
INSERT INTO `role_permissions` VALUES ('461', '4', '系统管理员', '34_249_4', '249', null, null);
INSERT INTO `role_permissions` VALUES ('462', '4', '系统管理员', '35_250_4', '250', null, null);
INSERT INTO `role_permissions` VALUES ('463', '4', '系统管理员', '35_251_7', '251', null, null);
INSERT INTO `role_permissions` VALUES ('464', '4', '系统管理员', '35_252_8', '252', null, null);
INSERT INTO `role_permissions` VALUES ('465', '4', '系统管理员', '36_253_4', '253', null, null);
INSERT INTO `role_permissions` VALUES ('466', '4', '系统管理员', '36_254_7', '254', null, null);
INSERT INTO `role_permissions` VALUES ('467', '4', '系统管理员', '36_255_8', '255', null, null);
INSERT INTO `role_permissions` VALUES ('468', '4', '系统管理员', '37_256_1', '256', null, null);
INSERT INTO `role_permissions` VALUES ('469', '4', '系统管理员', '37_257_2', '257', null, null);
INSERT INTO `role_permissions` VALUES ('470', '4', '系统管理员', '37_258_3', '258', null, null);
INSERT INTO `role_permissions` VALUES ('471', '4', '系统管理员', '37_259_4', '259', null, null);
INSERT INTO `role_permissions` VALUES ('472', '4', '系统管理员', '37_260_5', '260', null, null);
INSERT INTO `role_permissions` VALUES ('473', '4', '系统管理员', '37_261_6', '261', null, null);
INSERT INTO `role_permissions` VALUES ('474', '4', '系统管理员', '37_262_7', '262', null, null);
INSERT INTO `role_permissions` VALUES ('475', '4', '系统管理员', '37_263_8', '263', null, null);
INSERT INTO `role_permissions` VALUES ('476', '4', '系统管理员', '38_264_4', '264', null, null);
INSERT INTO `role_permissions` VALUES ('477', '4', '系统管理员', '38_265_7', '265', null, null);
INSERT INTO `role_permissions` VALUES ('478', '4', '系统管理员', '38_266_8', '266', null, null);
INSERT INTO `role_permissions` VALUES ('479', '4', '系统管理员', '39_267_4', '267', null, null);
INSERT INTO `role_permissions` VALUES ('480', '4', '系统管理员', '39_268_7', '268', null, null);
INSERT INTO `role_permissions` VALUES ('481', '4', '系统管理员', '39_269_8', '269', null, null);
INSERT INTO `role_permissions` VALUES ('482', '4', '系统管理员', '40_270_4', '270', null, null);
INSERT INTO `role_permissions` VALUES ('483', '4', '系统管理员', '40_271_7', '271', null, null);
INSERT INTO `role_permissions` VALUES ('484', '4', '系统管理员', '40_272_8', '272', null, null);
INSERT INTO `role_permissions` VALUES ('485', '4', '系统管理员', '41_273_3', '273', null, null);
INSERT INTO `role_permissions` VALUES ('486', '4', '系统管理员', '41_274_4', '274', null, null);
INSERT INTO `role_permissions` VALUES ('487', '4', '系统管理员', '41_275_7', '275', null, null);
INSERT INTO `role_permissions` VALUES ('488', '4', '系统管理员', '41_276_8', '276', null, null);
INSERT INTO `role_permissions` VALUES ('489', '4', '系统管理员', '42_277_4', '277', null, null);
INSERT INTO `role_permissions` VALUES ('490', '4', '系统管理员', '42_278_7', '278', null, null);
INSERT INTO `role_permissions` VALUES ('491', '4', '系统管理员', '42_279_8', '279', null, null);
INSERT INTO `role_permissions` VALUES ('492', '4', '系统管理员', '43_280_4', '280', null, null);
INSERT INTO `role_permissions` VALUES ('493', '4', '系统管理员', '43_281_7', '281', null, null);
INSERT INTO `role_permissions` VALUES ('494', '4', '系统管理员', '43_282_8', '282', null, null);
INSERT INTO `role_permissions` VALUES ('495', '4', '系统管理员', '44_283_4', '283', null, null);
INSERT INTO `role_permissions` VALUES ('496', '4', '系统管理员', '44_284_7', '284', null, null);
INSERT INTO `role_permissions` VALUES ('497', '4', '系统管理员', '44_285_8', '285', null, null);
INSERT INTO `role_permissions` VALUES ('498', '4', '系统管理员', '45_286_4', '286', null, null);
INSERT INTO `role_permissions` VALUES ('499', '4', '系统管理员', '45_287_7', '287', null, null);
INSERT INTO `role_permissions` VALUES ('500', '4', '系统管理员', '45_288_8', '288', null, null);
INSERT INTO `role_permissions` VALUES ('501', '4', '系统管理员', '46_289_4', '289', null, null);
INSERT INTO `role_permissions` VALUES ('502', '4', '系统管理员', '46_290_7', '290', null, null);
INSERT INTO `role_permissions` VALUES ('503', '4', '系统管理员', '46_291_8', '291', null, null);
INSERT INTO `role_permissions` VALUES ('504', '4', '系统管理员', '47_292_4', '292', null, null);
INSERT INTO `role_permissions` VALUES ('505', '4', '系统管理员', '47_293_7', '293', null, null);
INSERT INTO `role_permissions` VALUES ('506', '4', '系统管理员', '47_294_8', '294', null, null);
INSERT INTO `role_permissions` VALUES ('507', '4', '系统管理员', '49_295_4', '295', null, null);
INSERT INTO `role_permissions` VALUES ('508', '4', '系统管理员', '49_296_7', '296', null, null);
INSERT INTO `role_permissions` VALUES ('509', '4', '系统管理员', '49_297_8', '297', null, null);
INSERT INTO `role_permissions` VALUES ('510', '4', '系统管理员', '50_298_1', '298', null, null);
INSERT INTO `role_permissions` VALUES ('511', '4', '系统管理员', '50_299_2', '299', null, null);
INSERT INTO `role_permissions` VALUES ('512', '4', '系统管理员', '50_300_3', '300', null, null);
INSERT INTO `role_permissions` VALUES ('513', '4', '系统管理员', '50_301_4', '301', null, null);
INSERT INTO `role_permissions` VALUES ('514', '4', '系统管理员', '50_302_5', '302', null, null);
INSERT INTO `role_permissions` VALUES ('515', '4', '系统管理员', '50_303_6', '303', null, null);
INSERT INTO `role_permissions` VALUES ('516', '4', '系统管理员', '50_304_7', '304', null, null);
INSERT INTO `role_permissions` VALUES ('517', '4', '系统管理员', '50_305_8', '305', null, null);
INSERT INTO `role_permissions` VALUES ('518', '4', '系统管理员', '51_306_1', '306', null, null);
INSERT INTO `role_permissions` VALUES ('519', '4', '系统管理员', '51_307_2', '307', null, null);
INSERT INTO `role_permissions` VALUES ('520', '4', '系统管理员', '51_308_3', '308', null, null);
INSERT INTO `role_permissions` VALUES ('521', '4', '系统管理员', '51_309_4', '309', null, null);
INSERT INTO `role_permissions` VALUES ('522', '4', '系统管理员', '51_310_5', '310', null, null);
INSERT INTO `role_permissions` VALUES ('523', '4', '系统管理员', '51_311_6', '311', null, null);
INSERT INTO `role_permissions` VALUES ('524', '4', '系统管理员', '51_312_7', '312', null, null);
INSERT INTO `role_permissions` VALUES ('525', '4', '系统管理员', '51_313_8', '313', null, null);
INSERT INTO `role_permissions` VALUES ('526', '4', '系统管理员', '52_314_1', '314', null, null);
INSERT INTO `role_permissions` VALUES ('527', '4', '系统管理员', '52_315_2', '315', null, null);
INSERT INTO `role_permissions` VALUES ('528', '4', '系统管理员', '52_316_3', '316', null, null);
INSERT INTO `role_permissions` VALUES ('529', '4', '系统管理员', '52_317_4', '317', null, null);
INSERT INTO `role_permissions` VALUES ('530', '4', '系统管理员', '52_318_5', '318', null, null);
INSERT INTO `role_permissions` VALUES ('531', '4', '系统管理员', '52_319_6', '319', null, null);
INSERT INTO `role_permissions` VALUES ('532', '4', '系统管理员', '52_320_7', '320', null, null);
INSERT INTO `role_permissions` VALUES ('533', '4', '系统管理员', '52_321_8', '321', null, null);
INSERT INTO `role_permissions` VALUES ('534', '4', '系统管理员', '53_322_1', '322', null, null);
INSERT INTO `role_permissions` VALUES ('535', '4', '系统管理员', '53_323_2', '323', null, null);
INSERT INTO `role_permissions` VALUES ('536', '4', '系统管理员', '53_324_3', '324', null, null);
INSERT INTO `role_permissions` VALUES ('537', '4', '系统管理员', '53_325_4', '325', null, null);
INSERT INTO `role_permissions` VALUES ('538', '4', '系统管理员', '53_326_5', '326', null, null);
INSERT INTO `role_permissions` VALUES ('539', '4', '系统管理员', '53_327_6', '327', null, null);
INSERT INTO `role_permissions` VALUES ('540', '4', '系统管理员', '53_328_7', '328', null, null);
INSERT INTO `role_permissions` VALUES ('541', '4', '系统管理员', '53_329_8', '329', null, null);
INSERT INTO `role_permissions` VALUES ('542', '4', '系统管理员', '54_330_1', '330', null, null);
INSERT INTO `role_permissions` VALUES ('543', '4', '系统管理员', '54_331_3', '331', null, null);
INSERT INTO `role_permissions` VALUES ('544', '4', '系统管理员', '54_332_4', '332', null, null);
INSERT INTO `role_permissions` VALUES ('545', '4', '系统管理员', '54_333_7', '333', null, null);
INSERT INTO `role_permissions` VALUES ('546', '4', '系统管理员', '54_334_8', '334', null, null);
INSERT INTO `role_permissions` VALUES ('547', '4', '系统管理员', '55_335_1', '335', null, null);
INSERT INTO `role_permissions` VALUES ('548', '4', '系统管理员', '55_336_2', '336', null, null);
INSERT INTO `role_permissions` VALUES ('549', '4', '系统管理员', '55_337_3', '337', null, null);
INSERT INTO `role_permissions` VALUES ('550', '4', '系统管理员', '55_338_4', '338', null, null);
INSERT INTO `role_permissions` VALUES ('551', '4', '系统管理员', '55_339_5', '339', null, null);
INSERT INTO `role_permissions` VALUES ('552', '4', '系统管理员', '55_340_6', '340', null, null);
INSERT INTO `role_permissions` VALUES ('553', '4', '系统管理员', '55_341_7', '341', null, null);
INSERT INTO `role_permissions` VALUES ('554', '4', '系统管理员', '55_342_8', '342', null, null);
INSERT INTO `role_permissions` VALUES ('555', '4', '系统管理员', '56_343_1', '343', null, null);
INSERT INTO `role_permissions` VALUES ('556', '4', '系统管理员', '56_344_2', '344', null, null);
INSERT INTO `role_permissions` VALUES ('557', '4', '系统管理员', '56_345_3', '345', null, null);
INSERT INTO `role_permissions` VALUES ('558', '4', '系统管理员', '56_346_4', '346', null, null);
INSERT INTO `role_permissions` VALUES ('559', '4', '系统管理员', '56_347_5', '347', null, null);
INSERT INTO `role_permissions` VALUES ('560', '4', '系统管理员', '56_348_6', '348', null, null);
INSERT INTO `role_permissions` VALUES ('561', '4', '系统管理员', '56_350_8', '350', null, null);
INSERT INTO `role_permissions` VALUES ('562', '4', '系统管理员', '57_351_1', '351', null, null);
INSERT INTO `role_permissions` VALUES ('563', '4', '系统管理员', '57_352_2', '352', null, null);
INSERT INTO `role_permissions` VALUES ('564', '4', '系统管理员', '57_353_3', '353', null, null);
INSERT INTO `role_permissions` VALUES ('565', '4', '系统管理员', '57_354_4', '354', null, null);
INSERT INTO `role_permissions` VALUES ('566', '4', '系统管理员', '57_355_5', '355', null, null);
INSERT INTO `role_permissions` VALUES ('567', '4', '系统管理员', '57_356_6', '356', null, null);
INSERT INTO `role_permissions` VALUES ('568', '4', '系统管理员', '57_357_7', '357', null, null);
INSERT INTO `role_permissions` VALUES ('569', '4', '系统管理员', '57_358_8', '358', null, null);
INSERT INTO `role_permissions` VALUES ('570', '4', '系统管理员', '58_359_1', '359', null, null);
INSERT INTO `role_permissions` VALUES ('571', '4', '系统管理员', '58_360_2', '360', null, null);
INSERT INTO `role_permissions` VALUES ('572', '4', '系统管理员', '58_361_3', '361', null, null);
INSERT INTO `role_permissions` VALUES ('573', '4', '系统管理员', '58_362_4', '362', null, null);
INSERT INTO `role_permissions` VALUES ('574', '4', '系统管理员', '58_363_5', '363', null, null);
INSERT INTO `role_permissions` VALUES ('575', '4', '系统管理员', '58_364_6', '364', null, null);
INSERT INTO `role_permissions` VALUES ('576', '4', '系统管理员', '58_365_7', '365', null, null);
INSERT INTO `role_permissions` VALUES ('577', '4', '系统管理员', '58_366_8', '366', null, null);
INSERT INTO `role_permissions` VALUES ('578', '4', '系统管理员', '59_367_4', '367', null, null);
INSERT INTO `role_permissions` VALUES ('579', '4', '系统管理员', '59_368_7', '368', null, null);
INSERT INTO `role_permissions` VALUES ('580', '4', '系统管理员', '59_369_8', '369', null, null);
INSERT INTO `role_permissions` VALUES ('581', '4', '系统管理员', '60_370_4', '370', null, null);
INSERT INTO `role_permissions` VALUES ('582', '4', '系统管理员', '60_371_7', '371', null, null);
INSERT INTO `role_permissions` VALUES ('583', '4', '系统管理员', '60_378_8', '372', null, null);
INSERT INTO `role_permissions` VALUES ('584', '4', '系统管理员', '61_373_1', '373', null, null);
INSERT INTO `role_permissions` VALUES ('585', '4', '系统管理员', '61_374_2', '374', null, null);
INSERT INTO `role_permissions` VALUES ('586', '4', '系统管理员', '61_375_3', '375', null, null);
INSERT INTO `role_permissions` VALUES ('587', '4', '系统管理员', '61_376_4', '376', null, null);
INSERT INTO `role_permissions` VALUES ('588', '4', '系统管理员', '61_377_5', '377', null, null);
INSERT INTO `role_permissions` VALUES ('589', '4', '系统管理员', '61_378_6', '378', null, null);
INSERT INTO `role_permissions` VALUES ('590', '4', '系统管理员', '61_379_7', '379', null, null);
INSERT INTO `role_permissions` VALUES ('591', '4', '系统管理员', '61_380_8', '380', null, null);
INSERT INTO `role_permissions` VALUES ('592', '4', '系统管理员', '62_381_4', '381', null, null);
INSERT INTO `role_permissions` VALUES ('593', '4', '系统管理员', '63_382_1', '382', null, null);
INSERT INTO `role_permissions` VALUES ('594', '4', '系统管理员', '63_383_2', '383', null, null);
INSERT INTO `role_permissions` VALUES ('595', '4', '系统管理员', '63_384_3', '384', null, null);
INSERT INTO `role_permissions` VALUES ('596', '4', '系统管理员', '63_385_4', '385', null, null);
INSERT INTO `role_permissions` VALUES ('597', '4', '系统管理员', '63_386_5', '386', null, null);
INSERT INTO `role_permissions` VALUES ('598', '4', '系统管理员', '63_387_6', '387', null, null);
INSERT INTO `role_permissions` VALUES ('599', '4', '系统管理员', '63_388_7', '388', null, null);
INSERT INTO `role_permissions` VALUES ('600', '4', '系统管理员', '63_389_8', '389', null, null);
INSERT INTO `role_permissions` VALUES ('601', '4', '系统管理员', '64_390_4', '390', null, null);
INSERT INTO `role_permissions` VALUES ('602', '4', '系统管理员', '65_391_1', '391', null, null);
INSERT INTO `role_permissions` VALUES ('603', '4', '系统管理员', '65_392_2', '392', null, null);
INSERT INTO `role_permissions` VALUES ('604', '4', '系统管理员', '65_393_3', '393', null, null);
INSERT INTO `role_permissions` VALUES ('605', '4', '系统管理员', '65_394_4', '394', null, null);
INSERT INTO `role_permissions` VALUES ('606', '4', '系统管理员', '65_395_7', '395', null, null);
INSERT INTO `role_permissions` VALUES ('607', '4', '系统管理员', '65_396_8', '396', null, null);
INSERT INTO `role_permissions` VALUES ('608', '4', '系统管理员', '66_397_4', '397', null, null);
INSERT INTO `role_permissions` VALUES ('609', '4', '系统管理员', '66_398_7', '398', null, null);
INSERT INTO `role_permissions` VALUES ('610', '4', '系统管理员', '66_399_8', '399', null, null);
INSERT INTO `role_permissions` VALUES ('611', '4', '系统管理员', '67_400_4', '400', null, null);
INSERT INTO `role_permissions` VALUES ('612', '4', '系统管理员', '68_401_4', '401', null, null);
INSERT INTO `role_permissions` VALUES ('613', '4', '系统管理员', '69_402_1', '402', null, null);
INSERT INTO `role_permissions` VALUES ('614', '4', '系统管理员', '69_403_2', '403', null, null);
INSERT INTO `role_permissions` VALUES ('615', '4', '系统管理员', '69_404_3', '404', null, null);
INSERT INTO `role_permissions` VALUES ('616', '4', '系统管理员', '69_405_4', '405', null, null);
INSERT INTO `role_permissions` VALUES ('617', '4', '系统管理员', '69_406_5', '406', null, null);
INSERT INTO `role_permissions` VALUES ('618', '4', '系统管理员', '69_407_6', '407', null, null);
INSERT INTO `role_permissions` VALUES ('619', '4', '系统管理员', '69_408_7', '408', null, null);
INSERT INTO `role_permissions` VALUES ('620', '4', '系统管理员', '69_409_8', '409', null, null);
INSERT INTO `role_permissions` VALUES ('621', '4', '系统管理员', '70_410_4', '410', null, null);
INSERT INTO `role_permissions` VALUES ('622', '4', '系统管理员', '71_411_1', '411', null, null);
INSERT INTO `role_permissions` VALUES ('623', '4', '系统管理员', '71_412_2', '412', null, null);
INSERT INTO `role_permissions` VALUES ('624', '4', '系统管理员', '71_413_3', '413', null, null);
INSERT INTO `role_permissions` VALUES ('625', '4', '系统管理员', '71_414_4', '414', null, null);
INSERT INTO `role_permissions` VALUES ('626', '4', '系统管理员', '71_415_7', '415', null, null);
INSERT INTO `role_permissions` VALUES ('627', '4', '系统管理员', '71_416_8', '416', null, null);
INSERT INTO `role_permissions` VALUES ('628', '4', '系统管理员', '72_417_4', '417', null, null);
INSERT INTO `role_permissions` VALUES ('629', '4', '系统管理员', '72_418_8', '418', null, null);
INSERT INTO `role_permissions` VALUES ('630', '4', '系统管理员', '73_419_1', '419', null, null);
INSERT INTO `role_permissions` VALUES ('631', '4', '系统管理员', '73_420_2', '420', null, null);
INSERT INTO `role_permissions` VALUES ('632', '4', '系统管理员', '73_421_3', '421', null, null);
INSERT INTO `role_permissions` VALUES ('633', '4', '系统管理员', '73_422_4', '422', null, null);
INSERT INTO `role_permissions` VALUES ('634', '4', '系统管理员', '73_423_7', '423', null, null);
INSERT INTO `role_permissions` VALUES ('635', '4', '系统管理员', '73_424_8', '424', null, null);
INSERT INTO `role_permissions` VALUES ('636', '4', '系统管理员', '74_425_4', '425', null, null);
INSERT INTO `role_permissions` VALUES ('637', '4', '系统管理员', '74_426_7', '426', null, null);
INSERT INTO `role_permissions` VALUES ('638', '4', '系统管理员', '74_427_8', '427', null, null);
INSERT INTO `role_permissions` VALUES ('639', '4', '系统管理员', '75_428_1', '428', null, null);
INSERT INTO `role_permissions` VALUES ('640', '4', '系统管理员', '75_429_2', '429', null, null);
INSERT INTO `role_permissions` VALUES ('641', '4', '系统管理员', '75_430_3', '430', null, null);
INSERT INTO `role_permissions` VALUES ('642', '4', '系统管理员', '75_431_4', '431', null, null);
INSERT INTO `role_permissions` VALUES ('643', '4', '系统管理员', '75_432_7', '432', null, null);
INSERT INTO `role_permissions` VALUES ('644', '4', '系统管理员', '75_433_8', '433', null, null);
INSERT INTO `role_permissions` VALUES ('645', '4', '系统管理员', '76_434_1', '434', null, null);
INSERT INTO `role_permissions` VALUES ('646', '4', '系统管理员', '76_435_2', '435', null, null);
INSERT INTO `role_permissions` VALUES ('647', '4', '系统管理员', '76_436_3', '436', null, null);
INSERT INTO `role_permissions` VALUES ('648', '4', '系统管理员', '76_437_4', '437', null, null);
INSERT INTO `role_permissions` VALUES ('649', '4', '系统管理员', '76_438_7', '438', null, null);
INSERT INTO `role_permissions` VALUES ('650', '4', '系统管理员', '76_439_8', '439', null, null);
INSERT INTO `role_permissions` VALUES ('651', '4', '系统管理员', '77_440_1', '440', null, null);
INSERT INTO `role_permissions` VALUES ('652', '4', '系统管理员', '77_441_2', '441', null, null);
INSERT INTO `role_permissions` VALUES ('653', '4', '系统管理员', '77_442_3', '442', null, null);
INSERT INTO `role_permissions` VALUES ('654', '4', '系统管理员', '77_443_4', '443', null, null);
INSERT INTO `role_permissions` VALUES ('655', '4', '系统管理员', '77_444_7', '444', null, null);
INSERT INTO `role_permissions` VALUES ('656', '4', '系统管理员', '77_445_8', '445', null, null);
INSERT INTO `role_permissions` VALUES ('657', '4', '系统管理员', '78_446_1', '446', null, null);
INSERT INTO `role_permissions` VALUES ('658', '4', '系统管理员', '78_447_2', '447', null, null);
INSERT INTO `role_permissions` VALUES ('659', '4', '系统管理员', '78_448_3', '448', null, null);
INSERT INTO `role_permissions` VALUES ('660', '4', '系统管理员', '78_449_4', '449', null, null);
INSERT INTO `role_permissions` VALUES ('661', '4', '系统管理员', '78_450_7', '450', null, null);
INSERT INTO `role_permissions` VALUES ('662', '4', '系统管理员', '78_451_8', '451', null, null);
INSERT INTO `role_permissions` VALUES ('663', '4', '系统管理员', '80_456_3', '456', null, null);
INSERT INTO `role_permissions` VALUES ('664', '4', '系统管理员', '80_457_4', '457', null, null);
INSERT INTO `role_permissions` VALUES ('665', '4', '系统管理员', '80_458_7', '458', null, null);
INSERT INTO `role_permissions` VALUES ('666', '4', '系统管理员', '80_459_8', '459', null, null);
INSERT INTO `role_permissions` VALUES ('667', '4', '系统管理员', '81_460_1', '460', null, null);
INSERT INTO `role_permissions` VALUES ('668', '4', '系统管理员', '81_461_2', '461', null, null);
INSERT INTO `role_permissions` VALUES ('669', '4', '系统管理员', '81_462_3', '462', null, null);
INSERT INTO `role_permissions` VALUES ('670', '4', '系统管理员', '81_463_4', '463', null, null);
INSERT INTO `role_permissions` VALUES ('671', '4', '系统管理员', '81_464_7', '464', null, null);
INSERT INTO `role_permissions` VALUES ('672', '4', '系统管理员', '81_465_8', '465', null, null);
INSERT INTO `role_permissions` VALUES ('673', '4', '系统管理员', '82_466_1', '466', null, null);
INSERT INTO `role_permissions` VALUES ('674', '4', '系统管理员', '82_467_2', '467', null, null);
INSERT INTO `role_permissions` VALUES ('675', '4', '系统管理员', '82_468_3', '468', null, null);
INSERT INTO `role_permissions` VALUES ('676', '4', '系统管理员', '82_469_4', '469', null, null);
INSERT INTO `role_permissions` VALUES ('677', '4', '系统管理员', '82_470_7', '470', null, null);
INSERT INTO `role_permissions` VALUES ('678', '4', '系统管理员', '82_471_8', '471', null, null);
INSERT INTO `role_permissions` VALUES ('679', '4', '系统管理员', '83_475_4', '475', null, null);
INSERT INTO `role_permissions` VALUES ('680', '4', '系统管理员', '83_476_7', '476', null, null);
INSERT INTO `role_permissions` VALUES ('681', '4', '系统管理员', '83_477_8', '477', null, null);
INSERT INTO `role_permissions` VALUES ('682', '4', '系统管理员', '84_478_4', '478', null, null);
INSERT INTO `role_permissions` VALUES ('683', '4', '系统管理员', '84_479_7', '479', null, null);
INSERT INTO `role_permissions` VALUES ('684', '4', '系统管理员', '84_480_8', '480', null, null);
INSERT INTO `role_permissions` VALUES ('685', '4', '系统管理员', '85_481_4', '481', null, null);
INSERT INTO `role_permissions` VALUES ('686', '4', '系统管理员', '85_482_7', '482', null, null);
INSERT INTO `role_permissions` VALUES ('687', '4', '系统管理员', '85_483_8', '483', null, null);
INSERT INTO `role_permissions` VALUES ('688', '4', '系统管理员', '86_484_4', '484', null, null);
INSERT INTO `role_permissions` VALUES ('689', '4', '系统管理员', '86_485_7', '485', null, null);
INSERT INTO `role_permissions` VALUES ('690', '4', '系统管理员', '86_486_8', '486', null, null);
INSERT INTO `role_permissions` VALUES ('691', '4', '系统管理员', '87_487_1', '487', null, null);
INSERT INTO `role_permissions` VALUES ('692', '4', '系统管理员', '87_488_4', '488', null, null);
INSERT INTO `role_permissions` VALUES ('694', '4', '系统管理员', '87_490_7', '490', null, null);
INSERT INTO `role_permissions` VALUES ('695', '4', '系统管理员', '87_491_8', '491', null, null);
INSERT INTO `role_permissions` VALUES ('696', '4', '系统管理员', '88_492_4', '492', null, null);
INSERT INTO `role_permissions` VALUES ('697', '4', '系统管理员', '88_493_7', '493', null, null);
INSERT INTO `role_permissions` VALUES ('698', '4', '系统管理员', '88_494_8', '494', null, null);
INSERT INTO `role_permissions` VALUES ('699', '4', '系统管理员', '89_495_4', '495', null, null);
INSERT INTO `role_permissions` VALUES ('700', '4', '系统管理员', '89_496_7', '496', null, null);
INSERT INTO `role_permissions` VALUES ('701', '4', '系统管理员', '89_497_8', '497', null, null);
INSERT INTO `role_permissions` VALUES ('702', '4', '系统管理员', '90_498_1', '498', null, null);
INSERT INTO `role_permissions` VALUES ('703', '4', '系统管理员', '90_499_2', '499', null, null);
INSERT INTO `role_permissions` VALUES ('704', '4', '系统管理员', '90_500_3', '500', null, null);
INSERT INTO `role_permissions` VALUES ('705', '4', '系统管理员', '90_501_4', '501', null, null);
INSERT INTO `role_permissions` VALUES ('706', '4', '系统管理员', '90_502_5', '502', null, null);
INSERT INTO `role_permissions` VALUES ('707', '4', '系统管理员', '90_503_6', '503', null, null);
INSERT INTO `role_permissions` VALUES ('708', '4', '系统管理员', '90_504_7', '504', null, null);
INSERT INTO `role_permissions` VALUES ('709', '4', '系统管理员', '90_505_8', '505', null, null);
INSERT INTO `role_permissions` VALUES ('710', '4', '系统管理员', '91_506_1', '506', null, null);
INSERT INTO `role_permissions` VALUES ('711', '4', '系统管理员', '91_507_2', '507', null, null);
INSERT INTO `role_permissions` VALUES ('712', '4', '系统管理员', '91_508_3', '508', null, null);
INSERT INTO `role_permissions` VALUES ('713', '4', '系统管理员', '91_509_4', '509', null, null);
INSERT INTO `role_permissions` VALUES ('714', '4', '系统管理员', '91_510_5', '510', null, null);
INSERT INTO `role_permissions` VALUES ('715', '4', '系统管理员', '91_511_6', '511', null, null);
INSERT INTO `role_permissions` VALUES ('716', '4', '系统管理员', '91_512_7', '512', null, null);
INSERT INTO `role_permissions` VALUES ('717', '4', '系统管理员', '91_513_8', '513', null, null);
INSERT INTO `role_permissions` VALUES ('718', '4', '系统管理员', '92_514_1', '514', null, null);
INSERT INTO `role_permissions` VALUES ('719', '4', '系统管理员', '92_515_2', '515', null, null);
INSERT INTO `role_permissions` VALUES ('720', '4', '系统管理员', '92_516_3', '516', null, null);
INSERT INTO `role_permissions` VALUES ('721', '4', '系统管理员', '92_517_4', '517', null, null);
INSERT INTO `role_permissions` VALUES ('722', '4', '系统管理员', '92_518_5', '518', null, null);
INSERT INTO `role_permissions` VALUES ('723', '4', '系统管理员', '92_519_6', '519', null, null);
INSERT INTO `role_permissions` VALUES ('724', '4', '系统管理员', '92_520_7', '520', null, null);
INSERT INTO `role_permissions` VALUES ('725', '4', '系统管理员', '92_521_8', '521', null, null);
INSERT INTO `role_permissions` VALUES ('726', '4', '系统管理员', '93_522_1', '522', null, null);
INSERT INTO `role_permissions` VALUES ('727', '4', '系统管理员', '93_523_2', '523', null, null);
INSERT INTO `role_permissions` VALUES ('728', '4', '系统管理员', '93_524_3', '524', null, null);
INSERT INTO `role_permissions` VALUES ('729', '4', '系统管理员', '93_525_4', '525', null, null);
INSERT INTO `role_permissions` VALUES ('730', '4', '系统管理员', '93_526_5', '526', null, null);
INSERT INTO `role_permissions` VALUES ('731', '4', '系统管理员', '93_527_6', '527', null, null);
INSERT INTO `role_permissions` VALUES ('732', '4', '系统管理员', '93_528_7', '528', null, null);
INSERT INTO `role_permissions` VALUES ('733', '4', '系统管理员', '93_529_8', '529', null, null);
INSERT INTO `role_permissions` VALUES ('734', '4', '系统管理员', '94_530_4', '530', null, null);
INSERT INTO `role_permissions` VALUES ('735', '4', '系统管理员', '94_531_7', '531', null, null);
INSERT INTO `role_permissions` VALUES ('736', '4', '系统管理员', '94_532_8', '532', null, null);
INSERT INTO `role_permissions` VALUES ('737', '4', '系统管理员', '95_533_1', '533', null, null);
INSERT INTO `role_permissions` VALUES ('738', '4', '系统管理员', '95_534_2', '534', null, null);
INSERT INTO `role_permissions` VALUES ('739', '4', '系统管理员', '95_535_3', '535', null, null);
INSERT INTO `role_permissions` VALUES ('740', '4', '系统管理员', '95_536_4', '536', null, null);
INSERT INTO `role_permissions` VALUES ('741', '4', '系统管理员', '95_537_5', '537', null, null);
INSERT INTO `role_permissions` VALUES ('742', '4', '系统管理员', '95_538_6', '538', null, null);
INSERT INTO `role_permissions` VALUES ('743', '4', '系统管理员', '95_539_7', '539', null, null);
INSERT INTO `role_permissions` VALUES ('744', '4', '系统管理员', '95_540_8', '540', null, null);
INSERT INTO `role_permissions` VALUES ('745', '4', '系统管理员', '96_541_1', '541', null, null);
INSERT INTO `role_permissions` VALUES ('746', '4', '系统管理员', '96_542_2', '542', null, null);
INSERT INTO `role_permissions` VALUES ('747', '4', '系统管理员', '96_543_3', '543', null, null);
INSERT INTO `role_permissions` VALUES ('748', '4', '系统管理员', '96_544_4', '544', null, null);
INSERT INTO `role_permissions` VALUES ('749', '4', '系统管理员', '96_545_5', '545', null, null);
INSERT INTO `role_permissions` VALUES ('750', '4', '系统管理员', '96_546_6', '546', null, null);
INSERT INTO `role_permissions` VALUES ('751', '4', '系统管理员', '96_547_7', '547', null, null);
INSERT INTO `role_permissions` VALUES ('752', '4', '系统管理员', '96_548_8', '548', null, null);
INSERT INTO `role_permissions` VALUES ('753', '4', '系统管理员', '97_549_3', '549', null, null);
INSERT INTO `role_permissions` VALUES ('754', '4', '系统管理员', '97_551_7', '551', null, null);
INSERT INTO `role_permissions` VALUES ('755', '4', '系统管理员', '97_552_8', '552', null, null);
INSERT INTO `role_permissions` VALUES ('756', '4', '系统管理员', '98_554_8', '554', null, null);
INSERT INTO `role_permissions` VALUES ('757', '4', '系统管理员', '99_555_3', '555', null, null);
INSERT INTO `role_permissions` VALUES ('758', '4', '系统管理员', '99_556_4', '556', null, null);
INSERT INTO `role_permissions` VALUES ('759', '4', '系统管理员', '99_557_8', '557', null, null);
INSERT INTO `role_permissions` VALUES ('760', '4', '系统管理员', '100_558_1', '558', null, null);
INSERT INTO `role_permissions` VALUES ('761', '4', '系统管理员', '100_559_4', '559', null, null);
INSERT INTO `role_permissions` VALUES ('762', '4', '系统管理员', '100_560_8', '560', null, null);
INSERT INTO `role_permissions` VALUES ('763', '4', '系统管理员', '101_565_1', '561', null, null);
INSERT INTO `role_permissions` VALUES ('764', '4', '系统管理员', '101_563_4', '562', null, null);
INSERT INTO `role_permissions` VALUES ('765', '4', '系统管理员', '101_563_8', '563', null, null);
INSERT INTO `role_permissions` VALUES ('766', '4', '系统管理员', '1000_565_7', '565', null, null);
INSERT INTO `role_permissions` VALUES ('767', '4', '系统管理员', '1000_566_8', '566', null, null);
INSERT INTO `role_permissions` VALUES ('768', '4', '系统管理员', '1001_568_7', '568', null, null);
INSERT INTO `role_permissions` VALUES ('769', '4', '系统管理员', '1001_578_8', '569', null, null);
INSERT INTO `role_permissions` VALUES ('770', '4', '系统管理员', '1002_570_4', '570', null, null);
INSERT INTO `role_permissions` VALUES ('771', '4', '系统管理员', '1002_571_8', '571', null, null);
INSERT INTO `role_permissions` VALUES ('772', '4', '系统管理员', '1003_572_4', '572', null, null);
INSERT INTO `role_permissions` VALUES ('773', '4', '系统管理员', '1003_573_8', '573', null, null);
INSERT INTO `role_permissions` VALUES ('774', '4', '系统管理员', '1004_574_1', '574', null, null);
INSERT INTO `role_permissions` VALUES ('775', '4', '系统管理员', '1004_572_4', '575', null, null);
INSERT INTO `role_permissions` VALUES ('776', '4', '系统管理员', '1004_576_8', '576', null, null);
INSERT INTO `role_permissions` VALUES ('777', '4', '系统管理员', '1005_577_4', '577', null, null);
INSERT INTO `role_permissions` VALUES ('778', '4', '系统管理员', '1005_578_8', '578', null, null);
INSERT INTO `role_permissions` VALUES ('779', '4', '系统管理员', '1006_579_3', '579', null, null);
INSERT INTO `role_permissions` VALUES ('780', '4', '系统管理员', '1006_580_4', '580', null, null);
INSERT INTO `role_permissions` VALUES ('781', '4', '系统管理员', '1006_581_8', '581', null, null);
INSERT INTO `role_permissions` VALUES ('782', '4', '系统管理员', '1007_582_4', '582', null, null);
INSERT INTO `role_permissions` VALUES ('783', '4', '系统管理员', '1007_583_8', '583', null, null);
INSERT INTO `role_permissions` VALUES ('785', '4', '系统管理员', '1008_585_4', '585', null, null);
INSERT INTO `role_permissions` VALUES ('786', '4', '系统管理员', '1008_586_8', '586', null, null);
INSERT INTO `role_permissions` VALUES ('787', '4', '系统管理员', '1009_587_3', '587', null, null);
INSERT INTO `role_permissions` VALUES ('788', '4', '系统管理员', '1009_588_4', '588', null, null);
INSERT INTO `role_permissions` VALUES ('789', '4', '系统管理员', '1009_589_5', '589', null, null);
INSERT INTO `role_permissions` VALUES ('790', '4', '系统管理员', '1009_590_8', '590', null, null);
INSERT INTO `role_permissions` VALUES ('791', '4', '系统管理员', '1010_591_3', '591', null, null);
INSERT INTO `role_permissions` VALUES ('792', '4', '系统管理员', '1010_592_4', '592', null, null);
INSERT INTO `role_permissions` VALUES ('793', '4', '系统管理员', '1010_593_8', '593', null, null);
INSERT INTO `role_permissions` VALUES ('795', '4', '系统管理员', '1008_584_3', '584', null, null);
INSERT INTO `role_permissions` VALUES ('816', '4', '系统管理员', '1000_564_4', '564', null, null);
INSERT INTO `role_permissions` VALUES ('817', '4', '系统管理员', '1001_1011_4', '1011', null, null);
INSERT INTO `role_permissions` VALUES ('818', '4', '系统管理员', '98_1012_4', '1012', null, null);
INSERT INTO `role_permissions` VALUES ('819', '4', '系统管理员', '97_550_4', '550', null, null);
INSERT INTO `role_permissions` VALUES ('820', '4', '系统管理员', '32_1016_4', '1016', null, null);
INSERT INTO `role_permissions` VALUES ('821', '4', '系统管理员', '48_1013_4', '1013', null, null);
INSERT INTO `role_permissions` VALUES ('822', '4', '系统管理员', '48_1015_8', '1015', null, null);
INSERT INTO `role_permissions` VALUES ('830', '4', '系统管理员', '1017_1018_4', '1018', null, null);
INSERT INTO `role_permissions` VALUES ('831', '4', '系统管理员', '1022_1023_4', '1023', null, null);
INSERT INTO `role_permissions` VALUES ('832', '4', '系统管理员', '1017_1021_3', '1021', null, null);
INSERT INTO `role_permissions` VALUES ('833', '4', '系统管理员', '1017_1019_7', '1019', null, null);
INSERT INTO `role_permissions` VALUES ('834', '4', '系统管理员', '1017_1020_8', '1020', null, null);
INSERT INTO `role_permissions` VALUES ('835', '2', '业务', '15_167_1', '167', null, null);
INSERT INTO `role_permissions` VALUES ('836', '2', '业务', '15_168_2', '168', null, null);
INSERT INTO `role_permissions` VALUES ('837', '2', '业务', '15_169_3', '169', null, null);
INSERT INTO `role_permissions` VALUES ('838', '2', '业务', '15_170_4', '170', null, null);
INSERT INTO `role_permissions` VALUES ('839', '2', '业务', '15_171_5', '171', null, null);
INSERT INTO `role_permissions` VALUES ('840', '2', '业务', '15_172_6', '172', null, null);
INSERT INTO `role_permissions` VALUES ('841', '2', '业务', '15_173_7', '173', null, null);
INSERT INTO `role_permissions` VALUES ('842', '2', '业务', '15_174_8', '174', null, null);
INSERT INTO `role_permissions` VALUES ('843', '2', '业务', '16_175_3', '175', null, null);
INSERT INTO `role_permissions` VALUES ('844', '2', '业务', '16_176_4', '176', null, null);
INSERT INTO `role_permissions` VALUES ('845', '2', '业务', '17_177_1', '177', null, null);
INSERT INTO `role_permissions` VALUES ('846', '2', '业务', '17_178_4', '178', null, null);
INSERT INTO `role_permissions` VALUES ('847', '2', '业务', '17_179_7', '179', null, null);
INSERT INTO `role_permissions` VALUES ('848', '2', '业务', '17_180_8', '180', null, null);
INSERT INTO `role_permissions` VALUES ('849', '2', '业务', '18_181_2', '181', null, null);
INSERT INTO `role_permissions` VALUES ('850', '2', '业务', '18_182_3', '182', null, null);
INSERT INTO `role_permissions` VALUES ('851', '2', '业务', '18_183_4', '183', null, null);
INSERT INTO `role_permissions` VALUES ('852', '2', '业务', '18_184_5', '184', null, null);
INSERT INTO `role_permissions` VALUES ('853', '2', '业务', '18_185_7', '185', null, null);
INSERT INTO `role_permissions` VALUES ('854', '2', '业务', '18_186_8', '186', null, null);
INSERT INTO `role_permissions` VALUES ('855', '2', '业务', '19_187_1', '187', null, null);
INSERT INTO `role_permissions` VALUES ('856', '2', '业务', '19_188_2', '188', null, null);
INSERT INTO `role_permissions` VALUES ('857', '2', '业务', '19_189_3', '189', null, null);
INSERT INTO `role_permissions` VALUES ('858', '2', '业务', '19_190_4', '190', null, null);
INSERT INTO `role_permissions` VALUES ('859', '2', '业务', '19_191_7', '191', null, null);
INSERT INTO `role_permissions` VALUES ('860', '2', '业务', '19_192_8', '192', null, null);
INSERT INTO `role_permissions` VALUES ('861', '2', '业务', '20_193_3', '193', null, null);
INSERT INTO `role_permissions` VALUES ('862', '2', '业务', '21_194_3', '194', null, null);
INSERT INTO `role_permissions` VALUES ('863', '2', '业务', '22_195_3', '195', null, null);
INSERT INTO `role_permissions` VALUES ('864', '2', '业务', '32_235_4', '235', null, null);
INSERT INTO `role_permissions` VALUES ('865', '2', '业务', '24_197_3', '197', null, null);
INSERT INTO `role_permissions` VALUES ('866', '2', '业务', '25_198_3', '198', null, null);
INSERT INTO `role_permissions` VALUES ('867', '2', '业务', '26_199_3', '199', null, null);
INSERT INTO `role_permissions` VALUES ('868', '2', '业务', '27_200_1', '200', null, null);
INSERT INTO `role_permissions` VALUES ('869', '2', '业务', '27_201_2', '201', null, null);
INSERT INTO `role_permissions` VALUES ('870', '2', '业务', '27_202_3', '202', null, null);
INSERT INTO `role_permissions` VALUES ('871', '2', '业务', '27_203_4', '203', null, null);
INSERT INTO `role_permissions` VALUES ('872', '2', '业务', '27_204_7', '204', null, null);
INSERT INTO `role_permissions` VALUES ('873', '2', '业务', '27_205_8', '205', null, null);
INSERT INTO `role_permissions` VALUES ('874', '2', '业务', '28_206_1', '206', null, null);
INSERT INTO `role_permissions` VALUES ('875', '2', '业务', '28_207_2', '207', null, null);
INSERT INTO `role_permissions` VALUES ('876', '2', '业务', '28_208_3', '208', null, null);
INSERT INTO `role_permissions` VALUES ('877', '2', '业务', '28_209_4', '209', null, null);
INSERT INTO `role_permissions` VALUES ('878', '2', '业务', '28_210_5', '210', null, null);
INSERT INTO `role_permissions` VALUES ('879', '2', '业务', '28_211_6', '211', null, null);
INSERT INTO `role_permissions` VALUES ('880', '2', '业务', '28_212_7', '212', null, null);
INSERT INTO `role_permissions` VALUES ('881', '2', '业务', '28_213_8', '213', null, null);
INSERT INTO `role_permissions` VALUES ('882', '2', '业务', '29_214_3', '214', null, null);
INSERT INTO `role_permissions` VALUES ('883', '2', '业务', '29_215_4', '215', null, null);
INSERT INTO `role_permissions` VALUES ('884', '2', '业务', '30_216_4', '216', null, null);
INSERT INTO `role_permissions` VALUES ('885', '2', '业务', '30_217_7', '217', null, null);
INSERT INTO `role_permissions` VALUES ('886', '2', '业务', '30_218_8', '218', null, null);
INSERT INTO `role_permissions` VALUES ('887', '2', '业务', '31_2169_4', '219', null, null);
INSERT INTO `role_permissions` VALUES ('888', '2', '业务', '31_230_7', '230', null, null);
INSERT INTO `role_permissions` VALUES ('889', '2', '业务', '31_232_8', '231', null, null);
INSERT INTO `role_permissions` VALUES ('890', '2', '业务', '32_232_1', '232', null, null);
INSERT INTO `role_permissions` VALUES ('891', '2', '业务', '32_233_2', '233', null, null);
INSERT INTO `role_permissions` VALUES ('892', '2', '业务', '32_234_3', '234', null, null);
INSERT INTO `role_permissions` VALUES ('893', '2', '业务', '32_1016_4', '1016', null, null);
INSERT INTO `role_permissions` VALUES ('894', '2', '业务', '32_236_5', '236', null, null);
INSERT INTO `role_permissions` VALUES ('895', '2', '业务', '32_237_6', '237', null, null);
INSERT INTO `role_permissions` VALUES ('896', '2', '业务', '32_238_7', '238', null, null);
INSERT INTO `role_permissions` VALUES ('897', '2', '业务', '32_239_8', '239', null, null);
INSERT INTO `role_permissions` VALUES ('898', '2', '业务', '33_242_3', '242', null, null);
INSERT INTO `role_permissions` VALUES ('899', '2', '业务', '33_243_4', '243', null, null);
INSERT INTO `role_permissions` VALUES ('900', '2', '业务', '34_248_3', '248', null, null);
INSERT INTO `role_permissions` VALUES ('901', '2', '业务', '34_249_4', '249', null, null);
INSERT INTO `role_permissions` VALUES ('902', '2', '业务', '35_250_4', '250', null, null);
INSERT INTO `role_permissions` VALUES ('903', '2', '业务', '35_251_7', '251', null, null);
INSERT INTO `role_permissions` VALUES ('904', '2', '业务', '35_252_8', '252', null, null);
INSERT INTO `role_permissions` VALUES ('905', '2', '业务', '36_253_4', '253', null, null);
INSERT INTO `role_permissions` VALUES ('906', '2', '业务', '36_254_7', '254', null, null);
INSERT INTO `role_permissions` VALUES ('907', '2', '业务', '36_255_8', '255', null, null);
INSERT INTO `role_permissions` VALUES ('908', '2', '业务', '37_256_1', '256', null, null);
INSERT INTO `role_permissions` VALUES ('909', '2', '业务', '37_257_2', '257', null, null);
INSERT INTO `role_permissions` VALUES ('910', '2', '业务', '37_258_3', '258', null, null);
INSERT INTO `role_permissions` VALUES ('911', '2', '业务', '37_259_4', '259', null, null);
INSERT INTO `role_permissions` VALUES ('912', '2', '业务', '37_260_5', '260', null, null);
INSERT INTO `role_permissions` VALUES ('913', '2', '业务', '37_261_6', '261', null, null);
INSERT INTO `role_permissions` VALUES ('914', '2', '业务', '37_262_7', '262', null, null);
INSERT INTO `role_permissions` VALUES ('915', '2', '业务', '37_263_8', '263', null, null);
INSERT INTO `role_permissions` VALUES ('916', '2', '业务', '38_264_4', '264', null, null);
INSERT INTO `role_permissions` VALUES ('917', '2', '业务', '38_265_7', '265', null, null);
INSERT INTO `role_permissions` VALUES ('918', '2', '业务', '38_266_8', '266', null, null);
INSERT INTO `role_permissions` VALUES ('919', '2', '业务', '39_267_4', '267', null, null);
INSERT INTO `role_permissions` VALUES ('920', '2', '业务', '39_268_7', '268', null, null);
INSERT INTO `role_permissions` VALUES ('921', '2', '业务', '39_269_8', '269', null, null);
INSERT INTO `role_permissions` VALUES ('922', '2', '业务', '40_270_4', '270', null, null);
INSERT INTO `role_permissions` VALUES ('923', '2', '业务', '40_271_7', '271', null, null);
INSERT INTO `role_permissions` VALUES ('924', '2', '业务', '40_272_8', '272', null, null);
INSERT INTO `role_permissions` VALUES ('925', '2', '业务', '41_273_3', '273', null, null);
INSERT INTO `role_permissions` VALUES ('926', '2', '业务', '41_274_4', '274', null, null);
INSERT INTO `role_permissions` VALUES ('927', '2', '业务', '41_275_7', '275', null, null);
INSERT INTO `role_permissions` VALUES ('928', '2', '业务', '41_276_8', '276', null, null);
INSERT INTO `role_permissions` VALUES ('929', '2', '业务', '42_277_4', '277', null, null);
INSERT INTO `role_permissions` VALUES ('930', '2', '业务', '42_278_7', '278', null, null);
INSERT INTO `role_permissions` VALUES ('931', '2', '业务', '42_279_8', '279', null, null);
INSERT INTO `role_permissions` VALUES ('932', '2', '业务', '43_280_4', '280', null, null);
INSERT INTO `role_permissions` VALUES ('933', '2', '业务', '43_281_7', '281', null, null);
INSERT INTO `role_permissions` VALUES ('934', '2', '业务', '43_282_8', '282', null, null);
INSERT INTO `role_permissions` VALUES ('935', '2', '业务', '44_283_4', '283', null, null);
INSERT INTO `role_permissions` VALUES ('936', '2', '业务', '44_284_7', '284', null, null);
INSERT INTO `role_permissions` VALUES ('937', '2', '业务', '44_285_8', '285', null, null);
INSERT INTO `role_permissions` VALUES ('938', '2', '业务', '45_286_4', '286', null, null);
INSERT INTO `role_permissions` VALUES ('939', '2', '业务', '45_287_7', '287', null, null);
INSERT INTO `role_permissions` VALUES ('940', '2', '业务', '45_288_8', '288', null, null);
INSERT INTO `role_permissions` VALUES ('941', '2', '业务', '46_289_4', '289', null, null);
INSERT INTO `role_permissions` VALUES ('942', '2', '业务', '46_290_7', '290', null, null);
INSERT INTO `role_permissions` VALUES ('943', '2', '业务', '46_291_8', '291', null, null);
INSERT INTO `role_permissions` VALUES ('944', '2', '业务', '47_292_4', '292', null, null);
INSERT INTO `role_permissions` VALUES ('945', '2', '业务', '47_293_7', '293', null, null);
INSERT INTO `role_permissions` VALUES ('946', '2', '业务', '47_294_8', '294', null, null);
INSERT INTO `role_permissions` VALUES ('947', '2', '业务', '48_1013_4', '1013', null, null);
INSERT INTO `role_permissions` VALUES ('948', '2', '业务', '48_1014_7', '1014', null, null);
INSERT INTO `role_permissions` VALUES ('949', '2', '业务', '48_1015_8', '1015', null, null);
INSERT INTO `role_permissions` VALUES ('950', '2', '业务', '49_295_4', '295', null, null);
INSERT INTO `role_permissions` VALUES ('951', '2', '业务', '49_296_7', '296', null, null);
INSERT INTO `role_permissions` VALUES ('952', '2', '业务', '49_297_8', '297', null, null);
INSERT INTO `role_permissions` VALUES ('953', '7', '仓库', '58_359_1', '359', null, null);
INSERT INTO `role_permissions` VALUES ('954', '7', '仓库', '58_360_2', '360', null, null);
INSERT INTO `role_permissions` VALUES ('955', '7', '仓库', '58_361_3', '361', null, null);
INSERT INTO `role_permissions` VALUES ('956', '7', '仓库', '58_362_4', '362', null, null);
INSERT INTO `role_permissions` VALUES ('957', '7', '仓库', '58_363_5', '363', null, null);
INSERT INTO `role_permissions` VALUES ('958', '7', '仓库', '58_364_6', '364', null, null);
INSERT INTO `role_permissions` VALUES ('959', '7', '仓库', '58_365_7', '365', null, null);
INSERT INTO `role_permissions` VALUES ('960', '7', '仓库', '58_366_8', '366', null, null);
INSERT INTO `role_permissions` VALUES ('961', '7', '仓库', '59_367_4', '367', null, null);
INSERT INTO `role_permissions` VALUES ('962', '7', '仓库', '59_368_7', '368', null, null);
INSERT INTO `role_permissions` VALUES ('963', '7', '仓库', '59_369_8', '369', null, null);
INSERT INTO `role_permissions` VALUES ('964', '7', '仓库', '60_370_4', '370', null, null);
INSERT INTO `role_permissions` VALUES ('965', '7', '仓库', '60_371_7', '371', null, null);
INSERT INTO `role_permissions` VALUES ('966', '7', '仓库', '60_378_8', '372', null, null);
INSERT INTO `role_permissions` VALUES ('967', '7', '仓库', '61_373_1', '373', null, null);
INSERT INTO `role_permissions` VALUES ('968', '7', '仓库', '61_374_2', '374', null, null);
INSERT INTO `role_permissions` VALUES ('969', '7', '仓库', '61_375_3', '375', null, null);
INSERT INTO `role_permissions` VALUES ('970', '7', '仓库', '61_376_4', '376', null, null);
INSERT INTO `role_permissions` VALUES ('971', '7', '仓库', '61_377_5', '377', null, null);
INSERT INTO `role_permissions` VALUES ('972', '7', '仓库', '61_378_6', '378', null, null);
INSERT INTO `role_permissions` VALUES ('973', '7', '仓库', '61_379_7', '379', null, null);
INSERT INTO `role_permissions` VALUES ('974', '7', '仓库', '61_380_8', '380', null, null);
INSERT INTO `role_permissions` VALUES ('975', '7', '仓库', '62_381_4', '381', null, null);
INSERT INTO `role_permissions` VALUES ('976', '7', '仓库', '63_382_1', '382', null, null);
INSERT INTO `role_permissions` VALUES ('977', '7', '仓库', '63_383_2', '383', null, null);
INSERT INTO `role_permissions` VALUES ('978', '7', '仓库', '63_384_3', '384', null, null);
INSERT INTO `role_permissions` VALUES ('979', '7', '仓库', '63_385_4', '385', null, null);
INSERT INTO `role_permissions` VALUES ('980', '7', '仓库', '63_386_5', '386', null, null);
INSERT INTO `role_permissions` VALUES ('981', '7', '仓库', '63_387_6', '387', null, null);
INSERT INTO `role_permissions` VALUES ('982', '7', '仓库', '63_388_7', '388', null, null);
INSERT INTO `role_permissions` VALUES ('983', '7', '仓库', '63_389_8', '389', null, null);
INSERT INTO `role_permissions` VALUES ('984', '7', '仓库', '64_390_4', '390', null, null);
INSERT INTO `role_permissions` VALUES ('985', '7', '仓库', '65_391_1', '391', null, null);
INSERT INTO `role_permissions` VALUES ('986', '7', '仓库', '65_392_2', '392', null, null);
INSERT INTO `role_permissions` VALUES ('987', '7', '仓库', '65_393_3', '393', null, null);
INSERT INTO `role_permissions` VALUES ('988', '7', '仓库', '65_394_4', '394', null, null);
INSERT INTO `role_permissions` VALUES ('989', '7', '仓库', '65_395_7', '395', null, null);
INSERT INTO `role_permissions` VALUES ('990', '7', '仓库', '65_396_8', '396', null, null);
INSERT INTO `role_permissions` VALUES ('991', '7', '仓库', '66_397_4', '397', null, null);
INSERT INTO `role_permissions` VALUES ('992', '7', '仓库', '66_398_7', '398', null, null);
INSERT INTO `role_permissions` VALUES ('993', '7', '仓库', '66_399_8', '399', null, null);
INSERT INTO `role_permissions` VALUES ('994', '7', '仓库', '67_400_4', '400', null, null);
INSERT INTO `role_permissions` VALUES ('995', '7', '仓库', '68_401_4', '401', null, null);
INSERT INTO `role_permissions` VALUES ('996', '7', '仓库', '69_402_1', '402', null, null);
INSERT INTO `role_permissions` VALUES ('997', '7', '仓库', '69_403_2', '403', null, null);
INSERT INTO `role_permissions` VALUES ('998', '7', '仓库', '69_404_3', '404', null, null);
INSERT INTO `role_permissions` VALUES ('999', '7', '仓库', '69_405_4', '405', null, null);
INSERT INTO `role_permissions` VALUES ('1000', '7', '仓库', '69_406_5', '406', null, null);
INSERT INTO `role_permissions` VALUES ('1001', '7', '仓库', '69_407_6', '407', null, null);
INSERT INTO `role_permissions` VALUES ('1002', '7', '仓库', '69_408_7', '408', null, null);
INSERT INTO `role_permissions` VALUES ('1003', '7', '仓库', '69_409_8', '409', null, null);
INSERT INTO `role_permissions` VALUES ('1004', '7', '仓库', '70_410_4', '410', null, null);
INSERT INTO `role_permissions` VALUES ('1005', '7', '仓库', '71_411_1', '411', null, null);
INSERT INTO `role_permissions` VALUES ('1006', '7', '仓库', '71_412_2', '412', null, null);
INSERT INTO `role_permissions` VALUES ('1007', '7', '仓库', '71_413_3', '413', null, null);
INSERT INTO `role_permissions` VALUES ('1008', '7', '仓库', '71_414_4', '414', null, null);
INSERT INTO `role_permissions` VALUES ('1009', '7', '仓库', '71_415_7', '415', null, null);
INSERT INTO `role_permissions` VALUES ('1010', '7', '仓库', '71_416_8', '416', null, null);
INSERT INTO `role_permissions` VALUES ('1011', '7', '仓库', '72_417_4', '417', null, null);
INSERT INTO `role_permissions` VALUES ('1012', '7', '仓库', '72_418_8', '418', null, null);
INSERT INTO `role_permissions` VALUES ('1013', '7', '仓库', '73_419_1', '419', null, null);
INSERT INTO `role_permissions` VALUES ('1014', '7', '仓库', '73_420_2', '420', null, null);
INSERT INTO `role_permissions` VALUES ('1015', '7', '仓库', '73_421_3', '421', null, null);
INSERT INTO `role_permissions` VALUES ('1016', '7', '仓库', '73_422_4', '422', null, null);
INSERT INTO `role_permissions` VALUES ('1017', '7', '仓库', '73_423_7', '423', null, null);
INSERT INTO `role_permissions` VALUES ('1018', '7', '仓库', '73_424_8', '424', null, null);
INSERT INTO `role_permissions` VALUES ('1019', '7', '仓库', '74_425_4', '425', null, null);
INSERT INTO `role_permissions` VALUES ('1020', '7', '仓库', '74_426_7', '426', null, null);
INSERT INTO `role_permissions` VALUES ('1021', '7', '仓库', '74_427_8', '427', null, null);
INSERT INTO `role_permissions` VALUES ('1022', '9', '采购', '2_106_1', '106', null, null);
INSERT INTO `role_permissions` VALUES ('1023', '9', '采购', '2_107_2', '107', null, null);
INSERT INTO `role_permissions` VALUES ('1024', '9', '采购', '2_108_3', '108', null, null);
INSERT INTO `role_permissions` VALUES ('1025', '9', '采购', '2_109_4', '109', null, null);
INSERT INTO `role_permissions` VALUES ('1026', '9', '采购', '2_110_5', '110', null, null);
INSERT INTO `role_permissions` VALUES ('1027', '9', '采购', '2_111_6', '111', null, null);
INSERT INTO `role_permissions` VALUES ('1028', '9', '采购', '2_112_7', '112', null, null);
INSERT INTO `role_permissions` VALUES ('1029', '9', '采购', '2_113_8', '113', null, null);
INSERT INTO `role_permissions` VALUES ('1030', '9', '采购', '3_105_4', '115', null, null);
INSERT INTO `role_permissions` VALUES ('1031', '9', '采购', '3_116_7', '116', null, null);
INSERT INTO `role_permissions` VALUES ('1032', '9', '采购', '3_117_8', '117', null, null);
INSERT INTO `role_permissions` VALUES ('1033', '9', '采购', '4_118_1', '118', null, null);
INSERT INTO `role_permissions` VALUES ('1034', '9', '采购', '4_119_2', '119', null, null);
INSERT INTO `role_permissions` VALUES ('1035', '9', '采购', '4_120_3', '120', null, null);
INSERT INTO `role_permissions` VALUES ('1036', '9', '采购', '4_121_4', '121', null, null);
INSERT INTO `role_permissions` VALUES ('1037', '9', '采购', '4_123_7', '123', null, null);
INSERT INTO `role_permissions` VALUES ('1038', '9', '采购', '4_122_8', '122', null, null);
INSERT INTO `role_permissions` VALUES ('1039', '9', '采购', '5_124_1', '124', null, null);
INSERT INTO `role_permissions` VALUES ('1040', '9', '采购', '5_125_3', '125', null, null);
INSERT INTO `role_permissions` VALUES ('1041', '9', '采购', '5_126_4', '126', null, null);
INSERT INTO `role_permissions` VALUES ('1042', '9', '采购', '5_127_8', '127', null, null);
INSERT INTO `role_permissions` VALUES ('1043', '9', '采购', '6_128_3', '128', null, null);
INSERT INTO `role_permissions` VALUES ('1044', '9', '采购', '6_129_4', '129', null, null);
INSERT INTO `role_permissions` VALUES ('1045', '9', '采购', '6_130_5', '130', null, null);
INSERT INTO `role_permissions` VALUES ('1046', '9', '采购', '6_131_8', '131', null, null);
INSERT INTO `role_permissions` VALUES ('1047', '9', '采购', '7_132_1', '132', null, null);
INSERT INTO `role_permissions` VALUES ('1048', '9', '采购', '7_133_2', '133', null, null);
INSERT INTO `role_permissions` VALUES ('1049', '9', '采购', '7_134_3', '134', null, null);
INSERT INTO `role_permissions` VALUES ('1050', '9', '采购', '7_135_4', '135', null, null);
INSERT INTO `role_permissions` VALUES ('1051', '9', '采购', '7_136_7', '136', null, null);
INSERT INTO `role_permissions` VALUES ('1052', '9', '采购', '7_137_8', '137', null, null);
INSERT INTO `role_permissions` VALUES ('1053', '9', '采购', '8_138_4', '138', null, null);
INSERT INTO `role_permissions` VALUES ('1054', '9', '采购', '9_139_1', '139', null, null);
INSERT INTO `role_permissions` VALUES ('1055', '9', '采购', '9_140_2', '140', null, null);
INSERT INTO `role_permissions` VALUES ('1056', '9', '采购', '9_141_3', '141', null, null);
INSERT INTO `role_permissions` VALUES ('1057', '9', '采购', '9_142_4', '142', null, null);
INSERT INTO `role_permissions` VALUES ('1058', '9', '采购', '9_143_5', '143', null, null);
INSERT INTO `role_permissions` VALUES ('1059', '9', '采购', '9_145_6', '145', null, null);
INSERT INTO `role_permissions` VALUES ('1060', '9', '采购', '9_146_7', '146', null, null);
INSERT INTO `role_permissions` VALUES ('1061', '9', '采购', '9_147_8', '147', null, null);
INSERT INTO `role_permissions` VALUES ('1062', '9', '采购', '10_148_4', '148', null, null);
INSERT INTO `role_permissions` VALUES ('1063', '9', '采购', '10_149_7', '149', null, null);
INSERT INTO `role_permissions` VALUES ('1064', '9', '采购', '10_150_8', '150', null, null);
INSERT INTO `role_permissions` VALUES ('1065', '9', '采购', '11_151_1', '151', null, null);
INSERT INTO `role_permissions` VALUES ('1066', '9', '采购', '11_152_4', '152', null, null);
INSERT INTO `role_permissions` VALUES ('1067', '9', '采购', '11_153_7', '153', null, null);
INSERT INTO `role_permissions` VALUES ('1068', '9', '采购', '11_154_8', '154', null, null);
INSERT INTO `role_permissions` VALUES ('1069', '9', '采购', '12_155_4', '155', null, null);
INSERT INTO `role_permissions` VALUES ('1070', '9', '采购', '12_156_7', '156', null, null);
INSERT INTO `role_permissions` VALUES ('1071', '9', '采购', '12_157_8', '157', null, null);
INSERT INTO `role_permissions` VALUES ('1072', '9', '采购', '13_158_1', '158', null, null);
INSERT INTO `role_permissions` VALUES ('1073', '9', '采购', '13_159_2', '159', null, null);
INSERT INTO `role_permissions` VALUES ('1074', '9', '采购', '13_160_3', '160', null, null);
INSERT INTO `role_permissions` VALUES ('1075', '9', '采购', '13_161_4', '161', null, null);
INSERT INTO `role_permissions` VALUES ('1076', '9', '采购', '13_162_7', '162', null, null);
INSERT INTO `role_permissions` VALUES ('1077', '9', '采购', '13_163_8', '163', null, null);
INSERT INTO `role_permissions` VALUES ('1078', '9', '采购', '14_164_4', '164', null, null);
INSERT INTO `role_permissions` VALUES ('1079', '9', '采购', '14_165_7', '165', null, null);
INSERT INTO `role_permissions` VALUES ('1080', '9', '采购', '14_166_8', '166', null, null);
INSERT INTO `role_permissions` VALUES ('1081', '9', '采购', '1017_1021_3', '1021', null, null);
INSERT INTO `role_permissions` VALUES ('1082', '9', '采购', '1017_1018_4', '1018', null, null);
INSERT INTO `role_permissions` VALUES ('1083', '9', '采购', '1017_1019_7', '1019', null, null);
INSERT INTO `role_permissions` VALUES ('1084', '9', '采购', '1017_1020_8', '1020', null, null);
INSERT INTO `role_permissions` VALUES ('1085', '9', '采购', '1022_1023_4', '1023', null, null);
INSERT INTO `role_permissions` VALUES ('1086', '4', '系统管理员', '8_138_4', '138', null, null);

-- ----------------------------
-- Table structure for sale_goods
-- ----------------------------
DROP TABLE IF EXISTS `sale_goods`;
CREATE TABLE `sale_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '销售-销货单',
  `create_date` datetime DEFAULT NULL COMMENT '制单日期',
  `sale_no` varchar(255) DEFAULT NULL COMMENT '销售单号',
  `warehouse_out` varchar(50) DEFAULT NULL COMMENT '出货仓库',
  `warehouse_out_str` varchar(255) DEFAULT NULL COMMENT '出货仓库描述',
  `customer_no` varchar(50) DEFAULT NULL COMMENT '客户编号',
  `customer_no_str` varchar(255) DEFAULT NULL COMMENT '客户编号描述',
  `customer_order_no` varchar(50) DEFAULT NULL COMMENT '客户订单号',
  `special_order` tinyint(1) DEFAULT NULL COMMENT '特批单',
  `special_price_order` tinyint(1) DEFAULT NULL COMMENT '特价单',
  `invalid` tinyint(1) DEFAULT NULL COMMENT '单据作废',
  `tax` varchar(20) DEFAULT NULL COMMENT '税别',
  `currency` varchar(50) DEFAULT NULL COMMENT '币别',
  `discount` varchar(20) DEFAULT NULL COMMENT '折扣',
  `customer_category` varchar(50) DEFAULT NULL COMMENT '客户类别',
  `sale_receivable` decimal(10,2) DEFAULT NULL COMMENT '销售应收',
  `business_leader` varchar(255) DEFAULT NULL COMMENT '业务负责',
  `business_leader_str` varchar(255) DEFAULT NULL COMMENT '业务负责描述',
  `payment` varchar(50) DEFAULT NULL COMMENT '支付方式',
  `made_people` varchar(50) DEFAULT NULL COMMENT '制单人',
  `delivery_status` varchar(50) DEFAULT NULL COMMENT '发货状态',
  `carry_method` varchar(50) DEFAULT NULL COMMENT '运输方式',
  `carry_method_str` varchar(255) DEFAULT NULL COMMENT '快递或运输公司',
  `weight` double DEFAULT NULL COMMENT '重量（kg）',
  `last_update` varchar(50) DEFAULT NULL COMMENT '最后修改人',
  `last_update_str` varchar(100) DEFAULT NULL COMMENT '最后修改人描述',
  `audit` varchar(50) DEFAULT NULL COMMENT '审核人',
  `audit_str` varchar(100) DEFAULT NULL COMMENT '审核人描述',
  `customer_name` varchar(50) DEFAULT NULL COMMENT '客户姓名',
  `zip` varchar(20) DEFAULT NULL COMMENT '邮政编码',
  `contact` varchar(20) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `fax` varchar(50) DEFAULT NULL COMMENT '传真',
  `shipping_address` varchar(255) DEFAULT NULL COMMENT '送货地址',
  `invoice_title` varchar(50) DEFAULT NULL COMMENT '发票抬头',
  `invoice_address` varchar(255) DEFAULT NULL COMMENT '发票地址',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  `order_audit` tinyint(1) DEFAULT '0' COMMENT '单据审核状态',
  `pass_back` tinyint(1) DEFAULT NULL COMMENT '回传',
  `pass_back_date` datetime DEFAULT NULL COMMENT '回传日期',
  `financial` varchar(255) DEFAULT NULL COMMENT '财务',
  `order_invoice` tinyint(1) DEFAULT NULL COMMENT '已核销项发票',
  `import_outbound_audit` tinyint(1) DEFAULT NULL COMMENT '导出到销货出库单',
  `tax_rate` decimal(10,2) DEFAULT '0.00' COMMENT '税率',
  `ticket` tinyint(1) DEFAULT '0' COMMENT '销货单开票情况',
  `money` decimal(10,2) DEFAULT '0.00' COMMENT '货款金额',
  `money_tax` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '税款金额',
  `import_express_audit` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sale_goods
-- ----------------------------
INSERT INTO `sale_goods` VALUES ('31', '2019-04-28 00:00:00', 'A1904280001', '309', 'A区仓库', '20190428215126', '上海君之道信息有限公司', 'A1904280001', '0', '0', '0', '外加', 'RMB', '100', '目标客户', null, '(A005)财务测试人员', '财务测试人员', '先款后货', '销售测试账号', '正常发货', '顺丰标准件', '22', '1000', '销售测试账号', '2019-04-28', 'Administrator', '2019-04-28', '上海君之道信息有限公司', '21000', '陈先生', '17521282266', '652412', '上海市普陀区中江路638号天地软件园27号207', null, null, '2019-04-28 22:13:25', '1', null, null, '是', null, '1', '0.16', '1', '0.00', '0.00', '1');
INSERT INTO `sale_goods` VALUES ('32', '2019-04-29 00:00:00', 'A1904290001', '325', 'C区', '20190428215126', '上海君之道信息有限公司', null, '0', '0', '0', '外加', 'RMB', '100', '目标客户', null, '(A005)财务测试人员', '财务测试人员', '先款后货', 'Administrator', '正常发货', '顺丰标准件', null, null, 'Administrator', '2019-04-29', 'Administrator', '2019-04-29', '上海君之道信息有限公司', null, '陈先生', '17521282266', '652412', '上海市普陀区中江路638号天地软件园27号207', '上海君之道信息有限公司', '上海市普陀区中江路638号天地软件园27号207', '2019-04-29 09:44:35', '1', null, null, '否', null, '1', '0.16', '0', '0.00', '0.00', '0');
INSERT INTO `sale_goods` VALUES ('33', '2019-04-29 00:00:00', 'A1904290002', '309', 'A区仓库', '20190428215126', '上海君之道信息有限公司', 'A1904280001', '0', '0', '0', '外加', 'RMB', '80', '目标客户', null, '(A005)财务测试人员', '财务测试人员', '先款后货', 'Administrator', '正常发货', '顺丰特惠件', null, null, 'Administrator', '2019-04-29', 'Administrator', '2019-04-29', '上海君之道信息有限公司', '21000', '陈先生', '17521282266', '652412', '上海市普陀区中江路638号天地软件园27号207', '上海君之道信息有限公司', '上海君之道信息有限公司', '2019-04-29 10:47:43', '1', null, null, '是', null, '0', '0.16', '1', '1600.00', '256.00', '0');

-- ----------------------------
-- Table structure for sale_goods_product
-- ----------------------------
DROP TABLE IF EXISTS `sale_goods_product`;
CREATE TABLE `sale_goods_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '销售-销货单-销货产品',
  `sale_goods_id` bigint(20) DEFAULT NULL COMMENT '销货单id',
  `product_no` varchar(50) DEFAULT NULL COMMENT '产品编号',
  `order_no` varchar(50) DEFAULT NULL COMMENT '订单编号',
  `product_name` varchar(50) DEFAULT NULL COMMENT '产品名称',
  `category` varchar(20) DEFAULT NULL COMMENT '分类',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `unit` varchar(20) DEFAULT NULL COMMENT '单位',
  `pricing` decimal(10,2) DEFAULT NULL COMMENT '定价',
  `discount` double(10,2) DEFAULT NULL COMMENT '折扣',
  `price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `money` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `warehouse_position` varchar(20) DEFAULT NULL COMMENT '库位',
  `floor` varchar(20) DEFAULT NULL COMMENT '楼层',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  `outbound_num` int(11) DEFAULT '0' COMMENT '出库数量',
  `source_order` varchar(255) DEFAULT NULL COMMENT '单据来源',
  `source_no` varchar(255) DEFAULT NULL COMMENT '来源单号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sale_goods_product
-- ----------------------------
INSERT INTO `sale_goods_product` VALUES ('33', '31', 'TMTR6R 0.2L15 BXC', null, 'TMTR6R 0.2L15 BXC', 'ABH替换式', '20', '斤', '100.00', '100.00', '100.00', '2000.00', 'A001', '四楼', null, '2019-04-28 22:13:25', '20', '订货单', 'A1904280001');
INSERT INTO `sale_goods_product` VALUES ('35', '33', 'TMTR6R 0.2L15 BXC', null, 'TMTR6R 0.2L15 BXC', 'ABH替换式', '20', '斤', '100.00', '100.00', '80.00', '64.00', 'A001', '四楼', null, '2019-04-29 10:47:43', '0', '订货单', 'A1904280001');

-- ----------------------------
-- Table structure for sale_offer_product
-- ----------------------------
DROP TABLE IF EXISTS `sale_offer_product`;
CREATE TABLE `sale_offer_product` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '销售-报价产品',
  `quotation_id` bigint(20) DEFAULT NULL,
  `product_no` varchar(50) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(50) DEFAULT NULL COMMENT '产品名称',
  `category` varchar(50) DEFAULT NULL COMMENT '品类',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `unit` varchar(50) DEFAULT NULL COMMENT '单位',
  `pricing` decimal(10,2) DEFAULT NULL COMMENT '定价',
  `discount` varchar(50) DEFAULT NULL COMMENT '折扣',
  `price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `money` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  `userid` bigint(20) DEFAULT NULL COMMENT '添加用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sale_offer_product
-- ----------------------------
INSERT INTO `sale_offer_product` VALUES ('56', '20', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', 'ABH替换式', '20', '斤', '100.00', '100', '100.00', '2000.00', '', '2019-04-28 21:57:53', '1');
INSERT INTO `sale_offer_product` VALUES ('57', '21', 'HTSEGN532', 'HTSEGN532', 'ABH替换式', '30', '斤', '0.00', '100', '80.00', '2400.00', '', '2019-04-29 09:43:21', '1');
INSERT INTO `sale_offer_product` VALUES ('58', '22', 'SGSDS', 'SGSDS', 'ABH替换式', '30', '斤', '100.00', '90', '90.00', '2700.00', '', '2019-04-29 10:41:25', '1');
INSERT INTO `sale_offer_product` VALUES ('59', '23', 'KANDAK 16LGH89', 'KANDAK 16LGH89', 'ABH替换式', '20', '斤', '25.00', '100', '25.00', '500.00', '', '2019-05-06 09:55:31', '1');

-- ----------------------------
-- Table structure for sale_online_order
-- ----------------------------
DROP TABLE IF EXISTS `sale_online_order`;
CREATE TABLE `sale_online_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '销售-网上订单',
  `order_date` datetime DEFAULT NULL COMMENT '订货日期',
  `customer_no` varchar(255) DEFAULT NULL COMMENT '客户编号',
  `order_remark` varchar(255) DEFAULT NULL COMMENT '订单备注',
  `order_no` varchar(255) DEFAULT NULL COMMENT '订货编号',
  `customer_no_str` varchar(255) DEFAULT NULL COMMENT '客户编号备注',
  `order_people` varchar(255) DEFAULT NULL COMMENT '订货人',
  `contact` varchar(255) DEFAULT NULL COMMENT '联系人',
  `zip` varchar(255) DEFAULT NULL COMMENT '邮政编码',
  `invoice_title` varchar(255) DEFAULT NULL COMMENT '发票抬头',
  `invoice_address` varchar(255) DEFAULT NULL COMMENT '发票地址',
  `audit` varchar(255) DEFAULT NULL COMMENT '审核',
  `update_last` varchar(255) DEFAULT NULL COMMENT '最后修改人',
  `invalid_people` varchar(255) DEFAULT NULL COMMENT '作废人',
  `base_remark` varchar(255) DEFAULT NULL COMMENT '基本资料备注',
  `phone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `fax` varchar(255) DEFAULT NULL COMMENT '传真',
  `delivery_address` varchar(255) DEFAULT NULL COMMENT '送货地址',
  `audit_str` varchar(255) DEFAULT NULL COMMENT '审核备注',
  `update_last_str` varchar(255) DEFAULT NULL COMMENT '最后修改人备注',
  `invalid_people_str` varchar(255) DEFAULT NULL COMMENT '作废人备注',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  `invalid` tinyint(1) DEFAULT NULL COMMENT '作废',
  `order_audit` tinyint(1) DEFAULT '0' COMMENT '单据审核状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sale_online_order
-- ----------------------------
INSERT INTO `sale_online_order` VALUES ('9', '2019-04-28 00:00:00', '20190428215126', null, 'A1904280001', '上海君之道信息有限公司', '上海君之道信息有限公司', '陈先生', '42100', null, null, null, 'Administrator', null, null, '17521282266', '652412', '上海市普陀区中江路638号天地软件园27号207', null, '2019-04-28', null, '2019-04-28 23:07:19', '0', '0');

-- ----------------------------
-- Table structure for sale_online_order_product
-- ----------------------------
DROP TABLE IF EXISTS `sale_online_order_product`;
CREATE TABLE `sale_online_order_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '销售-网上订单-订货产品',
  `online_order_id` bigint(20) DEFAULT NULL COMMENT '网上订单id',
  `customer_no` varchar(11) DEFAULT NULL COMMENT '客户编号',
  `product_no` varchar(255) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `category` varchar(255) DEFAULT NULL COMMENT '品类',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `unit` varchar(255) DEFAULT NULL COMMENT '单位',
  `price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `money` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `usable_num` int(11) DEFAULT NULL COMMENT '可用数量',
  `ifstock` tinyint(1) DEFAULT NULL COMMENT '是否有货',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sale_online_order_product
-- ----------------------------
INSERT INTO `sale_online_order_product` VALUES ('6', '9', null, 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', 'ABH替换式', '30', '斤', '100.00', '2000.00', '92', '1', '', '2019-04-28 23:07:19');

-- ----------------------------
-- Table structure for sale_purchase_order
-- ----------------------------
DROP TABLE IF EXISTS `sale_purchase_order`;
CREATE TABLE `sale_purchase_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '销售-订货单',
  `create_date` datetime DEFAULT NULL COMMENT '制单日期',
  `order_no` varchar(50) DEFAULT NULL COMMENT '订货单号',
  `customer_no` varchar(50) DEFAULT NULL COMMENT '客户编号',
  `warehouse_out` varchar(11) DEFAULT NULL COMMENT '出货仓库',
  `customer_no_str` varchar(255) DEFAULT NULL COMMENT '客户编号备注',
  `customer_order_no` varchar(255) DEFAULT NULL COMMENT '客户订单号',
  `special_order` tinyint(1) DEFAULT NULL COMMENT '特价单',
  `tax` varchar(50) DEFAULT NULL COMMENT '税别',
  `currency` varchar(50) DEFAULT NULL COMMENT '币别',
  `discount` varchar(50) DEFAULT NULL COMMENT '折扣',
  `invalid` tinyint(1) DEFAULT NULL COMMENT '作废',
  `customer_category` varchar(255) DEFAULT NULL COMMENT '客户类别',
  `receivable_balance` decimal(10,0) DEFAULT NULL COMMENT '应收余额',
  `business_leader` varchar(255) DEFAULT NULL COMMENT '业务负责',
  `business_leader_str` varchar(255) DEFAULT NULL COMMENT '业务负责备注',
  `payment_method` varchar(11) DEFAULT NULL COMMENT '结算方式',
  `made_people` varchar(255) DEFAULT NULL COMMENT '制单人',
  `audit_people` varchar(255) DEFAULT NULL COMMENT '审核人',
  `audit_people_str` varchar(255) DEFAULT NULL COMMENT '审核人备注',
  `last_update` varchar(20) DEFAULT NULL COMMENT '最后修改人',
  `last_update_str` varchar(255) DEFAULT NULL COMMENT '最后修改人备注',
  `customer_name` varchar(50) DEFAULT NULL COMMENT '客户名称',
  `zip` varchar(20) DEFAULT NULL COMMENT '邮政编码',
  `contact` varchar(20) DEFAULT NULL COMMENT '联系人',
  `fax` varchar(20) DEFAULT NULL COMMENT '传真',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `shipping_address` varchar(255) DEFAULT NULL COMMENT '送货地址',
  `invoice_title` varchar(100) DEFAULT NULL COMMENT '发票抬头',
  `invoice_address` varchar(255) DEFAULT NULL COMMENT '发票地址',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  `warehouse_out_str` varchar(255) DEFAULT NULL COMMENT '出货仓库备注',
  `order_audit` tinyint(1) DEFAULT '0' COMMENT '订单审核状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sale_purchase_order
-- ----------------------------
INSERT INTO `sale_purchase_order` VALUES ('28', '2019-04-28 00:00:00', 'A1904280001', '20190428215126', '309', '上海君之道信息有限公司', 'A1904280001', '0', '外加', 'RMB', '100', '0', '目标客户', null, '(A005)财务测试人员', '财务测试人员', '先款后货', 'Administrator', 'Administrator', '2019-04-29', 'Administrator', '2019-04-29', '上海君之道信息有限公司', '21000', '陈先生', '652412', '17521282266', '上海市普陀区中江路638号天地软件园27号207', null, null, '2019-04-28 22:13:08', 'A区仓库', '1');

-- ----------------------------
-- Table structure for sale_purchase_order_product
-- ----------------------------
DROP TABLE IF EXISTS `sale_purchase_order_product`;
CREATE TABLE `sale_purchase_order_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '销售-订货单-订货产品',
  `purchase_order_id` bigint(20) DEFAULT NULL COMMENT '订货单id',
  `product_no` varchar(50) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(50) DEFAULT NULL COMMENT '产品名称',
  `category` varchar(50) DEFAULT NULL COMMENT '品类',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `unit` varchar(20) DEFAULT NULL COMMENT '单位',
  `pricing` decimal(10,2) DEFAULT NULL COMMENT '订价',
  `discount` varchar(50) DEFAULT NULL COMMENT '折扣',
  `price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `money` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `order_no` varchar(50) DEFAULT NULL COMMENT '订货单号',
  `product_source` varchar(100) DEFAULT NULL COMMENT '产品来源',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  `sale_num` int(11) DEFAULT NULL COMMENT '销货数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sale_purchase_order_product
-- ----------------------------
INSERT INTO `sale_purchase_order_product` VALUES ('28', '28', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', 'ABH替换式', '20', '斤', '100.00', '100', '100.00', '2000.00', 'A1904280001', '报价单', null, '2019-04-28 22:13:08', '20');

-- ----------------------------
-- Table structure for sale_quotation
-- ----------------------------
DROP TABLE IF EXISTS `sale_quotation`;
CREATE TABLE `sale_quotation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '报价单',
  `create_date` datetime DEFAULT NULL COMMENT '制单日期',
  `offer_no` varchar(50) DEFAULT NULL COMMENT '报价单号',
  `enquiry_date` datetime DEFAULT NULL COMMENT '询价日期',
  `customer_no` varchar(50) DEFAULT NULL COMMENT '客户编号',
  `customer_no_str` varchar(255) DEFAULT NULL COMMENT '客户编号备注',
  `business` varchar(50) DEFAULT NULL COMMENT '业务负责',
  `business_str` varchar(255) DEFAULT NULL COMMENT '业务负责备注',
  `tax` varchar(50) DEFAULT NULL COMMENT '税别',
  `discount` varchar(20) DEFAULT NULL COMMENT '折扣',
  `special_offer` tinyint(1) DEFAULT NULL COMMENT '特价单',
  `amount_receivable` varchar(50) DEFAULT NULL COMMENT '应收余额',
  `valid_until` datetime DEFAULT NULL COMMENT '有效期至',
  `currency` varchar(50) DEFAULT NULL COMMENT '币别',
  `single_people` varchar(50) DEFAULT NULL COMMENT '制单人',
  `audit` varchar(50) DEFAULT NULL COMMENT '审核人',
  `audit_str` varchar(255) DEFAULT NULL COMMENT '审核人备注',
  `last_modifier` varchar(50) DEFAULT NULL COMMENT '最后修改人',
  `last_modifier_str` varchar(255) DEFAULT NULL COMMENT '最后修改人备注',
  `customer_name` varchar(50) DEFAULT NULL COMMENT '客户姓名',
  `customer_category` varchar(50) DEFAULT NULL COMMENT '客户类别',
  `contact` varchar(20) DEFAULT NULL COMMENT '联系人',
  `telephone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `fax` varchar(50) DEFAULT NULL COMMENT '传真',
  `address` varchar(255) DEFAULT NULL COMMENT '送货地址',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  `order_audit` tinyint(1) DEFAULT '0' COMMENT '单据审核状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sale_quotation
-- ----------------------------
INSERT INTO `sale_quotation` VALUES ('20', '2019-04-28 00:00:00', 'A1904280001', '2019-04-28 00:00:00', '20190428215126', '上海君之道信息有限公司', '(A005)财务测试人员', '财务测试人员', '外加', '100', '0', null, '2019-05-28 00:00:00', 'RMB', 'Administrator', '销售测试账号', '2019-04-28', 'Administrator', '2019-04-28', '上海君之道信息有限公司', '目标客户', '陈先生', '17521282266', '652412', '上海市普陀区中江路638号天地软件园27号207', '2019-04-28 21:57:53', '1');
INSERT INTO `sale_quotation` VALUES ('21', '2019-04-29 00:00:00', 'A1904290001', '2019-04-29 00:00:00', '20190428215126', '上海君之道信息有限公司', '(A005)财务测试人员', '财务测试人员', '外加', '100', '0', null, '2019-05-29 00:00:00', 'RMB', 'Administrator', 'Administrator', '2019-04-29', 'Administrator', '2019-04-29', '上海君之道信息有限公司', '目标客户', '陈先生', '17521282266', '652412', '上海市普陀区中江路638号天地软件园27号207', '2019-04-29 09:43:21', '1');
INSERT INTO `sale_quotation` VALUES ('22', '2019-04-29 00:00:00', 'A1904290002', '2019-04-29 00:00:00', '20190428215126', '上海君之道信息有限公司', '(A005)财务测试人员', '财务测试人员', '外加', '90', '0', null, '2019-05-29 00:00:00', 'RMB', 'Administrator', 'Administrator', '2019-04-29', 'Administrator', '2019-04-29', '上海君之道信息有限公司', '目标客户', '陈先生', '17521282266', '652412', '上海市普陀区中江路638号天地软件园27号207', '2019-04-29 10:41:25', '1');
INSERT INTO `sale_quotation` VALUES ('23', '2019-05-06 00:00:00', 'A1905060001', '2019-05-06 00:00:00', '20190428215126', '上海君之道信息有限公司', '(A005)财务测试人员', '财务测试人员', '外加', '100', '0', null, '2019-06-06 00:00:00', 'RMB', 'Administrator', null, null, 'Administrator', '2019-05-06', '上海君之道信息有限公司', '目标客户', '陈先生', '17521282266', '652412', '上海市普陀区中江路638号天地软件园27号207', '2019-05-06 09:55:31', '0');

-- ----------------------------
-- Table structure for sale_return_goods
-- ----------------------------
DROP TABLE IF EXISTS `sale_return_goods`;
CREATE TABLE `sale_return_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '销售-销售退货单',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `pinback_no` varchar(255) DEFAULT NULL COMMENT '销退单号',
  `early_document` tinyint(1) DEFAULT NULL COMMENT '前期单据',
  `customer_no` varchar(50) DEFAULT NULL COMMENT '客户编号',
  `customer_no_str` varchar(255) DEFAULT NULL COMMENT '客户编号描述',
  `business_leader` varchar(50) DEFAULT NULL COMMENT '业务负责人',
  `business_leader_str` varchar(255) DEFAULT NULL COMMENT '业务负责人描述',
  `tax` varchar(50) DEFAULT NULL COMMENT '税别',
  `currency` varchar(50) DEFAULT NULL COMMENT '币别',
  `warehouse_in` varchar(50) DEFAULT NULL COMMENT '入库仓库',
  `warehouse_in_str` varchar(255) DEFAULT NULL COMMENT '入库仓库描述',
  `return_reason` varchar(50) DEFAULT NULL COMMENT '退货原因',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `return_reason_people` varchar(50) DEFAULT NULL COMMENT '退货申请人',
  `made_people` varchar(50) DEFAULT NULL COMMENT '制单人',
  `audit_people` varchar(50) DEFAULT NULL COMMENT '审核人',
  `audit_people_str` varchar(255) DEFAULT NULL COMMENT '审核人描述',
  `last_update` varchar(50) DEFAULT NULL COMMENT '最后修改人',
  `last_update_str` varchar(255) DEFAULT NULL COMMENT '最后修改人描述',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  `order_audit` tinyint(1) DEFAULT '0' COMMENT '单据审核状态',
  `invalid` tinyint(1) DEFAULT '0' COMMENT '作废',
  `tax_rate` float(10,2) DEFAULT NULL COMMENT '税率',
  `ticket` tinyint(1) DEFAULT '0' COMMENT '销项发票开票',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sale_return_goods
-- ----------------------------
INSERT INTO `sale_return_goods` VALUES ('22', '2019-04-28 00:00:00', 'A1904280001', '1', '20190428215126', '上海君之道信息有限公司', '(A005)财务测试人员', '财务测试人员', '外加', 'RMB', '309', 'A区仓库', '质量问题', null, null, '销售测试账号', 'Administrator', '2019-04-28', 'Administrator', '2019-04-28', '2019-04-28 23:05:38', '1', '0', '0.16', '0');

-- ----------------------------
-- Table structure for sale_return_goods_product
-- ----------------------------
DROP TABLE IF EXISTS `sale_return_goods_product`;
CREATE TABLE `sale_return_goods_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '销售-销售退货单-销退产品',
  `return_goods_id` bigint(20) DEFAULT NULL COMMENT '销售退货单id',
  `product_no` varchar(50) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(50) DEFAULT NULL COMMENT '产品名称',
  `category` varchar(50) DEFAULT NULL COMMENT '品类',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `unit` varchar(20) DEFAULT NULL COMMENT '单位',
  `pricing` decimal(10,2) DEFAULT NULL COMMENT '定价',
  `price` decimal(10,2) DEFAULT '0.00' COMMENT '单价',
  `money` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `source` varchar(50) DEFAULT NULL COMMENT '来源',
  `order_no` varchar(50) DEFAULT NULL COMMENT '订单号',
  `warehouse_position` varchar(50) DEFAULT NULL COMMENT '库位',
  `floor` varchar(50) DEFAULT NULL COMMENT '楼层',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  `inbound_num` int(11) DEFAULT '0' COMMENT '入库数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='销售-销售退货单-销退产品';

-- ----------------------------
-- Records of sale_return_goods_product
-- ----------------------------
INSERT INTO `sale_return_goods_product` VALUES ('9', '22', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', 'ABH替换式', '2', '斤', '100.00', '100.00', '200.00', '销货单', 'A1904280001', 'A001', '四楼', null, '2019-04-28 23:05:38', '2');

-- ----------------------------
-- Table structure for stock_change
-- ----------------------------
DROP TABLE IF EXISTS `stock_change`;
CREATE TABLE `stock_change` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `changedate` date DEFAULT NULL COMMENT '异动日期',
  `changeorder` varchar(255) DEFAULT '' COMMENT '异动单号',
  `depotorder` varchar(255) DEFAULT '' COMMENT '异动仓库编号',
  `depotname` varchar(255) DEFAULT '' COMMENT '异动仓库名称',
  `changetype` int(11) DEFAULT '0' COMMENT '异动类型',
  `peopleorder` varchar(255) DEFAULT '' COMMENT '异动申请人编号',
  `peoplename` varchar(255) DEFAULT '' COMMENT '异动申请人名称',
  `changecurrency` int(255) DEFAULT '0' COMMENT '币率',
  `changesourse` varchar(255) DEFAULT '' COMMENT '异动来源',
  `createpeople` varchar(255) DEFAULT '' COMMENT '制单人员',
  `shpeople` varchar(255) DEFAULT '' COMMENT '审核人',
  `shdate` varchar(255) DEFAULT '' COMMENT '审核时间',
  `updatepeople` varchar(255) DEFAULT '' COMMENT '最后修改人',
  `updatedate` varchar(255) DEFAULT '' COMMENT '最后修改时间',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `shstatus` int(11) DEFAULT '0' COMMENT '是否审核 默认、0      已审核、1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='库存异动表';

-- ----------------------------
-- Records of stock_change
-- ----------------------------
INSERT INTO `stock_change` VALUES ('12', '2019-04-28', 'A1904280001', '309', 'A区仓库', '1', 'A004', '仓库测试人员', '1', '盘库作业', 'Administrator', '', '', '', '', '', '1');
INSERT INTO `stock_change` VALUES ('13', '2019-04-28', 'A1904280002', '309', 'A区仓库', '2', 'A004', '仓库测试人员', '1', '销售退货单', 'Administrator', '', '', '', '', '', '1');
INSERT INTO `stock_change` VALUES ('14', '2019-04-29', 'A1904290001', '309', 'A区仓库', '1', 'A004', '仓库测试人员', '1', '盘库作业', 'Administrator', 'Administrator', '2019-04-29', '', '', '', '1');
INSERT INTO `stock_change` VALUES ('15', '2019-05-05', 'A1905050001', '310', 'B区仓库', '1', 'A004', '仓库测试人员', '1', '盘库作业', 'Administrator', 'Administrator', '2019-05-05', '', '', '', '1');

-- ----------------------------
-- Table structure for stock_change_product
-- ----------------------------
DROP TABLE IF EXISTS `stock_change_product`;
CREATE TABLE `stock_change_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `soursebill` varchar(255) DEFAULT '' COMMENT '来源单据',
  `sourseorder` varchar(255) DEFAULT '' COMMENT '订单编号',
  `sort` varchar(255) DEFAULT '' COMMENT '序号',
  `productorder` varchar(255) DEFAULT '' COMMENT '产品编号',
  `productname` varchar(255) DEFAULT '' COMMENT '产品名称',
  `productnum` int(11) DEFAULT '0' COMMENT '产品数量',
  `unit` varchar(255) DEFAULT '' COMMENT '单位',
  `depotorder` varchar(255) DEFAULT '' COMMENT '库位编号',
  `depotname` varchar(255) DEFAULT '' COMMENT '库位名称',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `changestockid` bigint(20) DEFAULT '0' COMMENT '库存异动编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='库存异动产品';

-- ----------------------------
-- Records of stock_change_product
-- ----------------------------
INSERT INTO `stock_change_product` VALUES ('18', '盘库作业', 'A1904280001', '1', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', '10', '斤', 'A001', '4楼库位', '', '12');
INSERT INTO `stock_change_product` VALUES ('19', '销售退货单', 'A1904280001', '1', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', '2', '斤', 'A001', '4楼库位', null, '13');
INSERT INTO `stock_change_product` VALUES ('20', '盘库作业', 'A1904290001', '1', 'SGSDS', 'SGSDS', '10', '斤', 'A001', '4楼库位', '', '14');
INSERT INTO `stock_change_product` VALUES ('21', '盘库作业', 'A1905050001', '1', 'KANDAK 16LGH89', 'KANDAK 16LGH89', '1', '斤', 'A002', 'A002', '', '15');

-- ----------------------------
-- Table structure for stock_out_sale
-- ----------------------------
DROP TABLE IF EXISTS `stock_out_sale`;
CREATE TABLE `stock_out_sale` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '销货出库单',
  `create_date` datetime DEFAULT NULL COMMENT '制单日期',
  `outbound_no` varchar(50) DEFAULT NULL COMMENT '出库单号',
  `sale_no` varchar(50) DEFAULT NULL COMMENT '销货出库单号',
  `warehouse_in` varchar(50) DEFAULT NULL COMMENT '入库仓库',
  `warehouse_in_str` varchar(255) DEFAULT NULL COMMENT '入库仓库描述',
  `customer_no` varchar(50) DEFAULT NULL COMMENT '客户编号',
  `customer_no_str` varchar(255) DEFAULT NULL COMMENT '客户编号描述',
  `made_people` varchar(50) DEFAULT NULL COMMENT '制单人',
  `audit` varchar(50) DEFAULT NULL COMMENT '审核人',
  `audit_str` varchar(255) DEFAULT NULL COMMENT '审核人描述',
  `last_update` varchar(50) DEFAULT NULL COMMENT '最后修改人',
  `last_update_str` varchar(255) DEFAULT NULL COMMENT '最后修改人描述',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  `order_audit` tinyint(1) DEFAULT NULL COMMENT '审核状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stock_out_sale
-- ----------------------------
INSERT INTO `stock_out_sale` VALUES ('22', '2019-04-28 00:00:00', 'A1904280001', 'A1904280001', '309', 'A区仓库', '20190428215126', '上海君之道信息有限公司', 'Administrator', 'Administrator', '2019-04-28', 'Administrator', '2019-04-28', '2019-04-28 22:27:48', '1');
INSERT INTO `stock_out_sale` VALUES ('23', '2019-04-29 00:00:00', 'A1904290001', 'A1904290001', '325', 'C区', '20190428215126', '上海君之道信息有限公司', 'Administrator', 'Administrator', '2019-04-29', 'Administrator', '2019-04-29', '2019-04-29 10:31:21', '1');

-- ----------------------------
-- Table structure for stock_out_sale_product
-- ----------------------------
DROP TABLE IF EXISTS `stock_out_sale_product`;
CREATE TABLE `stock_out_sale_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '销货出库单-产品',
  `stock_out_sale_id` bigint(20) DEFAULT NULL COMMENT '销货出库单id',
  `order_source` varchar(255) DEFAULT NULL COMMENT '单据来源',
  `order_no` varchar(255) DEFAULT NULL COMMENT '订单号',
  `product_no` varchar(255) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `category` varchar(255) DEFAULT NULL COMMENT '品类',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `unit` varchar(50) DEFAULT NULL COMMENT '单位',
  `price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `warehouse_name` varchar(50) DEFAULT NULL COMMENT '仓库名称',
  `floor` varchar(50) DEFAULT NULL COMMENT '楼层',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stock_out_sale_product
-- ----------------------------
INSERT INTO `stock_out_sale_product` VALUES ('16', '22', '销货单', 'A1904280001', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', 'ABH替换式', '20', '斤', '100.00', 'A001', '四楼', null, '2019-04-28 22:27:48');
INSERT INTO `stock_out_sale_product` VALUES ('17', '23', '销货单', 'A1904290001', 'HTSEGN532', 'HTSEGN532', 'ABH替换式', '30', '斤', '80.00', 'A003', '一楼', null, '2019-04-29 10:31:21');

-- ----------------------------
-- Table structure for supplier_basic
-- ----------------------------
DROP TABLE IF EXISTS `supplier_basic`;
CREATE TABLE `supplier_basic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `idnum` varchar(255) DEFAULT '' COMMENT '编号',
  `supdes` varchar(255) DEFAULT '' COMMENT '供应商简称',
  `supname` varchar(255) DEFAULT '' COMMENT '供应商名称',
  `regadd` varchar(255) DEFAULT '' COMMENT '注册地址',
  `country` int(11) DEFAULT '1' COMMENT '国家',
  `area` varchar(255) DEFAULT '' COMMENT '地区',
  `postalcode` varchar(255) DEFAULT '' COMMENT '邮政编码',
  `phonetype` int(11) DEFAULT '1' COMMENT '电话类型',
  `phone` varchar(255) DEFAULT '' COMMENT '电话',
  `faxtype` int(11) DEFAULT '1' COMMENT '传真类型',
  `fax` varchar(255) DEFAULT '' COMMENT '传真',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `addpeople` varchar(255) DEFAULT '' COMMENT '建档人',
  `adddate` varchar(255) DEFAULT '' COMMENT '建档日期',
  `updatepeople` varchar(255) DEFAULT '' COMMENT '最后修改人',
  `updatedate` varchar(255) DEFAULT '' COMMENT '最后修改日期',
  `isstop` int(11) DEFAULT '0' COMMENT '是否暂停',
  `stopdes` varchar(255) DEFAULT '' COMMENT '暂停说明',
  `comeandgo` int(11) DEFAULT '0' COMMENT '禁止来往',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of supplier_basic
-- ----------------------------
INSERT INTO `supplier_basic` VALUES ('3', 'A001', '三禄刀具', '上海三禄贸易刀具', '上海市浦东新区', '1', '上海', '421000', '1', '电话1;1752126442', '1', '传真1;176251512', '测试', 'Administrator', '2019-04-28', 'Administrator', '2019-04-29', '0', '', '0');
INSERT INTO `supplier_basic` VALUES ('4', 'A002', '测试', '测试', '上海', '1', '上海', '12323', '1', '电话1,电话2;1752271612,662512', '1', '传真2,传真1;34412,1762323', '测试', 'Administrator', '2019-04-29', 'Administrator', '2019-04-29', '0', '', '0');

-- ----------------------------
-- Table structure for supplier_contact
-- ----------------------------
DROP TABLE IF EXISTS `supplier_contact`;
CREATE TABLE `supplier_contact` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增编号',
  `keycontact` int(11) DEFAULT '0' COMMENT '主要联系人',
  `uname` varchar(255) DEFAULT '' COMMENT '姓名',
  `udepartment` varchar(255) DEFAULT '' COMMENT '部门',
  `ujob` varchar(255) DEFAULT '' COMMENT '职务',
  `uphone` varchar(255) DEFAULT '' COMMENT '电话',
  `ufax` varchar(255) DEFAULT '' COMMENT '传真',
  `umobile` varchar(255) DEFAULT '' COMMENT '移动电话',
  `uemail` varchar(255) DEFAULT '' COMMENT 'Email',
  `uremarks` varchar(255) DEFAULT '' COMMENT '备注',
  `ispoint` int(11) DEFAULT '0' COMMENT '是否主要联系人',
  `supplierid` bigint(20) DEFAULT '1' COMMENT '供应商编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of supplier_contact
-- ----------------------------
INSERT INTO `supplier_contact` VALUES ('19', '1', '瞿先生', '生产部', '经理', '17521286225', '9298121', '17521286225', null, null, '0', '3');
INSERT INTO `supplier_contact` VALUES ('20', '1', '采购先生', '采购部', '员工', '123444123', '4123123', '1726152112', null, null, '0', '4');
INSERT INTO `supplier_contact` VALUES ('21', '0', '马先生', '销售部', '员工', '123444123', '144123123', '1244123123', null, null, '0', '3');

-- ----------------------------
-- Table structure for supplier_purchaser
-- ----------------------------
DROP TABLE IF EXISTS `supplier_purchaser`;
CREATE TABLE `supplier_purchaser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增编号',
  `staffcode` varchar(255) DEFAULT '' COMMENT '员工编号',
  `staffid` bigint(20) DEFAULT '0' COMMENT '员工编号',
  `staffname` varchar(255) DEFAULT '' COMMENT '姓名',
  `keycontent` int(11) DEFAULT '0' COMMENT '主要负责人',
  `staffremarks` varchar(255) DEFAULT '' COMMENT '备注',
  `ispoint` int(11) DEFAULT '0' COMMENT '是否是主要负责人',
  `supplierid` bigint(20) DEFAULT '1' COMMENT '供应商编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of supplier_purchaser
-- ----------------------------
INSERT INTO `supplier_purchaser` VALUES ('15', 'A003', '1', '采购测试人员', '0', '测试人员', '0', '3');
INSERT INTO `supplier_purchaser` VALUES ('18', 'A001', '1', 'Administrator', '1', '', '0', '3');
INSERT INTO `supplier_purchaser` VALUES ('19', 'A003', '1', '采购测试人员', '1', '测试人员', '0', '4');

-- ----------------------------
-- Table structure for tax_rate
-- ----------------------------
DROP TABLE IF EXISTS `tax_rate`;
CREATE TABLE `tax_rate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `starttime` date DEFAULT NULL COMMENT '开始时间',
  `endtime` date DEFAULT NULL COMMENT '结束时间',
  `nums` double DEFAULT NULL COMMENT '税率',
  `types` int(11) DEFAULT '0' COMMENT '税率类型  1、进项  2、销项',
  `addtime` date DEFAULT NULL COMMENT '记录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='税率设置';

-- ----------------------------
-- Records of tax_rate
-- ----------------------------
INSERT INTO `tax_rate` VALUES ('1', '2019-01-01', null, '0.16', '1', '2019-04-04');
INSERT INTO `tax_rate` VALUES ('2', '2019-01-01', null, '0.16', '2', '2019-04-04');

-- ----------------------------
-- Table structure for timecard
-- ----------------------------
DROP TABLE IF EXISTS `timecard`;
CREATE TABLE `timecard` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `employeeorder` varchar(255) DEFAULT '' COMMENT '员工编号',
  `employeename` varchar(255) DEFAULT '' COMMENT '员工名称',
  `createtime` varchar(255) DEFAULT '' COMMENT '创建时间',
  `status` int(11) DEFAULT '1' COMMENT '状态 1、考勤资料  2、食堂资料',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of timecard
-- ----------------------------

-- ----------------------------
-- Table structure for transportation_inventory
-- ----------------------------
DROP TABLE IF EXISTS `transportation_inventory`;
CREATE TABLE `transportation_inventory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orders` varchar(255) DEFAULT '' COMMENT '装箱单号',
  `senddate` date DEFAULT NULL COMMENT '发货日期',
  `invoicenum` varchar(255) DEFAULT '' COMMENT '发票号码',
  `comedate` date DEFAULT NULL COMMENT '预计到货日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='在途库存';

-- ----------------------------
-- Records of transportation_inventory
-- ----------------------------
INSERT INTO `transportation_inventory` VALUES ('7', '66666666', '2019-04-28', '82721721', '2019-05-08');
INSERT INTO `transportation_inventory` VALUES ('9', '47777', '2019-04-29', '33333', '2019-05-09');
INSERT INTO `transportation_inventory` VALUES ('12', '87878787', '2019-05-03', '23124123', '2019-05-13');
INSERT INTO `transportation_inventory` VALUES ('20', '67676767', '2019-05-03', '4123123', '2019-05-13');
INSERT INTO `transportation_inventory` VALUES ('22', '3123', '2019-05-05', '123123', '2019-05-15');
INSERT INTO `transportation_inventory` VALUES ('23', '44444321', '2019-05-05', '423123123', '2019-05-15');
INSERT INTO `transportation_inventory` VALUES ('24', '122222131', '2019-05-07', '4123', '2019-05-17');

-- ----------------------------
-- Table structure for transportation_product
-- ----------------------------
DROP TABLE IF EXISTS `transportation_product`;
CREATE TABLE `transportation_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parentid` bigint(20) DEFAULT '0' COMMENT '在途库存编号',
  `purchaseorder` varchar(255) DEFAULT '' COMMENT '采购单号',
  `sort` int(11) DEFAULT '0' COMMENT '序号',
  `seeorder` varchar(255) DEFAULT '' COMMENT '参考单号',
  `pronum` varchar(255) DEFAULT '' COMMENT '产品编号',
  `proname` varchar(255) DEFAULT '' COMMENT '产品名称',
  `stocknum` varchar(255) DEFAULT '' COMMENT '库位',
  `boxnum` varchar(255) DEFAULT '' COMMENT '箱号',
  `thistimeontheway` int(11) DEFAULT '0' COMMENT '本次在途数量',
  `totalnum` int(11) DEFAULT '0' COMMENT '订货数量',
  `stockover` int(11) DEFAULT '0' COMMENT '已入库数量',
  `forcenum` int(11) DEFAULT '0' COMMENT '已强制结案数量',
  `ontheway` int(11) DEFAULT '0' COMMENT '已在途数量',
  `depotnum` varchar(255) DEFAULT '' COMMENT '库位编号',
  `depotname` varchar(255) DEFAULT '' COMMENT '库位名称',
  `depotfloor` varchar(255) DEFAULT '' COMMENT '楼层',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='在途产品';

-- ----------------------------
-- Records of transportation_product
-- ----------------------------
INSERT INTO `transportation_product` VALUES ('5', '7', 'A1904280001', '0', 'F1904280001', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', 'A区仓库', '', '100', '0', '100', '0', '0', 'A001', '4楼库位', '四楼', '');
INSERT INTO `transportation_product` VALUES ('7', '9', 'A1904290001', '1', 'F1904280001', 'HTSEGN532', 'HTSEGN532', 'C区', '23', '50', '50', '0', '50', '50', '', '', '', '');
INSERT INTO `transportation_product` VALUES ('10', '12', 'A1904290008', '1', 'F1904280001', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', 'A区仓库', '', '5', '20', '0', '15', '5', 'A001', '4楼库位', '四楼', '');
INSERT INTO `transportation_product` VALUES ('11', '12', 'A1904290008', '2', 'F1904280001', 'HTSEGN532', 'HTSEGN532', 'A区仓库', '', '10', '30', '0', '20', '10', 'A003', '测试库位', '一楼', '');
INSERT INTO `transportation_product` VALUES ('12', '12', 'A1904290008', '3', 'F1904280001', 'SGSDS', 'SGSDS', 'A区仓库', '', '10', '40', '0', '30', '10', '', '', '', '');
INSERT INTO `transportation_product` VALUES ('15', '20', 'A1905030001', '1', 'F1904280001', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', 'B区仓库', '', '100', '0', '100', '0', '0', 'A002', 'A002', '五楼', '');
INSERT INTO `transportation_product` VALUES ('16', '22', 'A1905040002', '1', 'F1904280001', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', '', '3213', '50', '100', '0', '50', '50', '', '', '', '');
INSERT INTO `transportation_product` VALUES ('17', '23', 'A1905050001', '1', 'F1904280001', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', '', '', '100', '100', '100', '100', '0', 'A002', 'A002', '五楼', '');
INSERT INTO `transportation_product` VALUES ('18', '23', 'A1905050001', '2', 'F1904280001', 'HTSEGN532', 'HTSEGN532', '', '', '100', '100', '100', '100', '0', 'A002', 'A002', '五楼', '');
INSERT INTO `transportation_product` VALUES ('19', '24', 'A1905070002', '1', 'F1904280001', 'TMTR6R 0.2L15 BXC', 'TMTR6R 0.2L15 BXC', '', '', '100', '500', '100', '200', '100', 'A002', 'A002', '五楼', '');

-- ----------------------------
-- Table structure for transport_basic
-- ----------------------------
DROP TABLE IF EXISTS `transport_basic`;
CREATE TABLE `transport_basic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `idnum` varchar(255) DEFAULT '' COMMENT '编号',
  `supdes` varchar(255) DEFAULT '' COMMENT '供应商简称',
  `supname` varchar(255) DEFAULT '' COMMENT '供应商名称',
  `regadd` varchar(255) DEFAULT '' COMMENT '注册地址',
  `country` int(11) DEFAULT '1' COMMENT '国家',
  `area` varchar(255) DEFAULT '' COMMENT '地区',
  `postalcode` varchar(255) DEFAULT '' COMMENT '邮政编码',
  `phonetype` int(11) DEFAULT '1' COMMENT '电话类型',
  `phone` varchar(255) DEFAULT '' COMMENT '电话',
  `faxtype` int(11) DEFAULT '1' COMMENT '传真类型',
  `fax` varchar(255) DEFAULT '' COMMENT '传真',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `addpeople` varchar(255) DEFAULT '' COMMENT '建档人',
  `adddate` varchar(255) DEFAULT '' COMMENT '建档日期',
  `updatepeople` varchar(255) DEFAULT '' COMMENT '最后修改人',
  `updatedate` varchar(255) DEFAULT '' COMMENT '最后修改日期',
  `isstop` int(11) DEFAULT '0' COMMENT '是否暂停',
  `stopdes` varchar(255) DEFAULT '' COMMENT '暂停说明',
  `comeandgo` int(11) DEFAULT '0' COMMENT '禁止来往',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of transport_basic
-- ----------------------------
INSERT INTO `transport_basic` VALUES ('5', 'A001', '运输商测试', '运输商测试', '上海浦东新区', '1', '上海', '00012', '1', '电话1;17521286225', '1', '传真1;928121', '测试', 'Administrator', '2019-04-28', 'Administrator', '2019-04-28', '0', '', '0');

-- ----------------------------
-- Table structure for transport_contact
-- ----------------------------
DROP TABLE IF EXISTS `transport_contact`;
CREATE TABLE `transport_contact` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增编号',
  `keycontact` int(11) DEFAULT '0' COMMENT '主要联系人',
  `uname` varchar(255) DEFAULT '' COMMENT '姓名',
  `udepartment` varchar(255) DEFAULT '' COMMENT '部门',
  `ujob` varchar(255) DEFAULT '' COMMENT '职务',
  `uphone` varchar(255) DEFAULT '' COMMENT '电话',
  `ufax` varchar(255) DEFAULT '' COMMENT '传真',
  `umobile` varchar(255) DEFAULT '' COMMENT '移动电话',
  `uemail` varchar(255) DEFAULT '' COMMENT 'Email',
  `uremarks` varchar(255) DEFAULT '' COMMENT '备注',
  `ispoint` int(11) DEFAULT '0' COMMENT '是否主要联系人',
  `supplierid` bigint(20) DEFAULT '1' COMMENT '供应商编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of transport_contact
-- ----------------------------
INSERT INTO `transport_contact` VALUES ('1', '0', '王运输', '运输部', '员工', '1752625112', null, null, null, null, '0', '5');

-- ----------------------------
-- Table structure for transport_purchaser
-- ----------------------------
DROP TABLE IF EXISTS `transport_purchaser`;
CREATE TABLE `transport_purchaser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增编号',
  `staffcode` varchar(255) DEFAULT '' COMMENT '员工编号',
  `staffid` bigint(20) DEFAULT '0' COMMENT '员工编号',
  `staffname` varchar(255) DEFAULT '' COMMENT '姓名',
  `keycontent` int(11) DEFAULT '0' COMMENT '主要负责人',
  `staffremarks` varchar(255) DEFAULT '' COMMENT '备注',
  `ispoint` int(11) DEFAULT '0' COMMENT '是否是主要负责人',
  `supplierid` bigint(20) DEFAULT '1' COMMENT '供应商编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of transport_purchaser
-- ----------------------------
INSERT INTO `transport_purchaser` VALUES ('17', 'A003', '1', '采购测试人员', '0', '测试人员', '0', '5');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'quzhangjing', '123123');

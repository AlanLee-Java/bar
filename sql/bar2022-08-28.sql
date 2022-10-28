/*
 Navicat Premium Data Transfer

 Source Server         : 124.71.12.51
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 124.71.12.51:3306
 Source Schema         : bar

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 28/08/2022 17:28:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `merchant_id` bigint(20) NOT NULL COMMENT '商家ID',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '活动名称',
  `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '活动图片',
  `color` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '颜色',
  `activity_time` datetime(0) NOT NULL COMMENT '活动时间',
  `refund_rule` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '退款规则说明',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '修改人',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '活动' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for activity_goods_join
-- ----------------------------
DROP TABLE IF EXISTS `activity_goods_join`;
CREATE TABLE `activity_goods_join`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `activity_id` bigint(20) NOT NULL COMMENT '活动ID',
  `goods_id` bigint(20) NOT NULL COMMENT '商品ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '修改人',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_activity_id`(`activity_id`) USING BTREE COMMENT '活动ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '活动商品关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for activity_guests
-- ----------------------------
DROP TABLE IF EXISTS `activity_guests`;
CREATE TABLE `activity_guests`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `activity_id` bigint(20) NOT NULL COMMENT '活动ID',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '嘉宾名字',
  `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '嘉宾图片',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '修改人',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '活动嘉宾' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for activity_refund_rule
-- ----------------------------
DROP TABLE IF EXISTS `activity_refund_rule`;
CREATE TABLE `activity_refund_rule`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `activity_id` bigint(20) NOT NULL COMMENT '活动ID',
  `refund_time` datetime(0) NOT NULL COMMENT '退款时间',
  `condition_type` tinyint(2) NOT NULL COMMENT '条件类型：1前，2后',
  `is_allow` tinyint(2) NOT NULL COMMENT '是否允许退款：0不允许，1允许',
  `refund_rate` decimal(5, 2) NOT NULL COMMENT '退款比例',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '修改人',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '活动退款规则' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `category_id` bigint(20) NOT NULL COMMENT '文章类型ID',
  `category_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '文章类型编码',
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '文章标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '文章内容',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '更新者',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '文章' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1, 1, 'BOOMB_SERVICE', '蹦嘣服务', '<p><b><span style=\"font-size: 14px;\">1.商家散票</span></b><img src=\"http://localhost/profile/upload/2022/08/11/ruoyi_20220811105500A001.png\"></p><p>&nbsp; &nbsp;用户可以在蹦嘣上选择商户购买散票。散票有效期为所</p><p>购商户当日开始时间至次日打烊时间。超过该时间段订单</p><p>无效。</p>', 'admin', '2022-08-11 10:50:46', 'admin', '2022-08-11 11:53:35');

-- ----------------------------
-- Table structure for article_category
-- ----------------------------
DROP TABLE IF EXISTS `article_category`;
CREATE TABLE `article_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '文章类型编码',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '文章类型名称',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '更新者',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_code`(`code`) USING BTREE COMMENT '唯一类型编码'
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '文章分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_category
-- ----------------------------
INSERT INTO `article_category` VALUES (1, 'BOOMB_SERVICE', '蹦嘣服务', 'admin', '2022-08-10 22:23:25', 'admin', '2022-08-11 10:59:53');

-- ----------------------------
-- Table structure for browse_history
-- ----------------------------
DROP TABLE IF EXISTS `browse_history`;
CREATE TABLE `browse_history`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `type` tinyint(2) NOT NULL COMMENT '浏览类型：1商家，2活动',
  `object_id` bigint(20) NOT NULL COMMENT '对象ID',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '浏览历史' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of browse_history
-- ----------------------------
INSERT INTO `browse_history` VALUES (1, 1, 1, 1, '2022-08-17 21:52:24', '2022-08-17 21:52:27');
INSERT INTO `browse_history` VALUES (2, 1, 1, 2, '2022-08-17 21:55:09', '2022-08-17 21:55:11');
INSERT INTO `browse_history` VALUES (3, 1, 1, 1, '2021-08-17 21:57:50', '2021-08-17 21:57:57');
INSERT INTO `browse_history` VALUES (4, 1, 1, 1, '2022-08-18 22:01:31', '2022-08-18 22:01:34');

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '反馈内容',
  `screenshot` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '反馈截图',
  `contact` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '联系方式',
  `status` tinyint(2) NOT NULL COMMENT '0：待处理，1：已处理',
  `handle_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '处理人',
  `handle_time` datetime(0) NULL DEFAULT NULL COMMENT '处理时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '处理备注',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '问题反馈' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作 sub主子表操作）',
  `package_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '代码生成业务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table
-- ----------------------------
INSERT INTO `gen_table` VALUES (2, 'article_category', '文章分类', '', NULL, 'ArticleCategory', 'crud', 'com.ruoyi.system', 'system', 'articleCategory', '文章分类', 'AlanLee', '0', '/', '{\"parentMenuId\":\"1062\",\"treeName\":\"\",\"treeParentCode\":\"\",\"parentMenuName\":\"运营管理\",\"treeCode\":\"\"}', 'admin', '2022-08-10 22:01:01', '', '2022-08-10 22:03:39', '');
INSERT INTO `gen_table` VALUES (3, 'article', '文章', '', NULL, 'Article', 'crud', 'com.ruoyi.system', 'system', 'article', '文章', 'AlanLee', '0', '/', '{\"parentMenuId\":\"1062\",\"treeName\":\"\",\"treeParentCode\":\"\",\"parentMenuName\":\"运营管理\",\"treeCode\":\"\"}', 'admin', '2022-08-11 10:34:09', '', '2022-08-11 10:39:03', '');
INSERT INTO `gen_table` VALUES (4, 'merchant', '商家', '', NULL, 'Merchant', 'crud', 'com.ruoyi.system', 'system', 'merchant', '商家管理', 'AlanLee', '0', '/', '{\"parentMenuId\":\"1062\",\"treeName\":\"\",\"treeParentCode\":\"\",\"parentMenuName\":\"运营管理\",\"treeCode\":\"\"}', 'admin', '2022-08-11 22:18:08', '', '2022-08-11 22:23:54', '');

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '字典类型',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '代码生成业务表字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
INSERT INTO `gen_table_column` VALUES (6, '2', 'id', '唯一主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-08-10 22:01:01', NULL, '2022-08-10 22:03:39');
INSERT INTO `gen_table_column` VALUES (7, '2', 'code', '文章类型编码', 'varchar(32)', 'String', 'code', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2022-08-10 22:01:01', NULL, '2022-08-10 22:03:39');
INSERT INTO `gen_table_column` VALUES (8, '2', 'name', '文章类型名称', 'varchar(32)', 'String', 'name', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2022-08-10 22:01:01', NULL, '2022-08-10 22:03:39');
INSERT INTO `gen_table_column` VALUES (9, '2', 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 4, 'admin', '2022-08-10 22:01:01', NULL, '2022-08-10 22:03:39');
INSERT INTO `gen_table_column` VALUES (10, '2', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, '1', NULL, 'EQ', 'datetime', '', 5, 'admin', '2022-08-10 22:01:01', NULL, '2022-08-10 22:03:39');
INSERT INTO `gen_table_column` VALUES (11, '2', 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2022-08-10 22:01:01', NULL, '2022-08-10 22:03:39');
INSERT INTO `gen_table_column` VALUES (12, '2', 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2022-08-10 22:01:01', NULL, '2022-08-10 22:03:39');
INSERT INTO `gen_table_column` VALUES (13, '3', 'id', '唯一主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-08-11 10:34:09', NULL, '2022-08-11 10:39:03');
INSERT INTO `gen_table_column` VALUES (14, '3', 'category_id', '文章类型ID', 'bigint(20)', 'Long', 'categoryId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 2, 'admin', '2022-08-11 10:34:09', NULL, '2022-08-11 10:39:03');
INSERT INTO `gen_table_column` VALUES (15, '3', 'category_code', '文章类型编码', 'varchar(32)', 'String', 'categoryCode', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 3, 'admin', '2022-08-11 10:34:09', NULL, '2022-08-11 10:39:03');
INSERT INTO `gen_table_column` VALUES (16, '3', 'title', '文章标题', 'varchar(32)', 'String', 'title', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2022-08-11 10:34:09', NULL, '2022-08-11 10:39:03');
INSERT INTO `gen_table_column` VALUES (17, '3', 'content', '文章内容', 'text', 'String', 'content', '0', '0', '1', '1', '1', '1', NULL, 'EQ', 'summernote', '', 5, 'admin', '2022-08-11 10:34:09', NULL, '2022-08-11 10:39:03');
INSERT INTO `gen_table_column` VALUES (18, '3', 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2022-08-11 10:34:09', NULL, '2022-08-11 10:39:03');
INSERT INTO `gen_table_column` VALUES (19, '3', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, '1', NULL, 'EQ', 'datetime', '', 7, 'admin', '2022-08-11 10:34:09', NULL, '2022-08-11 10:39:03');
INSERT INTO `gen_table_column` VALUES (20, '3', 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'input', '', 8, 'admin', '2022-08-11 10:34:10', NULL, '2022-08-11 10:39:03');
INSERT INTO `gen_table_column` VALUES (21, '3', 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'datetime', '', 9, 'admin', '2022-08-11 10:34:10', NULL, '2022-08-11 10:39:03');
INSERT INTO `gen_table_column` VALUES (22, '4', 'id', '唯一主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-08-11 22:18:08', NULL, '2022-08-11 22:23:54');
INSERT INTO `gen_table_column` VALUES (23, '4', 'type', '商户类型：1夜店，2酒吧', 'tinyint(2)', 'Integer', 'type', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 2, 'admin', '2022-08-11 22:18:08', NULL, '2022-08-11 22:23:54');
INSERT INTO `gen_table_column` VALUES (24, '4', 'name', '商家名称', 'varchar(32)', 'String', 'name', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2022-08-11 22:18:08', NULL, '2022-08-11 22:23:54');
INSERT INTO `gen_table_column` VALUES (25, '4', 'logo', '商家logo', 'varchar(255)', 'String', 'logo', '0', '0', '1', '1', '1', '1', NULL, 'EQ', 'upload', '', 4, 'admin', '2022-08-11 22:18:08', NULL, '2022-08-11 22:23:54');
INSERT INTO `gen_table_column` VALUES (26, '4', 'city', '商家城市', 'varchar(50)', 'String', 'city', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2022-08-11 22:18:08', NULL, '2022-08-11 22:23:54');
INSERT INTO `gen_table_column` VALUES (27, '4', 'address', '商家地址', 'varchar(100)', 'String', 'address', '0', '0', '1', '1', '1', '1', NULL, 'EQ', 'input', '', 6, 'admin', '2022-08-11 22:18:08', NULL, '2022-08-11 22:23:54');
INSERT INTO `gen_table_column` VALUES (28, '4', 'desc', '商家描述', 'varchar(1000)', 'String', 'desc', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'textarea', '', 7, 'admin', '2022-08-11 22:18:08', NULL, '2022-08-11 22:23:54');
INSERT INTO `gen_table_column` VALUES (29, '4', 'start_day', '开始开放工作日', 'int(10)', 'Integer', 'startDay', '0', '0', '1', '1', '1', '1', NULL, 'EQ', 'input', '', 8, 'admin', '2022-08-11 22:18:08', NULL, '2022-08-11 22:23:54');
INSERT INTO `gen_table_column` VALUES (30, '4', 'end_day', '结束开放工作日', 'int(10)', 'Integer', 'endDay', '0', '0', '1', '1', '1', '1', NULL, 'EQ', 'input', '', 9, 'admin', '2022-08-11 22:18:08', NULL, '2022-08-11 22:23:54');
INSERT INTO `gen_table_column` VALUES (31, '4', 'start_time', '开始开放时间', 'varchar(10)', 'String', 'startTime', '0', '0', '1', '1', '1', '1', NULL, 'EQ', 'input', '', 10, 'admin', '2022-08-11 22:18:08', NULL, '2022-08-11 22:23:54');
INSERT INTO `gen_table_column` VALUES (32, '4', 'end_time', '结束开放时间', 'varchar(10)', 'String', 'endTime', '0', '0', '1', '1', '1', '1', NULL, 'EQ', 'input', '', 11, 'admin', '2022-08-11 22:18:08', NULL, '2022-08-11 22:23:54');
INSERT INTO `gen_table_column` VALUES (33, '4', 'contact', '联系方式', 'varchar(255)', 'String', 'contact', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2022-08-11 22:18:08', NULL, '2022-08-11 22:23:54');
INSERT INTO `gen_table_column` VALUES (34, '4', 'create_by', '创建人', 'varchar(64)', 'String', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 13, 'admin', '2022-08-11 22:18:08', NULL, '2022-08-11 22:23:54');
INSERT INTO `gen_table_column` VALUES (35, '4', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, '1', NULL, 'EQ', 'datetime', '', 14, 'admin', '2022-08-11 22:18:08', NULL, '2022-08-11 22:23:54');
INSERT INTO `gen_table_column` VALUES (36, '4', 'update_by', '修改人', 'varchar(64)', 'String', 'updateBy', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'input', '', 15, 'admin', '2022-08-11 22:18:08', NULL, '2022-08-11 22:23:54');
INSERT INTO `gen_table_column` VALUES (37, '4', 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'datetime', '', 16, 'admin', '2022-08-11 22:18:08', NULL, '2022-08-11 22:23:54');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `merchant_id` bigint(20) NOT NULL COMMENT '商家ID',
  `type` tinyint(2) NOT NULL COMMENT '商品类型：1.散票，2.套餐，3.活动票',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '商品名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '商品描述',
  `price` decimal(10, 2) NOT NULL COMMENT '商品价格',
  `quantity` int(10) NOT NULL COMMENT '商品数量',
  `status` tinyint(2) NOT NULL COMMENT '商品状态：0待审核，1已上架，2审核不通过，3已下架',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '修改人',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '商品' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for goods_refund_rule
-- ----------------------------
DROP TABLE IF EXISTS `goods_refund_rule`;
CREATE TABLE `goods_refund_rule`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `goods_id` bigint(20) NOT NULL COMMENT '商品ID',
  `refund_time` datetime(0) NOT NULL COMMENT '退款时间',
  `condition_type` tinyint(2) NOT NULL COMMENT '条件类型：1前，2后',
  `is_allow` tinyint(2) NOT NULL COMMENT '是否允许退款：0不允许，1允许',
  `refund_rate` decimal(5, 2) NOT NULL COMMENT '退款比例',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '修改人',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '商品退款规则' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for merchant
-- ----------------------------
DROP TABLE IF EXISTS `merchant`;
CREATE TABLE `merchant`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `type` tinyint(2) NOT NULL COMMENT '商户类型：1夜店，2酒吧',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '商家名称',
  `logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '商家logo',
  `city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '商家城市',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '商家地址',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '商家描述',
  `start_day` int(10) NOT NULL COMMENT '开始开放工作日',
  `end_day` int(10) NOT NULL COMMENT '结束开放工作日',
  `start_time` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '开始开放时间',
  `end_time` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '结束开放时间',
  `contact` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '联系方式',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '修改人',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '商家' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of merchant
-- ----------------------------
INSERT INTO `merchant` VALUES (1, 1, 'MIAMI CLUB', 'http://124.71.12.51/admin/profile/upload/2022/08/28/1492_0_pwdWlLFJ7aKzxHMmGNdh_20220828164653A002.jpg', '太原', '小店区亲贤街茂业天地酒吧街1号', '就很吊！', 1, 7, '20:30', '06:00', '0789-84337777', 'admin', '2022-08-11 22:51:41', 'admin', '2022-08-28 16:46:55');
INSERT INTO `merchant` VALUES (2, 1, '骚吧', 'http://124.71.12.51/admin/profile/upload/2022/08/28/1492_0_pwdWlLFJ7aKzxHMmGNdh_20220828164636A001.jpg', '太原', '小店区亲贤街茂业天地酒吧街1号', '骚里骚气。', 1, 7, '20:30', '06:00', '0789-84337777', 'admin', '2022-08-11 23:27:31', 'admin', '2022-08-28 16:46:42');
INSERT INTO `merchant` VALUES (3, 1, '庐盏-国潮酒馆', 'http://124.71.12.51/admin/profile/upload/2022/08/28/shop2_20220828172714A003.jpg', '太原', '河东区中山大街128号', '中国文化,国潮酒馆', 1, 7, '2022-08-28', '2022-09-28', '16622222', 'admin', '2022-08-28 17:27:16', 'admin', '2022-08-28 17:27:16');

-- ----------------------------
-- Table structure for merchant_topic
-- ----------------------------
DROP TABLE IF EXISTS `merchant_topic`;
CREATE TABLE `merchant_topic`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `merchant_id` bigint(20) NOT NULL COMMENT '商家ID',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '话题名称',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '修改人',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '商家话题' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `order_no` bigint(20) NOT NULL COMMENT '订单号',
  `verification_code` bigint(20) NULL DEFAULT NULL COMMENT '核销码',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `merchant_id` bigint(20) NOT NULL COMMENT '商家ID',
  `activity_id` bigint(20) NULL DEFAULT NULL COMMENT '活动ID',
  `activity_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '活动名称',
  `goods_id` bigint(20) NOT NULL COMMENT '商品ID',
  `goods_type` tinyint(2) NOT NULL COMMENT '商品类型：1.散票，2.套餐，3.活动票',
  `goods_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '商品名称',
  `goods_price` decimal(10, 2) NOT NULL COMMENT '商品价格',
  `quantity` int(10) NOT NULL COMMENT '购买数量',
  `order_amount` decimal(10, 2) NOT NULL COMMENT '订单总额',
  `status` tinyint(2) NOT NULL COMMENT '订单状态：0待付款，1未完成，2已完成，3退款中，4已退款，5退款失败，6支付失败，7已失效',
  `pay_channel` tinyint(2) NULL DEFAULT NULL COMMENT '支付渠道：1微信支付',
  `trade_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '支付单号',
  `valid_start_time` datetime(0) NOT NULL COMMENT '有效开始时间',
  `valid_end_time` datetime(0) NOT NULL COMMENT '有效结束时间',
  `consume_time` datetime(0) NULL DEFAULT NULL COMMENT '消费时间',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_order_no`(`order_no`) USING BTREE COMMENT '唯一订单号',
  UNIQUE INDEX `uk_verification_code`(`verification_code`) USING BTREE COMMENT '唯一核销码'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for package
-- ----------------------------
DROP TABLE IF EXISTS `package`;
CREATE TABLE `package`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `goods_id` bigint(20) NOT NULL COMMENT '商品ID',
  `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '套餐图片',
  `notice` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '使用须知',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '修改人',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '套餐' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for package_item
-- ----------------------------
DROP TABLE IF EXISTS `package_item`;
CREATE TABLE `package_item`  (
  `id` bigint(20) NOT NULL COMMENT '唯一主键',
  `package_id` bigint(20) NOT NULL COMMENT '套餐ID',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '项目名称',
  `price` decimal(10, 2) NOT NULL COMMENT '项目价格',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '修改人',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '商家套餐项目' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for refund
-- ----------------------------
DROP TABLE IF EXISTS `refund`;
CREATE TABLE `refund`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `refund_no` bigint(20) NOT NULL COMMENT '退款号',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `merchant_id` bigint(20) NOT NULL COMMENT '商户ID',
  `order_id` bigint(20) NOT NULL COMMENT '订单ID',
  `refund_amount` decimal(10, 2) NOT NULL COMMENT '退款金额',
  `reason_id` bigint(20) NOT NULL COMMENT '退款原因ID',
  `reason_detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '退款详细原因',
  `status` tinyint(2) NOT NULL COMMENT '退款状态：0提交申请，1退款中，2退款成功，3退款失败',
  `fail_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '退款失败原因',
  `trade_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '退款单号',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_refund_no`(`refund_no`) USING BTREE COMMENT '唯一退款单号'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '退款单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for refund_reason
-- ----------------------------
DROP TABLE IF EXISTS `refund_reason`;
CREATE TABLE `refund_reason`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `merchant_id` bigint(20) NOT NULL COMMENT '商户ID',
  `reason` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '原因',
  `sort` int(10) NOT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '修改人',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '退款原因' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2022-08-01 14:42:33', '', NULL, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2022-08-01 14:42:33', '', NULL, '初始化密码 123456');
INSERT INTO `sys_config` VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2022-08-01 14:42:33', '', NULL, '深黑主题theme-dark，浅色主题theme-light，深蓝主题theme-blue');
INSERT INTO `sys_config` VALUES (4, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 'admin', '2022-08-01 14:42:33', '', NULL, '是否开启注册用户功能（true开启，false关闭）');
INSERT INTO `sys_config` VALUES (5, '用户管理-密码字符范围', 'sys.account.chrtype', '0', 'Y', 'admin', '2022-08-01 14:42:33', '', NULL, '默认任意字符范围，0任意（密码可以输入任意字符），1数字（密码只能为0-9数字），2英文字母（密码只能为a-z和A-Z字母），3字母和数字（密码必须包含字母，数字）,4字母数字和特殊字符（目前支持的特殊字符包括：~!@#$%^&*()-=_+）');
INSERT INTO `sys_config` VALUES (6, '用户管理-初始密码修改策略', 'sys.account.initPasswordModify', '0', 'Y', 'admin', '2022-08-01 14:42:33', '', NULL, '0：初始密码修改策略关闭，没有任何提示，1：提醒用户，如果未修改初始密码，则在登录时就会提醒修改密码对话框');
INSERT INTO `sys_config` VALUES (7, '用户管理-账号密码更新周期', 'sys.account.passwordValidateDays', '0', 'Y', 'admin', '2022-08-01 14:42:33', '', NULL, '密码更新周期（填写数字，数据初始化值为0不限制，若修改必须为大于0小于365的正整数），如果超过这个周期登录系统时，则在登录时就会提醒修改密码对话框');
INSERT INTO `sys_config` VALUES (8, '主框架页-菜单导航显示风格', 'sys.index.menuStyle', 'default', 'Y', 'admin', '2022-08-01 14:42:33', '', NULL, '菜单导航显示风格（default为左侧导航菜单，topnav为顶部导航菜单）');
INSERT INTO `sys_config` VALUES (9, '主框架页-是否开启页脚', 'sys.index.footer', 'true', 'Y', 'admin', '2022-08-01 14:42:33', '', NULL, '是否开启底部页脚显示（true显示，false隐藏）');
INSERT INTO `sys_config` VALUES (10, '主框架页-是否开启页签', 'sys.index.tagsView', 'true', 'Y', 'admin', '2022-08-01 14:42:33', '', NULL, '是否开启菜单多页签显示（true显示，false隐藏）');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 110 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', '若依科技', 0, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-08-01 14:42:29', '', NULL);
INSERT INTO `sys_dept` VALUES (101, 100, '0,100', '深圳总公司', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-08-01 14:42:29', '', NULL);
INSERT INTO `sys_dept` VALUES (102, 100, '0,100', '长沙分公司', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-08-01 14:42:29', '', NULL);
INSERT INTO `sys_dept` VALUES (103, 101, '0,100,101', '研发部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-08-01 14:42:29', '', NULL);
INSERT INTO `sys_dept` VALUES (104, 101, '0,100,101', '市场部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-08-01 14:42:29', '', NULL);
INSERT INTO `sys_dept` VALUES (105, 101, '0,100,101', '测试部门', 3, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-08-01 14:42:29', '', NULL);
INSERT INTO `sys_dept` VALUES (106, 101, '0,100,101', '财务部门', 4, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-08-01 14:42:29', '', NULL);
INSERT INTO `sys_dept` VALUES (107, 101, '0,100,101', '运维部门', 5, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-08-01 14:42:29', '', NULL);
INSERT INTO `sys_dept` VALUES (108, 102, '0,100,102', '市场部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-08-01 14:42:29', '', NULL);
INSERT INTO `sys_dept` VALUES (109, 102, '0,100,102', '财务部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-08-01 14:42:29', '', NULL);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '默认分组');
INSERT INTO `sys_dict_data` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '系统分组');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 99, '其他', '0', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '其他操作');
INSERT INTO `sys_dict_data` VALUES (19, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '新增操作');
INSERT INTO `sys_dict_data` VALUES (20, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '修改操作');
INSERT INTO `sys_dict_data` VALUES (21, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '删除操作');
INSERT INTO `sys_dict_data` VALUES (22, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '授权操作');
INSERT INTO `sys_dict_data` VALUES (23, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '导出操作');
INSERT INTO `sys_dict_data` VALUES (24, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '导入操作');
INSERT INTO `sys_dict_data` VALUES (25, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '强退操作');
INSERT INTO `sys_dict_data` VALUES (26, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '生成操作');
INSERT INTO `sys_dict_data` VALUES (27, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '清空操作');
INSERT INTO `sys_dict_data` VALUES (28, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (29, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '停用状态');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2022-08-01 14:42:32', '', NULL, '登录状态列表');

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `login_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '登录账号',
  `ipaddr` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 154 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '系统访问记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES (100, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-01 14:55:23');
INSERT INTO `sys_logininfor` VALUES (101, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-01 16:06:22');
INSERT INTO `sys_logininfor` VALUES (102, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-04 15:14:00');
INSERT INTO `sys_logininfor` VALUES (103, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-04 15:17:42');
INSERT INTO `sys_logininfor` VALUES (104, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-04 16:24:48');
INSERT INTO `sys_logininfor` VALUES (105, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-04 18:49:26');
INSERT INTO `sys_logininfor` VALUES (106, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-05 13:33:37');
INSERT INTO `sys_logininfor` VALUES (107, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-05 16:52:46');
INSERT INTO `sys_logininfor` VALUES (108, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-06 00:01:25');
INSERT INTO `sys_logininfor` VALUES (109, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-06 15:06:06');
INSERT INTO `sys_logininfor` VALUES (110, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-06 15:08:19');
INSERT INTO `sys_logininfor` VALUES (111, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-06 15:18:24');
INSERT INTO `sys_logininfor` VALUES (112, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2022-08-06 15:28:37');
INSERT INTO `sys_logininfor` VALUES (113, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-06 15:28:41');
INSERT INTO `sys_logininfor` VALUES (114, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2022-08-07 00:02:51');
INSERT INTO `sys_logininfor` VALUES (115, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-07 00:02:53');
INSERT INTO `sys_logininfor` VALUES (116, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '密码输入错误1次', '2022-08-08 22:10:53');
INSERT INTO `sys_logininfor` VALUES (117, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-08 22:17:40');
INSERT INTO `sys_logininfor` VALUES (118, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2022-08-08 22:19:46');
INSERT INTO `sys_logininfor` VALUES (119, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-08 22:19:50');
INSERT INTO `sys_logininfor` VALUES (120, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-09 10:39:49');
INSERT INTO `sys_logininfor` VALUES (121, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '密码输入错误1次', '2022-08-09 17:16:47');
INSERT INTO `sys_logininfor` VALUES (122, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2022-08-09 17:16:57');
INSERT INTO `sys_logininfor` VALUES (123, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-09 17:17:00');
INSERT INTO `sys_logininfor` VALUES (124, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2022-08-10 21:26:33');
INSERT INTO `sys_logininfor` VALUES (125, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-10 21:26:37');
INSERT INTO `sys_logininfor` VALUES (126, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-10 21:50:46');
INSERT INTO `sys_logininfor` VALUES (127, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-10 22:00:08');
INSERT INTO `sys_logininfor` VALUES (128, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2022-08-10 22:10:38');
INSERT INTO `sys_logininfor` VALUES (129, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-10 22:10:46');
INSERT INTO `sys_logininfor` VALUES (130, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-10 22:12:40');
INSERT INTO `sys_logininfor` VALUES (131, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-10 22:23:06');
INSERT INTO `sys_logininfor` VALUES (132, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-11 10:21:33');
INSERT INTO `sys_logininfor` VALUES (133, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-11 10:27:19');
INSERT INTO `sys_logininfor` VALUES (134, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-11 10:34:00');
INSERT INTO `sys_logininfor` VALUES (135, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-11 10:47:40');
INSERT INTO `sys_logininfor` VALUES (136, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-11 10:54:38');
INSERT INTO `sys_logininfor` VALUES (137, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-11 10:59:35');
INSERT INTO `sys_logininfor` VALUES (138, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-11 11:03:20');
INSERT INTO `sys_logininfor` VALUES (139, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-11 11:30:27');
INSERT INTO `sys_logininfor` VALUES (140, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-11 11:40:41');
INSERT INTO `sys_logininfor` VALUES (141, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-11 11:51:51');
INSERT INTO `sys_logininfor` VALUES (142, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-11 22:17:50');
INSERT INTO `sys_logininfor` VALUES (143, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-11 22:33:15');
INSERT INTO `sys_logininfor` VALUES (144, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-11 22:38:54');
INSERT INTO `sys_logininfor` VALUES (145, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-11 23:25:41');
INSERT INTO `sys_logininfor` VALUES (146, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2022-08-11 23:37:40');
INSERT INTO `sys_logininfor` VALUES (147, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-11 23:37:43');
INSERT INTO `sys_logininfor` VALUES (148, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-11 23:47:06');
INSERT INTO `sys_logininfor` VALUES (149, 'admin', '223.152.61.180', 'XX XX', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-28 16:41:32');
INSERT INTO `sys_logininfor` VALUES (150, 'admin', '223.152.61.180', 'XX XX', 'Chrome 8', 'Windows 10', '0', '退出成功', '2022-08-28 16:42:38');
INSERT INTO `sys_logininfor` VALUES (151, 'admin', '223.152.61.180', 'XX XX', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-28 16:43:49');
INSERT INTO `sys_logininfor` VALUES (152, 'admin', '118.255.175.45', 'XX XX', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2022-08-28 17:03:06');
INSERT INTO `sys_logininfor` VALUES (153, 'admin', '118.255.175.45', 'XX XX', 'Chrome 8', 'Windows 10', '0', '登录成功', '2022-08-28 17:03:07');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '#' COMMENT '请求地址',
  `target` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '打开方式（menuItem页签 menuBlank新窗口）',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `is_refresh` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '1' COMMENT '是否刷新（0刷新 1不刷新）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1086 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, '#', '', 'M', '0', '1', '', 'fa fa-gear', 'admin', '2022-08-01 14:42:30', '', NULL, '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 2, '#', '', 'M', '0', '1', '', 'fa fa-video-camera', 'admin', '2022-08-01 14:42:30', '', NULL, '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 3, '#', '', 'M', '0', '1', '', 'fa fa-bars', 'admin', '2022-08-01 14:42:30', '', NULL, '系统工具目录');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, '/system/user', '', 'C', '0', '1', 'system:user:view', 'fa fa-user-o', 'admin', '2022-08-01 14:42:30', '', NULL, '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, '/system/role', '', 'C', '0', '1', 'system:role:view', 'fa fa-user-secret', 'admin', '2022-08-01 14:42:30', '', NULL, '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, '/system/menu', '', 'C', '0', '1', 'system:menu:view', 'fa fa-th-list', 'admin', '2022-08-01 14:42:30', '', NULL, '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, '/system/dept', '', 'C', '0', '1', 'system:dept:view', 'fa fa-outdent', 'admin', '2022-08-01 14:42:30', '', NULL, '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, '/system/post', '', 'C', '0', '1', 'system:post:view', 'fa fa-address-card-o', 'admin', '2022-08-01 14:42:30', '', NULL, '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, '/system/dict', '', 'C', '0', '1', 'system:dict:view', 'fa fa-bookmark-o', 'admin', '2022-08-01 14:42:30', '', NULL, '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, '/system/config', '', 'C', '0', '1', 'system:config:view', 'fa fa-sun-o', 'admin', '2022-08-01 14:42:30', '', NULL, '参数设置菜单');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, '/system/notice', '', 'C', '0', '1', 'system:notice:view', 'fa fa-bullhorn', 'admin', '2022-08-01 14:42:30', '', NULL, '通知公告菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, '#', '', 'M', '0', '1', '', 'fa fa-pencil-square-o', 'admin', '2022-08-01 14:42:30', '', NULL, '日志管理菜单');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, '/monitor/online', '', 'C', '0', '1', 'monitor:online:view', 'fa fa-user-circle', 'admin', '2022-08-01 14:42:30', '', NULL, '在线用户菜单');
INSERT INTO `sys_menu` VALUES (111, '数据监控', 2, 3, '/monitor/data', '', 'C', '0', '1', 'monitor:data:view', 'fa fa-bug', 'admin', '2022-08-01 14:42:30', '', NULL, '数据监控菜单');
INSERT INTO `sys_menu` VALUES (112, '服务监控', 2, 4, '/monitor/server', '', 'C', '0', '1', 'monitor:server:view', 'fa fa-server', 'admin', '2022-08-01 14:42:30', '', NULL, '服务监控菜单');
INSERT INTO `sys_menu` VALUES (113, '缓存监控', 2, 5, '/monitor/cache', '', 'C', '0', '1', 'monitor:cache:view', 'fa fa-cube', 'admin', '2022-08-01 14:42:30', '', NULL, '缓存监控菜单');
INSERT INTO `sys_menu` VALUES (114, '表单构建', 3, 1, '/tool/build', '', 'C', '0', '1', 'tool:build:view', 'fa fa-wpforms', 'admin', '2022-08-01 14:42:30', '', NULL, '表单构建菜单');
INSERT INTO `sys_menu` VALUES (115, '代码生成', 3, 2, '/tool/gen', '', 'C', '0', '1', 'tool:gen:view', 'fa fa-code', 'admin', '2022-08-01 14:42:30', '', NULL, '代码生成菜单');
INSERT INTO `sys_menu` VALUES (116, '系统接口', 3, 3, '/tool/swagger', '', 'C', '0', '1', 'tool:swagger:view', 'fa fa-gg', 'admin', '2022-08-01 14:42:30', '', NULL, '系统接口菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, '/monitor/operlog', '', 'C', '0', '1', 'monitor:operlog:view', 'fa fa-address-book', 'admin', '2022-08-01 14:42:30', '', NULL, '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, '/monitor/logininfor', '', 'C', '0', '1', 'monitor:logininfor:view', 'fa fa-file-image-o', 'admin', '2022-08-01 14:42:30', '', NULL, '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1000, '用户查询', 100, 1, '#', '', 'F', '0', '1', 'system:user:list', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1001, '用户新增', 100, 2, '#', '', 'F', '0', '1', 'system:user:add', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1002, '用户修改', 100, 3, '#', '', 'F', '0', '1', 'system:user:edit', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1003, '用户删除', 100, 4, '#', '', 'F', '0', '1', 'system:user:remove', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1004, '用户导出', 100, 5, '#', '', 'F', '0', '1', 'system:user:export', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1005, '用户导入', 100, 6, '#', '', 'F', '0', '1', 'system:user:import', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1006, '重置密码', 100, 7, '#', '', 'F', '0', '1', 'system:user:resetPwd', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1007, '角色查询', 101, 1, '#', '', 'F', '0', '1', 'system:role:list', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1008, '角色新增', 101, 2, '#', '', 'F', '0', '1', 'system:role:add', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1009, '角色修改', 101, 3, '#', '', 'F', '0', '1', 'system:role:edit', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1010, '角色删除', 101, 4, '#', '', 'F', '0', '1', 'system:role:remove', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1011, '角色导出', 101, 5, '#', '', 'F', '0', '1', 'system:role:export', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1012, '菜单查询', 102, 1, '#', '', 'F', '0', '1', 'system:menu:list', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1013, '菜单新增', 102, 2, '#', '', 'F', '0', '1', 'system:menu:add', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1014, '菜单修改', 102, 3, '#', '', 'F', '0', '1', 'system:menu:edit', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1015, '菜单删除', 102, 4, '#', '', 'F', '0', '1', 'system:menu:remove', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1016, '部门查询', 103, 1, '#', '', 'F', '0', '1', 'system:dept:list', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1017, '部门新增', 103, 2, '#', '', 'F', '0', '1', 'system:dept:add', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1018, '部门修改', 103, 3, '#', '', 'F', '0', '1', 'system:dept:edit', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1019, '部门删除', 103, 4, '#', '', 'F', '0', '1', 'system:dept:remove', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1020, '岗位查询', 104, 1, '#', '', 'F', '0', '1', 'system:post:list', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1021, '岗位新增', 104, 2, '#', '', 'F', '0', '1', 'system:post:add', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1022, '岗位修改', 104, 3, '#', '', 'F', '0', '1', 'system:post:edit', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1023, '岗位删除', 104, 4, '#', '', 'F', '0', '1', 'system:post:remove', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1024, '岗位导出', 104, 5, '#', '', 'F', '0', '1', 'system:post:export', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1025, '字典查询', 105, 1, '#', '', 'F', '0', '1', 'system:dict:list', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1026, '字典新增', 105, 2, '#', '', 'F', '0', '1', 'system:dict:add', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1027, '字典修改', 105, 3, '#', '', 'F', '0', '1', 'system:dict:edit', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1028, '字典删除', 105, 4, '#', '', 'F', '0', '1', 'system:dict:remove', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1029, '字典导出', 105, 5, '#', '', 'F', '0', '1', 'system:dict:export', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1030, '参数查询', 106, 1, '#', '', 'F', '0', '1', 'system:config:list', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1031, '参数新增', 106, 2, '#', '', 'F', '0', '1', 'system:config:add', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1032, '参数修改', 106, 3, '#', '', 'F', '0', '1', 'system:config:edit', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1033, '参数删除', 106, 4, '#', '', 'F', '0', '1', 'system:config:remove', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1034, '参数导出', 106, 5, '#', '', 'F', '0', '1', 'system:config:export', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1035, '公告查询', 107, 1, '#', '', 'F', '0', '1', 'system:notice:list', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1036, '公告新增', 107, 2, '#', '', 'F', '0', '1', 'system:notice:add', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1037, '公告修改', 107, 3, '#', '', 'F', '0', '1', 'system:notice:edit', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1038, '公告删除', 107, 4, '#', '', 'F', '0', '1', 'system:notice:remove', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1039, '操作查询', 500, 1, '#', '', 'F', '0', '1', 'monitor:operlog:list', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1040, '操作删除', 500, 2, '#', '', 'F', '0', '1', 'monitor:operlog:remove', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1041, '详细信息', 500, 3, '#', '', 'F', '0', '1', 'monitor:operlog:detail', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1042, '日志导出', 500, 4, '#', '', 'F', '0', '1', 'monitor:operlog:export', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1043, '登录查询', 501, 1, '#', '', 'F', '0', '1', 'monitor:logininfor:list', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1044, '登录删除', 501, 2, '#', '', 'F', '0', '1', 'monitor:logininfor:remove', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1045, '日志导出', 501, 3, '#', '', 'F', '0', '1', 'monitor:logininfor:export', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1046, '账户解锁', 501, 4, '#', '', 'F', '0', '1', 'monitor:logininfor:unlock', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1047, '在线查询', 109, 1, '#', '', 'F', '0', '1', 'monitor:online:list', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1048, '批量强退', 109, 2, '#', '', 'F', '0', '1', 'monitor:online:batchForceLogout', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1049, '单条强退', 109, 3, '#', '', 'F', '0', '1', 'monitor:online:forceLogout', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1057, '生成查询', 115, 1, '#', '', 'F', '0', '1', 'tool:gen:list', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1058, '生成修改', 115, 2, '#', '', 'F', '0', '1', 'tool:gen:edit', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1059, '生成删除', 115, 3, '#', '', 'F', '0', '1', 'tool:gen:remove', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1060, '预览代码', 115, 4, '#', '', 'F', '0', '1', 'tool:gen:preview', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1061, '生成代码', 115, 5, '#', '', 'F', '0', '1', 'tool:gen:code', '#', 'admin', '2022-08-01 14:42:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1062, '运营管理', 0, 0, '#', 'menuItem', 'M', '0', '1', NULL, 'fa fa-archive', 'admin', '2022-08-10 21:38:46', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1069, '文章分类', 1062, 1, '/system/articleCategory', '', 'C', '0', '1', 'system:articleCategory:view', '#', 'admin', '2022-08-10 22:04:58', '', NULL, '文章分类菜单');
INSERT INTO `sys_menu` VALUES (1070, '文章分类查询', 1069, 1, '#', '', 'F', '0', '1', 'system:articleCategory:list', '#', 'admin', '2022-08-10 22:04:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1071, '文章分类新增', 1069, 2, '#', '', 'F', '0', '1', 'system:articleCategory:add', '#', 'admin', '2022-08-10 22:04:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1072, '文章分类修改', 1069, 3, '#', '', 'F', '0', '1', 'system:articleCategory:edit', '#', 'admin', '2022-08-10 22:04:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1073, '文章分类删除', 1069, 4, '#', '', 'F', '0', '1', 'system:articleCategory:remove', '#', 'admin', '2022-08-10 22:04:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1075, '文章管理', 1062, 1, '/system/article', 'menuItem', 'C', '0', '1', 'system:article:view', '#', 'admin', '2022-08-11 10:40:13', 'admin', '2022-08-11 10:48:02', '文章菜单');
INSERT INTO `sys_menu` VALUES (1076, '文章查询', 1075, 1, '#', '', 'F', '0', '1', 'system:article:list', '#', 'admin', '2022-08-11 10:40:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1077, '文章新增', 1075, 2, '#', '', 'F', '0', '1', 'system:article:add', '#', 'admin', '2022-08-11 10:40:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1078, '文章修改', 1075, 3, '#', '', 'F', '0', '1', 'system:article:edit', '#', 'admin', '2022-08-11 10:40:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1079, '文章删除', 1075, 4, '#', '', 'F', '0', '1', 'system:article:remove', '#', 'admin', '2022-08-11 10:40:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1081, '商家管理', 1062, 1, '/system/merchant', '', 'C', '0', '1', 'system:merchant:view', '#', 'admin', '2022-08-11 22:24:33', '', NULL, '商家管理菜单');
INSERT INTO `sys_menu` VALUES (1082, '商家管理查询', 1081, 1, '#', '', 'F', '0', '1', 'system:merchant:list', '#', 'admin', '2022-08-11 22:24:33', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1083, '商家管理新增', 1081, 2, '#', '', 'F', '0', '1', 'system:merchant:add', '#', 'admin', '2022-08-11 22:24:33', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1084, '商家管理修改', 1081, 3, '#', '', 'F', '0', '1', 'system:merchant:edit', '#', 'admin', '2022-08-11 22:24:33', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1085, '商家管理删除', 1081, 4, '#', '', 'F', '0', '1', 'system:merchant:remove', '#', 'admin', '2022-08-11 22:24:33', '', NULL, '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '通知公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '温馨提醒：2018-07-01 若依新版本发布啦', '2', '新版本内容', '0', 'admin', '2022-08-01 14:42:34', '', NULL, '管理员');
INSERT INTO `sys_notice` VALUES (2, '维护通知：2018-07-01 若依系统凌晨维护', '1', '维护内容', '0', 'admin', '2022-08-01 14:42:34', '', NULL, '管理员');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '返回参数',
  `status` int(1) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 76 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '操作日志记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES (1, '用户管理', 5, 'com.ruoyi.web.controller.system.SysUserController.export()', 'POST', 1, 'admin', '研发部门', '/system/user/export', '127.0.0.1', '内网IP', '{\"deptId\":[\"\"],\"parentId\":[\"\"],\"loginName\":[\"\"],\"phonenumber\":[\"15888888888\"],\"status\":[\"\"],\"params[beginTime]\":[\"\"],\"params[endTime]\":[\"\"],\"orderByColumn\":[\"createTime\"],\"isAsc\":[\"desc\"]}', '{\"msg\":\"362b7e8f-a67a-4e46-acef-8a65a4bb5cf2_用户数据.xlsx\",\"code\":0}', 0, NULL, '2022-08-04 16:25:17');
INSERT INTO `sys_oper_log` VALUES (2, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/importTable', '127.0.0.1', '内网IP', '{\"tables\":[\"goods\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-04 18:34:43');
INSERT INTO `sys_oper_log` VALUES (3, '代码生成', 3, 'com.ruoyi.generator.controller.GenController.remove()', 'POST', 1, 'admin', '研发部门', '/tool/gen/remove', '127.0.0.1', '内网IP', '{\"ids\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-04 18:36:23');
INSERT INTO `sys_oper_log` VALUES (4, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/110', '127.0.0.1', '内网IP', '110', '{\"msg\":\"存在子菜单,不允许删除\",\"code\":301}', 0, NULL, '2022-08-06 15:09:37');
INSERT INTO `sys_oper_log` VALUES (5, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/1056', '127.0.0.1', '内网IP', '1056', '{\"msg\":\"菜单已分配,不允许删除\",\"code\":301}', 0, NULL, '2022-08-06 15:10:38');
INSERT INTO `sys_oper_log` VALUES (6, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\"roleId\":[\"2\"],\"roleName\":[\"普通角色\"],\"roleKey\":[\"common\"],\"roleSort\":[\"2\"],\"status\":[\"0\"],\"remark\":[\"普通角色\"],\"menuIds\":[\"1,100,1000,1001,1002,1003,1004,1005,1006,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,1046,2,109,1047,1048,1049,111,112,113,3,114,115,1057,1058,1059,1060,1061,116,4\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-06 15:10:58');
INSERT INTO `sys_oper_log` VALUES (7, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/1056', '127.0.0.1', '内网IP', '1056', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-06 15:11:09');
INSERT INTO `sys_oper_log` VALUES (8, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/1055', '127.0.0.1', '内网IP', '1055', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-06 15:11:18');
INSERT INTO `sys_oper_log` VALUES (9, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/1054', '127.0.0.1', '内网IP', '1054', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-06 15:11:32');
INSERT INTO `sys_oper_log` VALUES (10, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/1053', '127.0.0.1', '内网IP', '1053', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-06 15:11:37');
INSERT INTO `sys_oper_log` VALUES (11, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/1052', '127.0.0.1', '内网IP', '1052', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-06 15:11:43');
INSERT INTO `sys_oper_log` VALUES (12, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/1051', '127.0.0.1', '内网IP', '1051', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-06 15:11:48');
INSERT INTO `sys_oper_log` VALUES (13, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/1050', '127.0.0.1', '内网IP', '1050', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-06 15:11:54');
INSERT INTO `sys_oper_log` VALUES (14, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/110', '127.0.0.1', '内网IP', '110', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-06 15:11:59');
INSERT INTO `sys_oper_log` VALUES (15, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/4', '127.0.0.1', '内网IP', '4', '{\"msg\":\"菜单已分配,不允许删除\",\"code\":301}', 0, NULL, '2022-08-09 17:17:41');
INSERT INTO `sys_oper_log` VALUES (16, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\"roleId\":[\"2\"],\"roleName\":[\"普通角色\"],\"roleKey\":[\"common\"],\"roleSort\":[\"2\"],\"status\":[\"0\"],\"remark\":[\"普通角色\"],\"menuIds\":[\"1,100,1000,1001,1002,1003,1004,1005,1006,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,1046,2,109,1047,1048,1049,111,112,113,3,114,115,1057,1058,1059,1060,1061,116\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-09 17:17:50');
INSERT INTO `sys_oper_log` VALUES (17, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/4', '127.0.0.1', '内网IP', '4', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-09 17:17:55');
INSERT INTO `sys_oper_log` VALUES (18, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/importTable', '127.0.0.1', '内网IP', '{\"tables\":[\"article_category\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-10 21:27:37');
INSERT INTO `sys_oper_log` VALUES (19, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\"parentId\":[\"0\"],\"menuType\":[\"M\"],\"menuName\":[\"运营管理\"],\"url\":[\"\"],\"target\":[\"menuItem\"],\"perms\":[\"\"],\"orderNum\":[\"0\"],\"icon\":[\"fa fa-archive\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-10 21:38:46');
INSERT INTO `sys_oper_log` VALUES (20, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网IP', '{\"tableId\":[\"1\"],\"tableName\":[\"article_category\"],\"tableComment\":[\"文章分类\"],\"className\":[\"ArticleCategory\"],\"functionAuthor\":[\"AlanLee\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"1\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"唯一主键\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"2\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"文章类型编码\"],\"columns[1].javaType\":[\"String\"],\"columns[1].javaField\":[\"code\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].isRequired\":[\"1\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"3\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"文章类型名称\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"name\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"LIKE\"],\"columns[2].isRequired\":[\"1\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"4\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"创建时间\"],\"columns[3].javaType\":[\"Date\"],\"columns[3].javaField\":[\"createTime\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"datetime\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"5\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"修改时间\"],\"columns[4].javaType\":[\"Date\"],\"columns[4].javaField\":[\"updateTime\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"datetime\"],\"columns[4].dictType\":[\"\"],\"tplCategory\":[\"crud\"],\"packageName\":[\"com.ruoyi.system\"],\"moduleName\":[\"system\"],\"businessName\":[\"category\"],\"functionName\":[\"文章分类\"],\"params[parentMenuId]\":[\"1062\"],\"params[parentMenuName]\":[\"运营管理\"],\"genType\":[\"0\"]', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-10 21:40:36');
INSERT INTO `sys_oper_log` VALUES (21, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.download()', 'GET', 1, 'admin', '研发部门', '/tool/gen/download/article_category', '127.0.0.1', '内网IP', '\"article_category\"', NULL, 0, NULL, '2022-08-10 21:40:42');
INSERT INTO `sys_oper_log` VALUES (22, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网IP', '{\"tableId\":[\"1\"],\"tableName\":[\"article_category\"],\"tableComment\":[\"文章分类\"],\"className\":[\"ArticleCategory\"],\"functionAuthor\":[\"AlanLee\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"1\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"唯一主键\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"2\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"文章类型编码\"],\"columns[1].javaType\":[\"String\"],\"columns[1].javaField\":[\"code\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].isRequired\":[\"1\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"3\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"文章类型名称\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"name\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"LIKE\"],\"columns[2].isRequired\":[\"1\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"4\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"创建时间\"],\"columns[3].javaType\":[\"Date\"],\"columns[3].javaField\":[\"createTime\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"datetime\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"5\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"修改时间\"],\"columns[4].javaType\":[\"Date\"],\"columns[4].javaField\":[\"updateTime\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"datetime\"],\"columns[4].dictType\":[\"\"],\"tplCategory\":[\"crud\"],\"packageName\":[\"com.ruoyi.system\"],\"moduleName\":[\"system\"],\"businessName\":[\"articleCategory\"],\"functionName\":[\"文章分类\"],\"params[parentMenuId]\":[\"1062\"],\"params[parentMenuName]\":[\"运营管理\"],\"genType', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-10 21:42:36');
INSERT INTO `sys_oper_log` VALUES (23, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.download()', 'GET', 1, 'admin', '研发部门', '/tool/gen/download/article_category', '127.0.0.1', '内网IP', '\"article_category\"', NULL, 0, NULL, '2022-08-10 21:42:40');
INSERT INTO `sys_oper_log` VALUES (24, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/1068', '127.0.0.1', '内网IP', '1068', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-10 21:45:08');
INSERT INTO `sys_oper_log` VALUES (25, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/1063', '127.0.0.1', '内网IP', '1063', '{\"msg\":\"存在子菜单,不允许删除\",\"code\":301}', 0, NULL, '2022-08-10 22:00:20');
INSERT INTO `sys_oper_log` VALUES (26, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/1067', '127.0.0.1', '内网IP', '1067', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-10 22:00:25');
INSERT INTO `sys_oper_log` VALUES (27, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/1066', '127.0.0.1', '内网IP', '1066', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-10 22:00:30');
INSERT INTO `sys_oper_log` VALUES (28, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/1065', '127.0.0.1', '内网IP', '1065', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-10 22:00:39');
INSERT INTO `sys_oper_log` VALUES (29, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/1064', '127.0.0.1', '内网IP', '1064', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-10 22:00:44');
INSERT INTO `sys_oper_log` VALUES (30, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/1063', '127.0.0.1', '内网IP', '1063', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-10 22:00:47');
INSERT INTO `sys_oper_log` VALUES (31, '代码生成', 3, 'com.ruoyi.generator.controller.GenController.remove()', 'POST', 1, 'admin', '研发部门', '/tool/gen/remove', '127.0.0.1', '内网IP', '{\"ids\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-10 22:00:56');
INSERT INTO `sys_oper_log` VALUES (32, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/importTable', '127.0.0.1', '内网IP', '{\"tables\":[\"article_category\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-10 22:01:01');
INSERT INTO `sys_oper_log` VALUES (33, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网IP', '{\"tableId\":[\"2\"],\"tableName\":[\"article_category\"],\"tableComment\":[\"文章分类\"],\"className\":[\"ArticleCategory\"],\"functionAuthor\":[\"AlanLee\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"6\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"唯一主键\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"7\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"文章类型编码\"],\"columns[1].javaType\":[\"String\"],\"columns[1].javaField\":[\"code\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].isRequired\":[\"1\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"8\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"文章类型名称\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"name\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"LIKE\"],\"columns[2].isRequired\":[\"1\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"9\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"创建者\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"createBy\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].isRequired\":[\"1\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"10\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"创建时间\"],\"columns[4].javaType\":[\"Date\"],\"columns[4].javaField\":[\"createTime\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].isRequired\":[\"1\"],\"columns[4].htmlType\":[\"datetime\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"11\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"更新者\"],\"columns[5].javaType\":[\"String\"],\"columns[5].javaField\":[\"updateBy\"],\"columns[5].isInsert\":[\"1\"],\"columns[5].isEdit\":[\"1\"]', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-10 22:01:14');
INSERT INTO `sys_oper_log` VALUES (34, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网IP', '{\"tableId\":[\"2\"],\"tableName\":[\"article_category\"],\"tableComment\":[\"文章分类\"],\"className\":[\"ArticleCategory\"],\"functionAuthor\":[\"AlanLee\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"6\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"唯一主键\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"7\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"文章类型编码\"],\"columns[1].javaType\":[\"String\"],\"columns[1].javaField\":[\"code\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].isRequired\":[\"1\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"8\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"文章类型名称\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"name\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"LIKE\"],\"columns[2].isRequired\":[\"1\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"9\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"创建者\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"createBy\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].isRequired\":[\"1\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"10\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"创建时间\"],\"columns[4].javaType\":[\"Date\"],\"columns[4].javaField\":[\"createTime\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].isRequired\":[\"1\"],\"columns[4].htmlType\":[\"datetime\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"11\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"更新者\"],\"columns[5].javaType\":[\"String\"],\"columns[5].javaField\":[\"updateBy\"],\"columns[5].isInsert\":[\"1\"]', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-10 22:03:40');
INSERT INTO `sys_oper_log` VALUES (35, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.download()', 'GET', 1, 'admin', '研发部门', '/tool/gen/download/article_category', '127.0.0.1', '内网IP', '\"article_category\"', NULL, 0, NULL, '2022-08-10 22:03:44');
INSERT INTO `sys_oper_log` VALUES (36, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.download()', 'GET', 1, 'admin', '研发部门', '/tool/gen/download/article_category', '127.0.0.1', '内网IP', '\"article_category\"', NULL, 0, NULL, '2022-08-10 22:03:54');
INSERT INTO `sys_oper_log` VALUES (37, '文章分类', 1, 'com.ruoyi.web.controller.system.ArticleCategoryController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/articleCategory/add', '127.0.0.1', '内网IP', '{\"code\":[\"BOOMB_SERVICE\"],\"name\":[\"蹦嘣服务\"]}', NULL, 1, '\r\n### Error updating database.  Cause: java.sql.SQLException: Field \'create_by\' doesn\'t have a default value\r\n### The error may exist in file [D:\\git_repository\\bar\\bar-system\\target\\classes\\mapper\\system\\ArticleCategoryMapper.xml]\r\n### The error may involve com.ruoyi.system.mapper.ArticleCategoryMapper.insertArticleCategory-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into article_category          ( code,             name,                          create_time )           values ( ?,             ?,                          ? )\r\n### Cause: java.sql.SQLException: Field \'create_by\' doesn\'t have a default value\n; Field \'create_by\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'create_by\' doesn\'t have a default value', '2022-08-10 22:17:41');
INSERT INTO `sys_oper_log` VALUES (38, '文章分类', 1, 'com.ruoyi.web.controller.system.ArticleCategoryController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/articleCategory/add', '127.0.0.1', '内网IP', '{\"code\":[\"BOOMB_SERVICE\"],\"name\":[\"蹦嘣服务\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-10 22:23:24');
INSERT INTO `sys_oper_log` VALUES (39, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/importTable', '127.0.0.1', '内网IP', '{\"tables\":[\"article\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-11 10:34:10');
INSERT INTO `sys_oper_log` VALUES (40, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网IP', '{\"tableId\":[\"3\"],\"tableName\":[\"article\"],\"tableComment\":[\"文章\"],\"className\":[\"Article\"],\"functionAuthor\":[\"AlanLee\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"13\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"唯一主键\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"14\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"文章类型ID\"],\"columns[1].javaType\":[\"Long\"],\"columns[1].javaField\":[\"categoryId\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].isRequired\":[\"1\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"15\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"文章类型编码\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"categoryCode\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].isRequired\":[\"1\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"16\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"文章标题\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"title\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].isRequired\":[\"1\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"17\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"文章内容\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"content\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].isQuery\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].isRequired\":[\"1\"],\"columns[4].htmlType\":[\"summernote\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"18\"],\"columns[5].sort\":[\"6\"],\"colu', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-11 10:34:24');
INSERT INTO `sys_oper_log` VALUES (41, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网IP', '{\"tableId\":[\"3\"],\"tableName\":[\"article\"],\"tableComment\":[\"文章\"],\"className\":[\"Article\"],\"functionAuthor\":[\"AlanLee\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"13\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"唯一主键\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"14\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"文章类型ID\"],\"columns[1].javaType\":[\"Long\"],\"columns[1].javaField\":[\"categoryId\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].isRequired\":[\"1\"],\"columns[1].htmlType\":[\"select\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"15\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"文章类型编码\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"categoryCode\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].isRequired\":[\"1\"],\"columns[2].htmlType\":[\"select\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"16\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"文章标题\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"title\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].isRequired\":[\"1\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"17\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"文章内容\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"content\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].isRequired\":[\"1\"],\"columns[4].htmlType\":[\"summernote\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"18\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"创', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-11 10:39:03');
INSERT INTO `sys_oper_log` VALUES (42, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.download()', 'GET', 1, 'admin', '研发部门', '/tool/gen/download/article', '127.0.0.1', '内网IP', '\"article\"', NULL, 0, NULL, '2022-08-11 10:39:07');
INSERT INTO `sys_oper_log` VALUES (43, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.download()', 'GET', 1, 'admin', '研发部门', '/tool/gen/download/article', '127.0.0.1', '内网IP', '\"article\"', NULL, 0, NULL, '2022-08-11 10:39:29');
INSERT INTO `sys_oper_log` VALUES (44, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\"menuId\":[\"1075\"],\"parentId\":[\"1062\"],\"menuType\":[\"C\"],\"menuName\":[\"文章管理\"],\"url\":[\"/system/article\"],\"target\":[\"menuItem\"],\"perms\":[\"system:article:view\"],\"orderNum\":[\"1\"],\"icon\":[\"#\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-11 10:48:02');
INSERT INTO `sys_oper_log` VALUES (45, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/1080', '127.0.0.1', '内网IP', '1080', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-11 10:48:11');
INSERT INTO `sys_oper_log` VALUES (46, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/1074', '127.0.0.1', '内网IP', '1074', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-11 10:48:17');
INSERT INTO `sys_oper_log` VALUES (47, '文章', 1, 'com.ruoyi.web.controller.system.ArticleController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/article/add', '127.0.0.1', '内网IP', '{\"title\":[\"1\"],\"content\":[\"<p>1.商户散票<img src=\\\"http://localhost/profile/upload/2022/08/11/ruoyi_20220811105201A001.png\\\" style=\\\"width: 121px;\\\"></p>\"]}', NULL, 1, '\r\n### Error updating database.  Cause: java.sql.SQLException: Field \'category_id\' doesn\'t have a default value\r\n### The error may exist in file [D:\\git_repository\\bar\\bar-system\\target\\classes\\mapper\\system\\ArticleMapper.xml]\r\n### The error may involve com.ruoyi.system.mapper.ArticleMapper.insertArticle-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into article          ( title,             content,                          create_time )           values ( ?,             ?,                          ? )\r\n### Cause: java.sql.SQLException: Field \'category_id\' doesn\'t have a default value\n; Field \'category_id\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'category_id\' doesn\'t have a default value', '2022-08-11 10:52:05');
INSERT INTO `sys_oper_log` VALUES (48, '文章', 1, 'com.ruoyi.web.controller.system.ArticleController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/article/add', '127.0.0.1', '内网IP', '{\"title\":[\"1\"],\"content\":[\"<p>1.商家散票<img src=\\\"http://localhost/profile/upload/2022/08/11/ruoyi_20220811105500A001.png\\\" style=\\\"width: 121px;\\\"></p>\"]}', NULL, 1, '\r\n### Error updating database.  Cause: java.sql.SQLException: Field \'category_id\' doesn\'t have a default value\r\n### The error may exist in file [D:\\git_repository\\bar\\bar-system\\target\\classes\\mapper\\system\\ArticleMapper.xml]\r\n### The error may involve com.ruoyi.system.mapper.ArticleMapper.insertArticle-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into article          ( title,             content,                          create_time )           values ( ?,             ?,                          ? )\r\n### Cause: java.sql.SQLException: Field \'category_id\' doesn\'t have a default value\n; Field \'category_id\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'category_id\' doesn\'t have a default value', '2022-08-11 10:55:02');
INSERT INTO `sys_oper_log` VALUES (49, '文章分类', 2, 'com.ruoyi.web.controller.system.ArticleCategoryController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/articleCategory/edit', '127.0.0.1', '内网IP', '{\"id\":[\"1\"],\"name\":[\"蹦嘣服务2\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-11 10:59:49');
INSERT INTO `sys_oper_log` VALUES (50, '文章分类', 2, 'com.ruoyi.web.controller.system.ArticleCategoryController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/articleCategory/edit', '127.0.0.1', '内网IP', '{\"id\":[\"1\"],\"name\":[\"蹦嘣服务\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-11 10:59:53');
INSERT INTO `sys_oper_log` VALUES (51, '文章', 2, 'com.ruoyi.web.controller.system.ArticleController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/article/edit', '127.0.0.1', '内网IP', '{\"id\":[\"1\"],\"title\":[\"蹦嘣服务\"],\"content\":[\"<p>1.商家散票<img src=\\\"http://localhost/profile/upload/2022/08/11/ruoyi_20220811105500A001.png\\\"></p><p>&nbsp; &nbsp; 用户可以在蹦嘣上选择商户购买散票。</p>\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-11 11:31:26');
INSERT INTO `sys_oper_log` VALUES (52, '文章', 2, 'com.ruoyi.web.controller.system.ArticleController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/article/edit', '127.0.0.1', '内网IP', '{\"id\":[\"1\"],\"title\":[\"蹦嘣服务\"],\"content\":[\"<p>1.商家散票<img src=\\\"http://localhost/profile/upload/2022/08/11/ruoyi_20220811105500A001.png\\\">&nbsp; &nbsp; </p><p>&nbsp; &nbsp; 用户可以在蹦嘣上选择商户购买散票。</p>\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-11 11:32:08');
INSERT INTO `sys_oper_log` VALUES (53, '文章', 2, 'com.ruoyi.web.controller.system.ArticleController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/article/edit', '127.0.0.1', '内网IP', '{\"id\":[\"1\"],\"title\":[\"蹦嘣服务\"],\"content\":[\"<p>1.商家散票<img src=\\\"http://localhost/profile/upload/2022/08/11/ruoyi_20220811105500A001.png\\\">&nbsp;</p><p>&nbsp; &nbsp;用户可以在蹦嘣上选择商户购买散票。</p>\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-11 11:35:01');
INSERT INTO `sys_oper_log` VALUES (54, '文章', 2, 'com.ruoyi.web.controller.system.ArticleController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/article/edit', '127.0.0.1', '内网IP', '{\"id\":[\"1\"],\"title\":[\"蹦嘣服务\"],\"content\":[\"<p>1.商家散票<img src=\\\"http://localhost/profile/upload/2022/08/11/ruoyi_20220811105500A001.png\\\"></p><p>&nbsp; &nbsp;用户可以在蹦嘣上选择商户购买散票。</p>\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-11 11:40:56');
INSERT INTO `sys_oper_log` VALUES (55, '文章', 2, 'com.ruoyi.web.controller.system.ArticleController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/article/edit', '127.0.0.1', '内网IP', '{\"id\":[\"1\"],\"title\":[\"蹦嘣服务\"],\"content\":[\"<p><b><span style=\\\"font-size: 14px;\\\">1.商家散票</span></b><img src=\\\"http://localhost/profile/upload/2022/08/11/ruoyi_20220811105500A001.png\\\"></p><p>&nbsp; &nbsp;用户可以在蹦嘣上选择商户购买散票。散票有效期为所</p><p>购商户当日开始时间至次日打烊时间。超过该时间段订单</p><p>无效。</p>\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-11 11:53:37');
INSERT INTO `sys_oper_log` VALUES (56, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/importTable', '127.0.0.1', '内网IP', '{\"tables\":[\"merchant\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-11 22:18:08');
INSERT INTO `sys_oper_log` VALUES (57, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网IP', '{\"tableId\":[\"4\"],\"tableName\":[\"merchant\"],\"tableComment\":[\"商家\"],\"className\":[\"Merchant\"],\"functionAuthor\":[\"AlanLee\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"22\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"唯一主键\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"23\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"商户类型：1夜店，2酒吧\"],\"columns[1].javaType\":[\"Integer\"],\"columns[1].javaField\":[\"type\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].isRequired\":[\"1\"],\"columns[1].htmlType\":[\"select\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"24\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"商家名称\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"name\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"LIKE\"],\"columns[2].isRequired\":[\"1\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"25\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"商家logo\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"logo\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].isRequired\":[\"1\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"26\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"商家城市\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"city\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].isQuery\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].isRequired\":[\"1\"],\"columns[4].htmlType\":[\"input\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"27\"],\"columns[5].sort\":[\"6\"],\"columns[5].co', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-11 22:18:39');
INSERT INTO `sys_oper_log` VALUES (58, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网IP', '{\"tableId\":[\"4\"],\"tableName\":[\"merchant\"],\"tableComment\":[\"商家\"],\"className\":[\"Merchant\"],\"functionAuthor\":[\"AlanLee\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"22\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"唯一主键\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"23\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"商户类型：1夜店，2酒吧\"],\"columns[1].javaType\":[\"Integer\"],\"columns[1].javaField\":[\"type\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].isRequired\":[\"1\"],\"columns[1].htmlType\":[\"select\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"24\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"商家名称\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"name\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"LIKE\"],\"columns[2].isRequired\":[\"1\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"25\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"商家logo\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"logo\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].isRequired\":[\"1\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"26\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"商家城市\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"city\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].isQuery\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].isRequired\":[\"1\"],\"columns[4].htmlType\":[\"input\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"27\"],\"columns[5].sort\":[\"6\"],\"columns[5].co', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-11 22:19:09');
INSERT INTO `sys_oper_log` VALUES (59, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网IP', '{\"tableId\":[\"4\"],\"tableName\":[\"merchant\"],\"tableComment\":[\"商家\"],\"className\":[\"Merchant\"],\"functionAuthor\":[\"AlanLee\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"22\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"唯一主键\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"23\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"商户类型：1夜店，2酒吧\"],\"columns[1].javaType\":[\"Integer\"],\"columns[1].javaField\":[\"type\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].isRequired\":[\"1\"],\"columns[1].htmlType\":[\"select\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"24\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"商家名称\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"name\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"LIKE\"],\"columns[2].isRequired\":[\"1\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"25\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"商家logo\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"logo\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].isRequired\":[\"1\"],\"columns[3].htmlType\":[\"upload\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"26\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"商家城市\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"city\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].isQuery\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].isRequired\":[\"1\"],\"columns[4].htmlType\":[\"input\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"27\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"商家地址\"],\"col', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-11 22:23:54');
INSERT INTO `sys_oper_log` VALUES (60, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.download()', 'GET', 1, 'admin', '研发部门', '/tool/gen/download/merchant', '127.0.0.1', '内网IP', '\"merchant\"', NULL, 0, NULL, '2022-08-11 22:23:57');
INSERT INTO `sys_oper_log` VALUES (61, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.download()', 'GET', 1, 'admin', '研发部门', '/tool/gen/download/merchant', '127.0.0.1', '内网IP', '\"merchant\"', NULL, 0, NULL, '2022-08-11 22:24:13');
INSERT INTO `sys_oper_log` VALUES (62, '商家管理', 1, 'com.ruoyi.web.controller.system.MerchantController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/merchant/add', '127.0.0.1', '内网IP', '{\"name\":[\"MIAMI CLUB\"],\"logo\":[\"\"],\"city\":[\"太原\"],\"address\":[\"小店区亲贤街茂业天地酒吧街1号\"],\"desc\":[\"源于美国，首战龙城，全新的音乐理念，超前时尚设计。用世界顶级声光电，为龙城娱乐揭开新的篇章！\"],\"startDay\":[\"1\"],\"endDay\":[\"7\"],\"startTime\":[\"20:30\"],\"endTime\":[\"06:00\"],\"contact\":[\"0789-84337777\"]}', NULL, 1, '\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'desc,\n            start_day,\n            end_day,\n            start_time,\n      \' at line 6\r\n### The error may exist in file [D:\\git_repository\\bar\\bar-system\\target\\classes\\mapper\\system\\MerchantMapper.xml]\r\n### The error may involve com.ruoyi.system.mapper.MerchantMapper.insertMerchant-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into merchant          ( name,                          city,             address,             desc,             start_day,             end_day,             start_time,             end_time,             contact,             create_by,             create_time,             update_by,             update_time )           values ( ?,                          ?,             ?,             ?,             ?,             ?,             ?,             ?,             ?,             ?,             ?,             ?,             ? )\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'desc,\n            start_day,\n            end_day,\n            start_time,\n      \' at line 6\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'desc,\n            start_day,\n            end_day,\n            start_time,\n      \' at line 6', '2022-08-11 22:43:40');
INSERT INTO `sys_oper_log` VALUES (63, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/1086', '127.0.0.1', '内网IP', '1086', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-11 23:25:51');
INSERT INTO `sys_oper_log` VALUES (64, '商家管理', 1, 'com.ruoyi.web.controller.system.MerchantController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/merchant/add', '127.0.0.1', '内网IP', '{\"type\":[\"1\"],\"name\":[\"骚吧\"],\"logo\":[\"\"],\"city\":[\"太原\"],\"address\":[\"小店区亲贤街茂业天地酒吧街1号\"],\"description\":[\"骚里骚气\"],\"startDay\":[\"1\"],\"endDay\":[\"7\"],\"startTime\":[\"20:30\"],\"endTime\":[\"06:00\"],\"contact\":[\"0789-84337777\"]}', NULL, 1, '\r\n### Error updating database.  Cause: java.sql.SQLException: Field \'logo\' doesn\'t have a default value\r\n### The error may exist in file [D:\\git_repository\\bar\\bar-system\\target\\classes\\mapper\\system\\MerchantMapper.xml]\r\n### The error may involve com.ruoyi.system.mapper.MerchantMapper.insertMerchant-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into merchant          ( type,             name,                          city,             address,                          start_day,             end_day,             start_time,             end_time,             contact,             create_by,             create_time,             update_by,             update_time )           values ( ?,             ?,                          ?,             ?,                          ?,             ?,             ?,             ?,             ?,             ?,             ?,             ?,             ? )\r\n### Cause: java.sql.SQLException: Field \'logo\' doesn\'t have a default value\n; Field \'logo\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'logo\' doesn\'t have a default value', '2022-08-11 23:27:06');
INSERT INTO `sys_oper_log` VALUES (65, '商家管理', 1, 'com.ruoyi.web.controller.system.MerchantController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/merchant/add', '127.0.0.1', '内网IP', '{\"type\":[\"1\"],\"name\":[\"骚吧\"],\"logo\":[\"http://localhost/profile/upload/2022/08/11/微信截图_20220811223935_20220811232727A001.png\"],\"city\":[\"太原\"],\"address\":[\"小店区亲贤街茂业天地酒吧街1号\"],\"description\":[\"骚里骚气\"],\"startDay\":[\"1\"],\"endDay\":[\"7\"],\"startTime\":[\"20:30\"],\"endTime\":[\"06:00\"],\"contact\":[\"0789-84337777\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-11 23:27:31');
INSERT INTO `sys_oper_log` VALUES (66, '商家管理', 2, 'com.ruoyi.web.controller.system.MerchantController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/merchant/edit', '127.0.0.1', '内网IP', '{\"id\":[\"2\"],\"type\":[\"1\"],\"name\":[\"骚吧\"],\"logo\":[\"http://localhost/profile/upload/2022/08/11/微信截图_20220811223935_20220811232727A001.png\"],\"city\":[\"太原\"],\"address\":[\"小店区亲贤街茂业天地酒吧街1号\"],\"description\":[\"骚里骚气\"],\"startDay\":[\"1\"],\"endDay\":[\"7\"],\"startTime\":[\"20:30\"],\"endTime\":[\"06:00\"],\"contact\":[\"0789-84337777\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-11 23:32:16');
INSERT INTO `sys_oper_log` VALUES (67, '商家管理', 2, 'com.ruoyi.web.controller.system.MerchantController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/merchant/edit', '127.0.0.1', '内网IP', '{\"id\":[\"2\"],\"type\":[\"1\"],\"name\":[\"骚吧\"],\"logo\":[\"http://localhost/profile/upload/2022/08/11/微信截图_20220811223935_20220811232727A001.png\"],\"city\":[\"太原\"],\"address\":[\"小店区亲贤街茂业天地酒吧街1号\"],\"description\":[\"骚里骚气\"],\"startDay\":[\"1\"],\"endDay\":[\"7\"],\"startTime\":[\"20:30\"],\"endTime\":[\"06:00\"],\"contact\":[\"0789-84337777\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-11 23:38:27');
INSERT INTO `sys_oper_log` VALUES (68, '商家管理', 2, 'com.ruoyi.web.controller.system.MerchantController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/merchant/edit', '127.0.0.1', '内网IP', '{\"id\":[\"2\"],\"type\":[\"1\"],\"name\":[\"骚吧\"],\"logo\":[\"http://localhost/profile/upload/2022/08/11/微信截图_20220811223935_20220811232727A001.png\"],\"city\":[\"太原\"],\"address\":[\"小店区亲贤街茂业天地酒吧街1号\"],\"description\":[\"骚里骚气\"],\"startDay\":[\"1\"],\"endDay\":[\"7\"],\"startTime\":[\"20:30\"],\"endTime\":[\"06:00\"],\"contact\":[\"0789-84337777\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-11 23:38:44');
INSERT INTO `sys_oper_log` VALUES (69, '商家管理', 2, 'com.ruoyi.web.controller.system.MerchantController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/merchant/edit', '127.0.0.1', '内网IP', '{\"id\":[\"2\"],\"type\":[\"1\"],\"name\":[\"骚吧\"],\"logo\":[\"http://localhost/profile/upload/2022/08/11/微信截图_20220811223935_20220811232727A001.png\"],\"city\":[\"太原\"],\"address\":[\"小店区亲贤街茂业天地酒吧街1号\"],\"description\":[\"骚里骚气。\"],\"startDay\":[\"1\"],\"endDay\":[\"7\"],\"startTime\":[\"20:30\"],\"endTime\":[\"06:00\"],\"contact\":[\"0789-84337777\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-11 23:47:20');
INSERT INTO `sys_oper_log` VALUES (70, '商家管理', 2, 'com.ruoyi.web.controller.system.MerchantController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/merchant/edit', '127.0.0.1', '内网IP', '{\"id\":[\"1\"],\"type\":[\"1\"],\"name\":[\"MIAMI CLUB\"],\"logo\":[\"http://localhost/profile/upload/2022/08/11/ruoyi_20220811105500A001.png\"],\"city\":[\"太原\"],\"address\":[\"小店区亲贤街茂业天地酒吧街1号\"],\"description\":[\"就很吊！\"],\"startDay\":[\"1\"],\"endDay\":[\"7\"],\"startTime\":[\"20:30\"],\"endTime\":[\"06:00\"],\"contact\":[\"0789-84337777\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-11 23:47:33');
INSERT INTO `sys_oper_log` VALUES (71, '商家管理', 2, 'com.ruoyi.web.controller.system.MerchantController.editSave()', 'POST', 1, 'admin', '研发部门', '/admin/system/merchant/edit', '223.152.61.180', 'XX XX', '{\"id\":[\"2\"],\"type\":[\"1\"],\"name\":[\"骚吧\"],\"logo\":[\"http://localhost/profile/upload/2022/08/11/微信截图_20220811223935_20220811232727A001.png\"],\"city\":[\"太原\"],\"address\":[\"小店区亲贤街茂业天地酒吧街1号\"],\"description\":[\"骚里骚气。\"],\"startDay\":[\"1\"],\"endDay\":[\"7\"],\"startTime\":[\"20:30\"],\"endTime\":[\"06:00\"],\"contact\":[\"0789-84337777\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-28 16:45:31');
INSERT INTO `sys_oper_log` VALUES (72, '商家管理', 2, 'com.ruoyi.web.controller.system.MerchantController.editSave()', 'POST', 1, 'admin', '研发部门', '/admin/system/merchant/edit', '223.152.61.180', 'XX XX', '{\"id\":[\"2\"],\"type\":[\"1\"],\"name\":[\"骚吧\"],\"logo\":[\"http://124.71.12.51/admin/profile/upload/2022/08/28/1492_0_pwdWlLFJ7aKzxHMmGNdh_20220828164636A001.jpg\"],\"city\":[\"太原\"],\"address\":[\"小店区亲贤街茂业天地酒吧街1号\"],\"description\":[\"骚里骚气。\"],\"startDay\":[\"1\"],\"endDay\":[\"7\"],\"startTime\":[\"20:30\"],\"endTime\":[\"06:00\"],\"contact\":[\"0789-84337777\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-28 16:46:42');
INSERT INTO `sys_oper_log` VALUES (73, '商家管理', 2, 'com.ruoyi.web.controller.system.MerchantController.editSave()', 'POST', 1, 'admin', '研发部门', '/admin/system/merchant/edit', '223.152.61.180', 'XX XX', '{\"id\":[\"1\"],\"type\":[\"1\"],\"name\":[\"MIAMI CLUB\"],\"logo\":[\"http://124.71.12.51/admin/profile/upload/2022/08/28/1492_0_pwdWlLFJ7aKzxHMmGNdh_20220828164653A002.jpg\"],\"city\":[\"太原\"],\"address\":[\"小店区亲贤街茂业天地酒吧街1号\"],\"description\":[\"就很吊！\"],\"startDay\":[\"1\"],\"endDay\":[\"7\"],\"startTime\":[\"20:30\"],\"endTime\":[\"06:00\"],\"contact\":[\"0789-84337777\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-28 16:46:55');
INSERT INTO `sys_oper_log` VALUES (74, '商家管理', 1, 'com.ruoyi.web.controller.system.MerchantController.addSave()', 'POST', 1, 'admin', '研发部门', '/admin/system/merchant/add', '118.255.175.45', 'XX XX', '{\"type\":[\"1\"],\"name\":[\"庐盏-国潮酒馆\"],\"logo\":[\"\"],\"city\":[\"太原\"],\"address\":[\"河东区中山大街128号\"],\"description\":[\"中国文化,国潮酒馆\"],\"startDay\":[\"1\"],\"endDay\":[\"7\"],\"startTime\":[\"2022-08-28\"],\"endTime\":[\"2022-09-28\"],\"contact\":[\"16622222\"]}', NULL, 1, '\n### Error updating database.  Cause: java.sql.SQLException: Field \'logo\' doesn\'t have a default value\n### The error may exist in URL [jar:file:/opt/bar/bar-admin/bar-admin.jar!/BOOT-INF/lib/bar-system-1.0.0.jar!/mapper/system/MerchantMapper.xml]\n### The error may involve com.ruoyi.system.mapper.MerchantMapper.insertMerchant-Inline\n### The error occurred while setting parameters\n### SQL: insert into merchant          ( type,             name,                          city,             address,             description,             start_day,             end_day,             start_time,             end_time,             contact,             create_by,             create_time,             update_by,             update_time )           values ( ?,             ?,                          ?,             ?,             ?,             ?,             ?,             ?,             ?,             ?,             ?,             ?,             ?,             ? )\n### Cause: java.sql.SQLException: Field \'logo\' doesn\'t have a default value\n; Field \'logo\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'logo\' doesn\'t have a default value', '2022-08-28 17:27:09');
INSERT INTO `sys_oper_log` VALUES (75, '商家管理', 1, 'com.ruoyi.web.controller.system.MerchantController.addSave()', 'POST', 1, 'admin', '研发部门', '/admin/system/merchant/add', '118.255.175.45', 'XX XX', '{\"type\":[\"1\"],\"name\":[\"庐盏-国潮酒馆\"],\"logo\":[\"http://124.71.12.51/admin/profile/upload/2022/08/28/shop2_20220828172714A003.jpg\"],\"city\":[\"太原\"],\"address\":[\"河东区中山大街128号\"],\"description\":[\"中国文化,国潮酒馆\"],\"startDay\":[\"1\"],\"endDay\":[\"7\"],\"startTime\":[\"2022-08-28\"],\"endTime\":[\"2022-09-28\"],\"contact\":[\"16622222\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-08-28 17:27:16');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '岗位信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 'ceo', '董事长', 1, '0', 'admin', '2022-08-01 14:42:29', '', NULL, '');
INSERT INTO `sys_post` VALUES (2, 'se', '项目经理', 2, '0', 'admin', '2022-08-01 14:42:29', '', NULL, '');
INSERT INTO `sys_post` VALUES (3, 'hr', '人力资源', 3, '0', 'admin', '2022-08-01 14:42:29', '', NULL, '');
INSERT INTO `sys_post` VALUES (4, 'user', '普通员工', 4, '0', 'admin', '2022-08-01 14:42:29', '', NULL, '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, '1', '0', '0', 'admin', '2022-08-01 14:42:30', '', NULL, '超级管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '2', '0', '0', 'admin', '2022-08-01 14:42:30', 'admin', '2022-08-09 17:17:49', '普通角色');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色和部门关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES (2, 100);
INSERT INTO `sys_role_dept` VALUES (2, 101);
INSERT INTO `sys_role_dept` VALUES (2, 105);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 100);
INSERT INTO `sys_role_menu` VALUES (2, 101);
INSERT INTO `sys_role_menu` VALUES (2, 102);
INSERT INTO `sys_role_menu` VALUES (2, 103);
INSERT INTO `sys_role_menu` VALUES (2, 104);
INSERT INTO `sys_role_menu` VALUES (2, 105);
INSERT INTO `sys_role_menu` VALUES (2, 106);
INSERT INTO `sys_role_menu` VALUES (2, 107);
INSERT INTO `sys_role_menu` VALUES (2, 108);
INSERT INTO `sys_role_menu` VALUES (2, 109);
INSERT INTO `sys_role_menu` VALUES (2, 111);
INSERT INTO `sys_role_menu` VALUES (2, 112);
INSERT INTO `sys_role_menu` VALUES (2, 113);
INSERT INTO `sys_role_menu` VALUES (2, 114);
INSERT INTO `sys_role_menu` VALUES (2, 115);
INSERT INTO `sys_role_menu` VALUES (2, 116);
INSERT INTO `sys_role_menu` VALUES (2, 500);
INSERT INTO `sys_role_menu` VALUES (2, 501);
INSERT INTO `sys_role_menu` VALUES (2, 1000);
INSERT INTO `sys_role_menu` VALUES (2, 1001);
INSERT INTO `sys_role_menu` VALUES (2, 1002);
INSERT INTO `sys_role_menu` VALUES (2, 1003);
INSERT INTO `sys_role_menu` VALUES (2, 1004);
INSERT INTO `sys_role_menu` VALUES (2, 1005);
INSERT INTO `sys_role_menu` VALUES (2, 1006);
INSERT INTO `sys_role_menu` VALUES (2, 1007);
INSERT INTO `sys_role_menu` VALUES (2, 1008);
INSERT INTO `sys_role_menu` VALUES (2, 1009);
INSERT INTO `sys_role_menu` VALUES (2, 1010);
INSERT INTO `sys_role_menu` VALUES (2, 1011);
INSERT INTO `sys_role_menu` VALUES (2, 1012);
INSERT INTO `sys_role_menu` VALUES (2, 1013);
INSERT INTO `sys_role_menu` VALUES (2, 1014);
INSERT INTO `sys_role_menu` VALUES (2, 1015);
INSERT INTO `sys_role_menu` VALUES (2, 1016);
INSERT INTO `sys_role_menu` VALUES (2, 1017);
INSERT INTO `sys_role_menu` VALUES (2, 1018);
INSERT INTO `sys_role_menu` VALUES (2, 1019);
INSERT INTO `sys_role_menu` VALUES (2, 1020);
INSERT INTO `sys_role_menu` VALUES (2, 1021);
INSERT INTO `sys_role_menu` VALUES (2, 1022);
INSERT INTO `sys_role_menu` VALUES (2, 1023);
INSERT INTO `sys_role_menu` VALUES (2, 1024);
INSERT INTO `sys_role_menu` VALUES (2, 1025);
INSERT INTO `sys_role_menu` VALUES (2, 1026);
INSERT INTO `sys_role_menu` VALUES (2, 1027);
INSERT INTO `sys_role_menu` VALUES (2, 1028);
INSERT INTO `sys_role_menu` VALUES (2, 1029);
INSERT INTO `sys_role_menu` VALUES (2, 1030);
INSERT INTO `sys_role_menu` VALUES (2, 1031);
INSERT INTO `sys_role_menu` VALUES (2, 1032);
INSERT INTO `sys_role_menu` VALUES (2, 1033);
INSERT INTO `sys_role_menu` VALUES (2, 1034);
INSERT INTO `sys_role_menu` VALUES (2, 1035);
INSERT INTO `sys_role_menu` VALUES (2, 1036);
INSERT INTO `sys_role_menu` VALUES (2, 1037);
INSERT INTO `sys_role_menu` VALUES (2, 1038);
INSERT INTO `sys_role_menu` VALUES (2, 1039);
INSERT INTO `sys_role_menu` VALUES (2, 1040);
INSERT INTO `sys_role_menu` VALUES (2, 1041);
INSERT INTO `sys_role_menu` VALUES (2, 1042);
INSERT INTO `sys_role_menu` VALUES (2, 1043);
INSERT INTO `sys_role_menu` VALUES (2, 1044);
INSERT INTO `sys_role_menu` VALUES (2, 1045);
INSERT INTO `sys_role_menu` VALUES (2, 1046);
INSERT INTO `sys_role_menu` VALUES (2, 1047);
INSERT INTO `sys_role_menu` VALUES (2, 1048);
INSERT INTO `sys_role_menu` VALUES (2, 1049);
INSERT INTO `sys_role_menu` VALUES (2, 1057);
INSERT INTO `sys_role_menu` VALUES (2, 1058);
INSERT INTO `sys_role_menu` VALUES (2, 1059);
INSERT INTO `sys_role_menu` VALUES (2, 1060);
INSERT INTO `sys_role_menu` VALUES (2, 1061);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `login_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '登录账号',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '00' COMMENT '用户类型（00系统用户 01注册用户）',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '头像路径',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '盐加密',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `pwd_update_date` datetime(0) NULL DEFAULT NULL COMMENT '密码最后更新时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 103, 'admin', '若依', '00', 'ry@163.com', '15888888888', '1', '', '29c67a30398638269fe600f73a054934', '111111', '0', '0', '118.255.175.45', '2022-08-28 17:03:08', '2022-08-01 14:42:29', 'admin', '2022-08-01 14:42:29', '', '2022-08-28 17:03:07', '管理员');
INSERT INTO `sys_user` VALUES (2, 105, 'ry', '若依', '00', 'ry@qq.com', '15666666666', '1', '', '8e6d98b90472783cc73c17047ddccf36', '222222', '0', '0', '127.0.0.1', '2022-08-01 14:42:29', '2022-08-01 14:42:29', 'admin', '2022-08-01 14:42:29', '', NULL, '测试员');

-- ----------------------------
-- Table structure for sys_user_online
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_online`;
CREATE TABLE `sys_user_online`  (
  `sessionId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '用户会话id',
  `login_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '登录账号',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '部门名称',
  `ipaddr` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '操作系统',
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '在线状态on_line在线off_line离线',
  `start_timestamp` datetime(0) NULL DEFAULT NULL COMMENT 'session创建时间',
  `last_access_time` datetime(0) NULL DEFAULT NULL COMMENT 'session最后访问时间',
  `expire_time` int(5) NULL DEFAULT 0 COMMENT '超时时间，单位为分钟',
  PRIMARY KEY (`sessionId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '在线用户记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_online
-- ----------------------------
INSERT INTO `sys_user_online` VALUES ('3e5e1c24-81b6-451b-a969-935b8485ec0d', 'admin', '研发部门', '223.152.61.180', 'XX XX', 'Chrome 8', 'Windows 10', 'on_line', '2022-08-28 16:42:39', '2022-08-28 16:51:36', 1800000);
INSERT INTO `sys_user_online` VALUES ('db38dae5-1473-48f4-8df2-be73b2c68014', 'admin', '研发部门', '118.255.175.45', 'XX XX', 'Chrome 8', 'Windows 10', 'on_line', '2022-08-28 17:02:44', '2022-08-28 17:26:25', 1800000);

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户与岗位关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1, 1);
INSERT INTO `sys_user_post` VALUES (2, 2);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `openid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '微信用户的唯一标识',
  `session_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '会话密钥',
  `unionid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户在微信开放平台的唯一标识符',
  `wx_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '微信昵称',
  `wx_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '微信头像',
  `salt` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '盐加密',
  `access_token` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '访问令牌',
  `access_token_time` datetime(0) NULL DEFAULT NULL COMMENT '访问令牌有效时间',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '最近一次登录时间',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_openid`(`openid`) USING BTREE COMMENT '用户唯一标识',
  UNIQUE INDEX `uk_access_token`(`access_token`) USING BTREE COMMENT '唯一访问令牌'
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'oJMAM5fCnwPFRlfswdzvZ5VZAblc', 'ueSLWDSIgDxcukb3TSYxbw==', NULL, '🐶', 'https://thirdwx.qlogo.cn/mmopen/vi_32/VkqPIiaHS5GCn4es0BRA9XM81JyO5Yt4PZE1eb48ykib2UlrM0GAeC8rEoVKKjsQQiblLGXj0h1jFhXWlghvUaEqg/132', '8ef054', '21028dc2a041dd40a8ee7a6bb053c195', '2022-09-27 16:15:15', '2022-08-28 16:15:15', '2022-08-28 16:12:57', '2022-08-28 16:15:15');

SET FOREIGN_KEY_CHECKS = 1;

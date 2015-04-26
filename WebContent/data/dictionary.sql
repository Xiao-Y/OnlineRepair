/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50045
Source Host           : 127.0.0.1:3306
Source Database       : onlinerepair

Target Server Type    : MYSQL
Target Server Version : 50045
File Encoding         : 65001

Date: 2015-04-26 20:37:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary` (
  `SEQID` int(11) NOT NULL auto_increment,
  `KEYWORD` varchar(255) default NULL,
  `DDL_CODE` int(11) default NULL,
  `DDL_NAME` varchar(255) default NULL,
  PRIMARY KEY  (`SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES ('97', '角色类型', '1', '系统管理员');
INSERT INTO `dictionary` VALUES ('98', '角色类型', '2', '高级管理员');
INSERT INTO `dictionary` VALUES ('99', '角色类型', '3', '业务用户');
INSERT INTO `dictionary` VALUES ('101', '角色类型', '5', '普通用户');
INSERT INTO `dictionary` VALUES ('102', '性别', '1', '男');
INSERT INTO `dictionary` VALUES ('103', '性别', '2', '女');
INSERT INTO `dictionary` VALUES ('104', '性别', '3', '保密');
INSERT INTO `dictionary` VALUES ('105', '区域', '1', '教室');
INSERT INTO `dictionary` VALUES ('106', '区域', '2', '寝室');
INSERT INTO `dictionary` VALUES ('107', '区域', '3', '机房');
INSERT INTO `dictionary` VALUES ('108', '区域', '4', '办公室');
INSERT INTO `dictionary` VALUES ('109', '运行状态', '1', '运行正常');
INSERT INTO `dictionary` VALUES ('110', '运行状态', '2', '运行异常');
INSERT INTO `dictionary` VALUES ('111', '安装位置', '1', 'A4059');
INSERT INTO `dictionary` VALUES ('112', '安装位置', '2', '6139');
INSERT INTO `dictionary` VALUES ('113', '安装位置', '3', 'D4982');
INSERT INTO `dictionary` VALUES ('114', '安装位置', '4', 'D1069');
INSERT INTO `dictionary` VALUES ('115', '安装位置', '5', 'U7867');
INSERT INTO `dictionary` VALUES ('116', '优先级别', '1', '高');
INSERT INTO `dictionary` VALUES ('117', '优先级别', '2', '中');
INSERT INTO `dictionary` VALUES ('118', '优先级别', '3', '低');
INSERT INTO `dictionary` VALUES ('119', '维护类型', '1', '电工');
INSERT INTO `dictionary` VALUES ('120', '维护类型', '2', '木工');
INSERT INTO `dictionary` VALUES ('121', '维护类型', '3', '泥工');
INSERT INTO `dictionary` VALUES ('122', '维护类型', '4', '水工');
INSERT INTO `dictionary` VALUES ('123', '维护类型', '5', '业务用户');
INSERT INTO `dictionary` VALUES ('124', '审核状态', '1', '待审核');
INSERT INTO `dictionary` VALUES ('125', '审核状态', '2', '已通过');
INSERT INTO `dictionary` VALUES ('126', '审核状态', '3', '未通过');
INSERT INTO `dictionary` VALUES ('127', '评价状态', '1', '已评价');
INSERT INTO `dictionary` VALUES ('128', '评价状态', '2', '待评价');
INSERT INTO `dictionary` VALUES ('131', '维护状态', '1', '未处理');
INSERT INTO `dictionary` VALUES ('132', '维护状态', '2', '待维护');
INSERT INTO `dictionary` VALUES ('133', '维护状态', '3', '已维护');

/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : tetris

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2018-12-11 11:39:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_point
-- ----------------------------
DROP TABLE IF EXISTS `user_point`;
CREATE TABLE `user_point` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `point` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_point
-- ----------------------------
INSERT INTO `user_point` VALUES ('1', '汤姆', '111');
INSERT INTO `user_point` VALUES ('2', '杰瑞', '123');
INSERT INTO `user_point` VALUES ('3', '李华', '121');
INSERT INTO `user_point` VALUES ('4', '韩梅梅', '200');
INSERT INTO `user_point` VALUES ('6', 'test2', '10');
INSERT INTO `user_point` VALUES ('7', ' test3', '20');
INSERT INTO `user_point` VALUES ('8', 'test4', '70');

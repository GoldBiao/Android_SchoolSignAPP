/*
 Navicat Premium Data Transfer

 Source Server         : mysql5.6
 Source Server Type    : MySQL
 Source Server Version : 50620
 Source Host           : localhost:3306
 Source Schema         : db_school_sign

 Target Server Type    : MySQL
 Target Server Version : 50620
 File Encoding         : 65001

 Date: 22/11/2022 00:15:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for kecheng
-- ----------------------------
DROP TABLE IF EXISTS `kecheng`;
CREATE TABLE `kecheng`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` varchar(111) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of kecheng
-- ----------------------------
INSERT INTO `kecheng` VALUES (1, '软件工程理论', '6A-204', '2022-12-03 15:00', 4);
INSERT INTO `kecheng` VALUES (11, '高数', '6A-203', '2022-12-04 14:00', 4);
INSERT INTO `kecheng` VALUES (12, '计算机科学与技术', '7B-203', '2022-11-23 14:00', 5);
INSERT INTO `kecheng` VALUES (13, '软件工程理论', '6B-203', '2022-12-06 10:00', 4);

-- ----------------------------
-- Table structure for srecord
-- ----------------------------
DROP TABLE IF EXISTS `srecord`;
CREATE TABLE `srecord`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) NULL DEFAULT NULL,
  `kid` int(11) NULL DEFAULT NULL,
  `time` varchar(111) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of srecord
-- ----------------------------
INSERT INTO `srecord` VALUES (1, 15, 1, '2022-11-21 01:05');
INSERT INTO `srecord` VALUES (2, 16, 13, '2022-11-21 23:48');
INSERT INTO `srecord` VALUES (3, 16, 1, '2022-11-21 23:53');

-- ----------------------------
-- Table structure for stucent
-- ----------------------------
DROP TABLE IF EXISTS `stucent`;
CREATE TABLE `stucent`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `banji` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of stucent
-- ----------------------------
INSERT INTO `stucent` VALUES (15, '1001', '王洁', 'http://192.168.1.6:8080/tangyan.png', '13511113333', '计算机3班');
INSERT INTO `stucent` VALUES (16, '1002', '张豪', 'http://192.168.1.6:8080/luoyunxi.jpg', '13598930934', '计算机3班');

-- ----------------------------
-- Table structure for tercher
-- ----------------------------
DROP TABLE IF EXISTS `tercher`;
CREATE TABLE `tercher`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tercher
-- ----------------------------
INSERT INTO `tercher` VALUES (4, 'TH001', '123456', '13511112222', '张大帅');
INSERT INTO `tercher` VALUES (5, 'TH002', '123456', '13562983923', '王小天');

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : excellentcourse

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 04/04/2021 22:42:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_answer
-- ----------------------------
DROP TABLE IF EXISTS `t_answer`;
CREATE TABLE `t_answer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '留言id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '留言内容',
  `answeruser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '留言人',
  `pubtime2` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '留言时间',
  `topicId` int(11) NOT NULL COMMENT '帖子id',
  `state2` int(11) NULL DEFAULT 1 COMMENT '留言状态 1：留言成功',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKC06B980D29D8874D`(`topicId`) USING BTREE,
  CONSTRAINT `FKC06B980D29D8874D` FOREIGN KEY (`topicId`) REFERENCES `t_topic` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_answer
-- ----------------------------
INSERT INTO `t_answer` VALUES (11, '<p>你好张三，我是李四，很高兴认识你</p>\r\n', 'lisi', '2021-04-04 22:40:02', 41, 1);

-- ----------------------------
-- Table structure for t_news
-- ----------------------------
DROP TABLE IF EXISTS `t_news`;
CREATE TABLE `t_news`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章标题',
  `article` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章内容',
  `time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章发布时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 67 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_news
-- ----------------------------
INSERT INTO `t_news` VALUES (66, '面向对象的特征', '<p>面向对象有三个特征：分别是继承、封装、多态</p>\r\n', '2021-04-01 22:03:21');

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permissionName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `roleId` int(11) NULL DEFAULT NULL COMMENT '1：管理员，2：普通用户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `roleId`(`roleId`) USING BTREE,
  CONSTRAINT `t_permission_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES (1, 'admin:*', 1);
INSERT INTO `t_permission` VALUES (2, 'user:*', 2);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `roleName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'admin');
INSERT INTO `t_role` VALUES (2, 'user');

-- ----------------------------
-- Table structure for t_source
-- ----------------------------
DROP TABLE IF EXISTS `t_source`;
CREATE TABLE `t_source`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文件id',
  `filename` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名称',
  `filepath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件上传路径',
  `pubtime` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件上传时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_source
-- ----------------------------
INSERT INTO `t_source` VALUES (33, '目标销量 2021-03-23.xlsx', 'D:/upload/source/目标销量 2021-03-23.xlsx', '2021-04-01 21:52:30');
INSERT INTO `t_source` VALUES (34, '新人入职须知.txt', 'D:/upload/source/新人入职须知.txt', '2021-04-04 21:24:37');
INSERT INTO `t_source` VALUES (35, '在线学习交流平台功能概要.txt', 'D:/upload/source/在线学习交流平台功能概要.txt', '2021-04-04 21:41:41');

-- ----------------------------
-- Table structure for t_topic
-- ----------------------------
DROP TABLE IF EXISTS `t_topic`;
CREATE TABLE `t_topic`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '帖子id',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '帖子名称',
  `pubtime` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布时间',
  `detail` varchar(3000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '帖子内容',
  `edituser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发帖人',
  `state` int(11) NULL DEFAULT 1 COMMENT '帖子状态 1:发帖成功',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_topic
-- ----------------------------
INSERT INTO `t_topic` VALUES (41, '新人加入，大家多多关照', '2021-04-04 22:32:12', '<p>你好，我是张三，刚加入在线学习交流平台，请大家多多关照哈！</p>\r\n', 'zhangsan', 1);
INSERT INTO `t_topic` VALUES (43, '111', '2021-04-04 22:32:53', '<p>22222</p>\r\n', 'zhangsan', 1);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `state` int(11) NULL DEFAULT 1 COMMENT '状态 1:已激活 2:未激活',
  `roleId` int(11) NULL DEFAULT 2 COMMENT '角色 1:管理员 2:普通用户',
  `integral` int(11) NOT NULL DEFAULT 100 COMMENT '积分，默认有100积分',
  `isUpload` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '1：表示可以上传，2：表示不可以上传',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `roleId`(`roleId`) USING BTREE,
  CONSTRAINT `t_user_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (6, 'admin', '4a7d1ed414474e4033ac29ccb8653d9b', '123@qq.com', 1, 1, 100, '1');
INSERT INTO `t_user` VALUES (33, 'zhangsan', '4a7d1ed414474e4033ac29ccb8653d9b', '1060678024@qq.com', 1, 2, 40, '1');
INSERT INTO `t_user` VALUES (34, 'lisi', '4a7d1ed414474e4033ac29ccb8653d9b', 'lisi@qq.com', 1, 2, 50, '2');

-- ----------------------------
-- Table structure for t_video
-- ----------------------------
DROP TABLE IF EXISTS `t_video`;
CREATE TABLE `t_video`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '视频id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频名称',
  `size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频大小',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频格式',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频上传路径',
  `uploadTime` datetime(0) NULL DEFAULT NULL COMMENT '视频上传时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_video
-- ----------------------------
INSERT INTO `t_video` VALUES (12, '深入理解Java虚拟机', '1.16MB', '.mp4', '/video/1.mp4', '2021-04-01 13:56:18');
INSERT INTO `t_video` VALUES (13, 'Java从入门到精通', '2.29MB', '.mp4', '/video/7.mp4', '2021-04-01 13:57:50');

SET FOREIGN_KEY_CHECKS = 1;

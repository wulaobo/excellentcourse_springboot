/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : safe

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-09-05 09:32:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_answer
-- ----------------------------
DROP TABLE IF EXISTS `t_answer`;
CREATE TABLE `t_answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `answeruser` varchar(50) DEFAULT NULL,
  `pubtime2` varchar(50) DEFAULT NULL,
  `topicId` int(11) NOT NULL,
  `state2` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FKC06B980D29D8874D` (`topicId`),
  CONSTRAINT `FKC06B980D29D8874D` FOREIGN KEY (`topicId`) REFERENCES `t_topic` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_answer
-- ----------------------------
INSERT INTO `t_answer` VALUES ('3', '<p>小姐姐，你好鸭</p>\r\n', 'qianqi', '2019-06-28', '12', '1');
INSERT INTO `t_answer` VALUES ('4', '<p>先看黑马，尚硅谷的全套java视频来学习，这样可以更好地入门</p>\r\n', 'qianqi', '2019-06-28', '14', '2');
INSERT INTO `t_answer` VALUES ('8', '<p>我同意二楼qianqi的观点，不过我建议看过视频后，应该再来看书，这样会更好，会更深入。</p>\r\n', 'lili', '2019-06-29', '14', '1');

-- ----------------------------
-- Table structure for t_news
-- ----------------------------
DROP TABLE IF EXISTS `t_news`;
CREATE TABLE `t_news` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `article` text,
  `time` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_news
-- ----------------------------
INSERT INTO `t_news` VALUES ('55', '基于Matlab的数字电视原理实验教学研究 ', '<p>&nbsp;</p>\r\n\r\n<p>针对电视原理实验教学的需求,以演示实验为主,由于学生无法了解数字电视信号处理的细节与算法,为此笔者基于Matlab软件开发建设了数字电视原理实验教学平台。根据数字电视原理教学内容设计了6个实验模块,每个模块包含不同的实验项目,涵盖了数字电视信号的产生、处理和压缩编码预测等内容。给出了实验模块的设计原则,并依次阐述了模块的实验原理及内容,以具体实验模块的操作为例讲解了平台的使用方法。实际应用表明,数字电视实验教学平台满足了课程的实验教学需求,取得了较好的教学效果。</p>\r\n', '2019-05-14');
INSERT INTO `t_news` VALUES ('60', '交个朋友？', '<p>交个朋友好吗？</p>\r\n', '2019-07-01');
INSERT INTO `t_news` VALUES ('61', '你的业余爱好是什么？', '<p>看经典电影，阿甘正传啥的</p>\r\n', '2019-07-01');
INSERT INTO `t_news` VALUES ('62', '你最喜欢的运动是什么？', '<p>跑步啊</p>\r\n', '2019-07-01');
INSERT INTO `t_news` VALUES ('65', '哼哼', '<p>哼哼哈嘿</p>\r\n', '2019-09-05');

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permissionName` varchar(50) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `t_permission_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1', 'admin:*', '1');
INSERT INTO `t_permission` VALUES ('2', 'user:*', '2');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'admin');
INSERT INTO `t_role` VALUES ('2', 'user');

-- ----------------------------
-- Table structure for t_source
-- ----------------------------
DROP TABLE IF EXISTS `t_source`;
CREATE TABLE `t_source` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `filename` varchar(50) DEFAULT NULL,
  `filepath` varchar(255) DEFAULT NULL,
  `pubtime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_source
-- ----------------------------
INSERT INTO `t_source` VALUES ('31', '新建文本文档.txt', 'D:/upload/source/新建文本文档.txt', '2019-09-04');

-- ----------------------------
-- Table structure for t_topic
-- ----------------------------
DROP TABLE IF EXISTS `t_topic`;
CREATE TABLE `t_topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `pubtime` varchar(50) DEFAULT NULL,
  `detail` varchar(255) NOT NULL,
  `edituser` varchar(50) DEFAULT NULL,
  `state` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_topic
-- ----------------------------
INSERT INTO `t_topic` VALUES ('12', '1111', '2019-04-10', '<p>在此输入内容...</p>\r\n', 'test', '1');
INSERT INTO `t_topic` VALUES ('14', 'java基础？', '2019-05-09', '<p>大佬们好，我是一位大一的萌新，请问java基础该怎么学呢？</p>\r\n', 'zhangsan', '1');
INSERT INTO `t_topic` VALUES ('19', '圆通快递在哪？', '2019-06-27', '<p>你们好，请问谁知道圆通快递在哪？</p>\r\n', 'lisi', '1');
INSERT INTO `t_topic` VALUES ('21', '实验二', '2019-06-26', '<p>你好的你是领导检查</p>\r\n', 'lisi', '1');
INSERT INTO `t_topic` VALUES ('24', '实验三', '2019-06-27', '<p>哈哈哈</p>\r\n', 'lili', '1');
INSERT INTO `t_topic` VALUES ('27', '我的毕业设计重构工作开始了', '2019-06-27', '<p>毕业设计用boot重构，正在进行中...</p>\r\n', 'lili', '1');
INSERT INTO `t_topic` VALUES ('33', '商店出售的', '2019-06-27', '<p>的v开发女排</p>\r\n', 'lili', '1');
INSERT INTO `t_topic` VALUES ('35', '给他让他给如果', '2019-06-27', '<p>士大夫vever</p>\r\n', 'lili', '1');
INSERT INTO `t_topic` VALUES ('36', '实验二', '2019-06-27', '<p>在此输入内容</p>\r\n', 'lili', '1');
INSERT INTO `t_topic` VALUES ('37', '项目介绍', '2019-06-27', '<p>在此输入内容sd</p>\r\n', 'lili', '1');
INSERT INTO `t_topic` VALUES ('38', 'git视频教程', '2019-06-27', '<p>在此输入内容sf</p>\r\n', 'lili', '1');
INSERT INTO `t_topic` VALUES ('39', '有想找程序员男朋友的小姐姐吗？', '2019-09-05', '<p>本人97年，大学本科学历，想结束母胎单身，有想认识一下的小姐姐赶快联系我哦！！！</p>\r\n', 'lisi', '1');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `state` int(11) DEFAULT '1',
  `roleId` int(11) DEFAULT '2',
  PRIMARY KEY (`id`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `t_user_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('6', 'admin', '4a7d1ed414474e4033ac29ccb8653d9b', '123@qq.com', '1', '1');
INSERT INTO `t_user` VALUES ('33', 'zhangsan', '4a7d1ed414474e4033ac29ccb8653d9b', '1060678024@qq.com', '2', '2');
INSERT INTO `t_user` VALUES ('34', 'lisi', '4a7d1ed414474e4033ac29ccb8653d9b', '123@qq.com', '2', '2');
INSERT INTO `t_user` VALUES ('37', '江继康', '4a7d1ed414474e4033ac29ccb8653d9b', 'aa@163.comaaa', '1', '2');
INSERT INTO `t_user` VALUES ('38', 'haha', '4a7d1ed414474e4033ac29ccb8653d9b', 'bb@163.comaaa', '1', '2');
INSERT INTO `t_user` VALUES ('39', 'hengheng', '4a7d1ed414474e4033ac29ccb8653d9b', 'heng@163.com', '1', '2');
INSERT INTO `t_user` VALUES ('40', 'lili', '4a7d1ed414474e4033ac29ccb8653d9b', 'lili@qq.com', '1', '2');
INSERT INTO `t_user` VALUES ('41', 'qianqi', '4a7d1ed414474e4033ac29ccb8653d9b', 'qianqi@aliyun.com', '1', '2');

-- ----------------------------
-- Table structure for t_video
-- ----------------------------
DROP TABLE IF EXISTS `t_video`;
CREATE TABLE `t_video` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `uploadTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_video
-- ----------------------------
INSERT INTO `t_video` VALUES ('1', '经典影片1', '899.83KB', '.mp4', 'video/1.mp4', '2019-09-04 07:23:55');
INSERT INTO `t_video` VALUES ('2', '经典影片二', '1.58MB', '.mp4', 'video/2.mp4', '2019-09-04 07:24:12');
INSERT INTO `t_video` VALUES ('3', '经典影片3', '1.54MB', '.mp4', 'video/3.mp4', '2019-09-04 07:24:31');
INSERT INTO `t_video` VALUES ('4', '经典影片四', '2.47MB', '.mp4', 'video/4.mp4', '2019-09-04 07:24:47');

/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : backupanddeploy

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 06/07/2022 00:40:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_backupDB
-- ----------------------------
DROP TABLE IF EXISTS `sys_backupDB`;
CREATE TABLE `sys_backupDB` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `db_host_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `db_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `db_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `db_port` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `db_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `tar_dir` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `restore_file` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_backupDB
-- ----------------------------
BEGIN;
INSERT INTO `sys_backupDB` (`ID`, `db_host_ip`, `db_username`, `db_password`, `db_port`, `db_name`, `tar_dir`, `restore_file`) VALUES (1, '43.142.114.213', 'root', '123456', '3306', 'tp_music', '/Users/le/Downloads/', '/Users/le/Downloads/2022-07-06/tp_music.sql');
INSERT INTO `sys_backupDB` (`ID`, `db_host_ip`, `db_username`, `db_password`, `db_port`, `db_name`, `tar_dir`, `restore_file`) VALUES (2, '1', '1', '5', '1', '1', '1', NULL);
INSERT INTO `sys_backupDB` (`ID`, `db_host_ip`, `db_username`, `db_password`, `db_port`, `db_name`, `tar_dir`, `restore_file`) VALUES (3, '1', '1', '5', '1', '1', '1', NULL);
INSERT INTO `sys_backupDB` (`ID`, `db_host_ip`, `db_username`, `db_password`, `db_port`, `db_name`, `tar_dir`, `restore_file`) VALUES (4, '1', '1', '5', '1', '1', '1', NULL);
INSERT INTO `sys_backupDB` (`ID`, `db_host_ip`, `db_username`, `db_password`, `db_port`, `db_name`, `tar_dir`, `restore_file`) VALUES (5, '1', '1', '3', '1', '1', '1', NULL);
INSERT INTO `sys_backupDB` (`ID`, `db_host_ip`, `db_username`, `db_password`, `db_port`, `db_name`, `tar_dir`, `restore_file`) VALUES (6, '1', '1', '5', '1', '1', '1', NULL);
INSERT INTO `sys_backupDB` (`ID`, `db_host_ip`, `db_username`, `db_password`, `db_port`, `db_name`, `tar_dir`, `restore_file`) VALUES (7, '1', '1', '5', '1', '1', '1', NULL);
INSERT INTO `sys_backupDB` (`ID`, `db_host_ip`, `db_username`, `db_password`, `db_port`, `db_name`, `tar_dir`, `restore_file`) VALUES (8, '1', '1', '5', '1', '1', '1', NULL);
INSERT INTO `sys_backupDB` (`ID`, `db_host_ip`, `db_username`, `db_password`, `db_port`, `db_name`, `tar_dir`, `restore_file`) VALUES (9, '1', '1', '5', '1', '1', '1', NULL);
INSERT INTO `sys_backupDB` (`ID`, `db_host_ip`, `db_username`, `db_password`, `db_port`, `db_name`, `tar_dir`, `restore_file`) VALUES (10, '1', '1', '5', '1', '1', '1', NULL);
INSERT INTO `sys_backupDB` (`ID`, `db_host_ip`, `db_username`, `db_password`, `db_port`, `db_name`, `tar_dir`, `restore_file`) VALUES (11, '1', '1', '5', '1', '1', '1', NULL);
INSERT INTO `sys_backupDB` (`ID`, `db_host_ip`, `db_username`, `db_password`, `db_port`, `db_name`, `tar_dir`, `restore_file`) VALUES (12, '1', '1', '5', '1', '1', '1', NULL);
INSERT INTO `sys_backupDB` (`ID`, `db_host_ip`, `db_username`, `db_password`, `db_port`, `db_name`, `tar_dir`, `restore_file`) VALUES (22, 'asdfasd', 'asdfasd', 'asdf', '123', 'asdfasd', 'asdfasd', 'asdf');
INSERT INTO `sys_backupDB` (`ID`, `db_host_ip`, `db_username`, `db_password`, `db_port`, `db_name`, `tar_dir`, `restore_file`) VALUES (23, 'asdf', 'asdf', 'asdf', '123', 'sadf', 'asdf', 'sadfasdf');
INSERT INTO `sys_backupDB` (`ID`, `db_host_ip`, `db_username`, `db_password`, `db_port`, `db_name`, `tar_dir`, `restore_file`) VALUES (24, 'asdfasd', 'wer', 'weqrw', '12323', 'dswf', 'asdfasd', 'asdfas');
COMMIT;

-- ----------------------------
-- Table structure for sys_backupDir
-- ----------------------------
DROP TABLE IF EXISTS `sys_backupDir`;
CREATE TABLE `sys_backupDir` (
  `id` int NOT NULL AUTO_INCREMENT,
  `host_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `port` int NOT NULL,
  `src_dir` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tar_dir` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_backupDir
-- ----------------------------
BEGIN;
INSERT INTO `sys_backupDir` (`id`, `host_ip`, `username`, `password`, `port`, `src_dir`, `tar_dir`) VALUES (1, '43.142.114.213', 'ubuntu', '839119912oO', 22, '/home/ubuntu/deploy', '/Users/le/Downloads/');
INSERT INTO `sys_backupDir` (`id`, `host_ip`, `username`, `password`, `port`, `src_dir`, `tar_dir`) VALUES (4, 'asdfasdf', '12312', 'edfr', 1234, 'asdf', 'sadf');
COMMIT;

-- ----------------------------
-- Table structure for sys_deployDir
-- ----------------------------
DROP TABLE IF EXISTS `sys_deployDir`;
CREATE TABLE `sys_deployDir` (
  `hostIP` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `srcDir` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tarDir` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ID` int NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_deployDir
-- ----------------------------
BEGIN;
INSERT INTO `sys_deployDir` (`hostIP`, `username`, `password`, `srcDir`, `tarDir`, `ID`) VALUES ('43.142.114.213', 'ubuntu', '123456', '/Users/le/Downloads/2022-06-27', '/home/ubuntu/', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '路径',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图标',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `pid` int DEFAULT NULL COMMENT '父级id',
  `page_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '页面路径',
  `sort_num` int DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` (`id`, `name`, `path`, `icon`, `description`, `pid`, `page_path`, `sort_num`) VALUES (1, '数据库备份发布', '/backupdb', 'el-icon-s-grid', 'backup or restore for database', NULL, 'database', 1);
INSERT INTO `sys_menu` (`id`, `name`, `path`, `icon`, `description`, `pid`, `page_path`, `sort_num`) VALUES (2, '项目备份发布', '/backupdir', 'el-icon-s-grid', 'backup or deploy for project', NULL, 'project', 2);
INSERT INTO `sys_menu` (`id`, `name`, `path`, `icon`, `description`, `pid`, `page_path`, `sort_num`) VALUES (3, '数据报表', '/dashbord', 'el-icon-s-marketing', '11', NULL, 'Dashbord', 100);
INSERT INTO `sys_menu` (`id`, `name`, `path`, `icon`, `description`, `pid`, `page_path`, `sort_num`) VALUES (4, '系统管理', NULL, 'el-icon-s-grid', NULL, NULL, NULL, 300);
INSERT INTO `sys_menu` (`id`, `name`, `path`, `icon`, `description`, `pid`, `page_path`, `sort_num`) VALUES (5, '用户管理', '/user', 'el-icon-user', NULL, 4, 'User', 301);
INSERT INTO `sys_menu` (`id`, `name`, `path`, `icon`, `description`, `pid`, `page_path`, `sort_num`) VALUES (6, '角色管理', '/role', 'el-icon-s-custom', NULL, 4, 'Role', 302);
INSERT INTO `sys_menu` (`id`, `name`, `path`, `icon`, `description`, `pid`, `page_path`, `sort_num`) VALUES (7, '菜单管理', '/menu', 'el-icon-menu', NULL, 4, 'Menu', 303);
COMMIT;

-- ----------------------------
-- Table structure for sys_restoreDB
-- ----------------------------
DROP TABLE IF EXISTS `sys_restoreDB`;
CREATE TABLE `sys_restoreDB` (
  `dbHostIP` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `dbUsername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `dbPassword` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `dbPort` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `dbName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `restoreFile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_restoreDB
-- ----------------------------
BEGIN;
INSERT INTO `sys_restoreDB` (`dbHostIP`, `dbUsername`, `dbPassword`, `dbPort`, `dbName`, `restoreFile`) VALUES ('43.142.114.213', 'root', '123456', '3306', 'tp_music', '/Users/le/Downloads/2022-06-27/backup.sql');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `flag` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '唯一标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `name`, `description`, `flag`) VALUES (1, '管理员', '管理员', 'ROLE_ADMIN');
INSERT INTO `sys_role` (`id`, `name`, `description`, `flag`) VALUES (2, '用户', '用户', 'ROLE_USER');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` int NOT NULL COMMENT '角色id',
  `menu_id` int NOT NULL COMMENT '菜单id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='角色菜单关系表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 1);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 2);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色',
  `create_time` timestamp NULL DEFAULT (now()),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `username`, `password`, `role`, `create_time`) VALUES (1, 'admin', 'admin', 'ROLE_ADMIN', '2022-07-04 15:25:45');
INSERT INTO `sys_user` (`id`, `username`, `password`, `role`, `create_time`) VALUES (2, 'user', 'user', 'ROLE_USER', '2022-06-27 11:04:11');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

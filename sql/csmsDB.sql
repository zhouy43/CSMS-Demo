/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.5-10.3.7-MariaDB : Database - csmssys
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`csmssys` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `csmssys`;

/*Table structure for table `csms_projects` */

DROP TABLE IF EXISTS `csms_projects`;

CREATE TABLE `csms_projects` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '项目名称',
  `serviceDept` varchar(20) DEFAULT NULL COMMENT '服务落地部门',
  `weibao` varchar(4) DEFAULT NULL COMMENT '在保状态 1在保 0过保',
  `proSource` varchar(10) DEFAULT NULL COMMENT '项目来源',
  `proWeihu` varchar(10) DEFAULT NULL COMMENT '维护状态',
  `proType` varchar(20) DEFAULT NULL COMMENT '项目类型',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='项目管理';

/*Data for the table `csms_projects` */

insert  into `csms_projects`(`id`,`name`,`serviceDept`,`weibao`,`proSource`,`proWeihu`,`proType`,`createdTime`,`modifiedTime`) values (6,'江苏省南通市公安局存储扩容项目','南京办','在保','U9','已维护','售后交付','2020-06-10 08:00:00','2020-06-11 08:00:00'),(9,'江苏省南京市28所某数据中心存储项目（2分中心试点）','南京办','在保','U9','已维护','售后交付','2020-06-11 08:00:00','2020-06-10 08:00:00'),(15,'江苏省测绘工程研究院存储项目','南京办','在保','SAM','未维护','设备借货','2020-06-11 16:38:06','2020-06-11 16:38:06'),(18,'江苏省南京市熊猫电子办公平台存储采购项目','杭州办','在保','SAM','已维护','售前测试','2020-06-12 20:49:18','2020-06-12 20:49:18'),(19,'江苏省无锡江南计算技术研究所存储项目','合肥办','在保','SAM','未维护','设备借货','2020-06-12 20:49:46','2020-06-12 20:49:46'),(21,'江苏省南京邮电大学高性能计算数据调用资源池项目（借转销）','南京办','在保','SAM','已维护','借货转销','2020-06-12 20:53:25','2020-06-15 21:12:03'),(23,'杭州交警总队测试项目','杭州办','过保','SAM','已维护','设备借货','2020-06-12 22:47:48','2020-06-25 20:57:00'),(24,'江苏省食品药品监督管理局存储项目','南京办','在保','U9','已维护','售后交付','2020-06-15 21:11:24','2020-06-20 02:20:07'),(31,'江苏省省委省政府机房建设项目','南京办','在保','SAM','已维护','售后交付','2020-06-17 08:55:47','2020-06-27 19:01:03'),(38,'TestRedis','Redis','在保','U9','已维护','售后交付','2020-06-28 01:39:52','2020-06-28 01:45:19');

/*Table structure for table `csms_roles` */

DROP TABLE IF EXISTS `csms_roles`;

CREATE TABLE `csms_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  `permission` varchar(50) NOT NULL COMMENT '权限信息',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='角色';

/*Data for the table `csms_roles` */

insert  into `csms_roles`(`id`,`name`,`note`,`permission`,`createdTime`,`modifiedTime`) values (10,'系统管理员','最高权限','sys:admin,sys:manager,sys:engineer','2020-06-16 22:30:00','2020-06-16 22:30:00'),(11,'服务经理','次级权限','sys:manager,sys:engineer','2020-06-16 22:31:00','2020-06-16 22:31:00'),(12,'一线工程师','较低权限','sys:engineer','2020-06-16 22:32:00','2020-06-16 22:32:00');

/*Table structure for table `csms_user_roles` */

DROP TABLE IF EXISTS `csms_user_roles`;

CREATE TABLE `csms_user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

/*Data for the table `csms_user_roles` */

insert  into `csms_user_roles`(`id`,`user_id`,`role_id`) values (22,32,12),(25,35,11),(26,36,12),(27,37,11),(28,38,12),(29,39,12),(41,49,11),(42,50,12),(44,51,10),(52,31,10),(53,31,11),(55,48,11);

/*Table structure for table `csms_users` */

DROP TABLE IF EXISTS `csms_users`;

CREATE TABLE `csms_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(50) DEFAULT NULL COMMENT '盐  密码加密时前缀，使加密后的值不同',
  `superiorId` int(11) DEFAULT NULL COMMENT '上级ID',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `valid` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常  默认值 ：1',
  `deptName` varchar(11) DEFAULT NULL COMMENT '部门',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COMMENT='系统用户';

/*Data for the table `csms_users` */

insert  into `csms_users`(`id`,`username`,`password`,`salt`,`superiorId`,`mobile`,`valid`,`deptName`,`createdTime`,`modifiedTime`) values (31,'庄波','e89d58e628d076ab127773fcb493ad44','147255b7-da72-4428-9c5a-03e361f49d1a',30,'17714415126',1,'南京办','2020-06-16 22:40:01','2020-06-25 20:48:59'),(32,'李文启','c4c33035c5d8e840616c128db9f87b25','016a0948-b581-43aa-8a5f-9bb76a80e737',31,'17714415127',1,'南京办','2020-06-16 22:40:11','2020-06-16 22:40:11'),(35,'陈琦','c4c33035c5d8e840616c128db9f87b25','016a0948-b581-43aa-8a5f-9bb76a80e737',30,'17714415130',1,'南宁办','2020-06-16 22:50:23','2020-06-16 22:50:23'),(36,'黄再辉','c4c33035c5d8e840616c128db9f87b25','016a0948-b581-43aa-8a5f-9bb76a80e737',35,'17714415131',1,'南宁办','2020-06-16 22:38:00','2020-06-16 22:38:00'),(37,'傅镇苗','c4c33035c5d8e840616c128db9f87b25','016a0948-b581-43aa-8a5f-9bb76a80e737',30,'17714415132',1,'广州办','2020-06-16 22:38:02','2020-06-16 22:38:02'),(38,'万玉林','c4c33035c5d8e840616c128db9f87b25','016a0948-b581-43aa-8a5f-9bb76a80e737',37,'17714415133',0,'广州办','2020-06-16 22:38:11','2020-06-16 22:38:11'),(39,'刘俊','c4c33035c5d8e840616c128db9f87b25','016a0948-b581-43aa-8a5f-9bb76a80e737',31,'17714415134',0,'南京办','2020-06-16 22:38:15','2020-06-16 22:38:15'),(48,'马思聪','68fc6d4f07db85014418abc29908432f','698a09bc-25fb-43a5-a433-6d58c06092f8',51,'17712908565',1,'南京办','2020-06-20 21:53:16','2020-06-25 20:51:07'),(49,'石岩','0d44f18e63aaf301403dd67ad1963aed','63970221-4f64-45ca-8dc5-4cb9ff0bc210',30,'17712908567',1,'合肥办','2020-06-20 22:03:13','2020-06-20 22:03:13'),(50,'李运','bb4ae613e23f28f562ed5460ece445bc','3300f543-add3-46eb-99a3-8a0ba15c0816',49,'17712908570',1,'合肥办','2020-06-20 22:03:34','2020-06-20 22:03:34'),(51,'admin','307fa1059d8e3410c5810285cd66b0ac','c0f54678-773a-4f16-b5b6-bd13539e3aa5',NULL,'',1,'技术服务部','2020-06-22 20:10:56','2020-06-25 15:55:13');

/*Table structure for table `spare_address` */

DROP TABLE IF EXISTS `spare_address`;

CREATE TABLE `spare_address` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `address` varchar(50) DEFAULT NULL COMMENT '收件地址',
  `note` text DEFAULT NULL COMMENT '备注',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='备件地址';

/*Data for the table `spare_address` */

insert  into `spare_address`(`id`,`address`,`note`,`createdTime`,`modifiedTime`) values (1,'江苏省南京市雨花台区软件大道66号华通科技园1楼','软件谷中心','2020-06-18 16:40:00','2020-06-18 18:41:08'),(2,'江苏省南京市雨花台区绿地之窗商务广场D2 903','南京办事处地址','2020-06-18 16:41:00','2020-06-18 16:41:00'),(3,'江苏省南京市江宁区南方花园','南京租房地址','2020-06-18 16:42:00','2020-06-18 16:42:00'),(4,'江苏省南京市江宁区诚信大道19号','南瑞集团','2020-06-18 17:32:25','2020-06-18 17:32:25'),(8,'江苏省南京市鼓楼区北京西路70号','江苏省委省政府','2020-06-25 15:24:04','2020-06-25 15:24:17');

/*Table structure for table `spare_list` */

DROP TABLE IF EXISTS `spare_list`;

CREATE TABLE `spare_list` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `workOrderId` int(12) DEFAULT NULL COMMENT '工单编号',
  `recipients` varchar(50) DEFAULT NULL COMMENT '收件人',
  `phone` varchar(11) DEFAULT NULL COMMENT '收件人联系方式',
  `address` varchar(50) NOT NULL COMMENT '收件地址',
  `materialsCode` varchar(10) NOT NULL COMMENT '物料编码',
  `materialsName` varchar(50) NOT NULL COMMENT '物料名称',
  `materialsNum` varchar(10) NOT NULL COMMENT '物料数量',
  `materialsNote` text DEFAULT NULL COMMENT '物料备注',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='工单管理';

/*Data for the table `spare_list` */

insert  into `spare_list`(`id`,`workOrderId`,`recipients`,`phone`,`address`,`materialsCode`,`materialsName`,`materialsNum`,`materialsNote`,`createdTime`,`modifiedTime`) values (2,8,'庄波','17712908525','江苏省南京市雨花台区软件大道66号华通科技园1楼','12008654','机械硬盘-8TB-7200转 DSU1616/DSU2624','3','需要宏杉LOGO','2020-06-20 00:37:30','2020-06-20 00:37:30'),(5,17,'马俊','13565467895','江苏省南京市雨花台区绿地之窗商务广场D2 903','120006547','固态SSD-2TB-MLC-2.5寸','2','要求尽快到货','2020-06-20 14:22:00','2020-06-20 14:22:00'),(8,19,'马思聪','12345678910','江苏省南京市雨花台区软件大道66号华通科技园1楼','12008654','机械硬盘-8TB-7200转 DSU1616/DSU2624','1','设备到货损坏','2020-06-25 15:23:16','2020-06-25 15:23:16'),(10,36,'庄波','17712908525','江苏省南京市雨花台区软件大道66号华通科技园1楼','12008654','机械硬盘-8TB-7200转 DSU1616/DSU2624','1','','2020-06-28 01:42:27','2020-06-28 01:42:27');

/*Table structure for table `work_hours` */

DROP TABLE IF EXISTS `work_hours`;

CREATE TABLE `work_hours` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hours` int(11) DEFAULT NULL COMMENT '工时时长',
  `workOrderId` int(11) NOT NULL,
  `relatedProjectId` int(11) DEFAULT NULL COMMENT '关联项目',
  `principalUser` varchar(20) NOT NULL,
  `note` text DEFAULT NULL COMMENT '工时描述',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='工时管理';

/*Data for the table `work_hours` */

insert  into `work_hours`(`id`,`hours`,`workOrderId`,`relatedProjectId`,`principalUser`,`note`,`createdTime`,`modifiedTime`) values (2,3,10,6,'庄波','1.更换备件 2.服务确认','2020-06-17 18:10:00','2020-06-18 16:02:17'),(4,4,8,9,'庄波','开局','2020-06-17 20:10:10','2020-06-17 20:10:10'),(5,11,13,31,'李运','1.软件升级 2.在途','2020-06-18 12:17:34','2020-06-18 12:17:34'),(6,12,9,23,'石岩','1.设备上架; 2.升级软件版本; 3.资源划分; 4.挂载存储资源; 5.业务测试; 6.服务确认;','2020-06-18 12:23:26','2020-06-18 16:01:07'),(7,8,7,6,'庄波','1.在途 2.备件更换 3.服务确认','2020-06-18 15:25:12','2020-06-18 16:00:52'),(8,20,15,19,'刘俊','1.更换备件 2.服务确认 3.在途','2020-06-18 16:11:01','2020-06-18 16:11:53'),(9,5,15,19,'刘俊','1.调整上层主机业务','2020-06-18 16:34:23','2020-06-18 23:45:06'),(11,2,16,23,'马思聪','1.设备巡检','2020-06-18 23:44:38','2020-06-18 23:44:38'),(12,4,17,31,'庄波','1.在途\n2.备件更换\n3.服务确认','2020-06-20 14:22:41','2020-06-20 14:22:41'),(14,10,19,9,'马思聪','1.完成开局','2020-06-25 15:22:39','2020-06-25 15:22:39'),(15,2,21,35,'石岩','测试Redis缓存效果','2020-06-27 23:28:46','2020-06-27 23:28:46'),(16,222,28,36,'庄波','2222','2020-06-28 00:25:02','2020-06-28 00:25:02'),(17,3,35,38,'庄波','333','2020-06-28 01:41:52','2020-06-28 01:41:52'),(18,22,33,38,'庄波','22','2020-06-28 01:42:04','2020-06-28 01:42:04');

/*Table structure for table `work_orders` */

DROP TABLE IF EXISTS `work_orders`;

CREATE TABLE `work_orders` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `status` varchar(5) DEFAULT NULL COMMENT '工单状态',
  `name` varchar(50) DEFAULT NULL COMMENT '工单名称',
  `gdType` varchar(10) DEFAULT NULL COMMENT '工单类型',
  `principalUser` varchar(10) NOT NULL COMMENT '责任人',
  `relatedProject` varchar(50) NOT NULL COMMENT '关联项目',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='工单管理';

/*Data for the table `work_orders` */

insert  into `work_orders`(`id`,`status`,`name`,`gdType`,`principalUser`,`relatedProject`,`createdTime`,`modifiedTime`) values (3,'开启','江苏省南京邮电大学高性能计算数据调用资源池项目（借转销）###设备维护','维护','李文启','江苏省南京邮电大学高性能计算数据调用资源池项目（借转销）','2020-06-12 21:00:00','2020-06-12 21:00:00'),(7,'关闭','江苏省南通市公安局存储扩容项目###更换磁盘','维护','庄波','江苏省南通市公安局存储扩容项目','2020-06-17 02:05:27','2020-06-17 00:29:25'),(8,'关闭','江苏省南京市28所某数据中心存储项目（2分中心试点）###常规维护','维护','李文启','江苏省南京市28所某数据中心存储项目（2分中心试点）','2020-06-17 02:05:20','2020-06-17 00:30:16'),(9,'关闭','杭州交警总队测试项目###开局','开局','石岩','杭州交警总队测试项目','2020-06-17 02:08:50','2020-06-17 00:31:05'),(10,'开启','江苏省南通市公安局存储扩容项目###常规维护','维护','庄波','江苏省南通市公安局存储扩容项目','2020-06-17 02:06:24','2020-06-17 01:37:44'),(12,'关闭','江苏省南京市熊猫电子办公平台存储采购项目###软件升级','升级','万玉林','江苏省南京市熊猫电子办公平台存储采购项目','2020-06-17 08:55:08','2020-06-17 08:54:56'),(13,'开启','江苏省省委省政府机房建设项目###测试','测试','李运','江苏省省委省政府机房建设项目','2020-06-17 17:58:55','2020-06-17 08:56:12'),(15,'关闭','江苏省无锡江南计算技术研究所存储项目###业务调整','维护','刘俊','江苏省无锡江南计算技术研究所存储项目','2020-06-18 23:37:19','2020-06-18 16:06:07'),(16,'开启','杭州交警总队测试项目###常规维护','维护','马思聪','杭州交警总队测试项目','2020-06-18 23:37:49','2020-06-18 23:37:49'),(17,'关闭','江苏省省委省政府机房建设项目###备件更换','维护','庄波','江苏省省委省政府机房建设项目','2020-06-20 14:50:32','2020-06-20 14:19:55'),(18,'关闭','测试项目111###软件升级','维护','庄波','测试项目111','2020-06-25 15:20:05','2020-06-22 22:22:03'),(19,'开启','江苏省南京市28所某数据中心存储项目（2分中心试点）###设备开局','开局','马思聪','江苏省南京市28所某数据中心存储项目（2分中心试点）','2020-06-25 20:45:19','2020-06-25 15:22:10'),(36,'关闭','TestRedis###666','666','庄波','TestRedis','2020-06-28 01:44:36','2020-06-28 01:41:41'),(37,'开启','TestRedis###222','222','庄波','TestRedis','2020-06-28 01:45:12','2020-06-28 01:45:12');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

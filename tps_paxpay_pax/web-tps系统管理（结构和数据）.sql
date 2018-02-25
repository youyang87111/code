/*
Navicat MySQL Data Transfer

Source Server         : szys_测试
Source Server Version : 50709
Source Host           : 192.168.13.222:3306
Source Database       : paxpaydb_szys

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2017-11-23 17:47:08
*/

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `t_b_language`;
CREATE TABLE `t_b_language` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ABBRNAME` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '英文简称',
  `NAME` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '语言名称',
  `DISNAME` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '展示名称',
  `PKGPATH` varchar(256) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '语言包路径',
  `PKGVERSION` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '语言包版本',
  `URL` varchar(256) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '页面访问路径',
  `TYPE` char(1) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '语言包适用类型',
  `STATUS` char(1) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '状态',
  `BUILDDATETIME` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建日期时间',
  `NOTES` varchar(200) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
  `VERSION` int(11) NOT NULL DEFAULT '0' COMMENT '乐观锁',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ABBRNAME` (`ABBRNAME`,`VERSION`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_b_language
-- ----------------------------
INSERT INTO `t_b_language` VALUES ('1', 'en_US', '英文', 'English', 'i18n/i18n_en.properties', '1.0', '/tps/', '0', '1', '20170309181220', '测试', '0');
INSERT INTO `t_b_language` VALUES ('2', 'zh_CN', '中文', '简体中文', 'i18n/i18n_zh.properties', '1.0', '/tps/', '0', '1', '20170309181220', '测试', '0');

-- ----------------------------
-- Table structure for t_si_menu
-- ----------------------------

DROP TABLE IF EXISTS `t_si_menu`;
CREATE TABLE `t_si_menu` (
  `id` int(10) NOT NULL COMMENT '菜单id',
  `site_id` int(10) NOT NULL COMMENT '站点id',
  `name` varchar(100) COLLATE gbk_bin NOT NULL COMMENT '菜单名称',
  `url` varchar(100) COLLATE gbk_bin DEFAULT NULL COMMENT '菜单路径',
  `orderno` int(10) NOT NULL COMMENT '菜单序号',
  `img` varchar(100) COLLATE gbk_bin DEFAULT NULL COMMENT '菜单图标路径',
  `pid` varchar(10) COLLATE gbk_bin DEFAULT NULL COMMENT '上级菜单id',
  `buildoper` varchar(20) COLLATE gbk_bin NOT NULL COMMENT '创建者',
  `builddatetime` varchar(20) COLLATE gbk_bin NOT NULL COMMENT '创建时间',
  `modifyoper` varchar(20) COLLATE gbk_bin DEFAULT NULL COMMENT '修改者',
  `modifydatetime` varchar(20) COLLATE gbk_bin DEFAULT NULL COMMENT '修改时间',
  `notes` varchar(200) COLLATE gbk_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COLLATE=gbk_bin;

-- ----------------------------
-- Records of t_si_menu
-- ----------------------------
INSERT INTO `t_si_menu` VALUES ('1', '1', '所有菜单', '', '1', null, null, 'admin', '20170505151329', 'admin', '20170815142950', null);
INSERT INTO `t_si_menu` VALUES ('2', '1', '系统管理', null, '9', null, '1', 'admin', '20170518151826', null, null, null);
INSERT INTO `t_si_menu` VALUES ('3', '1', '用户管理', '/user/enter', '1', 'http2', '2', 'admin', '20170505151329', null, null, null);
INSERT INTO `t_si_menu` VALUES ('4', '1', '角色管理', '/role/enter', '2', '', '2', '', '20170512214206', null, null, '');
INSERT INTO `t_si_menu` VALUES ('5', '1', '权限管理', '/authority/enter', '3', 'http3', '2', 'admin', '20170505151329', null, null, null);
INSERT INTO `t_si_menu` VALUES ('6', '1', '菜单管理', '/menu/enter', '4', '', '2', '', '20170512214321', null, null, '');
INSERT INTO `t_si_menu` VALUES ('7', '1', '功能管理', '/function/enter', '5', '', '2', '', '20170512214334', null, null, '');
INSERT INTO `t_si_menu` VALUES ('8', '1', '报表管理', '', '3', null, '1', 'admin', '20170815171213', 'admin', '20170825094532', null);
INSERT INTO `t_si_menu` VALUES ('13', '1', '站点管理', '/site/enter', '0', '', '2', 'admin', '20170518123716', null, null, '');
INSERT INTO `t_si_menu` VALUES ('55', '1', '角色模板管理', '/rolemodel/enter', '8', '', '2', 'admin', '20170526164755', null, null, '');
INSERT INTO `t_si_menu` VALUES ('56', '1', '资源管理', '', '1', '', '1', 'admin', '20170815151248', null, null, '');
INSERT INTO `t_si_menu` VALUES ('57', '1', '映射管理', '', '2', '', '1', 'admin', '20170815151302', null, null, '');
INSERT INTO `t_si_menu` VALUES ('58', '1', '渠道映射管理', '/branchMap/enter', '1', '', '57', 'admin', '20170815151340', null, null, '');
INSERT INTO `t_si_menu` VALUES ('59', '1', '商户映射管理', '/merMap/enter', '2', '', '57', 'admin', '20170815151401', null, null, '');
INSERT INTO `t_si_menu` VALUES ('60', '1', '终端映射管理', '/termMap/enter', '3', '', '57', 'admin', '20170815151424', null, null, '');
INSERT INTO `t_si_menu` VALUES ('61', '1', '接入商户管理', '/cposMer/enter', '1', null, '56', 'admin', '20170815151610', 'admin', '20170815161301', null);
INSERT INTO `t_si_menu` VALUES ('62', '1', '接入终端管理', '/cposTerm/enter', '2', null, '56', 'admin', '20170815154910', 'admin', '20170815161304', null);
INSERT INTO `t_si_menu` VALUES ('63', '1', '转出商户管理', '/rMer/enter', '3', null, '56', 'admin', '20170815155005', 'admin', '20170816161216', null);
INSERT INTO `t_si_menu` VALUES ('64', '1', '转出终端管理', '/rTerm/enter', '4', null, '56', 'admin', '20170815155201', 'admin', '20170816161219', null);
INSERT INTO `t_si_menu` VALUES ('65', '1', '接入流水报表', '/cposhistrans/enter', '1', null, '8', 'admin', '20170915171212', 'admin', '20170815171747', null);
INSERT INTO `t_si_menu` VALUES ('66', '1', '交易日志报表', '/cposhislog/enter', '2', '', '8', 'admin', '20171012095313', null, null, '');
INSERT INTO `t_si_menu` VALUES ('67', '1', '监控管理', '', '4', '', '1', 'admin', '20170825115754', null, null, '');
INSERT INTO `t_si_menu` VALUES ('68', '1', '接入终端交易监控', '/monitor/enter', '1', '', '67', 'admin', '20170825115818', null, null, '');
INSERT INTO `t_si_menu` VALUES ('69', '1', '日志管理', '/log/enter', '7', null, '2', 'admin', '20170829120801', null, null, null);
INSERT INTO `t_si_menu` VALUES ('70', '1', '交易同步状态', '/realtimenotion/enter', '3', '', '8', 'admin', '20171012105313', null, null, '');

-- ----------------------------
-- Table structure for t_si_function
-- ----------------------------

DROP TABLE IF EXISTS `t_si_function`;
CREATE TABLE `t_si_function` (
  `id` int(10) NOT NULL COMMENT '功能id',
  `site_id` int(10) NOT NULL COMMENT '站点id',
  `name` varchar(100) COLLATE gbk_bin NOT NULL COMMENT '功能名称',
  `url` varchar(100) COLLATE gbk_bin DEFAULT NULL COMMENT '功能路径',
  `img` varchar(100) COLLATE gbk_bin DEFAULT NULL COMMENT '功能图标路径',
  `menu_id` varchar(10) COLLATE gbk_bin NOT NULL COMMENT '菜单id',
  `buildoper` varchar(20) COLLATE gbk_bin NOT NULL COMMENT '创建者',
  `builddatetime` varchar(20) COLLATE gbk_bin NOT NULL COMMENT '创建时间',
  `modifyoper` varchar(20) COLLATE gbk_bin DEFAULT NULL COMMENT '修改者',
  `modifydatetime` varchar(20) COLLATE gbk_bin DEFAULT NULL COMMENT '修改时间',
  `notes` varchar(200) COLLATE gbk_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COLLATE=gbk_bin;

-- ----------------------------
-- Records of t_si_function
-- ----------------------------
INSERT INTO `t_si_function` VALUES ('1', '1', '修改用户', null, null, '3', '', '20170509114617', 'admin', '20170724161532', null);
INSERT INTO `t_si_function` VALUES ('2', '1', '修改角色', null, null, '4', ' ', '20170510185901', null, null, null);
INSERT INTO `t_si_function` VALUES ('3', '1', '修改权限', null, null, '5', '', '20170509162853', null, null, null);
INSERT INTO `t_si_function` VALUES ('4', '1', '修改菜单', '', '', '6', '', '20170510105141', null, null, '');
INSERT INTO `t_si_function` VALUES ('5', '1', '修改功能', '', '', '7', '', '20170510115037', null, null, '');
INSERT INTO `t_si_function` VALUES ('6', '1', '用户页面查看', '', '', '3', 'admin', '20170517162415', null, null, '');
INSERT INTO `t_si_function` VALUES ('7', '1', '新增用户', '', '', '3', 'admin', '20170517162532', null, null, '');
INSERT INTO `t_si_function` VALUES ('8', '1', '批量删除用户', '', '', '3', 'admin', '20170517162802', null, null, '');
INSERT INTO `t_si_function` VALUES ('9', '1', '查看用户信息', '', '', '3', 'admin', '20170517162817', null, null, '');
INSERT INTO `t_si_function` VALUES ('10', '1', '删除用户', '', '', '3', 'admin', '20170517162831', null, null, '');
INSERT INTO `t_si_function` VALUES ('11', '1', '查看角色页面', '', '', '4', 'admin', '20170517164121', null, null, '');
INSERT INTO `t_si_function` VALUES ('12', '1', '新增角色', '', '', '4', 'admin', '20170517164140', null, null, '');
INSERT INTO `t_si_function` VALUES ('13', '1', '批量删除角色', '', '', '4', 'admin', '20170517164215', null, null, '');
INSERT INTO `t_si_function` VALUES ('14', '1', '查看角色信息', '', '', '4', 'admin', '20170517164227', null, null, '');
INSERT INTO `t_si_function` VALUES ('15', '1', '删除角色', '', '', '4', 'admin', '20170517164242', null, null, '');
INSERT INTO `t_si_function` VALUES ('16', '1', '分配角色权限', '', '', '4', 'admin', '20170517164316', null, null, '');
INSERT INTO `t_si_function` VALUES ('17', '1', '查看权限页面', '', '', '5', 'admin', '20170517164342', null, null, '');
INSERT INTO `t_si_function` VALUES ('18', '1', '新增权限', '', '', '5', 'admin', '20170517164355', null, null, '');
INSERT INTO `t_si_function` VALUES ('19', '1', '批量删除权限', '', '', '5', 'admin', '20170517164408', null, null, '');
INSERT INTO `t_si_function` VALUES ('20', '1', '查看权限信息', '', '', '5', 'admin', '20170517164520', null, null, '');
INSERT INTO `t_si_function` VALUES ('21', '1', '分配权限功能', '', '', '5', 'admin', '20170517164627', null, null, '');
INSERT INTO `t_si_function` VALUES ('22', '1', '删除权限', '', '', '5', 'admin', '20170517164640', null, null, '');
INSERT INTO `t_si_function` VALUES ('23', '1', '查看菜单页面', '', '', '6', 'admin', '20170517164711', null, null, '');
INSERT INTO `t_si_function` VALUES ('24', '1', '查看菜单信息', '', '', '6', 'admin', '20170517164750', null, null, '');
INSERT INTO `t_si_function` VALUES ('25', '1', '新增菜单', '', '', '6', 'admin', '20170517164802', null, null, '');
INSERT INTO `t_si_function` VALUES ('26', '1', '删除菜单', '', '', '6', 'admin', '20170517164817', null, null, '');
INSERT INTO `t_si_function` VALUES ('27', '1', '查看功能页面', '', '', '7', 'admin', '20170517164856', null, null, '');
INSERT INTO `t_si_function` VALUES ('28', '1', '新增功能', '', '', '7', 'admin', '20170517164906', null, null, '');
INSERT INTO `t_si_function` VALUES ('29', '1', '批量删除功能', '', '', '7', 'admin', '20170517164916', null, null, '');
INSERT INTO `t_si_function` VALUES ('30', '1', '查看功能信息', '', '', '7', 'admin', '20170517164928', null, null, '');
INSERT INTO `t_si_function` VALUES ('31', '1', '删除功能', '', '', '7', 'admin', '20170517164938', null, null, '');
INSERT INTO `t_si_function` VALUES ('32', '1', '获取功能列表', '', '', '7', 'admin', '20170517164955', null, null, '');
INSERT INTO `t_si_function` VALUES ('33', '1', '获取菜单列表', '', '', '6', 'admin', '20170517165011', null, null, '');
INSERT INTO `t_si_function` VALUES ('34', '1', '获取权限列表', '', '', '5', 'admin', '20170517165024', null, null, '');
INSERT INTO `t_si_function` VALUES ('35', '1', '获取角色列表', '', '', '4', 'admin', '20170517165037', null, null, '');
INSERT INTO `t_si_function` VALUES ('36', '1', '获取用户列表', '', '', '3', 'admin', '20170517165050', null, null, '');
INSERT INTO `t_si_function` VALUES ('37', '1', '修改站点', '', '', '13', 'admin', '20170517165328', null, null, '');
INSERT INTO `t_si_function` VALUES ('38', '1', '新增站点', '', '', '13', 'admin', '20170517173307', null, null, '');
INSERT INTO `t_si_function` VALUES ('39', '1', '查看站点页面', '', '', '13', 'admin', '20170517180333', null, null, '');
INSERT INTO `t_si_function` VALUES ('41', '1', '查看站点信息', '', '', '13', 'admin', '20170517180436', null, null, '');
INSERT INTO `t_si_function` VALUES ('42', '1', '冻结站点', '', '', '13', 'admin', '20170517180501', null, null, '');
INSERT INTO `t_si_function` VALUES ('43', '1', '解冻站点', '', '', '13', 'admin', '20170517180512', null, null, '');
INSERT INTO `t_si_function` VALUES ('45', '1', '冻结用户', '', '', '3', 'admin', '20170517180913', null, null, '');
INSERT INTO `t_si_function` VALUES ('46', '1', '解冻用户', '', '', '3', 'admin', '20170517180923', null, null, '');
INSERT INTO `t_si_function` VALUES ('47', '1', '分配用户角色', '', '', '3', 'admin', '20170517210108', null, null, '');
INSERT INTO `t_si_function` VALUES ('48', '1', '获取站点列表', '', '', '13', 'admin', '20170518091945', null, null, '');
INSERT INTO `t_si_function` VALUES ('170', '1', '查看角色模板管理页面', '', '', '55', 'admin', '20170526164833', null, null, '');
INSERT INTO `t_si_function` VALUES ('171', '1', '新增角色模板', '', '', '55', 'admin', '20170526170935', null, null, '');
INSERT INTO `t_si_function` VALUES ('172', '1', '获取角色模板列表', '', '', '55', 'admin', '20170526170956', null, null, '');
INSERT INTO `t_si_function` VALUES ('173', '1', '查看角色模板信息', '', '', '55', 'admin', '20170526171009', null, null, '');
INSERT INTO `t_si_function` VALUES ('174', '1', '修改角色模板', '', '', '55', 'admin', '20170526171037', null, null, '');
INSERT INTO `t_si_function` VALUES ('175', '1', '分配角色模板权限', '', '', '55', 'admin', '20170526171057', null, null, '');
INSERT INTO `t_si_function` VALUES ('194', '1', '修改密码', '', '', '3', 'admin', '20170531094607', null, null, '');
INSERT INTO `t_si_function` VALUES ('195', '1', '重置密码', '', '', '3', 'admin', '20170531094624', null, null, '');
INSERT INTO `t_si_function` VALUES ('196', '1', '查看渠道映射页面', '', '', '58', 'admin', '20170815151553', null, null, '');
INSERT INTO `t_si_function` VALUES ('197', '1', '查看商户映射页面', '', '', '59', 'admin', '20170815151749', null, null, '');
INSERT INTO `t_si_function` VALUES ('198', '1', '查看终端映射页面', '', '', '60', 'admin', '20170815151832', null, null, '');
INSERT INTO `t_si_function` VALUES ('199', '1', '查看接入商户页面', '', '', '61', 'admin', '20170815160329', 'admin', '20170815160421', '');
INSERT INTO `t_si_function` VALUES ('200', '1', '查看接入终端页面', '', '', '62', 'admin', '20170815160345', 'admin', '20170815160415', '');
INSERT INTO `t_si_function` VALUES ('201', '1', '查看转出商户页面', '', '', '63', 'admin', '20170815160400', 'admin', '20170815160410', '');
INSERT INTO `t_si_function` VALUES ('202', '1', '查看转出终端页面', '', '', '64', 'admin', '20170815160439', null, null, '');
INSERT INTO `t_si_function` VALUES ('203', '1', '查看接入流水报表页面', '', '', '65', 'admin', '20170815171556', null, null, '');
INSERT INTO `t_si_function` VALUES ('205', '1', '获取接入商户列表', '', '', '61', 'admin', '20170816093757', null, null, '');
INSERT INTO `t_si_function` VALUES ('206', '1', '获取接入终端列表', '', '', '62', 'admin', '20170816094040', null, null, '');
INSERT INTO `t_si_function` VALUES ('207', '1', '获取转出商户列表', '', '', '63', 'admin', '20170816143146', null, null, '');
INSERT INTO `t_si_function` VALUES ('208', '1', '获取转出终端列表', '', '', '64', 'admin', '20170816143202', null, null, '');
INSERT INTO `t_si_function` VALUES ('209', '1', '查看接入商户详情页面', '', '', '61', 'admin', '20170817113147', null, null, '');
INSERT INTO `t_si_function` VALUES ('210', '1', '查看接入终端详情页面', '', '', '62', 'admin', '20170817125657', null, null, '');
INSERT INTO `t_si_function` VALUES ('211', '1', '查看转出商户详情页面', '', '', '63', 'admin', '20170817125721', null, null, '');
INSERT INTO `t_si_function` VALUES ('212', '1', '查看转出终端详情页面', '', '', '64', 'admin', '20170817125741', null, null, '');
INSERT INTO `t_si_function` VALUES ('213', '1', '新增接入商户', '', '', '61', 'admin', '20170817125955', null, null, '');
INSERT INTO `t_si_function` VALUES ('214', '1', '新增接入终端', '', '', '62', 'admin', '20170817130013', null, null, '');
INSERT INTO `t_si_function` VALUES ('215', '1', '新增转出商户', '', '', '63', 'admin', '20170817130030', null, null, '');
INSERT INTO `t_si_function` VALUES ('216', '1', '新增转出终端', '', '', '64', 'admin', '20170817130044', null, null, '');
INSERT INTO `t_si_function` VALUES ('217', '1', '修改接入商户', '', '', '61', 'admin', '20170817130400', null, null, '');
INSERT INTO `t_si_function` VALUES ('219', '1', '修改转出商户', '', '', '63', 'admin', '20170817130432', null, null, '');
INSERT INTO `t_si_function` VALUES ('221', '1', '冻结接入商户', '', '', '61', 'admin', '20170817133217', null, null, '');
INSERT INTO `t_si_function` VALUES ('222', '1', '冻结接入终端', '', '', '62', 'admin', '20170817133232', null, null, '');
INSERT INTO `t_si_function` VALUES ('223', '1', '冻结转出商户', '', '', '63', 'admin', '20170817133244', null, null, '');
INSERT INTO `t_si_function` VALUES ('224', '1', '冻结转出终端', '', '', '64', 'admin', '20170817133301', null, null, '');
INSERT INTO `t_si_function` VALUES ('225', '1', '解冻接入商户', '', '', '61', 'admin', '20170817133324', null, null, '');
INSERT INTO `t_si_function` VALUES ('226', '1', '解冻接入终端', '', '', '62', 'admin', '20170817133337', null, null, '');
INSERT INTO `t_si_function` VALUES ('227', '1', '解冻转出商户', '', '', '63', 'admin', '20170817133354', null, null, '');
INSERT INTO `t_si_function` VALUES ('228', '1', '解冻转出终端', '', '', '64', 'admin', '20170817133406', null, null, '');
INSERT INTO `t_si_function` VALUES ('229', '1', '审核接入商户', '', '', '61', 'admin', '20170817133927', null, null, '');
INSERT INTO `t_si_function` VALUES ('230', '1', '审核接入终端', '', '', '62', 'admin', '20170817133941', null, null, '');
INSERT INTO `t_si_function` VALUES ('231', '1', '审核转出商户', '', '', '63', 'admin', '20170817133953', null, null, '');
INSERT INTO `t_si_function` VALUES ('232', '1', '审核转出终端', '', '', '64', 'admin', '20170817134009', null, null, '');
INSERT INTO `t_si_function` VALUES ('234', '1', '删除接入商户', '', '', '61', 'admin', '20170817140157', null, null, '');
INSERT INTO `t_si_function` VALUES ('235', '1', '删除接入终端', '', '', '62', 'admin', '20170817140215', null, null, '');
INSERT INTO `t_si_function` VALUES ('236', '1', '删除转出商户', '', '', '63', 'admin', '20170817140228', null, null, '');
INSERT INTO `t_si_function` VALUES ('237', '1', '删除转出终端', '', '', '64', 'admin', '20170817140241', null, null, '');
INSERT INTO `t_si_function` VALUES ('238', '1', '导入接入商户', '', '', '61', 'admin', '20170817141617', null, null, '');
INSERT INTO `t_si_function` VALUES ('239', '1', '导入接入终端', '', '', '62', 'admin', '20170817141629', null, null, '');
INSERT INTO `t_si_function` VALUES ('240', '1', '导入转出商户', '', '', '63', 'admin', '20170817141641', null, null, '');
INSERT INTO `t_si_function` VALUES ('241', '1', '导入转出终端', '', '', '64', 'admin', '20170817141653', null, null, '');
INSERT INTO `t_si_function` VALUES ('242', '1', '查看接入流水报表列表页面', '', '', '65', 'admin', '20170817165226', 'admin', '20170911175948', '');
INSERT INTO `t_si_function` VALUES ('244', '1', '导出流水报表', '', '', '65', 'admin', '20170822162819', 'admin', '20170822163006', '');
INSERT INTO `t_si_function` VALUES ('246', '1', '生成密钥', '', '', '62', 'admin', '20170823112143', null, null, '');
INSERT INTO `t_si_function` VALUES ('247', '1', '导出密钥', '', '', '62', 'admin', '20170823112208', null, null, '');
INSERT INTO `t_si_function` VALUES ('248', '1', '重置密钥', '', '', '62', 'admin', '20170823112257', null, null, '');
INSERT INTO `t_si_function` VALUES ('249', '1', '转换第三方密钥', '', '', '64', 'admin', '20170823112320', null, null, '');
INSERT INTO `t_si_function` VALUES ('250', '1', '导入密钥', '', '', '64', 'admin', '20170823112331', null, null, '');
INSERT INTO `t_si_function` VALUES ('251', '1', '新增渠道映射', '', '', '58', 'admin', '20170825095616', 'admin', '20170825100406', '');
INSERT INTO `t_si_function` VALUES ('252', '1', '删除渠道映射', '', '', '58', 'admin', '20170825095819', 'admin', '20170825100413', '');
INSERT INTO `t_si_function` VALUES ('253', '1', '批量删除渠道映射', '', '', '58', 'admin', '20170825095841', 'admin', '20170825100420', '');
INSERT INTO `t_si_function` VALUES ('254', '1', '查看渠道映射详情', '', '', '58', 'admin', '20170825095859', 'admin', '20170825100428', '');
INSERT INTO `t_si_function` VALUES ('255', '1', '修改渠道映射', '', '', '58', 'admin', '20170825095916', 'admin', '20170825100441', '');
INSERT INTO `t_si_function` VALUES ('256', '1', '审核终端映射', '', '', '58', 'admin', '20170825095947', 'admin', '20170825100448', '');
INSERT INTO `t_si_function` VALUES ('257', '1', '冻结渠道映射', '', '', '58', 'admin', '20170825100000', 'admin', '20170825100457', '');
INSERT INTO `t_si_function` VALUES ('258', '1', '解冻渠道映射', '', '', '58', 'admin', '20170825100029', 'admin', '20170825100503', '');
INSERT INTO `t_si_function` VALUES ('259', '1', '查看交易监控页面', '', '', '68', 'admin', '20170825120235', null, null, '');
INSERT INTO `t_si_function` VALUES ('260', '1', '新增商户映射', '', '', '59', 'admin', '20170825165222', null, null, '');
INSERT INTO `t_si_function` VALUES ('261', '1', '删除商户映射', '', '', '59', 'admin', '20170825165251', null, null, '');
INSERT INTO `t_si_function` VALUES ('262', '1', '批量删除商户映射', '', '', '59', 'admin', '20170825165310', null, null, '');
INSERT INTO `t_si_function` VALUES ('263', '1', '查看商户映射详情', '', '', '59', 'admin', '20170825165326', null, null, '');
INSERT INTO `t_si_function` VALUES ('264', '1', '修改商户映射', '', '', '59', 'admin', '20170825165415', null, null, '');
INSERT INTO `t_si_function` VALUES ('265', '1', '审核商户映射', '', '', '59', 'admin', '20170825165436', null, null, '');
INSERT INTO `t_si_function` VALUES ('266', '1', '冻结商户映射', '', '', '59', 'admin', '20170825165512', null, null, '');
INSERT INTO `t_si_function` VALUES ('267', '1', '解冻商户映射', '', '', '59', 'admin', '20170825165539', null, null, '');
INSERT INTO `t_si_function` VALUES ('268', '1', '新增终端映射', '', '', '60', 'admin', '20170825170606', null, null, '');
INSERT INTO `t_si_function` VALUES ('269', '1', '删除终端映射', '', '', '60', 'admin', '20170825170622', null, null, '');
INSERT INTO `t_si_function` VALUES ('270', '1', '批量删除终端映射', '', '', '60', 'admin', '20170825170646', null, null, '');
INSERT INTO `t_si_function` VALUES ('271', '1', '获取渠道映射列表', '', '', '58', 'admin', '20170825174710', null, null, '');
INSERT INTO `t_si_function` VALUES ('272', '1', '获取商户映射列表', '', '', '59', 'admin', '20170825174725', null, null, '');
INSERT INTO `t_si_function` VALUES ('273', '1', '获取终端映射列表', '', '', '60', 'admin', '20170825174742', null, null, '');
INSERT INTO `t_si_function` VALUES ('274', '1', '审核终端映射', '', '', '60', 'admin', '20170825180728', null, null, '');
INSERT INTO `t_si_function` VALUES ('275', '1', '冻结终端映射', '', '', '60', 'admin', '20170825180743', null, null, '');
INSERT INTO `t_si_function` VALUES ('276', '1', '解冻终端映射', '', '', '60', 'admin', '20170825180800', null, null, '');
INSERT INTO `t_si_function` VALUES ('277', '1', '查看终端映射详情', '', '', '60', 'admin', '20170825181054', null, null, '');
INSERT INTO `t_si_function` VALUES ('278', '1', '查看日志信息', '', '', '69', 'admin', '20170829141531', null, null, '');
INSERT INTO `t_si_function` VALUES ('279', '1', '获取日志列表', '', '', '69', 'admin', '20170829141607', 'admin', '20170911171952', '');
INSERT INTO `t_si_function` VALUES ('281', '1', '查看日志页面', '', '', '69', 'admin', '20170829153053', 'admin', '20170911172039', '');
INSERT INTO `t_si_function` VALUES ('282', '1', '批量删除接入商户', '', '', '61', 'admin', '20170912103424', null, null, '');
INSERT INTO `t_si_function` VALUES ('283', '1', '批量删除接入终端', '', '', '62', 'admin', '20170912103441', null, null, '');
INSERT INTO `t_si_function` VALUES ('284', '1', '批量删除转出商户', '', '', '63', 'admin', '20170912103500', null, null, '');
INSERT INTO `t_si_function` VALUES ('285', '1', '批量删除转出终端', '', '', '64', 'admin', '20170912103517', null, null, '');
INSERT INTO `t_si_function` VALUES ('286', '1', '查看交易日志页面', '', '', '66', 'admin', '20171012100009', null, null, '');
INSERT INTO `t_si_function` VALUES ('287', '1', '获取交易日志列表', '', '', '66', 'admin', '20171012100119', null, null, '');
INSERT INTO `t_si_function` VALUES ('288', '1', '查看同步状态页面', '', '', '70', 'admin', '20171012102809', null, null, '');
INSERT INTO `t_si_function` VALUES ('289', '1', '获取同步状态列表', '', '', '70', 'admin', '20171012102813', null, null, '');
INSERT INTO `t_si_function` VALUES ('290', '1', '导出交易日志', '', '', '66', 'admin', '20171013134730', null, null, '');
INSERT INTO `t_si_function` VALUES ('291', '1', '重发', '', '', '70', 'admin', '20171013134840', null, null, '');



-- ----------------------------
-- Table structure for t_si_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_si_authority`;
CREATE TABLE `t_si_authority` (
  `id` int(10) NOT NULL COMMENT '权限id',
  `site_id` int(10) NOT NULL COMMENT '站点id',
  `name` varchar(100) COLLATE gbk_bin NOT NULL COMMENT '权限名称',
  `tag` varchar(100) COLLATE gbk_bin NOT NULL COMMENT '权限标识',
  `buildoper` varchar(20) COLLATE gbk_bin NOT NULL COMMENT '创建者',
  `builddatetime` varchar(20) COLLATE gbk_bin NOT NULL COMMENT '创建时间',
  `modifyoper` varchar(20) COLLATE gbk_bin DEFAULT NULL COMMENT '修改者',
  `modifydatetime` varchar(20) COLLATE gbk_bin DEFAULT NULL COMMENT '修改时间',
  `notes` varchar(200) COLLATE gbk_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COLLATE=gbk_bin;

-- ----------------------------
-- Records of t_si_authority
-- ----------------------------
INSERT INTO `t_si_authority` VALUES ('1', '1', '修改用户', 'user:update', '', '20170510142718', null, null, '');
INSERT INTO `t_si_authority` VALUES ('2', '1', '修改角色', 'role:update', '', '20170510154052', null, null, '');
INSERT INTO `t_si_authority` VALUES ('3', '1', '修改权限', 'auth:update', '', '20170511094413', null, null, '');
INSERT INTO `t_si_authority` VALUES ('4', '1', '修改菜单', 'menu:update', ' ', '20170512111329', null, null, null);
INSERT INTO `t_si_authority` VALUES ('5', '1', '修改功能', 'func:update', ' ', '20170512111359', null, null, null);
INSERT INTO `t_si_authority` VALUES ('6', '1', '修改站点', 'site:update', 'admin', '20170517165353', null, null, '');
INSERT INTO `t_si_authority` VALUES ('7', '1', '新增站点', 'site:save', 'admin', '20170517173330', null, null, '');
INSERT INTO `t_si_authority` VALUES ('8', '1', '用户页面查看', 'user:enter', '', '20170517174521', null, null, '');
INSERT INTO `t_si_authority` VALUES ('9', '1', '新增用户', 'user:save', '', '20170517174557', null, null, '');
INSERT INTO `t_si_authority` VALUES ('10', '1', '批量删除用户', 'user:delete', '', '20170517174650', null, null, '');
INSERT INTO `t_si_authority` VALUES ('11', '1', '查看用户信息', 'user:detail', '', '20170517174830', null, null, '');
INSERT INTO `t_si_authority` VALUES ('12', '1', '删除用户', 'user:delete', '', '20170517174858', null, null, '');
INSERT INTO `t_si_authority` VALUES ('13', '1', '获取用户列表', 'user:list', '', '20170517174958', null, null, '');
INSERT INTO `t_si_authority` VALUES ('14', '1', '查看角色页面', 'role:enter', '', '20170517175058', null, null, '');
INSERT INTO `t_si_authority` VALUES ('15', '1', '新增角色', 'role:save', '', '20170517175124', null, null, '');
INSERT INTO `t_si_authority` VALUES ('16', '1', '批量删除角色', 'role:delete', '', '20170517175147', null, null, '');
INSERT INTO `t_si_authority` VALUES ('17', '1', '查看角色信息', 'role:detail', '', '20170517175209', null, null, '');
INSERT INTO `t_si_authority` VALUES ('18', '1', '删除角色', 'role:delete', '', '20170517175231', null, null, '');
INSERT INTO `t_si_authority` VALUES ('19', '1', '分配角色权限', 'role:grantAuths', '', '20170517175328', null, null, '');
INSERT INTO `t_si_authority` VALUES ('20', '1', '获取角色列表', 'role:list', '', '20170517175407', null, null, '');
INSERT INTO `t_si_authority` VALUES ('21', '1', '查看权限页面', 'auth:enter', '', '20170517175505', null, null, '');
INSERT INTO `t_si_authority` VALUES ('22', '1', '新增权限', 'auth:save', '', '20170517175525', null, null, '');
INSERT INTO `t_si_authority` VALUES ('23', '1', '批量删除权限', 'auth:delete', '', '20170517175548', null, null, '');
INSERT INTO `t_si_authority` VALUES ('24', '1', '查看权限信息', 'auth:detail', '', '20170517175611', null, null, '');
INSERT INTO `t_si_authority` VALUES ('25', '1', '分配权限功能', 'auth:grantFuncs', '', '20170517175636', null, null, '');
INSERT INTO `t_si_authority` VALUES ('26', '1', '删除权限', 'auth:delete', '', '20170517175654', null, null, '');
INSERT INTO `t_si_authority` VALUES ('27', '1', '获取权限列表', 'auth:list', '', '20170517175712', null, null, '');
INSERT INTO `t_si_authority` VALUES ('28', '1', '查看菜单页面', 'menu:enter', '', '20170517175734', null, null, '');
INSERT INTO `t_si_authority` VALUES ('29', '1', '查看菜单信息', 'menu:detail', '', '20170517175817', null, null, '');
INSERT INTO `t_si_authority` VALUES ('30', '1', '新增菜单', 'menu:save', '', '20170517175835', null, null, '');
INSERT INTO `t_si_authority` VALUES ('31', '1', '删除菜单', 'menu:delete', '', '20170517175903', null, null, '');
INSERT INTO `t_si_authority` VALUES ('32', '1', '获取菜单列表', 'menu:list', '', '20170517175921', null, null, '');
INSERT INTO `t_si_authority` VALUES ('33', '1', '查看功能页面', 'func:enter', '', '20170517180002', null, null, '');
INSERT INTO `t_si_authority` VALUES ('34', '1', '新增功能', 'func:save', '', '20170517180037', null, null, '');
INSERT INTO `t_si_authority` VALUES ('35', '1', '批量删除功能', 'func:delete', '', '20170517180057', null, null, '');
INSERT INTO `t_si_authority` VALUES ('36', '1', '查看功能信息', 'func:detail', '', '20170517180120', null, null, '');
INSERT INTO `t_si_authority` VALUES ('37', '1', '删除功能', 'func:delete', '', '20170517180230', null, null, '');
INSERT INTO `t_si_authority` VALUES ('38', '1', '获取功能列表', 'func:list', '', '20170517180245', null, null, '');
INSERT INTO `t_si_authority` VALUES ('39', '1', '查看站点页面', 'site:enter', 'admin', '20170517180606', null, null, '');
INSERT INTO `t_si_authority` VALUES ('41', '1', '查看站点信息', 'site:detail', 'admin', '20170517180652', null, null, '');
INSERT INTO `t_si_authority` VALUES ('42', '1', '冻结站点', 'site:frozen', 'admin', '20170517180736', null, null, '');
INSERT INTO `t_si_authority` VALUES ('43', '1', '解冻站点', 'site:unfrozen', 'admin', '20170517180803', null, null, '');
INSERT INTO `t_si_authority` VALUES ('45', '1', '冻结用户', 'user:frozen', 'admin', '20170517180939', null, null, '');
INSERT INTO `t_si_authority` VALUES ('46', '1', '解冻用户', 'user:unfrozen', 'admin', '20170517181026', null, null, '');
INSERT INTO `t_si_authority` VALUES ('47', '1', '分配用户角色', 'user:grantRoles', 'admin', '20170517210131', null, null, '');
INSERT INTO `t_si_authority` VALUES ('48', '1', '获取站点列表', 'site:list', 'admin', '20170518092025', null, null, '');
INSERT INTO `t_si_authority` VALUES ('164', '1', '查看角色模板管理页面', 'rolemodel:enter', 'admin', '20170526164900', null, null, '');
INSERT INTO `t_si_authority` VALUES ('165', '1', '新增角色模板', 'rolemodel:save', 'admin', '20170526171121', null, null, '');
INSERT INTO `t_si_authority` VALUES ('166', '1', '获取角色模板列表', 'rolemodel:list', 'admin', '20170526171145', null, null, '');
INSERT INTO `t_si_authority` VALUES ('167', '1', '查看角色模板信息', 'rolemodel:detail', 'admin', '20170526171211', null, null, '');
INSERT INTO `t_si_authority` VALUES ('168', '1', '修改角色模板', 'rolemodel:update', 'admin', '20170526171230', null, null, '');
INSERT INTO `t_si_authority` VALUES ('169', '1', '分配角色模板权限', 'rolemodel:grantAuths', 'admin', '20170526171309', null, null, '');
INSERT INTO `t_si_authority` VALUES ('194', '1', '修改密码', 'user:updatePwd', 'admin', '20170531094703', null, null, '');
INSERT INTO `t_si_authority` VALUES ('195', '1', '重置密码', 'user:resetPwd', 'admin', '20170531094748', null, null, '');
INSERT INTO `t_si_authority` VALUES ('196', '1', '查看渠道映射页面', 'branchMap:enter', 'admin', '20170815151640', null, null, '');
INSERT INTO `t_si_authority` VALUES ('197', '1', '查看商户映射页面', 'merMap:enter', 'admin', '20170815151810', null, null, '');
INSERT INTO `t_si_authority` VALUES ('198', '1', '查看终端映射页面', 'termMap:enter', 'admin', '20170815151846', null, null, '');
INSERT INTO `t_si_authority` VALUES ('199', '1', '查看接入商户页面', 'cposMer:enter', 'admin', '20170815160535', 'admin', '20170815161206', '');
INSERT INTO `t_si_authority` VALUES ('200', '1', '查看接入终端页面', 'cposTerm:enter', 'admin', '20170815160606', 'admin', '20170815161221', '');
INSERT INTO `t_si_authority` VALUES ('201', '1', '查看转出商户页面', 'rMer:enter', 'admin', '20170815160645', 'admin', '20170815161145', '');
INSERT INTO `t_si_authority` VALUES ('202', '1', '查看转出终端页面', 'rTerm:enter', 'admin', '20170815160712', 'admin', '20170815161134', '');
INSERT INTO `t_si_authority` VALUES ('203', '1', '查看接入流水报表页面', 'cposhistrans:enter', 'admin', '20170815171657', null, null, '');
INSERT INTO `t_si_authority` VALUES ('205', '1', '获取接入商户列表', 'cposMer:list', 'admin', '20170816093707', null, null, '');
INSERT INTO `t_si_authority` VALUES ('206', '1', '获取接入终端列表', 'cposTerm:list', 'admin', '20170816094113', null, null, '');
INSERT INTO `t_si_authority` VALUES ('207', '1', '获取转出商户列表', 'rMer:list', 'admin', '20170816143317', null, null, '');
INSERT INTO `t_si_authority` VALUES ('208', '1', '获取转出终端列表', 'rTerm:list', 'admin', '20170816143346', null, null, '');
INSERT INTO `t_si_authority` VALUES ('209', '1', '查看接入商户详情页面', 'cposMer:detail', 'admin', '20170817113218', null, null, '');
INSERT INTO `t_si_authority` VALUES ('210', '1', '查看接入终端详情页面', 'cposTerm:detail', 'admin', '20170817125821', null, null, '');
INSERT INTO `t_si_authority` VALUES ('211', '1', '查看转出商户详情页面', 'rMer:detail', 'admin', '20170817125848', null, null, '');
INSERT INTO `t_si_authority` VALUES ('212', '1', '查看转出终端详情页面', 'rTerm:detail', 'admin', '20170817125909', null, null, '');
INSERT INTO `t_si_authority` VALUES ('213', '1', '新增接入商户', 'cposMer:save', 'admin', '20170817130109', null, null, '');
INSERT INTO `t_si_authority` VALUES ('214', '1', '新增接入终端', 'cposTerm:save', 'admin', '20170817130137', null, null, '');
INSERT INTO `t_si_authority` VALUES ('215', '1', '新增转出商户', 'rMer:save', 'admin', '20170817130204', 'admin', '20170817130221', '');
INSERT INTO `t_si_authority` VALUES ('216', '1', '新增转出终端', 'rTerm:save', 'admin', '20170817130237', null, null, '');
INSERT INTO `t_si_authority` VALUES ('217', '1', '修改接入商户', 'cposMer:update', 'admin', '20170817130533', null, null, '');
INSERT INTO `t_si_authority` VALUES ('219', '1', '修改转出商户', 'rMer:update', 'admin', '20170817130621', null, null, '');
INSERT INTO `t_si_authority` VALUES ('221', '1', '冻结接入商户', 'cposMer:frozen', 'admin', '20170817133457', null, null, '');
INSERT INTO `t_si_authority` VALUES ('222', '1', '冻结接入终端', 'cposTerm:frozen', 'admin', '20170817133527', null, null, '');
INSERT INTO `t_si_authority` VALUES ('223', '1', '冻结转出商户', 'rMer:frozen', 'admin', '20170817133554', null, null, '');
INSERT INTO `t_si_authority` VALUES ('224', '1', '冻结转出终端', 'rTerm:frozen', 'admin', '20170817133621', null, null, '');
INSERT INTO `t_si_authority` VALUES ('225', '1', '解冻接入商户', 'cposMer:unfrozen', 'admin', '20170817133706', null, null, '');
INSERT INTO `t_si_authority` VALUES ('226', '1', '解冻接入终端', 'cposTerm:unfrozen', 'admin', '20170817133728', null, null, '');
INSERT INTO `t_si_authority` VALUES ('227', '1', '解冻转出商户', 'rMer:unfrozen', 'admin', '20170817133751', null, null, '');
INSERT INTO `t_si_authority` VALUES ('228', '1', '解冻转出终端', 'rTerm:unfrozen', 'admin', '20170817133806', null, null, '');
INSERT INTO `t_si_authority` VALUES ('229', '1', '审核接入商户', 'cposMer:audit', 'admin', '20170817134029', null, null, '');
INSERT INTO `t_si_authority` VALUES ('230', '1', '审核接入终端', 'cposTerm:audit', 'admin', '20170817134057', null, null, '');
INSERT INTO `t_si_authority` VALUES ('231', '1', '审核转出商户', 'rMer:audit', 'admin', '20170817134121', null, null, '');
INSERT INTO `t_si_authority` VALUES ('232', '1', '审核转出终端', 'rTerm:audit', 'admin', '20170817134143', null, null, '');
INSERT INTO `t_si_authority` VALUES ('234', '1', '删除接入商户', 'cposMer:delete', 'admin', '20170817140300', null, null, '');
INSERT INTO `t_si_authority` VALUES ('235', '1', '删除接入终端', 'cposTerm:delete', 'admin', '20170817140319', null, null, '');
INSERT INTO `t_si_authority` VALUES ('236', '1', '删除转出商户', 'rMer:delete', 'admin', '20170817140337', null, null, '');
INSERT INTO `t_si_authority` VALUES ('237', '1', '删除转出终端', 'rTerm:delete', 'admin', '20170817140355', null, null, '');
INSERT INTO `t_si_authority` VALUES ('238', '1', '导入接入商户', 'cposMer:import', 'admin', '20170817141714', null, null, '');
INSERT INTO `t_si_authority` VALUES ('239', '1', '导入接入终端', 'cposTerm:import', 'admin', '20170817141731', 'admin', '20170817141758', '');
INSERT INTO `t_si_authority` VALUES ('240', '1', '导入转出商户', 'rMer:import', 'admin', '20170817141747', null, null, '');
INSERT INTO `t_si_authority` VALUES ('241', '1', '导入转出终端', 'rTerm:import', 'admin', '20170817141817', null, null, '');
INSERT INTO `t_si_authority` VALUES ('242', '1', '查看接入流水报表列表页面', 'cposhistrans:list', 'admin', '20170817165341', null, null, '');
INSERT INTO `t_si_authority` VALUES ('244', '1', '导出流水报表', 'cposhistrans:export', 'admin', '20170822162901', 'admin', '20170822162951', '');
INSERT INTO `t_si_authority` VALUES ('246', '1', '生成密钥', 'cposTerm:createKey', 'admin', '20170823112439', null, null, '');
INSERT INTO `t_si_authority` VALUES ('247', '1', '导出密钥', 'cposTerm:exportKey', 'admin', '20170823112501', null, null, '');
INSERT INTO `t_si_authority` VALUES ('248', '1', '重置密钥', 'cposTerm:resetKey', 'admin', '20170823112525', null, null, '');
INSERT INTO `t_si_authority` VALUES ('249', '1', '转换第三方密钥', 'rTerm:exchangeKey', 'admin', '20170823112557', null, null, '');
INSERT INTO `t_si_authority` VALUES ('250', '1', '导入密钥', 'rTerm:importKey', 'admin', '20170823112616', null, null, '');
INSERT INTO `t_si_authority` VALUES ('251', '1', '查看交易监控页面', 'monitor:enter', 'admin', '20170825120145', null, null, '');
INSERT INTO `t_si_authority` VALUES ('252', '1', '新增渠道映射', 'branchMap:save', 'admin', '20170825164329', null, null, '');
INSERT INTO `t_si_authority` VALUES ('253', '1', '删除渠道映射', 'branchMap:delete', 'admin', '20170825164418', null, null, '');
INSERT INTO `t_si_authority` VALUES ('254', '1', '批量删除渠道映射', 'branchMap:delete', 'admin', '20170825164537', null, null, '');
INSERT INTO `t_si_authority` VALUES ('255', '1', '查看渠道映射详情', 'branchMap:detail', 'admin', '20170825164646', null, null, '');
INSERT INTO `t_si_authority` VALUES ('256', '1', '审核渠道映射', 'branchMap:audit', 'admin', '20170825164730', null, null, '');
INSERT INTO `t_si_authority` VALUES ('257', '1', '冻结渠道映射', 'branchMap:frozen', 'admin', '20170825164858', null, null, '');
INSERT INTO `t_si_authority` VALUES ('258', '1', '解冻渠道映射', 'branchMap:unfrozen', 'admin', '20170825165126', null, null, '');
INSERT INTO `t_si_authority` VALUES ('259', '1', '新增商户映射', 'merMap:save', 'admin', '20170825165624', null, null, '');
INSERT INTO `t_si_authority` VALUES ('260', '1', '删除商户映射', 'merMap:delete', 'admin', '20170825165655', null, null, '');
INSERT INTO `t_si_authority` VALUES ('261', '1', '批量删除商户映射', 'merMap:delete', 'admin', '20170825165731', null, null, '');
INSERT INTO `t_si_authority` VALUES ('262', '1', '查看商户映射详情', 'merMap:detail', 'admin', '20170825165759', null, null, '');
INSERT INTO `t_si_authority` VALUES ('263', '1', '修改商户映射', 'merMap:update', 'admin', '20170825165853', null, null, '');
INSERT INTO `t_si_authority` VALUES ('264', '1', '审核商户映射', 'merMap:audit', 'admin', '20170825165945', null, null, '');
INSERT INTO `t_si_authority` VALUES ('265', '1', '冻结商户映射', 'merMap:frozen', 'admin', '20170825170104', null, null, '');
INSERT INTO `t_si_authority` VALUES ('266', '1', '解冻商户映射', 'merMap:unfrozen', 'admin', '20170825170159', null, null, '');
INSERT INTO `t_si_authority` VALUES ('267', '1', '修改渠道映射', 'branchMap:update', 'admin', '20170825170239', null, null, '');
INSERT INTO `t_si_authority` VALUES ('268', '1', '获取渠道映射列表', 'branchMap:list', 'admin', '20170825174804', null, null, '');
INSERT INTO `t_si_authority` VALUES ('269', '1', '获取商户映射列表', 'merMap:list', 'admin', '20170825174844', null, null, '');
INSERT INTO `t_si_authority` VALUES ('270', '1', '获取终端映射列表', 'termMap:list', 'admin', '20170825174932', null, null, '');
INSERT INTO `t_si_authority` VALUES ('271', '1', '新增终端映射', 'termMap:save', 'admin', '20170825180419', null, null, '');
INSERT INTO `t_si_authority` VALUES ('272', '1', '删除终端映射', 'termMap:delete', 'admin', '20170825180900', null, null, '');
INSERT INTO `t_si_authority` VALUES ('273', '1', '批量删除终端映射', 'termMap:delete', 'admin', '20170825180925', null, null, '');
INSERT INTO `t_si_authority` VALUES ('274', '1', '查看终端映射详情', 'termMap:detail', 'admin', '20170825181010', null, null, '');
INSERT INTO `t_si_authority` VALUES ('275', '1', '审核终端映射', 'termMap:audit', 'admin', '20170825181211', null, null, '');
INSERT INTO `t_si_authority` VALUES ('276', '1', '冻结终端映射', 'termMap:frozen', 'admin', '20170825181244', null, null, '');
INSERT INTO `t_si_authority` VALUES ('277', '1', '解冻终端映射', 'termMap:unfrozen', 'admin', '20170825181317', null, null, '');
INSERT INTO `t_si_authority` VALUES ('278', '1', '查看日志信息', 'log:enter', 'admin', '20170829141652', null, null, '');
INSERT INTO `t_si_authority` VALUES ('279', '1', '获取日志列表', 'log:list', 'admin', '20170829141710', 'admin', '20170911171943', '');
INSERT INTO `t_si_authority` VALUES ('281', '1', '查看日志页面', 'log:getParamsById', 'admin', '20170829153413', 'admin', '20170911172046', '');
INSERT INTO `t_si_authority` VALUES ('282', '1', '批量删除接入商户', 'cposMer:delete', 'admin', '20170912103618', null, null, '');
INSERT INTO `t_si_authority` VALUES ('283', '1', '批量删除接入终端', 'cposTerm:delete', 'admin', '20170912103647', null, null, '');
INSERT INTO `t_si_authority` VALUES ('284', '1', '批量删除转出商户', 'rMer:delete', 'admin', '20170912103718', null, null, '');
INSERT INTO `t_si_authority` VALUES ('285', '1', '批量删除转出终端', 'rTerm:delete', 'admin', '20170912103840', null, null, '');
INSERT INTO `t_si_authority` VALUES ('286', '1', '查看交易日志页面', 'cposhislog:enter', 'admin', '20171012100227', null, null, '');
INSERT INTO `t_si_authority` VALUES ('287', '1', '获取交易日志列表', 'cposhislog:list', 'admin', '20171012100415', null, null, '');
INSERT INTO `t_si_authority` VALUES ('288', '1', '获取同步状态页面', 'realtimenotion:enter', 'admin', '20171012103017', null, null, '');
INSERT INTO `t_si_authority` VALUES ('289', '1', '查看同步状态列表', 'realtimenotion:list', 'admin', '20171012103117', null, null, '');
INSERT INTO `t_si_authority` VALUES ('290', '1', '导出交易日志', 'cposhislog:export', 'admin', '20171013134938', null, null, '');
INSERT INTO `t_si_authority` VALUES ('291', '1', '重发', 'realtimenotion:resend', 'admin', '20171013135018', null, null, '');






-- ----------------------------
-- Table structure for t_si_authfunc
-- ----------------------------
DROP TABLE IF EXISTS `t_si_authfunc`;
CREATE TABLE `t_si_authfunc` (
  `auth_id` int(10) NOT NULL,
  `func_id` int(10) NOT NULL,
  PRIMARY KEY (`auth_id`,`func_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COLLATE=gbk_bin;

-- ----------------------------
-- Records of t_si_authfunc
-- ----------------------------
INSERT INTO `t_si_authfunc` VALUES ('0', '37');
INSERT INTO `t_si_authfunc` VALUES ('0', '38');
INSERT INTO `t_si_authfunc` VALUES ('1', '1');
INSERT INTO `t_si_authfunc` VALUES ('2', '2');
INSERT INTO `t_si_authfunc` VALUES ('3', '3');
INSERT INTO `t_si_authfunc` VALUES ('4', '4');
INSERT INTO `t_si_authfunc` VALUES ('5', '5');
INSERT INTO `t_si_authfunc` VALUES ('6', '37');
INSERT INTO `t_si_authfunc` VALUES ('7', '38');
INSERT INTO `t_si_authfunc` VALUES ('8', '6');
INSERT INTO `t_si_authfunc` VALUES ('9', '7');
INSERT INTO `t_si_authfunc` VALUES ('10', '8');
INSERT INTO `t_si_authfunc` VALUES ('11', '9');
INSERT INTO `t_si_authfunc` VALUES ('12', '10');
INSERT INTO `t_si_authfunc` VALUES ('13', '36');
INSERT INTO `t_si_authfunc` VALUES ('14', '11');
INSERT INTO `t_si_authfunc` VALUES ('15', '12');
INSERT INTO `t_si_authfunc` VALUES ('16', '13');
INSERT INTO `t_si_authfunc` VALUES ('17', '14');
INSERT INTO `t_si_authfunc` VALUES ('18', '15');
INSERT INTO `t_si_authfunc` VALUES ('19', '16');
INSERT INTO `t_si_authfunc` VALUES ('20', '35');
INSERT INTO `t_si_authfunc` VALUES ('21', '17');
INSERT INTO `t_si_authfunc` VALUES ('22', '18');
INSERT INTO `t_si_authfunc` VALUES ('23', '19');
INSERT INTO `t_si_authfunc` VALUES ('24', '20');
INSERT INTO `t_si_authfunc` VALUES ('25', '21');
INSERT INTO `t_si_authfunc` VALUES ('26', '22');
INSERT INTO `t_si_authfunc` VALUES ('27', '34');
INSERT INTO `t_si_authfunc` VALUES ('28', '23');
INSERT INTO `t_si_authfunc` VALUES ('29', '24');
INSERT INTO `t_si_authfunc` VALUES ('30', '25');
INSERT INTO `t_si_authfunc` VALUES ('31', '26');
INSERT INTO `t_si_authfunc` VALUES ('32', '33');
INSERT INTO `t_si_authfunc` VALUES ('33', '27');
INSERT INTO `t_si_authfunc` VALUES ('34', '28');
INSERT INTO `t_si_authfunc` VALUES ('35', '29');
INSERT INTO `t_si_authfunc` VALUES ('36', '30');
INSERT INTO `t_si_authfunc` VALUES ('37', '31');
INSERT INTO `t_si_authfunc` VALUES ('38', '32');
INSERT INTO `t_si_authfunc` VALUES ('39', '39');
INSERT INTO `t_si_authfunc` VALUES ('41', '41');
INSERT INTO `t_si_authfunc` VALUES ('42', '42');
INSERT INTO `t_si_authfunc` VALUES ('43', '43');
INSERT INTO `t_si_authfunc` VALUES ('45', '45');
INSERT INTO `t_si_authfunc` VALUES ('46', '46');
INSERT INTO `t_si_authfunc` VALUES ('47', '47');
INSERT INTO `t_si_authfunc` VALUES ('48', '48');
INSERT INTO `t_si_authfunc` VALUES ('164', '170');
INSERT INTO `t_si_authfunc` VALUES ('165', '171');
INSERT INTO `t_si_authfunc` VALUES ('166', '172');
INSERT INTO `t_si_authfunc` VALUES ('167', '173');
INSERT INTO `t_si_authfunc` VALUES ('168', '174');
INSERT INTO `t_si_authfunc` VALUES ('169', '175');
INSERT INTO `t_si_authfunc` VALUES ('194', '194');
INSERT INTO `t_si_authfunc` VALUES ('195', '195');
INSERT INTO `t_si_authfunc` VALUES ('196', '196');
INSERT INTO `t_si_authfunc` VALUES ('197', '197');
INSERT INTO `t_si_authfunc` VALUES ('198', '198');
INSERT INTO `t_si_authfunc` VALUES ('199', '199');
INSERT INTO `t_si_authfunc` VALUES ('200', '200');
INSERT INTO `t_si_authfunc` VALUES ('201', '201');
INSERT INTO `t_si_authfunc` VALUES ('202', '202');
INSERT INTO `t_si_authfunc` VALUES ('203', '203');
INSERT INTO `t_si_authfunc` VALUES ('205', '205');
INSERT INTO `t_si_authfunc` VALUES ('206', '206');
INSERT INTO `t_si_authfunc` VALUES ('207', '207');
INSERT INTO `t_si_authfunc` VALUES ('208', '208');
INSERT INTO `t_si_authfunc` VALUES ('209', '209');
INSERT INTO `t_si_authfunc` VALUES ('210', '210');
INSERT INTO `t_si_authfunc` VALUES ('211', '211');
INSERT INTO `t_si_authfunc` VALUES ('212', '212');
INSERT INTO `t_si_authfunc` VALUES ('213', '213');
INSERT INTO `t_si_authfunc` VALUES ('214', '214');
INSERT INTO `t_si_authfunc` VALUES ('215', '215');
INSERT INTO `t_si_authfunc` VALUES ('216', '216');
INSERT INTO `t_si_authfunc` VALUES ('217', '217');
INSERT INTO `t_si_authfunc` VALUES ('219', '219');
INSERT INTO `t_si_authfunc` VALUES ('221', '221');
INSERT INTO `t_si_authfunc` VALUES ('222', '222');
INSERT INTO `t_si_authfunc` VALUES ('223', '223');
INSERT INTO `t_si_authfunc` VALUES ('224', '224');
INSERT INTO `t_si_authfunc` VALUES ('225', '225');
INSERT INTO `t_si_authfunc` VALUES ('226', '226');
INSERT INTO `t_si_authfunc` VALUES ('227', '227');
INSERT INTO `t_si_authfunc` VALUES ('228', '228');
INSERT INTO `t_si_authfunc` VALUES ('229', '229');
INSERT INTO `t_si_authfunc` VALUES ('230', '230');
INSERT INTO `t_si_authfunc` VALUES ('231', '231');
INSERT INTO `t_si_authfunc` VALUES ('232', '232');
INSERT INTO `t_si_authfunc` VALUES ('234', '234');
INSERT INTO `t_si_authfunc` VALUES ('235', '235');
INSERT INTO `t_si_authfunc` VALUES ('236', '236');
INSERT INTO `t_si_authfunc` VALUES ('237', '237');
INSERT INTO `t_si_authfunc` VALUES ('238', '238');
INSERT INTO `t_si_authfunc` VALUES ('239', '239');
INSERT INTO `t_si_authfunc` VALUES ('240', '240');
INSERT INTO `t_si_authfunc` VALUES ('241', '241');
INSERT INTO `t_si_authfunc` VALUES ('242', '242');
INSERT INTO `t_si_authfunc` VALUES ('244', '244');
INSERT INTO `t_si_authfunc` VALUES ('246', '246');
INSERT INTO `t_si_authfunc` VALUES ('247', '247');
INSERT INTO `t_si_authfunc` VALUES ('248', '248');
INSERT INTO `t_si_authfunc` VALUES ('249', '249');
INSERT INTO `t_si_authfunc` VALUES ('250', '250');
INSERT INTO `t_si_authfunc` VALUES ('251', '259');
INSERT INTO `t_si_authfunc` VALUES ('252', '251');
INSERT INTO `t_si_authfunc` VALUES ('253', '252');
INSERT INTO `t_si_authfunc` VALUES ('254', '253');
INSERT INTO `t_si_authfunc` VALUES ('255', '254');
INSERT INTO `t_si_authfunc` VALUES ('256', '256');
INSERT INTO `t_si_authfunc` VALUES ('257', '257');
INSERT INTO `t_si_authfunc` VALUES ('258', '258');
INSERT INTO `t_si_authfunc` VALUES ('259', '260');
INSERT INTO `t_si_authfunc` VALUES ('260', '261');
INSERT INTO `t_si_authfunc` VALUES ('262', '263');
INSERT INTO `t_si_authfunc` VALUES ('263', '264');
INSERT INTO `t_si_authfunc` VALUES ('264', '265');
INSERT INTO `t_si_authfunc` VALUES ('265', '266');
INSERT INTO `t_si_authfunc` VALUES ('266', '267');
INSERT INTO `t_si_authfunc` VALUES ('267', '255');
INSERT INTO `t_si_authfunc` VALUES ('268', '271');
INSERT INTO `t_si_authfunc` VALUES ('269', '272');
INSERT INTO `t_si_authfunc` VALUES ('270', '273');
INSERT INTO `t_si_authfunc` VALUES ('271', '268');
INSERT INTO `t_si_authfunc` VALUES ('272', '269');
INSERT INTO `t_si_authfunc` VALUES ('273', '270');
INSERT INTO `t_si_authfunc` VALUES ('274', '277');
INSERT INTO `t_si_authfunc` VALUES ('275', '274');
INSERT INTO `t_si_authfunc` VALUES ('276', '275');
INSERT INTO `t_si_authfunc` VALUES ('277', '276');
INSERT INTO `t_si_authfunc` VALUES ('278', '278');
INSERT INTO `t_si_authfunc` VALUES ('279', '279');
INSERT INTO `t_si_authfunc` VALUES ('281', '281');
INSERT INTO `t_si_authfunc` VALUES ('282', '282');
INSERT INTO `t_si_authfunc` VALUES ('283', '283');
INSERT INTO `t_si_authfunc` VALUES ('284', '284');
INSERT INTO `t_si_authfunc` VALUES ('285', '285');
INSERT INTO `t_si_authfunc` VALUES ('286', '286');
INSERT INTO `t_si_authfunc` VALUES ('287', '287');
INSERT INTO `t_si_authfunc` VALUES ('288', '288');
INSERT INTO `t_si_authfunc` VALUES ('289', '289');
INSERT INTO `t_si_authfunc` VALUES ('290', '290');
INSERT INTO `t_si_authfunc` VALUES ('291', '291');

-- ----------------------------
-- Table structure for t_si_site
-- ----------------------------
DROP TABLE IF EXISTS `t_si_site`;
CREATE TABLE `t_si_site` (
  `id` int(10) NOT NULL COMMENT '站点id',
  `name` varchar(100) COLLATE gbk_bin NOT NULL COMMENT '站点名称',
  `tag` char(1) COLLATE gbk_bin NOT NULL COMMENT '站点标识，P:管理平台；A:代理商门户；B:通道商门户；M：商户或门店门户',
  `url` varchar(100) COLLATE gbk_bin NOT NULL COMMENT '首页URL',
  `status` char(1) COLLATE gbk_bin NOT NULL COMMENT '状态，0：冻结；1：启用',
  `buildoper` varchar(20) COLLATE gbk_bin NOT NULL COMMENT '创建者',
  `builddatetime` varchar(20) COLLATE gbk_bin NOT NULL COMMENT '创建时间',
  `modifyoper` varchar(20) COLLATE gbk_bin DEFAULT NULL COMMENT '修改者',
  `modifydatetime` varchar(20) COLLATE gbk_bin DEFAULT NULL COMMENT '修改时间',
  `notes` varchar(200) COLLATE gbk_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COLLATE=gbk_bin;

-- ----------------------------
-- Records of t_si_site
-- ----------------------------
INSERT INTO `t_si_site` VALUES ('1', '管理平台', 'P', '/', '2', '', '20170505170749', 'admin', '20170821112655', null);

-- ----------------------------
-- Table structure for t_si_organization
-- ----------------------------
DROP TABLE IF EXISTS `t_si_organization`;
CREATE TABLE `t_si_organization` (
  `id` int(32) NOT NULL COMMENT '机构id',
  `site_id` int(10) NOT NULL COMMENT '站点id',
  `orgId` varchar(100) COLLATE gbk_bin DEFAULT NULL,
  `name` varchar(100) COLLATE gbk_bin NOT NULL COMMENT '机构名称',
  `pid` int(32) DEFAULT NULL COMMENT '上级机构id',
  `orderno` int(10) NOT NULL,
  `buildoper` varchar(20) COLLATE gbk_bin NOT NULL COMMENT '创建者',
  `builddatetime` varchar(20) COLLATE gbk_bin NOT NULL COMMENT '创建时间',
  `modifyoper` varchar(20) COLLATE gbk_bin DEFAULT NULL COMMENT '修改者',
  `modifydatetime` varchar(20) COLLATE gbk_bin DEFAULT NULL COMMENT '修改时间',
  `notes` varchar(200) COLLATE gbk_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COLLATE=gbk_bin;

-- ----------------------------
-- Records of t_si_organization
-- ----------------------------
INSERT INTO `t_si_organization` VALUES ('1', '1', '1', '百富', null, '1', 'admin', '20170502170745', null, null, null);

-- ----------------------------
-- Table structure for t_si_role
-- ----------------------------
DROP TABLE IF EXISTS `t_si_role`;
CREATE TABLE `t_si_role` (
  `id` int(10) NOT NULL COMMENT '角色id',
  `site_id` int(10) NOT NULL COMMENT '站点id',
  `name` varchar(100) COLLATE gbk_bin NOT NULL COMMENT '角色名称',
  `org_id` int(32) NOT NULL COMMENT '机构id',
  `buildoper` varchar(20) COLLATE gbk_bin NOT NULL COMMENT '创建者',
  `builddatetime` varchar(20) COLLATE gbk_bin NOT NULL COMMENT '创建时间',
  `modifyoper` varchar(20) COLLATE gbk_bin DEFAULT NULL COMMENT '修改者',
  `modifydatetime` varchar(20) COLLATE gbk_bin DEFAULT NULL COMMENT '修改时间',
  `notes` varchar(200) COLLATE gbk_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COLLATE=gbk_bin;

-- ----------------------------
-- Records of t_si_role
-- ----------------------------
INSERT INTO `t_si_role` VALUES ('1', '1', '超级管理员角色', '1', '', '20170510153528', null, null, '');

-- ----------------------------
-- Table structure for t_si_roleauth
-- ----------------------------
DROP TABLE IF EXISTS `t_si_roleauth`;
CREATE TABLE `t_si_roleauth` (
  `role_id` int(10) NOT NULL,
  `auth_id` int(10) NOT NULL,
  PRIMARY KEY (`role_id`,`auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COLLATE=gbk_bin;

-- ----------------------------
-- Records of t_si_roleauth
-- ----------------------------
INSERT INTO `t_si_roleauth` VALUES ('1', '1');
INSERT INTO `t_si_roleauth` VALUES ('1', '2');
INSERT INTO `t_si_roleauth` VALUES ('1', '3');
INSERT INTO `t_si_roleauth` VALUES ('1', '4');
INSERT INTO `t_si_roleauth` VALUES ('1', '5');
INSERT INTO `t_si_roleauth` VALUES ('1', '6');
INSERT INTO `t_si_roleauth` VALUES ('1', '7');
INSERT INTO `t_si_roleauth` VALUES ('1', '8');
INSERT INTO `t_si_roleauth` VALUES ('1', '9');
INSERT INTO `t_si_roleauth` VALUES ('1', '10');
INSERT INTO `t_si_roleauth` VALUES ('1', '11');
INSERT INTO `t_si_roleauth` VALUES ('1', '12');
INSERT INTO `t_si_roleauth` VALUES ('1', '13');
INSERT INTO `t_si_roleauth` VALUES ('1', '14');
INSERT INTO `t_si_roleauth` VALUES ('1', '15');
INSERT INTO `t_si_roleauth` VALUES ('1', '16');
INSERT INTO `t_si_roleauth` VALUES ('1', '17');
INSERT INTO `t_si_roleauth` VALUES ('1', '18');
INSERT INTO `t_si_roleauth` VALUES ('1', '19');
INSERT INTO `t_si_roleauth` VALUES ('1', '20');
INSERT INTO `t_si_roleauth` VALUES ('1', '21');
INSERT INTO `t_si_roleauth` VALUES ('1', '22');
INSERT INTO `t_si_roleauth` VALUES ('1', '23');
INSERT INTO `t_si_roleauth` VALUES ('1', '24');
INSERT INTO `t_si_roleauth` VALUES ('1', '25');
INSERT INTO `t_si_roleauth` VALUES ('1', '26');
INSERT INTO `t_si_roleauth` VALUES ('1', '27');
INSERT INTO `t_si_roleauth` VALUES ('1', '28');
INSERT INTO `t_si_roleauth` VALUES ('1', '29');
INSERT INTO `t_si_roleauth` VALUES ('1', '30');
INSERT INTO `t_si_roleauth` VALUES ('1', '31');
INSERT INTO `t_si_roleauth` VALUES ('1', '32');
INSERT INTO `t_si_roleauth` VALUES ('1', '33');
INSERT INTO `t_si_roleauth` VALUES ('1', '34');
INSERT INTO `t_si_roleauth` VALUES ('1', '35');
INSERT INTO `t_si_roleauth` VALUES ('1', '36');
INSERT INTO `t_si_roleauth` VALUES ('1', '37');
INSERT INTO `t_si_roleauth` VALUES ('1', '38');
INSERT INTO `t_si_roleauth` VALUES ('1', '39');
INSERT INTO `t_si_roleauth` VALUES ('1', '41');
INSERT INTO `t_si_roleauth` VALUES ('1', '42');
INSERT INTO `t_si_roleauth` VALUES ('1', '43');
INSERT INTO `t_si_roleauth` VALUES ('1', '45');
INSERT INTO `t_si_roleauth` VALUES ('1', '46');
INSERT INTO `t_si_roleauth` VALUES ('1', '47');
INSERT INTO `t_si_roleauth` VALUES ('1', '48');
INSERT INTO `t_si_roleauth` VALUES ('1', '164');
INSERT INTO `t_si_roleauth` VALUES ('1', '165');
INSERT INTO `t_si_roleauth` VALUES ('1', '166');
INSERT INTO `t_si_roleauth` VALUES ('1', '167');
INSERT INTO `t_si_roleauth` VALUES ('1', '168');
INSERT INTO `t_si_roleauth` VALUES ('1', '169');
INSERT INTO `t_si_roleauth` VALUES ('1', '194');
INSERT INTO `t_si_roleauth` VALUES ('1', '195');
INSERT INTO `t_si_roleauth` VALUES ('1', '196');
INSERT INTO `t_si_roleauth` VALUES ('1', '197');
INSERT INTO `t_si_roleauth` VALUES ('1', '198');
INSERT INTO `t_si_roleauth` VALUES ('1', '199');
INSERT INTO `t_si_roleauth` VALUES ('1', '200');
INSERT INTO `t_si_roleauth` VALUES ('1', '201');
INSERT INTO `t_si_roleauth` VALUES ('1', '202');
INSERT INTO `t_si_roleauth` VALUES ('1', '203');
INSERT INTO `t_si_roleauth` VALUES ('1', '205');
INSERT INTO `t_si_roleauth` VALUES ('1', '206');
INSERT INTO `t_si_roleauth` VALUES ('1', '207');
INSERT INTO `t_si_roleauth` VALUES ('1', '208');
INSERT INTO `t_si_roleauth` VALUES ('1', '209');
INSERT INTO `t_si_roleauth` VALUES ('1', '210');
INSERT INTO `t_si_roleauth` VALUES ('1', '211');
INSERT INTO `t_si_roleauth` VALUES ('1', '212');
INSERT INTO `t_si_roleauth` VALUES ('1', '213');
INSERT INTO `t_si_roleauth` VALUES ('1', '214');
INSERT INTO `t_si_roleauth` VALUES ('1', '215');
INSERT INTO `t_si_roleauth` VALUES ('1', '216');
INSERT INTO `t_si_roleauth` VALUES ('1', '217');
INSERT INTO `t_si_roleauth` VALUES ('1', '219');
INSERT INTO `t_si_roleauth` VALUES ('1', '221');
INSERT INTO `t_si_roleauth` VALUES ('1', '222');
INSERT INTO `t_si_roleauth` VALUES ('1', '223');
INSERT INTO `t_si_roleauth` VALUES ('1', '224');
INSERT INTO `t_si_roleauth` VALUES ('1', '225');
INSERT INTO `t_si_roleauth` VALUES ('1', '226');
INSERT INTO `t_si_roleauth` VALUES ('1', '227');
INSERT INTO `t_si_roleauth` VALUES ('1', '228');
INSERT INTO `t_si_roleauth` VALUES ('1', '229');
INSERT INTO `t_si_roleauth` VALUES ('1', '230');
INSERT INTO `t_si_roleauth` VALUES ('1', '231');
INSERT INTO `t_si_roleauth` VALUES ('1', '232');
INSERT INTO `t_si_roleauth` VALUES ('1', '234');
INSERT INTO `t_si_roleauth` VALUES ('1', '235');
INSERT INTO `t_si_roleauth` VALUES ('1', '236');
INSERT INTO `t_si_roleauth` VALUES ('1', '237');
INSERT INTO `t_si_roleauth` VALUES ('1', '238');
INSERT INTO `t_si_roleauth` VALUES ('1', '239');
INSERT INTO `t_si_roleauth` VALUES ('1', '240');
INSERT INTO `t_si_roleauth` VALUES ('1', '241');
INSERT INTO `t_si_roleauth` VALUES ('1', '242');
INSERT INTO `t_si_roleauth` VALUES ('1', '244');
INSERT INTO `t_si_roleauth` VALUES ('1', '246');
INSERT INTO `t_si_roleauth` VALUES ('1', '247');
INSERT INTO `t_si_roleauth` VALUES ('1', '248');
INSERT INTO `t_si_roleauth` VALUES ('1', '249');
INSERT INTO `t_si_roleauth` VALUES ('1', '250');
INSERT INTO `t_si_roleauth` VALUES ('1', '251');
INSERT INTO `t_si_roleauth` VALUES ('1', '252');
INSERT INTO `t_si_roleauth` VALUES ('1', '253');
INSERT INTO `t_si_roleauth` VALUES ('1', '254');
INSERT INTO `t_si_roleauth` VALUES ('1', '255');
INSERT INTO `t_si_roleauth` VALUES ('1', '256');
INSERT INTO `t_si_roleauth` VALUES ('1', '257');
INSERT INTO `t_si_roleauth` VALUES ('1', '258');
INSERT INTO `t_si_roleauth` VALUES ('1', '259');
INSERT INTO `t_si_roleauth` VALUES ('1', '260');
INSERT INTO `t_si_roleauth` VALUES ('1', '262');
INSERT INTO `t_si_roleauth` VALUES ('1', '263');
INSERT INTO `t_si_roleauth` VALUES ('1', '264');
INSERT INTO `t_si_roleauth` VALUES ('1', '265');
INSERT INTO `t_si_roleauth` VALUES ('1', '266');
INSERT INTO `t_si_roleauth` VALUES ('1', '267');
INSERT INTO `t_si_roleauth` VALUES ('1', '268');
INSERT INTO `t_si_roleauth` VALUES ('1', '269');
INSERT INTO `t_si_roleauth` VALUES ('1', '270');
INSERT INTO `t_si_roleauth` VALUES ('1', '271');
INSERT INTO `t_si_roleauth` VALUES ('1', '272');
INSERT INTO `t_si_roleauth` VALUES ('1', '273');
INSERT INTO `t_si_roleauth` VALUES ('1', '274');
INSERT INTO `t_si_roleauth` VALUES ('1', '275');
INSERT INTO `t_si_roleauth` VALUES ('1', '276');
INSERT INTO `t_si_roleauth` VALUES ('1', '277');
INSERT INTO `t_si_roleauth` VALUES ('1', '278');
INSERT INTO `t_si_roleauth` VALUES ('1', '279');
INSERT INTO `t_si_roleauth` VALUES ('1', '281');
INSERT INTO `t_si_roleauth` VALUES ('1', '286');
INSERT INTO `t_si_roleauth` VALUES ('1', '287');
INSERT INTO `t_si_roleauth` VALUES ('1', '288');
INSERT INTO `t_si_roleauth` VALUES ('1', '289');
INSERT INTO `t_si_roleauth` VALUES ('1', '290');
INSERT INTO `t_si_roleauth` VALUES ('1', '291');

-- ----------------------------
-- Table structure for t_si_user
-- ----------------------------
DROP TABLE IF EXISTS `t_si_user`;
CREATE TABLE `t_si_user` (
  `id` int(10) NOT NULL COMMENT '用户id',
  `site_id` int(10) NOT NULL COMMENT '站点id',
  `name` varchar(100) COLLATE gbk_bin NOT NULL COMMENT '用户姓名',
  `loginname` varchar(100) COLLATE gbk_bin NOT NULL COMMENT '登陆名称',
  `salt` varchar(100) COLLATE gbk_bin NOT NULL COMMENT '密码md5盐値',
  `password` varchar(100) COLLATE gbk_bin NOT NULL COMMENT '登陆密码',
  `email` varchar(100) COLLATE gbk_bin DEFAULT NULL COMMENT '邮箱',
  `phoneno` varchar(100) COLLATE gbk_bin DEFAULT NULL COMMENT '手机',
  `org_id` int(32) NOT NULL COMMENT '机构id',
  `status` char(1) COLLATE gbk_bin NOT NULL COMMENT '状态，0 : 未启用 ; 1：冻结；2：启用 ; 3: 注销',
  `buildoper` varchar(20) COLLATE gbk_bin NOT NULL COMMENT '创建者',
  `builddatetime` varchar(20) COLLATE gbk_bin NOT NULL COMMENT '创建时间',
  `modifyoper` varchar(20) COLLATE gbk_bin DEFAULT NULL COMMENT '修改者',
  `modifydatetime` varchar(20) COLLATE gbk_bin DEFAULT NULL COMMENT '修改时间',
  `notes` varchar(200) COLLATE gbk_bin DEFAULT NULL COMMENT '备注',
  `lastlogintime` varchar(14) COLLATE gbk_bin DEFAULT NULL,
  `lastupdatepwdtime` varchar(14) COLLATE gbk_bin DEFAULT NULL,
  `frozentime` varchar(14) COLLATE gbk_bin DEFAULT NULL,
  `frozenreason` varchar(200) COLLATE gbk_bin DEFAULT NULL,
  `errortimes` varchar(1) COLLATE gbk_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COLLATE=gbk_bin;

-- ----------------------------
-- Records of t_si_user
-- ----------------------------
INSERT INTO `t_si_user` VALUES ('1', '1', 'admin', 'admin', 'ix51fS', '45f458923827b08a3f245c408c0cf46b', '12344121@qq.com', '13212341234', '1', '2', '', '20170509164541', null, null, null, '20171124013245', '20170904181823', null, '', '');

-- ----------------------------
-- Table structure for t_si_userrole
-- ----------------------------
DROP TABLE IF EXISTS `t_si_userrole`;
CREATE TABLE `t_si_userrole` (
  `user_id` int(10) NOT NULL,
  `role_id` int(10) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COLLATE=gbk_bin;

-- ----------------------------
-- Records of t_si_userrole
-- ----------------------------
INSERT INTO `t_si_userrole` VALUES ('1', '1');

-- ----------------------------
-- Table structure for t_si_rolemodel
-- ----------------------------
DROP TABLE IF EXISTS `t_si_rolemodel`;
CREATE TABLE `t_si_rolemodel` (
  `id` int(10) NOT NULL COMMENT '角色id',
  `site_id` int(10) NOT NULL COMMENT '站点id',
  `name` varchar(100) COLLATE gbk_bin NOT NULL COMMENT '角色名称',
  `tag` varchar(2) COLLATE gbk_bin NOT NULL,
  `buildoper` varchar(20) COLLATE gbk_bin NOT NULL COMMENT '创建者',
  `builddatetime` varchar(20) COLLATE gbk_bin NOT NULL COMMENT '创建时间',
  `modifyoper` varchar(20) COLLATE gbk_bin DEFAULT NULL COMMENT '修改者',
  `modifydatetime` varchar(20) COLLATE gbk_bin DEFAULT NULL COMMENT '修改时间',
  `notes` varchar(200) COLLATE gbk_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COLLATE=gbk_bin;

-- ----------------------------
-- Records of t_si_rolemodel
-- ----------------------------




-- ----------------------------
-- Table structure for t_si_rolemodelauth
-- ----------------------------
DROP TABLE IF EXISTS `t_si_rolemodelauth`;
CREATE TABLE `t_si_rolemodelauth` (
  `role_id` int(10) NOT NULL,
  `auth_id` int(10) NOT NULL,
  PRIMARY KEY (`role_id`,`auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COLLATE=gbk_bin;

-- ----------------------------
-- Records of t_si_rolemodelauth
-- ----------------------------

-- ----------------------------
-- Table structure for t_si_userrolemodel
-- ----------------------------
DROP TABLE IF EXISTS `t_si_userrolemodel`;
CREATE TABLE `t_si_userrolemodel` (
  `user_id` int(10) NOT NULL,
  `role_id` int(10) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COLLATE=gbk_bin;

-- ----------------------------
-- Records of t_si_userrolemodel
-- ----------------------------

-- ----------------------------
-- Table structure for t_si_operatelog
-- ----------------------------
DROP TABLE IF EXISTS `t_si_operatelog`;
CREATE TABLE `t_si_operatelog` (
  `id` int(10) NOT NULL,
  `operator` varchar(50) COLLATE gbk_bin DEFAULT NULL,
  `name` varchar(50) COLLATE gbk_bin DEFAULT NULL,
  `operatetime` varchar(14) COLLATE gbk_bin DEFAULT NULL,
  `flag` varchar(1) COLLATE gbk_bin DEFAULT NULL,
  `description` varchar(255) COLLATE gbk_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COLLATE=gbk_bin;



-- ----------------------------
-- Table structure for t_si_operatelogparam
-- ----------------------------
DROP TABLE IF EXISTS `t_si_operatelogparam`;
CREATE TABLE `t_si_operatelogparam` (
  `operatelogid` int(10) DEFAULT NULL,
  `name` varchar(50) COLLATE gbk_bin DEFAULT NULL,
  `val` varchar(255) COLLATE gbk_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk COLLATE=gbk_bin;








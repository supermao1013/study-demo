/*
Navicat MySQL Data Transfer

Source Server         : myConsult
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : consult

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2017-08-31 23:03:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `consult_configarea`
-- ----------------------------
DROP TABLE IF EXISTS `consult_configarea`;
CREATE TABLE `consult_configarea` (
  `areaCode` varchar(6) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `areaName` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `state` int(1) DEFAULT NULL COMMENT '0 有效，1 失效',
  PRIMARY KEY (`areaCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of consult_configarea
-- ----------------------------
INSERT INTO `consult_configarea` VALUES ('101', '测试1', '0');
INSERT INTO `consult_configarea` VALUES ('102', '测试2', '0');
INSERT INTO `consult_configarea` VALUES ('103', '测试2', '0');
INSERT INTO `consult_configarea` VALUES ('104', '测试2', '0');
INSERT INTO `consult_configarea` VALUES ('105', '测试2', '0');
INSERT INTO `consult_configarea` VALUES ('106', '测试2', '0');
INSERT INTO `consult_configarea` VALUES ('A', '湖南', '0');
INSERT INTO `consult_configarea` VALUES ('B', '湖北', '0');
INSERT INTO `consult_configarea` VALUES ('C', '江西', '0');
INSERT INTO `consult_configarea` VALUES ('E', '江西', '0');
INSERT INTO `consult_configarea` VALUES ('F', '安徽', '0');
INSERT INTO `consult_configarea` VALUES ('G', '江西', '0');
INSERT INTO `consult_configarea` VALUES ('HHVIP', 'basenameVIP', '0');
INSERT INTO `consult_configarea` VALUES ('base', 'basename', '0');
INSERT INTO `consult_configarea` VALUES ('ds31', 'ds3name---1', '0');

-- ----------------------------
-- Table structure for `consult_content`
-- ----------------------------
DROP TABLE IF EXISTS `consult_content`;
CREATE TABLE `consult_content` (
  `itemIndex` int(100) DEFAULT NULL,
  `content` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '0--22个问题的征询内容， 1--11个问题的',
  `state` int(1) DEFAULT NULL COMMENT '0 有效，1 失效',
  `id` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of consult_content
-- ----------------------------
INSERT INTO `consult_content` VALUES ('1', '是否经常头昏、眼花、耳鸣、晕血、晕针及有美尼尔病。', '0', '0', '1');
INSERT INTO `consult_content` VALUES ('2', '是否有性病、麻风病、艾滋病，以及HIV-1、HIV-2抗体阳性。', '0', '0', '2');
INSERT INTO `consult_content` VALUES ('3', '是否有肝病史、乙型肝炎表面抗原阳性、HVC抗体阳性。', '0', '0', '3');
INSERT INTO `consult_content` VALUES ('4', '是否患反复发作过敏性疾病、荨麻疹、支气管哮喘、药物过敏。', '0', '0', '4');
INSERT INTO `consult_content` VALUES ('5', '是否有肺结核、肾结核、淋巴结核、骨结核。', '0', '0', '5');
INSERT INTO `consult_content` VALUES ('6', '是否有心血管疾病及病史，各种心脏病、高血压、低血压、心肌炎、血栓性静脉炎。', '0', '0', '6');
INSERT INTO `consult_content` VALUES ('7', '是否有呼吸道疾病（包括慢性支气管炎、肺气肿、支气管扩张、肺功能不全）。', '0', '0', '7');
INSERT INTO `consult_content` VALUES ('8', '是否有消化系统疾病（如较严重的胃及十二指肠溃疡、慢性胃肠炎、慢性胰腺炎）。', '0', '0', '8');
INSERT INTO `consult_content` VALUES ('9', '是否有泌尿系统疾病（如急慢性肾炎、慢性泌尿系统感染、肾病综合征及急慢性肾功能不全）。', '0', '0', '9');
INSERT INTO `consult_content` VALUES ('10', '是否有各种血液病（包括贫血、白血病、真性红细胞增多症及各种出血凝血性疾病）。', '0', '0', '10');
INSERT INTO `consult_content` VALUES ('11', '是否有内分泌疾病或代谢障碍性疾病（如甲亢、肢端肥大症、尿崩症、糖尿病等）。', '0', '0', '11');
INSERT INTO `consult_content` VALUES ('12', '是否有器质性神经系统疾患或精神病（如脑炎、脑外伤后遗症、癫痫、精神分裂症、癔症、严重神经衰等）。', '0', '0', '12');
INSERT INTO `consult_content` VALUES ('13', '是否有寄生虫病及地方病（如黑热病、吸血虫病、丝虫病、钩虫病、绦虫病、肺吸虫病、克山病、大骨等）。', '0', '0', '13');
INSERT INTO `consult_content` VALUES ('14', '是否患恶性肿瘤及影响健康的良性肿瘤。', '0', '0', '14');
INSERT INTO `consult_content` VALUES ('15', '是否做过切除胃、肾、胆囊、脾、肺等重要脏器手术。', '0', '0', '15');
INSERT INTO `consult_content` VALUES ('16', '是否接触过有害物质或放射性物质。', '0', '0', '16');
INSERT INTO `consult_content` VALUES ('17', '是否是易感染人免疫缺陷病毒的高危人群，有吸毒、同性恋及有多个性伙伴。', '0', '0', '17');
INSERT INTO `consult_content` VALUES ('18', '是否患克雅病（克罗依茨-雅克布病，CJD）和变异性克雅病（vCJD)及有家族病史者，接受过人和动物脑垂体来源物质（如生长激素、促性激素、甲状腺刺激素等）治疗，接受器官（含角膜、骨髓、硬脑壳）移植。是否暴露于牛海绵状脑病（BSE）和vCJD。', '0', '0', '18');
INSERT INTO `consult_content` VALUES ('19', '是否患慢性皮肤病，特别是传染性、过敏性及炎症性全身皮肤病（如黄癣、广泛性湿疹及全身性牛皮癣等）。', '0', '0', '19');
INSERT INTO `consult_content` VALUES ('20', '是否患自身免疫性疾病及胶原性疾病（如系统性红斑狼疮、皮肌炎、硬皮病等）。', '0', '0', '20');
INSERT INTO `consult_content` VALUES ('21', '是否被携带狂犬病病毒的动物咬伤过。', '0', '0', '21');
INSERT INTO `consult_content` VALUES ('22', '是否患有医生认为不能供血浆的其他疾病。', '0', '0', '22');
INSERT INTO `consult_content` VALUES ('1', '近2周内是否拔过牙或做其它小手术？', '1', '0', '23');
INSERT INTO `consult_content` VALUES ('2', '近一周内是否患感冒、急性肠胃炎？', '1', '0', '24');
INSERT INTO `consult_content` VALUES ('3', '近一个月内是否患过急性泌尿系统感染，半年内是否患过肺炎？', '1', '0', '25');
INSERT INTO `consult_content` VALUES ('4', '近期是否外出去过某些传染病和防疫部门特定的传染病流行高危地区？半年内是否患过痢疾，一年内是否患过伤寒、布氏杆菌病，三年内是否有患过疟疾？', '1', '0', '26');
INSERT INTO `consult_content` VALUES ('5', '半年内是否有献过全血？两年内是否接受过输血治疗？', '1', '0', '27');
INSERT INTO `consult_content` VALUES ('6', '被血液或者组织液污染的器材致伤或者污染伤口以及施行纹身术后未满1年者？', '1', '0', '28');
INSERT INTO `consult_content` VALUES ('7', '是否与传染病患者有密切接触，自接触之日起至该病最长潜伏期？', '1', '0', '29');
INSERT INTO `consult_content` VALUES ('8', '接受动物血清制品者于最后一次注射后4周内？一年内是否接受乙型肝炎免疫球蛋白注射？', '1', '0', '30');
INSERT INTO `consult_content` VALUES ('9', '近2周内是否接受过麻疹、腮腺炎、黄热、脊髓灰质炎、甲型肝炎减毒活疫苗免疫注射？4周内是否接受过风疹活疫苗、狂犬病疫苗注射？', '1', '0', '31');
INSERT INTO `consult_content` VALUES ('10', '妇女是否月经前后三天、月经失调、妊娠期、流产后未满六个月，分娩及哺乳期未满一年者。', '1', '0', '32');
INSERT INTO `consult_content` VALUES ('11', '是否到其他单采血浆站供过浆？', '1', '0', '33');
INSERT INTO `consult_content` VALUES ('115', '?????119', '0', '1', '34');
INSERT INTO `consult_content` VALUES ('115', '?????119', '0', '1', '35');
INSERT INTO `consult_content` VALUES ('115', '?????119', '0', '1', '36');
INSERT INTO `consult_content` VALUES ('110', '我要测试！110', '0', '1', '37');
INSERT INTO `consult_content` VALUES ('88', 'jack老師要測試了！', '0', '1', '38');
INSERT INTO `consult_content` VALUES ('89', 'jack老師要測試了！1', '0', '1', '39');
INSERT INTO `consult_content` VALUES ('89', 'jack老師要測試了！1', '0', '1', '40');

-- ----------------------------
-- Table structure for `consult_contract`
-- ----------------------------
DROP TABLE IF EXISTS `consult_contract`;
CREATE TABLE `consult_contract` (
  `Contract_ID` int(12) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `psptId` varchar(20) NOT NULL,
  `Contract_code` varchar(50) NOT NULL,
  `state` int(1) DEFAULT NULL,
  `activeTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Contract_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=290 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of consult_contract
-- ----------------------------
INSERT INTO `consult_contract` VALUES ('000000000098', '452323197312273728', '105903', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000099', '452323198107061632', '406519', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000100', '452323196312010763', '407433', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000101', '452323197501300748', '204540', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000102', '452323197410102244', '407770', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000103', '452324198309020368', '400154', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000104', '452323196304151988', '408202', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000105', '452324197506281247', '100239', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000106', '452324197105071281', '400489', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000107', '450327198905041218', '403972', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000108', '450324198410253770', '105182', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000109', '452323198001274920', '407020', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000110', '452323196208014924', '403700', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000111', '452324198306250934', '106372', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000112', '452323197612256129', '101336', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000113', '452323197212270714', '402244', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000114', '450324199202023713', '204579', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000115', '452323197011193740', '101941', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000116', '452324196509190346', '104971', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000117', '452323197510022241', '204314', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000118', '452323197909153729', '204150', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000119', '452323198008090083', '407893', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000120', '450324198604074922', '104729', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000121', '452323197309262227', '301287', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000122', '452323197108212223', '407450', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000123', '452324197711161308', '104954', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000124', '452323196205292240', '403729', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000125', '452324197509020341', '400323', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000126', '452324197603070943', '106310', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000127', '431102197210300046', '408188', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000128', '45232419741206038X', '400340', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000129', '452323197108274635', '300417', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000130', '452324197702271521', '203532', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000131', '452324197210061587', '408008', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000132', '452324197411161541', '400341', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000133', '452324197601021566', '400310', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000134', '452324197101250928', '408231', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000135', '452324196502211546', '400858', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000136', '452324197402101563', '105083', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000137', '452324196511201526', '100594', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000138', '45232419651125154X', '203959', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000139', '452324196803191526', '100490', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000140', '45232419750513152X', '300042', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000141', '452324196310151622', '204425', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000142', '452324196111171524', '401110', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000143', '45232419601112152X', '401130', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000144', '452324197907201535', '200121', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000145', '452324196301191534', '200578', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000146', '452324196105011540', '401121', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000147', '452324196209171549', '105359', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000148', '452324197808031526', '200130', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000149', '452324196806261526', '106394', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000150', '452324196810051580', '400619', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000151', '452324196705071547', '400701', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000152', '452324197502131516', '204269', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000153', '452324196310081580', '105360', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000154', '452323197102012589', '204473', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000155', '452324197301161516', '400422', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000156', '452323196305245863', '403561', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000157', '452324196901120342', '105403', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000158', '45232419640202126X', '408106', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000159', '452323197810134966', '401589', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000160', '452323196803140423', '408232', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000161', '452323196301051017', '403634', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000162', '452323197101140802', '204543', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000163', '450324199401173415', '404333', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000164', '450324198803303425', '301098', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000165', '450324198802131959', '106119', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000166', '45232319650104222X', '408034', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000167', '452324198310142020', '204073', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000168', '452324196609061322', '400737', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000169', '452324197107270989', '100353', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000170', '452324196205210352', '408200', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000171', '452323197704174657', '200941', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000172', '45232319651111002X', '106162', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000173', '452323196210202289', '204492', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000174', '450324199012143415', '407395', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000175', '452324196808010317', '204020', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000176', '452324197606020319', '100222', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000177', '452324197812040329', '200125', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000178', '452324197310131829', '400390', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000179', '452324196609132223', '400734', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000180', '452324196102250327', '300182', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000181', '450324198907026514', '202964', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000182', '452323197909183741', '105150', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000183', '452323197906243737', '105156', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000184', '452324197901222212', '400237', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000185', '452323197209224610', '101744', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000186', '452324198005032220', '100156', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000187', '452324197605051228', '100223', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000188', '452324197310070325', '100294', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000189', '452324196712261234', '105566', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000190', '450324198905110413', '300903', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000191', '452324196510290344', '200449', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000192', '452323197909135846', '301167', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000193', '450324199707124915', '301323', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000194', '450325198504051260', '103171', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000195', '45232419641023031X', '105477', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000196', '452324196308060369', '100724', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000197', '452324196206080350', '401072', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000198', '452324196301240375', '106373', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000199', '452324196410010966', '203936', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000200', '452323197506291924', '203841', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000201', '452323197706061621', '203842', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000202', '450324198410295823', '204522', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000203', '452323196406182216', '102644', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000204', '452324196702151218', '100536', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000205', '452323197009050812', '301123', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000206', '452322196709111531', '407790', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000207', '452323198309253149', '407427', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000208', '450324199005062828', '405079', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000209', '452323197003232826', '402604', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000210', '452323196201272824', '403772', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000211', '452323196312072825', '403487', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000212', '45232319720715257X', '201257', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000213', '450324198904143416', '408150', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000214', '452323198311173420', '204426', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000215', '452323196907144947', '408198', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000216', '452323197404011928', '204255', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000217', '452323197904222844', '106308', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000218', '452323197701194038', '300285', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000219', '452323196904072810', '203839', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000220', '452323197103133753', '406964', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000221', '452323197012153767', '101928', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000222', '452323196603012216', '106419', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000223', '450325198905041223', '204051', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000224', '45232319660313079X', '201724', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000225', '452324197212210929', '100319', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000226', '450325199711290322', '204262', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000227', '450325198404222018', '404127', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000228', '452323196308142528', '201921', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000229', '452323196608120726', '201694', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000230', '45232319680822524X', '102209', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000231', '452324197009060654', '407057', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000232', '452323196805061657', '201563', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000233', '452323197309031621', '201176', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000234', '450324198807126147', '203124', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000235', '452323197711243737', '401661', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000236', '450324198712124923', '104498', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000237', '452323198210210739', '100956', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000238', '452323197303171324', '300383', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000239', '45032419890129282X', '105617', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000240', '452323197708102511', '106133', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000241', '450324199409086121', '202326', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000242', '450324199305171938', '106115', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000243', '452323196301080432', '407741', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000244', '452323196311200426', '204235', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000245', '452323196707040449', '106304', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000246', '452323198112033142', '401332', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000247', '452231196307011029', '403909', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000248', '452323197310242522', '402138', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000249', '452323196704033147', '201650', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000250', '450324198708175234', '406680', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000251', '452323198109095238', '401359', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000252', '450324199309223424', '202390', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000253', '45232319730423522X', '101683', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000254', '452323196901042819', '201490', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000255', '45032419900411653X', '103961', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000256', '450324199405084022', '404301', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000257', '452323197910260759', '401487', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000258', '45232319781004221X', '401595', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000259', '452323197501093136', '101512', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000260', '45032419900327320X', '103968', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000261', '450325199209031210', '202186', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000262', '452323196401170048', '201889', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000263', '45232319621201074X', '403656', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000264', '452323198309053120', '200675', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000265', '452323196107070732', '202082', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000266', '452324197409211247', '204305', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000267', '452323198012204629', '401399', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000268', '452323197910190439', '200812', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000269', '452323197212071643', '101715', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000270', '452323196603200807', '102456', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000271', '452323196810102548', '402801', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000272', '452323196909021916', '102106', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000273', '452323196311120725', '403496', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000274', '452323197609035245', '407712', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000275', '452324197106050933', '400486', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000276', '450324198604243458', '104725', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000277', '45232319691203315X', '402640', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000278', '450324198811201912', '203053', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000279', '452323196710202226', '406590', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000280', '450324199312226116', '103330', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000281', '452323196609053158', '408197', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000282', '450324199506292816', '103231', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000283', '45032419950826005X', '103224', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000284', '452323197705020721', '200940', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000285', '452323197410044654', '101540', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000286', '452323196202284640', '105428', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000287', '452323198303305219', '203826', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000288', '452323196609243154', '102409', '0', '2016-08-08 13:28:56');
INSERT INTO `consult_contract` VALUES ('000000000289', '452323196609165264', '102411', '0', '2016-08-08 13:28:56');

-- ----------------------------
-- Table structure for `consult_idcardinfo`
-- ----------------------------
DROP TABLE IF EXISTS `consult_idcardinfo`;
CREATE TABLE `consult_idcardinfo` (
  `psptId` varchar(20) COLLATE utf8_bin DEFAULT '',
  `Name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `Birthday` date DEFAULT NULL,
  `Sex` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `Address` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `activeTime` datetime DEFAULT NULL COMMENT '身份信息表 consult_idcardinfo',
  `innerId` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `picture` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `nation` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`innerId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of consult_idcardinfo
-- ----------------------------
INSERT INTO `consult_idcardinfo` VALUES ('124234', '测试1', '2019-00-00', '0', 'XXXX', '2017-08-19 00:00:00', '0000000001', 'xxxx', '2');
INSERT INTO `consult_idcardinfo` VALUES ('1242344', '测试2', '2019-00-00', '0', 'XXXX', '2017-08-19 00:00:00', '0000000002', 'xxxx', '2');
INSERT INTO `consult_idcardinfo` VALUES ('1242344', '测试2', '2019-00-00', '0', 'XXXX', '2017-08-19 00:00:00', '0000000003', 'xxxx', '2');
INSERT INTO `consult_idcardinfo` VALUES ('1242344', '测试2', '2019-00-00', '0', 'XXXX', '2017-08-19 00:00:00', '0000000004', 'xxxx', '2');
INSERT INTO `consult_idcardinfo` VALUES ('1242344', '测试2', '2019-00-00', '0', 'XXXX', '2017-08-19 00:00:00', '0000000005', 'xxxx', '2');
INSERT INTO `consult_idcardinfo` VALUES ('1242344', '测试2', '2019-00-00', '0', 'XXXX', '2017-08-19 00:00:00', '0000000006', 'xxxx', '2');

-- ----------------------------
-- Table structure for `consult_record`
-- ----------------------------
DROP TABLE IF EXISTS `consult_record`;
CREATE TABLE `consult_record` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `psptId` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '',
  `activeTime` datetime DEFAULT NULL,
  `autograph` varchar(200) COLLATE utf8_bin DEFAULT '' COMMENT 'http://192.168.250.11/upload/image.png,//供浆者签名地址',
  `ispass` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '“TYGJ/ZSJJ/YJJJ”,//同意供浆、暂时拒绝、永久拒绝',
  `docautograph` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT 'http://192.168.250.11/upload/image.png,//医生签名地址',
  `fingerprint` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT 'http://192.168.250.11/upload/image.png,//指纹图片表',
  `remark` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '“fldajfkldsjlkafjsdljf” 备注',
  `print_flag` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of consult_record
-- ----------------------------

-- ----------------------------
-- Table structure for `consult_recordcount`
-- ----------------------------
DROP TABLE IF EXISTS `consult_recordcount`;
CREATE TABLE `consult_recordcount` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `psptId` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '',
  `isproduce` int(100) DEFAULT NULL COMMENT '记录已打印数',
  `unproduce` int(100) DEFAULT NULL COMMENT '记录未打印数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of consult_recordcount
-- ----------------------------

-- ----------------------------
-- Table structure for `module`
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `mname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES ('1', 'add');
INSERT INTO `module` VALUES ('2', 'delete');
INSERT INTO `module` VALUES ('3', 'query');
INSERT INTO `module` VALUES ('4', 'update');

-- ----------------------------
-- Table structure for `module_role`
-- ----------------------------
DROP TABLE IF EXISTS `module_role`;
CREATE TABLE `module_role` (
  `rid` int(11) DEFAULT NULL,
  `mid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module_role
-- ----------------------------
INSERT INTO `module_role` VALUES ('1', '1');
INSERT INTO `module_role` VALUES ('1', '2');
INSERT INTO `module_role` VALUES ('1', '3');
INSERT INTO `module_role` VALUES ('1', '4');
INSERT INTO `module_role` VALUES ('2', '1');
INSERT INTO `module_role` VALUES ('2', '3');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin');
INSERT INTO `role` VALUES ('2', 'customer');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'jack', '123');
INSERT INTO `user` VALUES ('2', 'jeff', '1992');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `uid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('2', '2');

-- ----------------------------
-- Function structure for `fristPinyin`
-- ----------------------------
DROP FUNCTION IF EXISTS `fristPinyin`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `fristPinyin`(P_NAME VARCHAR(255)) RETURNS varchar(255) CHARSET gbk
BEGIN
 
    DECLARE V_RETURN VARCHAR(255);
 
    SET V_RETURN = ELT(INTERVAL(CONV(HEX(left(CONVERT(P_NAME USING gbk),1)),16,10), 
 
        0xB0A1,0xB0C5,0xB2C1,0xB4EE,0xB6EA,0xB7A2,0xB8C1,0xB9FE,0xBBF7, 
 
        0xBFA6,0xC0AC,0xC2E8,0xC4C3,0xC5B6,0xC5BE,0xC6DA,0xC8BB,
 
        0xC8F6,0xCBFA,0xCDDA,0xCEF4,0xD1B9,0xD4D1),    
 
    'A','B','C','D','E','F','G','H','J','K','L','M','N','O','P','Q','R','S','T','W','X','Y','Z');
 
    RETURN V_RETURN;
 
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for `pinyin`
-- ----------------------------
DROP FUNCTION IF EXISTS `pinyin`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `pinyin`(P_NAME VARCHAR(255)) RETURNS varchar(255) CHARSET utf8
BEGIN
    DECLARE V_COMPARE VARCHAR(255);
    DECLARE V_RETURN VARCHAR(255);
    DECLARE I INT;
  
    SET I = 1;
    SET V_RETURN = '';
    while I < LENGTH(P_NAME) do
        SET V_COMPARE = SUBSTR(P_NAME, I, 1);
        IF (V_COMPARE != '') THEN
            #SET V_RETURN = CONCAT(V_RETURN, ',', V_COMPARE);
            SET V_RETURN = CONCAT(V_RETURN, fristPinyin(V_COMPARE));
            #SET V_RETURN = fristPinyin(V_COMPARE);
        END IF;
        SET I = I + 1;
    end while;
    IF (ISNULL(V_RETURN) or V_RETURN = '') THEN
        SET V_RETURN = P_NAME;
    END IF;
    RETURN V_RETURN;
END
;;
DELIMITER ;

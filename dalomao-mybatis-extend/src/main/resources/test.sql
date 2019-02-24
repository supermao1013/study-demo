CREATE TABLE `consult_configarea` (
  `AREACODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `AREANAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `STATE` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `consult_configarea` (`AREACODE`, `AREANAME`, `STATE`) VALUES ('101', '男', '1');
INSERT INTO `consult_configarea` (`AREACODE`, `AREANAME`, `STATE`) VALUES ('102', '女', '1');

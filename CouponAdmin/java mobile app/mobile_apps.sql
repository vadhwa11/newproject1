-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.96-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema mobile_apps
--

CREATE DATABASE IF NOT EXISTS mobile_apps;
USE mobile_apps;

--
-- Definition of table `add_coupon`
--

DROP TABLE IF EXISTS `add_coupon`;
CREATE TABLE `add_coupon` (
  `Id` int(6) unsigned NOT NULL auto_increment,
  `coupan_code` varchar(20) default NULL,
  `flag` int(1) default NULL,
  `Distributor_Name` varchar(20) default NULL,
  `Distributor_id` int(20) default '0',
  `Date` date default NULL,
  `admin_serno` int(10) default NULL,
  `AddBy` varchar(12) default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=1042 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `add_coupon`
--

/*!40000 ALTER TABLE `add_coupon` DISABLE KEYS */;
INSERT INTO `add_coupon` (`Id`,`coupan_code`,`flag`,`Distributor_Name`,`Distributor_id`,`Date`,`admin_serno`,`AddBy`) VALUES 
 (1039,'12356',0,NULL,0,'2017-06-24',NULL,'jitendra'),
 (1040,'1548',0,NULL,0,'2017-06-24',NULL,'jitendra'),
 (1041,'7895qwer',0,NULL,0,'2017-06-24',NULL,'jitendra');
/*!40000 ALTER TABLE `add_coupon` ENABLE KEYS */;


--
-- Definition of table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `serno` int(10) NOT NULL,
  `username` varchar(20) default NULL,
  `password` varchar(12) default NULL,
  `name` varchar(12) default NULL,
  `gmail` varchar(12) default NULL,
  PRIMARY KEY  (`serno`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` (`serno`,`username`,`password`,`name`,`gmail`) VALUES 
 (1,'jitendra123','123','jitendra','gangwar');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;


--
-- Definition of table `coupan_master`
--

DROP TABLE IF EXISTS `coupan_master`;
CREATE TABLE `coupan_master` (
  `vochuer_id` varchar(20) default NULL,
  `gstn_no` varchar(30) default NULL,
  `user_name` varchar(30) default NULL,
  `pwd` varchar(50) default NULL,
  `name` varchar(45) default NULL,
  `ser_no` int(20) NOT NULL auto_increment,
  `email` varchar(45) default NULL,
  `mobile` char(10) default NULL,
  `address` varchar(45) default NULL,
  `pan` varchar(10) default NULL,
  `activate_date` datetime default NULL,
  `flag` char(1) NOT NULL,
  `company_name` varchar(30) default NULL,
  `reference` varchar(10) default NULL,
  `CoupanCode` varchar(20) default NULL,
  PRIMARY KEY  (`ser_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `coupan_master`
--

/*!40000 ALTER TABLE `coupan_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `coupan_master` ENABLE KEYS */;


--
-- Definition of table `distributor`
--

DROP TABLE IF EXISTS `distributor`;
CREATE TABLE `distributor` (
  `Distributor_id` int(10) NOT NULL auto_increment,
  `name` varchar(10) default NULL,
  `email` varchar(20) default NULL,
  `Mobile` varchar(20) default NULL,
  `coupan_count` int(10) default NULL,
  `sell_date` date default NULL,
  `town` varchar(10) default NULL,
  `city` varchar(10) default NULL,
  `state` varchar(10) default NULL,
  `pincode` int(10) default NULL,
  PRIMARY KEY  (`Distributor_id`)
) ENGINE=MyISAM AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `distributor`
--

/*!40000 ALTER TABLE `distributor` DISABLE KEYS */;
INSERT INTO `distributor` (`Distributor_id`,`name`,`email`,`Mobile`,`coupan_count`,`sell_date`,`town`,`city`,`state`,`pincode`) VALUES 
 (19,'jitendra','gangwar','9458547170',5,'2017-06-19','bareilly','bareilly','u.p',243203),
 (20,'jitendra','gangwar','9458547170',5,'2017-06-19','bareilly','bareilly','u.p',243203),
 (21,'jitendra','gangwar','9458547170',5,'2017-06-19','bareilly','bareilly','u.p',243203),
 (22,'jitendra','gangwar','9458547170',5,'2017-06-19','bareilly','bareilly','u.p',243203),
 (23,'jitendra','gangwar','9458547170',5,'2017-06-19','bareilly','bareilly','u.p',243203),
 (24,'jitendra','gangwar','9458547170',5,'2017-06-19','bareilly','bareilly','u.p',243203),
 (25,'jitendra','gangwar','9458547170',15,'2017-06-19','bareilly','bareilly','u.p',243203),
 (26,'jitendra','gangwar','9458547170',15,'2017-06-19','bareilly','bareilly','u.p',243203),
 (27,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (28,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (29,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (30,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (31,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (32,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (33,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (34,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (35,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (36,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (37,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (38,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (39,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (40,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (41,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (42,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (43,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (44,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (45,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (46,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (47,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (48,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (49,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (50,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (51,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (52,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (53,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (54,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (55,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (56,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203),
 (57,'jitendra','gangwar','9458547170',15,'2017-06-20','bareilly','bareilly','u.p',243203);
/*!40000 ALTER TABLE `distributor` ENABLE KEYS */;


--
-- Definition of table `distributor_coupan`
--

DROP TABLE IF EXISTS `distributor_coupan`;
CREATE TABLE `distributor_coupan` (
  `Distributor_coupan_code_id` int(6) NOT NULL auto_increment,
  `coupan_code` varchar(20) default NULL,
  `Distributor_id` int(10) default NULL,
  PRIMARY KEY  (`Distributor_coupan_code_id`)
) ENGINE=MyISAM AUTO_INCREMENT=556 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `distributor_coupan`
--

/*!40000 ALTER TABLE `distributor_coupan` DISABLE KEYS */;
INSERT INTO `distributor_coupan` (`Distributor_coupan_code_id`,`coupan_code`,`Distributor_id`) VALUES 
 (21,'glghhh1',19),
 (22,'ghkihjlok1',19),
 (23,'vfmbv123',19),
 (24,'vfngkij1',19),
 (25,'f12vgk',19),
 (26,'ghkihjlok1',20),
 (27,'vfmbv123',20),
 (28,'glghhh1',20),
 (29,'f12vgk',20),
 (30,'vfngkij1',20),
 (31,'f12vgk',21),
 (32,'glghhh1',21),
 (33,'ghkihjlok1',21),
 (34,'vfngkij1',21),
 (35,'vfmbv123',21),
 (36,'f12vgk',22),
 (37,'ghkihjlok1',22),
 (38,'glghhh1',22),
 (39,'vfmbv123',22),
 (40,'vfngkij1',22),
 (41,'f12vgk',23),
 (42,'vfmbv123',23),
 (43,'glghhh1',23),
 (44,'vfngkij1',23),
 (45,'ghkihjlok1',23),
 (46,'-1673310144',24),
 (47,'1474424877',24),
 (48,'866637220',24),
 (49,'611607228',24),
 (50,'123358490',24),
 (51,'-601127920',24),
 (52,'963826983',24),
 (53,'-601494326',24),
 (54,'575150918',24),
 (55,'-1286161567',24),
 (56,'-2141231330',24),
 (57,'-267611169',24),
 (58,'-1790676638',24),
 (59,'-1826277961',24),
 (60,'-1572349657',24),
 (61,'1671445691',25),
 (62,'838672641',25),
 (63,'587536406',25),
 (64,'-1613311903',25),
 (65,'1323235897',25),
 (66,'37734294',25),
 (67,'-1123134983',25),
 (68,'275356490',25),
 (69,'2030082549',25),
 (70,'-328802272',25),
 (71,'123626845',25),
 (72,'1152005338',25),
 (73,'-1916207749',25),
 (74,'296571268',25),
 (75,'-1787207354',25),
 (76,'-326404606',26),
 (77,'674425384',26),
 (78,'-886881857',26),
 (79,'-1384402274',26),
 (80,'1978755051',26),
 (81,'1124419388',26),
 (82,'992811886',26),
 (83,'-1898999530',26),
 (84,'1765766733',26),
 (85,'971000738',26),
 (86,'-670811965',26),
 (87,'1155551606',26),
 (88,'660099941',26),
 (89,'-691334673',26),
 (90,'-1232070422',26),
 (91,'113609174',27),
 (92,'737041953',27),
 (93,'-1494391070',27),
 (94,'1573175419',27),
 (95,'1990880103',27),
 (96,'1709555177',27),
 (97,'878127309',27),
 (98,'-65844468',27),
 (99,'-99589648',27),
 (100,'2075100449',27),
 (101,'1520931902',27),
 (102,'-1555712638',27),
 (103,'40371977',27),
 (104,'-1430025112',27),
 (105,'-2118064211',27),
 (106,'-1194798248',28),
 (107,'141866192',28),
 (108,'-1323577382',28),
 (109,'-1621680207',28),
 (110,'-1580659099',28),
 (111,'-365778784',28),
 (112,'-423806249',28),
 (113,'1331778710',28),
 (114,'-111853359',28),
 (115,'1183338998',28),
 (116,'-1247767052',28),
 (117,'-565601820',28),
 (118,'-1439524070',28),
 (119,'750898254',28),
 (120,'-868494801',28),
 (121,'-974454760',29),
 (122,'-1241112695',29),
 (123,'-621029491',29),
 (124,'-1754257410',29),
 (125,'1742043602',29),
 (126,'-954931546',29),
 (127,'-571083608',29),
 (128,'43425096',29),
 (129,'982534956',29),
 (130,'192340386',29),
 (131,'-1080868826',29),
 (132,'2004397217',29),
 (133,'1753006250',29),
 (134,'-936970095',29),
 (135,'145434110',29),
 (136,'-754918977',30),
 (137,'488457081',30),
 (138,'-560381782',30),
 (139,'1171875675',30),
 (140,'708836654',30),
 (141,'-809554096',30),
 (142,'1130794631',30),
 (143,'1050745755',30),
 (144,'635864631',30),
 (145,'1683007644',30),
 (146,'-599958179',30),
 (147,'-1489930822',30),
 (148,'1571027154',30),
 (149,'-774677871',30),
 (150,'-679935908',30),
 (151,'-739998949',31),
 (152,'-450895036',31),
 (153,'1156928652',31),
 (154,'2127600340',31),
 (155,'-429965109',31),
 (156,'238930162',31),
 (157,'499070740',31),
 (158,'590774451',31),
 (159,'2066672945',31),
 (160,'1584440924',31),
 (161,'81340326',31),
 (162,'217292484',31),
 (163,'1488461451',31),
 (164,'1845788043',31),
 (165,'1104324421',31),
 (166,'-402930973',32),
 (167,'-654068956',32),
 (168,'1429360062',32),
 (169,'1099637649',32),
 (170,'-447552561',32),
 (171,'2020463235',32),
 (172,'-553755848',32),
 (173,'1000143719',32),
 (174,'2118755633',32),
 (175,'-33155193',32),
 (176,'1022357986',32),
 (177,'995491368',32),
 (178,'-1170444642',32),
 (179,'-1785021371',32),
 (180,'1473919510',32),
 (181,'-2023390907',33),
 (182,'1156243588',33),
 (183,'997398424',33),
 (184,'-706009900',33),
 (185,'-760026883',33),
 (186,'-262942602',33),
 (187,'-1290018096',33),
 (188,'2100015265',33),
 (189,'553333631',33),
 (190,'-369638979',33),
 (191,'-1891628044',33),
 (192,'413059957',33),
 (193,'1193587269',33),
 (194,'2063523179',33),
 (195,'-1783991170',33),
 (196,'-99476211',34),
 (197,'1515650193',34),
 (198,'857450028',34),
 (199,'-1011584203',34),
 (200,'1639693748',34),
 (201,'-252533376',34),
 (202,'925841227',34),
 (203,'1558305115',34),
 (204,'-1263743273',34),
 (205,'1853615830',34),
 (206,'-276299243',34),
 (207,'1798660440',34),
 (208,'64218287',34),
 (209,'-1908741378',34),
 (210,'1427141511',34),
 (211,'-1663793715',35),
 (212,'1632637732',35),
 (213,'-98252481',35),
 (214,'1428597938',35),
 (215,'-1643746177',35),
 (216,'523891062',35),
 (217,'-1731889780',35),
 (218,'-1822303110',35),
 (219,'57841841',35),
 (220,'-744772347',35),
 (221,'-2144453730',35),
 (222,'1663470979',35),
 (223,'-476580223',35),
 (224,'177380559',35),
 (225,'-341549177',35),
 (226,'-1617278662',36),
 (227,'-94837966',36),
 (228,'97091215',36),
 (229,'1424390853',36),
 (230,'-775562654',36),
 (231,'-800857301',36),
 (232,'1570230149',36),
 (233,'1200127658',36),
 (234,'-182827634',36),
 (235,'1364527792',36),
 (236,'146504041',36),
 (237,'-1572333449',36),
 (238,'-733364310',36),
 (239,'-1081266547',36),
 (240,'-6024358',36),
 (241,'582434453',37),
 (242,'-317220120',37),
 (243,'1843367478',37),
 (244,'-1813296481',37),
 (245,'944525560',37),
 (246,'-1222855578',37),
 (247,'-775536103',37),
 (248,'732497281',37),
 (249,'-1425127051',37),
 (250,'1296864242',37),
 (251,'-1414775856',37),
 (252,'1678915015',37),
 (253,'-2016340034',37),
 (254,'-1817414337',37),
 (255,'1566396339',37),
 (256,'-611272961',38),
 (257,'224093063',38),
 (258,'471239537',38),
 (259,'-649882971',38),
 (260,'-1707885121',38),
 (261,'-1761002959',38),
 (262,'-1733141777',38),
 (263,'-1848955944',38),
 (264,'-2042683605',38),
 (265,'-810119391',38),
 (266,'515767499',38),
 (267,'1840451005',38),
 (268,'-277411278',38),
 (269,'1434095504',38),
 (270,'2122481500',38),
 (271,'947764257',39),
 (272,'-1470739721',39),
 (273,'1805408304',39),
 (274,'1869998419',39),
 (275,'-655442991',39),
 (276,'-2109564698',39),
 (277,'-1688244868',39),
 (278,'2004135956',39),
 (279,'-1365311219',39),
 (280,'-1920319639',39),
 (281,'1822900550',39),
 (282,'-1046453812',39),
 (283,'-29382966',39),
 (284,'1436566594',39),
 (285,'-913238056',39),
 (286,'2122059869',40),
 (287,'1488574069',40),
 (288,'132819900',40),
 (289,'847944491',40),
 (290,'910782416',40),
 (291,'-948703126',40),
 (292,'-197843694',40),
 (293,'-786840085',40),
 (294,'1679963150',40),
 (295,'-1825761111',40),
 (296,'-1609261732',40),
 (297,'2080966092',40),
 (298,'635025324',40),
 (299,'1879442657',40),
 (300,'126565629',40),
 (301,'-320169569',41),
 (302,'-894877363',41),
 (303,'-1774857349',41),
 (304,'1688720835',41),
 (305,'2054945398',41),
 (306,'-1220617415',41),
 (307,'752996535',41),
 (308,'-865172786',41),
 (309,'-853084186',41),
 (310,'1529600440',41),
 (311,'-1012186617',41),
 (312,'216032906',41),
 (313,'-2077271480',41),
 (314,'-367002203',41),
 (315,'946663707',41),
 (316,'-1436651587',42),
 (317,'909565570',42),
 (318,'1863601414',42),
 (319,'-6496506',42),
 (320,'-975725377',42),
 (321,'-1933475451',42),
 (322,'-2106819966',42),
 (323,'1343897352',42),
 (324,'520007675',42),
 (325,'2042827785',42),
 (326,'755904195',42),
 (327,'-1831694666',42),
 (328,'-1967168503',42),
 (329,'-56951562',42),
 (330,'-1778866259',42),
 (331,'-276828937',43),
 (332,'-236170258',43),
 (333,'-41921539',43),
 (334,'-25138341',43),
 (335,'-283916217',43),
 (336,'987431154',43),
 (337,'971404104',43),
 (338,'-62147207',43),
 (339,'478257908',43),
 (340,'-350231761',43),
 (341,'1872386297',43),
 (342,'797704693',43),
 (343,'309429106',43),
 (344,'-815836435',43),
 (345,'-1762491064',43),
 (346,'-954587433',44),
 (347,'1791907043',44),
 (348,'-521949044',44),
 (349,'-905363075',44),
 (350,'-2105970068',44),
 (351,'-1226683380',44),
 (352,'-564583714',44),
 (353,'150454648',44),
 (354,'233144701',44),
 (355,'1249804854',44),
 (356,'1543914593',44),
 (357,'-110953852',44),
 (358,'-641500401',44),
 (359,'1224110047',44),
 (360,'-1246425535',44),
 (361,'608236713',45),
 (362,'-588211818',45),
 (363,'-1718112167',45),
 (364,'-1970039074',45),
 (365,'-1461394229',45),
 (366,'1254637857',45),
 (367,'1952256603',45),
 (368,'-1414189221',45),
 (369,'-2104097899',45),
 (370,'-1542508341',45),
 (371,'-1945914711',45),
 (372,'322625586',45),
 (373,'1113489319',45),
 (374,'-1087287826',45),
 (375,'1374757793',45),
 (376,'-677525724',46),
 (377,'-1355724848',46),
 (378,'59342013',46),
 (379,'-1316328737',46),
 (380,'1302719809',46),
 (381,'-90479655',46),
 (382,'21047833',46),
 (383,'-901818656',46),
 (384,'-1763631590',46),
 (385,'2118642016',46),
 (386,'120224993',46),
 (387,'750102250',46),
 (388,'-1494781588',46),
 (389,'843188301',46),
 (390,'577653080',46),
 (391,'-79998240',47),
 (392,'2127008624',47),
 (393,'-1056889470',47),
 (394,'1751257408',47),
 (395,'-780049895',47),
 (396,'1120350740',47),
 (397,'1019713750',47),
 (398,'1798261363',47),
 (399,'314127254',47),
 (400,'616172778',47),
 (401,'2145909287',47),
 (402,'1369567601',47),
 (403,'866122025',47),
 (404,'-999805101',47),
 (405,'-1300481267',47),
 (406,'-1808755103',48),
 (407,'196149518',48),
 (408,'-358812934',48),
 (409,'767264701',48),
 (410,'-1455712981',48),
 (411,'-22461019',48),
 (412,'-2038373860',48),
 (413,'-1917836900',48),
 (414,'26331186',48),
 (415,'-1552541080',48),
 (416,'872607265',48),
 (417,'638772724',48),
 (418,'-920487851',48),
 (419,'-1713808244',48),
 (420,'-14484357',48),
 (421,'178708584',49),
 (422,'-231538046',49),
 (423,'-218725844',49),
 (424,'-1139582053',49),
 (425,'600006629',49),
 (426,'1051526085',49),
 (427,'1464540433',49),
 (428,'769650755',49),
 (429,'1615290495',49),
 (430,'1252426060',49),
 (431,'-769289093',49),
 (432,'586409182',49),
 (433,'-1095985153',49),
 (434,'-1612155464',49),
 (435,'247821403',49),
 (436,'138840937',50),
 (437,'780107861',50),
 (438,'-782056290',50),
 (439,'1341550914',50),
 (440,'2076796327',50),
 (441,'1285874006',50),
 (442,'-1503558039',50),
 (443,'-1006006614',50),
 (444,'-1491802743',50),
 (445,'-365908763',50),
 (446,'-893394038',50),
 (447,'1476389362',50),
 (448,'2030196536',50),
 (449,'-937589990',50),
 (450,'-1084733510',50),
 (451,'832195125',51),
 (452,'1815600456',51),
 (453,'869294342',51),
 (454,'1507101419',51),
 (455,'1441875193',51),
 (456,'1684640825',51),
 (457,'1151997723',51),
 (458,'-608326135',51),
 (459,'1806117241',51),
 (460,'-849304903',51),
 (461,'1713718423',51),
 (462,'-108662761',51),
 (463,'-1257484435',51),
 (464,'1208498014',51),
 (465,'621700',51),
 (466,'1220393294',52),
 (467,'483597127',52),
 (468,'-1120443896',52),
 (469,'-1044825233',52),
 (470,'-1830482210',52),
 (471,'-1335682492',52),
 (472,'726490551',52),
 (473,'1852062281',52),
 (474,'-1605557686',52),
 (475,'-450039464',52),
 (476,'1110997079',52),
 (477,'-1507320468',52),
 (478,'-683748835',52),
 (479,'1668875533',52),
 (480,'1578879755',52),
 (481,'-1187259234',53),
 (482,'-1711295568',53),
 (483,'616809819',53),
 (484,'-1364608466',53),
 (485,'1970446451',53),
 (486,'570444929',53),
 (487,'-1077308725',53),
 (488,'-1343712146',53),
 (489,'-1560784726',53),
 (490,'-1083625967',53),
 (491,'500755429',53),
 (492,'341642194',53),
 (493,'24313406',53),
 (494,'235287563',53),
 (495,'1207659380',53),
 (496,'-1792197570',54),
 (497,'262456638',54),
 (498,'-1660122858',54),
 (499,'1658494408',54),
 (500,'-1734088448',54),
 (501,'-1206363560',54),
 (502,'-294737061',54),
 (503,'-1007227938',54),
 (504,'-821549221',54),
 (505,'84310090',54),
 (506,'725108435',54),
 (507,'1248592034',54),
 (508,'1524146138',54),
 (509,'1463405751',54),
 (510,'-195915791',54),
 (511,'31993706',55),
 (512,'-1905541865',55),
 (513,'686948723',55),
 (514,'-557615815',55),
 (515,'-938890368',55),
 (516,'1218505763',55),
 (517,'358647480',55),
 (518,'1230996229',55),
 (519,'635758469',55),
 (520,'-1274197817',55),
 (521,'1326348317',55),
 (522,'-1581733190',55),
 (523,'-1394227445',55),
 (524,'476605586',55),
 (525,'-745396589',55),
 (526,'-931526007',56),
 (527,'-6756947',56),
 (528,'1145561662',56),
 (529,'1229883803',56),
 (530,'1934966110',56),
 (531,'716011995',56),
 (532,'1359569235',56),
 (533,'1214307684',56),
 (534,'1160269954',56),
 (535,'-11814230',56),
 (536,'2070731840',56),
 (537,'-93555198',56),
 (538,'-879691881',56),
 (539,'494140361',56),
 (540,'1808691718',56),
 (541,'685876109',57),
 (542,'-439179724',57),
 (543,'2060646214',57),
 (544,'1574890005',57),
 (545,'1088968853',57),
 (546,'-1942833020',57),
 (547,'312637570',57),
 (548,'880219886',57),
 (549,'902039287',57),
 (550,'1298002955',57),
 (551,'970957399',57),
 (552,'1119202355',57),
 (553,'-1677805056',57),
 (554,'1383755574',57),
 (555,'837368973',57);
/*!40000 ALTER TABLE `distributor_coupan` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

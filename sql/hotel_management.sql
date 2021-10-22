DROP TABLE IF EXISTS `member_type`;
CREATE TABLE `member_type`  (
  `member_type_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '会员类型编号',
  `member_type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '会员类型名称',
  `member_type_discount` float NOT NULL COMMENT '会员类型折扣',
  PRIMARY KEY (`member_type_id`) USING BTREE,
  UNIQUE INDEX `ix_member_type_name`(`member_type_name`) USING BTREE COMMENT '会员类型名称索引'
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会员类型' ROW_FORMAT = Dynamic;

INSERT INTO `member_type` VALUES (1, '普通', 0.95);
INSERT INTO `member_type` VALUES (2, '白银', 0.9);
INSERT INTO `member_type` VALUES (3, '黄金', 0.85);
INSERT INTO `member_type` VALUES (4, '白金', 0.8);
INSERT INTO `member_type` VALUES (5, '钻石', 0.75);

DROP TABLE IF EXISTS `room_type`;
CREATE TABLE `room_type`  (
  `room_type_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '房间类型编号',
  `room_type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '房间类型名称',
  PRIMARY KEY (`room_type_id`) USING BTREE,
  UNIQUE INDEX `ix_room_type_name`(`room_type_name`) USING BTREE COMMENT '房间类型名称索引'
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '房间类型' ROW_FORMAT = DYNAMIC;

INSERT INTO `room_type` VALUES (2, '大床房');
INSERT INTO `room_type` VALUES (5, '总统套房');
INSERT INTO `room_type` VALUES (4, '景观房');
INSERT INTO `room_type` VALUES (1, '标间');
INSERT INTO `room_type` VALUES (3, '豪华套房');

DROP TABLE IF EXISTS `user_type`;
CREATE TABLE `user_type`  (
  `user_type_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户类型编号',
  `user_type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户类型名称',
  PRIMARY KEY (`user_type_id`) USING BTREE,
  UNIQUE INDEX `ix_user_type_name`(`user_type_name`) USING BTREE COMMENT '用户类型名称索引'
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户类型' ROW_FORMAT = DYNAMIC;

INSERT INTO `user_type` VALUES (2, '前台');
INSERT INTO `user_type` VALUES (1, '系统管理员');
INSERT INTO `user_type` VALUES (3, '财务');

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `user_real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户真实姓名',
  `user_type_id` int(11) UNSIGNED NOT NULL COMMENT '用户类型编号',
  `user_age` int(11) NOT NULL COMMENT '用户年龄',
  `user_gender` bit(1) NOT NULL COMMENT '用户性别',
  `user_password_hash` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码哈希值',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `fk_user_user_type_id_user_type_user_type_id`(`user_type_id`) USING BTREE,
  CONSTRAINT `fk_user_user_type_id_user_type_user_type_id` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`user_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户' ROW_FORMAT = DYNAMIC;

INSERT INTO `user` VALUES (1, 'admin', 'aoba', 1, 17, b'1', 'C4CA4238A0B923820DCC509A6F75849B');

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `customer_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '客户编号',
  `customer_id_number` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户身份证号',
  `customer_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户姓名',
  `customer_birth_date` date NOT NULL COMMENT '客户生日',
  `customer_gender` bit(1) NOT NULL COMMENT '客户性别',
  `customer_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户手机',
  `customer_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户邮箱',
  `customer_member_type_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '客户会员类型',
  PRIMARY KEY (`customer_id`) USING BTREE,
  UNIQUE INDEX `ix_customer_phone`(`customer_phone`) USING BTREE COMMENT '客户手机索引',
  INDEX `ix_customer_name`(`customer_name`) USING BTREE COMMENT '客户名称索引',
  INDEX `fk_customer_customer_member_type_id_member_member_type_id`(`customer_member_type_id`) USING BTREE,
  CONSTRAINT `fk_customer_customer_member_type_id_member_member_type_id` FOREIGN KEY (`customer_member_type_id`) REFERENCES `member_type` (`member_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `room_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '房间编号',
  `room_level` int(11) NOT NULL COMMENT '房间楼层号',
  `room_number` int(11) NOT NULL COMMENT '房间号',
  `room_price` decimal(10, 2) UNSIGNED NOT NULL COMMENT '房间价格',
  `room_type_id` int(11) UNSIGNED NOT NULL COMMENT '房间类型编号',
  PRIMARY KEY (`room_id`) USING BTREE,
  INDEX `fk_room_room_type_id_room_type_room_type_id`(`room_type_id`) USING BTREE,
  CONSTRAINT `fk_room_room_type_id_room_type_room_type_id` FOREIGN KEY (`room_type_id`) REFERENCES `room_type` (`room_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '房间' ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `room_price_history`;
CREATE TABLE `room_price_history`  (
  `room_id` int(10) UNSIGNED NOT NULL COMMENT '房间编号',
  `room_previous_price` decimal(10, 2) NOT NULL COMMENT '房间修改前价格',
  `room_now_price` decimal(10, 2) NOT NULL COMMENT '房间修改后价格',
  `room_price_changed_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '房间价格变更时间',
  `room_price_changed_user_id` int(10) UNSIGNED NOT NULL COMMENT '房间变更操作人编号',
  PRIMARY KEY (`room_id`) USING BTREE,
  INDEX `fk_room_price_history_room_price_changed_user_id_user_user_id`(`room_price_changed_user_id`) USING BTREE,
  CONSTRAINT `fk_room_price_history_room_id_room_room_id` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_room_price_history_room_price_changed_user_id_user_user_id` FOREIGN KEY (`room_price_changed_user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '房间价格历史' ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `book_id` bigint(20) UNSIGNED NOT NULL COMMENT '订房编号',
  `book_customer_id` int(10) UNSIGNED NOT NULL COMMENT '订房顾客编号',
  `book_user_id` int(10) UNSIGNED NOT NULL COMMENT '订房操作用户编号',
  `book_created_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '订房时间',
  `book_start_time` datetime NOT NULL COMMENT '订房起始时间',
  `book_end_time` datetime NOT NULL COMMENT '订房结束时间',
  `book_is_checked_in` bit(1) NOT NULL COMMENT '订房入住',
  PRIMARY KEY (`book_id`) USING BTREE,
  INDEX `fk_book_book_customer_id_customer_customer_id`(`book_customer_id`) USING BTREE,
  INDEX `fk_book_book_user_id_user_user_id`(`book_user_id`) USING BTREE,
  CONSTRAINT `fk_book_book_customer_id_customer_customer_id` FOREIGN KEY (`book_customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_book_book_user_id_user_user_id` FOREIGN KEY (`book_user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订房' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `room_current`;
CREATE TABLE `room_current`  (
  `room_current_id` bigint(20) UNSIGNED NOT NULL COMMENT '房间入住编号',
  `room_current_start_time` datetime NOT NULL COMMENT '房间入住起始时间',
  `room_current_end_time` datetime NULL DEFAULT NULL COMMENT '房间入住结束时间',
  `room_current_book_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '订房编号',
  `room_current_customer_id` int(10) UNSIGNED NOT NULL COMMENT '房间入住客户编号',
  `room_current_created_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '房间入住操作时间',
  `room_current_user_id` int(11) UNSIGNED NOT NULL COMMENT '房间入住操作人',
  PRIMARY KEY (`room_current_id`) USING BTREE,
  INDEX `fk_room_current_room_current_book_id_book_book_id`(`room_current_book_id`) USING BTREE,
  INDEX `fk_room_current_room_current_user_id_user_user_id`(`room_current_user_id`) USING BTREE,
  INDEX `fk_room_current_room_current_customer_id_customer_customer_id`(`room_current_customer_id`) USING BTREE,
  CONSTRAINT `fk_room_current_room_current_book_id_book_book_id` FOREIGN KEY (`room_current_book_id`) REFERENCES `book` (`book_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_room_current_room_current_customer_id_customer_customer_id` FOREIGN KEY (`room_current_customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_room_current_room_current_user_id_user_user_id` FOREIGN KEY (`room_current_user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '房间入住' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `room_check_out`;
CREATE TABLE `room_check_out`  (
  `room_check_out_id` bigint(20) UNSIGNED NOT NULL COMMENT '退房编号',
  `room_check_out_time` datetime NOT NULL COMMENT '退房时间',
  `room_check_out_remain_price` decimal(10, 2) NOT NULL COMMENT '退房应付价格',
  `room_check_out_user_id` int(10) UNSIGNED NOT NULL COMMENT '退房操作用户编号',
  `room_check_out_created_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '退房操作时间',
  `room_check_out_room_current_id` bigint(20) UNSIGNED NOT NULL COMMENT '退房入住信息编号',
  PRIMARY KEY (`room_check_out_id`) USING BTREE,
  INDEX `fk_room_check_out_room_check_out_user_id_user_user_id`(`room_check_out_user_id`) USING BTREE,
  INDEX `fk_room_check_out_room_check_out_room_current_room_current_id`(`room_check_out_room_current_id`) USING BTREE,
  CONSTRAINT `fk_room_check_out_room_check_out_room_current_room_current_id` FOREIGN KEY (`room_check_out_room_current_id`) REFERENCES `room_current` (`room_current_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_room_check_out_room_check_out_user_id_user_user_id` FOREIGN KEY (`room_check_out_user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '退房' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `charge`;
CREATE TABLE `charge`  (
  `charge_id` bigint(20) UNSIGNED NOT NULL COMMENT '费用编号',
  `charge_room_current_id` bigint(20) UNSIGNED NOT NULL COMMENT '费用房间入住编号',
  `charge_customer_id` int(10) UNSIGNED NOT NULL COMMENT '费用客户编号',
  `charge_price` decimal(10, 2) NOT NULL COMMENT '费用金额',
  `charge_summary` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '费用摘要',
  `charge_comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '费用备注',
  PRIMARY KEY (`charge_id`) USING BTREE,
  INDEX `fk_charge_charge_room_current_id_room_current_room_current_id`(`charge_room_current_id`) USING BTREE,
  INDEX `fk_charge_charge_customer_id_customer_customer_id`(`charge_customer_id`) USING BTREE,
  CONSTRAINT `fk_charge_charge_customer_id_customer_customer_id` FOREIGN KEY (`charge_customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_charge_charge_room_current_id_room_current_room_current_id` FOREIGN KEY (`charge_room_current_id`) REFERENCES `room_current` (`room_current_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '费用' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `deposit`;
CREATE TABLE `deposit`  (
  `deposit_id` bigint(20) UNSIGNED NOT NULL COMMENT '订金编号',
  `deposit_customer_id` int(10) UNSIGNED NOT NULL COMMENT '订金交款客户编号',
  `deposit_book_id` bigint(20) UNSIGNED NOT NULL COMMENT '订金预定编号',
  `deposit_price` decimal(10, 2) NOT NULL COMMENT '订金价格',
  `deposit_created_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '订金创建时间',
  `deposit_user_id` int(10) UNSIGNED NOT NULL COMMENT '订金操作员编号',
  PRIMARY KEY (`deposit_id`) USING BTREE,
  INDEX `fk_deposit_deposit_customer_id_customer_customer_id`(`deposit_customer_id`) USING BTREE,
  INDEX `fk_deposit_deposit_book_id_book_book_id`(`deposit_book_id`) USING BTREE,
  INDEX `fk_deposit_deposit_user_id_user_user_id`(`deposit_user_id`) USING BTREE,
  CONSTRAINT `fk_deposit_deposit_book_id_book_book_id` FOREIGN KEY (`deposit_book_id`) REFERENCES `book` (`book_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_deposit_deposit_customer_id_customer_customer_id` FOREIGN KEY (`deposit_customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_deposit_deposit_user_id_user_user_id` FOREIGN KEY (`deposit_user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订金' ROW_FORMAT = Dynamic;
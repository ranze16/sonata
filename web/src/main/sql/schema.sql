
-- 创建用户信息表
CREATE TABLE user_info (
id bigint NOT NULL,
gmt_create datetime DEFAULT CURRENT_TIMESTAMP,
gmt_modified datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
nick_name varchar(30) NOT NULL COMMENT '昵称',
sex enum('male', 'female') COMMENT '性别',
birth_date datetime COMMENT '出生日期',
cell_phone_num char(15) NOT NULL COMMENT '手机号',
password varchar(30) COMMENT '密码',
living_country varchar(20) COMMENT '常住地国家',
living_province varchar(20) COMMENT '常住地省份',
living_city varchar(20) COMMENT '常住地城市',
signature varchar(50) COMMENT '个性签名',
head_portrait blob COMMENT '头像',
PRIMARY KEY (id),
UNIQUE KEY uk_cell_phone_num (cell_phone_num),
KEY idx_gmt_create (gmt_create),
KEY idx_birth_date (birth_date)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';
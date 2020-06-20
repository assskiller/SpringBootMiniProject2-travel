
-- 用户表
CREATE table t_user(
	id int(6) PRIMARY KEY auto_increment,
	username VARCHAR(60),
	password varchar(60),
	email varchar(60)
);


-- 省份表
CREATE table t_province(
	id int(6) PRIMARY KEY auto_increment,
	name VARCHAR(60),
	tags VARCHAR(60),
	placecounts int(4)
);


-- 景点表
CREATE table t_places(
	id int(6) PRIMARY KEY auto_increment,
	name VARCHAR(60),
	picpath VARCHAR(69),
	hottime TIMESTAMP,
	hotticket DOUBLE(7,2), -- 旺季门票
	dimticket DOUBLE(7,2), -- 淡季门票
	placedes VARCHAR(30),
	provinceid int(6) REFERENCES t_province(id)
);
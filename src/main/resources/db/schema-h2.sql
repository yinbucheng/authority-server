CREATE TABLE yinchong.t_user
(
	id BIGINT(20) auto_increment primary key COMMENT '主键ID',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	nick_name varchar(20) DEFAULT NULL,
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	`create_time` datetime,
	`delete_time` datetime,
	gender varchar(10),
	password varchar(255),
	remark varchar(255)
);

create table yinchong.t_project(
id bigint(20) auto_increment primary key ,
name varchar(20),
create_time datetime,
delete_time datetime,
parent_id bigint(20)
);

create table yinchong.t_role(
id bigint(20) auto_increment primary key ,
name varchar(20),
create_time datetime,
delete_time datetime
);

create table yinchong.t_mapping(
id bigint(20) auto_increment primary key ,
name varchar(20),
create_time datetime,
delete_time datetime,
project_id bigint(2) not null,
method varchar(25) default "*",
remark varchar(255),
url varchar(255)
);

create table yinchong.t_project_mapping(
id bigint(20) auto_increment primary key ,
project_id bigint(20) not null,
mapping_id bigint(20) not null,
create_time datetime,
delete_time datetime
);

create table yinchong.t_role_mapping(
id bigint(20) auto_increment primary key ,
role_id bigint(20) not null,
mapping_id bigint(20) not null,
create_time datetime,
delete_time datetime
);

create table yinchong.t_user_role(
id bigint(20) auto_increment primary key ,
user_id bigint(20) not null,
role_id bigint(20) not null,
create_time datetime,
delete_time datetime
)
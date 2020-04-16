## Mysql-创建导航

**直接复制使用**

```mysql
--用户表

CREATE TABLE `user` (
	`id_main` INT ( 11 ) NOT NULL AUTO_INCREMENT,
	`id_user` VARCHAR ( 20 ) NOT NULL COMMENT '用户id',
	`s_username` VARCHAR ( 50 ) NOT NULL COMMENT '用户名',
	`s_password` VARCHAR ( 50 ) NOT NULL,
	`s_salt` VARCHAR ( 128 ) DEFAULT NULL COMMENT '加密盐值',
	`s_email` VARCHAR ( 50 ) DEFAULT NULL COMMENT '邮箱',
	`s_phone` VARCHAR ( 50 ) DEFAULT NULL COMMENT '联系方式',
	`i_sex` INT ( 6 ) DEFAULT NULL COMMENT '年龄：1男2女',
	`i_age` INT ( 3 ) DEFAULT NULL COMMENT '年龄',
	`s_create_time` datetime DEFAULT NULL COMMENT '创建时间',
	`s_last_login` datetime DEFAULT NULL COMMENT '最后登录时间',
	`i_locked` SMALLINT ( 6 ) NOT NULL DEFAULT '0' COMMENT '是否锁定：0无1有',
	`i_black` SMALLINT ( 6 ) DEFAULT NULL COMMENT '是否被拉黑:0无1有',
	PRIMARY KEY ( `id_main`, `id_user` ) USING BTREE,
	UNIQUE KEY `s_username` ( `s_username` ) 
) ENGINE = INNODB AUTO_INCREMENT = 26 DEFAULT CHARSET = utf8;

--角色表
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `role` (
	`id_main` INT ( 11 ) NOT NULL AUTO_INCREMENT,
	`id_role` VARCHAR ( 20 ) NOT NULL COMMENT '角色id',
	`s_name` VARCHAR ( 50 ) NOT NULL COMMENT '角色名称',
	`s_description` VARCHAR ( 255 ) DEFAULT NULL COMMENT '角色描述',
	`i_status` INT ( 1 ) NOT NULL COMMENT '状态：1有效；2删除',
	`s_create_time` datetime DEFAULT NULL COMMENT '创建时间',
	`s_update_time` datetime DEFAULT NULL COMMENT '更新时间',
	PRIMARY KEY ( `id_main` ) 
) ENGINE = INNODB AUTO_INCREMENT = 7 DEFAULT CHARSET = utf8;

--权限表

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `perm` (
	`id_main` INT ( 11 ) NOT NULL AUTO_INCREMENT,
	`id_perm` VARCHAR ( 20 ) NOT NULL COMMENT '权限id',
	`s_name` VARCHAR ( 100 ) NOT NULL COMMENT '权限名称',
	`s_description` VARCHAR ( 255 ) DEFAULT NULL COMMENT '权限描述',
	`s_url` VARCHAR ( 255 ) DEFAULT NULL COMMENT '权限访问路径',
	`s_perms` VARCHAR ( 255 ) DEFAULT NULL COMMENT '权限标识',
	`i_type` INT ( 1 ) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
	`i_order_num` INT ( 3 ) DEFAULT '0' COMMENT '排序',
	`s_icon` VARCHAR ( 50 ) DEFAULT NULL COMMENT '图标',
	`i_status` INT ( 1 ) NOT NULL COMMENT '状态：1有效；2删除',
	`s_create_time` datetime DEFAULT NULL,
	PRIMARY KEY ( `id_main` ) USING BTREE 
) ENGINE = INNODB AUTO_INCREMENT = 35 DEFAULT CHARSET = utf8;


--中间表1
SET FOREIGN_KEY_CHECKS = 0;
CREATE TABLE `user_role` (
	`id_main` INT ( 11 ) NOT NULL AUTO_INCREMENT,
	`id_user` VARCHAR ( 20 ) NOT NULL COMMENT '用户id',
	`id_role` VARCHAR ( 20 ) NOT NULL COMMENT '角色id',
	PRIMARY KEY ( `id_main` ) 
) ENGINE = INNODB AUTO_INCREMENT = 5 DEFAULT CHARSET = utf8;


--中间表2
SET FOREIGN_KEY_CHECKS = 0;
CREATE TABLE `role_perm` (
	`id_main` INT ( 11 ) NOT NULL AUTO_INCREMENT,
	`id_role` VARCHAR ( 20 ) NOT NULL COMMENT '角色id',
	`id_perm` VARCHAR ( 20 ) NOT NULL COMMENT '权限id',
PRIMARY KEY ( `id_main` ) 
) ENGINE = INNODB AUTO_INCREMENT = 872 DEFAULT CHARSET = utf8;
```





### 用户表创建



#### User-SQL语句

```mysql

CREATE TABLE `user` (
	`id_main` INT ( 11 ) NOT NULL AUTO_INCREMENT,
	`id_user` VARCHAR ( 20 ) NOT NULL COMMENT '用户id',
	`s_username` VARCHAR ( 50 ) NOT NULL COMMENT '用户名',
	`s_password` VARCHAR ( 50 ) NOT NULL,
	`s_salt` VARCHAR ( 128 ) DEFAULT NULL COMMENT '加密盐值',
	`s_email` VARCHAR ( 50 ) DEFAULT NULL COMMENT '邮箱',
	`s_phone` VARCHAR ( 50 ) DEFAULT NULL COMMENT '联系方式',
	`i_sex` INT ( 6 ) DEFAULT NULL COMMENT '年龄：1男2女',
	`i_age` INT ( 3 ) DEFAULT NULL COMMENT '年龄',
	`s_create_time` datetime DEFAULT NULL COMMENT '创建时间',
	`s_last_login` datetime DEFAULT NULL COMMENT '最后登录时间',
	`i_locked` SMALLINT ( 6 ) NOT NULL DEFAULT '0' COMMENT '是否锁定：0无1有',
	`i_black` SMALLINT ( 6 ) DEFAULT NULL COMMENT '是否被拉黑:0无1有',
	PRIMARY KEY ( `id_main`, `id_user` ) USING BTREE,
	UNIQUE KEY `s_username` ( `s_username` ) 
) ENGINE = INNODB AUTO_INCREMENT = 26 DEFAULT CHARSET = utf8;

```

### 角色表



#### Role-SQL语句

```mysql
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `role` (
	`id_main` INT ( 11 ) NOT NULL AUTO_INCREMENT,
	`id_role` VARCHAR ( 20 ) NOT NULL COMMENT '角色id',
	`s_name` VARCHAR ( 50 ) NOT NULL COMMENT '角色名称',
	`s_description` VARCHAR ( 255 ) DEFAULT NULL COMMENT '角色描述',
	`i_status` INT ( 1 ) NOT NULL COMMENT '状态：1有效；2删除',
	`s_create_time` datetime DEFAULT NULL COMMENT '创建时间',
	`s_update_time` datetime DEFAULT NULL COMMENT '更新时间',
	PRIMARY KEY ( `id_main` ) 
) ENGINE = INNODB AUTO_INCREMENT = 7 DEFAULT CHARSET = utf8;


```





### 权限表

#### Perms-SQL

```MYSQL

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `perm` (
	`id_main` INT ( 11 ) NOT NULL AUTO_INCREMENT,
	`id_perm` VARCHAR ( 20 ) NOT NULL COMMENT '权限id',
	`s_name` VARCHAR ( 100 ) NOT NULL COMMENT '权限名称',
	`s_description` VARCHAR ( 255 ) DEFAULT NULL COMMENT '权限描述',
	`s_url` VARCHAR ( 255 ) DEFAULT NULL COMMENT '权限访问路径',
	`s_perms` VARCHAR ( 255 ) DEFAULT NULL COMMENT '权限标识',
	`i_type` INT ( 1 ) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
	`i_order_num` INT ( 3 ) DEFAULT '0' COMMENT '排序',
	`s_icon` VARCHAR ( 50 ) DEFAULT NULL COMMENT '图标',
	`i_status` INT ( 1 ) NOT NULL COMMENT '状态：1有效；2删除',
	`s_create_time` datetime DEFAULT NULL,
	PRIMARY KEY ( `id_main` ) USING BTREE 
) ENGINE = INNODB AUTO_INCREMENT = 35 DEFAULT CHARSET = utf8;

```

### 中间表

#### User-Role

```mysql
SET FOREIGN_KEY_CHECKS = 0;
CREATE TABLE `user_role` (
	`id_main` INT ( 11 ) NOT NULL AUTO_INCREMENT,
	`id_user` VARCHAR ( 20 ) NOT NULL COMMENT '用户id',
	`id_role` VARCHAR ( 20 ) NOT NULL COMMENT '角色id',
	PRIMARY KEY ( `id_main` ) 
) ENGINE = INNODB AUTO_INCREMENT = 5 DEFAULT CHARSET = utf8;

```

#### Role-Perms

```MYSQL
SET FOREIGN_KEY_CHECKS = 0;
CREATE TABLE `role_perm` (
	`id_main` INT ( 11 ) NOT NULL AUTO_INCREMENT,
	`id_role` VARCHAR ( 20 ) NOT NULL COMMENT '角色id',
	`id_perm` VARCHAR ( 20 ) NOT NULL COMMENT '权限id',
PRIMARY KEY ( `id_main` ) 
) ENGINE = INNODB AUTO_INCREMENT = 872 DEFAULT CHARSET = utf8;
```


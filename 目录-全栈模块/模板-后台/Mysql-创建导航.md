# Mysql-创建导航



### 用户表创建

#### 字段说明

|   字段名   |       意义       |
| :--------: | :--------------: |
|   mainId   |       主键       |
|   userId   |   外键映射标识   |
|  username  |      用户名      |
|  password  |       密码       |
|    salt    |       盐值       |
|   email    |       邮箱       |
|   phone    |       手机       |
|    sex     |       性别       |
|    age     |       年龄       |
| createTime |   用户注册时间   |
| lastLogin  | 最后一次登录时间 |
|   locked   |     是否锁定     |
|   black    |    是否被禁用    |
|  headImg   |       头像       |

#### User-SQL语句

```mysql
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE
IF
	EXISTS `biu`.`user`;
CREATE TABLE `user` (
	`mainId` INT ( 11 ) NOT NULL AUTO_INCREMENT,
	`userId` VARCHAR ( 20 ) NOT NULL COMMENT '用户id',
	`username` VARCHAR ( 50 ) NOT NULL COMMENT '用户名',
	`password` VARCHAR ( 50 ) NOT NULL,
	`salt` VARCHAR ( 128 ) DEFAULT NULL COMMENT '加密盐值',
	`email` VARCHAR ( 50 ) DEFAULT NULL COMMENT '邮箱',
	`phone` VARCHAR ( 50 ) DEFAULT NULL COMMENT '联系方式',
	`sex` INT ( 6 ) DEFAULT NULL COMMENT '年龄：1男2女',
	`age` INT ( 3 ) DEFAULT NULL COMMENT '年龄',
	`createTime` datetime DEFAULT NULL COMMENT '创建时间',
	`lastLogin` datetime DEFAULT NULL COMMENT '最后登录时间',
	`locked` SMALLINT ( 6 ) NOT NULL DEFAULT '0' COMMENT '是否锁定：0无1有',
	`black` SMALLINT ( 6 ) DEFAULT NULL COMMENT '是否被拉黑:0无1有',
	PRIMARY KEY ( `mainId`, `userId` ) USING BTREE,
	UNIQUE KEY `username` ( `username` ) 
) ENGINE = INNODB AUTO_INCREMENT = 26 DEFAULT CHARSET = utf8;

```

#### Java-字段映射

```java
@Data
public class BiuUser {
    private String username;
    private String password;
    private String salt;
    private String phone;
    private String email;
    private Integer sex;
    private Integer locked;
    private Integer string;
    private String lastLogin;
    private String createTime;
    private String mainId;
    private Integer black;
}
```

### 角色表

#### 字段说明

|   字段名    |   意义   |
| :---------: | :------: |
|   mainId    |   主键   |
|   roleId    | 外键引用 |
|  rolename   | 角色名字 |
| description |   描述   |
|   status    |   状态   |



#### Role-SQL语句

```mysql
/*
MySQL Backup
Database: shiro
Backup Time: 2020-03-05 14:46:37
*/ 
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE
IF
	EXISTS `shiro`.`role`;
CREATE TABLE `role` (
	`id` INT ( 11 ) NOT NULL AUTO_INCREMENT,
	`role_id` VARCHAR ( 20 ) NOT NULL COMMENT '角色id',
	`name` VARCHAR ( 50 ) NOT NULL COMMENT '角色名称',
	`description` VARCHAR ( 255 ) DEFAULT NULL COMMENT '角色描述',
	`status` INT ( 1 ) NOT NULL COMMENT '状态：1有效；2删除',
	`create_time` datetime DEFAULT NULL COMMENT '创建时间',
	`update_time` datetime DEFAULT NULL COMMENT '更新时间',
	PRIMARY KEY ( `id` ) 
) ENGINE = INNODB AUTO_INCREMENT = 7 DEFAULT CHARSET = utf8;


```

#### Java-字段映射

```JAVA
@Data
public class BiuRole {
    private String roleId;
    private String name;
    private String description;
    private Integer status;
    private String createTime;
}
```

### 权限表

#### 字段说明

| 字段名      | 意义         |
| :---------- | ------------ |
| mainId      | 主键         |
| permsId     | 外键对应     |
| permsname   | 权限名称     |
| description | 描述         |
| url         | 访问资源路径 |
| perms       | 权限         |
| type        | 按钮类型     |
| icon        | 图标         |
| status      | 状态         |
| create_time | 创建时间     |



#### Perms-SQL

```MYSQL

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE
IF
	EXISTS `biu`.`permission`;
CREATE TABLE `permission` (
	`mainId` INT ( 11 ) NOT NULL AUTO_INCREMENT,
	`permissionId` VARCHAR ( 20 ) NOT NULL COMMENT '权限id',
	`name` VARCHAR ( 100 ) NOT NULL COMMENT '权限名称',
	`description` VARCHAR ( 255 ) DEFAULT NULL COMMENT '权限描述',
	`url` VARCHAR ( 255 ) DEFAULT NULL COMMENT '权限访问路径',
	`perms` VARCHAR ( 255 ) DEFAULT NULL COMMENT '权限标识',
	`type` INT ( 1 ) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
	`order_num` INT ( 3 ) DEFAULT '0' COMMENT '排序',
	`icon` VARCHAR ( 50 ) DEFAULT NULL COMMENT '图标',
	`status` INT ( 1 ) NOT NULL COMMENT '状态：1有效；2删除',
	`create_time` datetime DEFAULT NULL,
	PRIMARY KEY ( `mainId` ) USING BTREE 
) ENGINE = INNODB AUTO_INCREMENT = 35 DEFAULT CHARSET = utf8;

```

#### Java-字段映射

```JAVA
@Data
public class BiuPerms {
    private String permsId;
    private String permsname;
    private String description;
    private String url;
    private String perms;
    private Integer type;
    private String icon;
    private Integer status;
    private String create_time;
}
```

### 中间表

#### User-Role

```mysql
SET FOREIGN_KEY_CHECKS = 0;
CREATE TABLE `user_role` (
	`id` INT ( 11 ) NOT NULL AUTO_INCREMENT,
	`userId` VARCHAR ( 20 ) NOT NULL COMMENT '用户id',
	`roleId` VARCHAR ( 20 ) NOT NULL COMMENT '角色id',
	PRIMARY KEY ( `id` ) 
) ENGINE = INNODB AUTO_INCREMENT = 5 DEFAULT CHARSET = utf8;

```

#### Role-Perms

```MYSQL
SET FOREIGN_KEY_CHECKS = 0;
CREATE TABLE `role_perm` (
	`id` INT ( 11 ) NOT NULL AUTO_INCREMENT,
	`role_id` VARCHAR ( 20 ) NOT NULL COMMENT '角色id',
	`permission_id` VARCHAR ( 20 ) NOT NULL COMMENT '权限id',
PRIMARY KEY ( `id` ) 
) ENGINE = INNODB AUTO_INCREMENT = 872 DEFAULT CHARSET = utf8;
```


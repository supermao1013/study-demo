drop table if exists t_sys_user;
create table t_sys_user (
  id 				bigint(20) 		not null auto_increment	   comment '主键',
  login_name 		varchar(32) 	not null 				   comment '登录账号',
  user_name 		varchar(32) 	not null 				   comment '用户昵称',
  user_type 		varchar(2) 	    default '00' 		       comment '用户类型（00系统用户 01普通用户）',
  email  			varchar(32) 	default '' 				   comment '用户邮箱',
  phonenumber  		varchar(16) 	default '' 				   comment '手机号码',
  sex  		        char(1) 	    default '0' 			   comment '用户性别（数据字典 0男 1女 2未知）',
  avatar            varchar(128) 	default '' 				   comment '头像路径',
  password 			varchar(64) 	default '' 				   comment '密码',
  salt 				varchar(32) 	default '' 				   comment '盐加密',
  status 			char(1) 		default '0' 			   comment '帐号状态（0正常 1停用）',
  del_flag			char(1) 		default '0' 			   comment '删除标志（0代表存在 1代表删除）',
  login_ip          varchar(32)     default ''                 comment '最后登陆IP',
  login_date        datetime                                   comment '最后登陆时间',
  create_user       varchar(32)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_user       varchar(32)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark 		    varchar(128) 	default '' 				   comment '备注',
  primary key (id)
) engine=innodb COLLATE =utf8_bin comment = '系统管理-用户信息表';

insert into t_sys_user values(1, 'admin', '管理员', '00', '163.com', '119', '1', '', '29c67a30398638269fe600f73a054934', '111111', '0', '0', '127.0.0.1', now(), 'admin', now(), 'admin', now(), '');



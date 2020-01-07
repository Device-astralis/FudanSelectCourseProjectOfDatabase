--用户账号密码表
create table `account` (
  `id` varchar(255) not null,
  `password` varchar(255) not null,
  primary key (id)
);

insert into selectcoursesystem.account(id,password) values('Root',123456)
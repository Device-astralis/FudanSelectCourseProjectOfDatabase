-- 创建教室数据表
create table `Classroom`(
  `classroom_id` varchar(255) not null,
  `class_capacity` int not null,
   primary key (classroom_id)
)
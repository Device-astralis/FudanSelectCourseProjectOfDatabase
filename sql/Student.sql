# 创建学生数据表
CREATE TABLE `Student` (
  `student_id` varchar(255) NOT NULL ,
  `student_name` VARCHAR(255) not null,
  `department` VARCHAR(255) not null,
   primary key (student_id)
);
insert into selectcoursesystem.student(student_id, student_name, department) values (1,"wqd","software");
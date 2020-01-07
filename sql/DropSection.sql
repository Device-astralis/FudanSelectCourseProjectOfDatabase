# 退课数据表
create table  `DropSection`(
   `student_id` varchar(255) NOT NULL ,
   `course_id` varchar(255) not null,
    `section_id` int not null,
    primary key (student_id,course_id,section_id),
   foreign key (student_id) references Student(student_id),
   foreign key (course_id,section_id) references Section(course_id,section_id) on delete cascade
)
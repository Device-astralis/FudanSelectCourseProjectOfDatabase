# 选课申请数据表
create table  `ApplySection`(
   `student_id` varchar(255) NOT NULL ,
   `course_id` varchar(255) not null,
    `section_id` int not null,
    `apply_status` int not null,
    `apply_reason` varchar(255) not null,
    primary key (student_id,course_id,section_id),
    foreign key (student_id) references student(student_id),
   foreign key (course_id,section_id) references Section(course_id,section_id) on delete cascade
)
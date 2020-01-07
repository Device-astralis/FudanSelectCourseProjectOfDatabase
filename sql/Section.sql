-- 创建开课数据表
create table `Section`(
    `course_id` varchar(255) not null,
    `section_id` int not null,
    `year` int not null check(year >= 2016 and year <= 2019),
    `semester` int not null,
    `section_capacity` int not null,
    `serial_number` int not null,
    `teacher_id` varchar(255) not null,
    `exam_id` INT NOT NULL,
    `classroom_id` varchar(255) not null,
    primary key (course_id,section_id),
    foreign key (teacher_id) references teacher(teacher_id),
    foreign key (course_id)references course(course_id) ,
    foreign key (exam_id) references exam(exam_id),
    foreign key (classroom_id) references classroom(classroom_id)
)
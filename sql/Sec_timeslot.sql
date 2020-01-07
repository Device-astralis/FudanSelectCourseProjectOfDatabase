--开课和上课时间数据表
create table `Sec_timeslot`(
     `course_id` varchar(255) not null,
    `section_id` int not null,
    `timeslot_id` int not null ,
    primary key (section_id,course_id,timeslot_id),
    foreign key (course_id,section_id) references section(course_id,section_id),
    foreign key (timeslot_id) references timeslot(timeslot_id)
)
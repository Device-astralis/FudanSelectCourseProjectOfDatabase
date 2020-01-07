-- 创建考试数据表
CREATE TABLE `Exam` (
  `exam_id` INT NOT NULL,
  `format` VARCHAR(255) not null ,
  `timeslot_id` int not null,
  `classroom_id` varchar(255) not null,
   primary key (exam_id),
   foreign key (timeslot_id) references timeslot(timeslot_id),
   foreign key (classroom_id) references classroom(classroom_id)
);
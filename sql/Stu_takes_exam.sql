--创建学生参加考试数据表
create table `Stu_takes_exam`(
   `student_id` varchar(255) NOT NULL ,
   `exam_id` INT NOT NULL,
   `grade` int not null,
   primary key (student_id,exam_id),
   foreign key (student_id) references student(student_id),
   foreign key (exam_id) references Exam(exam_id)
)
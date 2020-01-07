-- 创建课程数据表
CREATE TABLE `Course`(
   `course_id` varchar(255) not null,
   `course_name` varchar(255),
   `credit` int ,
   `department` varchar(255),
   `period` int ,
   `course_status` int default 1, -- 默认为1，代表未被删除，删除则为0，为0则不能被显示以及section不能被选课
   primary key (course_id)
)
# 创建时间点数据表
create table `Timeslot`(
  `timeslot_id` int not null ,
  `week` int not null,
  `slot_start_time` int not null,
  `slot_end_time` int not null,
   primary key (timeslot_id)
)

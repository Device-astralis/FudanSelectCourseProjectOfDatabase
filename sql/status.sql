-- 系统状态存储表
create table `systemstatus`(
     `year` int not null,
     `semester` int not null,
     `status` int not null, -- 0是未选课阶段，1是选课阶段
     primary key(year,semester,status)
)

insert into selectcoursesystem.systemstatus(year,semester,status) values(2019,0,1)
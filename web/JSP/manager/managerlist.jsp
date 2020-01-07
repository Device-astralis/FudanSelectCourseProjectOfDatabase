<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/12/11
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <div class="sidebar sidebar-dark sidebar-main sidebar-expand-md">
        <!-- /sidebar mobile toggler -->


        <!-- Sidebar content -->
        <div class="sidebar-content">
            <!-- Main navigation -->
            <div class="card card-sidebar-mobile">
                <ul class="nav nav-sidebar" data-nav-type="accordion">
                    <li class="nav-item-header"><div class="text-uppercase font-size-xs line-height-xs">管理员事务</div> <i class="icon-menu"></i></li>
                    <li class="nav-item"><a href="/managerShow?type=systemstatus" class="nav-link active">管理系统状态表</a></li>
                    <li class="nav-item"><a href="/managerShow?type=account" class="nav-link active">管理账号表</a></li>
                    <li class="nav-item"><a href="/managerShow?type=student" class="nav-link active">管理学生表</a></li>
                    <li class="nav-item"><a href="/managerShow?type=teacher" class="nav-link active">管理教师表</a></li>
                    <li class="nav-item"><a href="/managerShow?type=classroom" class="nav-link active">管理教室表</a></li>
                    <li class="nav-item"><a href="/managerShow?type=course" class="nav-link active">管理课程表</a></li>
                    <li class="nav-item"><a href="/managerShow?type=section" class="nav-link active">管理开课表</a></li>
                    <li class="nav-item"><a href="/managerShow?type=exam" class="nav-link active">管理考试表</a></li>
                    <li class="nav-item"><a href="/managerShow?type=timeslot" class="nav-link active">管理时间点表</a></li>
                    <li class="nav-item"><a href="/managerShow?type=selectsection" class="nav-link active">管理选课表</a></li>
                    <li class="nav-item"><a href="/managerShow?type=applysection" class="nav-link active">管理选课申请表</a></li>
                </ul>
            </div>
        </div>
        <!-- /main navigation -->
    </div>
</head>
<body>

</body>
</html>

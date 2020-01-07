<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/12/3
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>复旦大学选课系统</title>

    <!-- Global stylesheets -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900" rel="stylesheet" type="text/css">
    <link href="../assets/css/icons/icomoon/styles.css" rel="stylesheet" type="text/css">
    <link href="../assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../assets/css/bootstrap_limitless.min.css" rel="stylesheet" type="text/css">
    <link href="../assets/css/layout.min.css" rel="stylesheet" type="text/css">
    <link href="../assets/css/components.min.css" rel="stylesheet" type="text/css">
    <link href="../assets/css/colors.min.css" rel="stylesheet" type="text/css">
    <!-- /global stylesheets -->

    <!-- Core JS files -->
    <script src="../assets/js/main/jquery.min.js"></script>
    <script src="../assets/js/main/bootstrap.bundle.min.js"></script>
    <script src="../js/Timetables.min.js"></script>
    <script src="../js/index.js"></script>
    <!-- /core JS files -->

    <!-- Theme JS files -->
    <script src="../assets/js/plugins/prism.min.js"></script>
    <script src="../assets/js/plugins/sticky.min.js"></script>

    <script src="../assets/js/main/app.js"></script>
    <script src="../assets/js/pages/components_scrollspy.js"></script>
    <!-- /theme JS files -->
    <style>
        #coursesTable {
            padding: 15px 10px;
        }

        .Courses-head {
            background-color: #edffff;
            color: #02a9f5
        }

        .Courses-head > div {
            text-align: center;
            font-size: 14px;
            line-height: 28px;
        }

        .left-hand-TextDom, .Courses-head {
            background-color: #f2f6f7;
        }

        .Courses-leftHand {
            background-color: #f2f6f7;
            font-size: 12px;
        }

        .Courses-leftHand .left-hand-index {
            color: #9c9c9c;
            margin-bottom: 4px !important;
        }

        .Courses-leftHand .left-hand-name {
            color: #666;
        }

        .Courses-leftHand p {
            text-align: center;
            font-weight: 900;
        }

        .Courses-head > div {
            border-left: none !important;
        }

        .Courses-leftHand > div {
            padding-top: 5px;
            border-bottom: 1px dashed rgb(219, 219, 219);
        }

        .Courses-leftHand > div:last-child {
            border-bottom: none !important;
        }

        .left-hand-TextDom, .Courses-head {
            border-bottom: 1px solid rgba(0, 0, 0, 0.1) !important;
        }

        .Courses-content > ul {
            border-bottom: 1px dashed rgb(219, 219, 219);
            box-sizing: border-box;
        }

        .Courses-content > ul:last-child {
            border-bottom: none !important;
        }

        .Courses-content li {
            text-align: center;
            color: #666666;
            font-size: 14px;
            line-height: 50px;
        }

        .Courses-content li span {
            padding: 6px 2px;
            box-sizing: border-box;
            line-height: 18px;
            border-radius: 4px;
            white-space: normal;
            word-break: break-all;
            cursor: pointer;
        }

        .grid-active {
            z-index: 9999;
        }

        .grid-active span {
            box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
        }
    </style>

</head>

<body data-spy="scroll" data-target=".sidebar-component-right">

<!-- Main navbar -->
<jsp:include page="./user.jsp"></jsp:include>
<!-- /main navbar -->


<!-- Page content -->
<div class="page-content">

    <!-- Main sidebar -->
    <div class="sidebar sidebar-dark sidebar-main sidebar-expand-md">
        <!-- /sidebar mobile toggler -->


        <!-- Sidebar content -->
        <div class="sidebar-content">
            <!-- Main navigation -->
            <div class="card card-sidebar-mobile">
                <ul class="nav nav-sidebar" data-nav-type="accordion">
                    <li class="nav-item-header"><div class="text-uppercase font-size-xs line-height-xs">课程操作</div> <i class="icon-menu"></i></li>
                    <li class="nav-item"><a href="/SelectCourse" class="nav-link">选课</a></li>
                    <li class="nav-item"><a href="/JSP/ApplySelect.jsp" class="nav-link">选课申请</a></li>
                    <li class="nav-item"><a href="/ApplyStatus" class="nav-link">我的选课申请状态</a></li>

                    <li class="nav-item-header"><div class="text-uppercase font-size-xs line-height-xs">个人信息</div> <i class="icon-menu"></i></li>
                    <li class="nav-item"><a href="/CourseList" class="nav-link active">我的课程表</a></li>
                    <li class="nav-item"><a href="/ShowGrade" class="nav-link">我的成绩</a></li>
                </ul>
            </div>
        </div>
        <!-- /main navigation -->
    </div>
    <!-- /main sidebar -->


    <!-- Main content -->
    <div class="content-wrapper">

        <!-- Page header -->
        <div class="page-header">
            <div class="page-header-content header-elements-md-inline">
                <div class="page-title d-flex">
                    <h4><i class="icon-arrow-left52 mr-2"></i> <span class="font-weight-semibold">我的课程表</span> - 复旦大学选课系统</h4>
                    <a href="#" class="header-elements-toggle text-default d-md-none"><i class="icon-more"></i></a>
                </div>

                <div class="header-elements d-none py-0 mb-3 mb-md-0">
                    <div class="breadcrumb">
                        <a href="/JSP/index.jsp" class="breadcrumb-item"><i class="icon-home2 mr-2"></i> 主页</a>
                        <span class="breadcrumb-item active">复旦大学选课系统</span>
                    </div>
                </div>
            </div>
        </div>
        <!-- /page header -->


        <!-- Content area -->
        <div class="content pt-0">

            <!-- Inner container -->
            <div class="d-flex align-items-start flex-column flex-md-row">
                <div class="order-3 order-md-3" style="width: 100%">
                    <div class="card" id="selectCourse">
                        <div class="card-header header-elements-inline">
                            <h5 class="card-title">我的课程表</h5>
                        </div>
                        <div class="card-body">
                            <div class="mb-4">
                                <div id="coursesTable"></div>
                            </div>
                        </div>
                    </div>
                    <div class="card" id="dropSection">
                        <div class="card-header header-elements-inline">
                            <h5 class="card-title">退课</h5>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive mb-3">
                                <table class="table table-bordered table-striped">
                                    <thead>
                                    <tr>
                                        <th style="width: 10%">课程名称</th>
                                        <th style="width: 10%">课程代码</th>
                                        <th style="width: 10%">开课代码</th>
                                        <th style="width: 10%">任课教师</th>
                                        <th style="width: 5%">学分</th>
                                        <th style="width: 15%">上课时间</th>
                                        <th style="width: 10%">上课地点</th>
                                        <th style="width: 5%">考试形式</th>
                                        <th style="width: 10%">考试时间</th>
                                        <th style="width: 10%">考试地点</th>
                                        <th style="width: 5%"></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <%
                                        ArrayList<ArrayList<String>> sectionArrayList;
                                        sectionArrayList = (ArrayList<ArrayList<String>>)request.getAttribute("selected");
                                        for(ArrayList<String> arrayList: sectionArrayList){
                                    %>
                                    <tr>
                                        <td><%=arrayList.get(0)%></td>
                                        <td><%=arrayList.get(10)%></td>
                                        <td><%=arrayList.get(1)%></td>
                                        <td><%=arrayList.get(2)%></td>
                                        <td><%=arrayList.get(3)%></td>
                                        <td><%=arrayList.get(8)%></td>
                                        <td><%=arrayList.get(7)%></td>
                                        <td><%=arrayList.get(4)%></td>
                                        <td><%=arrayList.get(5)%></td>
                                        <td><%=arrayList.get(6)%></td>
                                        <td><button type="button" class="btn btn-primary active" onclick="location='/DropSection?section_id=<%=arrayList.get(1)%>&course_id=<%=arrayList.get(10)%>'">退课</button></td>
                                    </tr>
                                    <%}%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /inner container -->

        </div>
        <!-- /content area -->
    </div>
    <!-- /main content -->

</div>
<!-- /page content -->
<script>
    var courseList = new Array(7);
    var week1 = ['','','','','','','','','','','','','',''];
    var week2 = ['','','','','','','','','','','','','',''];
    var week3 = ['','','','','','','','','','','','','',''];
    var week4 = ['','','','','','','','','','','','','',''];
    var week5 = ['','','','','','','','','','','','','',''];
    var week6 = ['','','','','','','','','','','','','',''];
    var week7 = ['','','','','','','','','','','','','',''];
    courseList[0] = week1;
    courseList[1] = week2;
    courseList[2] = week3;
    courseList[3] = week4;
    courseList[4] = week5;
    courseList[5] = week6;
    courseList[6] = week7;
    <%
    for (ArrayList<String> a:sectionArrayList ) {
        String course_name = a.get(0);
        String time_all = a.get(8);
        String[] every_time = time_all.split("<br>");
        for (String string : every_time) {
            int week = Integer.parseInt(string.charAt(1)+"");
            String time = string.substring(string.indexOf(" ")+1);
            String[] some_time = time.split("-");
            int start_time = Integer.parseInt(some_time[0]);
            int end_time = Integer.parseInt(some_time[1]);
            for(int i = start_time-1;i < end_time;i++){
    %>
    courseList[<%=week-1%>][<%=i%>]='<%=course_name%>';
    <%}}}%>
    var week = window.innerWidth > 360 ? ['周一', '周二', '周三', '周四', '周五','周六','周日'] :
        ['一', '二', '三', '四', '五','六','七'];
    var day = new Date().getDay();
    var courseType = [
        [{index: '1'}, 1],
        [{index: '2'}, 1],
        [{index: '3'}, 1],
        [{index: '4'}, 1],
        [{index: '5'}, 1],
        [{index: '6'}, 1],
        [{index: '7'}, 1],
        [{index: '8'}, 1],
        [{index: '9'}, 1],
        [{index: '10'}, 1],
        [{index: '11'}, 1],
        [{index: '12'}, 1],
        [{index: '13'}, 1],
        [{index: '14'}, 1],
    ];
    // 实例化(初始化课表)
    var Timetable = new Timetables({
        el: '#coursesTable',
        timetables: courseList,
        week: week,
        timetableType: courseType,
        highlightWeek: day,
        gridOnClick: function (e) {
            alert(e.name + '  ' + e.week + ', 第' + e.index + '节课, 课长' + e.length + '节');
            console.log(e);
        },
        styles: {
            Gheight: 50
        }
    });
    <%String error = (String)request.getAttribute("error");%>
    <%if(error != null){%>
    alert("<%=error%>");
    <%}%>
    <%request.removeAttribute("error");%>
</script>
</body>
</html>


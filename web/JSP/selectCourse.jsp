<%@ page import="java.util.ArrayList" %>
<%@ page import="Entity.Section" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/12/3
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>复旦大学选课系统</title>

    <!-- Global stylesheets -->

    <link rel="stylesheet" type="text/css" href="../js/page/css/font-awesome.min.css">
    <link rel="stylesheet" href="../js/page/css/jquery.paginate.css" />
    <link rel="stylesheet" href="../js/page/css/jquery.yhhDataTable.css" />
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900" rel="stylesheet" type="text/css">
    <link href="../assets/css/icons/icomoon/styles.css" rel="stylesheet" type="text/css">
    <link href="../assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../assets/css/bootstrap_limitless.min.css" rel="stylesheet" type="text/css">
    <link href="../assets/css/layout.min.css" rel="stylesheet" type="text/css">
    <link href="../assets/css/components.min.css" rel="stylesheet" type="text/css">
    <link href="../assets/css/colors.min.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="../js/page/js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/page/js/jquery.paginate.js" ></script>
    <script type="text/javascript" src="../js/page/js/jquery.yhhDataTable.js" ></script>
    <!-- /global stylesheets -->

    <!-- Core JS files -->
<%--    <script src="../assets/js/main/jquery.min.js"></script>--%>
    <script src="../assets/js/main/bootstrap.bundle.min.js"></script>
    <!-- /core JS files -->

    <!-- Theme JS files -->
    <script src="../assets/js/plugins/prism.min.js"></script>
    <script src="../assets/js/plugins/sticky.min.js"></script>

    <script src="../assets/js/main/app.js"></script>
    <script src="../assets/js/pages/components_scrollspy.js"></script>
    <!-- /theme JS files -->

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
                    <li class="nav-item"><a href="/SelectCourse" class="nav-link active">选课</a></li>
                    <li class="nav-item"><a href="/JSP/ApplySelect.jsp" class="nav-link">选课申请</a></li>
                    <li class="nav-item"><a href="/ApplyStatus" class="nav-link">我的选课申请状态</a></li>

                    <li class="nav-item-header"><div class="text-uppercase font-size-xs line-height-xs">个人信息</div> <i class="icon-menu"></i></li>
                    <li class="nav-item"><a href="/CourseList" class="nav-link">我的课程表</a></li>
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
                    <h4><i class="icon-arrow-left52 mr-2"></i> <span class="font-weight-semibold">选课界面</span> - 复旦大学选课系统</h4>
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
                            <h5 class="card-title">选课界面</h5>
                        </div>
                        <%String status = (String) request.getAttribute("couldSelect");
                            ArrayList<ArrayList<String>> sectionArrayList;
                            sectionArrayList = (ArrayList<ArrayList<String>>)request.getAttribute("Section");
                        if(status.equals("1")){%>
                        <div class="card-body">
                            <div class="mb-4">
                                <p class="mb-3">请尽情选择您所喜爱的课程！</p>

                                <div class="table-responsive mb-3">
                                    <table class="table table-bordered table-striped" id="course_table">
                                        <thead>
                                        <tr>
                                            <th style="width: 10%">课程名称</th>
                                            <th style="width: 5%">课程代码</th>
                                            <th style="width: 5%">开课代码</th>
                                            <th style="width: 10%">任课教师</th>
                                            <th style="width: 5%">学分</th>
                                            <th style="width: 15%">上课时间</th>
                                            <th style="width: 10%">上课地点</th>
                                            <th style="width: 5%">考试形式</th>
                                            <th style="width: 10%">考试时间</th>
                                            <th style="width: 10%">考试地点</th>
                                            <th style="width: 10%">选课人数/最大人数</th>
                                            <th style="width: 5%"></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <%}else {%>
                        <div class="card-body">
                            <div class="mb-4">
                                <p class="mb-3">目前不处于选课阶段，您无法选课！</p>
                            </div>
                        </div>
                        <%}%>
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

</body>
<script>
    <%String error = (String)request.getAttribute("error");%>
    <%if(error != null){if(error.isEmpty()){%>
    alert("选课成功");
    <%}%>
    <%if(!error.isEmpty()){%>
    alert("选课失败，原因之一如下：<%=error%>");
    <%}}
    request.removeAttribute("error");%>
    <%if(!status.isEmpty() && status.equals("1")){%>
    $(document).ready(function(){
        var testdata1 = new Array(<%=sectionArrayList.size()%>);
        <%
         for(int i = 0 ; i < sectionArrayList.size() ; i++){
             ArrayList<String> tmp = sectionArrayList.get(i);
        %>
        testdata1[<%=i%>]=['<%=tmp.get(0)%>','<%=tmp.get(10)%>','<%=tmp.get(1)%>','<%=tmp.get(2)%>','<%=tmp.get(3)%>','<%=tmp.get(8)%>','<%=tmp.get(7)%>','<%=tmp.get(4)%>','<%=tmp.get(5)%>','<%=tmp.get(6)%>','<%=tmp.get(9)%>','<button type="button" class="btn btn-primary active" onclick="location=\'/realSelectCourse?student_id=S003&section_id=<%=tmp.get(1)%>&course_id=<%=tmp.get(10)%>\'">选课</button>'];
        <%}%>
        $('#course_table').yhhDataTable({
            'paginate':{
                'changeDisplayLen':false,
                'type':'updown',
                'visibleGo': true
            },
            'tbodyRow':{
                'zebra':true
            },
            'tbodyData':{
                'enabled':true,  /*是否传入表格数据*/
                'source':testdata1 /*传入的表格数据*/
            }
        });
    });
    <%}%>
</script>
</html>

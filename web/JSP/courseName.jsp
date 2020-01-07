<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: xiayutian
  Date: 2019/12/6
  Time: 5:47 下午
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
    <script src="../js/plugins/blockui.min.js"></script>
    <!-- /core JS files -->

    <!-- Theme JS files -->
    <script src="../assets/js/plugins/prism.min.js"></script>
    <script src="../assets/js/plugins/sticky.min.js"></script>
    <script src="../js/plugins/forms/styling/switchery.min.js"></script>
    <script src="../js/plugins/forms/styling/uniform.min.js"></script>
    <script src="../js/plugins/forms/selects/select2.min.js"></script>

    <script src="../assets/js/main/app.js"></script>
    <script src="../js/form_actions.js"></script>
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
                    <li class="nav-item-header"><div class="text-uppercase font-size-xs line-height-xs">教师事务</div> <i class="icon-menu"></i></li>
                    <li class="nav-item"><a href="/CourseName" class="nav-link active">查看课程花名册</a></li>
                    <li class="nav-item"><a href="/ManagerApply" class="nav-link">选课事务管理</a></li>
                    <li class="nav-item"><a href="/JSP/inputGrade.jsp" class="nav-link">成绩登入</a></li>
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
                    <h4><i class="icon-arrow-left52 mr-2"></i> <span class="font-weight-semibold">课程花名册</span> - 复旦大学选课系统</h4>
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
                <div class="order-3 order-md-3" style="width: 100%;">
                    <%
                        Map<String, ArrayList<ArrayList<String>>> courseName = (Map<String, ArrayList<ArrayList<String>>>)request.getAttribute("courseName");
                        if(courseName!=null && courseName.size() != 0){
                            Set<String> course_name = courseName.keySet();
                            for (String name:course_name) {
                    %>
                    <div class="card">
                        <div class="card-header header-elements-inline">
                            <h5 class="card-title"><%=name%></h5>
                            <div class="header-elements">
                                <div class="list-icons">
                                    <a class="list-icons-item" data-action="collapse"></a>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="mb-4">
                                <div class="table-responsive mb-3">
                                    <table class="table table-bordered table-striped">
                                        <thead>
                                        <tr>
                                            <th style="width: 50%">学生姓名</th>
                                            <th style="width: 50%">学生学号</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <%
                                            for (ArrayList<String> a:courseName.get(name)
                                                 ) {
                                        %>
                                        <tr>
                                            <td><%=a.get(1)%></td>
                                            <td><%=a.get(0)%></td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%
                        }}else{
                    %>
                    <div class="card-body">
                        <div class="mb-4">
                            <p class="mb-3">您没有开设任何课程！</p>
                        </div>
                    </div>
                    <%}%>
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
</html>


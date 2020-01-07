<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: xiayutian
  Date: 2019/12/6
  Time: 11:17 上午
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
                    <li class="nav-item"><a href="/SelectCourse" class="nav-link">选课</a></li>
                    <li class="nav-item"><a href="/JSP/ApplySelect.jsp" class="nav-link">选课申请</a></li>
                    <li class="nav-item"><a href="/ApplyStatus" class="nav-link active">我的选课申请状态</a></li>

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
                    <h4><i class="icon-arrow-left52 mr-2"></i> <span class="font-weight-semibold">我的选课申请状态</span> - 复旦大学选课系统</h4>
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
                <div class="order-3 order-md-3">
                    <div class="card" id="selectCourse">
                        <div class="card-header header-elements-inline">
                            <h5 class="card-title">选课申请状态界面</h5>
                        </div>
                        <div class="card-body">
                            <div class="mb-4">
                                <p class="mb-3">这里是你所提交的所有选课申请</p>

                                <div class="table-responsive mb-3">
                                    <table class="table table-bordered table-striped">
                                        <thead>
                                        <tr>
                                            <th style="width: 10%">课程代码</th>
                                            <th style="width: 10%">开课代码</th>
                                            <th style="width: 70%">申请理由</th>
                                            <th style="width: 10%">申请状态</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <%ArrayList<ArrayList<String>> result = (ArrayList<ArrayList<String>>)request.getAttribute("apply");
                                            for (ArrayList<String> a:result
                                                 ) {%>
                                        <tr>
                                            <td><%=a.get(0)%></td>
                                            <td><%=a.get(1)%></td>
                                            <td><%=a.get(2)%></td>
                                            <%if(a.get(3).equals("-1")){%>
                                            <td>已提交</td>
                                            <%}%>
                                            <%if(a.get(3).equals("0")){%>
                                            <td>不通过</td>
                                            <%}%>
                                            <%if(a.get(3).equals("1")){%>
                                            <td>通过</td>
                                            <%}%>
                                        </tr>
                                            <%}%>
                                        </tbody>
                                    </table>
                                </div>
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

</body>
</html>

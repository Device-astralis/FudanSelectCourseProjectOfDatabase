<%--
  Created by IntelliJ IDEA.
  User: xiayutian
  Date: 2019/12/6
  Time: 6:06 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
    <script src="../js/form_inputs.js"></script>
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
                    <li class="nav-item"><a href="/CourseName" class="nav-link">查看课程花名册</a></li>
                    <li class="nav-item"><a href="/ManagerApply" class="nav-link">选课事务管理</a></li>
                    <li class="nav-item"><a href="/JSP/inputGrade.jsp" class="nav-link active">成绩登入</a></li>
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
                    <h4><i class="icon-arrow-left52 mr-2"></i> <span class="font-weight-semibold">选课事务申请管理</span> - 复旦大学选课系统</h4>
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
                    <div class="card">
                        <div class="card-header header-elements-inline">
                            <h5 class="card-title">导入课程成绩</h5>
                            <div class="header-elements">
                                <div class="list-icons">
                                    <a class="list-icons-item" data-action="collapse"></a>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="mb-4">
                                <form action="/InputGrade" id="gradeFile" ENCTYPE="multipart/form-data" method="post" type="File">
                                    <fieldset class="mb-3">
                                        <div class="form-group row">
                                            <label class="col-form-label col-lg-2">请导入课程成绩</label>
                                            <div class="col-lg-10">
                                                <input name="file_input" id="file_input" type="file" class="form-control">
                                            </div>
                                        </div>
                                    </fieldset>
                                    <fieldset class="mb-3">
                                        <div class="form-group row">
                                            <label class="col-lg-11"></label>
                                            <div class="col-lg-1">
                                                <button type="submit" class="btn btn-info">提交</button>
                                            </div>
                                        </div>
                                    </fieldset>
                                </form>
                            </div>
                        </div>
                    </div>
                    <%
                        String errorData = (String) request.getAttribute("errorData");
                        if(errorData != null && !errorData.isEmpty()){

                    %>
                    <div class="card">
                        <div class="card-header header-elements-inline">
                            <h5 class="card-title">导入课程成绩</h5>
                            <div class="header-elements">
                                <div class="list-icons">
                                    <a class="list-icons-item" data-action="collapse"></a>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="mb-4">
                                <%=errorData%>
                            </div>
                        </div>
                    </div>
                    <%
                        }
                        request.removeAttribute("errorData");
                    %>
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
    $(function() {

        $("#gradeFile").submit(
            function() {
                //首先验证文件格式
                var fileName = $('#file_input').val();
                if (fileName === '') {
                    alert('请选择文件');
                    return false;
                }
                var fileType = (fileName.substring(fileName
                    .lastIndexOf(".") + 1, fileName.length))
                    .toLowerCase();
                if (fileType !== 'csv') {
                    alert('文件格式不正确，csv文件！');
                    return false;
                }
            });

    });
    <%String error = (String)request.getAttribute("error");%>
    <%if(error != null){if(error.isEmpty()){%>
    alert("导入成绩成功。");
    <%}%>
    <%if(!error.isEmpty()){%>
    alert("<%=error%>");
    <%}}
    request.removeAttribute("error");%>
</script>
</body>
</html>

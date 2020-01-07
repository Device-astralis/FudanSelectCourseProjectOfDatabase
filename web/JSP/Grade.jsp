<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: xiayutian
  Date: 2019/12/6
  Time: 1:57 下午
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
    <script src="../js/form_select2.js"></script>
    <script src="../js/select2.min.js"></script>
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
                    <li class="nav-item"><a href="/ApplyStatus" class="nav-link">我的选课申请状态</a></li>

                    <li class="nav-item-header"><div class="text-uppercase font-size-xs line-height-xs">个人信息</div> <i class="icon-menu"></i></li>
                    <li class="nav-item"><a href="/CourseList" class="nav-link">我的课程表</a></li>
                    <li class="nav-item"><a href="Grade.jsp" class="nav-link active">我的成绩</a></li>
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
                    <h4><i class="icon-arrow-left52 mr-2"></i> <span class="font-weight-semibold">学生成绩界面</span> - 复旦大学选课系统</h4>
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
                    <div class="card" id="">
                        <div class="card-header header-elements-inline">
                            <h5 class="card-title">选择学期</h5>
                        </div>
                        <div class="card-body">
                            <div class="mb-4">
                                <%
                                    if(request.getParameter("year") != null && request.getParameter("semester") != null){
                                        int year = Integer.parseInt(request.getParameter("year"));
                                        int semester = Integer.parseInt(request.getParameter("semester"));
                                %>
                                <div class="form-group">
                                    <label>选择年份</label>
                                    <select id="year" data-placeholder="选择一个年份..." class="form-control select" data-fouc>
                                        <option id="2019" value="2019" <%if(year == 2019){%>selected<%}%>>2019</option>
                                        <option id="2018" value="2018" <%if(year == 2018){%>selected<%}%>>2018</option>
                                        <option id="2017" value="2017" <%if(year == 2017){%>selected<%}%>>2017</option>
                                        <option id="2016" value="2016" <%if(year == 2016){%>selected<%}%>>2016</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>选择学期</label>
                                    <select id="semester" data-placeholder="选择一个学期..." class="form-control select" data-fouc>
                                        <option id="1" value="0"<%if(semester == 0){%>selected<%}%>>第一学期</option>
                                        <option id="2" value="1"<%if(semester == 1){%>selected<%}%>>第二学期</option>
                                    </select>
                                </div>
                                <%}else {%>
                                <div class="form-group">
                                    <label>选择年份</label>
                                    <select id="year" data-placeholder="选择一个年份..." class="form-control select" data-fouc>
                                        <option id="2019" value="2019" selected>2019</option>
                                        <option id="2018" value="2018">2018</option>
                                        <option id="2017" value="2017">2017</option>
                                        <option id="2016" value="2016">2016</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>选择学期</label>
                                    <select id="semester" data-placeholder="选择一个学期..." class="form-control select" data-fouc>
                                        <option id="1" value="0">第一学期</option>
                                        <option id="2" value="1" selected>第二学期</option>
                                    </select>
                                </div>
                                <%}%>
                            </div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header header-elements-inline">
                            <h5 class="card-title">学生成绩界面</h5>
                        </div>
                        <div class="card-body">
                            <div class="mb-4">
                                <%
                                    ArrayList<ArrayList<String>> gradeList = (ArrayList<ArrayList<String>>)request.getAttribute("gradeList");
                                    if(gradeList!=null && !gradeList.isEmpty()){
                                %>
                                <p class="mb-3">以下是你所选修的课程的成绩</p>

                                <div class="table-responsive mb-3">
                                    <table class="table table-bordered table-striped">
                                        <thead>
                                        <tr>
                                            <th style="width: 20%">课程名称</th>
                                            <th style="width: 20%">课程代码</th>
                                            <th style="width: 20%">开课代码</th>
                                            <th style="width: 20%">学分</th>
                                            <th style="width: 20%">绩点</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <%
                                            for (ArrayList<String> a:gradeList
                                                 ) {
                                        %>
                                        <tr>
                                            <td><%=a.get(0)%></td>
                                            <td><%=a.get(1)%></td>
                                            <td><%=a.get(2)%></td>
                                            <td><%=a.get(3)%></td>
                                            <%if(a.get(4).equals("-1")){%>
                                            <td>暂无成绩</td>
                                            <%}else {%>
                                            <td><%=a.get(4)%></td>
                                            <%}%>
                                        </tr>
                                        <%}}else { %>
                                        <p class="mb-3">你暂时没有任何成绩。</p>
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
                        if(gradeList!= null && !gradeList.isEmpty()){
                    %>
                    <div class="card">
                        <div class="card-header header-elements-inline">
                            <h5 class="card-title">学生成绩综合信息</h5>
                        </div>
                        <div class="card-body">
                            <div class="mb-4">
                                <%
                                    int sum_grade = 0;
                                    int sum_credit = 0;
                                    int sum_mutiply = 0;
                                    for (ArrayList<String> a:gradeList
                                         ) {
                                        if(!a.get(4).equals("-1")){
                                            sum_grade += Integer.parseInt(a.get(4));
                                            sum_credit += Integer.parseInt(a.get(3));
                                            sum_mutiply += Integer.parseInt(a.get(4))*Integer.parseInt(a.get(3));
                                        }
                                    }
                                %>
                                <p class="mb-3">以下是你本学期所选修的课程的总成绩</p>

                                <div class="table-responsive mb-3">
                                    <table class="table table-bordered table-striped">
                                        <thead>
                                        <tr>
                                            <th style="width: 30%">总学分</th>
                                            <th style="width: 30%">总绩点</th>
                                            <th style="width: 40%">平均绩点</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <%
                                            if(sum_credit != 0 && sum_grade != 0) {
                                                float float_result = (float) sum_mutiply / (float) sum_credit;
                                                String string_result = float_result + "";
                                        %>
                                        <tr>
                                            <td><%=sum_credit%></td>
                                            <td><%=sum_grade%></td>
                                            <td><%=string_result.substring(0,4)%></td>
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
                        }
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

</body>
<script>
    $(function () {
        $("#year").change(function (data) {
            var year = $("#year option:selected").attr("value");
            var semester = $("#semester option:selected").attr("value");
            var url = "/ShowGrade?year="+year+"&semester="+semester;
            window.location.href = url;
        });
        $("#semester").change(function (data) {
            var year = $("#year option:selected").attr("value");
            var semester = $("#semester option:selected").attr("value");
            var url = "/ShowGrade?year="+year+"&semester="+semester;
            window.location.href = url;
        });
    });
</script>
</html>

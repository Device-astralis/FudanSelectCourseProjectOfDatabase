<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/12/10
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="managerImport.jsp"></jsp:include>

<body data-spy="scroll" data-target=".sidebar-component-right">

<!-- Main navbar -->

<!-- /main navbar -->
<jsp:include page="../user.jsp"></jsp:include>

<!-- Page content -->
<div class="page-content">

    <!-- Main sidebar -->
    <jsp:include page="managerlist.jsp"></jsp:include>
    <!-- /main sidebar -->


    <!-- Main content -->
    <div class="content-wrapper">
        <%
            String type = (String) request.getAttribute("type");
            ArrayList<String> head = (ArrayList<String>)request.getAttribute("head");
            ArrayList<ArrayList<String>> returnData = (ArrayList<ArrayList<String>>)request.getAttribute("returnData");
            String typeName = "";
            switch(type){
                case "account":
                   typeName = "账号";
                    break;
                case "student":
                    typeName = "学生";
                    break;
                case "teacher":
                    typeName = "教师";
                    break;
                case "classroom":
                    typeName = "教室";
                    break;
                case "course":
                    typeName = "课程";
                    break;
                case "section":
                    typeName = "开课";
                    break;
                case "exam":
                    typeName = "考试";
                    break;
                case "timeslot":
                    typeName = "时间";
                    break;
                case "selectsection":
                    typeName = "选课";
                    break;
                case "dropsection":
                    typeName = "退课";
                    break;
                case "applysection":
                    typeName = "选课申请";
                    break;
                case "systemstatus":
                    typeName = "系统状态";
            }

        %>
        <!-- Page header -->
        <div class="page-header">
            <div class="page-header-content header-elements-md-inline">
                <div class="page-title d-flex">
                    <h4><i class="icon-arrow-left52 mr-2"></i> <span class="font-weight-semibold">管理<%=typeName%>表</span> - 复旦大学选课系统</h4>
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
                            <h5 class="card-title"><%=typeName%>表</h5>
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
                                            <%
                                                for(int i=0;i<head.size();i++){
                                            %>
                                            <th style="width: 30%"><%=head.get(i)%></th>
                                            <%
                                                }
                                            %>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <%
                                            if(returnData==null){
                                                %>
                                        <p>暂时没有信息，请添加信息！</p>
                                        <%
                                            }else{
                                            for(int i=0;i<returnData.size();i++){
                                            %>
                                        <tr>
                                            <% for(int j = 0;j<returnData.get(i).size();j++){
                                                %>
                                            <td><%=returnData.get(i).get(j)%></td>
                                            <%
                                                }
                                            %>
                                        </tr>
                                        <%
                                            }
                                            }
                                        %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%
                        ArrayList<ArrayList<String>> errorDatas = (ArrayList<ArrayList<String>>)request.getAttribute("errorData");
                        if(errorDatas!=null){
                    %>
                    <div class="card">
                        <div class="card-header header-elements-inline">
                            <h5 class="card-title"><%=typeName%>错误表</h5>
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
                                            <%
                                                for(int i=0;i<head.size();i++){
                                            %>
                                            <th style="width: 30%"><%=head.get(i)%></th>
                                            <%
                                                }
                                            %>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <%
                                            for(int i=0;i<errorDatas.size();i++){
                                        %>
                                        <tr>
                                            <% for(int j = 0;j<errorDatas.get(i).size();j++){
                                            %>
                                            <td><%=errorDatas.get(i).get(j)%></td>
                                            <%
                                                }
                                            %>
                                        </tr>
                                        <%
                                                }
//                                            }
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
                    <%
                        if(!(type.equals("account")||type.equals("systemstatus"))){
                            //账户和系统表不存在自动添加和手动添加功能
                    %>
                    <div class="card">
                        <div class="card-header header-elements-inline">
                            <h5 class="card-title">自动添加</h5>
                            <div class="header-elements">
                                <div class="list-icons">
                                    <a class="list-icons-item" data-action="collapse"></a>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="mb-4">
                                <form action="/addDataFromManagerServlet?type=<%=type%>" ENCTYPE="multipart/form-data" type="File" method="post">
                                    <fieldset class="mb-3">
                                        <div class="form-group row">
                                            <label class="col-form-label col-lg-2">请导入<%=typeName%>表</label>
                                            <div class="col-lg-10">
                                                <input name="excel" id="excel" type="file" class="form-control">
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

                    <div class="card">
                        <div class="card-header header-elements-inline">
                            <h5 class="card-title">手动添加</h5>
                            <div class="header-elements">
                                <div class="list-icons">
                                    <a class="list-icons-item" data-action="collapse"></a>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="mb-4">
                                <p class="mb-3"></p>
                                <form action="" method="">
                                    <%

                                        for(int i=0;i<head.size();i++){
                                            if(type.equals("applysection")&&i == 3){
                                                continue;
                                            }
                                    %>
                                    <div class="form-group">
                                        <label for="<%=head.get(i)%>"><%=head.get(i)%></label>
                                        <input name="<%=head.get(i)%>" id="<%=head.get(i)%>add" type="text" class="form-control">
                                    </div>
                                    <%
                                        }
                                    %>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <button type="button" class="btn btn-light">取消</button>
                                        <button type="button" class="btn bg-blue"onclick="addByHand()">提交 <i class="icon-paperplane ml-2"></i></button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <%
                        }
                        if(type.equals("course")){
                    %>
                    <div class="card">
                        <div class="card-header header-elements-inline">
                            <h5 class="card-title">手动删除课程</h5>
                            <div class="header-elements">
                                <div class="list-icons">
                                    <a class="list-icons-item" data-action="collapse"></a>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="mb-4">
                                <p class="mb-3"></p>
                                <form action="" method="">
                                    <%--<%--%>
                                        <%--for(int i=0;i<head.size();i++){--%>
                                    <%--%>--%>
                                    <div class="form-group">
                                        <label for="<%=head.get(0)%>"><%=head.get(0)%></label>
                                        <input name="<%=head.get(0)%>" id="<%=head.get(0)%>delete" type="text" class="form-control">
                                    </div>
                                    <%--<%--%>
                                        <%--}--%>
                                    <%--%>--%>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <button type="button" class="btn btn-light">取消</button>
                                        <button type="button" class="btn bg-blue" onclick="deleteCourse()">删除 <i class="icon-paperplane ml-2"></i></button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <%
                        }
                    %>

                    <%
                        if(type.equals("systemstatus")){
                    %>
                    <div class="card">
                        <div class="card-header header-elements-inline">
                            <h5 class="card-title">更改系统状态</h5>
                            <div class="header-elements">
                                <div class="list-icons">
                                    <a class="list-icons-item" data-action="collapse"></a>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="mb-4">
                                <p class="mb-3"></p>
                                <form action="" method="">
                                    <%
                                        for(int i=0;i<head.size();i++){
                                    %>
                                    <div class="form-group">
                                        <label for="<%=head.get(i)%>"><%=head.get(i)%></label>
                                        <input name="<%=head.get(i)%>" id="<%=head.get(i)%>add" type="text" class="form-control">
                                    </div>
                                    <%
                                        }
                                    %>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <button type="button" class="btn btn-light">取消</button>
                                        <button type="button" class="btn bg-blue" onclick="addByHand()">更改<i class="icon-paperplane ml-2"></i></button>
                                    </div>
                                </form>
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
  function addByHand() {
      <%--<%--%>
      <%--ArrayList<ArrayList<String>> results = new ArrayList<>();--%>
      <%--ArrayList<String> aline = new ArrayList<>();--%>
      <%--for(int i=0;i<head.size();i++){--%>
          <%--%>--%>
      <%--data = document.getElementById("<%=head.get(i)%>add");--%>
      <%--<%--%>
      <%--aline.add(--%>
      <%--%>data--%>
      <%--<%--%>
      <%--)--%>
      <%--}--%>
      <%--%>--%>
      var output = "";
      <%
      for(int i=0;i<head.size();i++){
      %>
      data = document.getElementById("<%=head.get(i)%>add").value;
      output+=data;
      output+=":";
      <%
      }
      %>
      // alert("2");
      window.location.href ="/addDataFromManagerServlet?type=<%=type%>&output="+output
  }
    function deleteCourse(){
        data = document.getElementById("<%=head.get(0)%>delete").value;
        window.location.href ="/managerDeleteCourse?course_id="+data;
    }
</script>
</html>

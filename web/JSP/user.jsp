<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/12/15
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String user = (String)session.getAttribute("user_name");
    %>
<div class="navbar navbar-expand-md navbar-light">
    <div class="navbar-header navbar-dark d-none d-md-flex align-items-md-center">
        <div class="navbar-brand navbar-brand-md">
            <a href="/JSP/index.jsp" class="d-inline-block">
                <img src="../assets/images/logo_light.png" alt="">
            </a>
        </div>

        <div class="navbar-brand navbar-brand-xs">
            <a href="/JSP/index.jsp" class="d-inline-block">
                <img src="../assets/images/logo_icon_light.png" alt="">
            </a>
        </div>
    </div>

    <div class="d-flex flex-1 d-md-none">
        <div class="navbar-brand mr-auto">
            <a href="/JSP/index.jsp" class="d-inline-block">
                <img src="../assets/images/logo_dark.png" alt="">
            </a>
        </div>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-mobile">
            <i class="icon-tree5"></i>
        </button>
        <button class="navbar-toggler sidebar-mobile-main-toggle" type="button">
            <i class="icon-paragraph-justify3"></i>
        </button>
        <button class="navbar-toggler sidebar-mobile-component-toggle" type="button">
            <i class="icon-unfold"></i>
        </button>
    </div>

    <div class="collapse navbar-collapse" id="navbar-mobile">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a href="#" class="navbar-nav-link sidebar-control sidebar-main-hide d-none d-md-block">
                    <i class="icon-paragraph-justify3"></i>
                </a>
            </li>
        </ul>

        <ul class="navbar-nav ml-md-auto">
            <li class="nav-item dropdown">
                <a href="#" class="navbar-nav-link">
                    <%=user%>
                </a>
            </li>
            <li class="nav-item dropdown" style="display:flex;align-items: center">
                <button type="button" onclick="quit()" class="btn btn-info active">
                    注销
                </button>
            </li>
        </ul>
    </div>
</div>
<script>
    function quit(){
        window.location.href ="/quit";
    }
</script>

</html>

<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/12/3
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html  lang="en">
<head>
    <!-- meta data -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!--font-family-->
    <!--    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&amp;subset=devanagari,latin-ext" rel="stylesheet">-->

    <!-- title of site -->
    <title>选课系统登陆</title>

    <!-- For favicon png -->
    <link rel="shortcut icon" type="image/icon" href="../picture/favicon.png"/>

    <!--font-awesome.min.css-->
    <link rel="stylesheet" href="../css/font-awesome.min.css">

    <!--animate.css-->
    <link rel="stylesheet" href="../css/animate.css">

    <!--bootstrap.min.css-->
    <link rel="stylesheet" href="../css/bootstrap.min.css">

    <!-- bootsnav -->
    <link rel="stylesheet" href="../css/bootsnav.css" >

    <!--style.css-->
    <link rel="stylesheet" href="../css/style.css">

    <!--responsive.css-->
    <link rel="stylesheet" href="../css/responsive.css">
    <script>
        function login() {
            user    =  document.getElementById("signin_form").value;
            password = document.getElementById("signin_form1").value;
            if(user!=""&&password!="") {
                window.location.href = "/loginServlet?id=" + user + "&password=" + password;
            }
        }
    </script>
</head>

<body>

<!-- signin end -->
<div class="row">
    <div class="col-sm-12" style="display: flex;justify-content: center;margin-top: 100px">
        <img src="../picture/banner.png" alt="">
    </div>
</div>
<section class="signin">
    <div class="container">

        <div class="sign-content">

            <h2>选课系统登陆</h2>

            <div class="row">
                <div class="col-sm-12">
                    <div class="signin-form">
                        <form action="">
                            <div class="form-group">
                                <label for="signin_form">用户名</label>
                                <input type="text" class="form-control" id="signin_form" placeholder="输入你的学号/工号，例如：S001/T001" style="text-transform: none">
                            </div><!--/.form-group -->
                            <div class="form-group">
                                <label for="signin_form1">密码</label>
                                <input type="password" class="form-control" id="signin_form1" placeholder="输入你的密码" style="text-transform: none">
                            </div><!--/.form-group -->
                        </form><!--/form -->
                    </div><!--/.signin-form -->
                </div><!--/.col -->
            </div><!--/.row -->

            <div class="row">
                <div class="col-sm-12">
                    <div class="signin-password">
                        <div class="awesome-checkbox-list">
                            <ul class="unstyled centered">

                                <li>
                                    <input class="styled-checkbox" id="styled-checkbox-2" type="checkbox" value="value2">
                                    <label for="styled-checkbox-2">记住密码</label>
                                </li>

                                <li>
                                    <a href="#">忘了你的密码？</a>
                                </li>

                            </ul>
                        </div><!--/.awesome-checkbox-list -->
                    </div><!--/.signin-password -->
                </div><!--/.col -->
            </div><!--/.row -->

            <div class="row">
                <div class="col-sm-12">
                    <div class="signin-footer">
                        <button type="submit" onclick="login()" class="btn signin_btn" data-toggle="modal" data-target=".signin_modal">
                            登陆
                        </button>
                    </div><!--/.signin-footer -->
                </div><!--/.col-->
            </div><!--/.row -->

        </div><!--/.sign-content -->
    </div><!--/.container -->

</section><!--/.signin -->

<!-- signin end -->

<!--footer copyright start -->
<footer class="footer-copyright">
    <div >
        <i class="fa fa-angle-double-up return-to-top"  data-toggle="tooltip" data-placement="top" title="" data-original-title="Back to Top" aria-hidden="true"></i>
    </div><!--/.scroll-Top-->

</footer><!--/.hm-footer-copyright-->
<!--footer copyright  end -->


<!-- Include all js compiled plugins (below), or include individual files as needed -->

<script src="../js/jquery.js"></script>

<!--modernizr.min.js-->
<script src="../js/modernizr.min.js"></script>

<!--bootstrap.min.js-->
<script src="../js/bootstrap.min.js"></script>

<!-- bootsnav js -->
<script src="../js/bootsnav.js"></script>

<!-- jquery.sticky.js -->
<script src="../js/jquery.sticky.js"></script>
<script src="../js/jquery.easing.min.js"></script>


<!--Custom JS-->
<script src="../js/custom.js"></script>
</body>

</html>
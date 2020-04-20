<%--
  Created by IntelliJ IDEA.
  User: wang_sir
  Date: 2019/11/16
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>login</title>
    <link rel="stylesheet" type="text/css" href="static/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="static/css/demo.css" />
    <!--必要样式-->
    <link rel="stylesheet" type="text/css" href="static/css/component.css" />
    <!--[if IE]>
    <script src="static/js/html5.js"></script>
    <![endif]-->
    <script type="text/javascript" src="static/js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        <!--校验用户名密码-->
        function checkForm() {
            var username = $("#userName").val();
            var password = $("#password").val();
            if (username==null || username==""){
                $("#error").html("账户有误")
                return false;
            }
            if (password== null || password==""){
                $("#error").html("密码有误");
                return false;
            }
            return true;
        }
   </script>
</head>
<body>
<div class="container demo-1">
    <div class="content">
        <div id="large-header" class="large-header">
            <canvas id="demo-canvas"></canvas>
            <div class="logo_box">
                <h3>星空博客欢迎你</h3>
                <form action="${pageContext.request.contextPath}/login.do" name="f" method="post" onsubmit="return checkForm()">
                    <div class="input_outer">
                        <span class="u_user"></span>
                        <input id="userName" name="userName" class="text" style="color: #FFFFFF !important" type="text" placeholder="请输入账户" value="${blogger.userName}">
                    </div>
                    <div class="input_outer">
                        <span class="us_uer"></span>
                        <input id="password" name="password" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="${blogger.password}" type="password" placeholder="请输入密码">
                    </div>
                    <div class="mb2"><a class="act-but submit" style="color: #FFFFFF"><button type="submit" style="color: blue">登录</button></a></div>
                    <span style="float: left"><a href="${pageContext.request.contextPath}/index.html" style="color: #e0f0dd">个人博客</a></span>
                    <span style=" color:red ;margin: auto "  id="error">${errorInfo}</span>
                </form>
            </div>
        </div>
    </div>
</div><!-- /container -->
<script src="static/js/TweenLite.min.js"></script>
<script src="static/js/EasePack.min.js"></script>
<script src="static/js/rAF.js"></script>
<script src="static/js/demo-1.js"></script>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: wang_sir
  Date: 2019/12/24
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="row">
    <div class="col-md-12" style="padding-top: 10px">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${ctx}/index.html"><font color="black"><strong>首页</strong></font></a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" >
                    <div class="navbar-brand">
                    <a class="nav navbar-nav">
                        <!-- <li><a href="#"><font color="black"><strong>CSDN博客</strong></font color="black"></a></li>-->
                        <li><a href="${ctx}/login.jsp" target="_blank"><font color="red"><strong>后台登录</strong></font></a></li>
                    </a>
                    </div>
                </div>
            </div>
        </nav>
    </div>
</div>
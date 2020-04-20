<%--
  Created by IntelliJ IDEA.
  User: wang_sir
  Date: 2019/12/1
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>博客评论审核管理页面</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="${ctx}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script>
        /**
         * 给标题加上链接并显示博客标题
         */
        function formatBlogTitle(value,row) {
            return "<a  target='_blank' href='${ctx}/blog/articles/"+value.id+".html'>"+value.title+"</a>";
        }

        /**
         * 评论状态的提交
         */
        function updateState(state) {
            var selections = $("#dg").datagrid("getSelections");
            if (selections.length==0){
                $.messager.alert("系统提示","请选择要审核的评论");
            }
            var select=[];
            for (var i=0;i<selections.length;i++){
                select.push(selections[i].id);
            }
            var ids=select.join(",");
            $.messager.confirm("系统提示","您确定要审核这<font color=red>"+selections.length+"</font>条数据吗？",function(r) {
                if (r){
                    $.post("${ctx}/admin/comment/updateState.do",{ids:ids, state:state},function (result) {
                        if (result.success){
                            $.messager.alert("系统提示","审核已提交");
                            $("#dg").datagrid("reload");
                        }else{
                            $.messager.alert("系统提示","审核已提交");
                            $("#dg").datagrid("reload");
                        }
                    },"json");
                }
            });
        }
    </script>
</head>
<body>
<table id="dg" title="评论管理" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true"
       url="${ctx}/admin/comment/list.do?state=0" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox='true' align='center'></th>
        <th field='id' width='20' align='center'>编号</th>
        <th field='blog' width='200' align='center' formatter="formatBlogTitle">博客标题</th>
        <th field='userIp' width='100' align='center'>用户IP</th>
        <th field='content' width='200' align='center'>评论内容</th>
        <th field='commentDate' width='50' align='center'>评论日期</th>
    </tr>
    </thead>
</table>
<div id="tb" >
<a href="javascript:updateState(1)" class="easyui-linkbutton" iconCls="icon-ok" plain="true">审核通过</a>
<a href="javascript:updateState(2)" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">审核未通过</a>
</div>
</body>
</html>

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
    <title>博客评论管理页面</title>
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
         * 显示评论状态
         */
        function formatState(val, row) {
            if (val == 0) {
                return '待审核';
            } else if (val == 1) {
                return '审核通过';
            } else {
                return '审核未通过';
            }
        }

        /**
         * 删除评论
         */
        function deleteComment() {
            var selections = $("#dg").datagrid("getSelections");
            if (selections.length==0){
                $.messager.alert("系统提示","请选择要删除的数据");
                return;
            }
            var selectIds =[];
            for(var i=0;i<selections.length;i++){
                selectIds.push(selections[i].id);
            }
            var ids = selectIds.join(",");
                $.messager.confirm("系统提示","您确定要删除这<font color=red>"+selections.length+"</font>条数据吗？",function(r) {
                if (r){
                    $.post("${ctx}/admin/comment/delete.do",{ids:ids},function (result) {
                        if (result.success){
                            $.messager.alert("系统提示","数据已成功删除！");
                            $("#dg").datagrid("reload");
                        }else{
                            $.messager.alert("系统提示","数据删除失败！");
                        }
                    },"json");
                }
            });
        }
    </script>
</head>
<body>
<table id="dg" title="评论管理" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true"
       url="${ctx}/admin/comment/list.do" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox='true' align='center'></th>
        <th field='id' width='20' align='center'>编号</th>
        <th field='blog' width='200' align='center' formatter="formatBlogTitle">博客标题</th>
        <th field='userIp' width='100' align='center'>用户IP</th>
        <th field='content' width='200' align='center'>评论内容</th>
        <th field='commentDate' width='50' align='center'>评论日期</th>
        <th field='state' width='20' align='center' formatter='formatState'>评论状态</th>
    </tr>
    </thead>
</table>
<div id="tb" >
<a href="javascript:deleteComment()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
</div>
</body>
</html>

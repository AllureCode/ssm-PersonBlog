<%--
  Created by IntelliJ IDEA.
  User: wang_sir
  Date: 2019/11/20
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set   var="ctx" value=" ${pageContext.request.contextPath}"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>博客管理页面</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="${ctx}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        function formatBlogType(val,row){  //val代表字段的值 row代表行记录数据
            return val.typeName;
        }
        //搜索
        function searchBlog(){
            $("#dg").datagrid('load',{
                "title":$("#s_title").val()
            });
        }
        //修改
        function openBlogModifyTab() {
            //选择修改的数据
            var selectedRows = $("#dg").datagrid('getSelections');
            if (selectedRows.length!=1){
                $.messager.alert("系统提示","请选择一个要修改的博客");
                return ;
            }
            var row = selectedRows[0];
            //打开父页面调用其方法
            window.parent.openTab('修改博客',"modifyBlog.jsp?id="+row.id,"icon-writeBlog");
        }
        //删除
        function deleteBlog() {
            var selectedRows = $("#dg").datagrid('getSelections');
            if (selectedRows.length==0){
                $.messager.alert("系统提示","请选择一个要删除的博客");
                return ;
            }
            var strIds=[];
            for(var i = 0;i<selectedRows.length;i++){
                strIds.push(selectedRows[i].id);
            }
            //将数据转为字符串
            var ids=strIds.join(",");
            $.messager.confirm("系统提示","您确定要删除所选的<font color=red>"+selectedRows.length+"</font>数据吗",
            function (r) {
                if (r){
                    $.post("${ctx}/admin/blog/delete.do",{"ids":ids},function (result) {
                        if (result.success){
                            $.messager. alert("系统提示","删除成功");
                            $("#dg").datagrid("reload"); //重新加载表格数据
                        }else{
                            $.messager. alert("系统提示","删除失败");
                        }
                    },"json");
                }
            });
        }

        /**
         * 给标题加上链接
         */
        function formatTitle(value,row) {
            return "<a  target='_blank' href='${ctx}/blog/articles/"+row.id+".html'>"+value+"</a>";
        }
    </script>
</head>
<body style="margin: 10px">
<table id="dg" title="博客管理" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true" url="${ctx}/admin/blog/list.do" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="id" width="20" align="center">编号</th>
        <th field="title" width="200" align="center" formatter="formatTitle">标题</th>
        <th field="releaseDate" width="50" align="center">发布日期</th>
        <th field="blogType" width="50" align="center" formatter="formatBlogType">博客类别</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <div>
        <a href="javascript:openBlogModifyTab()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript:deleteBlog()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <div>
        &nbsp;标题：&nbsp;<input type="text" id="s_title" size="20" onkeydown="if(event.keyCode==13) searchBlog()"/>
        <a href="javascript:searchBlog()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>
</body>
</html>

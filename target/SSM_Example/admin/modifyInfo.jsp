<%--
  Created by IntelliJ IDEA.
  User: wang_sir
  Date: 2019/12/5
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>个人信息修改页面管理</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="${ctx}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" charset="gbk" src="${ctx}/static/newUeditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="gbk" src="${ctx}/static/newUeditor/ueditor.all.min.js"> </script>
    <script type="text/javascript">
        function submitData() {
            var nickName = $("#nickName").val();
            var signature = $("#signature").val();
            var proFile = UE.getEditor("editor").getContent();
            if (nickName==null || nickName==""){
                $.messager.alert("系统提示","请输入昵称");
            }else if(signature==null || signature==""){
                $.messager.alert("系统提示","请输入个性签名");
            }else if (proFile==null || proFile==""){
                $.messager.alert("系统提示","请输入简介");
            }else{
                $("#profile").val(proFile); //将新的profile赋值到profile中
                $("#form1").submit();
            }
        }
    </script>
</head>
<body style="margin: 10px">
<div class="esayui-panel" title="修改个人信息" style="padding: 10px">
    <form id="form1" action="${ctx}/admin/blogger/save.do" method="post" enctype="multipart/form-data">
        <table  cellspacing="20px">
            <tr>
                <td width="80px">用户名：</td>
                <td>
                    <input type="hidden" id="id" name="id" value="${currentUser.id }"/>
                    <input type="text" id="userName" name="userName" style="width: 200px;" value="${currentUser.userName }" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td>昵称：</td>
                <td><input type="text" id="nickName" name="nickName"   value="${currentUser.nickName}" style="width: 200px;"/></td>
            </tr>
            <tr>
                <td>个性签名：</td>
                <td><input type="text" id="signature" name="signature" value="${currentUser.signature}" style="width: 400px;"/></td>
            </tr>
            <tr>
                <td>个人头像：</td>
                <td><input type="file" id="imageFile" name="imageFile" style="width: 400px;"/></td>
            </tr>
            <tr>
                <td valign="top">个人简介：</td>
                <td>
                    <script id="editor" type="text/plain" style="width:100%;height:500px;"></script>
                    <input type="hidden" id="profile" name="profile"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <a href="javascript:submitData()" class="easyui-linkbutton" data-options="iconCls:'icon-submit'">提交</a>
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    //实例化编辑器
    var ue = UE.getEditor("editor");
  ue.addListener("ready",function () {
      UE.ajax.request("${ctx}/admin/blogger/find.do",{
          method:"post",
          async:false,
          data:{},
          onsuccess:function (result) {
              result=eval("("+result.responseText+")");
              UE.getEditor("editor").setContent(result.profile)
          }
      });
  });
</script>
</body>
</html>

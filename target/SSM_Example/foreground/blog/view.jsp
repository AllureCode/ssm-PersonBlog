<%--
  Created by IntelliJ IDEA.
  User: wang_sir
  Date: 2019/12/24
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
    <%--显示全部评论--%>
    function showComment(){
        $(".otherComment").show();
    }
    <%--重新加载验证码--%>
    function loadImage() {
        $("#randImage").src("${ctx}/image.jsp?"+Math.random());
    }

    <%--提交评论--%>
    function submitData() {
        var content = $("#content").val();
        var imageCode = $("#imageCode").val();
        if(content==null || content==''){
            alert("请输入评论内容");
        }else if (imageCode==null || imageCode==''){
            alert("请输入验证码");
        }else{
            $.post("${ctx}/comment/save.do",{"content":content,"imageCode":imageCode,"blog.id":"${blog.id}"},
                function (result) {
                    if(result.success){
                        window.location.reload();
                        alert("评论已提交,审核通过后显示");
                    }else{
                        alert(result.errorInfo);
                    }
                },"json");
        }
    }

</script>
<div class="data_list">
    <div class="data_list_title">
        <img src="${ctx}/static/img/blog_show_icon.png">
        博客信息
    </div>

    <div>
        <div class="blog_title"><h3><strong>${blog.title}</strong></h3>
        </div>

        <!--显示博客内容-->
        <div class="blog_content">
            ${blog.content}
        </div>

        <div style="padding-left: 330px;padding-bottom:20px;padding-top: 10px">
            <div class="bshare-custom"><a title="分享到QQ空间" class="bshare-qzone"></a>
                <a title="分享到新浪微博" class="bshare-sinaminiblog">
                </a><a title="分享到人人网" class="bshare-renren"></a>
                <a title="分享到腾讯微博" class="bshare-qqmb"></a>
                <a title="分享到网易微博" class="bshare-neteasemb"></a>
                <a title="更多平台" class="bshare-more bshare-more-icon more-style-addthis">
                </a><span class="BSHARE_COUNT bshare-share-count">0</span>
            </div>
            <!--引入第三方标签 完成分享功能-->
            <script type="text/javascript" charset="utf-8"
                    src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=&amp;pophcol=1&amp;lang=zh">
            </script>
            <script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/bshareC0.js"></script>
        </div>
    </div>

    <div class="blog_info">
        发布时间：【<fmt:formatDate value="${blog.releaseDate}" type="date" pattern="yyyy年MM月dd HH:mm:ss"></fmt:formatDate> 】
        &nbsp;&nbsp;博客类别：${blog.blogType.typeName}
        &nbsp;&nbsp;阅读：${blog.clickHit}
        &nbsp;&nbsp;评论：${blog.replyHit}
    </div>
    <!--显示上一篇下一篇博客-->
    <div class="blog_lastAndNextPage">
        ${pageCode }
    </div>
</div>
<%--评论信息的显示--%>
<div class="data_list">
    <div class="data_list_title">
        <img src="${ctx}/static/img/comment_icon.png">
        评论信息
        <c:if test="#{commentList.size()>10}">
            <a href="javascript:showComment();" style="float:right;padding-right: 40px">显示所有评论</a>
        </c:if>
    </div>
    <div class="commentDatas">
        <c:choose>
            <c:when test="${commentList.size()==0}">
                暂无评论
            </c:when>
            <c:otherwise>
                <c:forEach var="comment" items="${commentList}" varStatus="status">
                    <c:choose>
                        <%--小于10条评论 --%>
                        <c:when test="${status.index<10}">
                            <div class="comment">
                                <span>
                                    ${status.index+1}楼&nbsp;&nbsp;&nbsp;&nbsp;
                                    ${comment.userIp}:
                                    ${comment.content}:&nbsp;&nbsp;&nbsp;&nbsp;
                                   评论时间【<fmt:formatDate  type="date" pattern="yyyy-MM-dd HH:mm:ss" value="${comment.commentDate}"/>】
                                </span>
                            </div>
                        </c:when>
                        <%--大于10条评论显示所有评论--%>
                        <c:otherwise>
                            <div class="otherComment">
                                <div class="comment">
                                  <span>
                                    ${status.index+1}楼&nbsp;&nbsp;&nbsp;&nbsp;
                                    ${comment.userIp}:
                                    ${comment.content}:&nbsp;&nbsp;&nbsp;&nbsp;
                                    评论时间【<fmt:formatDate  type="date" pattern="yyyy-MM-dd HH:mm:ss" value="${comment.commentDate}"/>】
                                </span>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<div class="data_list">
    <div class="data_list_title">
        <img src="${ctx}/static/img/publish_comment_icon.png">
        发表评论
    </div>
    <div class="publish_comment">
        <div>
            <textarea id="content" name="content" rows="3" style="width: 100%" placeholder="你可以发表您的观点....."></textarea>
        </div>
        <div class="verCode">
            验证码:<input type="text" name="imageCode" id="imageCode" size="10" onkeydown="if(event.keyCode==13) submitData()"/>
            &nbsp;<img src="${ctx}/image.jsp" name="randImage" id="randImage" title="点击换一张" onclick="javascript:loadImage();" width="60" height="20" border="1" align="absmiddle">
        </div>
        <div class="publishButton">
            <button class="btn btn-primary" type="button" onclick="submitData()">发表评论</button>
        </div>
    </div>
</div>



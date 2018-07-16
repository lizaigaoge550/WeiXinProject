<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>客户列表</title>
</head>
<body>
    <h3 align="center" >个人信息</h3>
    <table border="1" width="70%" align="center">
        <tr>
            <th>wx_id</th>
            <th>top</th>
            <th>phone</th>
            <th>minText</th>
            <th>全文</th>
        	<th>编辑</th>
        	<th>删除</th>
        </tr>
        <c:forEach items="${pb.beanList}" var="cstm">
        <tr>
            <td>${cstm.wxId}</td>
            <td>${cstm.top}</td>
            <td>${cstm.phone}</td>
            <td>${cstm.miniText}</td>
            <td>
            	<a href="<c:url value='/JobHuntingServlet?method=CheckPublishMessage&id=${cstm.id}'/>" >link</a>
            </td>
             <td>
             	<a href="<c:url value='/JobHuntingServlet?method=EditMessage&id=${cstm.id}'/>" >link</a>
            </td>
             <td>
             	<a href="<c:url value='/JobHuntingServlet?method=DeleteMessage&id=${cstm.id}'/>" >link</a>
            </td>
        </tr>
        </c:forEach>
    </table>
<br/>
<center>
    第${pb.pc}页/共${pb.tp}页
    <a href="${pb.url}&pc=1">首页</a>
    <c:if test="${pb.pc>1}">
        <a href="${pb.url}&pc=${pb.pc-1}">上一页</a>
    </c:if>


    <%--循环遍历页码列表--%>
    <c:forEach var="i" begin="${begin}" end="${end}">
        <c:choose>
            <c:when test="${i eq pb.pc}">
                [${i}]
            </c:when>
            <c:otherwise>
                <a href="${pb.url}&pc=${i}">[${i}]</a>
            </c:otherwise>
        </c:choose>

    </c:forEach>


    <c:if test="${pb.pc<pb.tp}">
    <a href="${pb.url}&pc=${pb.pc+1}">下一页</a>
    </c:if>
    <a href="${pb.url}&pc=${pb.tp}">尾页</a>

</center>

</body>
</html>
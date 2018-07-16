%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>客户列表</title>
</head>
<body>
    <h3 align="center"></h3>
    <table border="1" width="70%" align="center">
        <tr>
            <th>wx_id</th>
            <th>top</th>
            <th>phone</th>
            <th>minText</th>
            <th>全文</th>
        </tr>
        <c:forEach items="${pb.beanList}" var="cstm">
        <tr>
            <td>${cstm.wxId}</td>
            <td>${cstm.top}</td>
            <td>${cstm.phone}</td>
            <td>${cstm.miniText}</td>
        </tr>
        </c:forEach>
    </table>
</body>
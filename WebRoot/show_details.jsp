<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>客户列表</title>
</head>
<body>
    <h3 align="center" >客户列表</h3>
    <table border="1" width="70%" align="center">
        <tr>
            <th>wx_id</th>
            <th>top</th>
            <th>phone</th>
            <th>text</th>
            <th>image</th>
        </tr>
        <tr>
            <td>${record.wxId}</td>
            <td>${record.top}</td>
            <td>${record.phone}</td>
            <td>${record.text}</td>
            <td>
            	<img src='${record.image}'/>
            </td>
        </tr>
    </table>
<br/>

</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>客户列表</title>
</head>
<body>
<form action="<c:url value='/JobHuntingServlet?method=ModifyMessage'/>" method="post">
    <table border="1" width="70%" align="center">
     	<input type="hidden" name="id" value="${record.id}" />
        <input type="hidden" name="wx_id" value="${record.wxId}" />
        <tr>
            <td>置顶</td>
            <td>
                <input type="text" name="top" id="top" value="${record.top}"/>
            </td>
        </tr>
        
        <tr>
            <td>类型(全职or兼职)</td>
            <td>
                <input type="text" name="type" id="type" value="${record.type}"/>
            </td>
        </tr>
        
        <tr>
            <td>积分</td>
            <td>
                <input type="text" name="grade" id="type" value="${record.grade}"/>
            </td>
        </tr>
        
        <tr>
            <td></td>
            <td>
                <input type="submit" name="submit"/>
                <input type="reset" name="reset"/>
            </td>
        </tr>
    </table>
<br/>

</body>
</html>
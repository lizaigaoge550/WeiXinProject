<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>主页</title>
</head>
<form action="<c:url value='/JobHuntingServlet?method=UserLogin'/>" method="post">
    <input type="hidden" name="method" value="add">
    <table border="0" align="center" width="40%" style="margin-left: 100px">
        <tr>
        	<td width="100px">wx_id</td>
            	<td width="40%">
                	<input type="text" name="wx_id"/>
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
</html>
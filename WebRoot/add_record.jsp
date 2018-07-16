<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3 align="center">添加记录</h3>
<!-- wx_id, title, category, phone, text, image, top, type, start_time, end_time, view_number, comments -->
<form action="<c:url value='/JobHuntingServlet?method=AddPublishMessage'/>" method="post" enctype="multipart/form-data">
    <table border="0" align="center" width="40%" style="margin-left: 100px">
        <tr>
            <td>微信id</td>
            <td>
                <input type="text" name="wx_id" id="wx_id"/>
            </td>
        </tr>
        
        <tr>
           	<td>标题</td>
            <td>
                <input type="text" name="title" id="title"/>
            </td>
        </tr>
        
        <tr>
        	<td>类别</td>
            <td>
                <input type="text" name="category" id="category"/>
            </td>
        </tr>
        
        <tr>
        	<td>电话号码</td>
        	<td>
                <input type="text" name="phone" id="phone"/>
            </td>
        </tr>
        
        <tr>
            <td>描述</td>
            <td>
                <input type="text" name="description" id="description"/>
            </td>
        </tr>
        
        <tr>
            <td>图片</td>
            <td>
                <input type="file" name="image" id="image"/>
            </td>
        </tr>
        
        <tr>
            <td>置顶</td>
            <td>
                <input type="text" name="top" id="top"/>
            </td>
        </tr>
        
        <tr>
            <td>类型(全职or兼职)</td>
            <td>
                <input type="text" name="type" id="type"/>
            </td>
        </tr>
        
        <tr>
            <td>姓名</td>
            <td>
                <input type="text" name="name" id="name"/>
            </td>
        </tr>
        
        <tr>
            <td>性别</td>
            <td>
                <input type="text" name="gender" id="type"/>
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
</form>

</body>
</html>
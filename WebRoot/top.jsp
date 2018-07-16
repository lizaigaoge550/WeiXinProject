<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- 他的作用是为本页面所有的表单和超链接指定显示内容的框架-->
    <base target="main">
    <title>My JSP 'top.jsp' starting page</title>
</head>
<body style="text-align: center;">
    <h1>客户关系管理系统</h1>
    <a href="<c:url value='/add_record.jsp'/>">添加记录</a>
    <a href="<c:url value='/login.jsp'/>">登录</a>
    <a href="<c:url value='/JobHuntingServlet?method=PublishMessagesList'/>">记录列表</a>
    <a href="<c:url value='/JobHuntingServlet?method=ShowUserInfo'/>">查看个人信息</a>
    <a href="<c:url value='/inquire.jsp'/>">查询</a>
</body>
</html>
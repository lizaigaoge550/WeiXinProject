<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
  </head>
  
  <body>
    <form action="<c:url value='/JobHuntingServlet?method=QueryBasedOnWxId'/>" method="post">
    	<div><input type='text' name='wx_id'></div>
    	<div><input type='submit' nane='submit'></div>
  	</form>
  </body>
</html>

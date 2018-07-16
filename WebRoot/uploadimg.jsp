
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <title>My JSP 'upload.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
    <form action="<c:url value='/CustomerServlet?method=getImage'/>" method="post" enctype="multipart/form-data">	
   		
   		<table border="0" align="center" width="40%" style="margin-left: 100px">

        	<tr>  
         		<td>描述:<input type="text" name="description" id="description" /></td>
        	</tr>
         	<tr>      
         		<td>请选择上传的图片或文件:<input type="file" name="fileName"/></td>
        	</tr>
         
        	<tr>
        		<td><input type="submit" value="上传"/></td>
        	</tr>
       </table>
    </form>
  </body>

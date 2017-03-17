<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
  <body>
  	<c:forEach items="${fs }" var="f">
  		<a href="down.do?fname=${f }">下载【${f }】</a><br/>
  	</c:forEach>
  </body>
</html>

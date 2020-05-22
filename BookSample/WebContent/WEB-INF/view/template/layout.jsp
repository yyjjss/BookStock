<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<!--문자열 받아 올땐 ":getAsString"  -->
<title>야 너두 책 읽을 수 있어</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"/>
</head>
<body>
 <div class="app-container app-theme-white body-tabs-shadow fixed-sidebar fixed-header">
	<!--경로 받아 올때 ":insertAttribute"  -->
	<tiles:insertAttribute name="header"/>
	<tiles:insertAttribute name="body"/>
	
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- 
<link href="css/layout.css" rel="stylesheet" type="text/css">
-->

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/layout.css" />
		<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/global.css" />
 
<title><tiles:insertAttribute name="title" /></title>
</head>
<body>
	
		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>
		
	<div style="clear: both;"></div>
	<div id="globalContainer">


		<div id="content">
			
			<div id="left">
			<div id="userInfo">
				<tiles:insertAttribute name="menu" />
			</div>
			</div>
			<div id="middle">
				<tiles:insertAttribute name="content" />
			</div>
			<div id="right">
				<tiles:insertAttribute name="right" />
			</div>
			<div style="clear: both;"></div>
		</div>
		<div style="clear: both;"></div>
	</div>
	
	<div id="footer">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>
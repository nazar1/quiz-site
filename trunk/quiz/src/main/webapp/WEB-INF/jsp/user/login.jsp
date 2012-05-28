<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/layout.css">

<title><tiles:insertAttribute name="title" /></title>
</head>
<body>
	<div id="header">
		<tiles:insertAttribute name="header" />
	</div>
	<div id="globalContainer">


		<div id="content">


			<div id="login">

				<div id="formBox">

					<form:form method="post" modelAttribute="user">
						<table>
							<tr>
								<td class="label">User Name</td>
								<td class="label">Password</td>
							</tr>
							<tr>
								<td><form:input path="userName" /></td>
								<td><form:input path="password" /></td>
								<td><input class="myButton" type="submit" value="Sign up" />
							</tr>
							<tr>
								<td><form:errors path="userName" cssClass="error" /></td>
								<td><form:errors path="password" cssClass="error" /></td>
							</tr>
						</table>
					</form:form>
				</div>
			</div>

		</div>
	</div>
	<div style="clear: both;"></div>
	<div id="footer">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>
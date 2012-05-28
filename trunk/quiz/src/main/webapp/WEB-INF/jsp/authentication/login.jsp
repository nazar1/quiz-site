<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login Page</title>

</head>
<body onload='document.f.j_username.focus();'>

<div class="login">
	<h5>Login with Username and Password</h5>
 
	<c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
 
	<form name='f' action="<c:url value='j_spring_security_check' />"
		method='POST'>
 		

				<label>User Name:</label><br />
				<input type='text' name='j_username' value='' /><br />

				<label>Password:</label><br />
				<input type='password' name='j_password' /><br />

				<input name="submit" type="submit" value="Login" class="yellowButton"/>
				<!-- 
				<input name="reset" type="reset" class="yellowButton"/><br />
				 -->

	</form>
	
	<a href="/quiz/signup">Create Account</a>
	<br />
	<br />
	<br />
	</div>
</body>
</html>
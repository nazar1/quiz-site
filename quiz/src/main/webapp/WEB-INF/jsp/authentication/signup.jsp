<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="signup">
	<h5>Sign Up</h5>

	<form:form method="post" modelAttribute="user">

		<spring:hasBindErrors name="user">
			<div class="errorblock">
				<form:errors path="userName" cssClass="error" />
				<br />
				<form:errors path="password" cssClass="error" />
				<br />
				<form:errors path="email" cssClass="error" />
				<br />

			</div>
		</spring:hasBindErrors>

		<label>User Name</label>
		<br />
		<form:input path="userName" />
		<br />

		<label>Password</label>
		<br />
		<form:input path="password" />
		<br />

		<label>Email</label>
		<br />
		<form:input path="email" />
		<br />

		<input class="myButton" type="submit" value="Sign up" />
		<br />


	</form:form>
</div>

<div style="clear: both;"></div>

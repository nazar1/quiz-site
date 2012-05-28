<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
	<h5>Create Announcement</h5>
	
	<form:form method="post" modelAttribute="announcement">

		<spring:hasBindErrors name="announcement">
			<div class="errorblock">
				<form:errors path="announcement" cssClass="error" />
				<br />
			</div>
		</spring:hasBindErrors>

		<label>Announcement Text:</label>
		<br />

		<form:textarea path="announcement" />
		<br />

		<input class="myButton" type="submit" value="Submit" />
		<br />
	</form:form>
</div>

<div style="clear: both;"></div>

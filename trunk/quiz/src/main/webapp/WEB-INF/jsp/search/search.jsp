<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
	<h5>Search for users</h5>
	
	
	<c:if test="${not empty searchMessage}">
		<div class="errorblock">
		<c:out value="${searchMessage}" />
		</div>
	</c:if>
	
	<form:form method="post" modelAttribute="searchUser">

		<spring:hasBindErrors name="user">
			<div class="errorblock">
				<form:errors path="userName" cssClass="error" />
				<br />
			</div>
		</spring:hasBindErrors>

		<label>Enter User Name</label>
		<br />
		<form:input path="userName" />
		<br />

		<input class="myButton" type="submit" value="Find" />
		<br />
	</form:form>
</div>

<div style="clear: both;"></div>

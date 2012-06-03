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

<!-- section displayed after search found the user -->
	<c:if test="${not empty friendRequestSent}">
		<div class="message">
		<c:out value="${friendRequestSent}" />
		</div>
	</c:if>
	<c:if test="${not empty searchUser.userName}">
		<div>

			<c:if test="${not empty messageSendResult}">
				<div class="message">
					<c:out value="${messageSendResult}" />
				</div>
			</c:if>

			<div class="boldText">
				Would you like to send a message to user
				<c:out value="${searchUser.userName}" />
				?
			</div>
			<ul>
				<li class="friendButllet"><a class="menu"
					href="/quiz/user/sendFriendRequest">Send Friend Request</a></li>
			</ul>
		</div>
	</c:if>


<div style="clear: both;"></div>

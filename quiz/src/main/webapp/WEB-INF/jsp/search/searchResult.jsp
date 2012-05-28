<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div>

	<c:if test="${not empty messageSendResult}">
		<div class="message">
		<c:out value="${messageSendResult}" />
		</div>
	</c:if>
	
	<div class="boldText">
	Would you like to send a message to user <c:out value="${searchUser.userName}" />?
	</div>
	<ul>
		<li class="friendButllet"><a class="menu" href="/quiz/user/sendFriendRequest">Send Friend Request</a></li>
		<li class="noteButllet"><a class="menu" href="/quiz/user/sendNote">Send a Note</a></li>
		<li class="challengeButllet"><a class="menu" href="/quiz/user/sendChallenge">Send a Quiz Challenge</a></li>	
	</ul>
	
	
</div>
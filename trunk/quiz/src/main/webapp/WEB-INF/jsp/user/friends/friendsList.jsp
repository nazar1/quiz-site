<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="blockContent">
	<table>
		<c:forEach items="${friends}" var="user">
			<tr>
				<td><c:out value="${user.userName}" /></td>
				<td><a class="" title="sendNote"
					href="/quiz/user/sendNote/${user.id}" >Send Note</a>
				</td>
				<td><a class="" title="sendChallenge"
					href="/quiz/user/sendChallenges/${user.id}" >Send Challenge</a>
				</td>
				<td><a class="" title="delete"
					href="/quiz/user/deleteFriend/${user.id}" >Delete Friend</a>
				</td>
				
			</tr>
		</c:forEach>
	</table>

</div>
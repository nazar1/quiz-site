<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="blockContent">
	<c:choose>
	 <c:when test="${empty friendRequests}" >
		<p class="info">There are no friend Requests.</p>
	</c:when>
	<c:otherwise>
	<table>
		<tr>
			<th>Name</th>
			<th>Date</th>

		</tr>
		<c:forEach items="${friendRequests}" var="message">
			<tr>
				<td><c:out value="${message.sender.userName}" /></td>
				<td><fmt:formatDate type="date" value="${user.created}" /></td>
				<td width="50"><a class="demoteIcon" title="Accept"
					href="/quiz/user/friendRequest/accept/${message.id}"></a> <a
					class="deleteIcon" title="Deny"
					href="/quiz/user/friendRequest/deny/${message.id}"></a></td>
			</tr>
		</c:forEach>
	</table>
	</c:otherwise>
</c:choose>
</div>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="blockContent">
	<c:choose>
	 <c:when test="${empty notes}" >
		<p class="info">There are no notes for your.</p>
	</c:when>
	<c:otherwise>
	<table>
		<tr>
			<th>Name</th>
			<th>Date</th>

		</tr>
		<c:forEach items="${notes}" var="message">
			<tr>
				<td class="capFirstLetter"><c:out value="${message.sender.userName}" /></td>
				<td><c:out value="${message.message}" /></td>
				<td><fmt:formatDate type="date" value="${user.created}" /></td>
				<td width="50">
					<a class="deleteIcon" title="Delete"
					href="/quiz/user/notes/delete/${message.id}"></a></td>
			</tr>
		</c:forEach>
	</table>
	</c:otherwise>
</c:choose>
</div>
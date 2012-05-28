<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div>
	<c:if test="${not empty message}">
		<div class="message">
			<c:out value="${message}" />
		</div>
	</c:if>

	<div class="leftBlock">
		<div class="portlet-header ui-widget-header ui-corner-top">
			Latest Announcements</div>

		<div class="blockContent">

			<table>

				<c:forEach items="${announcements}" var="announcement">
					<tr>
						<td><c:out value="${announcement.announcement}" /></td>
						<td width="50"><a class="demoteIcon" title="Deactivate"
							href="/quiz/admin/announcement/deactivate/${announcement.id}"></a>
							<a class="deleteIcon" title="Delete"
							href="/quiz/admin/announcement/delete/${announcement.id}"></a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

	<div class="rightBlock">
		<div class="portlet-header ui-widget-header ui-corner-top">
			Site Statistics</div>

		<div class="blockContent">

			<table>
				<tr>
					<td width="20%">Users:</td>
					<td width="70%"><c:out value="${statistics.userCount}" /></td>
				</tr>

				<tr>
					<td width="20%">Quizzes:</td>
					<td width="70%"><c:out value="${statistics.quizCount}" /></td>
				</tr>
				<tr>
					<td width="20%">Announcements:</td>
					<td width="70%"><c:out
							value="${statistics.activeAnnouncementsCount}" /></td>
				</tr>
			</table>
		</div>
	</div>
	<div style="clear: both;"></div>
	<div id="users">
		<div class="portlet-header ui-widget-header ui-corner-top">
			Recently Resistered Users</div>
		<div class="blockContent">
			<table>
				<tr>
					<th>Name</th>
					<th>Authority</th>
					<th>Email</th>
					<th>Date</th>

				</tr>
				<c:forEach items="${recentUsers}" var="user">
					<tr>
						<td><c:out value="${user.userName}" /></td>
						<td><c:out value="${user.authority}" /></td>
						<td><c:out value="${user.email}" /></td>
						<td><fmt:formatDate type="date" value="${user.created}" /></td>
						<td width="50"><a class="demoteIcon" title="promote"
							href="/quiz/admin/user/promote/${user.id}"></a> <a
							class="deleteIcon" title="Delete"
							href="/quiz/admin/user/delete/${user.id}"></a></td>
					</tr>
				</c:forEach>
			</table>

		</div>
	</div>

</div>
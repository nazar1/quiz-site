<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>





	<div id="announcements">
		<h4>Announcements</h4>

		<table>

			<c:forEach items="${announcements}" var="announcement">
				<tr>
					<td class="text"><c:out value="${announcement.announcement}" /></td>
					<td class="date"><fmt:formatDate type="date" dateStyle="short"
							value="${announcement.created}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<div>

		<h4>Your recently created quizes</h4>

		<c:forEach items="${recentUserCreatedQuizes}" var="quiz">
			<table class="detailsList">
				<tr>
					<td><c:out value="${quiz.quizTitle}" /></td>
					<td class="date"><fmt:formatDate type="date" dateStyle="short"
							value="${quiz.created}" /></td>
				</tr>
				<tr>
					<td><c:out value="${quiz.description}" /></td>
				</tr>
			</table>
		</c:forEach>

	</div>

	<div>
	<h4>Your recently quiz-taking activities</h4>
	<table>
		<tr>
			<th>Questions (#)</th>
			<th>Correct Answers(#)</th>
			<th>Score</th>
			<th>Time</th>
			<th>Date</th>
		</tr>
		<c:forEach items="${userQuizHistory}" var="quizHistory">
			<tr>
				<td><c:out value="${quizHistory.numberOfQuestions}" /></td>
				<td><c:out value="${quizHistory.correctAnswers}" /></td>
				<td><c:out value="${quizHistory.score}%" /></td>
				<td><c:out value="${quizHistory.time}" /></td>
				<td><fmt:formatDate type="date" value="${quizHistory.created}" /></td>
			</tr>
		</c:forEach>
	</table>
</div>

	<h4>All Recently Created Quizes</h4>
	<table>
		<tr>
			<th>Title</th>
			<th>Description</th>
			<th>Category</th>
			<th>Creation Date</th>
		</tr>
		<c:forEach items="${recentCreatedQuizes}" var="quiz">
			<tr>
				<td><c:out value="${quiz.quizTitle}" /></td>
				<td><c:out value="${quiz.description}" /></td>
				<td><c:out value="${quiz.category}" /></td>
				<td><fmt:formatDate type="date" value="${quiz.created}" /></td>

			</tr>
		</c:forEach>
	</table>


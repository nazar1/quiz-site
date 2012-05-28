<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="quizList">

	<h5>Popular Quizes:</h5>
	<ul>
	<c:forEach items="${createdQuizes}" var="quiz">
		<li class="quizListButllet"><a class="quizList" href="/quiz/user/takeQuiz/${quiz.id}"><c:out value="${quiz.quizTitle}" /></a></li>
	</c:forEach>
	</ul>
</div>



<div class="quizList">

	<h5>Recently Created Quizes:</h5>
	<ul>
	<c:forEach items="${createdQuizes}" var="quiz">
		<li class="quizListButllet"><a class="menu" href="/quiz/user/takeQuiz/${quiz.id}"><c:out value="${quiz.quizTitle}" /></a></li>
	</c:forEach>
	</ul>
</div>
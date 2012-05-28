<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.nazarmerza.quiz.domain.types.QuestionType"%>
<%@ page import="java.util.*"%>

<div id="takeQuiz">
	<h4>Tak Quiz</h4>
	<div id="quizDescription" >
	<table>
	<tr>
		<td><label>Title: </label></td>
		<td><c:out value="  ${quiz.quizTitle}" /></td>
	</tr>
	
		<tr>
		<td><label>Description: </label></td>
		<td><c:out value="${quiz.description}" /></td>
	</tr>
	
		<tr>
		<td><label>Category:</label></td>
		<td><c:out value="${quiz.category}" /></td>
	</tr>

	
	</table>
	</div>
	<br />
	

	<form:form method="post" modelAttribute="questions">
		<spring:hasBindErrors name="questions">
			<div class="errorblock">
				<form:errors path="questionQuery.answer" />
				<br />
				<form:errors path="questionFillInBlank.answer" />
				<br />

			</div>
		</spring:hasBindErrors>

		<!-- Questions -->


		<label  >1 - (Question/Response): </label>
		<c:out value=" ${questions.questionQuery.question}" /><br />
		<!--   	<form:hidden path="questionQuery.question" />  -->


		<label for="questionQuery.answer">Enter answer:</label><br />
		<form:input path="questionQuery.answer" /><br />

		<label>2 - (Fill in the blank): </label>
		<c:out value="${questions.questionFillInBlank.pre}" /><br />
		<form:input path="questionFillInBlank.answer" />
		<label>
		<c:out value="${questions.questionFillInBlank.post}" />
		</label>
		<br />

		<input class="myButton" type="submit" value="Submit" />


	</form:form>


</div>
<div style="clear: both;"></div>

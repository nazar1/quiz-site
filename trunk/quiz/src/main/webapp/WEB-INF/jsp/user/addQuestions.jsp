<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.nazarmerza.quiz.domain.types.QuestionType"%>
<%@ page import="java.util.*"%>

<div id="signup">
	<h5>Add questions to the quiz!</h5>

	<form:form method="post" modelAttribute="questions">
		<spring:hasBindErrors name="questions">
			<div class="errorblock">

				<form:errors path="questionQuery.question" />
				<br />
				<form:errors path="questionQuery.answer" />
				<br />
				<form:errors path="questionFillInBlank.answer" />
				<br />
			</div>
		</spring:hasBindErrors>


		<!-- Questions -->

		<div class="customHeading">Q1 (Question/Response): </div>

		<label>Enter question:</label><br />
		<form:input path="questionQuery.question" /><br />


		<label>Enter answer:</label><br />
		<form:input path="questionQuery.answer" /><br />

		<div class="customHeading">Q2 (Fill in the blank):</div>

		<label>Enter pre-blank:</label><br />
		<form:input path="questionFillInBlank.pre" /><br />

		<label>Enter blank:</label><br />
		<form:input path="questionFillInBlank.answer" /><br />

		<label>Enter post-blank:</label><br />
		<form:input path="questionFillInBlank.post" /><br />


		<input type="submit" value="Create Quiz" />


	</form:form>

</div>

<div style="clear: both;"></div>

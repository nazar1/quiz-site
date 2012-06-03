<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.nazarmerza.quiz.domain.types.QuestionType"%>
<%@ page import="java.util.*"%>

<div id="createQuiz">
	<h5>Create Quiz</h5>

		<form:form method="post" modelAttribute="quiz">

			<spring:hasBindErrors name="quiz">
				<div class="errorblock">

					<form:errors path="quizTitle" />
					<br />
					<form:errors path="description"/>
					<br />

				</div>
			</spring:hasBindErrors>

			<label>Quiz Title:</label><br />
			<form:input path="quizTitle" /><br />

			<label>Quiz Description:</label><br />
			<form:textarea path="description" />
			<br />
			<label>Select a category:</label><br />
			<form:select path="category" items="${categories}" />
			<br />

			<form:checkbox path="random" />
			<label>Is random mode:</label>
			<br />

			<form:checkbox path="multipage" />
			<label>Is Multi-page?:</label>
			<br />

			<form:checkbox path="immediateCorrection" />
			<label>Immediate Correction?:</label>
			<br />

			<form:checkbox path="practiceMode" /> 
			<label>Is practice mode?:</label> <br />
			<br />
			<input type="submit" value="Next Page" />
		</form:form>

	</div>

<div style="clear: both;"></div>

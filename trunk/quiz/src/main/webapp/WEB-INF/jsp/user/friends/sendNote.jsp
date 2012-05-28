<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div id="sendNote">
	<span class="customHeading">Receiver: 	<c:out value="${searchUser.userName}" /></span>
	<br />
	<br />
	<form method="post" action="/quiz/user/sendNote">
		<label >Note Text:</label><br />
		<textarea name="note" rows="8" cols="450"></textarea><br />
		<input class="myButton" type="submit" value="Send Note" /><br />
		
	</form>
</div>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div id="userProfile">

<div class=customHeading>
${user.userName} 
</div>
</div>
<div id="userInfo">
	<ul>
		<li class="friendButllet"><a class="menu" href="/quiz/user/friendRequests">Friend Requests (${fn:length(friendRequests)}) </a></li>
		<li class="noteButllet"><a class="menu" href="/quiz/user/notes">Notes: (${fn:length(notes)})</a></li>
		<li class="challengeButllet"><a class="menu" href="/quiz/challenges">Challenges: (${fn:length(challenges)})</a></li>
		
	</ul>
</div>

<hr />
<div id="userActionMenu">
	<ul>
		<li class="friendButllet"><a class="menu" href="/quiz/user/friendsList">Friends (${fn:length(friendsNames)}) </a></li>
		<li class="challengeButllet"><a class="menu" href="/quiz/user/searchUsers">Search Users</a></li>
		<li class="noteButllet"><a class="menu" href="/quiz/user/user/earchQuizzes">Search Quizzes</a></li>
		<li class="friendButllet"><a class="menu" href="/quiz/user/action/myQuizzes">Show My Quizzes</a></li>
		<li class="friendButllet"><a class="menu" href="/quiz/user/createQuiz">Create Quiz</a></li>
		
		
		
	</ul>
</div>

<div id="friends">
		<h4>Friends</h4>

		<ul>

			<c:forEach items="${friendsNames}" var="friendName">
				<li><c:out value="${friendName}" /></li>
			</c:forEach>
		</ul>
	</div>
 
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
		<li class="friendButllet"><a class="menu" href="/quiz/user/friendRequests">Friend Requests (${friendRequests}) </a></li>
		<li class="challengeButllet"><a class="menu" href="/quiz/user/challenges/${user.id}">
		Challenges: (${challenges})
		</a>
		</li>
		<li class="noteButllet"><a class="menu" href="/quiz/user/Notes/${user.id}">Notes: (${challenges})</a></li>
		<li class="friendButllet"><a class="menu" href="/quiz/user/friendsList">friends (${fn:length(friendsNames)}) </a></li>
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
 
 
<!--  
<hr />
You Are an: <br/>
${user.profile.rank}

-->




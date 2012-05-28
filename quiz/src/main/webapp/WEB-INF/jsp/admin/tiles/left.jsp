
<div id="">

<div class=customHeading>
	Admin: ${user.userName} 
</div>

<div class="menu">

	<ul>
		<li class="friendButllet"><a class="menu" href="/quiz/admin/createAnnouncement">Create Announcement </a></li>
		<li class="friendButllet"><a class="menu" href="/quiz/admin/activeAnnouncements">Active Announcements </a></li>
		
	</ul>
	
	<form method="post" action="/quiz/admin/findUser">
		<label>Find User</label> <br />
		<input type="text" name="user" value="" />
		<input class="myButton" type="submit" value="Find" />
	</form>
	
	<form method="post" action="/quiz/admin/findQuiz">
		<label>Find Quiz</label> <br />
		<input type="text" name="user" value="" />
		<input class="myButton" type="submit" value="Find" />
	</form>

</div>

</div>



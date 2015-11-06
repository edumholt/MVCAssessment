<!doctype html>
<html>
<head>
<title>Java Web Programming: Movie Search</title>
<meta name="description" content="This is a JSP example that demonstrates how to use a form to search for a Movie from our Spreadsheet." />
<%@ include file="include/styles.jsp"  %>
</head>
<body>
<div class="container">
	<div class="hero-unit">
		<h1>Movie Search</h1>
	</div>
	<%@ include file="include/navigation.jsp"  %>
	<div class="container">
		<form action="SearchByTitle" method="post">
			<div class="form-group">
				<label for="title"><strong>Search by Title:</strong></label>
				<input name="title" />
				<input class="btn btn-primary btn-lg" type="submit" value="Search" />
			</div>
		</form>
	</div>
	<div class="container">
		<form action="SearchByDirector" method="post">
			<div class="form-group">
				<label for="director"><strong>Search by Director:</strong></label>
				<input name="director" />
				<input class="btn btn-primary btn-lg" type="submit" value="Search" />
			</div>
		</form>
	</div>
	<hr />
<%@ include file="include/footer.jsp"  %>
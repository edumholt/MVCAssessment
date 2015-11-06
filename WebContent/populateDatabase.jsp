<!doctype html>
<html>
<head>
<title>Java Web Programming: Populate Database</title>
<meta name="description" content="This is a JSP used to populate the Movie database." />
<%@ include file="include/styles.jsp"  %>
</head>
<body>
<div class="container">
	<div class="hero-unit">
		<h1>Populate Database</h1>
	</div>
	<%@ include file="include/navigation.jsp"  %>
	<div class="container">
		<form action="PopulateDatabase" method="post">
			<div class="form-group">
				<label for="submit"><strong>Build database</strong></label>
				<input name="submit" class="btn btn-primary btn-lg" type="submit" value="Populate" />
			</div>
		</form>
	</div>
	<hr />
<%@ include file="include/footer.jsp"  %>
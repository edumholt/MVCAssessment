<!doctype html>
<html>
<head>
<title>Java Web Programming: Error Page</title>
<meta name="description" content="This is a JSP example of an error page you may send users to via the RequestDispatcher when your application encounters a problem." />
<%@ include file="include/styles.jsp"  %>
</head>
<body>
<div class="container">
	<div class="hero-unit">
		<h1>Error: <small>Please see message below.</small></h1>
		<img alt="error" src="assets/img/error.png" class="img">
	</div>
	<%@ include file="include/navigation.jsp"  %>
	<div class="container">
		<h3 style="color:red;">${error}</h3>
	</div>
	<hr />
<%@ include file="include/footer.jsp"  %>
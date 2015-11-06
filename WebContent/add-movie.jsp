<!DOCTYPE html>
<html>
<head>
<meta name="description" content="This is a JSP example that demonstrates how to add a new Movie to a database">
<title>Java Web Programming - Add New Movie</title>
<%@ include file="include/styles.jsp" %>
</head>
<body>
<div class="container">
    <div class="hero-unit">
        <h1>Add Movie</h1>
    </div>
    <%@ include file="include/navigation.jsp" %>
    <div class="container">
        <form action="AddMovie" method="post">
            <div class="form-group">
            
                <label for="title"><strong>Movie Title:</strong></label>
                <input name="title" class="glow">
                
                <label for="director"><strong>Director:</strong></label>
                <input name="director" class="glow">
                
                <label for="minutes"><strong>Length in Minutes:</strong></label>
                <input name="minutes" class="glow">
                
                <label for="star_1"><strong>Star 1:</strong></label>
                <input name="star_1" class="glow">
                
                <label for="star_2"><strong>Star 2:</strong></label>
                <input name="star_2" class="glow">
                
                <label for="star_3"><strong>Star 3:</strong></label>
                <input name="star_3" class="glow">
                
                <input class="btn btn-primary btn-lg" type="submit" value="Add Movie">
            </div>
        </form>
    </div>
    <hr />
<%@ include file="include/footer.jsp" %>
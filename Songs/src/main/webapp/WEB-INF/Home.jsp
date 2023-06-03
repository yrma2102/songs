<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Songs</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/app.js"></script>
<!-- change to match your file/naming structure -->
</head>
<body>
<div class="container">
		<h1>Welcome, ${ user.firstName }</h1>
		<div class="text-end">
			<a href="/logout">Logout</a>
		</div>
		<hr />
		<h3>All Song Labs</h3>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Song</th>
					<th>Genre</th>
					<th># of Collaborations</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ songs }" var="song">
					<tr>
						<td><a href="/songs/${ song.id }">${ song.title }</a></td>
						<td>${ song.genre }</td>
						<td>${ song.getUsers().size() }</td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<div class="text-begin mt-3">
			<a class="btn btn-primary" href="/songs/new"> New Song</a>
		</div>
	</div>

</body>
</html>
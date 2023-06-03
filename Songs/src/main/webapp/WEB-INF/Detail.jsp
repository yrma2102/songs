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
<title>BOOK API</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/app.js"></script>
<!-- change to match your file/naming structure -->
</head>
<body>


<div class="container pt-5 w-50">
		<div class="card-body bg-light">
			<h2 class="text-center">Information</h2>
					<div class="row">
			<div class="col text-end">
				<c:choose>
					<c:when test="${user!=null}">
						<a href="/logout" class="text-dark">logout</a>
					</c:when>
				</c:choose>
			</div>
		</div>
		
			<div class="row mt-3">
				<div class="col-12">
					<div class="card">
						<div class="card-body">
							<h1 class="card-text"><c:out value="${song.title}"></c:out></h1>
						</div>
					</div>
				</div>
				<div class="col-12">
					<div class="card">
						<div class="card-body">
							<h5 class="card-title">Genre :</h5>
							<p class="card-text"><c:out value="${song.genre}"></c:out></p>
						</div>
					</div>
				</div>
				<div class="col-12">
					<div class="card">
						<div class="card-body">
							<h5 class="card-title">Lyrics</h5>
							<p class="card-text"><c:out value="${song.lyrics}"></c:out></p>
						</div>
					</div>
				</div>
				
			</div>
		</div>
		<div class="col-6 mt-3">
			<h4>Collaborators</h4>
			<c:forEach items="${song.users}" var="user">
				<li>${user.firstName }</li>
			</c:forEach>
		</div>
		
		<div class="text-begin mt-3">
			<a class="btn btn-primary" href="/songs/${ song.id }/edit"> Contribute</a>
		</div>
	</div>


		
</body>
</html>
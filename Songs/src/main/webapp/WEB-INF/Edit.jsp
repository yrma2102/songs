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
<title>Edit Song</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/app.js"></script>
<!-- change to match your file/naming structure -->
</head>
<body>
<div class="container mt-5">
		<div class="row">
			<div class="col-8">
				<h2>Add to ${ song.title }</h2>
				<form:form action="/songs/edit" method="post"
					modelAttribute="song" class="mt-3">
					<form:input type="hidden" path="id" value="${song.id }" />
					<div class="d-flex justify-content-between mt-3">
						<form:label path="title" class="fs-5 col-4">Song Title:</form:label>
						<form:input path="title" class="col-8 form-control w-50" />
					</div>
					<form:errors path="title" class="text-danger" />
					<div class="d-flex justify-content-between mt-3">
						<form:label path="genre" class="fs-5 col-4">Genre:</form:label>
						<form:input  path="genre" class="col-8 form-control w-50" />
					</div>
					<form:errors path="genre" class="text-danger" />
					<div class="d-flex justify-content-between mt-3">
						<label  class="fs-5 col-4"></label>
						<textarea class="col-8 form-control w-50" disabled>
								${ old }
						</textarea>
					</div>

					<div class="d-flex justify-content-between mt-3">
					
						<form:label path="lyrics" class="fs-5 col-4">Add your lyrics:</form:label>
						
						<form:textarea path="lyrics" class="col-8 form-control w-50"
							style="height: 100px" />
					</div>
					<form:errors path="lyrics" class="text-danger" />
					
					<div class="d-flex justify-content-end mt-3">
						<a href="/home" class="btn btn-danger me-4">Cancel</a> <input
							type="submit" value="Submit" class="btn btn-success" />
					</div>
					
				<c:choose>
					<c:when test="${succesDelete!=null}">
						<a class="btn btn-primary" href="/songs/delete/${ song.id }"> <c:out value="${succesDelete}" /></a>
					</c:when>
				</c:choose>
					<div class="text-begin mt-3">

						
					</div>
				</form:form>
			</div>
		</div>
	</div>


</body>
</html>
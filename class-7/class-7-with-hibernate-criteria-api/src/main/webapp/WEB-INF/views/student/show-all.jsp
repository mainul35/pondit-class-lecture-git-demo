<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<jsp:include page="../common/header.jsp"/>
<title>${pageTitle}</title>
<body>
	<h1>${pageTitle}</h1>
	<table class="table">
		<tr>
			<th>ID</th>
			<th>Student Name</th>
			<th>Email</th>
			<th>Age</th>
			<th>Gender</th>
			<th>Country</th>
			<th>Edit</th>
			<th>Details</th>
		</tr>
		<c:forEach items="${students }" var="student">
			<tr>
				<th>${ student.id }</th>
				<th>${ student.name }</th>
				<th>${ student.email }</th>
				<th>${ student.age }</th>
				<th>${ student.gender }</th>
				<th>${ student.country.countryName }</th>
				<th><a href="edit?id=${ student.id }">Edit</a></th>
				<th><a href="details?id=${ student.id }">Details</a></th>
			</tr>
		</c:forEach>
	</table>
</body>
</html>

<!-- GLOBAL FOOTER -->
<jsp:include page="../common/footer.jsp"/>

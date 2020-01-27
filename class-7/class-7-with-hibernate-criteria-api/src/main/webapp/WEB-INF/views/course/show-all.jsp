<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Course List</title>
</head>
<body>
	<table>
		<tr>
			<th>ID</th>
			<th>Course Name</th>
		</tr>
		<c:forEach items="${courses }" var="course">
			<tr>
				<th>${ course.courseId }</th>
				<th>${ course.courseName }</th>
				<th><a href="edit?id=${ course.courseId }">Edit</a></th>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Course</title>
</head>
<body>

	<h1>Edit Course</h1>

	<form:form action="${pageContext.request.contextPath }/course/edit"
		modelAttribute="course">
		<form:input path="courseId" value="${courseId}" hidden="hidden"/>
		<form:input path="courseName" value="${courseName}" />
		<br>

		<input type="submit" name="submit" value="Edit Course">
	</form:form>

</body>
</html>
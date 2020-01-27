<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add New Country</title>
</head>
<body>

	<h1>Add New Country</h1>

	<form:form action="${pageContext.request.contextPath }/country/add"
		modelAttribute="country">
		<form:input path="countryCode" /> <br>
		<form:input path="countryName" /> <br>

		<input type="submit" name="submit" value="Add Country">
	</form:form>

</body>
</html>
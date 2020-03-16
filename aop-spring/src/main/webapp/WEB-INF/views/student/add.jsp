<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Add New Course</title>
</head>
<body>

<h1>Add New Student</h1>

<form:form action="${pageContext.request.contextPath }/student/add"
           modelAttribute="student">
    <div class="form-group">
        <label>Name</label>
        <form:input path="name"></form:input>
    </div>
    <div class="form-group">
        <label>Age</label>
        <form:input path="age"></form:input>
    </div>

    <div class="form-group">
        <label>Gender</label>
        <form:radiobuttons path="gender" items="${genders}"></form:radiobuttons>
    </div>

    <div class="form-group">
        <label>Email</label>
        <form:input path="email"></form:input>
    </div>

    <div class="form-group">
        <label>Select a country</label>
        <form:select class="form-control" path="countryCode">
            <form:options items="${countries}" itemLabel="countryName" itemValue="countryCode"></form:options>
        </form:select>
    </div>

    <div class="form-group">
        <label>Select a country</label>
        <form:select class="form-control" multiple="true" path="courseCodes">
            <form:options items="${courses}" itemLabel="courseName" itemValue="courseCode"></form:options>
        </form:select>
    </div>


    <input type="submit" name="submit" value="Add Student">
</form:form>

</body>
</html>
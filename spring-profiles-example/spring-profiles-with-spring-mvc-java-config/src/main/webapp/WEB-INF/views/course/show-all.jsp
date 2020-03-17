<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Course List</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<table>
    <tr>
        <th>ID</th>
        <th>Course Name</th>
    </tr>
    <c:forEach items="${courses }" var="course">
        <tr>
            <th>${ course.courseId }</th>
            <th>${ course.courseName }</th>
            <sec:authorize access="hasAnyRole('USER')">
            <th><a href="edit?courseCode=${course.courseCode}">Edit</a></th>
            </sec:authorize>
        </tr>
    </c:forEach>
</table>
</body>
</html>
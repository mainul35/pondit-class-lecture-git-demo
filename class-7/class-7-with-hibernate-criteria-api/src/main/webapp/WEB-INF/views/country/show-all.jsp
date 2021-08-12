<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<title>${pageTitle}</title>
<!-- GLOBAL HEADER -->
<jsp:include page="../common/header.jsp"/>
<body>
	<h1>${pageTitle}</h1>
	<table class="table">
		<tr>
			<th>ID</th>
			<th>Country Code</th>
			<th>Country Name</th>
		</tr>
		<c:forEach items="${countries }" var="country">
			<tr>
				<th>${ country.id }</th>
				<th>${ country.countryCode }</th>
				<th>${ country.countryName }</th>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
<!-- GLOBAL FOOTER -->
<jsp:include page="../common/footer.jsp"/>

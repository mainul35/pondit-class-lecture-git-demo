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

	<div class="row">
		<div class="col-4">
			<form:form action="${pageContext.request.contextPath }/course/add"
					   modelAttribute="course">
				<div class="col-12">
					<label>Course Name</label>
					<form:input path="courseName" />
				</div>
				<div class="col-12">
					<input type="submit" name="submit" value="Add Course">
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>
<!-- GLOBAL FOOTER -->
<jsp:include page="../common/footer.jsp"/>

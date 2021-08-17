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

<div class="row">
	<div class="col-6">
		<form:form action="${pageContext.request.contextPath }/student/update"
				   modelAttribute="student">

			<form:input path="id" value="${id}" type="hidden" ></form:input>
<%--			<input type="hidden" value="${studentId}">--%>
			<div class="col-12">
				<div class="form-group">
					<label>Name</label>
					<form:input path="name"></form:input>
				</div>
			</div>

			<div class="col-12">
				<div class="form-group">
					<label>Age</label>
					<form:input path="age"></form:input>
				</div>
			</div>

			<div class="col-12">
				<div class="form-group">
					<label>Gender</label>
					<form:radiobuttons path="gender" items="${genders}" ></form:radiobuttons>
				</div>
			</div>

			<div class="col-12">
				<div class="form-group">
					<label>Email</label>
					<form:input path="email"></form:input>
				</div>
			</div>

			<div class="col-12">
				<div class="form-group">
					<label>Select a country</label>
					<select  class="form-control" name="countryCode">
						<option label="Please Select a country" disabled></option>
						<c:forEach items="${countries}" var="country">
							<option label="${country.countryName}"
									value="${country.countryCode}"
<%--									selected="${student.country.countryCode == country.countryCode}">--%>
									${student.country.countryCode == country.countryCode? "selected" : ''}>

							</option>

						</c:forEach>
					</select>
<%--					<form:select class="form-control" path="countryCode">--%>
<%--						<form:options items="${countries}" itemLabel="countryName" itemValue="countryCode"></form:options>--%>
<%--					</form:select>--%>
				</div>
			</div>

			<div class="col-12">
				<div class="form-group">
					<label>Select a course</label>
					<select class="form-control" multiple="true"  name="courseCodes">
<%--						<form:options items="${courses}" itemLabel="courseName" itemValue="courseCode"></form:options>--%>
						<c:forEach items="${courses}" var="course">
							<option value="${course.courseCode}"
									 label="${course.courseName}" >${course.courseName}</option>
						</c:forEach>
					</select>
<%--					<form:select path="courseCodes" multiple="true" items="${courses.courseCodes}"--%>
<%--								 itemLabel="${courses.courseNames}" itemValue="${courses.courseCodes}"/>--%>
				</div>
			</div>

			<div class="col-12">
				<input type="submit" name="submit" value="Update Student">
			</div>

		</form:form>
	</div>
</div>
</body>
</html>
<!-- GLOBAL FOOTER -->
<jsp:include page="../common/footer.jsp"/>

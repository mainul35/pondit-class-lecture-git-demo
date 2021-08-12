<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!-- GLOBAL HEADER -->
<jsp:include page="../common/header.jsp"/>
<title>${pageTitle}</title>
<html>
<body>
<h1>${pageTitle}</h1>

<div class="row">
    <div class="col-6">
        <form:form action="${pageContext.request.contextPath }/country/add"
                   modelAttribute="country">
            <div class="row">
                <div class="col-12">
                    <div class="form-group">
                        <label>Country code</label>
                        <form:input path="countryCode"/> <br>
                    </div>
                </div>

                <div class="col-12">
                    <div class="form-group">
                        <label>Country Name</label>
                        <form:input path="countryName"/> <br>
                    </div>
                </div>

                <div class="col-12">
                    <input type="submit" name="submit" value="Add Country">
                </div>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
<!-- GLOBAL FOOTER -->
<jsp:include page="../common/footer.jsp"/>

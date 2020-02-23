<%--
  Created by IntelliJ IDEA.
  User: Syed Mainul Hasan
  Date: 2/22/2020
  Time: 8:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/header.jsp"/>

<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="form-group">
    <input type="text" class="form-control course-query"
           placeholder="Course Name">
</div>
<div
        class="btn btn-primary course-query-submit btn-lg btn-block">SEARCH</div>

<br>
<table class="table">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Course Name</th>
        <th scope="col">Course code</th>
    </tr>
    </thead>
    <tbody class="search-result">

    </tbody>
</table>
<jsp:include page="../common/footer.jsp"/>
<script>
    $(document).ready(function(){
        $(".course-query-submit").click(function(){
            var queryString = $(".course-query").val();
            //  /api/v1/course/search | /course/search
            $.get("/api/v1/course/search?query="+queryString, function( data ) {
                var searchResContainer = $(".search-result");
                searchResContainer.html("");
                // data = JSON.parse(data);
                if (data.length > 0) {
                    $.each(data, function( index, value ) {
                        var tblRow = "<tr>\n" +
                            "        <td class='course-id'>"+ value.courseId +"</td>\n" +
                            "        <td class='course-name'>"+ value.courseName +"</td>\n" +
                            "        <td class='course-code'>"+ value.courseCode +"</td>\n" +
                            "    </tr>";
                        searchResContainer.append(tblRow);
                    });
                } else {
                    searchResContainer.html("<th >Nothing found!</th>");
                }
            });
        });
    });
</script>
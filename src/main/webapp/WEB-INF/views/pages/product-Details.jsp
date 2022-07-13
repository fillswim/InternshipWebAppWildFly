<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product-info</title>
</head>
<body>
<h2>Product details:</h2>

<form:form action="saveProduct" modelAttribute="product">

    <form:hidden path="id"/>

    Title <form:input path="title"/>
    <br>
    <br>
    Price <form:input path="price"/>
    <br>
    <br>
    <input type="submit" value="OK">

</form:form>

</body>
</html>
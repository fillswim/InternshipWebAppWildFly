<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    Manufacturer
    <form:select path="manufacturerId">
        <form:option value="0">--SELECT--</form:option>
        <form:options items="${manufacturers}" itemValue="id" itemLabel="title"></form:options>
    </form:select>
    <br>
    <br>

    <input type="submit" value="OK">

</form:form>

</body>
</html>
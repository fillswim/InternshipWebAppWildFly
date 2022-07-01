<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>employee-info</title>
</head>
<body>
<h2>Order item details: Info</h2>

<form:form action="saveOrderItem" modelAttribute="orderItem">

    <form:hidden path="id"/>

    Product <form:input path="product"/>
    <br>
    <br>
    Quantity <form:input path="quantity"/>
    <br>
    <br>
    <input type="submit" value="OK">

</form:form>

</body>
</html>
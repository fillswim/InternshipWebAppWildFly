<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div style="padding: 5px;">

    <h3>Current bucket:</h3>

    <div style="text-align: right">
        <h2>Sum: ${bucket.sum}</h2>
    </div>

    <table id="productsTable">

        <thead>
        <tr>
            <th>Product</th>
            <th>Price</th>
            <th>Amount</th>
            <th>Sum</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="detail" items="${bucket.bucketDetailsDTOS}">

            <tr>
                <td style="width: 25%">${detail.productTitle}</td>
                <td style="width: 25%; text-align: center">${detail.price}</td>
                <td style="width: 25%; text-align: center">${detail.amount}</td>
                <td style="width: 25%; text-align: center">${detail.sum}</td>
            </tr>

        </c:forEach>
        </tbody>

    </table>
    <br>

    <form:form action="${pageContext.request.contextPath}/orders/createOrder"
               modelAttribute="order" method="post">

        <table id="orderDetailsTable">
            <tr>
                <td>Address</td>
                <td><form:input path="address" id="addressField"/></td>
            </tr>
            <tr>
                <td>Description</td>
                <td><form:input path="description" id="descriptionField"/> </td>
            </tr>
        </table>

        <br>

        <input type="submit" id="createOrderButton" value="Create order">

    </form:form>


</div>


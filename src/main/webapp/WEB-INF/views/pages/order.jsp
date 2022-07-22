<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div style="padding: 5px;">

    <h3>Order #: </h3>

    <table id="orderTable">

        <thead>
        <tr>
            <th>Product</th>
            <th>Price</th>
            <th>Amount</th>
            <th>Sum</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="detail" items="${bucketDetails}">

            <tr>
                <td style="width: 25%">${detail.productTitle}</td>
                <td style="width: 25%; text-align: center">${detail.price}</td>
                <td style="width: 25%; text-align: center">${detail.amount}</td>
                <td style="width: 25%; text-align: center">${detail.sum}</td>
            </tr>

        </c:forEach>
        </tbody>

    </table>


</div>


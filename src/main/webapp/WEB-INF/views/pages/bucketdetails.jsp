<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="padding: 5px;">

    <h3>Bucket:</h3>

    <table id="productsTable">

        <thead>
        <tr>
            <th>Product</th>
            <th>Amount</th>
            <th>Sum</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="detail" items="${bucketDetails}">

            <tr>
                <td style="width: 25%">${detail.product.title}</td>
                <td style="width: 25%; text-align: center">${detail.amount}</td>
                <td style="width: 25%; text-align: center">XXX</td>
            </tr>

        </c:forEach>
        </tbody>

    </table>

    <input id="addButton" type="button" value="Return to all products"
           onclick="window.location.href='/test/app01'">

</div>


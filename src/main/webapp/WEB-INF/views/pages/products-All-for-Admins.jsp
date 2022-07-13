<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="padding: 5px;">

    <h3>All order items:</h3>

    <table id="productsTable">

        <thead>
        <tr>
            <th>Title</th>
            <th>Price</th>
            <th>Operations</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="detail" items="${products}">

            <c:url var="addButton" value="/updateProduct">
                <c:param name="productId" value="${detail.id}"/>
            </c:url>

            <c:url var="deleteButton" value="/deleteProduct">
                <c:param name="productId" value="${detail.id}"/>
            </c:url>

            <tr>
                <td style="width: 25%">${detail.title}</td>
                <td style="width: 25%; text-align: center">${detail.price}</td>
                <td style="width: 50%; text-align: center">
                    <input type="button" value="Update" style="width: 49%"
                           onclick="window.location.href = '${addButton}'"/>
                    <input type="button" value="Delete" style="width: 49%"
                           onclick="window.location.href = '${deleteButton}'"/>
                </td>
            </tr>

        </c:forEach>
        </tbody>

    </table>

    <br>

    <input id="addButton" type="button" value="Add"
           onclick="window.location.href='addNewProduct'">

    <br>
    <br>
    <input id="returnToAllCustomersButton" type="button" value="Return shopping"
           onclick="window.location.href='/test/app01'">

</div>


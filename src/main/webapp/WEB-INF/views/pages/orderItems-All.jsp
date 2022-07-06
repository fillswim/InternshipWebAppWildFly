<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="padding: 5px;">

    <h3>All order items:</h3>

    <table id="orderItems">

        <thead>
        <tr>
            <th>Product</th>
            <th>Quantity</th>
            <th>Operations</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="item" items="${allOrderItems}">

            <c:url var="updateButton" value="/updateOrderItem">
                <c:param name="itemId" value="${item.id}"/>
            </c:url>

            <c:url var="deleteButton" value="/deleteOrderItem">
                <c:param name="itemId" value="${item.id}"/>
            </c:url>

            <tr>
                <td style="width: 25%">${item.product}</td>
                <td style="width: 25%; text-align: center">${item.quantity}</td>
                <td style="width: 50%; text-align: center">
                    <input type="button" value="Update" style="width: 49%"
                           onclick="window.location.href = '${updateButton}'"/>
                    <input type="button" value="Delete" style="width: 49%"
                           onclick="window.location.href = '${deleteButton}'"/>
                </td>
            </tr>

        </c:forEach>
        </tbody>

    </table>

    <br>

    <input id="addButton" type="button" value="Add"
           onclick="window.location.href='addNewOrderItem'">

</div>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="padding: 5px;">

    <h3>All order items:</h3>

    <table>

        <tr>
            <th>Product</th>
            <th>Quantity</th>
            <th>Operations</th>
        </tr>

        <c:forEach var="item" items="${allOrderItems}">

            <c:url var="updateButton" value="/updateOrderItem">
                <c:param name="itemId" value="${item.id}"/>
            </c:url>

            <c:url var="deleteButton" value="/deleteOrderItem">
                <c:param name="itemId" value="${item.id}"/>
            </c:url>

            <tr>
                <td>${item.product}</td>
                <td>${item.quantity}</td>
                <td>
                    <input type="button" value="Update"
                           onclick="window.location.href = '${updateButton}'"/>
                    <input type="button" value="Delete"
                           onclick="window.location.href = '${deleteButton}'"/>
                </td>
            </tr>

        </c:forEach>

    </table>

    <br>

    <input type="button" value="Add"
           onclick="window.location.href='addNewOrderItem'">

</div>


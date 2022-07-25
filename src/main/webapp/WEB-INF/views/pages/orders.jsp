<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div style="padding: 5px;">

    <h3>Orders:</h3>

    <table id="ordersTable">

        <thead>
        <tr>
            <th>Order number</th>
            <th>Date</th>
            <th>Sum</th>
            <th>Address</th>
            <th>Operation</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="detail" items="${orders}">

            <c:url var="showOrderButton" value="/orders/showOrder">
                <c:param name="orderId" value="${detail.id}"/>
            </c:url>

            <tr>
                <td style="width: 20%; text-align: center">${detail.id}</td>
                <td style="width: 20%; text-align: center">${detail.date}</td>
                <td style="width: 20%; text-align: center">${detail.bucketDTO.sum}</td>
                <td style="width: 20%; text-align: center">${detail.address}</td>
                <td style="width: 20%; text-align: center">

                    <input type="button" value="Show order" style="width: 100%"
                           onclick="window.location.href = '${showOrderButton}'"/>

                </td>
            </tr>

        </c:forEach>
        </tbody>

    </table>


</div>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div style="padding: 5px;">

    <h3>Order # ${order.id}: </h3>

    <table id="orderHeaderTable">
        <tr>
            <td>
                <div style="text-align: left">
                    <h4>Address: ${order.address}</h4>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div style="text-align: left">
                    <h4>Description: ${order.description}</h4>
                </div>
            </td>
            <td>
                <div style="text-align: right">
                    <h2>Sum: ${order.bucketDTO.sum}</h2>
                </div>
            </td>
        </tr>
    </table>

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
        <c:forEach var="detail" items="${order.bucketDTO.bucketDetailsDTOS}">

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


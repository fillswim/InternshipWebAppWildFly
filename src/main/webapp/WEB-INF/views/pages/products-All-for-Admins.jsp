<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="padding: 5px;">

    <h3>All order items:</h3>

    <table id="productsTable">

        <thead>
        <tr>
            <th>Title</th>
            <th>Manufacturer</th>
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

            <tr id="productRow">
                <td style="width: 20%">${detail.title}</td>
                <td style="width: 20%; text-align: center">${detail.manufacturerTitle}</td>
                <td style="width: 20%; text-align: center">${detail.price}</td>
                <td style="width: 40%; text-align: center">
                    <input id="editProductButton" type="button" value="Edit" style="width: 49%"/>
<%--                    <input id="editProductButton" type="button" value="Edit" style="width: 49%"/>--%>
<%--                    <input type="button" value="Update" style="width: 49%"--%>
<%--                           onclick="window.location.href = '${addButton}'"/>--%>
                    <input type="button" value="Delete" style="width: 49%"
                           onclick="window.location.href = '${deleteButton}'"/>
                </td>
            </tr>

        </c:forEach>
        </tbody>

    </table>

    <br>

    <input id="addButton" type="button" value="Add product"
           onclick="window.location.href='addNewProduct'">

</div>

<script type="text/javascript">

    let buttons = $("input#editProductButton");
    let rows = $("tr#productRow");

    $("input#editProductButton").click(function () {
        let row = $(this).closest("tr");
        let index = row.parent().children("tr").index(row);
        console.log(rows[index]);

    });


</script>



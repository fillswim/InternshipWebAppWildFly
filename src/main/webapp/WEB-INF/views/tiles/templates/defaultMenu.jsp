<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div style="padding: 5px;">

    <h3>Menu</h3>

    <input id="ordersButton" type="button" value="Orders"
           onclick="window.location.href='/test/app01/orders'">

    <br>
    <br>

    <input id="bucketButton" type="button" value="My bucket"
           onclick="window.location.href='/test/app01/buckets'">

    <br>
    <br>

    <input id="returnToAllProductsButton" type="button" value="All products"
           onclick="window.location.href='/test/app01'">

    <br>
    <br>

    <sec:authorize access="hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')">
        <input id="editProductsButton" type="button" value="Edit products"
               onclick="window.location.href='/test/app01/editProducts'">

        <br>
        <br>

        <input id="manufacturersButton" type="button" value="All manufacturers"
               onclick="window.location.href='/test/app01/manufacturers'">

        <br>
        <br>

        <input id="usersButton" type="button" value="All users"
               onclick="window.location.href='/test/app01/users'">

    </sec:authorize>



</div>
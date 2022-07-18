<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="padding: 5px;">

    <h3>Users:</h3>

    <table id="usersTable">

        <thead>
        <tr>
            <th>id</th>
            <th>Username</th>
            <th>Email</th>
            <th>Role</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="user" items="${users}">

            <tr>
                <td style="width: 10%; text-align: center">${user.id}</td>
                <td style="width: 30%; text-align: center">${user.username}</td>
                <td style="width: 30%; text-align: center">${user.email}</td>
                <td style="width: 30%; text-align: center">${user.role}</td>
            </tr>

        </c:forEach>
        </tbody>

    </table>



</div>

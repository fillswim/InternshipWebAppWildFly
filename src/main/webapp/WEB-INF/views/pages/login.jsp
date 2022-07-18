<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div style="padding: 5px;">

    <h3>Log in as:</h3>

    ${pageContext.request.contextPath}
    <br>
    ${pageContext.request.userPrincipal.name}
    ${pageContext.request.userPrincipal}
    <br>
    ${pageContext.request.remoteUser}
    <br>

    <form name="f" action="${pageContext.request.contextPath}/auth" method="post">
        <table>
            <tr>
                <td>Username</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password"></td>
            </tr>
        </table>

        <button type="submit">Log in</button>

    </form>

<%--    <form:form action="/test/app01/auth" modelAttribute="loginUser">--%>

<%--        <table id="loginTable">--%>
<%--            <tr>--%>
<%--                <td>Username</td>--%>
<%--                <td><form:input path="username" id="usernameField"/></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>Password</td>--%>
<%--                <td><form:input path="password" id="passwordField"/></td>--%>
<%--            </tr>--%>

<%--        </table>--%>

<%--        <br>--%>

<%--        <input type="submit" id="loginButton" value="Log in">--%>

<%--    </form:form>--%>

</div>


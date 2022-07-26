<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="register-box" style="padding: 5px;">

    <h3>Register as:</h3>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <c:if test="${not empty msg}">
        <div class="msg">${msg}</div>
    </c:if>

    <form name='registerForm' action="<c:url value='/register' />" method='POST'>

        <table id="registerTable">

            <tr>
                <td>Username:</td>
                <td><input type='text' name='username'></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type='text' name='email'></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' name='password' /></td>
            </tr>
            <tr>
                <td>Matching password:</td>
                <td><input type='password' name='matchingPassword' /></td>
            </tr>
            <tr>
                <td colspan='2'><input name="submit" type="submit"
                                       value="submit" /></td>
            </tr>
        </table>

        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />

    </form>

</div>


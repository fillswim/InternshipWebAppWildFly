<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div>

    <div style="float: right">
        <input id="loginButton" type="button" value="Login"
               onclick="window.location.href='/test/app01/login'">

    </div>

    <sec:authentication property="principal.username" />

    <div style="float: right">
        <sec:authorize access="!isAuthenticated()">
            Login
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            Logout
        </sec:authorize>
    </div>

    <div style="float: left">
        <h2>${infoDTO.header}</h2>
    </div>

</div>
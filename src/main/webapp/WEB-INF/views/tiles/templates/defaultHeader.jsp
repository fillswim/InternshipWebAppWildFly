<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div>

    <div style="float: right">
        <sec:authorize access="!isAuthenticated()">

            <div style="float: right">
                <form:form action="${pageContext.request.contextPath}/login" method="GET">
                    <input type="submit" value="Log in" />
                </form:form>
            </div>

        </sec:authorize>
        <sec:authorize access="isAuthenticated()">

            <h2>Welcome: ${pageContext.request.userPrincipal.name}</h2>

            <div style="float: right">
                <form:form action="${pageContext.request.contextPath}/logout" method="POST">
                    <input type="submit" value="Logout" />
                </form:form>
            </div>

        </sec:authorize>
    </div>

    <div style="float: left">
        <h2>${infoDTO.header}</h2>
    </div>

</div>
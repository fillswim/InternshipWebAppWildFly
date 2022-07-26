<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div>

    <table id="headerTable">
        <tr>
            <td>
                <div style="float: left">
                    <h2>${infoDTO.header}</h2>
                </div>
            </td>

            <td>
                <div style="float: right">
                    <sec:authorize access="!isAuthenticated()">

                        <h3 style="text-align: center">Unknown user</h3>

                        <div style="float: right">
                            <form:form action="${pageContext.request.contextPath}/login" method="GET">
                                <input id="loginButton" type="submit" value="Log in" />
                            </form:form>
                        </div>
                        <div style="float: right">
                            <form:form action="${pageContext.request.contextPath}/register" method="GET">
                                <input id="registerButton" type="submit" value="Register" />
                            </form:form>
                        </div>

                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">

                        <h3 style="text-align: center">${pageContext.request.userPrincipal.name}</h3>

                        <div style="float: right">
                            <form:form action="${pageContext.request.contextPath}/logout" method="POST">
                                <input id="logoutButton" type="submit" value="Logout" />
                            </form:form>
                        </div>

                    </sec:authorize>
                </div>

            </td>

        </tr>

    </table>

</div>
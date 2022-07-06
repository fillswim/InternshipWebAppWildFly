<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
    <title><tiles:getAsString name="title" /></title>

    <link rel="stylesheet" href="<c:url value="/styles/main.css" context="/test/app01"/>">
    <script src="<c:url value="/scripts/main.js" context="/test/app01"/>"></script>

</head>

<body>
<table id="mainTable">
    <tr style="height: 10%; background: #E0E0E0">
        <td colspan="2">
            <tiles:insertAttribute name="header" />
        </td>
    </tr>
    <tr style="height: 80%">
        <td nowrap="nowrap" style="background: #E0E0E0; width: 10%; vertical-align: top">
            <tiles:insertAttribute name="menu" />
        </td>
        <td style="width: 85%; vertical-align: top">
            <tiles:insertAttribute name="body" />
        </td>
    </tr>
    <tr style="height: 5%; background: #E0E0E0">
        <td colspan="2">
            <tiles:insertAttribute name="footer" />
        </td>
    </tr>
</table>
</body>
</html>
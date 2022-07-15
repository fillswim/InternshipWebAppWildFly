<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div style="padding: 5px;">

  <h3>Manufacturers:</h3>

  <table id="manufacturersTable">

    <thead>
    <tr>
      <th>id</th>
      <th>Title</th>
      <th>Address</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="manufacturer" items="${manufacturers}">

      <tr>
        <td style="width: 10%; text-align: center">${manufacturer.id}</td>
        <td style="width: 45%; text-align: center">${manufacturer.title}</td>
        <td style="width: 45%; text-align: center">${manufacturer.address}</td>
      </tr>

    </c:forEach>
    </tbody>

  </table>

</div>


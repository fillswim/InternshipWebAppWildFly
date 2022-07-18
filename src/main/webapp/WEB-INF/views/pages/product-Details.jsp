<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div style="padding: 5px;">

    <h3>Product details:</h3>

    <form:form action="saveProduct" modelAttribute="product">

        <form:hidden path="id"/>

        <table id="productEditTable">
            <tr>
                <td>Title</td>
                <td><form:input path="title" id="titleField"/></td>
            </tr>
            <tr>
                <td>Price</td>
                <td><form:input path="price" id="priceField"/></td>
            </tr>
            <tr>
                <td>Manufacturer</td>
                <td><form:select path="manufacturerId" id="manufacturerField">
                    <form:option value="0">--SELECT--</form:option>
                    <form:options items="${manufacturers}" itemValue="id" itemLabel="title"></form:options>
                </form:select></td>
            </tr>

        </table>

        <br>

        <input type="submit" id="saveProductsButton" value="OK">

    </form:form>
</div>
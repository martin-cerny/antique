
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--
<c:forEach var="category" items="${categories}">
    <c:choose>
        <c:when test="${category.name == selectedCategory.name}">
            <div class="categoryButton" id="selectedCategory">
                <span>
                    ${category.name}
                </span>
            </div>
        </c:when>
        <c:otherwise>
            <a href="category?${category.id}" class="categoryButton">
                <span class="categoryText">
                    ${category.name}
                </span>
            </a>
        </c:otherwise>
    </c:choose>
</c:forEach>
--%>
<h1 class="text-center">${selectedCategory.name}</h1>
<c:forEach var="product" items="${categoryProducts}" varStatus="iter">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2 item-product">
            <br>
            <div class="col-lg-4" style="margin-bottom: 5px">
                <img src="../Antiq/img/products/${product.name}.jpg"
                     alt="${product.name}" width="100px">
                <br>
                vloženo: 
                <c:set var="lastUpdate" value="${product.lastUpdate}" />
                <fmt:formatDate type="both" dateStyle="medium" timeStyle="short" value="${lastUpdate}" />
                <br>
                prodejce: ${product.userId.username}
            </div>
            <div class="col-lg-4">
                ${product.name}
                <br>
                autoři: ${product.author}
                <br>
                stav knihy: ${product.stateId.name}
                <br>
                ${product.description}
            </div>

            <div class="col-lg-4 text-right">${product.price} Kč
                <a href="product?${product.id}">
                    <button type="submit" name="submit" class="btn btn-default"/>Koupit</button>
                </a>
            </div>
        </div>
    </div>
    <br>
</c:forEach>
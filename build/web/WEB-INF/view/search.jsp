<%-- 
    Document   : search
    Created on : Jul 3, 2014, 12:01:30 PM
    Author     : Black1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center">Nalezené knihy</h1>
<c:forEach var="product" items="${products}" varStatus="iter">
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
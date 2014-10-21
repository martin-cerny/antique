<%-- 
    Document   : index
    Created on : 27.6.2014, 16:13:22
    Author     : Black1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row">
    <div class="col-lg-4">
        <div class="row">
            <h3 style="margin-top: 0">Vyberte si položku</h3>
        </div>
        <p><a href="<c:url value='viewProducts'/>">Moje knihy</a></p>
        <p><a href="<c:url value='viewMessages'/>">Přijaté zprávy</a></p>
        <p><a href="<c:url value='displayBook'/>">Prodat knihu</a></p>
    </div>

    <%-- customerRecord is requested --%>
    <c:if test="${!empty user}">
        <div class="col-lg-8">
            <div class="row">
                <h3 style="margin-top: 0">Osobní údaje</h3>
            </div>
            <div class="row">
                <div class="col-lg-3 text-right"><strong>ID:</strong></div>
                <div class="col-lg-9">${user.id}</div>
            </div>
            <div class="row">
                <div class="col-lg-3 text-right"><strong>Jméno</strong></div>
                 <div class="col-lg-9">${user.username}</div>
            </div>
            <div class="row">
                <div class="col-lg-3 text-right"><strong>E-mail:</strong></div>
                 <div class="col-lg-9">${user.email}</div>
            </div>
            <div class="row">
                <div class="col-lg-3 text-right"><strong>Telefon:</strong></div>
                 <div class="col-lg-9">${user.phone}</div>
            </div>
            <div class="row">
                <div class="col-lg-3 text-right"><strong>Místo předání:</strong></div>
                 <div class="col-lg-9">${user.address}</div>
            </div>
        </div>
    </c:if> 
</div>
    
<%-- customerList is requested --%>
<c:if test="${!empty productList}">
    <div class="row">
        <h3>Books</h3>
    </div>
    <table class="table table-striped">
        <tr>
            <th>Název</th>
            <th>ISBN</th>
            <th>Cena</th>
        </tr>
        <c:forEach var="product" items="${productList}" varStatus="iter">
            <tr>
                <td><a href="product?${product.id}">${product.name}</td>
                <td>${product.isbn}</td>
                <td>${product.price} Kč</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<%-- orderList is requested --%>
<c:if test="${!empty messageList}">
    <div class="row">
        <h3>Zprávy přijaté od uživatelů</h3>
    </div>
    <table class="table table-striped">
        <tr>
            <th>E-mail</th>
            <th>Telefon</th>
            <th>Zpráva</th>
            <!--<th>Odeslána</th>-->
        </tr>
        <c:forEach var="message" items="${messageList}" varStatus="iter">
            <tr>
                <td>${message.sender}</td>
                <td>${message.phone}</td>
                <td>${message.text}</td>
                <!--<td>${message.created}</td>-->
            </tr>
        </c:forEach>
    </table>
</c:if>

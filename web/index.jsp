<%-- 
    Document   : index
    Created on : 20.6.2014, 10:06:08
    Author     : Black1
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-12 text-center">
        <h2>Kategorie</h2>
        <hr class="star-primary">
    </div>
</div>
<div class="row">
    <c:forEach var="category" items="${categories}">
        <div class="col-lg-3">
            <a href="category?${category.id}">
                <div class="item">
                    <div class="item-icon">
                        <img src="${initParam.categoryImagePath}${category.name}.jpg"
                             alt="${category.name}" width="220px" height="160px"/>
                    </div>
                    <div class="item-content"><h3>${category.name}</h3></div>
                </div>
            </a>
        </div>
    </c:forEach>
</div>


<%-- 
    Document   : product
    Created on : 29.6.2014, 23:07:37
    Author     : Black1
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row">
    <div class="col-lg-8 col-lg-offset-2 item-product">
        <h2>${product.name}</h2>
        <div class="col-lg-4">
            <img src="../Antiq/img/products/${product.name}.jpg"
                     alt="${product.name}" width="200px">
            <br>
            <c:set var="lastUpdate" value="${product.lastUpdate}" />
            <fmt:formatDate type="both" dateStyle="medium" timeStyle="short" value="${lastUpdate}" />
        </div>

        <div class="col-lg-4">
            autoři: ${product.author}
            <br>
            stav knihy: ${product.stateId.name}
            <br>
            ${product.description}
        </div>
        
        
        <div class="col-lg-4 text-right">${product.price} Kč
        </div>
        <div class="col-lg-12">
            <h3>Kontaktuj prodejce ohledně předání knihy</h3>
            <form class="form-horizontal" role="form" action="<c:url value='send'/>" method="post">
                <div class="form-group">
                    <label for="sender" class="col-lg-2 control-label">Email</label>
                    <div class="col-lg-4">
                        <input name="sender" type="text" class="form-control"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="phone" class="col-lg-2 control-label">Telefon</label>
                    <div class="col-sm-4">
                        <input name="phone" type="text" class="form-control"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="text" class="col-lg-2 control-label">Zpráva</label>
                    <div class="col-lg-8">
                        <textarea name="text" rows="4" class="form-control" placeholder="Vaše zpráva"></textarea>
                    </div>
                </div>
                <input type="hidden"
                       name="productId"
                       value="${product.id}">

                <div class="form-group">
                    <div class="col-lg-2 col-lg-offset-2">
                        <button type="submit" name="submit" class="btn btn-default"/>Odeslat</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<%-- 
    Document   : sell
    Created on : Jul 1, 2014, 9:47:02 PM
    Author     : Black1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="row">
    <div class="col-lg-12"><h2>Prodat knihu</h2></div>

    <c:if test="${!empty validationErrorFlag}">
        <span class="error smallText">Prosíme vyplňte správně tyto položky:</span>
        <c:if test="${!empty nameError}">
            <br><span><strong class="text-danger">Název</strong> (např.: Myslíme objektově v jazyku Java)</span>
        </c:if>   
        <c:if test="${!empty authorError}">
            <br><span><strong class="text-danger">Autor</strong> (např.: Rudolf Pecinovský)</span>
        </c:if>
        <c:if test="${!empty descriptionError}">
            <br><span><strong class="text-danger">Popis</strong> (např.: Několikrát přečtená kniha, i přesto ve výborném stavu.)</span>
        </c:if>
        <c:if test="${!empty isbnError}">
            <br><span><strong class="text-danger">ISBN</strong> (např.: 879-12348-4225)</span>
        </c:if>
        <c:if test="${!empty priceError}">
            <br><span><strong class="text-danger">Cena</strong> (350 Kč)</span>
        </c:if>
        <c:if test="${!empty stateError}">
            <br><span><strong class="text-danger">Stav</strong> (Použitá)</span>
        </c:if>
    </c:if>
    <form class="form-horizontal" role="form" action="<c:url value='sellBook'/>" method="post" enctype="multipart/form-data">
        <div class="col-lg-6">
            <div class="form-group">
                <label for="name" class="col-lg-4 control-label">Název</label>
                <div class="col-lg-6">
                    <input name="name" type="text" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label for="author" class="col-lg-4 control-label">Autor</label>
                <div class="col-sm-6">
                    <input name="author" type="text" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label for="description" class="col-lg-4 control-label">Popis knihy</label>
                <div class="col-lg-8">
                    <textarea name="description" rows="4" class="form-control"></textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-2 col-lg-offset-4">
                    <button type="submit" name="submit" class="btn btn-default"/>Odeslat</button>
                </div>
            </div>
        </div>
        <div class="col-lg-6">
            <div class="form-group">
                <label for="isbn" class="col-lg-4 control-label">ISBN</label>
                <div class="col-sm-5">
                    <input name="isbn" type="text" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label for="price" class="col-lg-4 control-label">Cena</label>
                <div class="col-lg-5">
                    <input name="price" type="text" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label for="state" class="col-lg-4 control-label">Stav</label>
                <div class="col-sm-5">
                    <select name="state" class="form-control">
                        <option value="1">Nová</option>
                        <option value="2">Velmi dobrá</option>
                        <option value="3">Použitá</option>
                        <option value="4">Starší</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="category" class="col-lg-4 control-label">Kategorie</label>
                <div class="col-lg-6">
                    <select name="category" class="form-control">
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="photo" class="col-lg-4 control-label">Fotka</label>
                <div class="col-lg-6">
                    <input type="file" name="photo" class="form-control">
                </div>
            </div>
        </div>
    </form>
</div>

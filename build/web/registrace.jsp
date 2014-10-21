<%-- 
    Document   : registrace.jsp
    Created on : Jul 2, 2014, 2:32:02 PM
    Author     : Black1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<div class="col-lg-12">
    <h3>Registrace nového uživatele</h3>
    <c:if test="${!empty validationErrorFlag}">
        <span class="error smallText">Prosíme vyplňte správně tyto položky:</span>
        <c:if test="${!empty usernameError}">
            <br><span><strong class="text-danger">Jméno a příjmení</strong> (např.: Martin Černý)</span>
        </c:if>   
        <c:if test="${!empty emailError}">
            <br><span><strong class="text-danger">E-mail</strong> (např.: info@antikvariat.cz)</span>
        </c:if>
        <c:if test="${!empty phoneError}">
            <br><span><strong class="text-danger">Telefon</strong> (např.: 222333444)</span>
        </c:if>
        <c:if test="${!empty addressError}">
            <br><span><strong class="text-danger">Adresa</strong> (např.: Korunní 56)</span>
        </c:if>
        <c:if test="${!empty passError}">
            <br><span><strong class="text-danger">Heslo</strong> (delší jak 3 znaky)</span>
        </c:if>
    </c:if>
            
    <form class="form-horizontal" role="form" action="<c:url value='registrace'/>" method="post">
        <div class="form-group">
            <label for="username" class="col-lg-2 control-label">Jméno a příjmení</label>
            <div class="col-lg-4">
                <input name="username" type="text" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label for="email" class="col-lg-2 control-label">Email</label>
            <div class="col-lg-4">
                <input name="email" type="text" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label for="phone" class="col-lg-2 control-label">Telefon</label>
            <div class="col-sm-4">
                <input name="phone" type="text" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label for="address" class="col-lg-2 control-label">Adresa</label>
            <div class="col-sm-4">
                <input name="address" type="text" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label for="pass" class="col-lg-2 control-label">Heslo</label>
            <div class="col-sm-4">
                <input name="pass" type="password" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-2 col-lg-offset-2">
                <button type="submit" name="submit" class="btn btn-default"/>Vytvořit</button>
            </div>
        </div>
    </form>
</div>
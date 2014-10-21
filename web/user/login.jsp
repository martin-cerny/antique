<%-- 
    Document   : login
    Created on : 27.6.2014, 16:13:29
    Author     : Black1
--%>
<div class="text-center">
<form action="j_security_check" method="post">
    <div class="col-lg-4 col-lg-offset-4">
        <p>
            <input type="text" size="20" name="j_username" class="form-control" placeholder="Username"/>
        </p>
        <p>
            <input type="password" size="20" name="j_password" class="form-control" placeholder="Password"/>
        </p>
        <p><button type="submit" class="btn btn-default">Submit</button></p>
    </div>
</form>
</div>
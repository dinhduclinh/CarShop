<%-- 
    Document   : forgot
    Created on : Mar 9, 2024, 2:30:20 PM
    Author     : DLCT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="css/login_style.css">
    </head>
    <body>
        <section class="container-fluid">
            <!--row justify-content-center is used for centering the login form-->
            <section class="row justify-content-center">
                <!--Making the form responsive-->
                <section class="col-12 col-sm-6 col-md-4">
                    <form class="form-container" method="post" action="ForgotController?Forgot=sendUsername">
                        <!--Binding the label and input together-->
                        <div class="form-group">
                            <h4 class="text-center font-weight-bold"> FIND ACCOUNT </h4>
                            <label for="Inputuser1">Username</label>
                            <input type="text" name="username" class="form-control" id="Inputuser1" aria-describeby="usernameHelp" placeholder="Enter username">
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Check</button>

                    </form>
                </section>
            </section>
        </section>
    <c:if test="${not empty requestScope.error}">
        <h4 style="display: flex;
            justify-content: center;
            color: red;">${requestScope.error}</h4>
    </c:if>
</body>
</html>

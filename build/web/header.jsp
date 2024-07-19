<%-- 
    Document   : header
    Created on : Feb 26, 2024, 3:26:13 PM
    Author     : DLCT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--===============================================================================================-->	
        <link rel="icon" type="image/png" href="images/icons/favicon.png"/>
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/linearicons-v1.0.0/icon-font.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/slick/slick.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/MagnificPopup/magnific-popup.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/perfect-scrollbar/perfect-scrollbar.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="css/util.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <!--===============================================================================================-->
    </head>
    <body>
        <c:set var="cart" value="${sessionScope.cart}"/>
        <c:set var="totalQuantity" value="0"/>

        <c:if test="${not empty cart and not empty cart.cartItems}">
            <c:forEach var="cartItem" items="${cart.cartItems}">
                <c:set var="totalQuantity" value="${totalQuantity + cartItem.quantity}"/>
            </c:forEach>
        </c:if>
        <!-- Header -->
        <header>
            <!-- Header desktop -->
            <div class="container-menu-desktop">
                <!-- Topbar -->
                <div class="top-bar" style="height: 5rem">
                    <div class="content-topbar flex-sb-m h-full container">
                        <div class="left-top-bar">
                            Welcome to Team Car
                        </div>

                        <div class="right-top-bar flex-w h-full">
                            <a href="HomePageController" class="flex-c-m trans-04 p-lr-25">
                                Home
                            </a>
                            <c:choose>
                                <c:when test="${not empty sessionScope.account}">


                                    <c:if test="${sessionScope.account.roleId eq 1}">
                                        <a href="ManageProductController?Product=Manage Product" class="flex-c-m trans-04 p-lr-25">
                                            Manage Product
                                        </a>
                                    </c:if>
                                    <a class="flex-c-m trans-04 p-lr-25">
                                        Welcome ${sessionScope.account.fullname}
                                    </a>
                                    <a href="LogoutController" class="flex-c-m trans-04 p-lr-25">
                                        Logout
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a href="login.jsp" class="flex-c-m trans-04 p-lr-25">
                                        My Account
                                    </a>
                                </c:otherwise>
                            </c:choose>
                            <div class="wrap-icon-header flex-w flex-r-m mt-4">
                                <a href="cartDetail.jsp">
                                    <div style="color: white" class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-22 icon-header-noti js-show-cart" data-notify="${totalQuantity}">
                                        <i class="zmdi zmdi-shopping-cart"></i>
                                    </div>
                                </a>
                            </div>

                        </div>


                    </div>
                </div>
            </div>
        </header>
    </body>
</html>

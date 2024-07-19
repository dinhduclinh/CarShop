<%-- 
    Document   : cartDetail
    Created on : Feb 27, 2024, 2:47:32 PM
    Author     : DLCT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Shoping Cart</title>
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
        <link rel="stylesheet" type="text/css" href="vendor/perfect-scrollbar/perfect-scrollbar.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="css/util.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <!--===============================================================================================-->
    </head>
    <body class="animsition">

        <jsp:include page="header.jsp" />

        <!-- Shoping Cart -->
        <form class="bg0 p-t-75 p-b-85">
            <div class="container">
                <div class="row">
                    <div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
                        <div class="m-l-25 m-r--38 m-lr-0-xl">
                            <c:choose>
                                <c:when test="${not empty sessionScope.cart and not empty sessionScope.cart.cartItems}">
                                    <div class="wrap-table-shopping-cart">

                                        <table class="table-shopping-cart">
                                            <tr class="table_head">
                                                <th class="column-1">Product</th>
                                                <th class="column-2"></th>
                                                <th class="column-3">Price</th>
                                                <th class="column-4">Quantity</th>
                                                <th class="column-5">Total</th>
                                                <th class="column-6">Action</th>
                                            </tr>
                                            <c:forEach var="cartItem" items="${sessionScope.cart.cartItems}">
                                                <tr class="table_row">

                                                    <td class="column-1">
                                                        <div class="how-itemcart1">
                                                            <img src="${cartItem.product.image}" alt="IMG">
                                                        </div>
                                                    </td>
                                                    <td class="column-2">${cartItem.product.name}</td>
                                                    <td class="column-3"> ${cartItem.product.price} VND</td>
                                                    <td class="column-4">
                                                        <div class="wrap-num-product flex-w m-l-auto m-r-0">
                                                            <a href="CartController?action=update&productId=${cartItem.product.id}&newQuantity=${cartItem.quantity-1}" class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
                                                                <i class="fs-16 zmdi zmdi-minus"></i>
                                                            </a>

                                                            <input id="quantity-${cartItem.product.id}" class="mtext-104 cl3 txt-center num-product" type="number" name="newQuantity" value="${cartItem.quantity}">

                                                            <a href="CartController?action=update&productId=${cartItem.product.id}&newQuantity=${cartItem.quantity+1}" class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
                                                                <i class="fs-16 zmdi zmdi-plus"></i>
                                                            </a>

                                                        </div>
                                                    </td>
                                                    <td class="column-5">${cartItem.product.price * cartItem.quantity} VND</td>
                                                    <td class="column-5"><a href="CartController?action=delete&productId=${cartItem.product.id}" class="btn btn-outline-dark" style="margin-right: 2rem">
                                                            Delete
                                                        </a></td>
                                                </tr>
                                            </c:forEach>


                                        </table>

                                    </div>
<!--                                    <div class="flex-w flex-sb-m bor15 p-t-18 p-b-15 p-lr-40 p-lr-15-sm">

                                        <div class="flex-c-m stext-101 cl2 size-119 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-10">
                                            Update Cart
                                        </div>
                                    </div>-->
                                </c:when>
                                <c:otherwise>
                                    <p class="col-md-12 text-center">NO PRODUCT</p>
                                </c:otherwise>
                            </c:choose>


                        </div>
                    </div>
                    <c:set var="totalPrice" value="0" />
                    <c:forEach var="cartItem" items="${sessionScope.cart.cartItems}">
                        <c:set var="totalPrice" value="${totalPrice + (cartItem.product.price * cartItem.quantity)}" />
                    </c:forEach>

                    <c:set var="cart" value="${sessionScope.cart}"/>
                    <c:set var="totalQuantity" value="0"/>

                    <c:if test="${not empty cart and not empty cart.cartItems}">
                        <c:forEach var="cartItem" items="${cart.cartItems}">
                            <c:set var="totalQuantity" value="${totalQuantity + cartItem.quantity}"/>
                        </c:forEach>
                    </c:if>

                    <div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50">
                        <div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
                            <h4 class="mtext-109 cl2 p-b-30">
                                Cart Totals
                            </h4>

                            <div class="flex-w flex-t bor12 p-b-13">
                                <div class="size-208">
                                    <span class="stext-110 cl2">
                                        Total Quantity:
                                    </span>
                                </div>
                                <div class="size-209 p-t-1">
                                    <span class="mtext-110 cl2">
                                        ${totalQuantity}
                                    </span>
                                </div>
                            </div>

                            <div class="flex-w flex-t p-t-27 p-b-33">
                                <div class="size-208">
                                    <span class="mtext-101 cl2">
                                        Total:
                                    </span>
                                </div>

                                <div class="size-209 p-t-1">
                                    <span class="mtext-110 cl2">
                                        ${totalPrice}
                                    </span>
                                </div>
                            </div>

                            <c:choose>
                                <c:when test="${not empty sessionScope.account}">
                                    <!-- Nếu có tài khoản trong session, chuyển hướng đến trang thanh toán -->
                                    <a href="CartController?action=checkcout" class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer btn">
                                        Proceed to Checkout
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <!-- Nếu không có tài khoản trong session, hiển thị thông báo và yêu cầu đăng nhập -->
                                    <p>Please login to proceed to checkout.</p>
                                </c:otherwise>
                            </c:choose>


                        </div>
                    </div>
                </div>
            </div>
        </form>

        <jsp:include page="footer.jsp" />

        <!--===============================================================================================-->	
        <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/animsition/js/animsition.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/bootstrap/js/popper.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/select2/select2.min.js"></script>
        <script>
            $(".js-select2").each(function () {
                $(this).select2({
                    minimumResultsForSearch: 20,
                    dropdownParent: $(this).next('.dropDownSelect2')
                });
            })
        </script>
        <!--===============================================================================================-->
        <script src="vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
        <script>
            $('.js-pscroll').each(function () {
                $(this).css('position', 'relative');
                $(this).css('overflow', 'hidden');
                var ps = new PerfectScrollbar(this, {
                    wheelSpeed: 1,
                    scrollingThreshold: 1000,
                    wheelPropagation: false,
                });

                $(window).on('resize', function () {
                    ps.update();
                })
            });
        </script>
        <!--===============================================================================================-->
        <script src="js/main.js"></script>

    </body>
</html>

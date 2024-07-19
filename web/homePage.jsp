<%-- 
    Document   : homePage
    Created on : Feb 23, 2024, 3:09:15 PM
    Author     : DLCT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Home</title>
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
        <style>

            .custom-btn {
                background-color: red;
                color: white;
            }

            .pagination {
                float: left;
                margin: 0 0 5px;
            }
            .pagination li a {
                border: none;
                font-size: 13px;
                min-width: 30px;
                min-height: 30px;
                color: #999;
                margin: 0 2px;
                line-height: 30px;
                border-radius: 2px !important;
                text-align: center;
                padding: 0 6px;
            }
            .pagination li a:hover {
                color: #666;
            }
            .pagination li.active a, .pagination li.active a.page-link {
                background: #03A9F4;
            }
            .pagination li.active a:hover {
                background: #0397d6;
            }
            .pagination li.disabled i {
                color: #ccc;
            }
            .pagination li i {
                font-size: 16px;
                padding-top: 6px
            }
            .hint-text {
                float: left;
                margin-top: 10px;
                font-size: 13px;
            }
            .button-container {
                display: flex;
                justify-content: space-between; /* hoặc thay đổi giá trị để đạt được kiểu căng mong muốn */
            }

        </style>

    </head>
    <body class="animsition">
        <jsp:include page="header.jsp" />
        <section class="bg0 p-b-20">
            <!--Container -->

            <section class="section-slide">
                <div class="wrap-slick1">
                    <div class="slick1">
                        <c:forEach var="item" items="${topProduct}">
                            <div class="item-slick1" style="background-image: url('${item.image}');">

                                <div class="container h-full">
                                    <div class="flex-col-l-m h-full p-t-100 p-b-30 respon5">
                                        <div class="layer-slick1 animated visible-false" data-appear="fadeInDown" data-delay="0">
                                            <span class="ltext-101 cl2 respon2">
                                                ${item.price} VND
                                            </span>
                                        </div>

                                        <div class="layer-slick1 animated visible-false" data-appear="fadeInUp" data-delay="800">
                                            <h2 class="ltext-201 cl2 p-t-19 p-b-43 respon1">
                                                ${item.name}
                                            </h2>
                                        </div>

                                        <div class="layer-slick1 animated visible-false" data-appear="zoomIn" data-delay="1600">
                                            <a href="DetailController?id=${item.id}" class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04">
                                                Quick View
                                            </a>
                                        </div>
                                        <div class="layer-slick1 animated visible-false m-t-10" data-appear="zoomIn" data-delay="2000">
                                            <a href="CartController?action=add&productId=${item.id}" class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04" style="background-color: black">
                                                Add To Cart
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </div>
            </section>

        </section>
        <!-- Product -->
        <section class="bg0 p-t-23 p-b-23">
            <div class="container">
                <div>
                    <h3 class="ltext-103 cl5 text-center">
                        Product Overview
                    </h3>
                </div>
                <!-- -->
                <div class="flex-w flex-sb-m p-b-52">
                    <div class="flex-w flex-l-m filter-tope-group m-tb-10">
                        <form action="HomePageController" method="GET">
                            <button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 ${(categoryId == null || categoryId == "" )? 'how-active1' : ''}">
                                <input type="hidden" name="index" value="${tag}">
                                <input type="hidden" name="color" value="${color}">
                                <input type="hidden" name="categoryId" value="">
                                <input type="hidden" name="sortField" value="${sortField}">
                                All Products
                            </button>
                        </form>
                        <c:forEach var="c" items="${categoryList}">
                            <form action="HomePageController" method="GET">
                                <input type="hidden" name="index" value="${tag}">
                                <input type="hidden" name="color" value="${color}">
                                <input type="hidden" name="categoryId" value="${c.id}">
                                <input type="hidden" name="sortField" value="${sortField}">
                                <button type="submit" class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 ${categoryId == c.id ? 'how-active1' : ''}">
                                    ${c.name}
                                </button>
                            </form>
                        </c:forEach>


                    </div>

                    <div class="flex-w flex-c-m m-tb-10">
                        <div class="flex-c-m stext-106 cl6 size-104 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-4 js-show-filter">
                            <i class="icon-filter cl2 m-r-6 fs-15 trans-04 zmdi zmdi-filter-list"></i>
                            <i class="icon-close-filter cl2 m-r-6 fs-15 trans-04 zmdi zmdi-close dis-none"></i>
                            Filter
                        </div>

                        <div class="flex-c-m stext-106 cl6 size-105 bor4 pointer hov-btn3 trans-04 m-tb-4 js-show-search">
                            <i class="icon-search cl2 m-r-6 fs-15 trans-04 zmdi zmdi-search"></i>
                            <i class="icon-close-search cl2 m-r-6 fs-15 trans-04 zmdi zmdi-close dis-none"></i>
                            Search
                        </div>
                    </div>

                    <!-- Search product -->
                    <div class="dis-none panel-search w-full p-t-10 p-b-15">

                        <form action="HomePageController">
                            <div class="bor8 dis-flex p-l-15">
                                <input type="hidden" id="indexInput" name="index" value="${tag}">
                                <input type="hidden" id="colorInput" name="color" value="${color2}">
                                <input type="hidden" id="categoryIdInput" name="categoryId" value="${categoryId}">
                                <input type="hidden" id="sortFieldInput" name="sortField" value="${sortField}">
                                <button type="submit" class="size-113 flex-c-m fs-16 cl2 hov-cl1 trans-04">
                                    <i class="zmdi zmdi-search"></i>
                                </button>

                                <input value="${search}" class="mtext-107 cl2 size-114 plh2 p-r-15" type="text" name="search" placeholder="Search Product">
                            </div>	
                        </form>


                    </div>

                    <!-- Filter -->
                    <div class="dis-none panel-filter w-full p-t-10">
                        <div class="wrap-filter flex-w bg6 w-full p-lr-40 p-t-27 p-lr-15-sm">
                            <div class="filter-col1 p-r-15 p-b-27">
                                <div class="mtext-102 cl2 p-b-15">
                                    Sort By
                                </div>


                                <ul>
                                    <li class="p-b-6">
                                        <a href="HomePageController?index=${tag}&color=&categoryId=${categoryId}&search=${search}&sortField=&sortDirection="  class="${((sortField == "" || sortField == null) && !isAscending ) ? 'stext-106 trans-04 filter-link-active' : 'filter-link stext-106 trans-04'}">
                                            Newness
                                        </a>
                                    </li>

                                    <li class="p-b-6">
                                        <a href="HomePageController?index=${tag}&color=&categoryId=${categoryId}&search=${search}&sortField=&sortDirection=asc"  class="${(sortField == "" && isAscending) ? 'stext-106 trans-04 filter-link-active' : 'filter-link stext-106 trans-04'}">
                                            Oldness
                                        </a>
                                    </li>

                                    <li class="p-b-6">
                                        <a href="HomePageController?index=${tag}&color=&categoryId=${categoryId}&search=${search}&sortField=price&sortDirection=asc"  class="${(sortField == "price" && isAscending) ? 'stext-106 trans-04 filter-link-active' : 'filter-link stext-106 trans-04'}">
                                            Price: Low to High
                                        </a>
                                    </li>

                                    <li class="p-b-6">
                                        <a href="HomePageController?index=${tag}&color=&categoryId=${categoryId}&search=${search}&sortField=price&sortDirection="  class="${(sortField == "price" && !isAscending) ? 'stext-106 trans-04 filter-link-active' : 'filter-link stext-106 trans-04'}">
                                            Price: High to Low
                                        </a>
                                    </li>
                                </ul>
                            </div>

                            <div class="filter-col3 p-r-15 p-b-27">
                                <div class="mtext-102 cl2 p-b-15">
                                    Color
                                </div>

                                <ul>
                                    <li class="p-b-6">
                                        <a href="HomePageController?index=${tag}&color=&categoryId=${categoryId}&sortField=${sortField}&search=${search}"  class="${(color == "" || color == null) ? 'stext-106 trans-04 filter-link-active' : 'filter-link stext-106 trans-04'}"
                                           >
                                            All
                                        </a>
                                    </li>
                                    <c:forEach  var="color2" items="${colors}">
                                        <li class="p-b-6">
                                            <a href="HomePageController?index=${tag}&color=${color2}&categoryId=${categoryId}&sortField=${sortField}&search=${search}"  class="${color2 == color ? 'stext-106 trans-04 filter-link-active' : 'filter-link stext-106 trans-04'}">
                                                ${color2}
                                            </a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>

                        </div>
                    </div>
                </div>

                <!--Container -->
                <div class="row">				


                    <c:forEach var="item" items="${productList}">
                        <div class="col-sm-6 col-md-4 col-lg-3 p-b-35">
                            <div class="block2">
                                <div class="block2-pic hov-img0 ">
                                    <img src="${item.image}" alt="IMG-PRODUCT" style="height: 10rem; width: 16rem">

                                    <div class="button-container block2-btn">
                                        <a href="DetailController?id=${item.id}" class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04">
                                            Quick View
                                        </a>
                                    </div>
                                </div>



                                <div class="dis-flex justify-content-lg-between">
                                    <div class="block2-txt-child1 flex-col-l">
                                        <a href="product-detail.html" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                            ${item.name}
                                        </a>

                                        <span class="stext-105 cl3">
                                            ${item.price} VND
                                        </span>

                                    </div>
                                    <div>
                                        <a href="CartController?action=add&productId=${item.id}" class="btn btn-outline-dark" style="margin-right: 2rem">
                                            Add To Cart
                                        </a>
                                    </div>
                                </div>


                            </div>
                        </div>
                        <!-- Code to iterate over the list -->
                    </c:forEach>
                </div>
                <div class="dis-flex justify-content-center" style="border-top: 1px solid">

                    <ul class="pagination m-t-20" >
                        <c:choose>
                            <c:when test="${tag != 1}">
                                <li class="page-item"><a href="HomePageController?Product=Manage Product&index=${tag-1}&color=${color}&categoryId=${categoryId}&sortField=${sortField}&search=${search}">Previous</a></li>
                                </c:when>
                                <c:otherwise>

                            </c:otherwise>
                        </c:choose>
                        <c:forEach begin="1" end="${endPage}" var="i">
                            <li class="${tag == i ? "page-item active" : "page-item"}"><a href="HomePageController?Product=Manage Product&index=${i}&color=${color}&categoryId=${categoryId}&sortField=${sortField}&search=${search}" class="page-link">${i}</a></li>
                            </c:forEach>
                            <c:choose>
                                <c:when test="${tag != endPage}">
                                <li class="page-item"><a href="HomePageController?Product=Manage Product&index=${tag+1}&color=${color}&categoryId=${categoryId}&sortField=${sortField}&search=${search}">Next</a></li>
                                </c:when>
                                <c:otherwise>

                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </div>
        </section>


        <!-- Footer -->
        <jsp:include page="footer.jsp" />


        <!-- Back to top -->
        <div class="btn-back-to-top" id="myBtn">
            <span class="symbol-btn-back-to-top">
                <i class="zmdi zmdi-chevron-up"></i>
            </span>
        </div>

        <!-- Modal1 -->
        <div class="wrap-modal1 js-modal1 p-t-60 p-b-20">
            <div class="overlay-modal1 js-hide-modal1"></div>

            <div class="container">
                <div class="bg0 p-t-60 p-b-30 p-lr-15-lg how-pos3-parent">
                    <button class="how-pos3 hov3 trans-04 js-hide-modal1">
                        <img src="images/icons/icon-close.png" alt="CLOSE">
                    </button>
                </div>
            </div>
        </div>

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
        <script src="vendor/daterangepicker/moment.min.js"></script>
        <script src="vendor/daterangepicker/daterangepicker.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/slick/slick.min.js"></script>
        <script src="js/slick-custom.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/parallax100/parallax100.js"></script>
        <script>
            $('.parallax100').parallax100();
        </script>
        <!--===============================================================================================-->
        <script src="vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
        <script>
            $('.gallery-lb').each(function () { // the containers for all your galleries
                $(this).magnificPopup({
                    delegate: 'a', // the selector for gallery item
                    type: 'image',
                    gallery: {
                        enabled: true
                    },
                    mainClass: 'mfp-fade'
                });
            });
        </script>
        <!--===============================================================================================-->
        <script src="vendor/isotope/isotope.pkgd.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/sweetalert/sweetalert.min.js"></script>
        <script>
            $('.js-addwish-b2').on('click', function (e) {
                e.preventDefault();
            });

            $('.js-addwish-b2').each(function () {
                var nameProduct = $(this).parent().parent().find('.js-name-b2').html();
                $(this).on('click', function () {
                    swal(nameProduct, "is added to wishlist !", "success");

                    $(this).addClass('js-addedwish-b2');
                    $(this).off('click');
                });
            });

            $('.js-addwish-detail').each(function () {
                var nameProduct = $(this).parent().parent().parent().find('.js-name-detail').html();

                $(this).on('click', function () {
                    swal(nameProduct, "is added to wishlist !", "success");

                    $(this).addClass('js-addedwish-detail');
                    $(this).off('click');
                });
            });

            /*---------------------------------------------*/

            $('.js-addcart-detail').each(function () {
                var nameProduct = $(this).parent().parent().parent().parent().find('.js-name-detail').html();
                $(this).on('click', function () {
                    swal(nameProduct, "is added to cart !", "success");
                });
            });

        </script>
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

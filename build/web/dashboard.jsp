<%-- 
    Document   : manageProduct
    Created on : Feb 26, 2024, 3:42:44 PM
    Author     : DLCT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>AddProduct</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <style>
            body {
                color: #566787;
                background: #FFCCCC;
                font-family: 'Varela Round', sans-serif;
                font-size: 13px;
                background-color: wheat;
            }
            .table-responsive {
                margin: 30px 0;
            }
            .table-wrapper {
                min-width: 1200px;
                background: #fff;
                padding: 20px 25px;
                border-radius: 3px;
                box-shadow: 0 1px 1px rgba(0,0,0,.05);
            }
            .table-title {
                padding-bottom: 15px;
                background: #299be4;
                color: #fff;
                padding: 16px 30px;
                margin: -20px -25px 10px;
                border-radius: 3px 3px 0 0;
            }
            .table-title h2 {
                margin: 5px 0 0;
                font-size: 24px;
            }
            .table-title .btn {
                color: #566787;
                float: right;
                font-size: 13px;
                background: #fff;
                border: none;
                min-width: 50px;
                border-radius: 2px;
                border: none;
                outline: none !important;
                margin-left: 10px;
            }
            .table-title .btn:hover, .table-title .btn:focus {
                color: #566787;
                background: #f2f2f2;
            }
            .table-title .btn i {
                float: left;
                font-size: 21px;
                margin-right: 5px;
            }
            .table-title .btn span {
                float: left;
                margin-top: 2px;
            }
            table.table tr th, table.table tr td {
                border-color: #e9e9e9;
                padding: 12px 15px;
                vertical-align: middle;
            }
            table.table tr th:first-child {
                width: 60px;
            }
            table.table tr th:last-child {
                width: 100px;
            }
            table.table-striped tbody tr:nth-of-type(odd) {
                background-color: #fcfcfc;
            }
            table.table-striped.table-hover tbody tr:hover {
                background: #f5f5f5;
            }
            table.table th i {
                font-size: 13px;
                margin: 0 5px;
                cursor: pointer;
            }	
            table.table td:last-child i {
                opacity: 0.9;
                font-size: 22px;
                margin: 0 5px;
            }
            table.table td a {
                font-weight: bold;
                color: #566787;
                display: inline-block;
                text-decoration: none;
            }
            table.table td a:hover {
                color: #2196F3;
            }
            table.table td a.settings {
                color: #2196F3;
            }
            table.table td a.delete {
                color: #F44336;
            }
            table.table td i {
                font-size: 19px;
            }
            table.table .avatar {
                height: 5rem;
                width: 7rem;
            }
            .status {
                font-size: 30px;
                margin: 2px 2px 0 0;
                display: inline-block;
                vertical-align: middle;
                line-height: 10px;
            }
            .text-success {
                color: #10c469;
            }
            .text-info {
                color: #62c9e8;
            }
            .text-warning {
                color: #FFC107;
            }
            .text-danger {
                color: #ff5b5b;
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
        </style>
        <script>
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
        </script>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="container-xl">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-5">
                                <h2>Revenue <b>Management</b></h2>
                            </div>
                            
                        </div>
                    </div>
                    
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>NO.</th>
                                <th>User Id</th>
                                <th>Date</th>
                                <th>Total Price</th>
                                <th>Order Status</th>
                                <th>Complete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="order" items="${orderList}" varStatus="loop">
                                <tr>

                                    <td>${loop.index + 1}</td>
                                    <td>${us.getUserById(order.userId).fullname}</td>
                                    <td><fmt:formatDate pattern="dd-MM-yyyy" value="${order.createDate}" /></td>
                                    <td>${od.getTotalPriceOrderDetailByOrderId(order.id)}</td>
                                    <td>${order.orderStatus}</td>
                                    <td>${order.status == false ? 'complete': 'not complete'}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="clearfix">
                        <ul class="pagination">
                            <c:choose>
                                <c:when test="${indexRevenue != 1}">
                                    <li class="page-item"><a href="ManageProductController?Product=Revenue&indexRevenue=${indexRevenue-1}">Previous</a></li>
                                    </c:when>
                                    <c:otherwise>

                                </c:otherwise>
                            </c:choose>
                            <c:forEach begin="1" end="${endPage}" var="i">
                                <li class="${indexRevenue == i ? "page-item active" : "page-item"}"><a href="ManageProductController?Product=Revenue&indexRevenue=${i}" class="page-link">${i}</a></li>
                                </c:forEach>
                                <c:choose>
                                    <c:when test="${indexRevenue != endPage}">
                                    <li class="page-item"><a href="ManageProductController?Product=Revenue&indexRevenue=${indexRevenue+1}">Next</a></li>
                                    </c:when>
                                    <c:otherwise>

                                </c:otherwise>
                            </c:choose>

                        </ul>
                    </div>
                </div>
            </div>
        </div>  
        <jsp:include page="footer.jsp" />
    </body>
</html>

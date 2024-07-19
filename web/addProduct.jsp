<%-- 
    Document   : addProduct
    Created on : Feb 26, 2024, 3:25:32 PM
    Author     : DLCT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Add Product</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp" />

        <div class="container" >
            <h1 class= "text-center"style="margin: 2rem 0">Add Product</h1>
            <div class="card" style="margin-bottom: 1rem; border-radius: 2rem ;box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.1);">
                <form method="post" action="ManageProductController" class="form-card" style=" border-radius: 2rem; background-color: rgba(241, 241, 241, 0.5); ">
                    <div class="row justify-content-between text-left p-4">

                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label for="name">Product Name:</label>
                            <input type="text" class="form-control" id="name" placeholder="Enter Product Name" name="name" required>
                        </div>
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label for="price">Price:</label>
                            <input type="number" class="form-control" id="price" placeholder="Enter Price" name="price" required>
                        </div>
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label for="title">Title:</label>
                            <input type="text" class="form-control" id="title" placeholder="Enter Title" name="title" required>
                        </div>
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label for="color">Color:</label>
                            <input type="text" class="form-control" id="color" placeholder="Enter Color" name="colorAdd" required>
                        </div>
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label for="image">Image URL:</label>
                            <input type="text" class="form-control" id="image" placeholder="Enter Image URL" name="image" required>
                        </div>
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label for="stock">Stock:</label>
                            <input type="number" class="form-control" id="stock" placeholder="Enter Stock" name="stock" required>
                        </div>
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label for="description">Description:</label>
                            <input type="text" class="form-control" id="description" placeholder="Enter Description" name="description" required/>
                        </div>
                        <div class="form-group col-sm-6 flex-column d-flex">
                            <label for="categoryId">Category:</label>
                            <select class="form-control" id="categoryIdAdd" name="categoryIdAdd">
                                <c:forEach var="cate" items="${categoryList}">
                                    <option value="${cate.id}">${cate.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="d-flex justify-content-end">
                        <div class="form-group col-sm-3 flex-column d-flex">
                            <button type="submit" class="btn btn-primary" name="Product" value="Create">Submit</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>



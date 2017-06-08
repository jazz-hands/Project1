<!DOCTYPE html>
<html>
  <head>
      <meta charset="utf-8">
      <title>Product Maintainence</title>
      <link rel="stylesheet" href="styles/main.css" type="text/css"/>
  </head>
  <body>

    <h1>Product</h1>
    <form action="addProduct" method="post">
      <strong>Code: </strong>
      <input type="text" name="productCode" value="${product.productCode}"><br>

      <strong>Description: </strong>
      <input type=text name="description" value="${product.description}" id="description"><br>

      <strong>Price: </strong>
      <input type=text name="price" value="${product.price}" id="price"><br>
      <input type="submit" value="Update Product"><input type="submit" value="View Products">
    </form>

  </body>
</html>

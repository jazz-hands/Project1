<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Confirm</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>
    
<h3>Are you sure you want to delete this product?</h3>
<p><b>Code:        ${sessionScope.product.code}</b></p>
<p><b>Description: ${sessionScope.product.description}</b></p>
<p><b>Price:       ${sessionScope.product.price}</b></p>
<input type="Submit" value="Yes"><a href="ProductMaint?action=deleteProduct&productCode=${product.code}"  >Yes</a></input><input type="Submit" value="No"></input><a href="ProductMaint?action=viewProduct&productCode=${product.code}"  >No</a>
        
</body>
</html>
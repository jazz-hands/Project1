package music.productmaint;

import music.data.ProductIO;
import music.business.Product;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ProductMaint extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/add.jsp";
        ServletContext sc = getServletContext();

        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "viewProducts";
        }

        String url = "/viewProducts.jsp";

        if (action.equals("viewProducts")) {
            url = "/viewProducts.jsp";
        } else if (action.equals("editProduct")) {
            url = editProduct(request, response);
        } else if (action.equals("addProduct")) {
            url = addProduct(request, response);
        } else if (action.equals("deleteProduct")) {
            url = deleteProduct(request, response);
        }

        sc.getRequestDispatcher(url)
                .forward(request, response);
    }

    private String addProduct(HttpServletRequest request, HttpServletResponse response)
      {
        // get the product data
       String productCode = request.getParameter("productCode");
       String description = request.getParameter("description");
       String price = request.getParameter("price");
       boolean productExists = false;

       // store the data in a Product object
       Product product = new Product();
       product.setProductCode(productCode);
       product.setDescription(description);
       product.setPrice(price);

       for (i=0; i>products.length; i++;) {
         if products[i].productCode == productCode {
           productExists = true;
         }
       }

       ServletContext sc = getServletContext();
       String path = sc.getRealPath("/WEB-INF/products.txt");

       if(!productExists) {
         ProductIO.insetProduct(product, path);
       }
       else{
         ProductIO.updateProduct(product, path);
       }

      //  String url = "/displayProducts.jsp";
       return "/displayProducts.jsp";
     }

     private String editProduct(HttpServletRequest request, HttpServletResponse response)
       {
         //TODO check logic for editing

         String productCode = request.getParameter("productCode");
        //  HttpSession session = request.getSession();

         //get updated Product
         String productCode = request.getParameter("productCode");
         String description = request.getParameter("description");
         String price = request.getParameter("price");

         // store the data in a Product object
         Product product = new Product();
         product.setProductCode(productCode);
         product.setDescription(description);
         product.setPrice(price);

         ServletContext sc = getServletContext();
         String path = sc.getRealPath("/WEB-INF/products.txt");
         ProductIO.add(product, path);
       }
}

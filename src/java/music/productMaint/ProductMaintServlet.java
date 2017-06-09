package music.productMaint;

import music.data.ProductIO;
import music.business.Product;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

public class ProductMaintServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = "/displayProducts.jsp";
        ServletContext sc = getServletContext();

        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "displayProducts";
        }
        
        if (action.equals("displayProducts")) {
            url = "./displayProducts.jsp";
//        } else if (action.equals("editProduct")) {
//            url = editProduct(request, response);
        } else if (action.equals("addProduct")) {
            url = addProduct(request, response);
        } else if (action.equals("deleteProduct")) {
            url = deleteProduct(request, response);
        }

        sc.getRequestDispatcher(url)
                .forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String path = getServletContext().getRealPath("/WEB-INF/products.txt");
        
        ProductIO.init(path);
        List<Product> products = ProductIO.selectProducts();
        
        session.setAttribute("products", products);
        
        System.out.println("products:" + products);
        
        String url = "/displayProducts.jsp";
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
        
//        doPost(request,response);
    }

    
    //Use this method to add and update a product
    private String addProduct(HttpServletRequest request,
            HttpServletResponse response) {
        
        // get the product data
       String productCode = request.getParameter("productCode");
       String description = request.getParameter("description");
       String priceString = request.getParameter("price");
       double price = Double.parseDouble(priceString);
       boolean productExists = false;

       // store the data in a Product object
       Product product = new Product();
       product.setCode(productCode);
       product.setDescription(description);
       product.setPrice(price);

//       for (int i=0; i<sessionScope.products.length; i++) {
//         if products[i].productCode == productCode {
//           productExists = true;
//         }
//       }

       ServletContext sc = getServletContext();
       String path = sc.getRealPath("/WEB-INF/products.txt");

       if(ProductIO.exists(product.getCode())) {
           ProductIO.updateProduct(product);
       }
       else{
           ProductIO.insertProduct(product);
       }

      //  String url = "/displayProducts.jsp";
       return "/displayProducts.jsp";
     }
    
        private String deleteProduct(HttpServletRequest request, HttpServletResponse response)
      {
          return null;
      }

}

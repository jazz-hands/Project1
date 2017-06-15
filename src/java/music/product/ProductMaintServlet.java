package music.product;

import music.data.ProductIO;
import music.business.Product;
import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import music.data.ProductDB;

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
            url = "/displayProducts.jsp";
        } else if (action.equals("addProduct")) {
            url = updateProduct(request, response);
        } else if (action.equals("updateProduct")) {
            url = updateProduct(request, response);
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

        doPost(request,response);
    }

  
    public String updateProduct(HttpServletRequest request,
            HttpServletResponse response) {
        
        HttpSession session = request.getSession();

        String productCode = request.getParameter("productCode");
        String description = request.getParameter("description");
        String priceString = request.getParameter("price");
        double price = Double.parseDouble(priceString);
        
        Product product = new Product();
        product.setCode(productCode);
        product.setDescription(description);
        product.setPrice(price);
        
        ProductDB.updateProduct(product);
        
        List<Product> products = ProductIO.selectProducts();
        session.setAttribute("products", products);
        
        return "/displayProducts.jsp";
    }

    private String deleteProduct(HttpServletRequest request, HttpServletResponse response)
      {
          return null;
      }
}

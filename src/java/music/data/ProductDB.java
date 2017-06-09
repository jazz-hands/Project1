

package music.data;

/**
 *
 * @author jasmi
 */

import music.data.ProductIO;
import music.business.Product;

public class ProductDB {
    
    public static void updateProduct(Product product)
      {       
          if(ProductIO.exists(product.getCode())) {
              ProductIO.updateProduct(product);
          }
          else {
              ProductIO.insertProduct(product);
          }
      }
    
}

package music.data;

/**
*
* @author jasmi
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import music.business.Product;

public class ProductDB {
  
  public static void updateProduct(Product product)
  {       
    if(ProductIO.exists(product.getCode())) {
      ProductIO.updateProduct(product);
      
    }
    else {
      ProductDB.insert(product);
      ProductIO.insertProduct(product);
      
    }
  }
  
  public static void deleteProduct(Product product)
  {       
    if(ProductIO.exists(product.getCode())) {
      ProductIO.deleteProduct(product);
    }
    else {
      ProductIO.updateProduct(product);
    }
  }
  
  //This method returns null if a product isn't found.
  public static Product selectProduct(String productCode) {
    ConnectionPool pool = ConnectionPool.getInstance();
    Connection connection = pool.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    String query = "SELECT * FROM Product "
    + "WHERE ProductCode = ?";
    try {
      ps = connection.prepareStatement(query);
      ps.setString(1, productCode);
      rs = ps.executeQuery();
      if (rs.next()) {
        Product p = new Product();
        p.setId(rs.getLong("ProductID"));
        p.setCode(rs.getString("ProductCode"));
        p.setDescription(rs.getString("ProductDescription"));
        p.setPrice(rs.getDouble("ProductPrice"));
        return p;
      } else {
        return null;
      }
    } catch (SQLException e) {
      System.err.println(e);
      return null;
    } finally {
      DBUtil.closeResultSet(rs);
      DBUtil.closePreparedStatement(ps);
      pool.freeConnection(connection);
    }
  }
  
  //This method returns null if a product isn't found.
  public static Product selectProduct(long productID) {
    ConnectionPool pool = ConnectionPool.getInstance();
    Connection connection = pool.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    String query = "SELECT * FROM Product "
    + "WHERE ProductID = ?";
    try {
      ps = connection.prepareStatement(query);
      ps.setLong(1, productID);
      rs = ps.executeQuery();
      if (rs.next()) {
        Product p = new Product();
        p.setId(rs.getLong("ProductID"));
        p.setCode(rs.getString("ProductCode"));
        p.setDescription(rs.getString("ProductDescription"));
        p.setPrice(rs.getDouble("ProductPrice"));
        return p;
      } else {
        return null;
      }
    } catch (SQLException e) {
      System.err.println(e);
      return null;
    } finally {
      DBUtil.closeResultSet(rs);
      DBUtil.closePreparedStatement(ps);
      pool.freeConnection(connection);
    }
  }
  
  //This method returns null if a product isn't found.
  public static List<Product> selectProducts() {
    ConnectionPool pool = ConnectionPool.getInstance();
    Connection connection = pool.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    String query = "SELECT * FROM Product";
    try {
      ps = connection.prepareStatement(query);
      rs = ps.executeQuery();
      ArrayList<Product> products; products = new ArrayList<Product>();
      while (rs.next()) {
        Product p = new Product();
        p.setCode(rs.getString("ProductCode"));
        p.setDescription(rs.getString("ProductDescription"));
        p.setPrice(rs.getDouble("ProductPrice"));
        products.add(p);
      }
      return products;
    } catch (SQLException e) {
      System.err.println(e);
      return null;
    } finally {
      DBUtil.closeResultSet(rs);
      DBUtil.closePreparedStatement(ps);
      pool.freeConnection(connection);
    }
  }
  
  public static void insert(Product product) {
    ConnectionPool pool = ConnectionPool.getInstance();
    Connection connection = pool.getConnection();
    PreparedStatement ps = null;
    
    String query
    = "INSERT INTO product (ProductID, ProductCode, ProductDescription, ProductPrice) "
    + "VALUES (?, ?, ?, ?)";
    try {
      ps = connection.prepareStatement(query);
      ps.setLong(1, product.getId());
      ps.setString(2, product.getCode());
      ps.setString(3, product.getDescription());
      System.out.println("Setting Double");
//      ps.setDouble(4, product.getPrice());
      
      ps.executeUpdate();
    } catch (SQLException e) {
      System.out.println("This didn't insert to database!");
      System.out.println(e);
    } finally {
      DBUtil.closePreparedStatement(ps);
      pool.freeConnection(connection);
    }
    
  }
  
  public static void remove(Product product) {
    ConnectionPool pool = ConnectionPool.getInstance();
    Connection connection = pool.getConnection();
    PreparedStatement ps = null;
    
    String query
    = "REMOVE FROM "
    + "WHERE ProductID = '?' and ProductCode = 'ProductCode'";
    try {
      ps = connection.prepareStatement(query);
      ps.setString(1, Long.toString(product.getId()));
      ps.setString(2, product.getCode());
      
      ps.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e);
    } finally {
      DBUtil.closePreparedStatement(ps);
      pool.freeConnection(connection);
    }
  }
  
}

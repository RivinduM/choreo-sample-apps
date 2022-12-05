package wso2.example.byoc.service;

import wso2.example.byoc.model.Product;
import wso2.example.byoc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product) {Connection conn = null;

        try
        {
            String getConsentResourcePrepStatement = "INSERT INTO testtable (ID) VALUES (?)";
            conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/testchoreo","crootuser","crootuser");
            System.out.println ("Database connection established");

            try (PreparedStatement getConsentResourcePreparedStmt =
                         conn.prepareStatement(getConsentResourcePrepStatement)) {
                String uuid = String.valueOf(UUID.randomUUID());
                getConsentResourcePreparedStmt.setString(1, uuid);
                getConsentResourcePreparedStmt.execute();
                System.out.println ("Database query executed : " + uuid);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close ();
                    System.out.println ("Database connection terminated");
                }
                catch (Exception e) { /* ignore close errors */ }
            }
        }
        return repository.save(product);
    }

    public List<Product> getProducts() {
        Connection conn = null;

        try
        {
            String getConsentResourcePrepStatement = "INSERT INTO testtable (ID) VALUES (?)";
            conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/testchoreo","crootuser","crootuser");
            System.out.println ("Database connection established");

            try (PreparedStatement getConsentResourcePreparedStmt =
                         conn.prepareStatement(getConsentResourcePrepStatement)) {
                String uuid = String.valueOf(UUID.randomUUID());
                getConsentResourcePreparedStmt.setString(1, uuid);
                getConsentResourcePreparedStmt.execute();
                System.out.println ("Database query executed : " + uuid);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close ();
                    System.out.println ("Database connection terminated");
                }
                catch (Exception e) { /* ignore close errors */ }
            }
        }
        return repository.getAllProducts();
    }

    public Product getProductById(int id) {
        return repository.findById(id);
    }

    public String deleteProduct(int id) {
        repository.delete(id);
        return "product removed !! " + id;
    }

    public Product updateProduct(Product product) {
       return repository.update(product);
    }


    private Connection getDBConnection() {

        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://112.134.175.240:3306/choreo","root","root");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public String dbConnection() {
        return "db connection made!!";
    }
}
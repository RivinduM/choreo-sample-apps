package wso2.example.byoc.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    private static final Log log = LogFactory.getLog(ProductService.class);

    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product) {Connection conn = null;
        return repository.save(product);
    }

    public List<Product> getProducts() {
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

    public String dbConnection() {
        String result = "db connection made!!";

        Connection conn = null;

        try
        {
            result = "trying";
            String getConsentResourcePrepStatement = "INSERT INTO testtable (ID) VALUES (?)";
            conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/testchoreo","crootuser","crootuser");
            System.out.println ("Database connection established");
            result = "Database connection established";

            try (PreparedStatement getConsentResourcePreparedStmt =
                         conn.prepareStatement(getConsentResourcePrepStatement)) {
                String uuid = String.valueOf(UUID.randomUUID());
                result = "uuid";
                getConsentResourcePreparedStmt.setString(1, uuid);
                getConsentResourcePreparedStmt.execute();
                System.out.println ("Database query executed : " + uuid);
                result = "Database query executed : " + uuid;
            } catch (SQLException throwables) {
                result = "error 1";
                throwables.printStackTrace();
            }
        } catch (Exception e) {
            result = e.getMessage();
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
                    result = "Database connection terminated";
                }
                catch (Exception e) { /* ignore close errors */ }
            }
        }
        System.out.println("This is a print statement");
        log.debug("This is a log");
        return result;
    }
}
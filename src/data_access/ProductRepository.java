package data_access;

import java.sql.Connection;

public class ProductRepository {
	Connection conn = DatabaseConnector.getConnection();
}

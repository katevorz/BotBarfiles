package org.example;
import org.example.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.math.BigDecimal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/baraxolkaDatabase";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static Connection connection = null;

    public Database() throws SQLException {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    public static void initializeDatabase() {
        String tableSQL = "CREATE TABLE IF NOT EXISTS Products ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(100) NOT NULL, "
                + "price DECIMAL(10, 2), "
                + "description TEXT, "
                + "phoneNumber VARCHAR(20), "
                + "photoId VARCHAR(100), "
                + "category VARCHAR(50)"
                + ");";

        try (Statement statement = getConnection().createStatement()) {
            statement.execute(tableSQL);
            System.out.println("Database and tables initialized successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertProduct(String name, String category, double price, String description, String phoneNumber, String photoId) {
        String sql = "INSERT INTO Products (name, category, price, description, phoneNumber, photoId) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, category);
            pstmt.setDouble(3, price);
            pstmt.setString(4, description);
            pstmt.setString(5, phoneNumber);
            pstmt.setString(6, photoId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Products";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                products.add(new Product(
                        rs.getString("name"),
                        new Category(rs.getString("category")),
                        rs.getBigDecimal("price"),
                        rs.getString("description"),
                        rs.getString("phoneNumber"),
                        rs.getString("photoId")

                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
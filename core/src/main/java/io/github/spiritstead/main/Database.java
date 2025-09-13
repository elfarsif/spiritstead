package io.github.spiritstead.main;

import java.sql.*;

public class Database {

    public static void connect() {
        // connection string
        String url = "jdbc:sqlite:c:/sqlite/chinook2.db";

        String sql = "SELECT id, name, capacity FROM warehouses";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.printf("%-5s%-25s%-10s%n",
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("capacity")
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        connect();
    }
}

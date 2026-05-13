package com.hashtagash.pnc.framework.db;

import com.hashtagash.pnc.framework.config.Config;
import java.sql.*;
import java.util.*;

public class Database {

    public Connection connection() throws SQLException {
        return DriverManager.getConnection(
                Config.get("db.url"),
                Config.get("db.user"),
                Config.get("db.password")
        );
    }

    public void execute(String sql) throws SQLException {
        try (Connection c = connection(); Statement s = c.createStatement()) {
            s.execute(sql);
        }
    }

    public List<Map<String, Object>> query(String sql) throws SQLException {
        try (Connection c = connection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(sql)) {

            ResultSetMetaData md = rs.getMetaData();
            List<Map<String, Object>> rows = new ArrayList<>();

            while (rs.next()) {
                Map<String, Object> row = new LinkedHashMap<>();
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    row.put(md.getColumnLabel(i), rs.getObject(i));
                }
                rows.add(row);
            }
            return rows;
        }
    }

    public void seedPayments() throws SQLException {
        execute("DROP TABLE IF EXISTS ledger");
        execute("DROP TABLE IF EXISTS payments");

        execute("""
                CREATE TABLE IF NOT EXISTS payments (
                    id INT PRIMARY KEY,
                    loan_id VARCHAR(20),
                    amount DECIMAL(10,2),
                    status VARCHAR(20),
                    channel VARCHAR(20)
                )
                """);

        execute("DELETE FROM payments");        

        execute("""
                INSERT INTO payments VALUES
                    (1, 'LN1001', 250.00, 'POSTED', 'WEB'),
                    (2, 'LN1002', 175.50, 'PENDING', 'MOBILE'),
                    (3, 'LN1003', 500.00, 'FAILED', 'API')
                """);

        execute("""
                CREATE TABLE IF NOT EXISTS ledger (
                    payment_id INT,
                    amount DECIMAL(10,2),
                    posted_flag VARCHAR(1)
                )
                """);
        execute("DELETE FROM ledger");        

        execute("""
                INSERT INTO ledger VALUES
                    (1, 125.50, 'Y'),
                    (3, 75.25, 'Y')
                """);
    }
}
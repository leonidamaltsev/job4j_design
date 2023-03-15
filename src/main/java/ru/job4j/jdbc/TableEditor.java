package ru.job4j.jdbc;

import java.io.InputStream;
import java.
        sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private final Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void statementSql(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initConnection() throws Exception {
        Class.forName(properties.getProperty("driver_class"));
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, username, password);
    }

    public void createTable(String tableName) {
       String sql = String.format("CREATE TABLE IF NOT EXISTS %s();", tableName);
        statementSql(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format("DROP TABLE %s;", tableName);
        statementSql(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = (String.format(
                "ALTER TABLE %s ADD COLUMN %s %s", tableName, columnName, type
        ));
        statementSql(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = (String.format(
                "ALTER TABLE %s DROP COLUMN %s", tableName, columnName
        ));
        statementSql(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = (String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s", tableName, columnName, newColumnName
        ));
        statementSql(sql);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        TableEditor tableEditor = new TableEditor(config);
        tableEditor.createTable("Customers");
        System.out.println("Создание таблицы");
        System.out.println(tableEditor.getTableScheme("Customers"));
        tableEditor.addColumn("Customers", "Name", "varchar(255)");
        System.out.println("Добавление столбца");
        System.out.println(tableEditor.getTableScheme("Customers"));
        System.out.println("Переименование столбца");
        tableEditor.renameColumn("Customers", "Name", "First_name");
        System.out.println(tableEditor.getTableScheme("Customers"));
        System.out.println("Удаление столбца");
        tableEditor.dropColumn("Customers",  "First_name");
        System.out.println(tableEditor.getTableScheme("Customers"));
        tableEditor.dropTable("Customers");
        System.out.println("Удаление таблицы");
    }
}


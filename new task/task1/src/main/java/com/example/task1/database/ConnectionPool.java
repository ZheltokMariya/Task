package com.example.task1.database;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static MysqlDataSource dataSource;

    static {
            dataSource = MySqlDataSourceFactory.createMySqlDataSource();
    }

    private ConnectionPool(){}

    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }
}

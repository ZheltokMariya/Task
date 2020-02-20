package com.example.task1.database;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.util.Properties;

import java.io.FileInputStream;

public class MySqlDataSourceFactory {

    public static MysqlDataSource createMySqlDataSource(){
        MysqlDataSource dataSource = null;
        Properties props = new Properties();
        try{
            FileInputStream inputStream = new FileInputStream("src\\main\\resources\\database.properties");
            props.load(inputStream);
            dataSource = new MysqlDataSource();
            dataSource.setURL(props.getProperty("db.url"));
            dataSource.setUser(props.getProperty("db.username"));
            dataSource.setPassword(props.getProperty("db.password"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return dataSource;
    }
}

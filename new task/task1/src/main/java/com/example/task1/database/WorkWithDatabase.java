package com.example.task1.database;

import java.sql.*;
import java.util.Arrays;

public class WorkWithDatabase {

    public final static int NUMBER_OF_COLUMNS = 2;
    public final static String TABLE_NAME = "number";
    public final static String FIRST_COLUMN_NAME = "value1";
    public final static String SECOND_COLUMN_NAME = "value2";

    public static void createTable(){
        String sqlCommand = "CREATE TABLE " + TABLE_NAME +
                " (id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, " + FIRST_COLUMN_NAME + " int NULL, " +
                SECOND_COLUMN_NAME + " int NULL)";
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionPool.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sqlCommand);
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(statement != null){
                try{
                    statement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void addToBatch(Statement statement, int start, int end, int[] numbers) throws SQLException{
        String sqlCommand = "INSERT INTO " + TABLE_NAME + " (" + FIRST_COLUMN_NAME + ", " + SECOND_COLUMN_NAME + ") VALUES (";
        int i = start;
        while (i < end){
            statement.addBatch(sqlCommand  + numbers[i] + ", " + numbers[i+1] + ")");
            i+=NUMBER_OF_COLUMNS;
        }
    }

    public static void addRowsToDatabase(String[] array, int start, int batchSize){
        int[] numbers = Arrays.stream(array).mapToInt(Integer::parseInt).toArray();

        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            if (((array.length - start) % NUMBER_OF_COLUMNS) != 0  && (array.length-start) < batchSize*NUMBER_OF_COLUMNS){
                String sqlCommand = "INSERT INTO " + TABLE_NAME + " (" + FIRST_COLUMN_NAME + ", " + SECOND_COLUMN_NAME + ") VALUES (";
                statement.addBatch(sqlCommand  + numbers[array.length-1] + ",  null)");

                 if (array.length-start != 1){
                     addToBatch(statement, start, array.length-1, numbers);
                 }
            }
            else{
                if (((array.length - start) % NUMBER_OF_COLUMNS) == 0 && (array.length-start) < batchSize*NUMBER_OF_COLUMNS){
                    addToBatch(statement, start, array.length, numbers);
                }
                else{
                    addToBatch(statement, start, start + batchSize * NUMBER_OF_COLUMNS, numbers);
                }
            }
            statement.executeBatch();
            connection.commit();
        }catch (SQLException e) {
            if (connection != null){
                try {
                    connection.rollback();
                    e.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            }catch (SQLException e) {
                e.printStackTrace();
             }
        }
    }
}

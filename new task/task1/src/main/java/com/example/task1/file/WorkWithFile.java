package com.example.task1.file;

import java.io.*;

public class WorkWithFile {
    public static final String NAME_OF_FILE = "..\\task.csv";

    public static String readFromFile() {
        BufferedReader bufferedReader = null;
        String row;
        StringBuilder data = new StringBuilder();

        try {
            bufferedReader = new BufferedReader(new FileReader(NAME_OF_FILE));
            while ((row = bufferedReader.readLine()) != null) {
                data.append(row);
                data.append(",");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found " + e);
        }catch (IOException e){
            System.err.println("Read error " + e);
        }finally {
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.err.println("Stream close error " + e);
                }
            }
        }
        return data.toString();
    }
}

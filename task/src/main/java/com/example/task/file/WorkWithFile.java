package com.example.task.file;

import java.io.*;
import java.util.Random;

public class WorkWithFile {
    public static final String NAME_OF_FILE = "src\\main\\resources\\task.csv";
    public static final int AMOUNT_OF_NUMBERS_IN_FILE = 100;
    public static final int RANGE_OF_POSSIBLE_NUMBERS = 100;

    public static void writeRandomNumbersIntoFile() {
        Random random = new Random();
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(NAME_OF_FILE);
            fileWriter.append(String.valueOf(random.nextInt(RANGE_OF_POSSIBLE_NUMBERS)+1));
            for (int i = 0; i < AMOUNT_OF_NUMBERS_IN_FILE-1; i++){
                fileWriter.append(",");
                fileWriter.append(String.valueOf(random.nextInt(RANGE_OF_POSSIBLE_NUMBERS)+1));
            }
        }catch (IOException e){
            System.err.println("Write error " + e);
        }finally {
            if (fileWriter != null){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.err.println("Stream close error " + e);
                }
            }
        }
    }

    public static String readFromFile() {
        BufferedReader bufferedReader = null;
        String row;
        String data = "";

        try {
            bufferedReader = new BufferedReader(new FileReader(NAME_OF_FILE));
            while ((row = bufferedReader.readLine()) != null) {
                data += row;
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
        return data;
    }
}

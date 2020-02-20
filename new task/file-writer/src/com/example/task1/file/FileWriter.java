package com.example.task1.file;

import java.io.IOException;
import java.util.Random;

public class FileWriter {
    public static final String NAME_OF_FILE = "..\\task.csv";
    public static final int RANGE_OF_POSSIBLE_NUMBERS = 100;

    public static void writeRandomNumbersIntoFile(int numberOfRows, int numberOfColumns) {
        Random random = new Random();
        java.io.FileWriter fileWriter = null;
        try{
            fileWriter = new java.io.FileWriter(NAME_OF_FILE);
            for (int i = 0; i < numberOfRows; i++){
                fileWriter.append(String.valueOf(random.nextInt(RANGE_OF_POSSIBLE_NUMBERS)+1));
                for (int j = 0; j < numberOfColumns-1; j++){
                    fileWriter.append(",");
                    fileWriter.append(String.valueOf(random.nextInt(RANGE_OF_POSSIBLE_NUMBERS)+1));
                }
                fileWriter.append("\n");
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
}

package com.example.task1;

import com.example.task1.file.FileWriter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество строк: ");
        int numberOfRows = scanner.nextInt();
        System.out.println("Введите количество столбцов: ");
        int numberOfColumns = scanner.nextInt();
        scanner.close();

        FileWriter.writeRandomNumbersIntoFile(numberOfRows, numberOfColumns);
    }
}

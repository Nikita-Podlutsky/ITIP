package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatrixMax {
    // Общая переменная для хранения глобального максимума
    private static int globalMax = Integer.MIN_VALUE;

    public static void main(String[] args) throws InterruptedException {
        // 1. Создание матрицы 4x10
        int[][] matrix = new int[4][10];
        Random random = new Random();
        
        System.out.println("Матрица:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = random.nextInt(100); // Случайные числа 0-99
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        // 2. Список потоков
        List<Thread> threads = new ArrayList<>();

        // 3. Создаем поток для каждой строки
        for (int i = 0; i < matrix.length; i++) {
            final int rowIndex = i;
            Thread thread = new Thread(() -> {
                int rowMax = Integer.MIN_VALUE;
                for (int val : matrix[rowIndex]) {
                    if (val > rowMax) {
                        rowMax = val;
                    }
                }
                // Синхронизированное обновление глобального максимума
                synchronized (MatrixMax.class) {
                    if (rowMax > globalMax) {
                        globalMax = rowMax;
                    }
                }
                System.out.println("Поток для строки " + rowIndex + " нашел локальный макс: " + rowMax);
            });
            threads.add(thread);
            thread.start();
        }

        // 4. Ждем завершения всех потоков (join)
        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Наибольший элемент в матрице: " + globalMax);
    }
}
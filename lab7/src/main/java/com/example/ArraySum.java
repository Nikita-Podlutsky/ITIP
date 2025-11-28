package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ArraySum {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 1. Подготовка данных
        int[] array = new int[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i; // Заполняем числами от 0 до 999
        }

        // 2. Создаем пул потоков (например, 4 потока)
        int threadsCount = 4;
        ExecutorService executor = Executors.newFixedThreadPool(threadsCount);
        
        // Разбиваем задачу на части
        int chunkSize = array.length / threadsCount;
        List<Callable<Integer>> tasks = new ArrayList<>();

        for (int i = 0; i < threadsCount; i++) {
            final int start = i * chunkSize;
            final int end = (i == threadsCount - 1) ? array.length : (i + 1) * chunkSize;
            
            // Создаем задачу для отдельного потока
            tasks.add(() -> {
                int sum = 0;
                for (int j = start; j < end; j++) {
                    sum += array[j];
                }
                System.out.println("Поток " + Thread.currentThread().getName() + " посчитал сумму от индекса " + start + " до " + end);
                return sum;
            });
        }

        // 3. Запускаем все задачи и ждем результат
        List<Future<Integer>> results = executor.invokeAll(tasks);
        
        // 4. Суммируем результаты от каждого потока
        int totalSum = 0;
        for (Future<Integer> result : results) {
            totalSum += result.get();
        }

        executor.shutdown();
        
        System.out.println("Общая сумма элементов: " + totalSum);
    }
}
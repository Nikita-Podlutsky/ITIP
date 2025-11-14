package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("===============================================");
        System.out.println("ЗАПУСК ДЕМОНСТРАЦИИ ДЛЯ ЗАДАНИЯ 1: ТОП-10 СЛОВ");
        System.out.println("===============================================");
        demonstrateTop10Words();
        System.out.println("\n");

        System.out.println("=================================================");
        System.out.println("ЗАПУСК ДЕМОНСТРАЦИИ ДЛЯ ЗАДАНИЯ 2: ОБОБЩЕННЫЙ СТЕК");
        System.out.println("=================================================");
        demonstrateGenericStack();
        System.out.println("\n");
        
        System.out.println("=================================================");
        System.out.println("ЗАПУСК ДЕМОНСТРАЦИИ ДЛЯ ЗАДАНИЯ 3: УЧЕТ ПРОДАЖ");
        System.out.println("=================================================");
        demonstrateSalesTracker();
    }

    /**
     * Демонстрация работы Задания 1: Поиск 10 самых популярных слов в файле.
     */
    private static void demonstrateTop10Words() {
        String filePath = "text.txt";
        Map<String, Integer> wordCounts = new HashMap<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                String word = scanner.next()
                        .replaceAll("[^a-zA-Zа-яА-Я]", "")
                        .toLowerCase();
                if (!word.isEmpty()) {
                    wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("ОШИБКА: Файл 'text.txt' не найден! Убедитесь, что он лежит в корне проекта.");
            return;
        }

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(wordCounts.entrySet());
        entryList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        System.out.println("Топ-10 самых часто встречающихся слов в файле:");
        int limit = Math.min(10, entryList.size());
        for (int i = 0; i < limit; i++) {
            Map.Entry<String, Integer> entry = entryList.get(i);
            System.out.println((i + 1) + ". '" + entry.getKey() + "' - " + entry.getValue() + " раз(а)");
        }
    }
    
    /**
     * Демонстрация работы Задания 2: Обобщенный класс Stack<T>.
     */
    private static void demonstrateGenericStack() {
        System.out.println("--- 1. Работа со Stack<Integer> ---");
        Stack<Integer> numberStack = new Stack<>(5);
        numberStack.push(10);
        numberStack.push(20);
        System.out.println("Верхний элемент (peek): " + numberStack.peek());
        System.out.println("Извлекаем элемент (pop): " + numberStack.pop());
        System.out.println("Новый верхний элемент: " + numberStack.peek());
        System.out.println("Стек пуст? " + numberStack.isEmpty());

        System.out.println("\n--- 2. Работа со Stack<String> и обработка исключений ---");
        Stack<String> stringStack = new Stack<>();
        stringStack.push("Hello");
        stringStack.push("World");

        while (!stringStack.isEmpty()) {
            System.out.println("Извлечено: " + stringStack.pop());
        }

        try {
            System.out.println("Пытаемся извлечь из пустого стека...");
            stringStack.pop();
        } catch (EmptyStackException e) {
            System.err.println("ПОЙМАНО ИСКЛЮЧЕНИЕ: Стек пуст, как и ожидалось.");
        }
    }

    /**
     * Демонстрация работы Задания 3: Учет продаж в магазине.
     */
    private static void demonstrateSalesTracker() {
        // Создаем товары
        Product apple = new Product("Яблоко", 50.0);
        Product milk = new Product("Молоко", 80.0);
        Product bread = new Product("Хлеб", 40.0);

        SalesTracker tracker = new SalesTracker();

        // Симулируем продажи
        tracker.addSale(apple);
        tracker.addSale(milk);
        tracker.addSale(apple);
        tracker.addSale(bread);
        tracker.addSale(apple);
        tracker.addSale(milk);

        // Выполняем все операции
        tracker.displaySales();
        double total = tracker.calculateTotalSales();
        System.out.printf("\nОбщая сумма продаж: %.2f руб.\n", total);
        
        tracker.getMostPopularProduct().ifPresent(product -> 
            System.out.println("Самый популярный товар: " + product.getName())
        );
    }
}
package com.example;

public class StoreDemo {
    public static void main(String[] args) {
        // Создаем наши товары
        Product apple = new Product("Яблоко", 50.0);
        Product milk = new Product("Молоко", 80.0);
        Product bread = new Product("Хлеб", 40.0);

        // Создаем трекер продаж
        SalesTracker tracker = new SalesTracker();

        // --- 1. Симулируем продажи ---
        tracker.addSale(apple);
        tracker.addSale(milk);
        tracker.addSale(apple);
        tracker.addSale(bread);
        tracker.addSale(apple); // Яблоки - самый популярный товар
        tracker.addSale(milk);

        System.out.println("\n--- 2. Выводим список продаж ---");
        tracker.displaySales();

        System.out.println("\n--- 3. Считаем общую выручку ---");
        double total = tracker.calculateTotalSales();
        System.out.printf("Общая сумма продаж: %.2f руб.\n", total);

        System.out.println("\n--- 4. Находим самый популярный товар ---");
        tracker.getMostPopularProduct().ifPresent(product -> 
            System.out.println("Самый популярный товар: " + product.getName())
        );
    }
}
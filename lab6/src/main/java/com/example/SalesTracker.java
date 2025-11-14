package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Класс для учета и анализа продаж в магазине.
 * Использует HashMap для эффективного хранения данных.
 */
public class SalesTracker {

    // Карта для хранения товаров и их количества.
    // Ключ - объект Product, Значение - количество проданных штук (Integer).
    private final Map<Product, Integer> salesData = new HashMap<>();

    /**
     * 1. Добавляет информацию о продаже одного товара.
     */
    public void addSale(Product product) {
        // getOrDefault - удобный метод:
        // если ключ (product) уже есть, он вернет его значение,
        // если нет - вернет значение по умолчанию (0).
        salesData.put(product, salesData.getOrDefault(product, 0) + 1);
    }

    /**
     * 2. Выводит на экран список всех проданных товаров и их количество.
     */
    public void displaySales() {
        System.out.println("--- Список проданных товаров ---");
        if (salesData.isEmpty()) {
            System.out.println("Продаж еще не было.");
            return;
        }
        // Проходим по всем записям (парам "ключ-значение") в карте.
        for (Map.Entry<Product, Integer> entry : salesData.entrySet()) {
            System.out.println(entry.getKey().getName() + " - " + entry.getValue() + " шт.");
        }
    }
    
    /**
     * 3. Рассчитывает и возвращает общую сумму всех продаж.
     */
    public double calculateTotalSales() {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : salesData.entrySet()) {
            // Умножаем цену товара на количество проданных штук
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }
    
    /**
     * 4. Находит и возвращает самый популярный товар.
     * Возвращает Optional, чтобы обработать случай, когда продаж не было.
     */
    public Optional<Product> getMostPopularProduct() {
        if (salesData.isEmpty()) {
            return Optional.empty(); // Возвращаем пустой Optional, если продаж нет.
        }
        
        Map.Entry<Product, Integer> mostPopularEntry = null;
        
        for (Map.Entry<Product, Integer> entry : salesData.entrySet()) {
            if (mostPopularEntry == null || entry.getValue() > mostPopularEntry.getValue()) {
                mostPopularEntry = entry;
            }
        }
        
        return Optional.ofNullable(mostPopularEntry.getKey());
    }
}
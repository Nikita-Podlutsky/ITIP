package com.example;

import java.util.Objects;

/**
 * Класс, представляющий товар в магазине.
 */
public class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "'" + name + "' (цена: " + price + " руб.)";
    }
    
    // Переопределяем equals, чтобы продукты с одинаковым названием считались одинаковыми.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    // Переопределяем hashCode, так как это требуется по контракту при переопределении equals.
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
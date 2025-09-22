package com.example;

// Класс для рыбки
public class Fish extends Animal {

    public Fish(String name, int age, double weight) {
        super(name, age, weight);
    }
    public Fish() {
        super("Безымянный рыба", 1, 4.0);
    }
    // рыбы молчат, но метод всеравно должен быть
    @Override
    public void makeSound() {
        System.out.println("... (бульк)");
    }
}
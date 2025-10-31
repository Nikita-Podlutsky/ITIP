package com.example;

// Класс для попугая, тоже наследуется от Animal
public class Parrot extends Animal {

    // Конструктор попугая
    public Parrot(String name, int age, double weight) {
        // опять вызываем конструктор Animal
        super(name, age, weight);
    }
    public Parrot() {
        super("Безымянный попуг", 1, 4.0);
    }
    // даем попугаю его собственный звук
    @Override
    public void makeSound() {
        System.out.println("Чирик!");
    }
}
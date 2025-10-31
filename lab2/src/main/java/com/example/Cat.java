package com.example;

// Класс для кошки, наследуется от Animal
public class Cat extends Animal {

    // Конструктор кошки
    public Cat(String name, int age, double weight) {
        // super() - это вызов конструктора родителя (Animal)
        // чтобы передать ему имя, возраст и вес
        super(name, age, weight);
    }
    public Cat() {
        super("Безымянный кот", 1, 4.0);
    }
    // @Override - значит мы переписываем метод из Animal
    // мы обязаны это сделать, потому что makeSound абстрактный. Но без него тоже можно хотя плохо для дебагинга
    @Override
    public void makeSound() {
        System.out.println("Мяу!");
    }
}
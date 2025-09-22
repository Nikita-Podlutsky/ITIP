package com.example;


public class Main {

    public static void main(String[] args) {

        System.out.println("--- Создаем наших животных ---");
        // Нельзя так как у насон abstract
        // Animal animal = new Animal("Барсик", 3, 5.5);
        
        // Cat - это тип, cat - это имя переменной, а new Cat(...) - создает сам объект
        Cat cat = new Cat("Барсик", 3, 5.5);

        Parrot parrot = new Parrot("Кеша", 2, 0.8);

        Fish fish = new Fish("Немо", 100, 0.1);

        System.out.println("\n--- Проверяем что они умеют делать ---");

        // заставляем кота делать что-то
        System.out.println("Действия кота:");
        cat.eat();
        cat.makeSound();

        // инфа про попугая
        System.out.println("\nИнформация о попугае:");
        parrot.showInfo();
        
        parrot.makeSound();
        
        System.out.println("\nИнформация о рыбе:");
        fish.showInfo();

        System.out.println("\n--- Проверяем общий счетчик ---");

        System.out.println("Всего животных в программе: " + Animal.getAnimalCount());
    }
}
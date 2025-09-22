package com.example;


// abstract значит нельзя создать просто "животное", нужно конкретное
public abstract class Animal {

    // поля которые есть у всех. private чтобы никто случайно не испортил
    private String name;
    private int age;
    private double weight;

    // Счетчик, сколько всего создали
    private static int animalCount = 0; // static значит один на всех

    // Конструктор - вызывается когда пишем new Animal()
    public Animal(String name, int age, double weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        animalCount++;
    }



    // этот метод одинаковый для всех, поэтому пишем его здесь
    public void eat() {
        System.out.println(this.name + " кушает.");
    }

    // тоже общий метод чтобы показать инфу
    public void showInfo() {
        System.out.println("Имя: " + this.name + ", Возраст: " + this.age + " лет, Вес: " + this.weight + " кг");
    }

    
    // этот метод у всех разный, поэтому тут его не пишем
    // но заставляем всех наследников его написать у себя
    public abstract void makeSound();


    public String getName() { return this.name; }
    public int getAge() { return this.age; }
    public double getWeight() { return this.weight; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setWeight(double weight) { this.weight = weight; }

    public static int getAnimalCount() {
        return animalCount;
    }
}
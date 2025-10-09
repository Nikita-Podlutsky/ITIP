package com.example;

public class Student {
    private String firstName;
    private String lastName;
    private int age;
    private double averageScore;

    public Student(String firstName, String lastName, int age, double averageScore) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.averageScore = averageScore;
    }

    // Этот метод нужен, чтобы красиво выводить информацию о студенте на экран
    @Override
    public String toString() {
        return "Студент{" +
               "имя='" + firstName + '\'' +
               ", фамилия='" + lastName + '\'' +
               ", возраст=" + age +
               ", средний балл=" + averageScore +
               '}';
    }
}
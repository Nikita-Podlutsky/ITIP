package com.example;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        
        HashMap<Integer, Student> studentDatabase = new HashMap<>();

        System.out.println("--- Добавляем студентов ---");
        studentDatabase.put(101, new Student("Иван", "Иванов", 20, 4.5));
        studentDatabase.put(102, new Student("Петр", "Петров", 21, 3.8));
        studentDatabase.put(103, new Student("Мария", "Сидорова", 19, 4.9));

        System.out.println("В базе " + studentDatabase.size() + " студентов.");
        System.out.println();


        System.out.println("--- Ищем студентов ---");
        int studentIdToFind = 102;
        Student foundStudent = studentDatabase.get(studentIdToFind);

        if (foundStudent != null) {
            System.out.println("Найден студент с ID " + studentIdToFind + ": " + foundStudent);
        } else {
            System.out.println("Студент с ID " + studentIdToFind + " не найден.");
        }

        Student notFoundStudent = studentDatabase.get(999);
        System.out.println("Результат поиска студента с ID 999: " + notFoundStudent);
        System.out.println();


        System.out.println("--- Удаляем студента ---");
        int studentIdToRemove = 101;
        studentDatabase.remove(studentIdToRemove);
        System.out.println("Студент с ID " + studentIdToRemove + " был удален.");

        Student removedStudent = studentDatabase.get(studentIdToRemove);
        System.out.println("Результат поиска удаленного студента: " + removedStudent);
        System.out.println("Теперь в базе " + studentDatabase.size() + " студентов.");
    }
}
package com.example;

import java.util.EmptyStackException;

public class StackDemo {
    public static void main(String[] args) {
        // --- Демонстрация работы со стеком целых чисел ---
        System.out.println("--- Работа со Stack<Integer> ---");
        Stack<Integer> numberStack = new Stack<>(5); // Создаем стек на 5 элементов

        System.out.println("Добавляем элементы: 10, 20, 30");
        numberStack.push(10);
        numberStack.push(20);
        numberStack.push(30);

        System.out.println("Текущий размер стека: " + numberStack.size()); // Ожидаем 3
        System.out.println("Верхний элемент (peek): " + numberStack.peek()); // Ожидаем 30

        System.out.println("Извлекаем элемент (pop): " + numberStack.pop()); // Ожидаем 30
        System.out.println("Новый верхний элемент (peek): " + numberStack.peek()); // Ожидаем 20
        System.out.println("Текущий размер стека: " + numberStack.size()); // Ожидаем 2

        System.out.println("\n--- Работа со Stack<String> ---");
        Stack<String> stringStack = new Stack<>(); // Используем емкость по умолчанию

        stringStack.push("Java");
        stringStack.push("is");
        stringStack.push("fun");
        
        System.out.println("Извлекаем все элементы из стека строк:");
        while (!stringStack.isEmpty()) {
            System.out.println("pop: " + stringStack.pop());
        }

        // --- Демонстрация обработки исключений ---
        System.out.println("\n--- Демонстрация исключений ---");
        try {
            System.out.println("Пытаемся извлечь элемент из пустого стека...");
            stringStack.pop();
        } catch (EmptyStackException e) {
            System.err.println("Поймали исключение: Стек пуст!");
        }
    }
}
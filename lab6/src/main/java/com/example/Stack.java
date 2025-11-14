package com.example;

import java.util.EmptyStackException;

/**
 * Обобщенный класс, реализующий структуру данных "стек" на основе массива.
 * @param <T> Тип элементов, которые будут храниться в стеке.
 */
public class Stack<T> {

    // Массив для хранения элементов стека.
    private T[] data;
    
    // Текущее количество элементов в стеке. Также указывает на индекс для следующего элемента.
    private int size;
    
    // Максимальная вместимость стека.
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Конструктор, создающий стек с емкостью по умолчанию (10).
     */
    public Stack() {
        // Вызываем другой конструктор с значением по умолчанию.
        this(DEFAULT_CAPACITY);
    }
    
    /**
     * Конструктор, создающий стек с заданной начальной емкостью.
     * @param capacity Максимальное количество элементов, которое может хранить стек.
     */
    @SuppressWarnings("unchecked")
    public Stack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Емкость должна быть положительным числом.");
        }
        // Создаем массив Object и приводим его к T[].
        // Это стандартный способ создания generic-массивов в Java.
        this.data = (T[]) new Object[capacity];
        this.size = 0; // Изначально стек пуст.
    }

    /**
     * Добавляет элемент на вершину стека.
     * @param element Элемент для добавления.
     * @throws IllegalStateException если стек переполнен.
     */
    public void push(T element) {
        if (size == data.length) {
            throw new IllegalStateException("Стек переполнен (Stack Overflow).");
        }
        data[size] = element;
        size++;
    }

    /**
     * Удаляет и возвращает элемент с вершины стека.
     * @return Элемент с вершины стека.
     * @throws EmptyStackException если стек пуст.
     */
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        size--; // Уменьшаем размер, теперь size указывает на элемент, который мы извлекаем.
        T element = data[size];
        data[size] = null; // Очищаем ссылку, чтобы сборщик мусора мог удалить объект.
        return element;
    }

    /**
     * Возвращает элемент с вершины стека, не удаляя его.
     * @return Элемент с вершины стека.
     * @throws EmptyStackException если стек пуст.
     */
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        // Вершина стека находится по индексу size - 1.
        return data[size - 1];
    }
    
    /**
     * Проверяет, пуст ли стек.
     * @return true, если стек пуст, иначе false.
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Возвращает текущее количество элементов в стеке.
     * @return Количество элементов.
     */
    public int size() {
        return size;
    }
}
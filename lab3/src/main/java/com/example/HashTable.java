package com.example;

import java.util.LinkedList;

public class HashTable<K, V> {

    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity = 16;
    private int size = 0;
    private LinkedList<Entry<K, V>>[] table;

    @SuppressWarnings("unchecked")
    public HashTable() {
        table = new LinkedList[capacity];
    }

    private int getIndex(K key) {
        if (key == null) {
            return 0;
        }
        return Math.abs(key.hashCode() % capacity);
    }

    public void put(K key, V value) {
        int index = getIndex(key);

        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        for (Entry<K, V> entry : table[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        table[index].add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> chain = table[index];

        if (chain == null) {
            return null;
        }

        for (Entry<K, V> entry : chain) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    public void remove(K key) {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> chain = table[index];

        if (chain == null) {
            return;
        }

        Entry<K, V> toRemove = null;
        for (Entry<K, V> entry : chain) {
            if (entry.key.equals(key)) {
                toRemove = entry;
                break;
            }
        }

        if (toRemove != null) {
            chain.remove(toRemove);
            size--;
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }
    
    
    public static void main(String[] args) {
        HashTable<Object, Integer> studentGrades = new HashTable<>();
        
        studentGrades.put("Иванов", 5);
        studentGrades.put("Петров", 4);
        studentGrades.put("Сидоров", 3);
        
        System.out.println("Оценка Петрова: " + studentGrades.get("Петров"));
        System.out.println("Размер таблицы: " + studentGrades.size());
        
        studentGrades.put("Петров", 5);
        System.out.println("Новая оценка Петрова: " + studentGrades.get("Петров"));
        
        studentGrades.remove("Сидоров");
        System.out.println("Оценка Сидорова после удаления: " + studentGrades.get("Сидоров"));
        System.out.println("Размер таблицы после удаления: " + studentGrades.size());
        System.out.println("Таблица пуста? " + studentGrades.isEmpty());
    }
}
package com.example;

import java.util.HashMap;
import java.util.Map;

public class NestedHashTable<K, V> {

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

    private Map<Integer, Entry<K, V>>[] table;

    @SuppressWarnings("unchecked")
    public NestedHashTable() {
        table = new HashMap[capacity];
    }

    private int getIndex(K key) {
        if (key == null) {
            return 0;
        }
        return Math.abs(key.hashCode() % capacity);
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        int keyHash = (key == null) ? 0 : key.hashCode();

        if (table[index] == null) {
            table[index] = new HashMap<>();
        }

        boolean exists = table[index].containsKey(keyHash);
        
        if (exists) {
            Entry<K, V> existing = table[index].get(keyHash);
            if (existing.key == null && key == null || 
                existing.key != null && existing.key.equals(key)) {

                existing.value = value;
                return;
            }
        }

        table[index].put(keyHash, new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = getIndex(key);
        int keyHash = (key == null) ? 0 : key.hashCode();
        
        Map<Integer, Entry<K, V>> bucket = table[index];

        if (bucket == null) {
            return null;
        }

        Entry<K, V> entry = bucket.get(keyHash);
        if (entry != null && 
            (entry.key == null && key == null || 
             entry.key != null && entry.key.equals(key))) {
            return entry.value;
        }
        
        return null;
    }

    public void remove(K key) {
        int index = getIndex(key);
        int keyHash = (key == null) ? 0 : key.hashCode();
        
        Map<Integer, Entry<K, V>> bucket = table[index];

        if (bucket == null) {
            return;
        }

        Entry<K, V> entry = bucket.get(keyHash);
        if (entry != null && 
            (entry.key == null && key == null || 
             entry.key != null && entry.key.equals(key))) {
            bucket.remove(keyHash);
            size--;
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }
    
    public void printStructure() {
        System.out.println("\n=== Структура таблицы ===");
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null && !table[i].isEmpty()) {
                System.out.println("Ячейка [" + i + "] содержит " + table[i].size() + " элементов:");
                for (Map.Entry<Integer, Entry<K, V>> e : table[i].entrySet()) {
                    System.out.println("  hash=" + e.getKey() + " -> key=" + 
                                     e.getValue().key + ", value=" + e.getValue().value);
                }
            }
        }
        System.out.println("Всего элементов: " + size);
    }
    
    public static void main(String[] args) {
        NestedHashTable<String, Integer> studentGrades = new NestedHashTable<>();
        
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
        
        studentGrades.printStructure();
    }
}
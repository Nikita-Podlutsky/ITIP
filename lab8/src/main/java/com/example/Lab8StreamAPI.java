package com.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lab8StreamAPI {

    public static void main(String[] args) {
        // 1. Создаем менеджер
        DataManager dataManager = new DataManager();

        // 2. Регистрируем обработчики данных (наши классы с логикой)
        // Сначала фильтруем, потом преобразуем
        dataManager.registerDataProcessor(new FilterProcessor());
        dataManager.registerDataProcessor(new TransformProcessor());

        // 3. Загружаем данные
        dataManager.loadData(Arrays.asList("apple", "cat", "banana", "dog", "elephant", "fox"));

        // 4. Запускаем обработку
        dataManager.processData();

        // 5. Сохраняем результат
        dataManager.saveData("Console Output");
    }

    // --- 1. АННОТАЦИЯ ---
    // @Retention(RUNTIME) - чтобы аннотация была видна во время выполнения программы (через рефлексию)
    // @Target(METHOD) - чтобы ее можно было вешать только на методы
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface DataProcessor {
        String description() default "General processing";
    }

    // --- 2. КЛАСС УПРАВЛЕНИЯ (DataManager) ---
    static class DataManager {
        private List<Object> processors = new ArrayList<>();
        private List<String> data = new ArrayList<>();

        public void registerDataProcessor(Object processor) {
            processors.add(processor);
        }

        public void loadData(List<String> sourceData) {
            this.data = new ArrayList<>(sourceData); // Создаем копию
            System.out.println("Данные загружены: " + data);
        }

        public void saveData(String destination) {
            System.out.println("Сохранение в " + destination + ": " + data);
        }

        public void processData() {
            // Проходим по всем зарегистрированным обработчикам
            for (Object processor : processors) {
                // Используем рефлексию, чтобы найти методы помеченные @DataProcessor
                for (Method method : processor.getClass().getDeclaredMethods()) {
                    if (method.isAnnotationPresent(DataProcessor.class)) {
                        try {
                            System.out.println("Выполняется: " + method.getAnnotation(DataProcessor.class).description());
                            
                            // Вызываем метод. Мы договариваемся, что методы обработки 
                            // принимают List<String> и возвращают List<String>
                            // (unchecked cast допустим для учебного примера)
                            this.data = (List<String>) method.invoke(processor, this.data);
                            
                            System.out.println("Текущее состояние данных: " + this.data);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    // --- 3. ОБРАБОТЧИКИ (Processors) ---

    // Класс для фильтрации (удаляет короткие слова)
    static class FilterProcessor {
        
        @DataProcessor(description = "Фильтрация слов короче 4 символов")
        public List<String> filterWords(List<String> input) {
            return input.stream()
                    .filter(word -> word.length() > 3) // Оставляем только длинные слова
                    .collect(Collectors.toList());
        }
    }

    // Класс для трансформации (перевод в верхний регистр + сортировка)
    static class TransformProcessor {

        @DataProcessor(description = "Преобразование в UpperCase и сортировка")
        public List<String> transformWords(List<String> input) {
            // Используем parallelStream для многопоточной обработки (требование задания)
            return input.parallelStream()
                    .map(String::toUpperCase) // Превращаем "apple" -> "APPLE"
                    .sorted()                 // Сортируем по алфавиту
                    .collect(Collectors.toList());
        }
    }
}
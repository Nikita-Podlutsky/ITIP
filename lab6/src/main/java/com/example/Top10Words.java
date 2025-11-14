package com.example;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Программа для поиска 10 самых часто встречающихся слов в текстовом файле.
 */
public class Top10Words {

    public static void main(String[] args) {
        // Указываем путь к файлу. Файл должен находиться в корне проекта.
        String filePath = "text.txt";

        // 1. Создаем Map для хранения слов и их частоты.
        // Ключ - это слово (String), значение - количество повторений (Integer).
        Map<String, Integer> wordCounts = new HashMap<>();

        // 2. Читаем файл и подсчитываем слова.
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                // Читаем следующее слово, очищаем от пунктуации и приводим к нижнему регистру.
                String word = scanner.next()
                        .replaceAll("[^a-zA-Zа-яА-Я]", "")
                        .toLowerCase();

                // Пропускаем пустые строки, которые могли образоваться после очистки.
                if (!word.isEmpty()) {
                    // Используем getOrDefault для удобного подсчета:
                    // если слово уже есть в карте, берем его значение и прибавляем 1,
                    // если нет - берем 0 (значение по умолчанию) и прибавляем 1.
                    wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка: Файл не найден по пути: " + filePath);
            e.printStackTrace();
            return; // Завершаем программу, если файл не найден.
        }

        // 3. Сортируем слова по частоте.
        // Создаем список из записей (Entry) нашей карты.
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(wordCounts.entrySet());

        // Сортируем список в порядке убывания значений (частоты).
        // Используем лямбда-выражение для простоты.
        entryList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // 4. Выводим топ-10 слов.
        System.out.println("Топ-10 самых часто встречающихся слов в файле:");
        
        // Определяем, сколько слов выводить: 10 или меньше, если уникальных слов не так много.
        int limit = Math.min(10, entryList.size());
        
        for (int i = 0; i < limit; i++) {
            Map.Entry<String, Integer> entry = entryList.get(i);
            System.out.println((i + 1) + ". '" + entry.getKey() + "' - " + entry.getValue() + " раз(а)");
        }
    }
}
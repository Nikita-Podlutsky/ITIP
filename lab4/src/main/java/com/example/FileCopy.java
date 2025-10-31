package com.example;



import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class FileCopy {

    public static void main(String[] args) {

        
        String sourceFile = "og_og_16686801592421761.jpg";
        String destinationFile = "new_file.jpg";

        System.out.println("Начинаем копирование из '" + sourceFile + "' в '" + destinationFile + "'");

        
        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(destinationFile)) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            System.out.println("Файлы успешно открыты. Начинаем чтение и запись...");

            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }

            System.out.println("Копирование успешно завершено!");

        } catch (FileNotFoundException e) {
            
            System.err.println("Ошибка: Исходный файл не найден!");
            System.err.println("Подробности: " + e.getMessage());
        } catch (IOException e) {
            
            System.err.println("Произошла ошибка ввода-вывода во время копирования.");
            System.err.println("Возможные причины:");
            System.err.println("  - Ошибка при чтении из исходного файла (диск поврежден, файл удален во время чтения).");
            System.err.println("  - Ошибка при записи в целевой файл (нет места на диске, нет прав на запись).");
            System.err.println("  - Ошибка при закрытии одного из файлов.");
            System.err.println("Подробности: " + e.getMessage());
        } finally {
            System.out.println("Операция копирования завершена.");
        }
    }
}
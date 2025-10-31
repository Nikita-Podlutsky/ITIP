package com.example.logging;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExceptionLogger {
    private static final String LOG_FILE = "exception_log.txt";
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    /**
     * Записывает информацию об исключении в лог-файл.
     * @param e Исключение для логирования.
     */
    public static void log(Exception e) {
        // Используем try-with-resources для автоматического закрытия PrintWriter и FileWriter
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            LocalDateTime now = LocalDateTime.now();
            writer.println("----------------------------------------");
            writer.println("Timestamp: " + dtf.format(now));
            writer.println("Exception Type: " + e.getClass().getName());
            writer.println("Message: " + e.getMessage());
            writer.println("Stack Trace:");
            // Записываем стектрейс в файл для детального анализа
            e.printStackTrace(writer);
            writer.println("----------------------------------------\n");
        } catch (IOException ioException) {
            System.err.println("CRITICAL ERROR: Could not write to log file!");
            ioException.printStackTrace();
        }
    }
}
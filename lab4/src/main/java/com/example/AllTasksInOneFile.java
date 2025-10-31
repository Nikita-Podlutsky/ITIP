package com.example;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AllTasksInOneFile {

    public static class ExceptionLogger {
        private static final String LOG_FILE = "exception_log.txt";
        private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        public static void log(Exception e, String taskDescription) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
                writer.println("----------------------------------------");
                writer.println("Task: " + taskDescription);
                writer.println("Timestamp: " + dtf.format(LocalDateTime.now()));
                writer.println("Exception Type: " + e.getClass().getName());
                writer.println("Message: " + e.getMessage());
                writer.println("Stack Trace:");
                e.printStackTrace(writer);
                writer.println("----------------------------------------\n");
            } catch (IOException ioException) {
                System.err.println("CRITICAL ERROR: Could not write to log file!");
                ioException.printStackTrace();
            }
        }
    }

    public static class CustomDivisionException extends Exception {
        public CustomDivisionException(String message) { super(message); }
    }

    public static class CustomAgeException extends Exception {
        public CustomAgeException(String message) { super(message); }
    }

    public static class CustomFileNotFoundException extends Exception {
        public CustomFileNotFoundException(String message) { super(message); }
    }

    public static class CustomNumberFormatException extends Exception {
        public CustomNumberFormatException(String message) { super(message); }
    }

    public static class CustomEmptyStackException extends Exception {
        public CustomEmptyStackException(String message) { super(message); }
    }

    public static class CustomInputMismatchException extends Exception {
        public CustomInputMismatchException(String message) { super(message); }
    }

    public static class CustomEmailFormatException extends Exception {
        public CustomEmailFormatException(String message) { super(message); }
    }

    public static class CustomUnsupportedOperationException extends Exception {
        public CustomUnsupportedOperationException(String message) { super(message); }
    }

    public static double divide(double a, double b) throws CustomDivisionException {
        if (b == 0) {
            throw new CustomDivisionException("Деление на ноль запрещено!");
        }
        return a / b;
    }

    public static void validateAge(int age) throws CustomAgeException {
        if (age < 0 || age > 120) {
            throw new CustomAgeException("Недопустимый возраст: " + age + ". Возраст должен быть в диапазоне от 0 до 120.");
        }
    }

    public static void readFile(String filePath) throws CustomFileNotFoundException {
        try {
            new java.io.FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new CustomFileNotFoundException("Файл не найден по пути: " + filePath);
        }
    }

    public static int convertToInt(String str) throws CustomNumberFormatException {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new CustomNumberFormatException("Строка '" + str + "' не может быть преобразована в число.");
        }
    }

    public static class CustomStack<T> {
        private final ArrayList<T> elements = new ArrayList<>();
        public void push(T item) { elements.add(item); }
        public T pop() throws CustomEmptyStackException {
            if (elements.isEmpty()) {
                throw new CustomEmptyStackException("Попытка извлечь элемент из пустого стека.");
            }
            return elements.remove(elements.size() - 1);
        }
    }

    public static int readInteger(Scanner scanner) throws CustomInputMismatchException {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new CustomInputMismatchException("Введенные данные не являются целым числом.");
        }
    }

    public static void validateEmail(String email) throws CustomEmailFormatException {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if (email == null || !email.matches(emailRegex)) {
            throw new CustomEmailFormatException("Неверный формат email-адреса: " + email);
        }
    }

    public static double calculate(double a, double b, char operation) throws CustomUnsupportedOperationException {
        switch (operation) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
            default:
                throw new CustomUnsupportedOperationException("Операция '" + operation + "' не поддерживается.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Запуск демонстрации обработки исключений. Результаты ошибок будут в консоли и в файле exception_log.txt");

        
        try {
            System.out.println("\n--- Задача 1: Попытка деления на ноль ---");
            divide(10, 0);
        } catch (CustomDivisionException e) {
            System.err.println("Перехвачено: " + e.getMessage());
            ExceptionLogger.log(e, "Задача 1: Деление на ноль");
        }

        // --- Задача 2: Проверка возраста ---
        try {
            System.out.println("\n--- Задача 2: Попытка указать некорректный возраст ---");
            validateAge(150);
        } catch (CustomAgeException e) {
            System.err.println("Перехвачено: " + e.getMessage());
            ExceptionLogger.log(e, "Задача 2: Проверка возраста");
        }

        // --- Задача 3: Файл не найден ---
        try {
            System.out.println("\n--- Задача 3: Попытка прочитать несуществующий файл ---");
            readFile("non_existent_file.txt");
        } catch (CustomFileNotFoundException e) {
            System.err.println("Перехвачено: " + e.getMessage());
            ExceptionLogger.log(e, "Задача 3: Файл не найден");
        }

        // --- Задача 4: Неверный формат числа ---
        try {
            System.out.println("\n--- Задача 4: Попытка преобразовать 'abc' в число ---");
            convertToInt("abc");
        } catch (CustomNumberFormatException e) {
            System.err.println("Перехвачено: " + e.getMessage());
            ExceptionLogger.log(e, "Задача 4: Неверный формат числа");
        }

        // --- Задача 5: Пустой стек ---
        try {
            System.out.println("\n--- Задача 5: Попытка извлечь элемент из пустого стека ---");
            new CustomStack<String>().pop();
        } catch (CustomEmptyStackException e) {
            System.err.println("Перехвачено: " + e.getMessage());
            ExceptionLogger.log(e, "Задача 5: Пустой стек");
        }

        // --- Задача 6: Ошибка ввода с клавиатуры ---
        try {
            System.out.println("\n--- Задача 6: Имитация ввода 'текст' вместо числа ---");
            readInteger(new Scanner("текст"));
        } catch (CustomInputMismatchException e) {
            System.err.println("Перехвачено: " + e.getMessage());
            ExceptionLogger.log(e, "Задача 6: Ошибка ввода");
        }

        // --- Задача 7: Неверный формат Email ---
        try {
            System.out.println("\n--- Задача 7: Попытка валидации некорректного email ---");
            validateEmail("invalid-email.com");
        } catch (CustomEmailFormatException e) {
            System.err.println("Перехвачено: " + e.getMessage());
            ExceptionLogger.log(e, "Задача 7: Неверный формат Email");
        }

        // --- Задача 8: Неподдерживаемая операция ---
        try {
            System.out.println("\n--- Задача 8: Попытка выполнить неподдерживаемую операцию '%' ---");
            calculate(10, 5, '%');
        } catch (CustomUnsupportedOperationException e) {
            System.err.println("Перехвачено: " + e.getMessage());
            ExceptionLogger.log(e, "Задача 8: Неподдерживаемая операция");
        }
    }
}
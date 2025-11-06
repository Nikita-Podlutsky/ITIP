package com.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

/**
 * Задание 1: Поиск всех чисел в тексте.
 * Программа находит и выводит все числовые значения из заданной строки.
 */
class NumberFinder {

    public static List<String> findNumbers(String text) {
        // Проверка на случай, если на вход подана пустая строка
        if (text == null || text.isEmpty()) {
            System.out.println("Ошибка: Входная строка не может быть пустой.");
            return new ArrayList<>();
        }

        List<String> numbers = new ArrayList<>();
        // Регулярное выражение для поиска целых чисел и чисел с плавающей точкой
        // \\d+ - одна или более цифр
        // (\\.\\d+)? - опциональная группа, состоящая из точки и одной или более цифр
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            numbers.add(matcher.group());
        }
        return numbers;
    }

    public static void main(String[] args) {
        System.out.println("--- Задание 1: Поиск чисел в тексте ---");
        String text = "The price of the product is $19.99, an old price was 150, and the discount is 25.5%. Item number is 12345.";
        System.out.println("Исходный текст: " + text);
        
        List<String> foundNumbers = findNumbers(text);
        
        if (foundNumbers.isEmpty()) {
            System.out.println("Числа в тексте не найдены.");
        } else {
            System.out.println("Найденные числа: " + foundNumbers);
        }
        System.out.println();
    }
}

/**
 * Задание 2: Проверка корректности ввода пароля.
 */
class PasswordValidator {
    
    // Регулярное выражение для проверки пароля:
    // ^                  - начало строки
    // (?=.*[0-9])        - как минимум одна цифра (positive lookahead)
    // (?=.*[A-Z])        - как минимум одна заглавная буква (positive lookahead)
    // [a-zA-Z0-9]{8,16}  - от 8 до 16 латинских букв или цифр
    // $                  - конец строки
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])[a-zA-Z0-9]{8,16}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public static boolean isPasswordValid(String password) {
        if (password == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println("--- Задание 2: Проверка корректности пароля ---");
        String[] testPasswords = {
            "ValidPass1",
            "invalidpass",
            "Invalid1",
            "NoDigitHERE",
            "nouppercase123",
            "ValidPassword1234567",
            "CorrectHorseBatteryStaple1"
        };

        for (String pass : testPasswords) {
            System.out.printf("Пароль \"%s\": %s%n", pass, isPasswordValid(pass) ? "Корректный" : "Некорректный");
        }
        System.out.println();
    }
}

/**
 * Задание 3: Поиск заглавной буквы после строчной.
 */
class CasePatternFinder {

    public static String highlightPattern(String text) {
        if (text == null || text.isEmpty()) {
            System.out.println("Ошибка: Входная строка не может быть пустой.");
            return "";
        }
        // Регулярное выражение для поиска строчной буквы, за которой сразу идет заглавная.
        // ([a-z]) - первая группа: любая строчная латинская буква
        // ([A-Z]) - вторая группа: любая заглавная латинская буква
        // $0 - ссылка на всю найденную группу (например, "aB")
        return text.replaceAll("([a-z])([A-Z])", "!$0!");
    }

    public static void main(String[] args) {
        System.out.println("--- Задание 3: Поиск заглавной буквы после строчной ---");
        String text = "ПримерСтроки для JavaCode и testCase. AnotherExample.";
        System.out.println("Исходный текст: " + text);
        String result = highlightPattern(text);
        System.out.println("Обработанный текст: " + result);
        System.out.println();
    }
}

/**
 * Задание 4: Проверка корректности ввода IP-адреса.
 */
class IpAddressValidator {
    
    // Регулярное выражение для проверки IP-адреса
    // Число от 0 до 255 можно описать как:
    // 25[0-5] | 2[0-4][0-9] | [01]?[0-9][0-9]?
    private static final String IP_OCTET_PATTERN = "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
    private static final String IP_ADDRESS_PATTERN = "^" + IP_OCTET_PATTERN + "\\." + IP_OCTET_PATTERN + "\\." + IP_OCTET_PATTERN + "\\." + IP_OCTET_PATTERN + "$";
    private static final Pattern pattern = Pattern.compile(IP_ADDRESS_PATTERN);

    public static boolean isIpAddressValid(String ipAddress) {
        if (ipAddress == null) {
            return false;
        }
        return pattern.matcher(ipAddress).matches();
    }

    public static void main(String[] args) {
        System.out.println("--- Задание 4: Проверка корректности IP-адреса ---");
        String[] testIps = {
            "192.168.0.1",
            "255.255.255.255",
            "0.0.0.0",
            "256.1.1.1",
            "192.168.0.256",
            "1.2.3",
            "not.an.ip.address"
        };
        
        for (String ip : testIps) {
            System.out.printf("IP-адрес \"%s\": %s%n", ip, isIpAddressValid(ip) ? "Корректный" : "Некорректный");
        }
        System.out.println();
    }
}

/**
 * Задание 5: Поиск всех слов, начинающихся с заданной буквы.
 */
class WordFinder {

    public static List<String> findWordsStartingWith(String text, char letter) {
        if (text == null || text.isEmpty()) {
            System.out.println("Ошибка: Входная строка не может быть пустой.");
            return new ArrayList<>();
        }
        
        List<String> result = new ArrayList<>();
        String regex = "\\b" + Pattern.quote(String.valueOf(letter)) + "\\p{L}*\\b";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("--- Задание 5: Поиск слов на заданную букву (Исправлено) ---");
        String text = "Простая программа для поиска слов. Пример выполнения прост.";
        char letterToFind = 'п';
        System.out.println("Исходный текст: " + text);
        System.out.println("Ищем слова на букву: '" + letterToFind + "'");

        List<String> foundWords = findWordsStartingWith(text, letterToFind);

        if (foundWords.isEmpty()) {
            System.out.println("Слова не найдены.");
        } else {
            System.out.println("Найденные слова: " + foundWords);
        }
    }
}

/**
 * Главный класс для запуска всех заданий по очереди.
 */
public class Lab5Executor {
    public static void main(String[] args) {
        NumberFinder.main(args);
        PasswordValidator.main(args);
        CasePatternFinder.main(args);
        IpAddressValidator.main(args);
        WordFinder.main(args);
    }
}
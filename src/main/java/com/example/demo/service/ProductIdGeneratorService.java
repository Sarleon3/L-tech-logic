package com.example.demo.service;

import com.example.demo.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

@Service
public class ProductIdGeneratorService {

    @Autowired
    private ProductsRepository productsRepository;

    private static String PI_DIGITS;  // Число π
    private static int currentIndex = 0;  // Индекс для текущего положения окна
    private static HashSet<String> uniqueNumbers = new HashSet<>(); // Для хранения уникальных чисел
    private static ArrayList<String> duplicateNumbers = new ArrayList<>(); // Для хранения повторяющихся чисел

    private static HashSet<String> exceptions = new HashSet<>(); // Для хранения чисел-исключений
    private static ArrayList<String> highlightedExceptions = new ArrayList<>(); // Для хранения чисел-исключений, которые не сгенерированы

    private static int numberLength = 9; // Длина генерируемых чисел

    // Загружаем числа-исключения из файла
    private void loadOrCreateExceptionsFile(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                exceptions.add(line.trim());  // Убираем пробелы и добавляем в набор исключений
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Файл с исключениями не найден, создаем новый.");
            try {
                new BufferedWriter(new FileWriter(filePath)).close(); // Создаем новый файл
            } catch (IOException ex) {
                ex.printStackTrace();
                throw new RuntimeException("Ошибка при создании файла исключений.");
            }
        }
    }

    // Загружаем число π из файла из classpath
    private void loadPiFromFile(String filePath) {
        try {
            ClassPathResource resource = new ClassPathResource(filePath);
            byte[] fileData = FileCopyUtils.copyToByteArray(resource.getInputStream());
            PI_DIGITS = new String(fileData).trim();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при загрузке числа π из файла: " + e.getMessage());
        }
    }

    // Генерация одного уникального числа
    public String generateUniqueNumber() {
        if (PI_DIGITS == null || PI_DIGITS.isEmpty()) {
            System.out.println("Ошибка: число π не инициализировано или пусто.");
            return null;
        }

        if (currentIndex + numberLength > PI_DIGITS.length()) {
            System.out.println("Индекс выходит за пределы длины числа π. Остановка генерации.");
            return null;
        }

        StringBuilder piFragment = new StringBuilder();
        for (int i = 0; i < numberLength; i++) {
            piFragment.append(PI_DIGITS.charAt(currentIndex + i));
        }

        String generatedNumber = piFragment.toString();

        // Проверяем, существует ли ID в базе данных
        if (productsRepository.existsById(Integer.parseInt(generatedNumber))) {
            currentIndex++;
            return generateUniqueNumber(); // Рекурсивно вызываем метод для генерации нового числа
        }

        if (uniqueNumbers.contains(generatedNumber)) {
            duplicateNumbers.add(generatedNumber);
            currentIndex++;
            return generateUniqueNumber(); // Рекурсивно вызываем метод для генерации нового числа
        }

        if (exceptions.contains(generatedNumber)) {
            highlightedExceptions.add(generatedNumber);
            addExceptionToFile(generatedNumber);
            currentIndex++;
            return generateUniqueNumber(); // Рекурсивно вызываем метод для генерации нового числа
        }

        uniqueNumbers.add(generatedNumber);
        currentIndex++;

        return generatedNumber; // Возвращаем одно уникальное число
    }

    // Метод для добавления исключения в файл
    private void addExceptionToFile(String exception) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("I:\\исключения.txt", true))) {
            writer.write(exception + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initializeGenerator() {
        // Изменяем путь к файлу исключений на относительный или ресурсный, если он тоже абсолютный
        // loadOrCreateExceptionsFile("I:\\исключения.txt"); // Проверьте этот путь тоже!
        loadPiFromFile("10m.txt"); // Читаем из classpath
    }
}

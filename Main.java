package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {
    
    public static void main(String[] args) {
        String input = null;
        int[] arr = new int[10];
        
        while (!"4".equals(input)) {
            System.out.println("Выберите один из предложенных вариантов: \n1) Мануальный ввод\n2) Генерация случайного массива\n3) Загрузка массива из файла\n4) Выход");
            input = System.console().readLine();
            
            switch (input) {
                case "1":
                    for (int i = 0; i < 10; i++) {
                        System.out.println("Введите целое число в ячейку №" + i);
                        arr[i] = Integer.parseInt(System.console().readLine());
                    }
                    System.out.println("Ввод окончен. Производится сортировка.");
                    bucketSort(arr);
                    output(arr);
                    break;
                    
                case "2":
                    Random random = new Random();
                    for (int i = 0; i < 10; i++) {
                        arr[i] = random.nextInt(200);
                    }
                    System.out.println("Исходный массив: " + Arrays.toString(arr));
                    System.out.println("Массив сгенерирован. Производится сортировка.");
                    bucketSort(arr);
                    output(arr);
                    break;
                    
                case "3":
                    String strArr = "";
                    System.out.println("Введите путь к файлу:");
                    String path = System.console().readLine();
                    
                    try (FileReader reader = new FileReader(path)) {
                        int c;
                        while ((c = reader.read()) != -1) {
                            strArr = strArr + (char) c;
                        }
                        
                        arr = Arrays.stream(strArr.trim().split("\\s+"))
                                  .mapToInt(Integer::parseInt)
                                  .toArray();
                        
                        System.out.println("Исходный массив: " + Arrays.toString(arr));
                        System.out.println("Массив получен. Производится сортировка.");
                        bucketSort(arr);
                        output(arr);
                        
                    } catch (IOException ex) {
                        System.out.println("Файл не найден или ошибка чтения.");
                    } catch (NumberFormatException ex) {
                        System.out.println("Ошибка формата данных в файле.");
                    }
                    break;
                    
                case "4":
                    System.out.println("Программа завершена.");
                    break;
                    
                default:
                    System.out.println("Неверный ввод. Пожалуйста, выберите вариант от 1 до 4.");
            }
        }
    }
    
    public static void bucketSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int max = arr[0];
        int min = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        if (max == min) {
            return;
        }
        int bucketCount = 5;
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }
        for (int num : arr) {
            int bucketIndex = (num - min) * (bucketCount - 1) / (max - min);
            buckets.get(bucketIndex).add(num);
        }
        int index = 0;
        for (ArrayList<Integer> bucket : buckets) {
            Collections.sort(bucket);
            for (int num : bucket) {
                arr[index++] = num;
            }
        }
    }
    
    public static void saveArrayToFile(int[] arr, String filePath) {
        try {
            Path path = Paths.get(filePath);

            if (Files.isDirectory(path)) {
                path = path.resolve("data_array.txt"); 
            }
            String content = Arrays.stream(arr)
                                   .mapToObj(String::valueOf)
                                   .collect(Collectors.joining(" "));

            Files.write(path, content.getBytes());

            System.out.println("Готово! Файл сохранен здесь: " + path.toAbsolutePath());

        } catch (IOException e) {
            System.err.println("Ошибка: Убедитесь, что папка существует и у вас есть права доступа.");
            System.err.println("Детали: " + e.getMessage());
        }
    }
    public static void output(int[] arr) {
    	String input2 = null;
        System.out.println("Выберите один из предложенных вариантов: \n1)Вывести массив на экран\n2)Сохранить отсортированный массив в файл");
        input2 = System.console().readLine();
        switch (input2) {
        	case "1":
        		System.out.println("Отсортированный массив: " + Arrays.toString(arr));
        		break;
        	case "2":
        		System.out.println("Введите путь до файла:");
        		String filePath = System.console().readLine();
			saveArrayToFile(arr, filePath);
        		break;
        	default:
        		System.out.println("Неверный ввод. Пожалуйста, выберите вариант от 1 или 2.");
        }
    }
}
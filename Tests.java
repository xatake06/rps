package lab2tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Tests {

	public static void main(String[] args) {
        System.out.println("Запуск тестов...");
        
        // Тест 1: Обычный массив
        int[] test1 = {29, 15, 37, 8, 42, 11, 5, 33, 19, 24};
        System.out.println("Тест 1 - Обычный массив:");
        System.out.println("  До сортировки: " + Arrays.toString(test1));
        bucketSort(test1);
        System.out.println("  После сортировки: " + Arrays.toString(test1));
        
        // Тест 2: Уже отсортированный массив
        int[] test2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("Тест 2 - Отсортированный массив:");
        System.out.println("  До сортировки: " + Arrays.toString(test2));
        bucketSort(test2);
        System.out.println("  После сортировки: " + Arrays.toString(test2));
        
        // Тест 3: Массив с одинаковыми элементами
        int[] test3 = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
        System.out.println("Тест 3 - Одинаковые элементы:");
        System.out.println("  До сортировки: " + Arrays.toString(test3));
        bucketSort(test3);
        System.out.println("  После сортировки: " + Arrays.toString(test3));
        
        // Тест 4: Массив с отрицательными числами
        int[] test4 = {-5, 10, -3, 8, -1, 0, 7, -8, 4, -2};
        System.out.println("Тест 4 - С отрицательными числами:");
        System.out.println("  До сортировки: " + Arrays.toString(test4));
        bucketSort(test4);
        System.out.println("  После сортировки: " + Arrays.toString(test4));
        
        System.out.println("Тесты завершены.");
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
}

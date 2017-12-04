package ru.mail.polis.Task2.BubbleSort2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BubbleSort2 {

    private static int bubbleSort(int[] array) {
        int count = 0;
        boolean wasSwap = true;
        for (int i = 0; wasSwap; i++) {

            wasSwap = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    wasSwap = true;
                    count++;
                }
            }
        }
        return count;
    }

    private static void swap(int[] array, int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            String[] a = reader.readLine().split(" ");
            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = Integer.parseInt(a[i]);
            }
            System.out.println(bubbleSort(array));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

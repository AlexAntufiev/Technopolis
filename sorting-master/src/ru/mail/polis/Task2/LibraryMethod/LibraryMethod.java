package ru.mail.polis.Task2.LibraryMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LibraryMethod {

    private static void swap(int[] array, int firstIndex, int secondIndex) {
        if (firstIndex != secondIndex) {
            int temp = array[firstIndex];
            array[firstIndex] = array[secondIndex];
            array[secondIndex] = temp;
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i]);
                if (i != array.length - 1) {
                    System.out.print(" ");
                }
            }
            if (firstIndex != array.length - 1) {
                System.out.println();
            }
        }
    }

    public static void insertSortSimple(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j >= 1 && array[j] < array[j - 1]; j--) {
                swap(array, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            String[] a = reader.readLine().split(" ");
            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = Integer.parseInt(a[i]);
            }
            insertSortSimple(array);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
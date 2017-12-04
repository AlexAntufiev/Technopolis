package ru.mail.polis.Task2.MinMethod;

import java.io.*;
import java.util.StringTokenizer;

public class MinMethod {

    static class FastScanner {
        BufferedReader bufferedReader;
        StringTokenizer stringTokenizer;

        FastScanner() {
//            try {
//                bufferedReader = new BufferedReader(new FileReader(new File("input.txt")));
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
        }

        String next() {
            while (stringTokenizer == null || !stringTokenizer.hasMoreTokens()) {
                try {
                    stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return stringTokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    private static int selectSort(int[] array) {
        int count = 0;
        //int firstIndex = 0;
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            int j;
            for (j = i + 1; j < array.length; j++) {
                if (array[min] > array[j]) {
                    min = j;
                }
            }
            //if ((min == firstIndex || i == firstIndex) && !(min == firstIndex && min == i)) {
            if (min != j) {
               // firstIndex = min;
                count++;
            }
            swap(array, min, i);
        }
        return count;
    }

    private static void swap(int[] array, int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner();
//        try (PrintWriter printWriter = new PrintWriter(new File("output.txt"))) {
        try (PrintWriter printWriter = new PrintWriter(System.out)) {
            int n = scanner.nextInt();
            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = scanner.nextInt();
            }
            printWriter.print(selectSort(array));
        }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
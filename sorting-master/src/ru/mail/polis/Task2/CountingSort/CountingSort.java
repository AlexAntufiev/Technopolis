package ru.mail.polis.Task2.CountingSort;

import java.io.*;
import java.util.StringTokenizer;

public class CountingSort {

    public static void sort(int[] array) {
        int[] count = new int[100001];
        for (int x : array) count[x]++;
        int pos = 0;
        for (int i = 0; i <= 100000; i++) {
            for (int j = 0; j < count[i]; j++) {
                array[pos++] = i;
            }
        }
    }

    static class FastScanner {
        BufferedReader bufferedReader;
        StringTokenizer stringTokenizer;

        FastScanner() {
            try {
                bufferedReader = new BufferedReader(new FileReader(new File("input.txt")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
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

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner();
        try (PrintWriter printWriter = new PrintWriter(new File("output.txt"))) {
            int n = scanner.nextInt();
            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = scanner.nextInt();
            }
            sort(array);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < n; i++) {
                stringBuilder.append(array[i]).append(" ");
            }
            printWriter.print(stringBuilder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}

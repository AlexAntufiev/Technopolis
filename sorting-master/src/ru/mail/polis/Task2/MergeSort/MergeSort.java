package ru.mail.polis.Task2.MergeSort;

import java.io.*;
import java.util.StringTokenizer;

public class MergeSort {

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

    private static void merge(int[] arrayFirst, int[] arraySecond, int[] tF, int[] tS, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            tF[i] = arrayFirst[i];
            tS[i] = arraySecond[i];
        }
        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i > mid) {
                arrayFirst[k] = tF[j];
                arraySecond[k] = tS[j++];
            } else if (j > right) {
                arrayFirst[k] = tF[i];
                arraySecond[k] = tS[i++];
            } else if (tF[j] < tF[i]) {
                arrayFirst[k] = tF[j];
                arraySecond[k] = tS[j++];
            } else {
                arrayFirst[k] = tF[i];
                arraySecond[k] = tS[i++];
            }
        }
    }

    private static void sort(int[] arrayFirst, int[] arraySecond) {
        int[] tF = new int[arrayFirst.length];
        int[] tS = new int[arrayFirst.length];
        for (int len = 1; len < arrayFirst.length; len *= 2) {
            for (int left = 0; left < arrayFirst.length - len; left += len + len) {
                int mid = left + len - 1;
                int right = Math.min(left + len + len - 1, arrayFirst.length - 1);
                merge(arrayFirst, arraySecond, tF, tS, left, mid, right);
            }
        }
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner();
        try (PrintWriter printWriter = new PrintWriter(new File("output.txt"))) {
            int n = scanner.nextInt();
            int[] arrayFirst = new int[n];
            int[] arraySecond = new int[n];
            for (int i = 0; i < n; i++) {
                arrayFirst[i] = scanner.nextInt();
                arraySecond[i] = scanner.nextInt();
            }
            sort(arrayFirst, arraySecond);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < n; i++) {
                stringBuilder.append(arrayFirst[i]).append(" ").append(arraySecond[i]).append("\n");
            }
            printWriter.print(stringBuilder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
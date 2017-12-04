package ru.mail.polis.Task2.AntiQuickSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class AntiQuickSort {

    static int[] temp;
    static int value;

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


    private static void shuf(int[] array, int[] b, int left, int right) {
        int m = (left + right) / 2;
        b[m] = array[value++];
        if (left < m)
            shuf(array, b, left, m - 1);
        if (right > m)
            shuf(array, b, m + 1, right);
    }

    private static void shuffle(int[] array) {
        int[] temp = new int[array.length];
        shuf(array, temp, 0, array.length - 1);
        for (int i = 0; i < array.length; i++)
            array[i] = temp[i];
    }

    private static int[] solve(int[] a) {
        int n = a.length;
        int dir = 1;
        int q = 1;

        int m = n;
        int x = (n - 1) / 2;
        dir = (n % 2 == 0 ? 1 : -1);
        int curr = 1;

        while (m > 0) {
            a[x] = curr;
            x += q * dir;
            curr++;
            q++;
            m--;
            dir = -dir;
        }

        return a;
    }


    public static void main(String[] args) {
        FastScanner scanner = new FastScanner();
//        try (PrintWriter printWriter = new PrintWriter(new File("output.txt"))) {
        int[] array = new int[scanner.nextInt()];

        int[] a = solve(array);
        for (int i = 0; i < a.length; i++) {
            System.out.printf("%d%c", a[i], (i == a.length - 1 ? '\n' : ' '));
        }
    }

}
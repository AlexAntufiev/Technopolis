package ru.mail.polis.Task2.QuickSort;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;


public class QuickSort {

    private static final int INSERTION_SORT_THRESHOLD = 7;

    private static <Key extends Comparable<Key>> void quickSort0(Key[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int idx = partitionWithRandom(array, left, right);
        quickSort0(array, left, idx);
        quickSort0(array, idx + 1, right);
    }

    public static <Key extends Comparable<Key>> void swap(Key[] array, int firstIndex, int secondIndex) {
        Key temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    private static <Key extends Comparable<Key>> int partitionWithRandom(Key[] array, int left, int right) {
        Key p = array[left + new Random().nextInt(right - left + 1)];
//        Key p = array[right - left / 2];
        int i = left, j = right;
        while (i <= j) {
            while (array[i].compareTo(p) < 0) {
                i++;
            }
            while (array[j].compareTo(p) > 0) {
                j--;
            }
            if (i <= j) {
                swap(array, i++, j--);
            }
        }
        return j;
    }

    private static <Key extends Comparable<Key>> void shift(Key[] array, int insertIndex, int index) {
        Key temp = array[index];
        System.arraycopy(array, insertIndex, array, insertIndex + 1, index - insertIndex);
        array[insertIndex] = temp;
    }

    private static <Key extends Comparable<Key>> int binarySearch(Key[] array, int startIndex, int endIndex, Key key) {
        int start = startIndex;
        int end = endIndex - 1;
        while (start <= end) {
            int mid = (start + end) >>> 1;
            if (array[mid].compareTo(key) < 0) {
                start = mid + 1;
            } else if (array[mid].compareTo(key) > 0) {
                end = mid - 1;
            } else {
                return mid;
            }
        }
        return start;
    }

    public static <Key extends Comparable<Key>> Key[] sort(Key[] array) {
        for (int i = 1; i < array.length; i++) {
            int index = binarySearch(array, 0, i, array[i]);
            shift(array, index, i);
        }
        return array;
    }


    public static <Key extends Comparable<Key>> void sort1(Key[] array) {
        if (array.length < INSERTION_SORT_THRESHOLD) {
            sort(array);
        } else {
        quickSort0(array, 0, array.length - 1);
        }
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] a = reader.readLine().split(" ");
            Integer[] array = new Integer[a.length];
            for (int i = 0; i < array.length; i++) {
                array[i] = Integer.parseInt(a[i]);
            }
            sort1(array);
            for (int i : array) {
                System.out.print(i + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package ru.mail.polis.sort;

import java.util.Random;

public class QuickSort<T extends Comparable<T>> extends AbstractSortOnComparisons<T> {
    private static final int INSERTION_SORT_THRESHOLD = 7;

    private void quickSort0(T[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int idx = partitionWithRandom(array, left, right);
        quickSort0(array, left, idx);
        quickSort0(array, idx + 1, right);
    }

    private int partitionWithRandom(T[] array, int left, int right) {
        T p = array[new Random(left).nextInt(right)];
        int i = left, j = right;
        while (i <= j) {
            while (lesser(array[i], p)) {
                i++;
            }
            while (greater(array[j], p)) {
                j--;
            }
            if (i <= j) {
                swap(array, i++, j--);
            }
        }
        return j;
    }

    private void quickSortThreeParts(T[] array, int left, int right) {
        T v = array[right];
        if (right <= left)
            return;
        int i = left;
        int j = right - 1;
        int p = left - 1;
        int q = right;
        while (i <= j) {
            while (lesser(array[i], v)) {
                i++;
            }
            while (greater(array[j--], v)) {
                j--;
            }
            if (i == j)
                break;
        }

        if (i >= j)
//            break;
            swap(array, i, j);
        if (compare(array[i], v) == 0) {
            p++;
            swap(array, p, i);
        }
        if (compare(array[j], v) == 0) {
            q--;
            swap(array, q, j);
        }
        swap(array, i, right);
        j = i - 1;
        i++;
        for (int k = 1; k <= p; k++, j--)
            swap(array, k, j);
        for (int k = right - 1; k >= q; k--, i++)
            swap(array, k, i);
        quickSortThreeParts(array, 1, j);
        quickSortThreeParts(array, i, right);
    }

    @Override
    public void sort(T[] array) {
        if (array.length < INSERTION_SORT_THRESHOLD) {
            new InsertSort<T>().sort(array);
        } else {
            quickSort0(array, 0, array.length - 1);
        }
    }

    public void sort2(T[] array) {
        quickSortThreeParts(array, 0, array.length);
    }
}

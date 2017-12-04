package ru.mail.polis.sort;

public class ShellSort<T extends Comparable<T>> extends AbstractSortOnComparisons<T> {

    @Override
    public void sort(T[] array) {
        int h = 1;
        while (h < array.length / 3) {
            h = 3 * h + 1;
        }
        while (h > 0) {
            for (int i = h; i < array.length; i++) {
                for (int j = i; j >= h && lesser(array[j], array[j - h]); j -= h) {
                    swap(array, j, j - h);
                }
            }
            h /= 3;
        }
    }
}

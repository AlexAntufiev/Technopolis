package ru.mail.polis.sort;

public class SelectSort<T extends Comparable<T>> extends AbstractSortOnComparisons<T> {

    @Override
    public void sort(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length - 1; j++) {
                if (greater(array[min], array[j])) {
                    min = j;
                }
            }
            swap(array, min, i);
        }
    }
}

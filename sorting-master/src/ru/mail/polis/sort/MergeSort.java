package ru.mail.polis.sort;

public class MergeSort<T extends Comparable<T>> extends AbstractSortOnComparisons<T> {

    private void mergeSort0(T[] array, int left, int right) {
        if (right <= left) {
            return;
        }
        int mid = (left + right) >>> 1;
        mergeSort0(array, left, mid);
        mergeSort0(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    private void merge(T[] array, int left, int mid, int right) {
        T[] tempArray = (T[]) new Object[array.length];
        System.arraycopy(array, 0, tempArray, 0, array.length);
        for (int i = left, j = mid + 1, k = left; k <= right; k++) {
            if (i > mid) {
                array[k] = tempArray[j++];
            } else if (j > right) {
                array[k] = tempArray[i++];
            } else if (lesser(tempArray[j], tempArray[i])) {
                array[k] = tempArray[j++];
            } else {
                array[k] = tempArray[i++];
            }
        }
    }

    @Override
    public void sort(T[] array) {
        mergeSort0(array, 0, array.length - 1);
    }

}
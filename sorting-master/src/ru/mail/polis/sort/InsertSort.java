package ru.mail.polis.sort;

public class InsertSort<T extends Comparable<T>> extends AbstractSortOnComparisons<T> {

    private int binarySearch(T[] array, int startIndex, int endIndex, T key) {
        int start = startIndex;
        int end = endIndex - 1;
        while (start <= end) {
            int mid = (start + end) >>> 1;
            if (lesser(array[mid], key)) {
                start = mid + 1;
            } else if (greater(array[mid], key)) {
                end = mid - 1;
            } else {
                return mid;
            }
        }
        return start;
    }

    private void shift(T[] array, int insertIndex, int index) {
        T temp = array[index];
        System.arraycopy(array, insertIndex, array, insertIndex + 1, index - insertIndex);
        array[insertIndex] = temp;
    }

    @Override
    public void sort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            int index = binarySearch(array, 0, i, array[i]);
            shift(array, index, i);
        }
    }

}
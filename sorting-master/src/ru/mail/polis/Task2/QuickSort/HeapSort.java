package ru.mail.polis.Task2.QuickSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class HeapSort {

    public interface IPriorityQueue<Key extends Comparable<Key>> extends Iterable<Key> {

        void add(Key key);

        Key peek();

        Key extractMin();

        boolean isEmpty();

        int size();
    }

    public static <Key extends Comparable<Key>> void swap(Key[] array, int firstIndex, int secondIndex) {
        Key temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    private static class ArrayPriorityQueue<Key extends Comparable<Key>> implements IPriorityQueue<Key> {

        private Object[] elementData;
        private Comparator<Key> comparator;
        private int size;
        private int capacity;

        public ArrayPriorityQueue() {
            elementData = new Object[10];
            capacity = elementData.length;
            size = 0;
            comparator = Comparator.naturalOrder();
        }

        public ArrayPriorityQueue(Key[] elementData) {
            this.elementData = elementData;
            capacity = elementData.length;
            size = elementData.length;
            comparator = Comparator.naturalOrder();
            for (int i = size - 1; i > -1; i--) {
                int index = 2 * i + 1;
                if (index < size) {
                    siftDown(i);
                }
            }
        }

        public ArrayPriorityQueue(Comparator<Key> comparator) {
            elementData = new Object[10];
            capacity = elementData.length;
            size = 0;
            this.comparator = comparator;
        }

        public void addAll(Key[] elementData) {
            this.elementData = elementData;
            size = elementData.length;
            for (int i = size - 1; i > -1; i--) {
                int index = 2 * i + 1;
                if (index < size) {
                    siftDown(i);
                }
            }
            size = elementData.length;
        }

        @Override
        public void add(Key key) {
            if (size < capacity) {
                elementData[size] = key;
                siftUp(size);
                size++;
            } else {
                grow();
                add(key);
            }
        }

        @Override
        public Key peek() {
            return (Key) elementData[0];
        }

        @Override
        public Key extractMin() {
            Key min = (Key) elementData[0];
            elementData[0] = elementData[size - 1];
            elementData[size - 1] = null;
            size--;
            siftDown(0);
            shrink();
            return min;
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        public int size() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        private void swap(int first, int second) {
            Key temp = (Key) elementData[first];
            elementData[first] = elementData[second];
            elementData[second] = temp;
        }

        private void siftUp(int index) {
            if (index > 0) {
                int fatherIndex = index % 2 == 0 ? (index - 2) / 2 : (index - 1) / 2;
                if (fatherIndex < size && greater(fatherIndex, index)) {
                    swap(fatherIndex, index);
                    siftUp(fatherIndex);
                }
            }
        }

        public void siftDown(int index) {
            if (index > -1) {
                int child1 = index * 2 + 1;
                int child2 = index * 2 + 2;
                if (child1 < size && greater(index, child1)) {
                    if (child2 < size && greater(index, child2)) {
                        int min = greater(child1, child2) ? child2 : child1;
                        swap(index, min);
                        siftDown(min);
                    } else {
                        swap(index, child1);
                        siftDown(child1);
                    }
                } else if (child2 < size && greater(index, child2)) {
                    swap(index, child2);
                    siftDown(child2);
                }
            }
        }

        private void grow() {
            if (size == capacity) {
                Object[] newArray = new Object[capacity / 2 * 3 + 1];
                System.arraycopy(elementData, 0, newArray, 0, size);
                elementData = newArray;
                capacity = elementData.length;
            }
        }

        private void shrink() {
            if (4 * size <= capacity) {
                Object[] newArray = new Object[capacity / 2];
                System.arraycopy(elementData, 0, newArray, 0, size);
                elementData = newArray;
                capacity = elementData.length;
            }
        }

        private boolean greater(int i, int j) {
            return comparator == null
                    ? ((Key) elementData[i]).compareTo((Key) elementData[j]) < 0
                    : comparator.compare((Key) elementData[i], (Key) elementData[j]) < 0
                    ;
        }

        @Override
        public Iterator<Key> iterator() {
            return new Iterator<Key>() {

                int current = 0;

                @Override
                public boolean hasNext() {
                    return elementData[current] != null;
                }

                @Override
                public Key next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    return (Key) elementData[current++];
                }
            };
        }
    }

    public static <Key extends Comparable<Key>> Key[] heapSort(Key[] array) {
        ArrayPriorityQueue<Key> queue = new ArrayPriorityQueue<>();
        queue.addAll(array);
        for (int i = 0; i < array.length - 1; i++) {
            swap(array, 0, array.length - 1 - i);
            queue.setSize(queue.size() - 1);
            queue.siftDown(0);
        }
        return array;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] a = reader.readLine().split(" ");
            Integer[] array = new Integer[a.length];

            for (int i = 0; i < a.length; i++) {
                array[i] = Integer.valueOf(a[i]);
            }

            heapSort(array);


            for (int i : array) {
                System.out.print(i + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

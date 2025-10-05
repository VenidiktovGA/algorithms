package ru.venidiktov;

public class ArrayOperation {

    /**
     * Переместить элемент в конец и сдвинуть все элементы после него
     *
     * @param array массив
     * @param index индекс начала сдвига
     * @return длина массива без учета сдвинутого элемента в конец
     */
    public static int shiftElementAtIndexToEnd(int[] array, int index) {
        if (index > array.length - 1) {
            return array.length;
        }

        for (int i = index; i < array.length - 1; i++) {
            int previous = array[i];
            array[i] = array[i + 1];
            array[i + 1] = previous;
        }

        return array.length - 1;
    }
}

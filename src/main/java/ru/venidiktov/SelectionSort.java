package ru.venidiktov;

/**
 * Сортировка выбором
 */
public class SelectionSort {

    /**
     * Сортировка выбором.
     * Время выполнения O(N * N).
     * Для работы используется дополнительная память.
     * <p>
     * Алгоритм, по порядку для каждого индекса определяем самый наименьший элемент из массива, делая полный проход по
     * подмассиву начиная со следующего индекса и до конца массива, далее переставляем текущий элемент с самым
     * наименьшим элементом.
     *
     * @param array массив
     * @return отсортированный массив в порядке убывания
     */
    public static int[] selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min_index = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[min_index] < array[j]) {
                    min_index = j;
                }
            }
            int temp = array[i];
            array[i] = array[min_index];
            array[min_index] = temp;
        }
        return array;
    }
}

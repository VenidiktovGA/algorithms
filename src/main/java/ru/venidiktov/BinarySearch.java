package ru.venidiktov;

public class BinarySearch {

    /**
     * Найти элемент в отсортированном массиве используя бинарный поиск, если элемента нет возвращается null
     * <p>
     * Время выполнения от количества элементов зависит как O = log2(N) (Логарифм по основанию 2)
     *
     * @param array входной отсортированный массив
     * @param value искомое значение
     * @return найденное значение
     */
    public static Integer binarySearch(int[] array, int value) {
        int middle = array.length / 2;
        int left = 0;
        int right = array.length - 1;
        System.out.printf("Начинаем: лево = %s, середина = %s, право = %s\n", left, middle, right);

        while (left <= right) {
            if (array[middle] > value) {
                right = middle - 1;
                middle = left + (right - left) / 2;
                System.out.printf("Идем влево: лево = %s, середина = %s, право = %s\n", left, middle, right);
            } else if (array[middle] < value) {
                left = middle + 1;
                middle = left + (right - left) / 2;
                System.out.printf("Идем вправо: лево = %s, середина = %s, право = %s\n", left, middle, right);
            } else {
                return array[middle];
            }
        }
        return null;
    }
}

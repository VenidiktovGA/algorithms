package ru.venidiktov;

import java.util.Arrays;

/**
 * Найти максимальное значение в не отсортированном массиве.
 * <p>
 * Сортировка большого массива занимает больше времени чем простой перебор для поиска максимального значения!
 */
public class FindMax {

    /**
     * Поиск максимального элемента в не отсортированном массиве используя перебор
     * Использование переменной max содержащей результат предыдущей итерации который используется в текущей итерации
     * это часть динамического программирования - такая переменная называется Аккумулятор!
     * <p>
     * Время выполнения зависит от количества элементов в массиве, в самом плохом случае это O(N), то есть зависимость линейная!
     *
     * @param array входной массив
     * @return максимальное число в массиве
     */
    public static int findMaxUsingBruteForce(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    /**
     * Поиска максимального элемента в не отсортированном массиве используя сортировку
     *
     * @param array входной массив
     * @return максимальное число
     */
    public static int findMaxUsingSorting(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        Arrays.sort(array);
        return array[array.length - 1];
    }

    /**
     * Поиск нескольких максимальных чисел в не отсортированном массиве используя перебор,
     * внутри используется массив найденных максимумов, что бы не делать столько проходов сколько максимумов нужно найти
     * как в методе findMaxesUsingNBruteForce
     * <p>
     * Время выполнения O(N)
     *
     * @param array       входной массив
     * @param numberOfMax количество максимумов которое нужно найти
     * @return максимумы
     */
    public static int[] findMaxesUsingBruteForce(int[] array, int numberOfMax) {
        if (array.length < numberOfMax) { //Нельзя возвращать значения по умолчанию так как не ясно часть это ответа или там нет данных
            throw new RuntimeException("Размер массива должен быть больше количества максимумов");
        }
        if (array.length == 0) {
            throw new RuntimeException("Массив не может быть пустым");
        }
        int[] maxes = new int[numberOfMax];
        //Максимумы по умолчанию должны быть минимально возможными числами
        for (int i = 0; i < maxes.length; i++) {
            maxes[i] = Integer.MIN_VALUE;
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] > maxes[maxes.length - 1]) {
                maxes[maxes.length - 1] = array[i];
                for (int j = maxes.length - 1; j > 0; j--) {
                    if (maxes[j] > maxes[j - 1]) {
                        int tempLeft = maxes[j - 1];
                        maxes[j - 1] = maxes[j];
                        maxes[j] = tempLeft;
                    } else {
                        break;
                    }
                }
            }
        }
        return maxes;
    }

    /**
     * Поиск нескольких максимальных чисел в не отсортированном массиве используя N переборов,
     * внутри делать столько проходов по массиву сколько максимумов нужно найти.
     * <p>
     * Время выполнения данного метода = numberOfMax * размер входного массива, или O(M * N)!
     *
     * @param array       входной массив
     * @param numberOfMax количество максимумов которое нужно найти
     * @return максимумы
     */
    public static int[] findMaxesUsingNBruteForce(int[] array, int numberOfMax) {
        if (array.length < numberOfMax) { //Нельзя возвращать значения по умолчанию так как не ясно часть это ответа или там нет данных
            throw new RuntimeException("Размер массива должен быть больше количества максимумов");
        }
        if (array.length == 0) {
            throw new RuntimeException("Массив не может быть пустым");
        }
        int[] maxes = new int[numberOfMax];
        //Максимумы по умолчанию должны быть минимально возможными числами
        for (int i = 0; i < maxes.length; i++) {
            maxes[i] = Integer.MIN_VALUE;
        }

        //Находим первый максимум
        for (int i = 0; i < array.length; i++) {
            if (array[i] > maxes[0]) {
                maxes[0] = array[i];
            }
        }

        for (int i = 1; i < maxes.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[j] > maxes[i] && array[j] < maxes[i - 1]) {
                    maxes[i] = array[j];
                }
            }
        }

        return maxes;
    }
}

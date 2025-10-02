package ru.venidiktov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

@DisplayName("Поиск максимального")
class FindMaxTest {

    @Nested
    @DisplayName("Поиск максимального числа в не отсортированном массиве")
    class FindOneMax {

        @ParameterizedTest(name = "Входной массив = {0}, максимальное число = {1}")
        @MethodSource("argumentsForMax")
        @DisplayName("Используя перебор")
        void findMaxUsingBruteForceWithNotSortedArray(int[] array, int expectedMax) {
            int max = FindMax.findMaxUsingBruteForce(array);

            assertThat(max).isEqualTo(expectedMax);
        }

        @ParameterizedTest(name = "Входной массив = {0}, максимальное число = {1}")
        @MethodSource("argumentsForMax")
        @DisplayName("Используя сортировку")
        void findMaxUsingSortingWithNotSortedArray(int[] array, int expectedMax) {
            int max = FindMax.findMaxUsingSorting(array);

            assertThat(max).isEqualTo(expectedMax);
        }

        public static Stream<Arguments> argumentsForMax() {
            return Stream.of(
                    of(new int[]{10, 6, 22, 78, 35, 45, 4}, 78),
                    of(new int[]{-1, -13, 3, 2}, 3),
                    of(new int[]{}, 0),
                    of(new int[]{-1, -13, -3, -2}, -1)
            );
        }

        @RepeatedTest(5)
        @DisplayName("Сортировка большого массива занимает больше времени чем перебор для поиска максимального значения!")
        void compareBruteForceAndSortingAlgorithms() {
            int[] array = generateRandomArray(5_000_000);

            // Измеряем время работы первого метода
            long startTime1 = System.nanoTime();
            int max1 = FindMax.findMaxUsingBruteForce(array);
            long endTime1 = System.nanoTime();
            long bruteForceTime = (endTime1 - startTime1) / 1_000_000;

            System.out.println("Поиск перебором:");
            System.out.println("Максимальное значение: " + max1);
            System.out.println("Время выполнения: " + bruteForceTime + " мс");

            // Измеряем время работы второго метода
            long startTime2 = System.nanoTime();
            int max2 = FindMax.findMaxUsingSorting(array);
            long endTime2 = System.nanoTime();
            long sortedTime = (endTime2 - startTime2) / 1_000_000;

            System.out.println("Поиск через сортировку:");
            System.out.println("Максимальное значение: " + max2);
            System.out.println("Время выполнения: " + sortedTime + " мс");
            System.out.println("-------------------------------------------------");

            assertThat(max1).isEqualTo(max2);
            assertThat(sortedTime).isGreaterThan(bruteForceTime);
        }
    }

    @Nested
    @DisplayName("Поиск нескольких максимальных чисел в не отсортированном массиве")
    class FindMaxes {
        @ParameterizedTest(name = "Входной массив = {0}, ищем {1} максимальных чисел, ожидаем массив = {2}")
        @MethodSource("argumentsForMaxes")
        @DisplayName("Поиск перебором")
        void findMaxesUsingBruteForce(int[] array, int numberOfMax, int[] expectedMaxes) {
            int[] maxes = FindMax.findMaxesUsingBruteForce(array, numberOfMax);

            assertThat(maxes).isEqualTo(expectedMaxes);
        }

        @Test
        @DisplayName("Сортировка выбором если количество искомых максимумов равно размеру массива")
        void findMaxesUsingBruteForceIsSelectionSorting() {
            int[] array = {10, 6, 22, 78, 35, 45, 4};
            int[] maxes = FindMax.findMaxesUsingBruteForce(array, array.length);

            assertThat(maxes).isEqualTo(new int[]{78, 45, 35, 22, 10, 6, 4});
        }

        @ParameterizedTest(name = "Входной массив = {0}, ищем {1} максимальных чисел, ожидаем массив = {2}")
        @MethodSource("argumentsForMaxes")
        @DisplayName("Поиск перебором N раз")
        void findMaxesUsingNBruteForce(int[] array, int numberOfMax, int[] expectedMaxes) {
            int[] maxes = FindMax.findMaxesUsingNBruteForce(array, numberOfMax);

            assertThat(maxes).isEqualTo(expectedMaxes);
        }

        public static Stream<Arguments> argumentsForMaxes() {
            return Stream.of(
                    of(new int[]{10, 6, 22, 78, 35, 45, 4}, 3, new int[]{78, 45, 35}),
                    of(new int[]{10, 6, 22, 78, 35, 45, 4}, 2, new int[]{78, 45}),
                    of(new int[]{-1, -13, 3, 2}, 1, new int[]{3}),
                    of(new int[]{4, 78}, 2, new int[]{78, 4}),
                    of(new int[]{-1, -13, -3, -2}, 2, new int[]{-1, -2})
            );
        }

        @RepeatedTest(5)
        @DisplayName("N проходов по массиву медленнее чем один проход с упорядоченным аккумулятором максимумов")
        void compareBruteForceAndSortingAlgorithms() {
            int[] array = generateRandomArray(5_000_000);

            // Измеряем время работы первого метода
            long startTime1 = System.nanoTime();
            int[] maxes1 = FindMax.findMaxesUsingBruteForce(array, 10);
            long endTime1 = System.nanoTime();
            long bruteForceTime = (endTime1 - startTime1) / 1_000_000;

            System.out.println("Поиск перебором с упорядоченным аккумулятором:");
            System.out.println("Максимальное значение: " + Arrays.toString(maxes1));
            System.out.println("Время выполнения: " + bruteForceTime + " мс");

            // Измеряем время работы второго метода
            long startTime2 = System.nanoTime();
            int[] maxes2 = FindMax.findMaxesUsingNBruteForce(array, 10);
            long endTime2 = System.nanoTime();
            long sortedTime = (endTime2 - startTime2) / 1_000_000;

            System.out.println("Поиск через перебор N раз:");
            System.out.println("Максимальное значение: " + Arrays.toString(maxes2));
            System.out.println("Время выполнения: " + sortedTime + " мс");
            System.out.println("-------------------------------------------------");

            assertThat(maxes1).isEqualTo(maxes2);
            assertThat(sortedTime).isGreaterThan(bruteForceTime);
        }
    }


    /**
     * Сгенерировать не упорядоченный массив чисел
     *
     * @param size размер массива
     * @return массив
     */
    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }

}
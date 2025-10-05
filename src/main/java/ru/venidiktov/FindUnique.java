package ru.venidiktov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Поиск уникальных элементов
 */
public class FindUnique {

    /**
     * Поиск уникальных элементов в не отсортированном списке, используется метод "грубой силы" (перебора)
     * <p>
     * Время выполнения метода в самом плохом случаем когда все элементы входного списка уникальны
     * будет = количество элементов списка в квадрате (Иза внутреннего цикла) -> O(N * N/2),
     * почему N/2 так как внутренний список увеличивается и проходы по нему от 1 + 2 + 3 + ... + N в математике это = N/2
     * <p>
     * Время выполнения метода в самом хорошем случае когда все элементы списка одинаковые = O(N)
     * <p>
     * Для работы используется дополнительная память (создается список для хранения уникальных элементов)
     *
     * @param array входной массив
     * @return список уникальных элементов
     */
    public static List<Integer> findUniques(List<Integer> array) {
        List<Integer> uniques = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            boolean isExist = false;
            for (int j = 0; j < uniques.size(); j++) {
                if (array.get(i).equals(uniques.get(j))) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                uniques.add(array.get(i));
            }
        }

        return uniques;
    }

    /**
     * Поиск уникальных элементов в отсортированном списке, используется метод "грубой силы" (перебора)
     * <p>
     * Время выполнения метода всегда = O(N), так как мы всегда идем по всем элементам, что бы отсеять лишнее
     * <p>
     * Для работы используется дополнительная память (создается список для хранения уникальных элементов)
     *
     * @param array входной массив
     * @return список уникальных элементов
     */
    public static List<Integer> findUniqueInSortedList(List<Integer> array) {
        List<Integer> uniques = new ArrayList<>();
        if (array.size() == 0) {
            return uniques;
        }
        Integer previousUnique = array.get(0);
        uniques.add(previousUnique);

        for (int i = 1; i < array.size(); i++) {
            if (!array.get(i).equals(previousUnique)) {
                uniques.add(array.get(i));
                previousUnique = array.get(i);
            }
        }
        return uniques;
    }

    /**
     * Найти уникальные элементы в не отсортированном массиве.
     * Входной массив будет изменен, уникальные элементы будут располагаться с 0 индекса до возвращенной длинный - 1.
     * При нахождении дубля для элемента данный элемент сдвигается в конец, а элементы за ним в начала при этом длинна
     * массива с уникальными элементами уменьшается на 1.
     * <p>
     * Алгоритм не использует дополнительной памяти.
     *
     * @param array массив чисел
     * @return длинна подмассива с уникальными элементами
     */
    public static int findUniqueWithShift(int[] array) {
        int length = array.length;
        int i = 0;

        while (i < length) {
            System.out.printf("Текущий индекс = %s, длина массива %s\n", i, length);
            boolean found = false;
            for (int j = i + 1; j < length; j++) {
                if (array[i] == array[j]) {
                    System.out.printf("Встречен дубль для элемента по индексу %s на индексе %s, делаем сдвиг от %s до %s\n", i, j, i, length - 1);
                    System.out.printf("Массив до сдвига: %s\n", Arrays.toString(array));
                    for (int k = i; k < length - 1; k++) {
                        int previous = array[k];
                        array[k] = array[k + 1];
                        array[k + 1] = previous;
                    }
                    System.out.printf("Массив после сдвига: %s\n", Arrays.toString(array));
                    length--;
                    found = true;
                    break;
                }
            }

            if (!found) {
                i++;
            }
        }

        return length;
    }
}

package ru.venidiktov;

import java.util.ArrayList;
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
}

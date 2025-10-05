package ru.venidiktov;

import ru.venidiktov.any.Player;

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
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2; // Можно получить переполнение int еспользуя - (left + right) / 2;
            System.out.printf("лево = %s, середина = %s, право = %s\n", left, middle, right);
            if (array[middle] > value) {
                right = middle - 1;
                System.out.println("Идем влево");
            } else if (array[middle] < value) {
                left = middle + 1;
                System.out.println("Идем вправо");
            } else {
                return array[middle];
            }
        }
        return null;
    }

    /**
     * Поиск места для вставки нового игрока в таблицу рейтинга.
     * Поиск места для вставки использует алгоритм бинарного поиска, новый игрок вставляется в таблицу левее (выше) старых
     * игроков с таким же рейтингом.
     *
     * @param ranking   массив, где игроки отсортированы по рейтингу
     * @param newPlayer новый игрок
     * @return индекс для вставки нового игрока в массив рейтинга
     */
    public static int findPlaceInRanking(Player[] ranking, Player newPlayer) {
        int left = 0;
        int right = ranking.length - 1;

        while (left < right) { // Используется строго < так как мы не исключаем центральный элемент когда идем влево -> right = middle; Иначе бесконечный цикл
            int middle = left + (right - left) / 2;
            System.out.printf("лево = %s, середина = %s, право = %s\n", left, middle, right);
            if (ranking[middle].getRating() < newPlayer.getRating()) {
                left = middle + 1;
                System.out.println("Идем вправо");
            } else { //Рейтинг игрока по индексу middle не меньше рейтинга нового игрока, то мы всегда двигаем right границу
                right = middle; // Элемент на middle может быть не один и его нельзя пропускать, возможно на его место нужно установить нового игрока
                System.out.println("Идем влево, если left < right");
            }
        }

        return left;
    }
}

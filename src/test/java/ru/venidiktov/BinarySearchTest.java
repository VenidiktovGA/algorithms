package ru.venidiktov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.venidiktov.any.Player;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

@DisplayName("Бинарный поиск")
class BinarySearchTest {

    @ParameterizedTest(name = "Входной массив {0}, искомый элемент {1}, найденное значение {2}")
    @MethodSource("arguments")
    void successBinarySearch(int[] array, int value, Integer expected) {
        Integer result = BinarySearch.binarySearch(array, value);

        assertThat(result).isEqualTo(expected);
    }

    public static Stream<Arguments> arguments() {
        return Stream.of(
                of(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 3, 3),
                of(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5, 5),
                of(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 8, 8),
                of(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 2, 2),
                of(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 2, 2),
                of(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 11, null)
        );
    }

    @ParameterizedTest(name = "Рейтинг нового игрока {0}, нужно поставить нового игрока на позицию {1}")
    @MethodSource("newPlayer")
    @DisplayName("Поиск места для вставки нового игрока в таблицу рейтинга")
    void test(int ranking, int expectedIndexForInsertion) {
        Player[] players = {
                new Player(1100, "Craowbar Freeman"),
                new Player(1200, "London Mollarik"),
                new Player(1600, "Raziel of Kain"),
                new Player(1600, "Gwinter of Rivia"),
                new Player(1600, "Slayer of Fate"),
                new Player(3000, "Jon Know"),
                new Player(4000, "Caius Cosades")
        };

        int indexForInsertion = BinarySearch.findPlaceInRanking(players, new Player(ranking, "Snake"));

        assertThat(indexForInsertion).isEqualTo(expectedIndexForInsertion);
    }

    public static Stream<Arguments> newPlayer() {
        return Stream.of(
                of(1600, 2),
                of(900, 0),
                of(3000, 5),
                of(1700, 5),
                of(9000, 6)
        );
    }

}
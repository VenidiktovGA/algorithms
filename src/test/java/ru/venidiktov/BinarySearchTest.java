package ru.venidiktov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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

}
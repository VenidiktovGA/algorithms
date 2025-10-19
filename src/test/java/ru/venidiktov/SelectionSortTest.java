package ru.venidiktov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

@DisplayName("Сортировка выбором")
class SelectionSortTest {

    @ParameterizedTest
    @MethodSource("arguments")
    @DisplayName("Сортировка по возрастанию используя сортировку выбором")
    void selectionSort(int[] array, int[] expected) {
        SelectionSort.selectionSort(array);

        assertThat(array).contains(expected);
    }

    public static Stream<Arguments> arguments() {
        return Stream.of(
                of(new int[]{29, 72, 98, 13, 87, 66, 52, 51, 36}, new int[]{98, 87, 66, 52, 51, 36, 29, 13}),
                of(new int[]{2, 1}, new int[]{1, 2}),
                of(new int[]{2}, new int[]{2}),
                of(new int[]{}, new int[]{})
        );
    }

}
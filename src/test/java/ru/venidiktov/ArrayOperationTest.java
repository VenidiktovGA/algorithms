package ru.venidiktov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

class ArrayOperationTest {

    @ParameterizedTest
    @MethodSource("argumentsForShiftElementToEnd")
    @DisplayName("Переместить элемент в конец и сдвинуть все элементы после него")
    void successShiftElementToEnd(int[] array, int index, int[] expectedArray) {
        ArrayOperation.shiftElementAtIndexToEnd(array, index);

        assertThat(array).isEqualTo(expectedArray);
    }

    public static Stream<Arguments> argumentsForShiftElementToEnd() {
        return Stream.of(
                of(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 2, new int[]{1, 2, 4, 5, 6, 7, 8, 9, 3}),
                of(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 0, new int[]{2, 3, 4, 5, 6, 7, 8, 9, 1}),
                of(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 8, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9})
        );
    }

}
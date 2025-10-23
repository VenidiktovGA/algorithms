package ru.venidiktov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

@DisplayName("Факториал числа")
class FactorialTest {

    @ParameterizedTest
    @MethodSource("arguments")
    @DisplayName("Факториал числа без рекурсии")
    void factorialWithoutRecursive(int number, int expected) {
        int result = Factorial.factorialWithoutRecursive(number);

        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("arguments")
    @DisplayName("Факториал числа используя рекурсию")
    void factorialWithRecursive(int number, int expected) {
        int result = Factorial.factorialWithRecursive(number);

        assertThat(result).isEqualTo(expected);
    }

    public static Stream<Arguments> arguments() {
        return Stream.of(
                of(5, 120),
                of(1, 1),
                of(0, 1),
                of(3, 6)
        );
    }

}
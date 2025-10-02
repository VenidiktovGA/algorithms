package ru.venidiktov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

@DisplayName("Поиск уникальных элементов")
class FindUniqueTest {

    @ParameterizedTest(name = "Входной массив {0}, ожидается {1}")
    @MethodSource("argumentsForUniques")
    @DisplayName("Поиск уникальных элементов в не отсортированном списке методом грубой силы")
    void findUniques(List<Integer> list, List<Integer> expected) {
        List<Integer> uniques = FindUnique.findUniques(list);

        assertThat(uniques).containsExactlyInAnyOrderElementsOf(expected);
    }

    public static Stream<Arguments> argumentsForUniques() {
        return Stream.of(
                of(List.of(22, 33, 44, 55, 22, 3, 55), List.of(22, 33, 44, 55, 3)),
                of(List.of(22, -3, 0, 55, 22, 3, 55), List.of(22, -3, 0, 55, 3))
        );
    }

    @ParameterizedTest(name = "Входной массив {0}, ожидается {1}")
    @MethodSource("sortedList")
    @DisplayName("Поиск уникальных элементов в отсортированном списке")
    void finUniqueInSortedList(List<Integer> list, List<Integer> expected) {
        List<Integer> uniques = FindUnique.findUniqueInSortedList(list);

        assertThat(uniques).containsExactlyInAnyOrderElementsOf(expected);
    }

    public static Stream<Arguments> sortedList() {
        return Stream.of(
                of(List.of(0, 1, 1, 2, 3, 3, 4, 4, 5), List.of(0, 1, 2, 3, 4, 5)),
                of(List.of(0), List.of(0)),
                of(List.of(), List.of())
        );
    }

}
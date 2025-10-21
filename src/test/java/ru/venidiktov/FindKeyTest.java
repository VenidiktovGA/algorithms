package ru.venidiktov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Поиск ключа в коробке с коробками")
class FindKeyTest {

    @Nested
    @DisplayName("Поиск ключа без использования рекурсии")
    class FindKeyUseNoneRecursive {
        @Test
        @DisplayName("Ключ найден успешно")
        void keyFoundSuccess() {
            FindKey.Box box = boxWithKey();

            FindKey.Key foundKey = FindKey.findKeyUseNoneRecursive(box);

            assertThat(foundKey.name).isEqualTo("Золотой ключик!");
        }

        @Test
        @DisplayName("Ключа нет")
        void keyNotExistSuccess() {
            FindKey.Box box = boxWithouthKey();

            FindKey.Key foundKey = FindKey.findKeyUseNoneRecursive(box);

            assertThat(foundKey).isNull();
        }
    }

    @Nested
    @DisplayName("Поиск ключа используя рекурсию")
    class FindKeyUseRecursive {
        @Test
        @DisplayName("Ключ найден успешно")
        void keyFoundSuccess() {
            FindKey.Box box = boxWithKey();

            FindKey.Key foundKey = FindKey.findKeyUseRecursive(box);

            assertThat(foundKey.name).isEqualTo("Золотой ключик!");
        }

        @Test
        @DisplayName("Ключа нет")
        void keyNotExistSuccess() {
            FindKey.Box box = boxWithouthKey();

            FindKey.Key foundKey = FindKey.findKeyUseRecursive(box);

            assertThat(foundKey).isNull();
        }
    }

    private FindKey.Box boxWithKey() {
        var flamethrower = FindKey.Thing.builder().type(FindKey.Type.THING).name("Огнемет").build();
        var box5_4 = FindKey.Box.builder().type(ru.venidiktov.FindKey.Type.BOX)
                .items(List.of(flamethrower))
                .name("Коробка 5 уровень 4").build();

        var key = FindKey.Key.builder().type(FindKey.Type.KEY).name("Золотой ключик!").build();
        var box4_3 = FindKey.Box.builder().type(FindKey.Type.BOX)
                .items(List.of(key, box5_4))
                .name("Коробка 4 уровень 3").build();

        var gun = FindKey.Thing.builder().type(FindKey.Type.THING).name("Ружье").build();
        var box3_2 = FindKey.Box.builder().type(FindKey.Type.BOX)
                .items(List.of(gun))
                .name("Коробка 3 уровень 2").build();

        var shorts = FindKey.Thing.builder().type(FindKey.Type.THING).name("Шорты").build();
        var box1_1 = FindKey.Box.builder().type(FindKey.Type.BOX)
                .items(List.of(shorts, box3_2))
                .name("Коробка 1 уровень 1").build();

        var boots = FindKey.Thing.builder().type(FindKey.Type.THING).name("Ботинки").build();
        var box2_1 = FindKey.Box.builder().type(FindKey.Type.BOX)
                .items(List.of(boots, box4_3))
                .name("Коробка 2 уровень 1").build();

        return FindKey.Box.builder()
                .type(FindKey.Type.BOX)
                .name("Самая большая коробка уровень 0")
                .items(List.of(box1_1, box2_1))
                .build();
    }

    private FindKey.Box boxWithouthKey() {
        var flamethrower = FindKey.Thing.builder().type(FindKey.Type.THING).name("Огнемет").build();
        var box5_4 = FindKey.Box.builder().type(ru.venidiktov.FindKey.Type.BOX)
                .items(List.of(flamethrower))
                .name("Коробка 5 уровень 4").build();

        var box4_3 = FindKey.Box.builder().type(FindKey.Type.BOX)
                .items(List.of(box5_4))
                .name("Коробка 4 уровень 3").build();

        var gun = FindKey.Thing.builder().type(FindKey.Type.THING).name("Ружье").build();
        var box3_2 = FindKey.Box.builder().type(FindKey.Type.BOX)
                .items(List.of(gun))
                .name("Коробка 3 уровень 2").build();

        var shorts = FindKey.Thing.builder().type(FindKey.Type.THING).name("Шорты").build();
        var box1_1 = FindKey.Box.builder().type(FindKey.Type.BOX)
                .items(List.of(shorts, box3_2))
                .name("Коробка 1 уровень 1").build();

        var boots = FindKey.Thing.builder().type(FindKey.Type.THING).name("Ботинки").build();
        var box2_1 = FindKey.Box.builder().type(FindKey.Type.BOX)
                .items(List.of(boots, box4_3))
                .name("Коробка 2 уровень 1").build();

        return FindKey.Box.builder()
                .type(FindKey.Type.BOX)
                .name("Самая большая коробка уровень 0")
                .items(List.of(box1_1, box2_1))
                .build();
    }


}
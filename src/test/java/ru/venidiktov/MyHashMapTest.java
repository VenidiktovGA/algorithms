package ru.venidiktov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MyHashMapTest {

    @Test
    @DisplayName("Элемент находится по ключу, коллизий нет")
    void getEntityByKey() {
        var myHashMap = new MyHashMap();
        myHashMap.add("1", "1");
        myHashMap.add("11", "11");

        String value1 = myHashMap.get("1");
        String value11 = myHashMap.get("11");
        assertAll(
                () -> assertThat(myHashMap.numberOfEntries).isEqualTo(2),
                () -> assertThat(myHashMap.size).isEqualTo(8),
                () -> assertThat(myHashMap.entries[1].key).isEqualTo("1"),
                () -> assertThat(myHashMap.entries[2].key).isEqualTo("11"),
                () -> assertThat(value1).isEqualTo("1"),
                () -> assertThat(value11).isEqualTo("11")
        );
    }

    @Test
    @DisplayName("Элемент находится по ключу при наличии коллизий")
    void getEntityByKeyWithCollisions() {
        var myHashMap = new MyHashMap();
        myHashMap.add("1", "1");
        myHashMap.add("2", "2"); // Коллизия, hash выдаст индекс который уже занят
        myHashMap.add("11", "11");

        String value1 = myHashMap.get("1");
        String value2 = myHashMap.get("2");
        String value11 = myHashMap.get("11");
        assertAll(
                () -> assertThat(myHashMap.numberOfEntries).isEqualTo(3),
                () -> assertThat(myHashMap.size).isEqualTo(8),
                () -> assertThat(myHashMap.entries[1].key).isEqualTo("1"),
                () -> assertThat(myHashMap.entries[2].key).isEqualTo("2"),
                () -> assertThat(myHashMap.entries[3].key).isEqualTo("11"),
                () -> assertThat(value1).isEqualTo("1"),
                () -> assertThat(value2).isEqualTo("2"),
                () -> assertThat(value11).isEqualTo("11")
        );
    }

    @Test
    @DisplayName("Граничное значение до увеличения размера внутреннего массива")
    void limitNumberOfEntriesBeforeIncreaseInnerArray() {
        var myHashMap = new MyHashMap();
        myHashMap.add("1", "1");
        myHashMap.add("2", "2");
        myHashMap.add("333", "333");
        myHashMap.add("4444", "4444");
        myHashMap.add("55555", "55555");
        myHashMap.add("666666", "666666");
        myHashMap.add("7777777", "7777777");

        String value1 = myHashMap.get("1");
        String value77777777 = myHashMap.get("7777777");
        assertAll(
                () -> assertThat(myHashMap.numberOfEntries).isEqualTo(7),
                () -> assertThat(myHashMap.size).isEqualTo(8),
                () -> assertThat(myHashMap.entries[1].key).isEqualTo("1"),
                () -> assertThat(myHashMap.entries[7].key).isEqualTo("7777777"),
                () -> assertThat(value1).isEqualTo("1"),
                () -> assertThat(value77777777).isEqualTo("7777777")
        );
    }

    @Test
    @DisplayName("При увеличении внутреннего массива элементы перераспределяются с учетом длинны нового массива")
    void increaseInnerArray() {
        var myHashMap = new MyHashMap();
        myHashMap.add("999999999", "999999999");
        myHashMap.add("1", "1");
        myHashMap.add("2", "2");
        myHashMap.add("333", "333");
        myHashMap.add("4444", "4444");
        myHashMap.add("55555", "55555");
        myHashMap.add("666666", "666666");
        myHashMap.add("7777777", "7777777");
        myHashMap.add("88888888", "88888888");

        String value1 = myHashMap.get("1");
        String value2 = myHashMap.get("2");
        assertAll(
                () -> assertThat(myHashMap.numberOfEntries).isEqualTo(9),
                () -> assertThat(myHashMap.size).isEqualTo(16),
                () -> assertThat(myHashMap.entries[1].key).isEqualTo("1"),
                () -> assertThat(value1).isEqualTo("1"),
                () -> assertThat(value2).isEqualTo("2")
        );
    }

    @Test
    @DisplayName("Уменьшение внутреннего массива при количестве данных 1/4 от размера массива")
    void decreaseInnerArray() {
        var myHashMap = new MyHashMap();
        myHashMap.add("1", "1");
        myHashMap.add("22", "22");
        myHashMap.add("333", "333");
        myHashMap.add("4444", "4444");
        myHashMap.add("55555", "55555");
        myHashMap.add("666666", "666666");
        myHashMap.add("7777777", "7777777");
        myHashMap.add("88888888", "88888888");
        myHashMap.add("999999999", "999999999");

        myHashMap.remove("1");
        myHashMap.remove("22");
        myHashMap.remove("333");
        myHashMap.remove("4444");
        myHashMap.remove("999999999");

        assertAll(
                () -> assertThat(myHashMap.numberOfEntries).isEqualTo(4),
                () -> assertThat(myHashMap.size).isEqualTo(8),
                () -> assertThat(myHashMap.entries[5].key).isEqualTo("55555"),
                () -> assertThat(myHashMap.entries[6].key).isEqualTo("666666"),
                () -> assertThat(myHashMap.entries[7].key).isEqualTo("7777777"),
                () -> assertThat(myHashMap.entries[0].key).isEqualTo("88888888")
        );
    }

}
package ru.venidiktov;

import lombok.AllArgsConstructor;

/**
 * Наивная реализация HashMap.
 * <p>
 * Для решения коллизий существу подход открытой адресация с пробированием,
 * при вставке в занятую ячейку, делается "проба" следующей за ней ячейки на то занята ли она,
 * если нет записываем значение в нее если занята делаем "пробу" следующей ячейки, если доходим до конца, то увеличиваем
 * размер массива
 */
public class MyHashMap {

    KeyValuePair[] entries = new KeyValuePair[8];
    int size = 8;
    int numberOfEntries = 0;

    public void add(String key, String value) {
        int index = findGoogIndex(key, entries, size);
        entries[index] = new KeyValuePair(key, value);
        numberOfEntries++;
        if (numberOfEntries == size) {
            resize(size * 2);
        }
    }

    public int remove(String key) {
        int index = findGoogIndex(key, entries, size);
        if (index != -1) {
            entries[index] = null;
            numberOfEntries--;
        }
        if (numberOfEntries == size / 4) {
            resize(size / 2);
        }
        return index;
    }

    public String get(String key) {
        int index = findGoogIndex(key, entries, size);
        if (index == -1) { // Элемент не найден
            return null;
        }
        KeyValuePair entry = entries[index];
        if (entry == null) {
            return null;
        }
        return entry.value;
    }

    /**
     * Хэш для ключа.
     * Возвращает в качестве хэш значения длину ключа
     *
     * @param key  ключ
     * @param size длинна массива
     * @return хэш для ключа
     */
    int hashFunction(String key, int size) {
        // Наивная реализация для тестирования
        return key.length() % size;
    }

    /**
     * Изменить размер внутреннего массива.
     * При изменении размера необходимо перенести элементы из старого массива в новый,
     * при переносе элементов необходимо искать их все так как их может быть например два в начале и в конце.
     *
     * @param newSize новый размер внутреннего массива
     */
    private void resize(int newSize) {
        System.out.printf("Меняем размер хранилища с %s на %s", size, newSize);
        KeyValuePair[] newEntries = new KeyValuePair[newSize];
        for (int j = 0, i = 0; j < numberOfEntries; i++) {
            KeyValuePair entry = entries[i];
            if (entry != null) {
                int index = findGoogIndex(entry.key, newEntries, newSize);
                newEntries[index] = entries[i];
                j++;
            }
        }
        entries = newEntries;
        size = newSize;
    }

    /**
     * Найти подходящий индекс для элемента с учетом коллизий
     *
     * @param key     ключ элемента
     * @param entries массив где необходимо найти индекс мета для элемента
     * @param size    целевой размер массива для хранения данных
     * @return индекс элемента с учетом коллизий решенных через открытую адресацию
     */
    private int findGoogIndex(String key, KeyValuePair[] entries, int size) {
        int hash = hashFunction(key, size);
        int index = hash % size;

        for (int i = 0; i < size; i++) {
            int probingIndex = (index + i) % size; // Делим на размер массива чтобы при достижении конца пойти сначала
            KeyValuePair entry = entries[probingIndex];
            if (entry == null || entry.key.equals(key)) { // Подходящее место для элемента
                return probingIndex;
            }
        }
        return -1;
    }

    @AllArgsConstructor
    static class KeyValuePair {
        public String key;
        public String value;
    }
}

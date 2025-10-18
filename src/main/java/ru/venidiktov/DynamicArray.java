package ru.venidiktov;

/**
 * Наивная реализация динамического массива
 */
public class DynamicArray {

    int[] values = new int[8];
    int size = 8;
    int currentIndex = 0;

    /**
     * Добавить элемент в конец массива.
     * Если занимается последнее свободное место в массиве, создается массив в двое больше размера и элементы копируются
     * в него
     *
     * @param value значение
     */
    public void add(int value) {
        values[currentIndex] = value;
        currentIndex++;
        if (currentIndex == size) {
            resize(size * 2);
        }
    }

    /**
     * Удалить последний элемент массива.
     * Если после удаления элемента размер данных равен 1/4 размера массива, создается новый массив в двое меньшего размера
     * и элементы копируются в него
     */
    public void remove() {
        values[currentIndex] = 0;
        currentIndex--;
        if (currentIndex == size / 4) {
            resize(size / 2);
        }
    }

    private void resize(int newSize) {
        System.out.printf("Меняем размер хранилища с %s на %s", size, newSize);
        int[] newValues = new int[newSize];
        for (int i = 0; i < currentIndex; i++) {
            newValues[i] = values[i];
        }
        values = newValues;
        size = newSize;
    }
}

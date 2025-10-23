package ru.venidiktov;

public class Factorial {

    /**
     * Факториал числа, без использования рекурсии
     *
     * @param number число
     * @return факториал числа
     */
    public static int factorialWithoutRecursive(int number) {
        int result = 1;

        for (int i = 1; i <= number; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Факториал числа, с использованием рекурсии
     *
     * @param number число
     * @return факториал числа
     */
    public static int factorialWithRecursive(int number) {
        if (number <= 1) {
            return 1;
        }
        return number * factorialWithRecursive(number - 1);
    }
}

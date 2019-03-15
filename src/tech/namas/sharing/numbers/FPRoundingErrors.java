package tech.namas.sharing.numbers;

import java.util.Scanner;
import java.util.function.Supplier;

public class FPRoundingErrors {

    private static final float HALF    = 1 / 2f;
    private static final float THIRD   = 1 / 3f;
    private static final float QUARTER = 1 / 4f;
    private static final float FIFTH   = 1 / 5f;
    private static final float SIXTH   = 1 / 6f;
    private static final float SEVENTH = 1 / 7f;

    public static void main(String[] args) {
        System.out.println("1/2 = " + HALF);
        System.out.println("1/3 = " + THIRD);
        System.out.println("1/4 = " + QUARTER);
        System.out.println("1/5 = " + FIFTH);
        System.out.println("1/6 = " + SIXTH);
        System.out.println("1/7 = " + SEVENTH);
        System.out.println();

        final int factor = getUserInput();

        System.out.println();

        printSum("1/2", HALF,    factor, () -> factor / 2);
        printSum("1/3", THIRD,   factor, () -> factor / 3);
        printSum("1/4", QUARTER, factor, () -> factor / 4);
        printSum("1/5", FIFTH,   factor, () -> factor / 5);
        printSum("1/6", SIXTH,   factor, () -> factor / 6);
        printSum("1/7", SEVENTH, factor, () -> factor / 7);
    }

    private static void printSum(String fraction, Float value, Integer factor, Supplier<Integer> expected) {
        float sum = 0f;
        for (int i = 0; i < factor; i++) {
            sum = sum + value;
        }
        printResult(fraction, factor, sum, expected.get());
    }

    private static void printResult(String fraction, Integer factor, Float sum, Integer expected) {
        String result = String.format(
            "%s summed %s times = %10s. Should be: %s.",
            fraction, factor, sum, expected
        );

        System.out.println(result);
    }

    private static int getUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input how many times we should sum those fractions: ");
        return scanner.nextInt();
    }
}

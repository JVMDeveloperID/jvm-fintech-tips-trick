package tech.namas.sharing.numbers;

public class IntOverflowProblem {

    public static void main(String[] args) {
        int oldAcc = 1000000;
        int newVal = 500000;
        int scale  = 400000;
        String output = String.format(
            "%d + (%d * %d) = %d",
            oldAcc, newVal, scale, accumulateAndMultiply(oldAcc, newVal, scale)
        );

        System.out.println(output);

        System.out.println(safeAccAndMultiply(oldAcc, newVal, scale));

        int mystery = -2147483648;
        int positiveMystery = -mystery;
        System.out.println(mystery);
        System.out.println(positiveMystery);

        int notAbsolute = -2147483648;
        int totallyAbsolute = Math.abs(notAbsolute);
        System.out.println(notAbsolute);
        System.out.println(totallyAbsolute);

        int someValue = 2147483640;
        int moreValue = 10000;
        int total = someValue + moreValue;
        System.out.println(String.format("%s + %s = %s", someValue, moreValue, total));
        System.out.println(String.format("%s + %s = %s", someValue, moreValue, safeAddCast(someValue, moreValue)));

    }

    private static int accumulateAndMultiply(int oldAcc, int newVal, int scale) {
        return oldAcc + (newVal * scale);
    }

    private static int safeAccAndMultiply(int oldAcc, int newVal, int scale) {
        return Math.addExact(oldAcc, Math.multiplyExact(newVal, scale));
    }

    private static int safeAccAndMultiply2(int oldAcc, int newVal, int scale) {
        return safeAdd(oldAcc, safeMultiply(newVal, scale));
    }

    private static int safeAdd(int left, int right) {
        if (right > 0 ? left > Integer.MAX_VALUE - right
                      : left < Integer.MAX_VALUE - right) {
            throw new ArithmeticException("Integer Overflow!");
        }

        return left + right;
    }

    private static long safeAddCast(int left, int right) {
        return (long) left + (long) right;
    }

    private static int safeMultiply(int left, int right) {
        if (right > 0  ? positiveBoundCheck(left, right) :
           (right < -1 ? negativeBoundCheck(left, right) :
            safeNegationCheck(left, right))) {
            throw new ArithmeticException("Integer overflow!");
        }

        return left * right;
    }

    private static boolean positiveBoundCheck(int left, int right) {
        return left > Integer.MAX_VALUE / right ||
               left < Integer.MIN_VALUE / right;
    }

    private static boolean negativeBoundCheck(int left, int right) {
        return left > Integer.MIN_VALUE / right ||
               left < Integer.MAX_VALUE / right;
    }

    private static boolean safeNegationCheck(int left, int right) {
        return right == -1 && left == Integer.MIN_VALUE;
    }
}

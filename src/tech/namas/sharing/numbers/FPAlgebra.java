package tech.namas.sharing.numbers;

public class FPAlgebra {

    public static void main(String[] args) {
        final float a = 1.0f;
        final float b = 3.0E-8f;
        final float c = 4.0E-8f;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);

        final float s1 = (a + b) + c;
        final float s2 = a + (b + c);

        System.out.println();
        System.out.println("s1 = (a + b) + c = " + s1);
        System.out.println("s2 = a + (b + c) = " + s2);
        System.out.println();

        final float e = 0.5f;
        final float f = 1.0E-45f;
        final float g = 3.0E38f;

        System.out.println("e = " + e);
        System.out.println("f = " + f);
        System.out.println("g = " + g);

        final float m1 = (e * f) * g;
        final float m2 = e * (f * g);

        System.out.println();
        System.out.println("m1 = (e * f) * g = " + m1);
        System.out.println("m2 = e * (f * g) = " + m2);
    }
}

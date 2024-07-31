package org.example.divider;

/**
 * Provides a {@link #divide(int, int)} method for integers
 */
public class Divider {
    /**
     * Divides two integers
     *
     * @param a the numerator
     * @param b the denominator. If the denominator is 0, the methods throws an <code>IllegalArgumentException</code>
     * @return the divided number
     */
    public float divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException();
        }

        return (float) a / b;
    }
}
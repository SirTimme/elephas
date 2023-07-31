package org.example;

public class Divider {
    public float divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException();
        }

        return (float) a / b;
    }
}
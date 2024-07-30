package org.example.testing;

public class IntegrationTestClass {
    private final Divider divider;

    public IntegrationTestClass(final Divider divider) {
        this.divider = divider;
    }

    public float divide(int a, int b) {
        return divider.divide(a, b);
    }
}
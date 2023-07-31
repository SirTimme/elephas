package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiplierTest {
    private Multiplier multiplier = new Multiplier();

    @Test
    void testMultiply() {
        assertEquals(56, multiplier.multiply(8, 7));
    }
}
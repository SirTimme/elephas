package dev.sirtimme.multiplier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiplierTest {
    private final Multiplier multiplier = new Multiplier();

    @Test
    void testMultiply() {
        assertEquals(56, multiplier.multiply(8, 7));
    }
}
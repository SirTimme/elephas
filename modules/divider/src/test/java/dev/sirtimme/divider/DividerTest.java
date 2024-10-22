package dev.sirtimme.divider;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DividerTest {
    private final Divider divider = new Divider();

    @Test
    void testDivide() {
        assertEquals(33, divider.divide(99, 3));
    }

    @Test
    void testWhenDiscNull() {
        assertThrows(IllegalArgumentException.class, () -> divider.divide(45, 0));
    }
}
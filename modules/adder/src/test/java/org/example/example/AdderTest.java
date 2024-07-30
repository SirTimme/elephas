package org.example.example;

import org.example.testing.Adder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdderTest {
    private Adder adder = new Adder();

    @Test
    void testAdd() {
        assertEquals(15, adder.add(10, 5));
    }
}
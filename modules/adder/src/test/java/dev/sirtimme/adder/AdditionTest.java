package dev.sirtimme.adder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdditionTest {
    private Adder adder;

    @BeforeEach
    public void clearAdder() {
        this.adder = new Adder();
    }

    @Test
    void simpleAdditionTestCase() {
        assertEquals(15, adder.add(10, 5));
    }

    @Test
    void complexAdditionTestCase() {
        final var resultOne = adder.add(10, 5);
        final var resultTwo = adder.add(68, 1);

        assertEquals(84, resultOne + resultTwo);
    }
}
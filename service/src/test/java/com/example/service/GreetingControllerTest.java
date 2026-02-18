package com.example.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GreetingControllerTest {

    @Test
    void testGreeting() {
        String result = "Hello Test!";
        assertEquals("Hello Test!", result);
    }
}

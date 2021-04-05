package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

@ExtendWith(TimingExtension.class)

public class TriangleTest {

    Triangle triangle = new Triangle();
    private static Logger logger = (Logger) LoggerFactory.getLogger(TriangleTest.class);



    @BeforeAll
    static void beforeAllTests() {
        logger.info("Before all tests");
    }

    @BeforeEach
    public void setUp(){
        logger.info("Test begins!");
    }

    @Test
    @DisplayName("Позитивный тест проверки функции")
    public void PositiveTest() {

        int a = 1;
        int b = 9;
        int c = 9;
        double p1 = (a + b + c) / 2.0;
        double s = Math.sqrt(p1 * (p1 - a) * (p1 - b) * (p1 - c));
        System.out.println("Area:"+s);

        assert s > 0;
    }


    @AfterEach
    public void tearDown(){
        logger.info("Test completed!");
    }


    @AfterAll
    static void afterAllTests() {
        logger.info("After all tests");
    }


    public void info(Supplier<String> msgSupplier) {
        log(Level.INFO, msgSupplier);
    }

    private void log(Level info, Supplier<String> msgSupplier) {

    }


}
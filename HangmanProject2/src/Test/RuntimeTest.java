import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RuntimeTest {

    @BeforeEach
    void setUp() {
        Runtime runtime = new Runtime();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void guessChecker() {

        Runtime.guessChecker('f', "falsehood");
    }

    @Test
    void convertStringToCharList() {

        assert Runtime.convertStringToCharList("False").contains('F');
    }

    @Test
    void displayMan() {
    }

    @Test
    void pickWord() {
    }

    @Test
    void displayImage() {

        displayImage();
    }
}
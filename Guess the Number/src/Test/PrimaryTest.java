import static org.junit.jupiter.api.Assertions.*;

class PrimaryTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        Primary primary = new Primary();
    }

    @org.junit.jupiter.api.Test
    void intro() {

        String name = "John";
        int guesses = 3;

        assertEquals(Primary.intro(1,name, 1), "Well, " + name + ", I am thinking of a number between 1 and 20.\nTake a guess.");

        assertEquals(Primary.intro(2,name, guesses), "Good job, " + name + "! You guessed my number in " + guesses + " guesses!\n");
    }

    @org.junit.jupiter.api.Test
    void gameText() {

        String name = "John";
        int guesses = 3;

        assertEquals(Primary.gameText(2, name, guesses), "Good job, " + name + "! You guessed my number in " + guesses + " guesses!\n");

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }
}
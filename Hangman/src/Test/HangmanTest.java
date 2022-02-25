import static org.junit.jupiter.api.Assertions.*;

class HangmanTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        Hangman hangman = new Hangman();
    }

    @org.junit.jupiter.api.Test
    void guessChecker() {
        Hangman.guessChecker('a',3,"ABC");
    }

    @org.junit.jupiter.api.Test
    void convertStringToCharList() {
       if(!Hangman.convertStringToCharList("ABC").equals(new char[]{'A', 'B', 'C'}))
       {
           assert true;
       } else{
           assert false;
       }

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

}

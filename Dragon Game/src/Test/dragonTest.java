import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class dragonTest {

    @BeforeEach
    void setUp() {
        dragon dragon = new dragon();
    }

    @Test
    void narrative() {
        assertSame(dragon.narrative(1),  "You approach the cave...\n It is dark and spooky... \n A large dragon jumps out in front of you! He opens his jaws and... \n Gobbles you down in one bite!\n"
);
        dragon.narrative(1);

        assertSame(dragon.narrative(2), "You approach the cave... \n A gentle light emanates from within... \n A glimmering golden dragon emerges... \n They provide you a gift of treasure!\n"
);
        dragon.narrative(2);

        assertSame(dragon.narrative(3), "I didn't understand that. Please enter (1) or (2).");
                dragon.narrative(3);
    }

    @Test
    void intro() {
        assertSame(dragon.intro(), "You are in a land full of dragons. \n In front of you, you see two caves. \n In one cave, the dragon is friendly and will share his treasure with you. \n The other dragon is greedy and hungry and will eat you on sight \n Which cave will you go into? (1 or 2)\n");
    }

    @AfterEach
    void tearDown() {
    }
}
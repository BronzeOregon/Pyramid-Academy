import java.util.Objects;
import java.util.Scanner;

import java.util.Random;

public class main {

    public static void main(String[] args)

    {
        int difficulty = 20;
        int guessthis = randInt(1, difficulty);

        System.out.println("Hello! What is your name?");

        Scanner sc = new Scanner(System.in);
        Scanner ac = new Scanner(System.in);
        String name = sc.nextLine();

            System.out.println("Well, " + name + ", I am thinking of a number between 1 and 20.");
            System.out.println("Take a guess.");
        int guesses = 1;

            while (guesses < 7) {
                int guess = sc.nextInt();
                if (guess == guessthis) {
                    System.out.println("Good job, " + name + "! You guessed my number in " + guesses + " guesses!");
                    System.out.println("Would you like to play again? (y or n)");
                    String answer = ac.nextLine();
                    String exit = "n";
                    String again = "y";

                    if (Objects.equals(answer, exit))
                    {
                        return;
                    }
                    if (Objects.equals(answer, again))
                    {
                        guesses =1;
                        difficulty = difficulty + 10;
                        guessthis = randInt(1, difficulty);
                        System.out.println("Alright, this time it'll be a number between 1 and " + difficulty + ". Ready?");
                        System.out.println("Take a guess.");
                    }

                } else if (guess > guessthis) {
                    System.out.println("Your guess is too high.");
                    System.out.println("Take a guess.");
                } else {
                    System.out.println("Your guess is too low.");
                    System.out.println("Take a guess.");
                }
                guesses++;


            }

            System.out.println("Sorry, too many guesses!");
            System.out.println("GAME OVER");
            System.out.println();

    }

    public static int randInt(int min, int max) {

        Random rand = new Random(System.currentTimeMillis());


        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }


}

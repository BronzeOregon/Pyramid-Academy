import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Primary{

    static boolean correct = false;
    static int difficulty = 20;

    public static void main(String[] args)
    {

        int guessThis = randInt(1, difficulty);
        int guess = 0;
        int guesses = 1;

        System.out.println(intro(0, "", 0));
        String name = input();
        System.out.println(intro(1, name, 0));
        while(!correct)
        {
            takeGuess(guessThis, guess, guesses, name);
        }

    }

    public static String intro(int x, String name, int guesses)
    {
        switch(x)
        {
            case 0: return "Hello! What is your name?";
            case 1: return "Well, " + name + ", I am thinking of a number between 1 and 20.\nTake a guess.";
            case 2: return "Good job, " + name + "! You guessed my number in " + guesses + " guesses!\n";
            default: return "Oops!";
        }
    }

    public static String gameText(int x, String name, int guesses)
    {

        switch(x)
        {
            case 0: return "Your guess is too high.\nTake a guess.\n";

            case 1: return "Your guess is too low.\nTake a guess.\n";

            case 2: return intro(2, name, guesses);

            case 3: return "Would you like to play again? (y or n)\n";

            case 4: return "Please enter a number.";

            case 5: return "Alright, this time it'll be a number between 1 and " + difficulty + ". Ready?\nTake a guess.\n";

            default: return "Hahahahah!";
        }

    }

    public static void takeGuess(int target, int guess, int guesses, String name)
    {
        String answer = new String();

        while(target!=guess)

        {
            try {
                guess = Integer.parseInt(input());
            } catch (NumberFormatException e) {
                System.out.print(gameText(4,"",guesses));
            }
            if (guess>target)
            {
                System.out.print(gameText(0,"",guesses));
                guesses++;
            }
            if (guess<target)
            {
                System.out.print(gameText(1,"",guesses));
                guesses++;
            }
        }

        if (target == guess)
        {
            System.out.print(gameText(2, name, guesses));
            try
            {
                System.out.print(gameText(3, name, guesses));
                answer = input();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(Objects.equals(answer, "y"))
            {
                correct = false;
                difficulty = difficulty + 10;
                System.out.print(gameText(5, name, guesses));

            }
            else
            {
                correct = true;
            }


        }


    }

    public static String input()
    {

        Scanner in = new Scanner(System.in);
        return in.nextLine();

    }

    public static int randInt(int min, int max) {

        Random rand = new Random(System.currentTimeMillis());


        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }



        }

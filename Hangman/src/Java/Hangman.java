import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Hangman {

    static boolean gameEnd = false;

    static List<Character> playerGuesses = new ArrayList<>();

    static int correctGuesses =0;

    static int wrongGuesses=0;

    public static void main(String[] Args) throws FileNotFoundException {

        //Looks for the "words_alpha.txt" file in the local directory to pull words from
        Scanner fileScanner = new Scanner(new File("./words_alpha.txt"));


        List<String> words = new ArrayList<>();
        while (fileScanner.hasNext())
        {
            words.add(fileScanner.nextLine());
        }

        Random rand = new Random();

        String word = words.get(rand.nextInt(words.size()-1));

        do {
            do {
                displayMan(wrongGuesses);


                System.out.println();
                if (playerGuesses.isEmpty()) {
                    for (int i = 0; i < word.length(); i++)
                        System.out.print("-");
                }

                if (wrongGuesses == 6)
                    break;

                System.out.println("\nTake a guess!");

                try {
                    guessChecker(getInputChar(), word.length(), word);
                } catch (Exception e) {
                    System.out.println("Please enter a single character");
                }

                if (playerGuesses.containsAll(convertStringToCharList(word))) {
                    System.out.println("You Win!");
                    break;
                }
                System.out.println("Would you like to take a guess at the word? Enter it now!");

                if (getInput().equals(word)) {
                    System.out.println("You win!");
                    break;
                } else {
                    System.out.println("Nope, please keep guessing!");
                }
            }while (!gameEnd);

            System.out.println("If you would like to quit, please enter 'n'. Otherwise, press any key to play again!");

            if(Objects.equals(getInput(), "n"))
            {
                break;
            } else{
                playerGuesses.clear();
                wrongGuesses=0;
                word = words.get(rand.nextInt(words.size()-1));
            }

        } while (true);

    }

    public static boolean guessChecker(Character x, int length, String word)
    {

        try
        {playerGuesses.add(x);

        if(!convertStringToCharList(word).contains(x))
        {
            wrongGuesses++;
            System.out.println("That letter is not in the word!");
        }


        for(int i=0; i<length; i++)
        {
            if(playerGuesses.contains(word.charAt(i)))
            {
                System.out.print(word.charAt(i));
            } else {
                System.out.print("-");
            }



        }

        System.out.println();

        return true;} catch (Exception e)
        {
            return false;
        }
    }

    public static char getInputChar() {
        Scanner input = new Scanner(System.in);

        String guessInput;

        guessInput = input.nextLine();

        return guessInput.charAt(0);
    }

    public static String getInput() {
        Scanner input = new Scanner(System.in);

        String guessInput = "null";
        try {
            guessInput = input.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return guessInput;
    }

    public static List<Character>
    convertStringToCharList(String str) {
        return new AbstractList<Character>() {

            @Override
            public Character get(int index) {
                return str.charAt(index);
            }

            @Override
            public int size() {
                return str.length();
            }
        };
    }

    public static void displayMan(int wrongs)
    {
        for (int i = wrongs; i<=wrongs; i++)
        {       System.out.println(" _______");
                System.out.println(" |     |");
            if(wrongs>=1)
                System.out.println(" o     |");
            if(wrongs>=2)
                System.out.print("/");
            if(wrongs>=3)
                System.out.print("|");
            if(wrongs>=4)
                System.out.println("\\");
            if(wrongs>=5)
                System.out.print("/");
            if(wrongs>=6)
            {
                System.out.println(" \\");
                System.out.println("You lose!");
            }

        }
    }
}

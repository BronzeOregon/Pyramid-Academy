import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Runtime {



    static ArrayList<Character> playerGuesses = new ArrayList<>();
    static int wrongGuesses = 0;
    static String playerName;
    static int playerScore = 0;
    static File hiScore;
    static Scanner hiSc;
    static String hiName;
    static String highScore;
    static boolean loop = true;

    public static void main(String[] args) throws IOException {

        hiScore = new File("./highScore.txt");
        hiSc = new Scanner("./highScore.txt");

        try {
            hiName = Files.readAllLines(Paths.get("./highScore.txt")).get(0);
            highScore = Files.readAllLines(Paths.get("./highScore.txt")).get(1);
        } catch (Exception e)
        {
            System.out.println("There is no current high score holder!");;
        }

        System.out.println("Current high score: "+highScore+". Held by: "+hiName+".");

        String word = pickWord();

        Stream<Character> wordStream = word.chars()
                .mapToObj(c->(char) c);

        System.out.println("What is your name?");

        playerName = getInput();

        do{
            displayImage();

            System.out.println();
            if(playerGuesses.isEmpty())
                //print dashes to represent number of letters in word
            {
                System.out.print(wordStream.map(i-> "-").collect(Collectors.toList()));
                System.out.println();
            }

            System.out.println("Please enter a letter.");

            try {
                guessChecker(getInputChar(), word);
            } catch (Exception e) {
                System.out.println("Please enter a single character");
            }

            if(playerGuesses.containsAll(convertStringToCharList(word)))
            {
                System.out.println("You win!");
                System.out.println("You scored: "+playerScore+" points!");
                System.out.println("Would you like to play again to earn more points?");
                String response = getInput();
                if(response.equals("yes"))
                {
                    word=pickWord();
                    wordStream = word.chars()
                            .mapToObj(c->(char) c);
                    playerGuesses.clear();
                    wrongGuesses=0;
                    System.out.println("Alright, let's play again! I've picked a new word!");
                } if(response.equals("no"))
                {
                    scoreChecker();
                    loop = false;
                } else {
                System.out.println("I'm sorry, I didn't get that!");

            }

            } else {
                System.out.println("Please try to guess the word.");
                String guess = getInput();
                if(word.equals(guess))
                {
                    playerScore+=10;
                    System.out.println("That's correct! You win!");
                    System.out.println("You scored: "+playerScore+" points!");
                    System.out.println("Would you like to play again to earn more points?");
                    String response = getInput();
                    if(response.equals("yes"))
                    {
                        word=pickWord();
                        wordStream = word.chars()
                                .mapToObj(c->(char) c);
                        playerGuesses.clear();
                        wrongGuesses=0;
                        System.out.println("Alright, let's play again! I've picked a new word!");
                    } if(response.equals("no"))
                {
                    scoreChecker();
                    loop = false;
                } else {
                    System.out.println("I'm sorry, I didn't get that!");

                }
                } else{
                    System.out.println("Sorry, that's incorrect!");
                }

            } if(wrongGuesses >=6) {
                System.out.println("I'm sorry, your man is hanged!");
                System.out.println("You scored: "+playerScore+" points!");
                scoreChecker();
                loop = false;
            }

        } while (loop);
        System.exit(0);
    }

    public static boolean guessChecker(Character x, String word)
    {

        try
        {playerGuesses.add(x);

            if(!convertStringToCharList(word).contains(x))
            {
                wrongGuesses++;
                System.out.println("That letter is not in the word!");
            } else{
                playerScore++;
                System.out.println("Yes! The word does contain "+x+"!");
            }

            word.chars().mapToObj(c->(char) c).forEach(i->{
                    if(playerGuesses.contains(i)) {
                        System.out.print(i);
                    } else {
                        System.out.print("-");
                    }
            });

            System.out.println();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static char getInputChar() {
        Scanner input = new Scanner(System.in);

        String guessInput;

        guessInput = input.nextLine();

        return guessInput.charAt(0);
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

    public static String pickWord() throws IOException {

        List<String> words = Files.readAllLines(Path.of("./words_alpha.txt"));

        Random rand = new Random();

        return words.get(rand.nextInt(words.size()-1));
    }

    public static String getInput() {
        Scanner inSc = new Scanner(System.in);

        String playerInput = inSc.nextLine();

        return playerInput;
    }

    public static void displayImage() throws IOException
    {
        BufferedImage img;
        ImageIcon icon;
        JFrame frame;
        JLabel lbl;
        switch (wrongGuesses){
            case 0:
                img=ImageIO.read(new File("./Hangman_Gallows0.jpg"));
                icon=new ImageIcon(img);
                frame=new JFrame();
                frame.setLayout(new FlowLayout());
                frame.setSize(400,400);
                lbl=new JLabel();
                lbl.setIcon(icon);
                frame.add(lbl);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                break;
            case 1:
                img=ImageIO.read(new File("./Hangman_Gallows1.jpg"));
                icon=new ImageIcon(img);
                frame=new JFrame();
                frame.setLayout(new FlowLayout());
                frame.setSize(400,400);
                lbl=new JLabel();
                lbl.setIcon(icon);
                frame.add(lbl);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                break;
            case 2:
                img=ImageIO.read(new File("./Hangman_Gallows2.jpg"));
                icon=new ImageIcon(img);
                frame=new JFrame();
                frame.setLayout(new FlowLayout());
                frame.setSize(400,400);
                lbl=new JLabel();
                lbl.setIcon(icon);
                frame.add(lbl);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                break;
            case 3:
                img=ImageIO.read(new File("./Hangman_Gallows3.jpg"));
                icon=new ImageIcon(img);
                frame=new JFrame();
                frame.setLayout(new FlowLayout());
                frame.setSize(400,400);
                lbl=new JLabel();
                lbl.setIcon(icon);
                frame.add(lbl);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                break;
            case 4:
                img=ImageIO.read(new File("./Hangman_Gallows4.jpg"));
                icon=new ImageIcon(img);
                frame=new JFrame();
                frame.setLayout(new FlowLayout());
                frame.setSize(400,400);
                lbl=new JLabel();
                lbl.setIcon(icon);
                frame.add(lbl);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                break;
            case 5:
                img=ImageIO.read(new File("./Hangman_Gallows5.jpg"));
                icon=new ImageIcon(img);
                frame=new JFrame();
                frame.setLayout(new FlowLayout());
                frame.setSize(400,400);
                lbl=new JLabel();
                lbl.setIcon(icon);
                frame.add(lbl);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                break;
        }

    }

    public static void scoreChecker() throws IOException {

        System.out.println("Thank you for playing!");
        FileWriter hiScWriter = new FileWriter("./highScore.txt");
        FileReader hiScReader = new FileReader("./highScore.txt");
                if(!hiScore.exists())
                {
                    System.out.println("It looks like you're the first person to play this!");
                    System.out.println("Congratulations, "+playerName+"! You have the high score of: " +playerScore+ " points!");
                    try{
                        hiScWriter.write(""+playerName+"\n"+playerScore+"");
                        hiScWriter.flush();
                        hiScWriter.close();
                    } catch (Exception e)
                    {
                        System.out.println("There was an error writing to the high scores file!");
                    }
                } else if(Integer.parseInt(highScore)<playerScore)
                {
                    System.out.println("You've beaten the high score, "+playerName+"! Congratulations!");
                    try{
                        hiScWriter.write(""+playerName+"\n"+playerScore+"");
                        hiScWriter.flush();
                        hiScWriter.close();
                    } catch (Exception e)
                    {
                        System.out.println("There was an error writing to the high scores file!");
                    }

                    System.out.println("Your high score has been recorded!");
                } else {
                    System.out.println("I'm sorry, "+playerName+". It seems like you didn't beat "+hiName+"'s score!");
                }
            }



    }

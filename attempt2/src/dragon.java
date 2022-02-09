import java.util.Scanner;

public class dragon {



        public static void main(String[] args) {

            System.out.println("You are in a land full of dragons.");
            System.out.println("In front of you, you see two caves.");
            System.out.println("In one cave, the dragon is friendly and will share his treasure with you.");
            System.out.println("The other dragon is greedy and hungry and will eat you on sight");
            System.out.println("Which cave will you go into? (1 or 2)");


            try {
                Scanner sc = new Scanner(System.in);
                int check = sc.nextInt();
                if (check == 1) {
                    System.out.println("You approach the cave...");
                    System.out.println("It is dark and spooky...");
                    System.out.println("A large dragon jumps out in front of you! He opens his jaws and...");
                    System.out.println("Gobbles you down in one bite!");
                } else {
                    if (check == 2) {
                        System.out.println("You approach the cave...");
                        System.out.println("A gentle light emanates from within...");
                        System.out.println("A glimmering golden dragon emerges...");
                        System.out.println("They provide you a gift of treasure!");
                    }

                }
            } catch (Exception e) {
                System.out.println("Error. Exception encountered.");
                return;
            }
        }
    }
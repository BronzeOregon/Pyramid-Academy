import java.util.Scanner;

public class dragon {



        public static void main(String[] args) {

            int check = 0;
            int err = 0;
            boolean satisfied = false;


            System.out.print(intro());


            try {
                check = inputCheck();
            } catch (Exception e) {
                System.out.println("I didn't get that. Do you want to enter cave 1 or 2?");
                err = 1;
            }
            while(err == 1) {
                try {
                    check = inputCheck();
                    err = 0;
                } catch (Exception e) {
                    System.out.println("I didn't get that. Do you want to enter cave 1 or 2?");
                    err = 1;
                }
            }
            while (satisfied == false) {

                switch(check){

                    case 1:
                    case 2:
                        System.out.println(narrative(check));
                            satisfied = true;
                            break;

                    default:
                        System.out.println(narrative(check));

                        try {
                            check = inputCheck();
                        } catch (Exception e) {
                            System.out.println("I didn't get that. Do you want to enter cave 1 or 2?");
                            err = 1;
                        }
                        while(err == 1) {
                            try {
                                check = inputCheck();
                                err = 0;
                            } catch (Exception e) {
                                System.out.println("I didn't get that. Do you want to enter cave 1 or 2?");
                                err = 1;
                            }
                        };

                }
//                if (check == 1) {
//                    System.out.print(narrative(check));
//                    satisfied = true;
//                } else {
//                    if (check == 2) {
//                        System.out.print(narrative(check));
//                        satisfied = true;
//                    }
//                    try {
//
//                            Scanner sc = new Scanner(System.in);
//                            check = inputCheck();
//                        } catch (Exception e) {
//                            System.out.println("I didn't get that. Do you want to enter cave 1 or 2?");
//                        }
//
//
//
//                }
           }



        }

        public static int inputCheck()
        {
            Scanner sc = new Scanner(System.in);
            return sc.nextInt();
        }

        public static String narrative(int x) {

            switch (x) {

                case 1:
                    return "You approach the cave...\n It is dark and spooky... \n A large dragon jumps out in front of you! He opens his jaws and... \n Gobbles you down in one bite!\n";

                case 2:
                    return "You approach the cave... \n A gentle light emanates from within... \n A glimmering golden dragon emerges... \n They provide you a gift of treasure!\n";

                default:
                    return "I didn't understand that. Please enter (1) or (2).";
            }


        }

        public static String intro()
        {
            return "You are in a land full of dragons. \n In front of you, you see two caves. \n In one cave, the dragon is friendly and will share his treasure with you. \n The other dragon is greedy and hungry and will eat you on sight \n Which cave will you go into? (1 or 2)\n";
        }
}



package ee.sda.maven.messagingapp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // user interface for the messaging app
        while (true) {
            //Interaction menu in command line
            //moved within while loop this needs to be repeated for the users
            System.out.println();
            System.out.println("Welcome to MessangerApp");
            System.out.println("Please choose your action:");
            System.out.println("1 - Create a new account");
            System.out.println("2 - Log into your account");
            System.out.println("3 - Display user details");
            System.out.println("0 - Exit application");

            //actions carried out based on user's choice
            int userOption = scanner.nextInt();

            switch (userOption) {
                case 0:
                    return;

                case 1:
                    //Creating a new personal account
//                    System.out.println("Please provide the following details");
//                    System.out.println("==================================================");
//                    System.out.println("1. Please enter your email address which shall be your log in");
//                    System.out.println("2. Please enter your nickname");
//                    System.out.println("3. Please choose a login password");
//                    System.out.println("Please enter your choice");
//                    int choiceOfAccountType = scanner.nextInt();
//
//                    System.out.println("Please input the name for this new account");
//                    //nested switch case for a choice of type of account
//                    String nameForAccount = scanner.next();
//                    switch (choiceOfAccountType) {
//                        case 1:
//                            user.createEmail(nameForAccount);
//                            break;
//
//                        case 2:
//                            user.createName(nameForAccount);
//                            break;
//
//                        case 3:
//                            user.createPassword(nameForAccount);
//                            break;
//
//                        default:
//                            System.out.println("You've made an error in your selection process");
//                            break;
//
//                    }
                    break;

                // Log into the account
                case 2:
//                    System.out.println("Please input the name on your account");
//                    String nameForAccount = scanner.next();
                    break;

                // Display user details of who logged in
                case 3:
//                    System.out.println("Enter your login name");
//                    String nameForUserDetails = scanner.next();
//                    System.out.println("Enter your login password");
//                    String nameForUserDetails = scanner.next();
//                    user.printUser(nameForUserDetails);
                    break;

                default:
                    System.out.println("You have incorrectly chosen from the menu. \n Please contact admin for help");
                    break;

            }
        }
    }
}

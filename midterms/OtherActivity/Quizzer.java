package midterms.OtherActivity;

import midterms.OtherActivity.services.InputMethods;

public class Quizzer {
    public static void main(String[] args) {
        boolean userHasNotExited = true;
        while (userHasNotExited) {
            System.out.println("""
                     ____________________________________________________
                    |                                                    |
                    | [1] Player Registration                            |
                    | [2] Play                                           |
                    | [0] Exit                                           |
                    |____________________________________________________|
                    """);
            int choice = InputMethods.inputInt("Choice: ", 0, 2);

            switch (choice) {
                case 1 -> {
                    System.out.println("Player has registered!");
                }
                case 2 -> {
                    System.out.println("Playing...(WIP)");
                }
                case 0 -> {
                    System.out.println("Exiting the program...");
                    userHasNotExited = false;
                }
            }
        }
    }
}

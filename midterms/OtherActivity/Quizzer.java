package midterms.OtherActivity;

import java.util.ArrayList;
import java.util.List;

import midterms.OtherActivity.models.Player;
import midterms.OtherActivity.services.InputMethods;
import midterms.OtherActivity.services.PlayerService;

public class Quizzer {
    private static List<Player> playerList = new ArrayList<>();

    public static void main(String[] args) {
        playerList = PlayerService.loadUsers();

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

package midterms.OtherActivity;

import java.util.ArrayList;
import java.util.List;

import midterms.OtherActivity.models.Player;
import midterms.OtherActivity.services.InputMethods;
import midterms.OtherActivity.services.PlayerService;

public class Quizzer {
    private static  List<Player> playerList = new ArrayList<>();

    public static void main(String[] args) {
        playerList = PlayerService.loadUsers();

        boolean userHasNotExited = true;
        while (userHasNotExited) {
            System.out.println("""
                     ____________________________________________________
                    |                                                    |
                    | [1] Sign In                                        |
                    | [2] Sign Up                                        |
                    | [0] Exit                                           |
                    |____________________________________________________|
                    """);
            int choice = InputMethods.inputInt("Choice: ", 0, 2);

            switch (choice) {
                case 1 -> {
                    PlayerService.signIn();
                }
                case 2 -> {
                    PlayerService.signUp();
                    PlayerService.saveUsers(playerList);
                }
                case 0 -> {
                    PlayerService.saveUsers(playerList);
                    userHasNotExited = false;
                }
            }

        }
    }
}

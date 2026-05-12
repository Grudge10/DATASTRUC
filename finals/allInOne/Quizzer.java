package finals.allInOne;

import java.util.List;
import java.util.Scanner;

import finals.allInOne.models.Question;
import finals.allInOne.models.User;
import finals.allInOne.services.QuizService;
import finals.allInOne.services.UserService;

public class Quizzer {
    public static void start(Scanner input) {
        User currentUser = null;
        List<User> userList = UserService.loadUsers();
        List<Question> questionList = QuizService.loadQuestions();

        boolean isRunning1 = true;
        while (isRunning1) {
            if (currentUser != null) {
                InputMethods.println("""
                         ____________________________________________________
                        |                                                    |
                        | [1] Logout                                         |
                        | [2] Play                                           |
                        | [3] Manage Question Bank                           |
                        | [4] Leaderboard                                    |
                        | [0] Exit                                           |
                        |____________________________________________________|
                        """);
            } else {
                InputMethods.println("""
                         ____________________________________________________
                        |                                                    |
                        | [1] Player Registration                            |
                        | [2] Play                                           |
                        | [3] Manage Question Bank                           |
                        | [4] Leaderboard                                    |
                        | [0] Exit                                           |
                        |____________________________________________________|
                        """);
            }
            int choice1 = InputMethods.inputInt("Choice: ", input, 0, 4);

            switch (choice1) {
                case 1 -> {
                    if (currentUser != null) {
                        currentUser = null;
                        InputMethods.println("Successfully logged out!");
                    } else {
                        boolean isRunning2 = true;
                        while (isRunning2) {
                            InputMethods.println("""
                                     ____________________________________________________
                                    |                                                    |
                                    | [1] Login                                          |
                                    | [2] Register                                       |
                                    | [0] Back                                           |
                                    |____________________________________________________|
                                    """);
                            int choice2 = InputMethods.inputInt("Choice: ", input, 0, 2);

                            switch (choice2) {
                                case 1 -> {
                                    currentUser = UserService.login(userList, input);

                                    if (currentUser != null) {
                                        InputMethods.println("You have successfully logged in!");
                                        isRunning2 = false;
                                    }
                                }
                                case 2 -> {
                                    UserService.register(userList, input);
                                    InputMethods.println("Successfully Registered! Please Login.");
                                }
                                case 0 -> {
                                    isRunning2 = false;
                                }
                            }
                        }
                    }
                }
                case 2 -> {
                    if (currentUser == null) {
                        InputMethods.println("ACCESS DENIED: You must Login or Register first");
                    } else {
                        InputMethods.println("Welcome " + currentUser.getUsername() + "!");

                        List<Question> selectedQuestions = QuizService.selectQuestions(questionList);
                        int score = QuizService.playGame(selectedQuestions, input);

                        if (score > currentUser.getHighestScore()) {
                            InputMethods.println("NEW HIGH SCORE!");
                            currentUser.setHighestScore(score);
                            UserService.saveUsers(userList);
                        }

                        InputMethods.println(String.format("your score is %d out of %d", score, selectedQuestions.size()));
                    }
                }
                case 3 -> {
                    if (currentUser == null) {
                        InputMethods.println("ACCESS DENIED: You must Login or Register first");
                    } else {
                        QuizService.manageQuestionBank(questionList, input);
                    }
                }
                case 4 -> {
                    UserService.displayLeaderboard(userList);
                }
                case 0 -> {
                    InputMethods.println("Exiting the program...");
                    isRunning1 = false;
                }
            }
        }
    }
}
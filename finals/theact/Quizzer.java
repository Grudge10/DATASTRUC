package finals.theact;

import java.util.List;

import finals.theact.models.Question;
import finals.theact.models.User;
import finals.theact.services.InputMethods;
import finals.theact.services.QuizService;
import finals.theact.services.UserService;

public class Quizzer {
    public static void main(String[] args) {
        User currentUser = null;
        List<User> userList = UserService.loadUsers();
        List<Question> questionList = QuizService.loadQuestions();

        boolean isRunning1 = true;
        while (isRunning1) {
            if (currentUser != null) {
                System.out.println("""
                         ____________________________________________________
                        |                                                    |
                        | [1] Logout                                         |
                        | [2] Play                                           |
                        | [3] Manage Question Bank                           |
                        | [0] Exit                                           |
                        |____________________________________________________|
                        """);
            } else {
                System.out.println("""
                         ____________________________________________________
                        |                                                    |
                        | [1] Player Registration                            |
                        | [2] Play                                           |
                        | [3] Manage Question Bank                           |
                        | [0] Exit                                           |
                        |____________________________________________________|
                        """);
            }
            int choice1 = InputMethods.inputInt("Choice: ", 0, 3);

            switch (choice1) {
                case 1 -> {
                    if (currentUser != null) {
                        currentUser = null;
                        System.out.println("Successfully logged out!");
                    } else {
                        boolean isRunning2 = true;
                        while (isRunning2) {
                            System.out.println("""
                                     ____________________________________________________
                                    |                                                    |
                                    | [1] Login                                          |
                                    | [2] Register                                       |
                                    | [0] Back                                           |
                                    |____________________________________________________|
                                    """);
                            int choice2 = InputMethods.inputInt("Choice: ", 0, 2);

                            switch (choice2) {
                                case 1 -> {
                                    currentUser = UserService.login(userList);

                                    if (currentUser != null) {
                                        System.out.println("You have successfully logged in!");
                                        isRunning2 = false;
                                    }
                                }
                                case 2 -> {
                                    UserService.register(userList);
                                    System.out.println("Successfully Registered! Please Login.");
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
                        System.out.println("ACCESS DENIED: You must Login or Register first");
                    } else {
                        System.out.println("Welcome " + currentUser.getUsername() + "!");

                        List<Question> selectedQuestions = QuizService.selectQuestions(questionList);
                        int score = QuizService.playGame(selectedQuestions);

                        if (score > currentUser.getHighestScore()) {
                            System.out.println("NEW HIGH SCORE!");
                            currentUser.setHighestScore(score);
                            UserService.saveUsers(userList); // Persist the change immediately!
                        }

                        System.out.printf("your score is %d out of %d\n", score, selectedQuestions.size());
                    }
                }
                case 3 -> {
                    QuizService.manageQuestionBank(questionList);
                }
                case 0 -> {
                    System.out.println("Exiting the program...");
                    isRunning1 = false;
                }
            }
        }
    }
}

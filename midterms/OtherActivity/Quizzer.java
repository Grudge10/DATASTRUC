package midterms.OtherActivity;

import java.util.List;

import midterms.OtherActivity.models.Question;
import midterms.OtherActivity.services.InputMethods;
import midterms.OtherActivity.services.QuizService;

public class Quizzer {
    public static void main(String[] args) {
        List<Question> questionList = QuizService.loadQuestions();

        boolean isRunning = true;
        while (isRunning) {
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
                    List<Question> selectedQuestions = QuizService.selectQuestions(questionList);
                    int score = QuizService.playGame(selectedQuestions);
                    System.out.printf("your score is %d out of %d\n", score, selectedQuestions.size());
                }
                case 0 -> {
                    System.out.println("Exiting the program...");
                    isRunning = false;
                }
            }
        }
    }
}

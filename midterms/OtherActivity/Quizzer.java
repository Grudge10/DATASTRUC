package midterms.OtherActivity;

import java.util.List;
import java.util.Random;

import midterms.OtherActivity.models.Question;
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

    public static void jumbleQuestions(List<Question> questionList) {
        Random random = new Random();

        for (int i = questionList.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            Question temp = questionList.get(i);
            questionList.set(i, questionList.get(j));
            questionList.set(j, temp);
        }
    }
}

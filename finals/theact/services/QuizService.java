package finals.theact.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import finals.theact.models.Question;

public class QuizService {
    public static List<Question> loadQuestions() {
        List<Question> questionList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("finals/theact/data/Questions.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                String question = data[0];
                String a = data[1];
                String b = data[2];
                String c = data[3];

                int answer = 0;
                try {
                    answer = Integer.parseInt(data[4]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                Question loadedQuestion = new Question(question, a, b, c, answer);

                questionList.add(loadedQuestion);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return questionList;
    }

    public static List<Question> selectQuestions(List<Question> questionList) {
        List<Question> newQuestionList = new ArrayList<>(questionList);
        Random random = new Random();
        for (int i = newQuestionList.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Question temp = newQuestionList.get(i);
            newQuestionList.set(i, newQuestionList.get(j));
            newQuestionList.set(j, temp);
        }

        List<Question> randomizedQuestionList = new ArrayList<>();
        int count = Math.min(15, newQuestionList.size());
        for (int i = 0; i < count; i++) {
            randomizedQuestionList.add(newQuestionList.get(i));
        }
        return randomizedQuestionList;
    }

    public static int playGame(List<Question> questionList) {
        int index = 0;
        int points = 0;
        Stack<Integer> oStack = new Stack<>();
        
        boolean[] answered = new boolean[questionList.size()];
        boolean[] correct = new boolean[questionList.size()];
        
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("-----------------------------------------------------");
            System.out.printf("Question # %d\n", (index + 1));
            System.out.print(questionList.get(index));
            
            if (answered[index]) {
                if (correct[index]) {
                    System.out.println("Tama ka dito beh!");
                } else {
                    System.out.println("Minali mo to tey!");
                }
            }
            
            System.out.println("""

                     ____________________________________________________
                    |                                                    |
                    | [1] Answer                                         |
                    | [2] Back                                           |
                    | [3] Next                                           |
                    | [4] Exit                                           |
                    |____________________________________________________|
                    """);
            
            int choice = InputMethods.inputInt("Choice: ", 1, 4);

            switch (choice) {
                case 1 -> {
                    if (answered[index]) {
                        System.out.println("You have already answered that question!");
                    } else {
                        int ans = InputMethods.inputInt("Answer (1-3): ", 1, 3);
                        if (ans == questionList.get(index).getAnswer()) {
                            System.out.println("Absolutely correct!");
                            correct[index] = true;
                            points++;
                        } else {
                            System.out.println("Do better next time!");
                            correct[index] = false;
                        }
                        answered[index] = true;
                        System.out.println("Points: " + points);
                    }
                }
                case 2 -> {
                    if (oStack.isEmpty()) {
                        System.out.println("No previous question yet!");
                    } else {
                        index = oStack.pop();
                    }
                }
                case 3 -> {
                    if (index < questionList.size() - 1) {
                        oStack.push(index);
                        index++;
                    } else {
                        System.out.println("Already at the last question");
                    }
                }
                case 4 -> {
                    System.out.println("Exiting current game...");
                    isRunning = false;
                }
            }
        }

        return points;
    }
}

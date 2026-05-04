package finals.theact.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import finals.theact.models.Question;

public class QuizService {
    private static final String QUESTION_FILE_PATH = "finals/theact/data/Questions.txt";

    public static List<Question> loadQuestions() {
        List<Question> questionList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(QUESTION_FILE_PATH))) {
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

    public static void saveQuestions(List<Question> questionList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(QUESTION_FILE_PATH))) {
            StringBuilder s = new StringBuilder();
            for (Question question : questionList) {
                s.append(question.getQuestion())
                        .append(",")
                        .append(question.getOptionA())
                        .append(",")
                        .append(question.getOptionB())
                        .append(",")
                        .append(question.getOptionC())
                        .append(",")
                        .append(question.getAnswer());

                writer.write(s.toString());
                writer.newLine();

                s.setLength(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void manageQuestionBank(List<Question> questionList) {
        int currentindex = 0;

        boolean isRunning = true;
        while (isRunning) {
            Question currentQuestion = null;
            int choice = 0;
            if (questionList.isEmpty()) {
                System.out.println("\n[!] The Question Bank is empty.");
                choice = InputMethods.inputInt("Add(1), Exit(0): ", 0, 1);
            } else {
                currentQuestion = questionList.get(currentindex);
                System.out.println("\n=== Question " + (currentindex + 1) + " of " + questionList.size() + " ===");
                System.out.println(currentQuestion);
                choice = InputMethods.inputInt("Add(1), Edit(2), Delete(3), Back(4), Next(5), Exit(0): ", 0, 5);
            }

            switch (choice) {
                case 1 -> {
                    String q = InputMethods.inputString("Question: ");
                    String a = InputMethods.inputString("Option A: ");
                    String b = InputMethods.inputString("Option B: ");
                    String c = InputMethods.inputString("Option C: ");
                    int ans = InputMethods.inputInt("Correct Answer(1-3): ", 1, 3);

                    questionList.add(new Question(q, a, b, c, ans));
                    saveQuestions(questionList);

                    System.out.println("Question added Successfully!");
                }
                case 2 -> {
                    if (!questionList.isEmpty()) {
                        System.out.println("Editing: " + currentQuestion.getQuestion());

                        currentQuestion.setQuestion(InputMethods.inputString("New Question: "));
                        currentQuestion.setOptionA(InputMethods.inputString("New Option A: "));
                        currentQuestion.setOptionB(InputMethods.inputString("New Option B: "));
                        currentQuestion.setOptionC(InputMethods.inputString("New Option C: "));
                        currentQuestion.setAnswer(InputMethods.inputInt("New Answer: ", 1, 3));

                        saveQuestions(questionList);

                        System.out.println("Question updated!");
                    }
                }
                case 3 -> {
                    if (!questionList.isEmpty()) {
                        questionList.remove(currentindex);
                        saveQuestions(questionList);

                        if (currentindex >= questionList.size() && !questionList.isEmpty()) {
                            currentindex = questionList.size() - 1;
                        }

                        System.out.println("Question deleted and saved.");
                    }
                }
                case 4 -> {
                    if (currentindex > 0)
                        currentindex--;
                    else
                        System.out.println("Beginning of list reached.");
                }
                case 5 -> {
                    if (currentindex < questionList.size() - 1)
                        currentindex++;
                    else
                        System.out.println("End of list reached.");
                }
                case 0 -> {
                    isRunning = false;
                }
            }
        }
    }
}

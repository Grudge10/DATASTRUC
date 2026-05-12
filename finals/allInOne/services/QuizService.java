package finals.allInOne.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

import finals.allInOne.InputMethods;
import finals.allInOne.models.Question;

public class QuizService {
    private static final String QUESTION_FILE_PATH = "finals/allInOne/data/Questions.txt";

    public static List<Question> loadQuestions() {
        List<Question> questionList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(QUESTION_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length < 5) {
                    InputMethods.println("Skipping malformed line: " + line);
                    continue;
                }

                String question = data[0];
                String a = data[1];
                String b = data[2];
                String c = data[3];

                int answer = 0;
                try {
                    answer = Integer.parseInt(data[4]);
                } catch (NumberFormatException e) {
                    InputMethods.println("Skipping line with invalid answer: " + line);
                    continue;
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

    public static int playGame(List<Question> questionList, Scanner input) {
        int index = 0;
        int points = 0;
        Stack<Integer> oStack = new Stack<>();

        boolean[] answered = new boolean[questionList.size()];
        boolean[] correct = new boolean[questionList.size()];

        boolean isRunning = true;

        while (isRunning) {
            InputMethods.println("-----------------------------------------------------");
            InputMethods.println(String.format("Question # %d", (index + 1)));
            InputMethods.print(questionList.get(index).toString());

            if (answered[index]) {
                if (correct[index]) {
                    InputMethods.println("Tama ka dito beh!");
                } else {
                    InputMethods.println("Minali mo to tey!");
                }
            }

            InputMethods.println("""

                     ____________________________________________________
                    |                                                    |
                    | [1] Answer                                         |
                    | [2] Back                                           |
                    | [3] Next                                           |
                    | [4] Exit                                           |
                    |____________________________________________________|
                    """);

            int choice = InputMethods.inputInt("Choice: ", input, 1, 4);

            switch (choice) {
                case 1 -> {
                    if (answered[index]) {
                        InputMethods.println("You have already answered that question!");
                    } else {
                        int ans = InputMethods.inputInt("Answer (1-3): ", input,1, 3);
                        if (ans == questionList.get(index).getAnswer()) {
                            InputMethods.println("Absolutely correct!");
                            correct[index] = true;
                            points++;
                        } else {
                            InputMethods.println("Do better next time!");
                            correct[index] = false;
                        }
                        answered[index] = true;
                        InputMethods.println("Points: " + points);
                    }
                }
                case 2 -> {
                    if (oStack.isEmpty()) {
                        InputMethods.println("No previous question yet!");
                    } else {
                        index = oStack.pop();
                    }
                }
                case 3 -> {
                    if (index < questionList.size() - 1) {
                        oStack.push(index);
                        index++;
                    } else {
                        InputMethods.println("Already at the last question");
                    }
                }
                case 4 -> {
                    InputMethods.println("Exiting current game...");
                    isRunning = false;
                }
            }
        }

        return points;
    }

    public static void saveQuestions(List<Question> questionList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(QUESTION_FILE_PATH))) {
            for (Question question : questionList) {
                StringBuilder s = new StringBuilder();
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void manageQuestionBank(List<Question> questionList, Scanner input) {
        int currentindex = 0;

        boolean isRunning = true;
        while (isRunning) {
            Question currentQuestion = null;
            int choice = 0;
            if (questionList.isEmpty()) {
                InputMethods.println("\n[!] The Question Bank is empty.");
                choice = InputMethods.inputInt("Add(1), Exit(0): ", input, 0, 1);
            } else {
                currentQuestion = questionList.get(currentindex);
                InputMethods.println("\n=== Question " + (currentindex + 1) + " of " + questionList.size() + " ===");
                InputMethods.println(currentQuestion.toString());
                choice = InputMethods.inputInt("Add(1), Edit(2), Delete(3), Back(4), Next(5), Exit(0): ", input, 0, 5);
            }

            switch (choice) {
                case 1 -> {
                    String q = InputMethods.inputString("Question: ", input);
                    String a = InputMethods.inputString("Option A: ", input);
                    String b = InputMethods.inputString("Option B: ", input);
                    String c = InputMethods.inputString("Option C: ", input);
                    int ans = InputMethods.inputInt("Correct Answer(1-3): ", input, 1, 3);

                    questionList.add(new Question(q, a, b, c, ans));
                    saveQuestions(questionList);

                    InputMethods.println("Question added Successfully!");
                }
                case 2 -> {
                    if (!questionList.isEmpty()) {
                        InputMethods.println("Editing: " + currentQuestion.getQuestion());

                        currentQuestion.setQuestion(InputMethods.inputString("New Question: ", input));
                        currentQuestion.setOptionA(InputMethods.inputString("New Option A: ", input));
                        currentQuestion.setOptionB(InputMethods.inputString("New Option B: ", input));
                        currentQuestion.setOptionC(InputMethods.inputString("New Option C: ", input));
                        currentQuestion.setAnswer(InputMethods.inputInt("New Answer: ", input, 1, 3));

                        saveQuestions(questionList);

                        InputMethods.println("Question updated!");
                    }
                }
                case 3 -> {
                    if (!questionList.isEmpty()) {
                        questionList.remove(currentindex);
                        saveQuestions(questionList);

                        if (currentindex >= questionList.size() && !questionList.isEmpty()) {
                            currentindex = questionList.size() - 1;
                        }

                        InputMethods.println("Question deleted and saved.");
                    }
                }
                case 4 -> {
                    if (currentindex > 0)
                        currentindex--;
                    else
                        InputMethods.println("Beginning of list reached.");
                }
                case 5 -> {
                    if (currentindex < questionList.size() - 1)
                        currentindex++;
                    else
                        InputMethods.println("End of list reached.");
                }
                case 0 -> {
                    isRunning = false;
                }
            }
        }
    }
}
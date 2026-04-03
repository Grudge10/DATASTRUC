package midterms.OtherActivity.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import midterms.OtherActivity.models.Question;

public class QuizService {
    public static List<Question> loadQuestions() {
        List<Question> questionList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("midterms/OtherActivity/data/Questions.txt"))) {
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
        Random random = new Random();
        for (int i = questionList.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Question temp = questionList.get(i);
            questionList.set(i, questionList.get(j));
            questionList.set(j, temp);
        }

        List<Question> newQuestionList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            newQuestionList.add(questionList.get(i));
        }
        return newQuestionList;
    }

    public static int playGame(List<Question> questionList) {
        int score = 0;

        for (Question question : questionList) {
            System.out.println(question);
            int answer = InputMethods.inputInt("Answer: ", 1, 3);

            if (answer == question.getAnswer()) {
                score++;
            }
        }

        return score;
    }
}

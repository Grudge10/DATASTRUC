package finals.theact.models;

public class Question {
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private int answer;

    public Question() {
    }

    public Question(String question, String optionA, String optionB, String optionC, int answer) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public int getAnswer() {
        return answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return String.format("""
                %s
                [1] %s
                [2] %s
                [3] %s
                """, question, optionA, optionB, optionC);
    }
}

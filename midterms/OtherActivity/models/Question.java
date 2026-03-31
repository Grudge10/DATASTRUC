package midterms.OtherActivity.models;

public class Question {
    private String question;
    private String a;
    private String b;
    private String c;
    private int answer;

    public Question() {
    }

    public Question(String question, String a, String b, String c, int answer) {
        this.question = question;
        this.a = a;
        this.b = b;
        this.c = c;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    public int getAnswer() {
        return answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setA(String a) {
        this.a = a;
    }

    public void setB(String b) {
        this.b = b;
    }

    public void setC(String c) {
        this.c = c;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return String.format("""
                %s
                A. %s
                B. %s
                C. %s
                """, question, a, b, c);
    }
}

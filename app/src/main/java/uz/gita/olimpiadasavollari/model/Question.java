package uz.gita.olimpiadasavollari.model;

public class Question {

    String question;
    String ans1;
    String ans2;
    String ans3;
    String ans4;
    Level level;
    String trueAns;

    public Question(String question, String ans1, String ans2, String ans3, String ans4, Level level, String trueAns) {
        this.question = question;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
        this.level = level;
        this.trueAns = trueAns;
    }

    public String getQuestion() {
        return question;
    }

    public String getAns1() {
        return ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public String getAns3() {
        return ans3;
    }

    public String getAns4() {
        return ans4;
    }

    public Level getLevel() {
        return level;
    }

    public String getTrueAns() {
        return trueAns;
    }
}

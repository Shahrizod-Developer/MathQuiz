package uz.gita.olimpiadasavollari.model;

public class Result {

    int countCorrectAns;
    int totalQuestion;
    double percentage;
    String level;

    public Result(int countCorrectAns, int totalQuestion, double percentage, String level) {
        this.countCorrectAns = countCorrectAns;
        this.totalQuestion = totalQuestion;
        this.percentage = percentage;
        this.level = level;
    }

    public Result() {
    }

    public int getCountCorrectAns() {
        return countCorrectAns;
    }

    public int getTotalQuestion() {
        return totalQuestion;
    }

    public double getPercentage() {
        return percentage;
    }

    public String getLevel() {
        return level;
    }
}

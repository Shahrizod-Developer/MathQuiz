package uz.gita.olimpiadasavollari.contract.easylevelimpl;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.navigation.Navigation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.stream.Stream;

import uz.gita.olimpiadasavollari.R;
import uz.gita.olimpiadasavollari.contract.EasyLevelScreenContract;
import uz.gita.olimpiadasavollari.model.Level;
import uz.gita.olimpiadasavollari.model.Question;
import uz.gita.olimpiadasavollari.model.Result;
import uz.gita.olimpiadasavollari.utils.MySharedPreference;

public class PresenterEasyLevelImpl implements EasyLevelScreenContract.Presenter {

    private final EasyLevelScreenContract.View view;
    private final EasyLevelScreenContract.Repository repository;
    private Level level;
    private Gson gson;
    Context context;
    MySharedPreference sharedPreference;
    private int index;
    private int totalCount;
    private int selectedAnswer = -1;
    private int correctAnswer;

    public PresenterEasyLevelImpl(EasyLevelScreenContract.View view, EasyLevelScreenContract.Repository repository, Context context) {
        this.view = view;
        this.repository = repository;
        this.context = context;
        init();
    }

    private void init() {
        sharedPreference = MySharedPreference.getInstance(context);
        view.initView();
        if (sharedPreference.getStart()) {
            Log.d("TTT", sharedPreference.getStart().toString());
            repository.initQuestions(Level.EASY);
        } else if (sharedPreference.getLevel().equals("EASY")) {
            level = Level.EASY;
            repository.initQuestions(Level.EASY);
        } else if (sharedPreference.getLevel().equals("MEDIUM")) {
            repository.initQuestions(Level.MEDIUM);
            level = Level.MEDIUM;
        } else if (sharedPreference.getLevel().equals("HARD")) {
            repository.initQuestions(Level.HARD);
            level = Level.HARD;
        }
        repository.shuffle();
        totalCount = 10;
        view.nextButtonState(false, 0.5f);
        view.loadQuestions(repository.getQuestion(index));
        view.changeState(index + 1, totalCount);
    }

    @Override
    public void selectedAnswer(int position) {
        view.nextButtonState(true, 1);
        if (selectedAnswer > -1 && selectedAnswer != position) {
            view.clearCheck(selectedAnswer);
        }
        selectedAnswer = position;
    }

    @Override
    public void onClickNextButton() {

        boolean isCompleted = isTestCompleted();

        if (isCompleted) {
            if (calculatePercentage(correctAnswer) > 60) {
                ArrayList<Result> resultList = readFromData();

                String lev = "";
                if ((level == Level.EASY)) {
                    lev = "EASY";
                } else if (level == Level.MEDIUM) {
                    lev = "MEDIUM";
                } else if (level == Level.HARD) {
                    lev = "HARD";
                }
                Result result = new Result(correctAnswer, totalCount, calculatePercentage(correctAnswer), lev);
                resultList.add(result);
                sharedPreference.setResults(gson.toJson(resultList));
            }
            view.result(correctAnswer, totalCount);
            return;
        }
        index++;
        view.clearCheck(selectedAnswer);
        selectedAnswer = -1;
        view.nextButtonState(false, 0.5f);
        Question question = repository.getQuestion(index);
        view.loadQuestions(question);
        view.changeState(index + 1, totalCount);
    }

    @Override
    public void setOnclickMenu(View view) {
        Navigation.findNavController(view).navigate(R.id.action_easyLevelScreen_to_mainScreen);
    }

    @Override
    public void setOnclickRestart() {
        init();
        index = 0;
        view.clearCheck(selectedAnswer);
        selectedAnswer = -1;
        correctAnswer = 0;
        view.changeState(1, totalCount);
    }

    @Override
    public void setOnclickNextLevel(View view) {

        if (level == Level.EASY) {
            sharedPreference.setLevel("MEDIUM");
        } else if (level == Level.MEDIUM) {
            sharedPreference.setLevel("HARD");
        } else {
            setOnclickMenu(view);
        }
        setOnclickRestart();
    }

    public double calculatePercentage(int correctAnswer) {
        return (correctAnswer * 1.0 / 10) * 100;
    }

    private boolean isTestCompleted() {
        Question question = repository.getQuestion(index);
        String answer;

        switch (selectedAnswer) {
            case 0:
                answer = question.getAns1();
                break;
            case 1:
                answer = question.getAns2();
                break;
            case 2:
                answer = question.getAns3();
                break;
            default:
                answer = question.getAns4();
                break;
        }
        if (answer.equals(question.getTrueAns())) {
            correctAnswer++;
        }
        return totalCount - 1 == index;
    }

    private ArrayList<Result> readFromData() {

        ArrayList<Result> list = new ArrayList<>();
        gson = new Gson();
        String result = sharedPreference.getResults();

        Type type = new TypeToken<ArrayList<Result>>() {
        }.getType();
        if (!result.equals("")) {
            list = gson.fromJson(result, type);
        }
        return list;
    }
}

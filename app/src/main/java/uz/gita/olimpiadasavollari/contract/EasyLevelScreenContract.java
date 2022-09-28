package uz.gita.olimpiadasavollari.contract;

import android.content.Context;

import java.util.ArrayList;

import uz.gita.olimpiadasavollari.model.Level;
import uz.gita.olimpiadasavollari.model.Question;

public interface EasyLevelScreenContract {

    interface View {

        void initView();

        void loadQuestions(Question question);

        void clearCheck(int position);

        void result(int correctAnswers, int totalQuestions);

        void changeState(int currentQuestionIndex, int totalQuestions);

        void nextButtonState(boolean state, float alpha);

    }

    interface Repository {

        ArrayList<Question> initQuestions(Level level);

        Question getQuestion(int index);

        void shuffle();

    }

    interface Presenter {

        void selectedAnswer(int position);

        void onClickNextButton();

        void setOnclickMenu(android.view.View view);

        void setOnclickRestart();

        void setOnclickNextLevel(android.view.View view);
    }
}

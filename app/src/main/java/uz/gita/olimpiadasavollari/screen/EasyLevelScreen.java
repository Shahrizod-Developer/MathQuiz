package uz.gita.olimpiadasavollari.screen;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import java.util.ArrayList;

import uz.gita.olimpiadasavollari.contract.EasyLevelScreenContract;
import uz.gita.olimpiadasavollari.contract.easylevelimpl.PresenterEasyLevelImpl;
import uz.gita.olimpiadasavollari.contract.easylevelimpl.RepositoryEasyLevelImpl;
import uz.gita.olimpiadasavollari.databinding.EasyLevelScreenBinding;
import uz.gita.olimpiadasavollari.databinding.ResultDialogBinding;
import uz.gita.olimpiadasavollari.model.Level;
import uz.gita.olimpiadasavollari.model.Question;
import uz.gita.olimpiadasavollari.utils.MySharedPreference;

public class EasyLevelScreen extends Fragment implements EasyLevelScreenContract.View {

    private EasyLevelScreenBinding binding;
    ArrayList<RadioButton> buttons;
    EasyLevelScreenContract.Presenter presenter;
    MySharedPreference sharedPreference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = EasyLevelScreenBinding.inflate(inflater, container, false);
        sharedPreference = MySharedPreference.getInstance(requireContext());
        presenter = new PresenterEasyLevelImpl(this, new RepositoryEasyLevelImpl(), requireContext());

        binding.finish.setOnClickListener(v -> {
            presenter.setOnclickMenu(requireView());
        });

        return binding.getRoot();
    }

    @Override
    public void initView() {
        buttons = new ArrayList<>();
        buttons.add(binding.ans1);
        buttons.add(binding.ans2);
        buttons.add(binding.ans3);
        buttons.add(binding.ans4);
        binding.next.setEnabled(false);
        binding.next.setAlpha(0.5f);
        binding.next.setOnClickListener(v -> presenter.onClickNextButton());
        initCheckerListener();
    }

    private void initCheckerListener() {
        for (int i = 0; i < buttons.size(); i++) {
            RadioButton button = buttons.get(i);
            button.setTag(i);
            button.setOnClickListener(v -> presenter.selectedAnswer((Integer) v.getTag()));
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void loadQuestions(Question question) {

        if (sharedPreference.getStart()) {
            binding.degree.setText("Oson");
            sharedPreference.setStart(false);
        } else if (sharedPreference.getLevel().equals("EASY")) {
            binding.degree.setText("Oson");
        } else if (sharedPreference.getLevel().equals("MEDIUM")) {
            binding.degree.setText("O'rta");
        } else if (sharedPreference.getLevel().equals("HARD")) {
            binding.degree.setText("Qiyin");
        }
        binding.question.setText(question.getQuestion());
        buttons.get(0).setText(question.getAns1());
        buttons.get(1).setText(question.getAns2());
        buttons.get(2).setText(question.getAns3());
        buttons.get(3).setText(question.getAns4());

    }

    @Override
    public void clearCheck(int position) {
        buttons.get(position).setChecked(false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void result(int correctAnswers, int totalQuestions) {

        AlertDialog dialog = new AlertDialog.Builder(requireContext()).create();
        ResultDialogBinding dialogView = ResultDialogBinding.inflate(LayoutInflater.from(requireContext()), null, false);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        double percentage = (correctAnswers * 1.0 / 10) * 100;
        dialogView.trueAns.setText(correctAnswers + " / " + 10);

        if (sharedPreference.getLevel().equals("EASY")) {
            dialogView.degree.setText("Oson");
        } else if (sharedPreference.getLevel().equals("MEDIUM")) {
            dialogView.degree.setText("O'rta");
        } else if (sharedPreference.getLevel().equals("HARD")) {
            dialogView.degree.setText("Qiyin");
        }
        dialogView.restart.setOnClickListener(v -> {
            presenter.setOnclickRestart();
            dialog.cancel();
        });
        dialogView.goToMenu.setOnClickListener(v -> {
            presenter.setOnclickMenu(requireView());
            dialog.cancel();
        });
        dialogView.percentage.setText(percentage + " %");

        if (percentage < 60) {
            dialogView.nextLevel.setEnabled(false);
            dialogView.nextLevel.setAlpha(0.5f);
        } else {
            dialogView.nextLevel.setEnabled(true);
            dialogView.nextLevel.setAlpha(1f);
        }

        dialogView.nextLevel.setOnClickListener(v -> {
            presenter.setOnclickNextLevel(requireView());
            dialog.cancel();
        });

        dialog.setView(dialogView.getRoot());
        dialog.show();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void changeState(int currentQuestionIndex, int totalQuestions) {
        binding.count.setText(currentQuestionIndex + " / " + totalQuestions);
    }

    @Override
    public void nextButtonState(boolean state, float alpha) {

        binding.next.setEnabled(state);
        binding.next.setAlpha(alpha);
    }
}
package uz.gita.olimpiadasavollari.screen;

import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Objects;

import uz.gita.olimpiadasavollari.R;
import uz.gita.olimpiadasavollari.adapter.ResultAdapter;
import uz.gita.olimpiadasavollari.databinding.InfoDialogBinding;
import uz.gita.olimpiadasavollari.databinding.LevelDialogBinding;
import uz.gita.olimpiadasavollari.databinding.MainScreenBinding;
import uz.gita.olimpiadasavollari.utils.MySharedPreference;

public class MainScreen extends Fragment {

    private MySharedPreference sharedPreference;
    private MainScreenBinding binding;
    private String levelText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = MainScreenBinding.inflate(getLayoutInflater(), container, false);

        sharedPreference = MySharedPreference.getInstance(requireContext());

        levelText = sharedPreference.getLevel();

        if (sharedPreference.getVisibleContinue()) {
            binding.continueGame.setVisibility(View.VISIBLE);
        } else {
            binding.continueGame.setVisibility(View.GONE);
        }

        binding.continueGame.setOnClickListener(view -> {
            Navigation.findNavController(requireView()).navigate(R.id.action_mainScreen_to_easyLevelScreen);
        });

        binding.start.setOnClickListener(view -> {
            sharedPreference.setStart(true);
            sharedPreference.setVisibleContinue(true);
            Navigation.findNavController(requireView()).navigate(R.id.action_mainScreen_to_easyLevelScreen);
        });

        binding.info.setOnClickListener(v -> infoDialog());

        binding.level.setOnClickListener(v -> {
            levelDialog();
        });

        binding.results.setOnClickListener(v -> Navigation.findNavController(requireView()).navigate(R.id.action_mainScreen_to_resultScreen));
        return binding.getRoot();
    }

    public void levelDialog() {

        AlertDialog dialog = new AlertDialog.Builder(requireContext()).create();
        LevelDialogBinding dialogView = LevelDialogBinding.inflate(LayoutInflater.from(requireContext()), null, false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        dialog.setCancelable(false);
        if (Objects.equals(levelText, "EASY")) {
            dialogView.easy.setAlpha(0.5f);
            dialogView.easy.setClickable(false);
        } else if (Objects.equals(levelText, "MEDIUM")) {
            sharedPreference.setLevel("MEDIUM");
            dialogView.medium.setAlpha(0.5f);
        } else if (Objects.equals(levelText, "HARD")) {
            sharedPreference.setLevel("HARD");
            dialogView.hard.setAlpha(0.5f);
        }
        dialogView.easy.setOnClickListener(v -> {
            sharedPreference.setLevel("EASY");
            dialogView.easy.setAlpha(0.5f);
            dialogView.easy.setClickable(false);
            dialogView.medium.setAlpha(1);
            dialogView.medium.setClickable(true);
            dialogView.hard.setAlpha(1);
            dialogView.hard.setClickable(true);
        });
        dialogView.medium.setOnClickListener(v -> {
            sharedPreference.setLevel("MEDIUM");
            dialogView.medium.setAlpha(0.5f);
            dialogView.medium.setClickable(false);
            dialogView.easy.setAlpha(1);
            dialogView.easy.setClickable(true);
            dialogView.hard.setAlpha(1);
            dialogView.hard.setClickable(true);
        });
        dialogView.hard.setOnClickListener(v -> {
            sharedPreference.setLevel("HARD");
            dialogView.hard.setAlpha(0.5f);
            dialogView.hard.setClickable(false);
            dialogView.medium.setAlpha(1);
            dialogView.medium.setClickable(true);
            dialogView.easy.setAlpha(1);
            dialogView.easy.setClickable(true);
        });

        dialogView.ok.setOnClickListener(v -> dialog.cancel());
        dialog.setView(dialogView.getRoot());
        dialog.show();
    }


    public void infoDialog() {

        AlertDialog dialog = new AlertDialog.Builder(requireContext()).create();
        InfoDialogBinding dialogView = InfoDialogBinding.inflate(LayoutInflater.from(requireContext()), null, false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

        dialogView.ok.setOnClickListener(v -> dialog.cancel());
        dialog.setView(dialogView.getRoot());
        dialog.show();
    }

}
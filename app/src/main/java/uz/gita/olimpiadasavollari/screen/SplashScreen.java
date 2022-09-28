package uz.gita.olimpiadasavollari.screen;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import uz.gita.olimpiadasavollari.R;
import uz.gita.olimpiadasavollari.databinding.SplashScreenBinding;


@SuppressLint("CustomSplashScreen")
public class SplashScreen extends Fragment {

    private SplashScreenBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = SplashScreenBinding.inflate(inflater, container, false);

        TextView text = binding.text;
        Animation slideAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.animation);
        text.startAnimation(slideAnimation);
        CountDownTimer timer = new CountDownTimer(2000, 1000) {

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_splashScreen_to_mainScreen);

            }
        };
        timer.start();

        return binding.getRoot();
    }
}
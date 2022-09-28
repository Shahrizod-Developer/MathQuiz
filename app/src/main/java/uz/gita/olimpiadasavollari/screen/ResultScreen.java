package uz.gita.olimpiadasavollari.screen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import uz.gita.olimpiadasavollari.R;
import uz.gita.olimpiadasavollari.adapter.ResultAdapter;
import uz.gita.olimpiadasavollari.databinding.ResultScreenBinding;
import uz.gita.olimpiadasavollari.model.Result;
import uz.gita.olimpiadasavollari.utils.MySharedPreference;

public class ResultScreen extends Fragment {

    private MySharedPreference sharedPreference;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        uz.gita.olimpiadasavollari.databinding.ResultScreenBinding binding = ResultScreenBinding.inflate(inflater, container, false);

        sharedPreference = MySharedPreference.getInstance(requireContext());

        if (sharedPreference.getResults().equals("")) {
            binding.textt.setVisibility(View.VISIBLE);
            binding.rv.setVisibility(View.GONE);
        } else {
            binding.textt.setVisibility(View.GONE);
            binding.rv.setVisibility(View.VISIBLE);
        }

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(requireContext());
        binding.rv.setHasFixedSize(true);
        binding.rv.setLayoutManager(mLayoutManager);
        ResultAdapter adapter = new ResultAdapter();
        adapter.setList(readFromData());
        binding.rv.setAdapter(adapter);

        binding.text.setOnClickListener(v -> Toast.makeText(requireContext(), sharedPreference.getResults(), Toast.LENGTH_SHORT).show());
        binding.finish.setOnClickListener(v -> Navigation.findNavController(requireView()).navigate(R.id.action_resultScreen_to_mainScreen));
        return binding.getRoot();
    }

    private ArrayList<Result> readFromData() {

        ArrayList<Result> list = new ArrayList<>();
        Gson gson = new Gson();
        String result = sharedPreference.getResults();

        Type type = new TypeToken<ArrayList<Result>>() {
        }.getType();
        if (!result.equals("")) {
            list = gson.fromJson(result, type);
        }
        return list;
    }
}
package uz.gita.olimpiadasavollari.adapter;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import uz.gita.olimpiadasavollari.databinding.ItemResultBinding;
import uz.gita.olimpiadasavollari.model.Result;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    private ArrayList<Result> list;

    public void setList(ArrayList<Result> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(ItemResultBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBind(list.get(position));
        holder.binding.number.setText(String.valueOf(position + 1));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ItemResultBinding binding;

        public ViewHolder(ItemResultBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void onBind(Result state) {

            binding.level.setText(state.getLevel());
            binding.total.setText(String.valueOf(state.getTotalQuestion()));
            binding.percentage.setText(String.valueOf(state.getPercentage()));
            binding.correct.setText(String.valueOf(state.getCountCorrectAns()));
        }
    }
}
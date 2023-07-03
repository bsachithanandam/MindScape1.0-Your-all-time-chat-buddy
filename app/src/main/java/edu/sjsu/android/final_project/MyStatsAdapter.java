package edu.sjsu.android.final_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.sjsu.android.final_project.databinding.FragmentStatsItemBinding;


public class MyStatsAdapter extends RecyclerView.Adapter<MyStatsAdapter.ViewHolder> {

    private final List<StatsItem> mValues;



    public MyStatsAdapter(List<StatsItem> items) {
        mValues = items;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentStatsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        StatsItem item = mValues.get(position);
        if (position == 0) {
            holder.mood.setText("Your Entries\n\nTotal Number of Entries: "+ item.getLength());
        } else {

            String date = item.getDate();
            String month = item.getMonth();
            String year = item.getYear();
            String mood = item.getName();
            String value_to_set = month + " " + date + ", " + year + "\n\nI felt " + mood;
            holder.mood.setText(value_to_set);
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mood;

        NavController controller;
        private Context context;
        public FragmentStatsItemBinding binding;

        public ViewHolder(FragmentStatsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            mood = binding.content;
        }
    }
}
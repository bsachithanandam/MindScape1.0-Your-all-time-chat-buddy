package edu.sjsu.android.final_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.sjsu.android.final_project.databinding.FragmentItemBinding;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private final List<ListItem> mValues;
    public  OnClickAdapter onClickAdapter;
    public MyAdapter(List<ListItem> items, OnClickAdapter onClickAdapter){
        this.mValues = items;
        this.onClickAdapter = onClickAdapter;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
       ListItem item = mValues.get(position);
        holder.image.setImageResource(item.getImage());
        holder.name.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView name;
        public final ImageView image;
        NavController controller;
        private Context context;
        public FragmentItemBinding binding;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
            image = binding.image;
            name = binding.content;
            binding.getRoot().setOnClickListener(view -> onClickAdapter.onClick(getLayoutPosition()));
        }
    }
}
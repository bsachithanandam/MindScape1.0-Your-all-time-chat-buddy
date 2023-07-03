package edu.sjsu.android.final_project;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class home extends Fragment  {

    ArrayList<ListItem> data;
    private NavController controller;
    private  ListItem selected;

    public home() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new ArrayList<>();
        data.add(new ListItem("Learn how to use me!!",R.drawable.info));
        data.add(new ListItem("Tell how you are feeling today \n\n ______________________",R.drawable.grinalt));
        data.add(new ListItem("You can talk to me",R.drawable.messages));
        data.add(new ListItem("Number of Mood Entries: "+ StatsItem.getLength(),R.drawable.stats));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new MyAdapter(data,this::onClick));
            controller = NavHostFragment.findNavController(this);

        }
        return view;
    }
    public void onClick(int position) {
        selected = data.get(position);
        Bundle bundle = new Bundle();
        bundle.putParcelable("selected", selected);

        if(position == 0){
            controller.navigate(R.id.action_home2_to_infoFragment,bundle);
        }
        if(position == 1) {
            controller.navigate(R.id.action_home2_to_add2, bundle);
        }
        else if(position == 2){
            controller.navigate(R.id.action_home2_to_chat2, bundle);
        }
        else if(position == 3){
            controller.navigate(R.id.action_home2_to_stats2,bundle);
        }
    }
}
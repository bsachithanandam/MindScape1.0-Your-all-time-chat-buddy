package edu.sjsu.android.final_project;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class stats extends Fragment {

    ArrayList<StatsItem> data;
    private NavController controller;

    private  ListItem selected;

    public stats(){
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new ArrayList<>();
        data.add(new StatsItem("0"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        EntriesDB dbHelper = new EntriesDB(getActivity());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        Cursor cursor = db.query(EntriesDB.TABLE_NAME,null, null, null, null, null, null);
        String mood = "Hello";
        String date,month,year;
        if (cursor.moveToFirst()) {
        do{
            mood = cursor.getString(4);
            date = cursor.getString(1);
            month = cursor.getString(2);
            year = cursor.getString(3);
            data.add(new StatsItem(date,month,year,mood));
        }while(cursor.moveToNext());
        }
        if(data.size()==1){
            Toast.makeText(getActivity(), "No Moods recorded yet!", Toast.LENGTH_LONG).show();
        }
        else{
            data.set(0,new StatsItem(Integer.toString(data.size()-1)));
        }

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new MyStatsAdapter(data));
            controller = NavHostFragment.findNavController(this);

        }
        return view;
    }

}
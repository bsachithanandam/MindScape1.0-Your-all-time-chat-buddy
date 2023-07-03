package edu.sjsu.android.final_project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class personal_home extends Fragment {



    public personal_home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal_home, container, false);

        view.findViewById(R.id.erase_db).setOnClickListener(view1 -> {
            delete();
            Toast.makeText(getActivity(), "All Mood data Erased!", Toast.LENGTH_SHORT).show();
        });
        view.findViewById(R.id.uninstall).setOnClickListener(view1 -> {
            Intent delete = new Intent(Intent.ACTION_DELETE,
                    Uri.parse("package:" + getActivity().getPackageName()));
            startActivity(delete);
        });
        return view;
    }
    public void delete(){
        getActivity().getApplicationContext().getContentResolver().delete(EntriesProvider.CONTENT_URI,null);
    }
}
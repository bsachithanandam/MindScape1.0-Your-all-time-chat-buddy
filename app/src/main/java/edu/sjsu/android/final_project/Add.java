package edu.sjsu.android.final_project;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Date;

public class Add extends Fragment {

    public Add() {
        // Required empty public constructor
    }
    public String mood_message = "Completely Okay";
    public int image_resource_id = R.drawable.wow;
    Context applicationContext = MainActivity.getContextOfApplication();
    public void insert(String date, String Month,String year, String mood_message){
        System.out.println(mood_message);
        try{
            ContentValues values = new ContentValues();
            values.put(EntriesDB.DATE,date);
            values.put(EntriesDB.MONTH,Month);
            values.put(EntriesDB.YEAR,year);
            values.put(EntriesDB.MOOD,mood_message);
            getActivity().getApplicationContext().getContentResolver().insert(EntriesProvider.CONTENT_URI,values);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        final SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekbar);
        seekBar.setProgress(50);
        final TextView text_message = (TextView) view.findViewById(R.id.mood_text);
        final ImageView iv = (ImageView) view.findViewById(R.id.imageView);
        seekBar.setOnSeekBarChangeListener(
                        new SeekBar.OnSeekBarChangeListener() {

                            // When the progress value has changed
                            @Override
                            public void onProgressChanged(
                                    SeekBar seekBar,
                                    int progress,
                                    boolean fromUser)
                            {
                                if(progress >0 && progress <20){
                                    mood_message = "Terrible";
                                    image_resource_id = R.drawable.deadskin;
                                    text_message.setText(mood_message);
                                    iv.setImageResource(image_resource_id);
                                }
                                else if(progress >=20 && progress <40){
                                    mood_message = "A bit bad";
                                    image_resource_id = R.drawable.confusing;
                                    text_message.setText(mood_message);
                                    iv.setImageResource(image_resource_id);
                                }
                                else if(progress >=40 && progress <60){
                                    mood_message = "Completely okay";
                                    image_resource_id = R.drawable.wow;
                                    text_message.setText(mood_message);
                                    iv.setImageResource(image_resource_id);
                                }
                                else if(progress >=60 && progress <80){
                                    mood_message = "Pretty Good";
                                    image_resource_id = R.drawable.laughing;
                                    text_message.setText(mood_message);
                                    iv.setImageResource(image_resource_id);
                                }
                                else if(progress >=80 && progress <=100){
                                    mood_message = "Super Awesome";
                                    image_resource_id = R.drawable.cool;
                                    text_message.setText(mood_message);
                                    iv.setImageResource(image_resource_id);
                                }
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar)
                            {
                                text_message.setText(mood_message);
                                iv.setImageResource(image_resource_id);
                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar)
                            {
                                text_message.setText(mood_message);
                                iv.setImageResource(image_resource_id);

                            }
                        });

        Button complete_check_in = view.findViewById(R.id.button);
        complete_check_in.setOnClickListener(view1 -> {
            String str = new Date().toString();
            String[] splited = str.split("\\s+");
            insert(splited[2],splited[1],splited[5],mood_message);
        });
            return view;
    }


}
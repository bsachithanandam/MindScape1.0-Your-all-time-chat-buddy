package edu.sjsu.android.final_project;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class quotes extends Fragment {
    public static int count=0;
    Handler m_handler;
    Runnable m_handlerTask ;

    static String[] quotes_array = {"\nSuccess is not final; failure is not fatal: It is the courage to continue that counts.\"\n — Winston S. Churchill","\n“Everything around you that you call life was made up by people that were no smarter than you. And you can change it, you can influence it… Once you learn that, you'll never be the same again.”—Steve Jobs","\n“The secret of getting ahead is getting started.”\n—Mark Twain","“The same boiling water that softens the potato hardens the egg. It’s what you’re made of. Not the circumstances.” ","\nHakkuna Matata"};
    static String quote_single = quotes_array[1];
    public quotes() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       View view = inflater.inflate(R.layout.fragment_quotes, container, false);
       TextView quote = (TextView) view.findViewById(R.id.quote_text);
        m_handler = new Handler();
        m_handlerTask = new Runnable() {
        int i = 0,j =0;
            @Override
            public void run() {
                if(i<5)
                {
                    quote_single = quotes_array[i];
                    quote.setText(quote_single);
                    i++;
                }
                else
                {
                    i = 0;
                    m_handler.removeCallbacks(m_handlerTask);
                }
                m_handler.postDelayed(m_handlerTask, 5000);
            }
        };
        m_handlerTask.run();

        return view;
    }

}
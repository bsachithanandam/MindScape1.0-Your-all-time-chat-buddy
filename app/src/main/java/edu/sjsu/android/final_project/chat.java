package edu.sjsu.android.final_project;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class chat extends Fragment {


    public chat() {
        // Required empty public constructor
    }
    TextView responseTV;
    TextView questionTV;
    TextInputEditText queryEdt;
    String url = "https://api.openai.com/v1/completions";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        responseTV = view.findViewById(R.id.idTVResponse);
        questionTV = view.findViewById(R.id.idTVQuestion);
        queryEdt = view.findViewById(R.id.idEdtQuery);
        queryEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    // setting response tv on below line.
                    responseTV.setText("Please wait..");
                    // validating text
                    if (queryEdt.getText().toString().length() > 0) {
                        // calling get response to get the response.
                        getResponse(queryEdt.getText().toString());
                    } else {
                        Toast.makeText(requireContext(), "Please enter your query..", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            }
        });
        return view;
    }
    private void getResponse(String query) {
        // setting text on for question on below line.
        questionTV.setText(query);
        queryEdt.setText("");
        // creating a queue for request queue.
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        // creating a json object on below line.
        JSONObject jsonObject = new JSONObject();
        // adding params to json object.
        try {
            jsonObject.put("model", "text-davinci-003");
            jsonObject.put("prompt", query);
            jsonObject.put("temperature", 0);
            jsonObject.put("max_tokens", 100);
            jsonObject.put("top_p", 1);
            jsonObject.put("frequency_penalty", 0.0);
            jsonObject.put("presence_penalty", 0.0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // on below line making json object request.
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                response -> {
                    // on below line getting response message and setting it to text view.
                    String responseMsg = "";
                    try {
                        responseMsg = response.getJSONArray("choices").getJSONObject(0).getString("text");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    responseTV.setText(responseMsg);
                },
                // adding on error listener
                error -> Log.e("TAGAPI", "Error is : " + error.getMessage() + "\n" + error)
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                // adding headers on below line.
                params.put("Content-Type", "application/json");
                params.put("Authorization", "Bearer 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");
                return params;
            }
        };

        // on below line adding retry policy for our request.
        postRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {
            }
        });
        // on below line adding our request to queue.
        queue.add(postRequest);
    }
}

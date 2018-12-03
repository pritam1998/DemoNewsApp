package com.example.coder.demonewsapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinkedList<String> titleData = new LinkedList<>();
        LinkedList<String> descriptionData = new LinkedList<>();

        recyclerView = findViewById(R.id.recyclerView);
        textView = findViewById(R.id.text);

        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = null;

        adapter = new RecyclerAdapter(this, titleData, descriptionData);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        textView.setText("Fetched!");

    }

    @Override
    protected void onStart() {
        super.onStart();


        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected()) {
            new FetchNewsData(adapter).execute();
            textView.setText("Fetching...");
        } else {
            textView.setText("No network!");
        }

    }
}

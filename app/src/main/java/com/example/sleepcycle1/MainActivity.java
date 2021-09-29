package com.example.sleepcycle1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    // Lists String Data
    private ArrayList<String> mOptionsPrimary = new ArrayList<>();
    private ArrayList<String> mOptionsSecondary = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate started");

        initOptions();
    }

    private void initOptions() {
        Log.d(TAG, "initOptions started");

        mOptionsPrimary.add(getString(R.string.opt1));
        mOptionsSecondary.add(getString(R.string.opt1_1));

        mOptionsPrimary.add(getString(R.string.opt2));
        mOptionsSecondary.add(getString(R.string.opt2_1));

        mOptionsPrimary.add(getString(R.string.opt3));
        mOptionsSecondary.add(getString(R.string.opt3_1));

        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView started");

        RecyclerView recyclerView = findViewById(R.id.optionsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        OptionsAdapter adapter = new OptionsAdapter(mOptionsPrimary, mOptionsSecondary, this);
        recyclerView.setAdapter(adapter);

    }
}
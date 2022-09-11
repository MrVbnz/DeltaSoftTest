package ru.vbnz.deltasofttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Content extends AppCompatActivity {
    private CustomAdapter mCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        LoadContent();
    }

    private void LoadContent(){
        ArrayList<ContentRow> contentLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getAssets().open("muscles_eng.txt")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentLines.add(new ContentRow(line, this));
            }
        } catch (IOException ignored) {}
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mCustomAdapter = new CustomAdapter(contentLines);
        RecyclerView list = findViewById(R.id.itemList);
        list.setAdapter(mCustomAdapter);
        list.setLayoutManager(layoutManager);
    }
}
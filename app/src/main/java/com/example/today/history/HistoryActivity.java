package com.example.today.history;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.today.DatabaseHelper;
import com.example.today.R;
import com.example.today.apiclient.Task;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    Toolbar toolbar;
    DatabaseHelper dbh;
    ArrayList<Task> tasks;
    RecyclerView recyclerView;
    HistoryAdapter historyAdapter;
    Button btnDeleteAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setTitle("");
        tasks = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_history);
        dbh = new DatabaseHelper(this);
        btnDeleteAll = findViewById(R.id.btn_delete_history);
        //      Toolbar
        toolbar = findViewById(R.id.add_toolbar_history);
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
//        Read local database
        Cursor cursor = dbh.baca_data();
        while (cursor.moveToNext()){
            tasks.add(new Task(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2)));
        }
//        RecyclerView adapter
        historyAdapter = new HistoryAdapter(tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(historyAdapter);
//        deleta all items
        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbh.deleteData();
                tasks.clear();
                historyAdapter = new HistoryAdapter(tasks);
                recyclerView.setAdapter(historyAdapter);
            }
        });
    }
    //    Toolbar back action
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
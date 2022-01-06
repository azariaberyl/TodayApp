package com.example.today;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.today.apiclient.APIClient;
import com.example.today.apiclient.Task;
import com.example.today.apiclient.TaskInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText edtTitle,edtDesc;
    Button btnUpdate, btnDelete;
    TaskInterface taskInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle("");
        edtTitle= findViewById(R.id.edt_title_detail);
        edtDesc= findViewById(R.id.edt_desc_detail);
        btnUpdate = findViewById(R.id.btn_update_detail);
        btnDelete = findViewById(R.id.btn_delete_history);
        Bundle bundle = getIntent().getExtras();
        taskInterface = APIClient.getClient().create(TaskInterface.class);
        //      Toolbar
        toolbar = findViewById(R.id.add_toolbar_detail);
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
//        get intent
        String title = bundle.getString("title");
        String desc = bundle.getString("desc");
        int id = bundle.getInt("id");
        edtTitle.setText(title);
        edtDesc.setText(desc);
//        Delete data
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Task> deleteTask = taskInterface.deleteTask(id);
                deleteTask.enqueue(new Callback<Task>() {
                    @Override
                    public void onResponse(Call<Task> call, Response<Task> response) {
                        Log.d("list_tasks", response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<Task> call, Throwable t) {
                        Log.d("error_tasks", t.getMessage());
                    }
                });
                finish();
            }
        });
//        update data
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edtTitle.getText().toString();
                String desc = edtDesc.getText().toString();
                Call<Task> updateTask = taskInterface.updateTask(title,desc,id);
                updateTask.enqueue(new Callback<Task>() {
                    @Override
                    public void onResponse(Call<Task> call, Response<Task> response) {
                        Log.d("list_tasks", response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<Task> call, Throwable t) {
                        Log.d("error_tasks", t.getMessage());
                    }
                });
                finish();
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
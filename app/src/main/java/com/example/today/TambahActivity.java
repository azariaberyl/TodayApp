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
import android.widget.Toast;

import com.example.today.apiclient.APIClient;
import com.example.today.apiclient.Task;
import com.example.today.apiclient.TaskInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText edtTitle, edtDesc;
    Button btnSave;
    TaskInterface anInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        setTitle("Tambah Aktivitas");
        edtTitle = findViewById(R.id.edt_title_detail);
        edtDesc = findViewById(R.id.edt_desc_detail);
        btnSave = findViewById(R.id.btn_update_detail);
        anInterface = APIClient.getClient().create(TaskInterface.class);

//      Toolbar
        toolbar = findViewById(R.id.add_toolbar_detail);
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
//      On save click
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edtTitle.getText().toString();
                String desc = edtDesc.getText().toString();
                Call<Task> postTask =  anInterface.postTask(title,desc);

                postTask.enqueue(new Callback<Task>() {
                    @Override
                    public void onResponse(Call<Task> call, Response<Task> response) {
                        Log.d("list_tasks", response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<Task> call, Throwable t) {
                        Log.d("error_tasks", t.getMessage());
                    }
                });
                Toast.makeText(v.getContext(), "Task has been added", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
//        Accept bundle
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            edtTitle.setText(bundle.getString("title"));
            edtDesc.setText(bundle.getString("desc"));
        }
    }
//  Back toolbar button
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
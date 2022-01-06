package com.example.today;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.today.apiclient.APIClient;
import com.example.today.apiclient.Task;
import com.example.today.apiclient.TaskInterface;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TaskInterface taskInterface;
    private RecyclerView recyclerView;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view1,Bundle savedInstanceState) {
        super.onViewCreated(view1, savedInstanceState);
        AppCompatActivity activity = (AppCompatActivity) view1.getContext();
        activity.setTitle("Tasks");
        ExtendedFloatingActionButton floatingActionButton = view1.findViewById(R.id.btn_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view1.getContext(), TambahActivity.class);
                startActivity(intent);
//                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
//                ft.detach(MainFragment.this).attach(MainFragment.this).commit();
            }
        });

        //       Get data from server
        recyclerView = view1.findViewById(R.id.rv_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(view1.getContext()));
        taskInterface = APIClient.getClient().create(TaskInterface.class);
        getDatas();
    }
    private void getDatas() {
        Call<List<Task>> getTasks = taskInterface.getTasks();
        getTasks.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                ArrayList<Task> tasks = (ArrayList<Task>) response.body();
//                Log.d("list_tasks", tasks.toString());
                RecyclerviewAdapter adapter = new RecyclerviewAdapter(tasks);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
//                Log.d("error_tasks", t.getMessage());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getDatas();
    }
}


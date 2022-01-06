package com.example.today;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.today.apiclient.APIClient;
import com.example.today.apiclient.Task;
import com.example.today.apiclient.TaskInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {

    ArrayList<Task> arrayList;
    DatabaseHelper databaseHelper;
    TaskInterface taskInterface;

    public RecyclerviewAdapter(ArrayList<Task> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewHolder viewHolder = new ViewHolder(inflater.inflate(R.layout.item_main_rvadapter, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewAdapter.ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        Task task = arrayList.get(position);
        holder.tvJudul.setText(task.getTitle());
        holder.tvDesc.setText(task.getDesc());
        databaseHelper = new DatabaseHelper(context);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("title", task.getTitle());
                intent.putExtra("desc", task.getDesc());
                intent.putExtra("id", task.getId());
                context.startActivity(intent);
            }
        });
        holder.cbItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(holder.cbItem.isChecked()){
                    holder.tvJudul.setPaintFlags(holder.tvJudul.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.tvDesc.setPaintFlags(holder.tvDesc.getPaintFlags()| ( Paint.STRIKE_THRU_TEXT_FLAG));
                    holder.cbItem.setEnabled(false);
                }

                databaseHelper.tambah_data(Integer.toString(task.getId()),task.getTitle(),task.getDesc());
                taskInterface = APIClient.getClient().create(TaskInterface.class);
                Call<Task> deleteTask = taskInterface.deleteTask(task.getId());
                deleteTask.enqueue(new Callback<Task>() {
                    @Override
                    public void onResponse(Call<Task> call, Response<Task> response) {
                        Log.d("deleteadapter",""+position+response.body());
                    }

                    @Override
                    public void onFailure(Call<Task> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul, tvDesc;
        CheckBox cbItem;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View v) {
            super(v);
            relativeLayout = v.findViewById(R.id.item_container);
            tvJudul = v.findViewById(R.id.tv_item_judul_history);
            tvDesc = v.findViewById(R.id.tv_item_desc_history);
            cbItem = v.findViewById(R.id.cb_item);
        }
    }
}

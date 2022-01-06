package com.example.today.recommendation;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.today.R;
import com.example.today.TambahActivity;
import com.example.today.apiclient.Task;

import java.util.ArrayList;

public class RecommendationAdapter extends RecyclerView.Adapter<RecommendationAdapter.ViewHolder> {
    ArrayList<Task> suggetionsTask;

    public RecommendationAdapter(ArrayList<Task> suggetionsTask) {
        this.suggetionsTask = suggetionsTask;
    }

    @NonNull
    @Override
    public RecommendationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewHolder viewHolder = new ViewHolder(inflater.inflate(R.layout.item_history_rvadapter, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendationAdapter.ViewHolder holder, int position) {
        Task task = suggetionsTask.get(position);
        Context context = holder.itemView.getContext();
        holder.tvJudul.setText(task.getTitle());
        holder.tvDesc.setText(task.getDesc());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TambahActivity.class);
                intent.putExtra("title",task.getTitle());
                intent.putExtra("desc",task.getDesc());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return suggetionsTask.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul,tvDesc;
        ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.contraintlayout_history);
            tvDesc = itemView.findViewById(R.id.tv_item_desc_history);
            tvJudul = itemView.findViewById(R.id.tv_item_judul_history);
        }
    }

}

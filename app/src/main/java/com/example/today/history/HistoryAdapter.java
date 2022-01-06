package com.example.today.history;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.today.R;
import com.example.today.apiclient.Task;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    ArrayList<Task> tasks;

    public HistoryAdapter(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewHolder viewHolder = new ViewHolder(inflater.inflate(R.layout.item_history_rvadapter, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        Task task = tasks.get(position);
        Context context = holder.itemView.getContext();
        holder.tvJudul.setText(task.getTitle());
        holder.tvDesc.setText(task.getDesc());
        holder.tvJudul.setPaintFlags(holder.tvJudul.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        holder.tvDesc.setPaintFlags(holder.tvDesc.getPaintFlags()| ( Paint.STRIKE_THRU_TEXT_FLAG));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul,tvDesc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDesc = itemView.findViewById(R.id.tv_item_desc_history);
            tvJudul = itemView.findViewById(R.id.tv_item_judul_history);
        }
    }

}

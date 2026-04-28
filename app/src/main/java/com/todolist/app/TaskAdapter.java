package com.todolist.app;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    public interface OnTaskActionListener {
        void onDelete(int id, int position);
        void onToggle(int id, boolean checked, int position);
    }

    private List<Task> tasks;
    private OnTaskActionListener listener;

    public TaskAdapter(List<Task> tasks, OnTaskActionListener listener) {
        this.tasks = tasks;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.cbDone.setChecked(task.isCompleted());
        holder.tvTitle.setText(task.getTitle());
        holder.tvPriority.setText(task.getPriority());

        switch (task.getPriority()) {
            case "Tinggi":
                holder.tvPriority.setTextColor(0xFFE53935);
                break;
            case "Sedang":
                holder.tvPriority.setTextColor(0xFFFB8C00);
                break;
            default:
                holder.tvPriority.setTextColor(0xFF43A047);
                break;
        }
      
        if (task.isCompleted()) {
            holder.tvTitle.setPaintFlags(
                holder.tvTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.tvTitle.setPaintFlags(
                holder.tvTitle.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        }

        holder.cbDone.setOnClickListener(v -> {
            listener.onToggle(task.getId(), holder.cbDone.isChecked(), position);
        });

        holder.btnDelete.setOnClickListener(v -> {
            listener.onDelete(task.getId(), position);
        });
    }

    @Override
    public int getItemCount() { return tasks.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox cbDone;
        TextView tvTitle, tvPriority;
        ImageButton btnDelete;

        ViewHolder(View itemView) {
            super(itemView);
            cbDone = itemView.findViewById(R.id.cbDone);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPriority = itemView.findViewById(R.id.tvPriority);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}

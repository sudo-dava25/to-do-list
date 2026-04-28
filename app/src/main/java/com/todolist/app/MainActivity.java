package com.todolist.app;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private TaskAdapter adapter;
    private List<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        tasks = db.getAllTasks();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new TaskAdapter(tasks, new TaskAdapter.OnTaskActionListener() {
            @Override
            public void onDelete(int id, int position) {
                db.deleteTask(id);
                tasks.remove(position);
                adapter.notifyItemRemoved(position);
            }

            @Override
            public void onToggle(int id, boolean checked, int position) {
                db.updateCompleted(id, checked);
                tasks.get(position).setCompleted(checked);
                adapter.notifyItemChanged(position);
            }
        });

        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> showAddDialog());
    }

    private void showAddDialog() {
        View dialogView = LayoutInflater.from(this)
            .inflate(R.layout.dialog_add_task, null);
        EditText etTitle = dialogView.findViewById(R.id.etTitle);
        Spinner spinnerPriority = dialogView.findViewById(R.id.spinnerPriority);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
            android.R.layout.simple_spinner_item,
            new String[]{"Tinggi", "Sedang", "Rendah"});
        spinnerAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item);
        spinnerPriority.setAdapter(spinnerAdapter);

        new AlertDialog.Builder(this)
            .setTitle("Tambah Tugas")
            .setView(dialogView)
            .setPositiveButton("Tambah", (dialog, which) -> {
                String title = etTitle.getText().toString().trim();
                String priority = spinnerPriority.getSelectedItem().toString();
                if (!title.isEmpty()) {
                    db.addTask(title, priority);
                    tasks.clear();
                    tasks.addAll(db.getAllTasks());
                    adapter.notifyDataSetChanged();
                }
            })
            .setNegativeButton("Batal", null)
            .show();
    }
}

package com.example.to_do_list;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class TodoFragment extends Fragment {

    private ListView listView;
    private TextView noTasksTextView; // Reference to "No Tasks" message
    private ArrayAdapter<String> adapter;
    private List<TodoTask> todoTaskList = new ArrayList<>();
    private List<String> taskTitles = new ArrayList<>(); // Task titles for the ListView

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, container, false);

        listView = view.findViewById(R.id.list_view_todo);
        noTasksTextView = view.findViewById(R.id.tv_no_tasks); // "No Tasks" message

        // Initialize ArrayAdapter
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, taskTitles);
        listView.setAdapter(adapter);

        // Show "No Tasks" message if the list is empty
        checkTaskListVisibility();

        // Handle task clicks
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            TodoTask selectedTask = todoTaskList.get(position);
            if (getActivity() instanceof OnTaskClickListener) {
                ((OnTaskClickListener) getActivity()).onTaskClick(selectedTask);
            }
        });

        // Floating Action Button click listener
        FloatingActionButton fab = view.findViewById(R.id.fab_add_task);
        fab.setOnClickListener(v -> {
            if (getActivity() instanceof OnTaskClickListener) {
                ((OnTaskClickListener) getActivity()).onAddTaskClick();
            }
        });

        return view;
    }

    // Method to add a task to the list and update UI
    public void addTask(TodoTask task) {
        todoTaskList.add(task);
        taskTitles.add(task.getTitle());
        adapter.notifyDataSetChanged(); // Refresh the ListView
        checkTaskListVisibility(); // Hide "No Tasks" message if a task is added
    }

    // Check if task list is empty and toggle visibility of the "No Tasks" message
    private void checkTaskListVisibility() {
        if (todoTaskList.isEmpty()) {
            listView.setVisibility(View.GONE);
            noTasksTextView.setVisibility(View.VISIBLE); // Show "No Tasks" message
        } else {
            listView.setVisibility(View.VISIBLE); // Show the list
            noTasksTextView.setVisibility(View.GONE); // Hide "No Tasks" message
        }
    }

    public interface OnTaskClickListener {
        void onTaskClick(TodoTask selectedTask);

        void onAddTaskClick();
    }
}

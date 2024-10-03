package com.example.to_do_list;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements TodoFragment.OnTaskClickListener, TaskInputFragment.OnTaskAddedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new TodoFragment())
                    .commit();
        }
    }

    @Override
    public void onTaskClick(TodoTask task) {
        // Open TaskDetailFragment to show task details
        TaskDetailFragment detailFragment = TaskDetailFragment.newInstance(task);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onAddTaskClick() {
        // Open TaskInputFragment to add a new task
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new TaskInputFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onTaskAdded(TodoTask task) {
        // Go back to TodoFragment after task is added
        getSupportFragmentManager().popBackStack();

        // Find the existing TodoFragment and add the new task to it
        TodoFragment todoFragment = (TodoFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (todoFragment instanceof TodoFragment) {
            todoFragment.addTask(task); // Add the new task to the list
        }
    }
}


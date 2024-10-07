package com.example.to_do_list;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity implements TodoFragment.OnTaskClickListener, TaskInputFragment.OnTaskAddedListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check for orientation and load fragments accordingly
        if (savedInstanceState == null) {
            if (isLandscape()) {
                // Load both TodoFragment and an empty TaskDetailFragment in landscape mode
                replaceFragment(new TodoFragment(), false, "TODO_FRAGMENT", R.id.todo_fragment_container);
                replaceFragment(new TaskDetailFragment(), false, "DETAIL_FRAGMENT", R.id.detail_fragment_container);
            } else {
                // Load only TodoFragment in portrait mode
                replaceFragment(new TodoFragment(), false, "TODO_FRAGMENT", R.id.todo_fragment_container);
            }
        }
    }

    // Method to check if the device is in landscape mode
    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    // Called when a task is clicked in the TodoFragment
    @Override


    public void onTaskClick(TodoTask task) {
        Log.d(TAG, "onTaskClick: Task clicked - " + task.getTitle());

        TaskDetailFragment detailFragment = TaskDetailFragment.newInstance(task.getTitle(), task.getDescription());

        View detailFragmentContainer = findViewById(R.id.detail_fragment_container);

        if (detailFragmentContainer != null) {
            Log.d(TAG, "onTaskClick: Detail fragment container found.");

            if (detailFragmentContainer.getVisibility() == View.GONE) {
                Log.d(TAG, "onTaskClick: Switching to detail view in portrait mode.");
                findViewById(R.id.todo_fragment_container).setVisibility(View.GONE);
                detailFragmentContainer.setVisibility(View.VISIBLE);
            }

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, detailFragment, "DETAIL_FRAGMENT")
                    .addToBackStack(null)
                    .commit();
        } else {
            Log.d(TAG, "onTaskClick: Detail fragment container not found, falling back to todo fragment container.");
            replaceFragment(detailFragment, true, "DETAIL_FRAGMENT", R.id.todo_fragment_container);
        }
    }





    // Called when the Floating Action Button is clicked in TodoFragment to add a new task
    @Override
    public void onAddTaskClick() {
        replaceFragment(new TaskInputFragment(), true, "TASK_INPUT_FRAGMENT", R.id.todo_fragment_container);
    }

    // Called when a new task is added in the TaskInputFragment
    @Override
    public void onTaskAdded(TodoTask task) {
        getSupportFragmentManager().popBackStack();  // Go back to TodoFragment

        TodoFragment todoFragment = (TodoFragment) getSupportFragmentManager().findFragmentByTag("TODO_FRAGMENT");
        if (todoFragment != null) {
            todoFragment.addTask(task); // Add task to the TodoFragment's list
        }
    }

    // Helper method to replace fragments in the specified container
    private void replaceFragment(Fragment fragment, boolean addToBackStack, String tag, int containerId) {
        if (addToBackStack) {
            getSupportFragmentManager().beginTransaction()
                    .replace(containerId, fragment, tag)
                    .addToBackStack(null)
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(containerId, fragment, tag)
                    .commit();
        }
    }
}

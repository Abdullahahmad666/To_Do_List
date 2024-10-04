package com.example.to_do_list;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity implements TodoFragment.OnTaskClickListener, TaskInputFragment.OnTaskAddedListener {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            replaceFragment(new TodoFragment(), false);
        }

        Log.d(TAG, "onCreate: ");


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onTaskClick(TodoTask task) {
        // Placeholder for task details logic (if needed)
    }

    @Override
    public void onAddTaskClick() {
        replaceFragment(new TaskInputFragment(), true);
    }

    @Override

    public void onTaskAdded(TodoTask task) {
        getSupportFragmentManager().popBackStack();  // Go back to TodoFragment

        TodoFragment todoFragment = (TodoFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (todoFragment != null) {
            todoFragment.addTask(task); // Add task to the TodoFragment's list
        }
    }


    private void replaceFragment(Fragment fragment, boolean addToBackStack) {
        if (addToBackStack) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}

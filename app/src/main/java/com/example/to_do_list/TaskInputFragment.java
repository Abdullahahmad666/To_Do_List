package com.example.to_do_list;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class TaskInputFragment extends Fragment {

    private static final String TAG = "TaskInputFragment";
    private EditText titleEditText;
    private EditText descriptionEditText;

    // Interface to communicate with the parent activity
    public interface OnTaskAddedListener {
        void onTaskAdded(TodoTask task);
    }

    private OnTaskAddedListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnTaskAddedListener) {
            listener = (OnTaskAddedListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnTaskAddedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_input, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize UI elements
        titleEditText = view.findViewById(R.id.edit_text_title);
        descriptionEditText = view.findViewById(R.id.edit_text_description);
        Button saveButton = view.findViewById(R.id.btn_save);

        // Save button listener
        saveButton.setOnClickListener(v -> {
            String title = titleEditText.getText().toString().trim();
            String description = descriptionEditText.getText().toString().trim();

            // Validate input
            if (title.isEmpty()) {
                titleEditText.setError("Title is required");
                return;
            }

            // Create a new TodoTask
            TodoTask newTask = new TodoTask(title, description);

            // Notify the activity to add the task to the task list
            if (listener != null) {
                listener.onTaskAdded(newTask);
            }

            // Remove this fragment (go back to the TodoFragment)
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        Log.d(TAG, "onViewCreated: TaskInputFragment is created");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: TaskInputFragment started");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: TaskInputFragment resumed");
    }
}

package com.example.to_do_list;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class TaskInputFragment extends Fragment {

    private EditText titleEditText;
    private EditText descriptionEditText;

    public interface OnTaskAddedListener {
        void onTaskAdded(TodoTask task);
    }

    private OnTaskAddedListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTaskAddedListener) {
            listener = (OnTaskAddedListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnTaskAddedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_input, container, false);

        titleEditText = view.findViewById(R.id.et_task_title);
        descriptionEditText = view.findViewById(R.id.et_task_description);
        Button saveButton = view.findViewById(R.id.btn_save_task);

        saveButton.setOnClickListener(v -> {
            String title = titleEditText.getText().toString();
            String description = descriptionEditText.getText().toString();

            if (title.isEmpty()) {
                titleEditText.setError("Title is required");
                return;
            }

            // Create a new TodoTask and pass it to the listener (MainActivity)
            TodoTask newTask = new TodoTask(title, description);
            listener.onTaskAdded(newTask); // Send the task back to MainActivity
        });

        return view;
    }
}

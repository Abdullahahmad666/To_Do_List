package com.example.to_do_list;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.to_do_list.R;
import com.example.to_do_list.TodoTask;

public class TaskInputFragment extends Fragment {

    private static final String TAG = "TaskInputFragment";
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

        titleEditText = view.findViewById(R.id.edit_text_title);
        descriptionEditText = view.findViewById(R.id.edit_text_description);
        Button saveButton = view.findViewById(R.id.btn_save);

        saveButton.setOnClickListener(v -> {
            String title = titleEditText.getText().toString().trim();
            String description = descriptionEditText.getText().toString().trim();

            if (title.isEmpty()) {
                titleEditText.setError("Title is required");
                return;
            }

            TodoTask newTask = new TodoTask(title, description);

            if (listener != null) {
                listener.onTaskAdded(newTask);
            }
        });

        Log.d(TAG, "onCreateView: ");

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }
}

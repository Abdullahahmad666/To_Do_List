package com.example.to_do_list;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TaskDetailFragment extends Fragment {

    private static final String ARG_TITLE = "task_title";
    private static final String ARG_DESCRIPTION = "task_description";

    private String taskTitle;
    private String taskDescription;

    // Create a newInstance method to pass task data to this fragment
    public static TaskDetailFragment newInstance(String title, String description) {
        TaskDetailFragment fragment = new TaskDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_DESCRIPTION, description);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            taskTitle = getArguments().getString(ARG_TITLE);
            taskDescription = getArguments().getString(ARG_DESCRIPTION);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_detail, container, false);

        TextView titleTextView = view.findViewById(R.id.text_view_task_title);
        TextView descriptionTextView = view.findViewById(R.id.text_view_task_description);

        // Set the task title and description in the TextViews
        titleTextView.setText(taskTitle);
        descriptionTextView.setText(taskDescription);

        return view;
    }
}

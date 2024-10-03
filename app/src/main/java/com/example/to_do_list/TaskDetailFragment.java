package com.example.to_do_list;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


    public class TaskDetailFragment extends Fragment {

        private static final String ARG_TASK = "task";
        private TodoTask task;

        public static TaskDetailFragment newInstance(TodoTask task) {
            TaskDetailFragment fragment = new TaskDetailFragment();
            Bundle args = new Bundle();
            args.putSerializable(ARG_TASK, task);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                task = (TodoTask) getArguments().getSerializable(ARG_TASK);
            }
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_task_detail, container, false);

            TextView titleTextView = view.findViewById(R.id.tv_task_title);
            TextView descriptionTextView = view.findViewById(R.id.tv_task_description);

            titleTextView.setText(task.getTitle());
            descriptionTextView.setText(task.getDescription());

            return view;
        }
    }


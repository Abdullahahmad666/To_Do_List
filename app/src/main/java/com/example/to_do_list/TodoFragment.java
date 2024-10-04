package com.example.to_do_list;

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
    private TextView noTasksTextView;
    private ArrayAdapter<String> adapter;
    private List<TodoTask> todoTaskList = new ArrayList<>();
    private List<String> taskTitles = new ArrayList<>();

    public interface OnTaskClickListener {
        void onTaskClick(TodoTask selectedTask);
        void onAddTaskClick();
    }

    @Nullable

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, container, false);

        listView = view.findViewById(R.id.list_view_todo);
        noTasksTextView = view.findViewById(R.id.tv_no_tasks);

        // Initialize ArrayAdapter
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, taskTitles);
        listView.setAdapter(adapter);

        // Remove the hardcoded task
        // TodoTask hardcodedTask = new TodoTask("Buy Groceries", "Milk, Eggs, Bread");
        // addTask(hardcodedTask);

        // Show "No Tasks" message if the list is empty
        checkTaskListVisibility();

        // Handle task clicks
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            TodoTask selectedTask = todoTaskList.get(position);
            openTaskDetailFragment(selectedTask); // Open TaskDetailFragment when a task is clicked
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
        adapter.notifyDataSetChanged();
        checkTaskListVisibility();
    }

    // Method to open TaskDetailFragment
    private void openTaskDetailFragment(TodoTask task) {
        TaskDetailFragment detailFragment = TaskDetailFragment.newInstance(task.getTitle(), task.getDescription());

        getActivity().getSupportFragmentManager().beginTransaction()
                .hide(this) // Hide the current instance of TodoFragment
                .add(R.id.fragment_container, detailFragment) // Add the TaskDetailFragment on top
                .addToBackStack(null) // Add the transaction to the back stack so the user can navigate back
                .commit();
    }


    // Check if task list is empty and toggle visibility of the "No Tasks" message
    private void checkTaskListVisibility() {
        if (todoTaskList.isEmpty()) {
            listView.setVisibility(View.GONE);
            noTasksTextView.setVisibility(View.VISIBLE);
        } else {
            listView.setVisibility(View.VISIBLE);
            noTasksTextView.setVisibility(View.GONE);
        }
    }
}

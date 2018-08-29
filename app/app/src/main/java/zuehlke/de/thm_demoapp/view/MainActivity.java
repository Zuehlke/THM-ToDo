package zuehlke.de.thm_demoapp.view;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import zuehlke.de.thm_demoapp.Controller.TodoManager;
import zuehlke.de.thm_demoapp.util.DependencyContainer;
import zuehlke.de.thm_demoapp.R;
import zuehlke.de.thm_demoapp.model.Todo;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    TodoManager todoManager;
    RecyclerView todoList;
    TodoListAdapter todoListAdapter;
    Button addTodoButton;
    TextView openTodos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoManager = DependencyContainer.get(TodoManager.class);

        todoListAdapter = new TodoListAdapter(todoManager.getTodos(), item -> {
            item.toggleDone();
            updateView();
        });

        todoList = findViewById(R.id.rv_todo_list);
        todoList.setLayoutManager(new LinearLayoutManager(this));
        todoList.setAdapter(todoListAdapter);

        addTodoButton = findViewById(R.id.btn_add_todo);
        addTodoButton.setOnClickListener(v -> showAddTodoAlert());

        openTodos = findViewById(R.id.txt_open_todos);

        updateView();
    }

    private void showAddTodoAlert() {
        View alertView = getLayoutInflater().inflate(R.layout.add_todo_dialog, null);
        EditText input = alertView.findViewById(R.id.et_todo_title);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Add Todo");
        alert.setView(alertView);
        alert.setPositiveButton(R.string.btn_ok, (dialog, whichButton) -> {
            Todo todo = new Todo(input.getText().toString());
            todoManager.addTodo(todo);
            updateView();
        });
        alert.setNegativeButton(R.string.btn_cancel, (dialog, whichButton) -> {
            dialog.cancel();
        });
        alert.show();
    }

    private void updateView() {
        todoListAdapter.notifyDataSetChanged();
        updateOpenTodoCount();
    }

    private void updateOpenTodoCount() {
        openTodos.setText(getString(R.string.open_todos, todoManager.getOpenTodos().size()));
    }
}

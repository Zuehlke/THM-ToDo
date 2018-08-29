package zuehlke.de.thm_demoapp.Controller;

import java.util.ArrayList;
import java.util.List;

import zuehlke.de.thm_demoapp.model.Todo;

public class TodoRepositoryImpl implements TodoRepository {

    private List<Todo> todos;

    public TodoRepositoryImpl(){
        todos = new ArrayList<>();
    }

    @Override
    public void addTodo(Todo todo) {
        todos.add(todo);
    }

    @Override
    public List<Todo> getTodos() {
        return todos;
    }

    public void removeTodo(Todo todo) {
        if (!todos.contains(todo)) {
            throw new IllegalArgumentException("Unknown Todo has been passed");
        }
        todos.remove(todo);
    }
}

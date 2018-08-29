package zuehlke.de.thm_demoapp.Controller;

import java.util.List;

import zuehlke.de.thm_demoapp.model.Todo;

public interface TodoManager {

    void addTodo(Todo todo);

    List<Todo> getTodos();

    List<Todo> getOpenTodos();
}

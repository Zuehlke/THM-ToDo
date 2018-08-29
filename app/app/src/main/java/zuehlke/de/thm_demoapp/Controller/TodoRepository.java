package zuehlke.de.thm_demoapp.Controller;

import java.util.List;

import zuehlke.de.thm_demoapp.model.Todo;

public interface TodoRepository {

    void addTodo(Todo todo);

    List<Todo> getTodos();

    void removeTodo(Todo todo);
}

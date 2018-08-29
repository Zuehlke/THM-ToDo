package zuehlke.de.thm_demoapp.Controller;

import java.util.ArrayList;
import java.util.List;

import zuehlke.de.thm_demoapp.model.Todo;

public class TodoManagerImpl implements TodoManager {

    private TodoRepository todoRepository;

    public TodoManagerImpl(TodoRepository repository){
        this.todoRepository = repository;
    }

    @Override
    public void addTodo(Todo todo) {
        this.todoRepository.addTodo(todo);
    }

    @Override
    public List<Todo> getTodos() {
        return this.todoRepository.getTodos();
    }

    @Override
    public List<Todo> getOpenTodos() {
        List<Todo> openTodos = new ArrayList<>();
        for (Todo todo : getTodos()) {
            if (!todo.isDone()){
                openTodos.add(todo);
            }
        }
        return openTodos;
    }
}

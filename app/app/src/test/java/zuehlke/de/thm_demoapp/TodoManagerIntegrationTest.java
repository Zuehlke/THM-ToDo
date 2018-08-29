package zuehlke.de.thm_demoapp;

import org.junit.Test;

import zuehlke.de.thm_demoapp.Controller.TodoManager;
import zuehlke.de.thm_demoapp.Controller.TodoManagerImpl;
import zuehlke.de.thm_demoapp.Controller.TodoRepository;
import zuehlke.de.thm_demoapp.Controller.TodoRepositoryImpl;
import zuehlke.de.thm_demoapp.model.Todo;

import static junit.framework.Assert.assertEquals;

public class TodoManagerIntegrationTest {

    @Test
    public void testTodoManager() {
        TodoRepository repository = new TodoRepositoryImpl();
        TodoManager todoManager = new TodoManagerImpl(repository);

        assertEquals(0, todoManager.getTodos().size());
        assertEquals(0, todoManager.getOpenTodos().size());

        Todo todo1 = new Todo("Todo 1");
        todoManager.addTodo(todo1);

        assertEquals(1, todoManager.getTodos().size());
        assertEquals(1, todoManager.getOpenTodos().size());

        todo1.toggleDone();

        assertEquals(1, todoManager.getTodos().size());
        assertEquals(0, todoManager.getOpenTodos().size());
    }
}

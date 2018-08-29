package zuehlke.de.thm_demoapp;

import org.junit.Test;

import zuehlke.de.thm_demoapp.Controller.TodoRepository;
import zuehlke.de.thm_demoapp.Controller.TodoRepositoryImpl;
import zuehlke.de.thm_demoapp.model.Todo;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class TodoRepositoryUnitTest {

    @Test
    public void testTodoRepository(){
        TodoRepository repository = new TodoRepositoryImpl();

        assertEquals(0, repository.getTodos().size());

        Todo todo1 = mock(Todo.class);
        repository.addTodo(todo1);
        assertEquals(1, repository.getTodos().size());
        assertEquals(todo1, repository.getTodos().get(0));

        Todo todo2 = mock(Todo.class);
        repository.addTodo(todo2);
        assertEquals(2, repository.getTodos().size());
        assertEquals(todo2, repository.getTodos().get(1));

        repository.removeTodo(todo2);
        assertEquals(1, repository.getTodos().size());
        assertEquals(todo1, repository.getTodos().get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemovingUnknownTodoWillThrow() {
        TodoRepository repository = new TodoRepositoryImpl();

        Todo todo = mock(Todo.class);

        repository.removeTodo(todo);
    }
}

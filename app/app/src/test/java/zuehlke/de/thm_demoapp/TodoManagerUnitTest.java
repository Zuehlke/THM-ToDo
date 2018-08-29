package zuehlke.de.thm_demoapp;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import zuehlke.de.thm_demoapp.Controller.TodoManager;
import zuehlke.de.thm_demoapp.Controller.TodoManagerImpl;
import zuehlke.de.thm_demoapp.Controller.TodoRepository;
import zuehlke.de.thm_demoapp.model.Todo;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TodoManagerUnitTest {

    private List<Todo> testDataSet = new ArrayList<>();

    @Before
    public void prepareData() {
        testDataSet.clear();
    }

    @Test
    public void testGetTodos() {
        Todo todo1 = mock(Todo.class);
        Todo todo2 = mock(Todo.class);

        testDataSet.add(todo1);
        testDataSet.add(todo2);

        TodoRepository todoRepositoryMock = mock(TodoRepository.class);
        when(todoRepositoryMock.getTodos()).thenReturn(testDataSet);

        TodoManager todoManager = new TodoManagerImpl(todoRepositoryMock);

        assertEquals(2, todoManager.getTodos().size());
        assertEquals(todo1, todoManager.getTodos().get(0));
        assertEquals(todo2, todoManager.getTodos().get(1));
    }

    @Test
    public void testAddTodos() {
        TodoRepository todoRepositoryMock = mock(TodoRepository.class);
        TodoManager todoManager = new TodoManagerImpl(todoRepositoryMock);

        Todo todo1 = mock(Todo.class);
        todoManager.addTodo(todo1);
        Todo todo2 = mock(Todo.class);
        todoManager.addTodo(todo2);

        verify(todoRepositoryMock, times(1)).addTodo(todo1);
        verify(todoRepositoryMock, times(2)).addTodo(any(Todo.class));
    }

    @Test
    public void testGetOpenTodos() {
        Todo todo1 = mock(Todo.class);
        when(todo1.isDone()).thenReturn(true);

        Todo todo2 = mock(Todo.class);
        when(todo2.isDone()).thenReturn(false);

        testDataSet.add(todo1);
        testDataSet.add(todo2);

        TodoRepository todoRepositoryMock = mock(TodoRepository.class);
        when(todoRepositoryMock.getTodos()).thenReturn(testDataSet);

        TodoManager todoManager = new TodoManagerImpl(todoRepositoryMock);

        assertEquals(1, todoManager.getOpenTodos().size());
        assertEquals(todo2, todoManager.getOpenTodos().get(0));
    }

}

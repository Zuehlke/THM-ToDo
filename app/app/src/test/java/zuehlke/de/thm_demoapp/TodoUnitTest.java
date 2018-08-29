package zuehlke.de.thm_demoapp;

import org.junit.Test;

import zuehlke.de.thm_demoapp.model.Todo;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class TodoUnitTest {

    @Test
    public void testTodo() {
        Todo t = new Todo("Test");

        assertEquals("Test", t.getTitle());
    }

    @Test
    public void testTodoDoneToggle() {
        Todo t = new Todo("Test");
        assertFalse(t.isDone());

        t.toggleDone();
        assertTrue(t.isDone());

        t.toggleDone();
        assertFalse(t.isDone());
    }
}

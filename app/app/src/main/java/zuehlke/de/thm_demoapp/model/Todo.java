package zuehlke.de.thm_demoapp.model;

import java.util.Objects;

public class Todo {

    private final String title;
    private boolean done;

    public Todo(String title) {
        this.title = title;
        this.done = false;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return done;
    }

    public void toggleDone() {
        this.done = !done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return done == todo.done &&
                Objects.equals(title, todo.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, done);
    }
}

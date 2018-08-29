package zuehlke.de.thm_demoapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import zuehlke.de.thm_demoapp.Controller.TodoManager;
import zuehlke.de.thm_demoapp.Controller.TodoManagerImpl;
import zuehlke.de.thm_demoapp.Controller.TodoRepository;
import zuehlke.de.thm_demoapp.model.Todo;
import zuehlke.de.thm_demoapp.util.DependencyContainer;
import zuehlke.de.thm_demoapp.view.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class MainActivityUiTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class, false, false);
    private Context context = InstrumentationRegistry.getTargetContext();

    @Test
    public void testAddingTodos() {
        activityRule.launchActivity(null);

        onView(withId(R.id.txt_open_todos))
                .check(matches(isDisplayed()))
                .check(matches(withText(context.getString(R.string.open_todos, 0))));

        onView(withId(R.id.btn_add_todo))
                .check(matches(isDisplayed()))
                .check(matches(withText(context.getString(R.string.btn_add_todo))))
                .perform(click());

        onView(withId(R.id.alertTitle))
                .inRoot(isDialog())
                .check(matches(withText("Add Todo")));

        onView(withId(R.id.et_todo_title))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(typeText("Android Aufgaben erledigen"));

        onView(withText(R.string.btn_ok))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.txt_open_todos))
                .check(matches(isDisplayed()))
                .check(matches(withText(context.getString(R.string.open_todos, 1))));

        onView(withId(R.id.rv_todo_list))
                .check(matches(hasDescendant(withId(R.id.txt_todo_title))))
                .check(matches(hasDescendant(withText("Android Aufgaben erledigen"))));

        onView(withId(R.id.btn_add_todo))
                .check(matches(isDisplayed()))
                .check(matches(withText(context.getString(R.string.btn_add_todo))))
                .perform(click());

        onView(withId(R.id.alertTitle))
                .inRoot(isDialog())
                .check(matches(withText("Add Todo")));

        onView(withId(R.id.et_todo_title))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(typeText("Semesterferien genießen"));

        onView(withText(R.string.btn_ok))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.rv_todo_list))
                .check(matches(hasDescendant(withId(R.id.txt_todo_title))))
                .check(matches(hasDescendant(withText("Semesterferien genießen"))));

        onView(withId(R.id.txt_open_todos))
                .check(matches(isDisplayed()))
                .check(matches(withText(context.getString(R.string.open_todos, 2))));

        onView(withId(R.id.rv_todo_list))
                .perform(actionOnItemAtPosition(0, click()));

        onView(withId(R.id.rv_todo_list))
                .check(matches(hasDescendant(withId(R.id.txt_todo_done))))
                .check(matches(hasDescendant(withText("✔︎"))));

        onView(withId(R.id.txt_open_todos))
                .check(matches(isDisplayed()))
                .check(matches(withText(context.getString(R.string.open_todos, 1))));
    }

    @Test
    public void testSeeingManyTodos() throws InterruptedException {
        TodoRepository repositoryMock = mock(TodoRepository.class);
        List<Todo> todos = new ArrayList<>();
        for (int i = 0; i<20; i++){
            Todo todo = new Todo("Todo i = " + i);
            if (i % 2 == 0) {
                todo.toggleDone();
            }
            todos.add(todo);
        }
        when(repositoryMock.getTodos()).thenReturn(todos);
        DependencyContainer.set(TodoManager.class, new TodoManagerImpl(repositoryMock));

        activityRule.launchActivity(null);

        onView(withId(R.id.txt_open_todos))
                .check(matches(isDisplayed()))
                .check(matches(withText(context.getString(R.string.open_todos, 10))));

        Thread.sleep(5000);
    }
}

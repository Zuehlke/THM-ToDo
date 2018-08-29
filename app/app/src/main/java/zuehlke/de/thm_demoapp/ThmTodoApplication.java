package zuehlke.de.thm_demoapp;

import android.app.Application;

import zuehlke.de.thm_demoapp.Controller.TodoManager;
import zuehlke.de.thm_demoapp.Controller.TodoManagerImpl;
import zuehlke.de.thm_demoapp.Controller.TodoRepository;
import zuehlke.de.thm_demoapp.Controller.TodoRepositoryImpl;
import zuehlke.de.thm_demoapp.util.DependencyContainer;

public class ThmTodoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        setupDependencies();
    }

    private void setupDependencies() {
        TodoRepository todoRepository = new TodoRepositoryImpl();
        DependencyContainer.set(TodoRepository.class, todoRepository);

        TodoManager todoManager = new TodoManagerImpl(todoRepository);
        DependencyContainer.set(TodoManager.class, todoManager);
    }
}

package zuehlke.de.thm_demoapp.util;

import java.util.HashMap;
import java.util.Map;

import zuehlke.de.thm_demoapp.Controller.TodoManager;
import zuehlke.de.thm_demoapp.Controller.TodoManagerImpl;
import zuehlke.de.thm_demoapp.Controller.TodoRepository;
import zuehlke.de.thm_demoapp.Controller.TodoRepositoryImpl;

public class DependencyContainer {

    private static Map<Class, Object> objectMap = new HashMap<>();

    public static <T> void set(Class<T> clazz, T object) {
        objectMap.put(clazz, object);
    }

    public static <T> T get(Class<T> clazz) {
        return (T) objectMap.get(clazz);
    }
}

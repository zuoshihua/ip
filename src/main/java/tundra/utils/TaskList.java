package tundra.utils;

import java.util.ArrayList;

import tundra.exceptions.TundraException;
import tundra.models.Task;

/**
 * Represents a collection of tasks.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public void update(int index, Task task) {
        tasks.set(index, task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public boolean contains(Task task) {
        return tasks.contains(task);
    }

    /**
     * Loads tasks from the specified storage source.
     * @param storage A storage source that contains tasks.
     */
    public void load(Storage storage) {
        assert storage != null;
        int status = storage.load(tasks);
        if (status < 0) {
            throw new TundraException("I'm sorry. I couldn't retrieve your tasks.");
        } else if (status > 0) {
            throw new TundraException("I'm sorry. I couldn't load " + status + " task(s)");
        }
    }

    /**
     * Saves tasks from the specified storage source.
     * @param storage A storage source that contains tasks.
     */
    public void save(Storage storage) {
        assert storage != null;
        storage.save(tasks);
    }
}

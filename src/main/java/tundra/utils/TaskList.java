package tundra.utils;

import java.util.ArrayList;

import tundra.exceptions.TundraException;
import tundra.models.Task;

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

    public void load(Storage storage) {
        int status = storage.load(tasks);
        if (status < 0) {
            throw new TundraException("I'm sorry. I couldn't retrieve your tasks.");
        } else if (status > 0) {
            throw new TundraException("I'm sorry. I couldn't load " + status + " task(s)");
        }
    }

    public void save(Storage storage) {
        storage.save(tasks);
    }
}

package tundra.models;

/**
 * Represents the type of {@link Task} with a single character.
 * T is for to-do, D is for deadline and E is for event.
 */
public enum TaskEnum {
    T(new TodoTask()), D(new DeadlineTask()), E(new EventTask());

    final Task task;

    TaskEnum(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

    @Override
    public String toString() {
        return this.name();
    }
}

package tundra.models;

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

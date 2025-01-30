package tundra.models;

public enum TaskEnum {
    T(new Todo()), D(new Deadline()), E(new Event());

    final Task task;

    TaskEnum(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }
}

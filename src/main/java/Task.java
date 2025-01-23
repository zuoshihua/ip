public class Task {
    private String name;
    private boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    private Task(String name, boolean completed) {
        this.name = name;
        this.completed = completed;
    }

    public Task mark() {
        return new Task(this.name, true);
    }

    public Task unmark() {
        return new Task(this.name, false);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", completed ? "X" : " ", name);
    }
}

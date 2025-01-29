public class Task {
    private String name;
    private boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String toStoredString() {
        return String.format("%s | %s", completed ? "1" : "0", name);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", completed ? "X" : " ", name);
    }
}

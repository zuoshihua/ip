public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", TaskEnum.TODO, super.toString());
    }
}

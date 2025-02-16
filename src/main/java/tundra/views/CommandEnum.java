package tundra.views;

/**
 * Represents the type of {@link Command} with its name in all uppercase.
 */
public enum CommandEnum {
    HELP(new HelpCommand()),
    LIST(new ListCommand()),
    FIND(new FindCommand()),
    DELETE(new DeleteCommand()),
    MARK(new MarkCommand()),
    UNMARK(new UnmarkCommand()),
    TODO(new TodoCommand()),
    DEADLINE(new DeadlineCommand()),
    EVENT(new EventCommand()),
    BYE(new ByeCommand()),
    FORCE(new ForceCommand());

    final Command command;

    CommandEnum(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}

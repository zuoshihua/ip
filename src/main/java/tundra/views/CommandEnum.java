package tundra.views;

import tundra.models.TaskEnum;

public enum CommandEnum {
    HELP(new HelpCommand()),
    LIST(new ListCommand()),
    DELETE(new DeleteCommand()),
    MARK(new MarkCommand()),
    UNMARK(new UnmarkCommand()),
    TODO(new TodoCommand()),
    DEADLINE(new DeadlineCommand()),
    EVENT(new EventCommand()),
    BYE(new ByeCommand());

    final Command command;

    CommandEnum(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}

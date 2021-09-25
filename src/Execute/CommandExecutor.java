package Execute;

import CitiesClasses.User;
import Commands.Settings.Command;
import Commands.Settings.CommandName;
import Messenger.*;
import WorkWithConsole.ConsoleWorker;
import WorkWithConsole.Scan;

import java.io.IOException;
import java.util.Scanner;

public class CommandExecutor {

    private ConsoleWorker consoleWorker;
    private Messenger messenger;
    private CommandManager commandManager;

    public CommandExecutor(ConsoleWorker consoleWorker, Messenger messenger, CommandManager commandManager) {
        this.consoleWorker = consoleWorker;
        this.messenger = messenger;
        this.commandManager = commandManager;
    }

    public Response executeCommand(Command command, User user) throws IOException {
        if (messenger.sendRequest(new Request(command, true))) {
            Response response = messenger.getResponse();
            return response;
        } else {
            return null;
        }
    }

    public boolean isCommandExit(Command command) {
        if (command.getCommandName().equals(CommandName.EXIT)) {
            return true;
        } else {
            return false;
        }
    }
}

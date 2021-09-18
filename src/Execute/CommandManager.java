package Execute;

import Commands.Settings.Command;
import Messenger.Messenger;
import WorkWithConsole.ConsoleWorker;

public class CommandManager {
    private ConsoleWorker consoleWorker;
    private Messenger messenger;

    public CommandManager(ConsoleWorker consoleWorker, Messenger messenger) {
        this.consoleWorker = consoleWorker;
        this.messenger = messenger;
    }

    public void startWaitingCommands() {
        CommandFactory commandFactory = new CommandFactory(consoleWorker);
        CommandExecutor commandExecutor = new CommandExecutor(consoleWorker, messenger);
        boolean exit = false;

        while (!exit) {
            consoleWorker.write("Введите команду. Для просмотра списка команд введите help");
            CommandString commandString = new CommandString(consoleWorker.read());
            if (commandString.isValid()) {
                Command command = commandFactory.createCommand(commandString);
                commandExecutor.executeCommand(command);
                exit = commandExecutor.isCommandExit(command);
            } else {
                consoleWorker.write(commandString.getErrString());
            }
        }
    }
}

package Execute;

import Commands.Settings.Command;
import Commands.Settings.CommandName;
import Messenger.*;
import WorkWithConsole.ConsoleWorker;

public class CommandExecutor {

    private ConsoleWorker consoleWorker;
    private Messenger messenger;

    public CommandExecutor(ConsoleWorker consoleWorker, Messenger messenger) {
        this.consoleWorker = consoleWorker;
        this.messenger = messenger;
    }

    public void executeCommand(Command command){
        if(messenger.sendRequest(new Request(command, true))){
            Response response = messenger.getResponse();
            consoleWorker.write(response.getMessage());
        }else {
            consoleWorker.write("Попробуйте ввести команду занова!");
        }
    }

    public boolean isCommandExit(Command command){
        if(command.getCommandName().equals(CommandName.EXIT)){
            return true;
        } else {
            return false;
        }
    }
}

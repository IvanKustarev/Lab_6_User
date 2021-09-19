package Execute;

import Commands.ExecuteScript;
import Commands.Settings.Command;
import Commands.Settings.CommandName;
import Messenger.Messenger;
import WorkWithConsole.ConsoleWorker;
import Messenger.Response;
import WorkWithConsole.Scan;
import WorkWithConsole.ScriptScanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandManager {
    private ConsoleWorker consoleWorker;
    private Messenger messenger;
    private CommandFactory commandFactory;
    private CommandExecutor commandExecutor;
    private List<String> executeScriptList = new ArrayList<>();

    public CommandManager(ConsoleWorker consoleWorker, Messenger messenger) {
        this.consoleWorker = consoleWorker;
        this.messenger = messenger;
        commandFactory = new CommandFactory(consoleWorker);
        commandExecutor = new CommandExecutor(consoleWorker, messenger, this);
    }

    public boolean startWaitingCommands() throws IOException{
        boolean exit = false;

        while (!exit) {
            consoleWorker.write("Введите команду. Для просмотра списка команд введите help");
            exit = executeCommand(consoleWorker.read(), consoleWorker, commandFactory);
        }
        return true;
    }

    public boolean executeCommand(String str, Scan scan, CommandFactory commandFactory) throws IOException {
        CommandString commandString = new CommandString(str);
        if (commandString.isValid()) {
            Command command = commandFactory.createCommand(commandString);
            Response response = commandExecutor.executeCommand(command, scan);
            if(commandExecutor.isCommandExit(command)){
                return true;
            }
            if (command.getCommandName().equals(CommandName.EXECUTE_SCRIPT)) {
                ExecuteScript executeScript = (ExecuteScript) command;
                String file = executeScript.getParameter();
                for(String lastFiles : executeScriptList){
                    if(lastFiles.equals(file)){
                        consoleWorker.write("Файл " + file + " не запускается ещё раз из-за рекурсии!");
                        return false;
                    }
                }
                executeScriptList.add(file);
                executeScript(response);
            } else {
                if (response == null) {
                    scan.write("Попробуйте ввести команду занова!");
                } else {
                    scan.write(response.getMessage());
                }
            }

        } else {
            scan.write(commandString.getErrString());
        }
        return false;
    }

    private boolean executeScript(Response response) throws IOException{
        if (response == null) {
            consoleWorker.write("Попробуйте ввести команду занова!");
        } else {
            if (response.getMessage().split(";")[0].equals("false")) {
                consoleWorker.write(response.getMessage().split(";")[1]);
            } else {
                String script = response.getMessage().split(";")[1];
                Scan scan = new ScriptScanner(new Scanner(script), consoleWorker);
                while (scan.hasNext()) {
                    executeCommand(scan.read(), scan, new CommandFactory(scan));
                }
            }
        }
        return false;
    }
}

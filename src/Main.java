import Execute.CommandManager;
import Messenger.*;
import WorkWithConsole.ConsoleWorker;

import java.io.IOException;

public class Main {
    private static ConnectionManager connectionManager;
    private static ConsoleWorker consoleWorker;
    private static CommandManager commandManager;

    public static void main(String[] args) {

        consoleWorker = new ConsoleWorker();
        Messenger messenger = new Messenger(consoleWorker);
        connectionManager = new ConnectionManager(messenger, consoleWorker);
        commandManager = new CommandManager(consoleWorker, messenger);

        connectToServer();
        boolean exit = false;
        while (!exit) {
            try {
                exit = commandManager.startWaitingCommands();
            } catch (IOException e) {
                consoleWorker.write("Проблема с подключением к серверу! необходимо переподключиться!");
                connectToServer();
            }
        }
    }

    public static void connectToServer() {
        if (connectionManager.connectToServer()) {
            try {
                boolean login = false;
                while (!login){
                    login = commandManager.login();
                }

            } catch (IOException e) {
                connectToServer();
            }
        } else {
            connectToServer();
        }
    }
}

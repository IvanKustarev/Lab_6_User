package Main;

import Execute.CommandManager;
import Messenger.*;
import WorkWithConsole.ConsoleWorker;

import java.io.IOException;

public class Main {
    private static ConnectionManager connectionManager;

    public static void main(String[] args) {

        ConsoleWorker consoleWorker = new ConsoleWorker();
        Messenger messenger = new Messenger(consoleWorker);
        connectionManager = new ConnectionManager(messenger, consoleWorker);
        connectToServer();

        CommandManager commandManager = new CommandManager(consoleWorker, messenger);

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
            return;
        } else {
            connectToServer();
        }
    }
}

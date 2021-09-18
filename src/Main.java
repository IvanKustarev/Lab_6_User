import Execute.CommandManager;
import Messenger.*;
import WorkWithConsole.ConsoleWorker;

public class Main {
    public static void main(String[] args) {

        ConsoleWorker consoleWorker = new ConsoleWorker();
        Messenger messenger = new Messenger(consoleWorker);
        ConnectionManager connectionManager = new ConnectionManager(messenger, consoleWorker);
        connectToServer(connectionManager);

        CommandManager commandManager = new CommandManager(consoleWorker, messenger);
        commandManager.startWaitingCommands();
    }

    private static void connectToServer(ConnectionManager connectionManager) {
        if (connectionManager.connectToServer()) {
            return;
        } else {
            connectToServer(connectionManager);
        }
    }
}

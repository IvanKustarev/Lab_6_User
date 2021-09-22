package Messenger;

import WorkWithConsole.ConsoleWorker;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ConnectionManager {
    private Messenger messenger;
    private ConsoleWorker consoleWorker;

    public ConnectionManager(Messenger messenger, ConsoleWorker consoleWorker) {
        this.messenger = messenger;
        this.consoleWorker = consoleWorker;
    }

    public boolean connectToServer(){
        consoleWorker.write("Введите порт сервера:");
        int port;
        try {
            port = Integer.valueOf(consoleWorker.read().trim());
        } catch (Exception e){
            consoleWorker.write("Порт должен быть числом!");
            return false;
        }
        try {
            messenger.connect(InetAddress.getLocalHost().getHostName(), port);
        } catch (Exception e) {
            consoleWorker.write("Не удалось подключиться к серверу!");
            return false;
        }
        return true;
    }
}

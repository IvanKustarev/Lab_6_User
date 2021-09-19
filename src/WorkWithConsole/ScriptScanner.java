package WorkWithConsole;

import java.util.Scanner;

public class ScriptScanner implements Scan{

    private Scanner scanner;
    private ConsoleWorker consoleWorker;

    public ScriptScanner(Scanner scanner, ConsoleWorker consoleWorker) {
        this.scanner = scanner;
        this.consoleWorker = consoleWorker;
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }

    @Override
    public void write(String str) {
        consoleWorker.write(str);
    }

    @Override
    public boolean hasNext() {
        return scanner.hasNext();
    }
}

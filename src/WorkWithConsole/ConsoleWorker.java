package WorkWithConsole;

import java.util.Scanner;

public class ConsoleWorker implements Scan{
    private Scanner scanner = new Scanner(System.in);
    public String read(){
        try {
            return scanner.nextLine();
        }catch (Exception e){
            return read();
        }
    }
    public void write(String str){
        System.out.println(str);
    }

    @Override
    public boolean hasNext() {
        return true;
    }
}

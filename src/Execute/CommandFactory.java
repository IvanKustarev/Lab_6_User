package Execute;

import CitiesClasses.CitiesCreator;
import CitiesClasses.City;
import Commands.*;
import Commands.Settings.Command;
import Commands.Settings.CommandName;
import WorkWithConsole.ConsoleWorker;

public class CommandFactory {

    private ConsoleWorker consoleWorker;

    public CommandFactory(ConsoleWorker consoleWorker) {
        this.consoleWorker = consoleWorker;
    }

    public Command createCommand(CommandString commandString){
        CommandName commandName = commandString.getCommandName();
        Command command = null;
        if (Add.getName().equals(commandName)) {
            command = new Add(createCity());
        } else if (Clear.getName().equals(commandName)) {
            command = new Clear();
        } else if (ExecuteScript.getName().equals(commandName)) {
            command = new ExecuteScript(commandString.getParameter());
        } else if (Exit.getName().equals(commandName)) {
            command = new Exit();
        } else if (FilterContainsName.getName().equals(commandName)) {
            command = new FilterContainsName(commandString.getParameter());
        } else if (FilterLessThanGovernor.getName().equals(commandName)) {
            command = new FilterLessThanGovernor(commandString.getParameter());
        } else if (Head.getName().equals(commandName)) {
            command = new Head();
        } else if (Help.getName().equals(commandName)) {
            command = new Help();
        } else if (Info.getName().equals(commandName)) {
            command = new Info();
        } else if(PrintFieldDescendingGovernment.getName().equals(commandName)){
            command = new PrintFieldDescendingGovernment();
        } else if(RemoveById.getName().equals(commandName)){
            command = new RemoveById(commandString.getParameter());
        } else if(RemoveFirst.getName().equals(commandName)){
            command = new RemoveFirst();
        } else if(RemoveHead.getName().equals(commandName)){
            command = new RemoveHead();
        } else if(Show.getName().equals(commandName)){
            command = new Show();
        } else if(Update.getName().equals(commandName)){
            command = new Update(commandString.getParameter(), createCity());
        }

        return command;
    }

    private City createCity(){
        return CitiesCreator.createCity(consoleWorker);
    }
}

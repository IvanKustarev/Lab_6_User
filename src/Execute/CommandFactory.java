package Execute;

import CitiesClasses.CitiesCreator;
import CitiesClasses.City;
import CitiesClasses.User;
import Commands.*;
import Commands.Settings.Command;
import Commands.Settings.CommandName;
import WorkWithConsole.Scan;

public class CommandFactory {

    private Scan scan;

    public CommandFactory(Scan scan) {
        this.scan = scan;
    }

    public Command createCommand(CommandString commandString, User user){
        CommandName commandName = commandString.getCommandName();
        Command command = null;
        if (Add.getName().equals(commandName)) {
            command = new Add(createCity(user), user);
        } else if (Clear.getName().equals(commandName)) {
            command = new Clear(user);
        } else if (ExecuteScript.getName().equals(commandName)) {
            command = new ExecuteScript(commandString.getParameter(), user);
        } else if (Exit.getName().equals(commandName)) {
            command = new Exit(user);
        } else if (FilterContainsName.getName().equals(commandName)) {
            command = new FilterContainsName(commandString.getParameter(), user);
        } else if (FilterLessThanGovernor.getName().equals(commandName)) {
            command = new FilterLessThanGovernor(commandString.getParameter(), user);
        } else if (Head.getName().equals(commandName)) {
            command = new Head(user);
        } else if (Help.getName().equals(commandName)) {
            command = new Help(user);
        } else if (Info.getName().equals(commandName)) {
            command = new Info(user);
        } else if(PrintFieldDescendingGovernment.getName().equals(commandName)){
            command = new PrintFieldDescendingGovernment(user);
        } else if(RemoveById.getName().equals(commandName)){
            command = new RemoveById(commandString.getParameter(), user);
        } else if(RemoveFirst.getName().equals(commandName)){
            command = new RemoveFirst(user);
        } else if(RemoveHead.getName().equals(commandName)){
            command = new RemoveHead(user);
        } else if(Show.getName().equals(commandName)){
            command = new Show(user);
        } else if(Update.getName().equals(commandName)){
            command = new Update(commandString.getParameter(), createCity(user), user);
        }

        return command;
    }

    private City createCity(User user){
        return CitiesCreator.createCity(scan, user);
    }
}

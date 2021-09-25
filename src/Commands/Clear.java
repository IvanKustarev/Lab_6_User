package Commands;

import CitiesClasses.User;
import Commands.Settings.AbstractCommand;
import Commands.Settings.CommandName;
import Commands.Settings.Executor;
import Messenger.Response;

public class Clear extends AbstractCommand {

    private User user;

    public Clear(User user) {
        this.user = user;
    }

    public static CommandName getName(){
        return CommandName.CLEAR;
    }
    public static boolean haveStringParameter() {
        return false;
    }

    @Override
    public Response execute(Executor executor) {
        return executor.executeClear(user);
    }

    @Override
    public CommandName getCommandName() {
        return getName();
    }

    @Override
    public User getUser() {
        return user;
    }
}

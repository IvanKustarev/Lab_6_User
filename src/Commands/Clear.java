package Commands;

import Commands.Settings.AbstractCommand;
import Commands.Settings.CommandName;
import Commands.Settings.Executor;
import Messenger.Response;

public class Clear extends AbstractCommand {
    public static CommandName getName(){
        return CommandName.CLEAR;
    }
    public static boolean haveStringParameter() {
        return false;
    }

    @Override
    public Response execute(Executor executor) {
        return executor.executeClear();
    }

    @Override
    public CommandName getCommandName() {
        return getName();
    }
}

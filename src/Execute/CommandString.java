package Execute;

import Commands.Settings.CommandClassByNameFactory;
import Commands.Settings.CommandName;

public class CommandString {
    private String commandString;
    private boolean valid = true;
    private String parameter = null;
    private String errString = null;
    private CommandName commandName;

    public CommandString(String commandString) {
        this.commandString = commandString;
        validate();
    }

    private void validate() {
        try {
            if (commandString == null) {
                valid = false;
                errString = "Получена пустая строка вместо команды!";
                return;
            }
            commandString = commandString.trim();
            commandString = commandString.replace("( )+", " ");
            if (commandString.equals("")) {
                valid = false;
                errString = "Получена пустая строка вместо команды!";
                return;
            }

            String[] substrings = commandString.split(" ");
            String commandNameString = substrings[0].toLowerCase();
            try {
                commandName = CommandName.valueOf(commandNameString.toUpperCase());
            } catch (Exception e) {
                valid = false;
                errString = "Неизвестная команда!";
                return;
            }
            if (commandName == null) {
                valid = false;
                errString = "Неизвестная команда!";
                return;
            }

            if (substrings.length > 1) {
                parameter = substrings[1];
            } else {
                parameter = null;
            }

            try {
                validateHavingParameter(commandName);
            }catch (Exception e){
                valid = false;
                errString = "Ошибка при обработке параметра!";
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            valid = false;
            errString = "Неизвестная ошибка при распозновании команды!";
            return;
        }
    }

    private void validateHavingParameter(CommandName commandName) {
        if(CommandClassByNameFactory.haveCommandParameter(commandName)){
            if(!hasParameter()){
                valid = false;
                errString = "Команде необходим параметр!";
            }
        }
    }

    public boolean isValid() {
        return valid;
    }

    public CommandName getCommandName() {
        return commandName;
    }

    private boolean hasParameter() {
        if (parameter != null) {
            return true;
        } else {
            return false;
        }
    }

    public String getParameter() {
        return parameter;
    }

    public String getErrString() {
        return errString;
    }
}

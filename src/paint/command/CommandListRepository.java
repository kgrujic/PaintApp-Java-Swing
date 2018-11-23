package paint.command;

import java.util.LinkedList;

public class CommandListRepository {

    private LinkedList<ICommand> commandsList;

    public CommandListRepository() {
        commandsList = new LinkedList<ICommand>();

    }

    public void addCommand(ICommand command){
        commandsList.add(command);
    }

    public LinkedList<ICommand> getAllCommands(){
        return commandsList;
    }

    public ICommand getLastCommand(){
        return commandsList.getLast();
    }
}

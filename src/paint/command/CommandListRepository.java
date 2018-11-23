package paint.command;

import java.util.LinkedList;

public class CommandListRepository {

    private LinkedList<ICommand> commandsList;
    private int index = 0;

    public CommandListRepository() {
        commandsList = new LinkedList<ICommand>();
    }

    public void IncrementIndex(){
        index++;
    }

    public void DecrementIndex(){
        index--;
    }

    public ICommand getCurrentCommand(){

        return commandsList.get(index-1);

    }

    public void addCommand(ICommand command){
        commandsList.add(command);
        IncrementIndex();
    }

    public LinkedList<ICommand> getAllCommands(){
        return commandsList;
    }

    public int getIndex(){
        return index;
    }
}

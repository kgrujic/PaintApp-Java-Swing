package paint.command;

import geometry.Shape;
import paint.mvc.PaintModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CommandListRepository {

    private LinkedList<ICommand> commandHistory;
    private int currentIndex;
    private  PaintModel model;

    int numBeforeUndo;

    int numAfterUndo;

    ArrayList<Shape> afterUndo;
    ArrayList<Shape> beforeUndoRedo;
    ICommand commandAfterUndo;

    boolean isFirstUndo = true;


    public CommandListRepository(PaintModel model) {
        commandHistory = new LinkedList<ICommand>();
        this.model = model;

    }


    public void addCommandToHistory(ICommand command) {
        commandHistory.add(command);
        if(commandHistory.size() == 1){
            currentIndex = 0;
        } else{
            currentIndex++;
        }

    }

    public void Undo(){

        beforeUndoRedo = new ArrayList<Shape>(model.getShapes());

        if(isFirstUndo == false){
            if(!areTwoListsEqual(beforeUndoRedo,afterUndo) && beforeUndoRedo.size() >= afterUndo.size()) {
                cutHistoryCommands(commandAfterUndo);
            }
        }
        if(commandHistory.size()!=0) {
            commandHistory.get(currentIndex).unexecute();
            currentIndex--;
            isFirstUndo = false;
            afterUndo = new ArrayList<Shape>(model.getShapes());
            commandAfterUndo = getCurrentCommand();

        }
    }

    public void Redo(){

        if(commandHistory.size()!=0) {
            commandHistory.get(currentIndex).execute();
            currentIndex++;

        }

    }

    public boolean areTwoListsEqual(ArrayList<Shape> afterUndo, ArrayList<Shape> beforeUndoRedo){
        return afterUndo.containsAll(beforeUndoRedo) && beforeUndoRedo.containsAll(afterUndo);
    }


    public void cutHistoryCommands(ICommand commandAfterUndo){
        this.commandHistory.subList(commandHistory.indexOf(commandAfterUndo)+1,commandHistory.indexOf(commandHistory.getLast())).clear();

        currentIndex = commandHistory.indexOf(commandHistory.getLast());
    }

    public ICommand getCurrentCommand(){
        if(currentIndex == -1){
            currentIndex = 0;
        }
        return commandHistory.get(currentIndex);
    }


}

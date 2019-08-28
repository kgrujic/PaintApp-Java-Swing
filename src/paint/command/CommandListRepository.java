package paint.command;

import geometry.Shape;
import paint.mvc.PaintController;
import paint.mvc.PaintModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class CommandListRepository {

    private LinkedList<ICommand> commandHistory;
    private ArrayList<String> commandHistoryStrings;
    private int currentIndex;
    private  PaintModel model;


    private DefaultListModel listModel;

    // TODO clear command history
    ArrayList<Shape> afterUndo;
    ArrayList<Shape> beforeUndoRedo;
    ICommand commandAfterUndo;

    boolean isFirstUndo = true;


    public CommandListRepository(PaintModel model,DefaultListModel listModel) {
        commandHistory = new LinkedList<ICommand>();
        this.model = model;
        this.listModel = listModel;
        this.commandHistoryStrings = new ArrayList<String>();
    }


    public void addCommandToHistory(ICommand command,String commandString,boolean isFromLog) {
        commandHistory.add(command);
        commandHistoryStrings.add(commandString);

        if(!isFromLog) {
            listModel.addElement(commandString + "\n");
        }


        if(commandHistory.size() == 1){
            currentIndex = 0;
        } else{
            currentIndex++;
        }

    }

    public void Undo(boolean isFromLog){

        if(model.getShapes().size() != 0){
            CmdUndo cmdUndo = new CmdUndo(this);
            cmdUndo.execute();
            if(!isFromLog) {
                listModel.addElement("Undo" + "\n");
            }
        }


        if(model.getShapes().size() == 0){
            isFirstUndo = true;
            beforeUndoRedo = new ArrayList<Shape>();
            afterUndo = new ArrayList<Shape>();
        }



    }


    public void Redo(boolean isFromLog){

        CmdRedo cmdRedo = new CmdRedo(this);
        cmdRedo.execute();
        if(!isFromLog) {
            listModel.addElement("Redo" + "\n");
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

    public LinkedList<ICommand> getCommandHistory() {
        return commandHistory;
    }

    public ArrayList<String> getCommandHistoryStrings() { return  commandHistoryStrings; }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public PaintModel getModel() {
        return model;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public ArrayList<Shape> getAfterUndo() {
        return afterUndo;
    }

    public void setAfterUndo(ArrayList<Shape> afterUndo) {
        this.afterUndo = afterUndo;
    }

    public ArrayList<Shape> getBeforeUndoRedo() {
        return beforeUndoRedo;
    }

    public void setBeforeUndoRedo(ArrayList<Shape> beforeUndoRedo) {
        this.beforeUndoRedo = beforeUndoRedo;
    }

    public ICommand getCommandAfterUndo() {
        return commandAfterUndo;
    }

    public void setCommandAfterUndo(ICommand commandAfterUndo) {
        this.commandAfterUndo = commandAfterUndo;
    }

    public boolean isFirstUndo() {
        return isFirstUndo;
    }

    public void setFirstUndo(boolean firstUndo) {
        isFirstUndo = firstUndo;
    }
}

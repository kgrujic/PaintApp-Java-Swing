package paint.command;

import geometry.Shape;

import java.util.ArrayList;

public class CmdUndo implements  ICommand, Cloneable{


    private CommandListRepository cmdListRepo;


    public CmdUndo(CommandListRepository cmdListRepo){

        this.cmdListRepo = cmdListRepo;
    }

    @Override
    public void execute() {

        if(cmdListRepo.getCurrentIndex() == cmdListRepo.getCommandHistory().size()) {
            cmdListRepo.setCurrentIndex(cmdListRepo.getCurrentIndex()-1);
        }

        cmdListRepo.setBeforeUndoRedo(new ArrayList<Shape>(cmdListRepo.getModel().getShapes()));

        if(cmdListRepo.isFirstUndo == false){
            if(!cmdListRepo.areTwoListsEqual(cmdListRepo.getBeforeUndoRedo(),cmdListRepo.getAfterUndo()) && cmdListRepo.getBeforeUndoRedo().size() >= cmdListRepo.getAfterUndo().size()) {
                cmdListRepo.cutHistoryCommands(cmdListRepo.getCommandAfterUndo());
            }
        }
        if(cmdListRepo.getCommandHistory().size()!=0) {


            cmdListRepo.getCommandHistory().get(cmdListRepo.getCurrentIndex()).unexecute();

            cmdListRepo.setCurrentIndex(cmdListRepo.getCurrentIndex() - 1);


            cmdListRepo.isFirstUndo = false;
            cmdListRepo.setAfterUndo(new ArrayList<Shape>(cmdListRepo.getModel().getShapes()));
            cmdListRepo.setCommandAfterUndo(cmdListRepo.getCurrentCommand());

        }
    }

    @Override
    public void unexecute() {

        if(cmdListRepo.getCommandHistory().size()!=0) {

            cmdListRepo.getCommandHistory().get(cmdListRepo.getCurrentIndex()).execute();

            cmdListRepo.setCurrentIndex(cmdListRepo.getCurrentIndex()+1);

        }

    }




}

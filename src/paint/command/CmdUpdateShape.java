package paint.command;

import geometry.Shape;

public class CmdUpdateShape implements ICommand, Cloneable {


    private Shape oldState;
    private Shape newState;
    private Shape originalState;


    //private boolean isSelected;



    public CmdUpdateShape(Shape oldState, Shape newState) {

        this.oldState = oldState;
        this.newState = newState;


    }


    @Override
    public void execute() {


            originalState = oldState.cloneInstance();
            oldState.update(newState);


    }

    @Override
    public void unexecute() {

            oldState.update(originalState);

    }


}

package paint.command;

import geometry.Shape;
import paint.mvc.PaintModel;

public class CmdUpdateShape implements ICommand, Cloneable {

    private PaintModel model;
    private Shape oldState;
    private Shape newState;
    private Shape originalState;
    private Shape originalNewState;


    public CmdUpdateShape(PaintModel model, Shape oldState, Shape newState) {
        this.model = model;
        this.oldState = oldState;
        this.newState = newState;

    }

    @Override
    public void execute() {
        originalState = oldState.cloneInstance();
        model.update(oldState, newState);

    }

    @Override
    public void unexecute() {
        model.update(oldState, originalState);
    }

    @Override
    public CmdUpdateShape clone() {
        try {
            return (CmdUpdateShape) super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}

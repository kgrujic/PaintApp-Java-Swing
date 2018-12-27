package paint.command;

import paint.mvc.PaintModel;

public class CmdRemoveAllShapes implements ICommand, Cloneable{
    private PaintModel model;

    public CmdRemoveAllShapes(PaintModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.removeAllShapes();
    }

    @Override
    public void unexecute() {
        model.addAllShapes();
    }

    @Override
    public CmdRemoveAllShapes clone() {
        try {
            return (CmdRemoveAllShapes) super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}

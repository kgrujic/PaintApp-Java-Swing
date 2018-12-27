package paint.command;

import geometry.Shape;
import paint.mvc.PaintModel;

public class CmdRemoveShape implements ICommand, Cloneable{

    private PaintModel model;
    private Shape shape;

    public CmdRemoveShape(PaintModel model, Shape shape) {
        this.model = model;
        this.shape = shape;
    }
    @Override
    public void execute() {
        model.remove(shape);
    }

    @Override
    public void unexecute() {
        model.add(shape);
    }

    @Override
    public CmdRemoveShape clone() {
        try {
            return (CmdRemoveShape) super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}

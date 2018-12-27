package paint.command;

import geometry.Shape;
import paint.mvc.PaintModel;

public class CmdAddShape implements ICommand,Cloneable{

    private PaintModel model;
    private Shape shape;

    public CmdAddShape() {
    }

    public CmdAddShape(PaintModel model, Shape shape) {
        this.model = model;
        this.shape = shape;
    }

    @Override
    public void execute() {
        model.add(shape);
    }

    @Override
    public void unexecute() {
        model.remove(shape);
    }


    @Override
    public CmdAddShape clone() {
        try {
            return (CmdAddShape) super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


}

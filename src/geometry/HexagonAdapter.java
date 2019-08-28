package geometry;

import hexagon.Hexagon;
import paint.command.CommandListRepository;
import paint.dialogs.HexagonDialog;
import paint.mvc.PaintModel;

import javax.swing.*;
import java.awt.*;

public class HexagonAdapter extends AreaShape{
    private static final long serialVersionUID = 5510713369353881462L;
    private Hexagon hexagon;

    public HexagonAdapter(Hexagon hexagon){
        this.hexagon = hexagon;
    }

    @Override
    public void draw(Graphics g) {
        hexagon.paint(g);
    }

    @Override
    public void selected(Graphics g) {

    }

    @Override
    public boolean contains(int x, int y) {
       return hexagon.doesContain(x,y);
    }

    @Override
    public <T extends JDialog> T createDialogForUpdate(boolean isUpdate, Shape oldShape, PaintModel paintModel, CommandListRepository commandListRepository) {
        HexagonDialog rectDialog = new HexagonDialog(isUpdate,oldShape, paintModel,commandListRepository);
        return (T) rectDialog;
    }

    @Override
    public void update(Shape newShape) {
        HexagonAdapter tmpHexagon = (HexagonAdapter) newShape;
        this.setX(tmpHexagon.getX());
        this.setY(tmpHexagon.getY());
        this.setR(tmpHexagon.getR());
        this.setInsideColor(tmpHexagon.getInsideColor());
        this.setOutlineColor(tmpHexagon.getOutlineColor());
        this.setSelected(tmpHexagon.isSelected());
    }

    @Override
    public Shape cloneInstance() {
        Hexagon originalHexagon = new Hexagon(this.getX(),this.getY(),this.getR());
        originalHexagon.setAreaColor(this.getInsideColor());
        originalHexagon.setAreaColor(this.getOutlineColor());
        originalHexagon.setSelected(this.isSelected());
        return new HexagonAdapter(originalHexagon);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public void fill(Graphics g) {

    }


    public Color getInsideColor() {
        return hexagon.getAreaColor();
    }

    public void setInsideColor(Color insideColor) {
        hexagon.setAreaColor(insideColor);
    }

    public Color getOutlineColor() {
        return hexagon.getBorderColor();
    }

    public void setOutlineColor(Color outlineColor) {
        hexagon.setBorderColor(outlineColor);
    }

    public int getX() {
        return hexagon.getX();
    }

    public void setX(int x) {
        hexagon.setX(x);
    }

    public int getY() {
        return hexagon.getY();
    }

    public void setY(int y) {
        hexagon.setY(y);
    }

    public int getR() {
        return hexagon.getR();
    }

    public void setR(int r) {
        hexagon.setR(r);
    }

    public boolean isSelected() {
        return hexagon.isSelected();
    }

    public void setSelected(boolean selected) {
        hexagon.setSelected(selected);
    }
}

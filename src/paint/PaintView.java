package paint;

import geometry.Point;
import geometry.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class PaintView extends JPanel  {

    private ArrayList<Shape> shapes;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape sh: shapes) {
            sh.draw(g);
        }
    }

    public void setShapes(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }
}

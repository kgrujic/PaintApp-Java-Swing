package paint.mvc;

import geometry.Point;
import geometry.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PaintView extends JPanel  {

    private BufferedImage image;

    public void setImage(BufferedImage image)
    {
        this.image = image;
        repaint();
    }

    private ArrayList<Shape> shapes;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null)
        {
            g.drawImage(image, 0, 0, null);
        }


        for (Shape sh : shapes) {
            sh.draw(g);
        }

    }

    public void setShapes(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }
}

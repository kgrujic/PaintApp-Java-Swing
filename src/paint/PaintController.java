package paint;

import geometry.*;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaintController {

    private PaintForm paintForm;
    private JRadioButton rbtnPoint;
    private JRadioButton rbtnLine;
    private JRadioButton rbtnRectangle;
    private JRadioButton rbtnCircle;
    private JRadioButton rbtnSquare;
    private JRadioButton rbtnSelect;
    private JButton btnDelete;
    private JButton btnUpdate;
    private JButton btnClearAll;
    private JButton btnOutlineColor;
    private JButton btnInsideColor;


    private Point lineStartPoint;
    private Point lineEndPoint;
    private Point clickPoint;

    private PaintView paintView;
    private PaintModel paintModel;

    private final boolean isUpdate = true;
    private Color activeOutlineColor = Color.black;
    private Color activeInsideColor = Color.white;




    public PaintController() {

        initComponents();
        click();

    }

    private void click() {

        paintView.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                Point upLeft = new Point(e.getX(),e.getY());

                if(rbtnSelect.isSelected()){
                    clickPoint = upLeft;
                    paintModel.selectShape(paintModel.getSelectedShape(clickPoint));
                    paintView.repaint();
                }
                else if(rbtnSquare.isSelected()){
                    SquareDialog dialog = new SquareDialog();
                    Square s = new Square(upLeft,dialog.getSideLength(),activeOutlineColor,activeInsideColor);

                    if(dialog.isReadyToAdd()) {
                        paintModel.add(s);
                        paintView.repaint();
                    }

                }
                else if(rbtnCircle.isSelected()){
                    CircleDialog dialog = new CircleDialog();
                    Circle c = new Circle(upLeft,dialog.getRadius(),activeOutlineColor,activeInsideColor);

                    if(dialog.isReadyToAdd()) {
                        paintModel.add(c);
                        paintView.repaint();
                    }

                }else if(rbtnRectangle.isSelected()){
                    RectangleDialog dialog = new RectangleDialog();
                    Rectangle r = new Rectangle(upLeft,dialog.getRHeight(),dialog.getRWidth(),activeOutlineColor,activeInsideColor);
                    if (dialog.isReadyToAdd()){
                    paintModel.add(r);
                    paintView.repaint();
                    }
                } else if(rbtnLine.isSelected()){
                        if(lineStartPoint == null){
                            lineStartPoint = upLeft;
                        }
                        else {
                            lineEndPoint = upLeft;

                            Line l = new Line(lineStartPoint,lineEndPoint,activeOutlineColor);
                            paintModel.add(l);
                            paintView.repaint();

                            lineStartPoint = null;
                        }

                }
                else if(rbtnPoint.isSelected()){
                    Point p = upLeft;
                    p.setOutlineColor(activeOutlineColor);
                    paintModel.add(p);
                    paintView.repaint();
                }
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rbtnSelect.isSelected()) {
                    Shape oldShape = paintModel.getSelectedShape(clickPoint);
                    if (oldShape == null) {
                        JOptionPane.showMessageDialog(paintForm, "Please select shape first");
                    } else {
                        oldShape.createDialog(isUpdate, oldShape, paintModel);
                        paintView.repaint();
                        clickPoint = new Point(0,0);
                    }
                }

            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rbtnSelect.isSelected()) {
                    Shape shapeToDelete = paintModel.getSelectedShape(clickPoint);
                    if (shapeToDelete == null) {
                        JOptionPane.showMessageDialog(paintForm, "Please select shape first");
                    } else {
                        paintModel.remove(shapeToDelete);
                        paintView.repaint();
                        clickPoint = new Point(0,0);
                    }
            }
        }
    });

        btnClearAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintModel.removeAllShapes();
                paintView.repaint();
            }
        });

        btnOutlineColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             activeOutlineColor =  paintModel.dialogSetOutlineColor(btnOutlineColor);
            }
        });

        btnInsideColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                activeInsideColor = paintModel.dialogSetInsideColor(btnInsideColor);
            }
        });
    }


    public void showMainFrame(){
        paintForm.setVisible(true);
    }


    private void initComponents() {

        paintForm = new PaintForm();

        paintModel = new PaintModel();

        paintView = paintForm.getPaint();
        paintView.setShapes(paintModel.getShapes());


        rbtnPoint = paintForm.getRbtnPoint();
        rbtnLine = paintForm.getRbtnLine();
        rbtnCircle = paintForm.getRbtnCircle();
        rbtnSquare = paintForm.getRbtnSquare();
        rbtnRectangle = paintForm.getRbtnRectangle();
        rbtnSelect = paintForm.getRbtnSelect();


        btnUpdate = paintForm.getBtnUpdate();
        btnDelete = paintForm.getBtnDelete();
        btnClearAll = paintForm.getBtnClearAll();

        btnOutlineColor = paintForm.getBtnOutlineColor();
        btnInsideColor = paintForm.getBtnInsideColor();

    }




}

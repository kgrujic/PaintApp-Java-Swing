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

    interface Conditionally<F> {
        void invoke(boolean c, F f);
    }

    interface SideEffect {
        void invoke();
    }

    private void click() {

        Conditionally<SideEffect> conditionally = (c, f) -> {
            if (c) {
                f.invoke();
            }
        };

        paintView.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                Point upLeft = new Point(e.getX(),e.getY());

                conditionally.invoke(rbtnSelect.isSelected(), () -> {
                    clickPoint = upLeft;
                    paintModel.selectShape(paintModel.getSelectedShape(clickPoint));
                    paintView.repaint();
                });
                conditionally.invoke(rbtnSquare.isSelected(), () -> {
                    SquareDialog dialog = new SquareDialog();
                    Square s = new Square(upLeft,dialog.getSideLength(),activeOutlineColor,activeInsideColor);

                    if(dialog.isReadyToAdd()) {
                        paintModel.add(s);
                        paintView.repaint();
                    }
                });

                conditionally.invoke(rbtnCircle.isSelected(), () -> {
                    CircleDialog dialog = new CircleDialog();
                    Circle c = new Circle(upLeft,dialog.getRadius(),activeOutlineColor,activeInsideColor);

                    if(dialog.isReadyToAdd()) {
                        paintModel.add(c);
                        paintView.repaint();
                    }
                });
                conditionally.invoke(rbtnRectangle.isSelected(), () -> {
                    RectangleDialog dialog = new RectangleDialog();
                    Rectangle r = new Rectangle(upLeft,dialog.getRHeight(),dialog.getRWidth(),activeOutlineColor,activeInsideColor);

                    if (dialog.isReadyToAdd()){
                        paintModel.add(r);
                        paintView.repaint();
                    }
                });
                conditionally.invoke(rbtnLine.isSelected(), () -> {
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
                });
                conditionally.invoke(rbtnPoint.isSelected(), () -> {
                    Point p = upLeft;
                    p.setOutlineColor(activeOutlineColor);
                    paintModel.add(p);
                    paintView.repaint();
                });

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

package paint.mvc;

import geometry.*;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import hexagon.Hexagon;
import paint.command.*;
import paint.dialogs.*;
import paint.PaintForm;
import paint.helpers.ColorDialogSetup;
import paint.helpers.Selection;
import paint.log.CommandExecutionFromLog;
import paint.log.Helpers;
import paint.log.ListAction;
import paint.observer.*;
import paint.strategy.exportfiles.ExportContext;
import paint.strategy.exportfiles.ExportImage;
import paint.strategy.exportfiles.ExportLog;
import paint.strategy.importfiles.ImportContext;
import paint.strategy.importfiles.ImportImage;
import paint.strategy.importfiles.ImportLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class PaintController  {
    private static final long serialVersionUID = 2216489104960003132L;

    private JRadioButton rbtnPoint;
    private JRadioButton rbtnLine;
    private JRadioButton rbtnRectangle;
    private JRadioButton rbtnCircle;
    private JRadioButton rbtnSquare;
    private JRadioButton rbtnHexagon;
    private JRadioButton rbtnSelect;

    private JButton btnDelete;
    private JButton btnUpdate;
    private JButton btnClearAll;
    private JButton btnDeselectAll;
    private JButton btnOutlineColor;
    private JButton btnInsideColor;

    private JList<String> logText;
    private DefaultListModel listModel;
    private JButton btnSaveLog;
    private JButton btnImportLog;
    private JButton btnSaveImage;
    private JButton btnImportImage;

    private JButton btnToFront;
    private JButton btnToBack;
    private JButton btnBringToFront;
    private JButton btnBringToBack;


    private JButton btnDownArrow;

    private JButton btnUndo;
    private JButton btnRedo;

    private Point lineStartPoint;
    private Point lineEndPoint;
    private Point clickPoint;

    private PaintForm paintForm;
    private PaintView paintView;
    private PaintModel paintModel;


    private final boolean isUpdate = true;
    private boolean isClearALlOrDelete = false;
    private Color activeOutlineColor = Color.black;
    private Color activeInsideColor = Color.white;


    private ImportContext importLogContext = new ImportContext();
    private ImportContext importImageContext = new ImportContext();
    private ExportContext exportLogContext = new ExportContext();
    private ExportContext exportImageContext = new ExportContext();

    private CommandListRepository commandListRepository;
    private Selection selection;
    private Helpers helper;
    private ColorDialogSetup colorDialogSetup;
    private CommandExecutionFromLog executionFromLog;
    private ManageObservers manageObservers;



    public PaintController(PaintForm paintForm, PaintModel paintModel) {
        this.paintForm = paintForm;
        this.paintModel = paintModel;
        this.selection = new Selection(paintModel);

        this.helper = new Helpers();
        this.colorDialogSetup  = new ColorDialogSetup();


        setPaintView(paintForm.getPaint());
        getPaintView().setShapes(paintModel.getShapes());

        initComponents();
        click();


        this.commandListRepository = new CommandListRepository(this.getPaintModel(),this.getListModel());

        this.manageObservers = new ManageObservers(this,this.commandListRepository);
        manageObservers.manageMyObservers();

        executionFromLog = new CommandExecutionFromLog(commandListRepository,paintModel,paintView,selection);

        importLogContext.setImportStrategy(new ImportLog(this.getListModel()));
        importImageContext.setImportStrategy(new ImportImage(this.getPaintView()));
        exportLogContext.setExportStrategy(new ExportLog(this.getListModel()));
        exportImageContext.setExportStrategy(new ExportImage(this.getPaintView()));

        btnDownArrow.setVisible(false);

    }



    interface Conditionally<F> {
        void invoke(boolean c, F f);
    }

    interface SideEffect {
        void invoke();
    }



    private void click() {


        Action displayAction = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                JList list = (JList)e.getSource();

                executionFromLog.executeCommand(list.getSelectedValue().toString());
            }
        };

        ListAction la = new ListAction(logText, displayAction);


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
                    Shape selectedShape = selection.getSelectedShape(clickPoint);


                    if(selectedShape != null){
                        Shape newShape = selectedShape.cloneInstance();

                        if(selectedShape.isSelected() == true){
                            newShape.setSelected(false);
                            CmdUpdateShape cmdSelectShape = new CmdUpdateShape(selectedShape,newShape);
                            cmdSelectShape.execute();
                            selection.removeSelectedShape(selectedShape);
                            addCommandToHistory(cmdSelectShape,helper.makeCommand(selectedShape,"Deselected"));
                        }
                        else {
                            newShape.setSelected(true);
                            CmdUpdateShape cmdSelectShape = new CmdUpdateShape(selectedShape,newShape);
                            cmdSelectShape.execute();
                            selection.addSelectedShape(selectedShape);
                            addCommandToHistory(cmdSelectShape,helper.makeCommand(selectedShape,"Selected"));
                        }

                    }

                    paintView.repaint();

                });

                conditionally.invoke(rbtnSquare.isSelected(), () -> {
                    SquareDialog dialog = new SquareDialog();
                    Square s = new Square(upLeft,dialog.getSideLength(),activeOutlineColor,activeInsideColor);

                    if(dialog.isReadyToAdd()) {

                        CmdAddShape cmdAddSquare = new CmdAddShape(paintModel,s);
                        cmdAddSquare.execute();
                        addCommandToHistory(cmdAddSquare,helper.makeCommand(s,"Added"));
                        paintView.repaint();

                    }
                });

                conditionally.invoke(rbtnCircle.isSelected(), () -> {
                    CircleDialog dialog = new CircleDialog();
                    Circle c = new Circle(upLeft,dialog.getRadius(),activeOutlineColor,activeInsideColor);

                    if(dialog.isReadyToAdd()) {

                        CmdAddShape cmdAddCircle = new CmdAddShape(paintModel,c);
                        cmdAddCircle.execute();
                        addCommandToHistory(cmdAddCircle,helper.makeCommand(c,"Added"));
                        paintView.repaint();

                    }
                });

                conditionally.invoke(rbtnRectangle.isSelected(), () -> {
                    RectangleDialog dialog = new RectangleDialog();
                    Rectangle r = new Rectangle(upLeft,dialog.getRHeight(),dialog.getRWidth(), activeOutlineColor, activeInsideColor);

                    if (dialog.isReadyToAdd()){

                        CmdAddShape cmdAddRectangle = new CmdAddShape(paintModel,r);
                        cmdAddRectangle.execute();
                        addCommandToHistory(cmdAddRectangle,helper.makeCommand(r,"Added"));
                        paintView.repaint();

                    }
                });

                conditionally.invoke(rbtnHexagon.isSelected(), () -> {
                    HexagonDialog dialog = new HexagonDialog();
                    Hexagon h = new Hexagon(dialog.getX(),dialog.getY(),dialog.getR());
                    h.setBorderColor(activeOutlineColor);
                    h.setAreaColor(activeInsideColor);

                    HexagonAdapter hexagonAdapter = new HexagonAdapter(h);

                    if (dialog.isReadyToAdd()){
                        CmdAddShape cmdAddHexagon = new CmdAddShape(paintModel,hexagonAdapter);
                        cmdAddHexagon.execute();
                        addCommandToHistory(cmdAddHexagon,helper.makeCommand(hexagonAdapter,"Added"));
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

                        CmdAddShape cmdAddLine = new CmdAddShape(paintModel,l);
                        cmdAddLine.execute();
                        addCommandToHistory(cmdAddLine,helper.makeCommand(l,"Added"));
                        paintView.repaint();

                        lineStartPoint = null;
                    }
                });

                conditionally.invoke(rbtnPoint.isSelected(), () -> {
                    Point p = upLeft;
                    p.setOutlineColor(activeOutlineColor);

                    CmdAddShape cmdAddPoint = new CmdAddShape(paintModel,p);
                    cmdAddPoint.execute();
                    addCommandToHistory(cmdAddPoint,helper.makeCommand(p,"Added"));
                    paintView.repaint();

                });

                manageObservers.manageMyObservers();
            }

        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    Shape oldShape = paintModel.getSelectedShapes().get(0);
                    if (oldShape == null) {
                        JOptionPane.showMessageDialog(paintForm, "Please select shape first");
                    } else {
                        oldShape.createDialogForUpdate(isUpdate, oldShape, paintModel,commandListRepository);
                        paintView.repaint();
                        clickPoint = new Point(0,0);
                    }

            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    ArrayList<Shape> shapesToDelete = paintModel.getSelectedShapes();

                    if (shapesToDelete == null) {
                        JOptionPane.showMessageDialog(paintForm, "Please select shape first");
                    } else {


                        for (Shape sh: shapesToDelete) {
                            CmdRemoveShape cmdRemoveShape = new CmdRemoveShape(paintModel,sh);
                            cmdRemoveShape.execute();
                            addCommandToHistory(cmdRemoveShape,helper.makeCommand(sh,"Deleted"));
                        }

                        paintView.repaint();
                        clickPoint = new Point(0,0);

                        selection.clearSelectedShapesList();
                    }
                if(paintModel.getShapes().size() == 0){
                    isClearALlOrDelete = true;
                }

                manageObservers.manageMyObservers();
        }
    });

        btnClearAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CmdRemoveAllShapes cmdRemoveAllShapes = new CmdRemoveAllShapes(paintModel);
                cmdRemoveAllShapes.execute();
                addCommandToHistory(cmdRemoveAllShapes,"Cleared all shapes");

                paintView.repaint();

                isClearALlOrDelete = true;
                manageObservers.manageMyObservers();
            }
        });


        btnUndo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandListRepository.Undo(false);
                manageObservers.manageMyObservers();
                paintView.repaint();
            }
        });

        btnRedo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandListRepository.Redo(false);
                manageObservers.manageMyObservers();
                paintView.repaint();
            }
        });

        btnSaveLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportLogContext.exportFile();
            }
        });

        btnImportLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importLogContext.importFile();
                if(listModel.isEmpty() == false){
                    btnDownArrow.setVisible(true);
                }

            }
        });

        btnSaveImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportImageContext.exportFile();

            }
        });

        btnImportImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importImageContext.importFile();
            }
        });

        btnToBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CmdToBack cmdToBack = new CmdToBack(paintModel);
                cmdToBack.execute();
                addCommandToHistory(cmdToBack, "ToBack");

                paintView.repaint();
            }
        });

        btnToFront.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CmdToFront cmdToFront = new CmdToFront(paintModel);
                cmdToFront.execute();
                addCommandToHistory(cmdToFront, "ToFront");
                paintView.repaint();
            }
        });

        btnBringToFront.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CmdBringToFront cmdBringToFront = new CmdBringToFront(paintModel);
                cmdBringToFront.execute();
                addCommandToHistory(cmdBringToFront,"BringToFront");
                paintView.repaint();
            }
        });

        btnBringToBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CmdBringToBack cmdBringToBack = new CmdBringToBack(paintModel);
                cmdBringToBack.execute();
                addCommandToHistory(cmdBringToBack, "BringToBack");
                paintView.repaint();
            }
        });



        btnDownArrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Robot robot = null;

                    try {

                        robot = new Robot();

                        logText.requestFocus();

                        robot.keyPress(KeyEvent.VK_DOWN);
                        robot.keyRelease(KeyEvent.VK_DOWN);

                        robot.keyPress(KeyEvent.VK_ENTER);
                        robot.keyRelease(KeyEvent.VK_ENTER);

                        manageObservers.manageMyObservers();

                    } catch (AWTException e1) {
                        e1.printStackTrace();
                    }
                }

        });


        btnOutlineColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             activeOutlineColor =  colorDialogSetup.dialogSetOutlineColor(btnOutlineColor);
            }
        });

        btnInsideColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                activeInsideColor = colorDialogSetup.dialogSetInsideColor(btnInsideColor);
            }
        });


        btnDeselectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintModel.deselectAllShapes();
                paintView.repaint();
            }
        });


    }


    public void addCommandToHistory(ICommand command, String commandString){
        commandListRepository.addCommandToHistory(command,commandString,false);
    }

    public void showMainFrame(){
        paintForm.setVisible(true);
    }


    private void initComponents() {


        rbtnPoint = paintForm.getRbtnPoint();
        rbtnLine = paintForm.getRbtnLine();
        rbtnCircle = paintForm.getRbtnCircle();
        rbtnSquare = paintForm.getRbtnSquare();
        rbtnRectangle = paintForm.getRbtnRectangle();
        rbtnHexagon = paintForm.getRbtnHexagon();
        rbtnSelect = paintForm.getRbtnSelect();

        logText = paintForm.getLogTxt();
        listModel = paintForm.getListModel();
        btnSaveLog = paintForm.getBtnSaveLog();
        btnImportLog = paintForm.getBtnImportLog();
        btnSaveImage = paintForm.getBtnSaveImage();
        btnImportImage = paintForm.getBtnImportImage();

        btnUpdate = paintForm.getBtnUpdate();
        btnDelete = paintForm.getBtnDelete();
        btnClearAll = paintForm.getBtnClearAll();
        btnDeselectAll = paintForm.getBtnDeselectAll();

        btnUndo = paintForm.getBtnUndo();
        btnRedo = paintForm.getBtnRedo();


        btnDownArrow = paintForm.getBtnDownArrow();

        btnOutlineColor = paintForm.getBtnOutlineColor();
        btnInsideColor = paintForm.getBtnInsideColor();

        btnToBack = paintForm.getBtnToBack();
        btnToFront = paintForm.getBtnToFront();
        btnBringToBack = paintForm.getBtnBringToBack();
        btnBringToFront = paintForm.getBtnBringToFront();

    }


    public PaintView getPaintView() {
        return paintView;
    }

    public void setPaintView(PaintView paintView) {
        this.paintView = paintView;
    }

    public DefaultListModel getListModel() {
        return listModel;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public JButton getBtnUpdate() {
        return btnUpdate;
    }

    public JButton getBtnClearAll() {
        return btnClearAll;
    }

    public JButton getBtnUndo() {
        return btnUndo;
    }

    public JButton getBtnRedo() {
        return btnRedo;
    }

    public JButton getBtnSaveLog() {
        return btnSaveLog;
    }

    public JButton getBtnImportLog() {
        return btnImportLog;
    }

    public JButton getBtnSaveImage() {
        return btnSaveImage;
    }

    public JButton getBtnImportImage() {
        return btnImportImage;
    }

    public JButton getBtnToFront() {
        return btnToFront;
    }

    public JButton getBtnToBack() {
        return btnToBack;
    }

    public JButton getBtnBringToFront() {
        return btnBringToFront;
    }

    public JButton getBtnBringToBack() {
        return btnBringToBack;
    }

    public JButton getBtnDeselectAll() {
        return btnDeselectAll;
    }

    public PaintModel getPaintModel() {
        return paintModel;
    }

    public boolean isClearALlOrDelete() {
        return isClearALlOrDelete;
    }
}

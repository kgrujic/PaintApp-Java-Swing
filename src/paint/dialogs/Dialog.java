package paint.dialogs;

import geometry.Shape;
import paint.command.CmdUpdateShape;
import paint.command.CommandListRepository;
import paint.command.ICommand;
import paint.mvc.PaintModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class Dialog extends JDialog {


    // Dialog look
    protected DialogConfiguration dialogConfiguration;

    // Update
    protected boolean isUpdate;
    protected Shape oldShape;
    protected PaintModel paintModel;
    protected Color outlineColor;
    protected Color insideColor;

    // Create
    protected boolean readyToAdd;

    // Command
    private CommandListRepository commandListRepository;

    public abstract void setFieldsValuesForUpdate(Shape tmpShape);

    public abstract void setVisibilityToFalse();


    public void setArgumentsForUpdate(boolean isUpdate, Shape oldShape, PaintModel model) {
        this.isUpdate = isUpdate;
        this.oldShape = oldShape;
        this.paintModel = model;
    }

    public void setCommandListRepository(CommandListRepository commandListRepository){
        this.commandListRepository = commandListRepository;
    }

    public void setupContentPane(JPanel contentPane, JButton buttonOK) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
    }

    public void setupDialog(DialogConfiguration dialogConfiguration) {
        dialogConfiguration = new DialogConfiguration();
        dialogConfiguration.setSizeAndPosition(this);

    }

    public void Update(Shape oldShape,Shape newShape){
        CmdUpdateShape cmdUpdateShape = new CmdUpdateShape(paintModel, oldShape, newShape);
        cmdUpdateShape.execute();
        ICommand clone = cmdUpdateShape.clone();
        commandListRepository.addCommandToHistory(clone);

    }

    public void setupColorButtonsListeners(JButton btnOutlineColor, JButton btnInsideColor){

        if(btnInsideColor != null) {
            btnOutlineColor.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    outlineColor = paintModel.dialogSetOutlineColor(btnOutlineColor);
                }
            });

            btnInsideColor.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    insideColor = paintModel.dialogSetInsideColor(btnInsideColor);
                }
            });
        }
        else {
            btnOutlineColor.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    outlineColor = paintModel.dialogSetOutlineColor(btnOutlineColor);
                }
            });
        }
    }






    }






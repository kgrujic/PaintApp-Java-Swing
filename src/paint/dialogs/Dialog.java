package paint.dialogs;

import geometry.Shape;
import paint.command.CmdUpdateShape;
import paint.command.CommandListRepository;
import paint.helpers.ColorDialogSetup;
import paint.log.Helpers;
import paint.mvc.PaintModel;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;

public abstract class Dialog extends JDialog {

    // Dialog look
    protected DialogConfiguration dialogConfiguration;

    // Updated
    protected boolean isUpdate;
    protected Shape oldShape;
    protected PaintModel paintModel;

    protected Color outlineColor;
    protected Color insideColor;

    // Create
    protected boolean readyToAdd;

    // Command
    private CommandListRepository commandListRepository;
    private Helpers helper = new Helpers();
    private ColorDialogSetup colorDialogSetup = new ColorDialogSetup();

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

    public void setupDialogUpdate(DialogConfiguration dialogConfiguration, String shapeType) {
        dialogConfiguration = new DialogConfiguration();
        dialogConfiguration.setSizeAndPositionUpdate(this, shapeType);

    }

    public void setupDialogCreate(DialogConfiguration dialogConfiguration, String shapeType) {
        dialogConfiguration = new DialogConfiguration();
        dialogConfiguration.setSizeAndPositionCreate(this, shapeType);

    }


    public void Update(Shape oldShape,Shape newShape){


        CmdUpdateShape cmdUpdateShape = new CmdUpdateShape(oldShape, newShape);
        cmdUpdateShape.execute();

        commandListRepository.addCommandToHistory(cmdUpdateShape,helper.makeCommandUpdate(oldShape,newShape,"Updated"),false);

    }

    public void dialogInputValidation(JFormattedTextField input, JButton button){

        addMaskFormatter(input);


        input.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

                if (e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE) {

                    if (input.getText().trim().length() == 0) {
                        button.setEnabled(false);
                    }

                }
                else if (!(input.getText().equals(new String())) && !Character.isLetter(e.getKeyChar())) {
                    button.setEnabled(true);
                }
            }
        });

    }


    private void addMaskFormatter(JFormattedTextField input){
        try {

            MaskFormatter intMask = new MaskFormatter("#########");
            intMask.install(input);

        }
        catch (ParseException ex) {
            ex.printStackTrace();

        }
    }

    public void setupColorButtonsListeners(JButton btnOutlineColor, JButton btnInsideColor){

        if(btnInsideColor != null) {
            btnOutlineColor.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    outlineColor = colorDialogSetup.dialogSetOutlineColor(btnOutlineColor);
                }
            });

            btnInsideColor.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    insideColor = colorDialogSetup.dialogSetInsideColor(btnInsideColor);
                }
            });
        }
        else {
            btnOutlineColor.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    outlineColor = colorDialogSetup.dialogSetOutlineColor(btnOutlineColor);
                }
            });
        }
    }






    }






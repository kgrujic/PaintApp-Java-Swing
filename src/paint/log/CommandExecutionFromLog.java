package paint.log;

import geometry.Shape;
import paint.command.*;
import paint.mvc.PaintModel;
import paint.mvc.PaintView;
import paint.helpers.Selection;

public class CommandExecutionFromLog {

    private LineParser lineParser = new LineParser();
    private enum commands{Added, Removed, Cleared, Selected, Deselected ,Updated,Undo,Redo,ToFront,ToBack,BringToFront,BringToBack}

    private CommandListRepository commandListRepository;
    private PaintModel model;
    private PaintView view;
    private Selection selection;

    public  CommandExecutionFromLog(CommandListRepository commandListRepository, PaintModel model, PaintView view, Selection selection){
        this.commandListRepository = commandListRepository;
        this.model = model;
        this.view = view;
        this.selection = selection;
    }


    public void executeCommand(String str){


            if (str.contains(commands.Added.toString())) {

                CmdAddShape cmdAddShape = new CmdAddShape(model, lineParser.makeShape(str));
                cmdAddShape.execute();
                cloneCommandAndAddToHistory(cmdAddShape);
                view.repaint();

            } else if (str.contains(commands.Removed.toString())) {

                CmdRemoveShape cmdRemoveShape = new CmdRemoveShape(model, lineParser.makeShape(str));
                cmdRemoveShape.execute();
                cloneCommandAndAddToHistory(cmdRemoveShape);
                view.repaint();

            }
            else if (str.contains(commands.Selected.toString()) || str.contains(commands.Deselected.toString())) {

                int index = model.getShapes().indexOf(lineParser.makeShape(str));
                Shape shapeToSelect = model.getShapes().get(index);
                Shape newShape = shapeToSelect.cloneInstance();


                if(shapeToSelect.isSelected() == true){
                    newShape.setSelected(false);
                    CmdUpdateShape cmdSelectShape = new CmdUpdateShape(shapeToSelect,newShape);
                    cmdSelectShape.execute();
                    selection.removeSelectedShape(shapeToSelect);
                    cloneCommandAndAddToHistory(cmdSelectShape);
                }
                else {
                    newShape.setSelected(true);
                    CmdUpdateShape cmdSelectShape = new CmdUpdateShape(shapeToSelect,newShape);
                    cmdSelectShape.execute();
                    selection.addSelectedShape(shapeToSelect);
                    cloneCommandAndAddToHistory(cmdSelectShape);
                }

                view.repaint();

            }

            else if(str.contains(commands.Cleared.toString())){

                CmdRemoveAllShapes cmdRemoveAllShapes = new CmdRemoveAllShapes(model);
                cmdRemoveAllShapes.execute();
                cloneCommandAndAddToHistory(cmdRemoveAllShapes);
                view.repaint();

            }
            else if(str.contains(commands.Undo.toString())){

                commandListRepository.Undo(true);
                view.repaint();

            }
            else if (str.contains(commands.Redo.toString())){

                commandListRepository.Redo(true);
                view.repaint();

            }
            else if(str.contains(commands.Updated.toString())){

                updateCommand(str);
            }

            else if(str.contains(commands.ToBack.toString())){
                CmdToBack cmdToBack = new CmdToBack(model);
                cmdToBack.execute();
                view.repaint();
                cloneCommandAndAddToHistory(cmdToBack);
            }
            else if(str.contains(commands.ToFront.toString())){
                CmdToFront cmdToFront = new CmdToFront(model);
                cmdToFront.execute();
                view.repaint();
                cloneCommandAndAddToHistory(cmdToFront);
            }
            else if(str.contains(commands.BringToBack.toString())){
                CmdBringToBack cmdBringToBack = new CmdBringToBack(model);
                cmdBringToBack.execute();
                view.repaint();
                cloneCommandAndAddToHistory(cmdBringToBack);
            }
            else if(str.contains(commands.BringToFront.toString())){
                CmdBringToFront cmdBringToFront = new CmdBringToFront(model);
                cmdBringToFront.execute();
                view.repaint();
                cloneCommandAndAddToHistory(cmdBringToFront);
            }



    }

    public void updateCommand(String str){

        String[] shapes = str.split("TO");
        String originalShape = shapes[0];
        String newShape = shapes[1];

        int index = model.getShapes().indexOf(lineParser.makeShape(originalShape));
        Shape originalSh = model.getShapes().get(index);
        Shape newSh = lineParser.makeShape(newShape);


        CmdUpdateShape cmdUpdateShape = new CmdUpdateShape(originalSh,newSh);
        cmdUpdateShape.execute();
        cloneCommandAndAddToHistory(cmdUpdateShape);

        view.repaint();

    }



    public void cloneCommandAndAddToHistory(ICommand command){

        commandListRepository.addCommandToHistory(command,null,true);

    }
}

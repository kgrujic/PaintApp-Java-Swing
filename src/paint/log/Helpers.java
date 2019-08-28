package paint.log;

import geometry.*;
import paint.command.*;

public class Helpers {



    public String makeCommand(Shape shape, String command){
        String type="";

        switch(shape.getClass().getName()) {
            case "geometry.Point":
                Point tmpP = (Point) shape;
                type = command + " Point" + "|" + "X: " + tmpP.getX() + "|" + "Y: " + tmpP.getY() + "|" + "Outline color:" + tmpP.getOutlineColor() + "|" + "isSelected:" + tmpP.isSelected();
                break;

            case "geometry.Line":
                Line tmpL = (Line) shape;
                type = command + " Line" + "|" + "Start point: " + tmpL.getStartPoint() + "|" + "End point: " + tmpL.getEndPoint() + "|" + "Outline color: " + tmpL.getOutlineColor()+ "|" + "isSelected:" + tmpL.isSelected();
                break;

            case "geometry.Circle":
                Circle tmpC = (Circle) shape;
                type = command + " Circle" + "|" + "Center: " + tmpC.getCenter() + "|" + "R: " + tmpC.getR() + "|" + "Outline color: " + tmpC.getOutlineColor() + "|" + "Inside color: " + tmpC.getInsideColor()+ "|" + "isSelected:" + tmpC.isSelected();
                break;
            case "geometry.Square":
                Square tmpS = (Square) shape;
                type = command + " Square" + "|" + "Up left point: " + tmpS.getUpLeft() + "|" + "Side length: " + tmpS.getSideLength() + "|" + "Outline color: " + tmpS.getOutlineColor() + "|" + "Inside color: " + tmpS.getInsideColor()+ "|" + "isSelected:" + tmpS.isSelected();
                break;

            case "geometry.Rectangle":
                Rectangle tmpR = (Rectangle) shape;
                type = command + " Rectangle" + "|" + "Up left point: " + tmpR.getUpLeft() + "|" + "Side length: " + tmpR.getSideLength() + "|" + "Side width: " + tmpR.getSideWidth() + "|" + "Outline color: " + tmpR.getOutlineColor() + "|" + "Inside color: " + tmpR.getInsideColor()  + "|" + "isSelected:" + tmpR.isSelected();
                break;
            case "geometry.HexagonAdapter":
                HexagonAdapter tmpH= (HexagonAdapter) shape;
                type = command +" Hexagon" + "|" + "X: " + tmpH.getX() + "|" + "Y: " + tmpH.getY() + "|" + "R: " + tmpH.getR()+ "|" + "Outline color: " + tmpH.getOutlineColor() + "|" + "Inside color: " + tmpH.getInsideColor() + "|" + "isSelected:" + tmpH.isSelected();
                break;

        }


        return type;
    }



    public String makeCommandUpdate(Shape originalShape, Shape newShape, String command){
        String type="";

        switch(originalShape.getClass().getName()) {
            case "geometry.Point":
                Point tmpP = (Point) originalShape;
                Point tmpPN = (Point) newShape;
                type = command + " Point" + "|" + "X: " + tmpP.getX() + "|" + "Y: " + tmpP.getY() + "|" + "Outline color:" + tmpP.getOutlineColor() + " TO " + " Point" + "|" + "X: " + tmpPN.getX() + "|" + "Y: " + tmpPN.getY() + "|" + "Outline color:" + tmpPN.getOutlineColor();
                break;

            case "geometry.Line":
                Line tmpL = (Line) originalShape;
                Line tmpLN = (Line) newShape;
                type = command + " Line" + "|" + "Start point: " + tmpL.getStartPoint() + "|" + "End point: " + tmpL.getEndPoint() + "|" + "Outline color: " + tmpL.getOutlineColor() + " TO " +" Line" + "|" + "Start point: " + tmpLN.getStartPoint() + "|" + "End point: " + tmpLN.getEndPoint() + "|" + "Outline color: " + tmpLN.getOutlineColor();
                break;

            case "geometry.Circle":
                Circle tmpC = (Circle) originalShape;
                Circle tmpCN= (Circle) newShape;
                type = command + " Circle" + "|" + "Center: " + tmpC.getCenter() + "|" + "R: " + tmpC.getR() + "|" + "Outline color: " + tmpC.getOutlineColor() + "|" + "Inside color: " + tmpC.getInsideColor() + " TO " + " Circle" + "|" + "Center: " + tmpCN.getCenter() + "|" + "R: " + tmpCN.getR() + "|" + "Outline color: " + tmpCN.getOutlineColor() + "|" + "Inside color: " + tmpCN.getInsideColor();
                break;
            case "geometry.Square":
                Square tmpS = (Square) originalShape;
                Square tmpSN = (Square) newShape;
                type = command + " Square" + "|" + "Up left point: " + tmpS.getUpLeft() + "|" + "Side length: " + tmpS.getSideLength() + "|" + "Outline color: " + tmpS.getOutlineColor() + "|" + "Inside color: " + tmpS.getInsideColor() + " TO " + " Square" + "|" + "Up left point: " + tmpSN.getUpLeft() + "|" + "Side length: " + tmpSN.getSideLength() + "|" + "Outline color: " + tmpSN.getOutlineColor() + "|" + "Inside color: " + tmpSN.getInsideColor();
                break;

            case "geometry.Rectangle":
                Rectangle tmpR = (Rectangle) originalShape;
                Rectangle tmpRN = (Rectangle) originalShape;
                type = command + " Rectangle" + "|" + "Up left point: " + tmpR.getUpLeft() + "|" + "Side length: " + tmpR.getSideLength() + "|" + "Side width: " + tmpR.getSideWidth() + "|" + "Outline color: " + tmpR.getOutlineColor() + "|" + "Inside color: " + tmpR.getInsideColor() + " TO " + " Rectangle" + "|" + "Up left point: " + tmpRN.getUpLeft() + "|" + "Side length: " + tmpRN.getSideLength() + "|" + "Side width: " + tmpRN.getSideWidth() + "|" + "Outline color: " + tmpRN.getOutlineColor() + "|" + "Inside color: " + tmpRN.getInsideColor();
                break;
            case "geometry.HexagonAdapter":
                HexagonAdapter tmpH= (HexagonAdapter) originalShape;
                HexagonAdapter tmpHN= (HexagonAdapter) originalShape;
                type = command + " Hexagon" + "|" + "X: " + tmpH.getX() + "|" + "Y: " + tmpH.getY() + "|" + "R: " + tmpH.getR()+ "|" + "Outline color: " + tmpH.getOutlineColor() + "|" + "Inside color: " + tmpH.getInsideColor() + " TO " + " Hexagon" + "|" + "X: " + tmpHN.getX() + "|" + "Y: " + tmpHN.getY() + "|" + "R: " + tmpHN.getR()+ "|" + "Outline color: " + tmpHN.getOutlineColor() + "|" + "Inside color: " + tmpHN.getInsideColor();
                break;

        }


        return type;
    }


}

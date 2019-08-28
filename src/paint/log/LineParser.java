package paint.log;

import geometry.*;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import hexagon.Hexagon;

import java.awt.*;
import java.util.Arrays;

public class LineParser {

    private enum shapes{Point,Line,Circle,Square,Rectangle,Hexagon};

    public int[] parseLine(String str) {
        //Arrays.toString(ints)

        int[] ints = Arrays.stream(str.replaceAll("-", " -").split("[^-\\d]+"))
                .filter(s -> !s.matches("-?"))
                .mapToInt(Integer::parseInt).toArray();
        return ints;

    }

    public String lineContainsShape(String str){
        String[] words = str.split("\\|");

        String shape="";

        if (words[0].contains(LineParser.shapes.Point.toString())){
            shape= LineParser.shapes.Point.toString();
        }
        else if(words[0].contains(LineParser.shapes.Line.toString())){
            shape= LineParser.shapes.Line.toString();
        }
        else if(words[0].contains(LineParser.shapes.Circle.toString())){
            shape= LineParser.shapes.Circle.toString();
        }
        else if(words[0].contains(LineParser.shapes.Square.toString())){
            shape= LineParser.shapes.Square.toString();
        }
        else if(words[0].contains(LineParser.shapes.Rectangle.toString())){
            shape= LineParser.shapes.Rectangle.toString();
        }
        else if(words[0].contains(LineParser.shapes.Hexagon.toString())){
            shape= LineParser.shapes.Hexagon.toString();
        }
        else {
            shape="";
        }

        return shape;
    }

    public Shape makeShape(String str){
        int[] numbers = parseLine(str);
        Shape shapeToReturn = null;

        switch (lineContainsShape(str)){
            case "Point":
                shapeToReturn = makePoint(numbers);
                break;
            case "Line":
                shapeToReturn = makeLine(numbers);
                break;
            case "Circle":
                shapeToReturn = makeCircle(numbers);
                break;
            case "Square":
                shapeToReturn = makeSquare(numbers);
                break;
            case "Rectangle":
                shapeToReturn = makeRectangle(numbers);
                break;
            case "Hexagon":
                shapeToReturn = makeHexagon(numbers);
                break;
        }

        return shapeToReturn;
    }

    public Point makePoint(int[] numbers){
        return new Point(numbers[0],numbers[1],new Color(numbers[2],numbers[3],numbers[4]));
    }

    public Line makeLine(int[] numbers){
        return new Line(new Point(numbers[0],numbers[1]),new Point(numbers[2],numbers[3]),new Color(numbers[4],numbers[5],numbers[6]));
    }

    public Circle makeCircle(int[] numbers){
        return new Circle(new Point(numbers[0],numbers[1]),numbers[2],new Color(numbers[3],numbers[4],numbers[5]),new Color(numbers[6],numbers[7],numbers[8]));
    }

    public Square makeSquare(int[] numbers){
        return new Square(new Point(numbers[0],numbers[1]),numbers[2],new Color(numbers[3],numbers[4],numbers[5]),new Color(numbers[6],numbers[7],numbers[8]));
    }

    public geometry.Rectangle makeRectangle(int[] numbers){
        return new Rectangle(new Point(numbers[0],numbers[1]),numbers[2],numbers[3],new Color(numbers[4],numbers[5],numbers[6]),new Color(numbers[7],numbers[8],numbers[9]));
    }

    public HexagonAdapter makeHexagon(int[] numbers){

        Hexagon h = new Hexagon(numbers[0],numbers[1],numbers[2]);
        h.setBorderColor(new Color(numbers[3],numbers[4],numbers[5]));
        h.setAreaColor(new Color(numbers[6],numbers[7],numbers[8]));

        return new HexagonAdapter(h);
    }
}

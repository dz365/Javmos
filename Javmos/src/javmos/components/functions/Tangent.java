package javmos.components.functions;

import javmos.components.Point;
import java.util.HashSet;
import javmos.enums.FunctionType;
import javmos.JavmosGUI;
import static javmos.enums.FunctionType.FIRST_DERIVATIVE;

public class Tangent extends Trigonometric {

    public Tangent(JavmosGUI gui, String function) {
        super(gui, function, "tan");
        
        String equation = function;
        String[] array;
        int equalLocation = function.indexOf("=");
        String equation1 = equation.replace(equation.substring(0, equalLocation + 1), "").replace("tan(", " ").replace("x)", "");
        array = equation1.split(" ");
        a = Double.parseDouble(array[0]);
        k = Double.parseDouble(array[1]);
    }

    @Override
    public String getFirstDerivative() {
        return a * k + "sec^2(" + k + "x)";
    }

    @Override
    public String getSecondDerivative() {
        return 2 * a * k * k + "sec^2(" + k + "x)tan(" + k + "x)";
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        if (functionType == FIRST_DERIVATIVE) {
            return a * k * 1 / Math.pow(Math.cos(k * x), 2);
        } else {
            return a * Math.tan(k * x);
        }
    }
    
    @Override
    public HashSet<Point> getCriticalPoints() {
        return null;
    }
    
    @Override
    public HashSet<Point> getInflectionPoints() {
        return null;
    }
}
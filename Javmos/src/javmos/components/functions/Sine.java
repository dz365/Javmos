package javmos.components.functions;

import javmos.enums.FunctionType;
import javmos.JavmosGUI;
import static javmos.enums.FunctionType.FIRST_DERIVATIVE;
import static javmos.enums.FunctionType.SECOND_DERIVATIVE;
import static javmos.enums.FunctionType.THIRD_DERIVATIVE;

public class Sine extends Trigonometric {

    public Sine(JavmosGUI gui, String function) {
        super(gui, function, "sin");

        String equation = function;
        String[] array;
        int equalLocation = function.indexOf("=");
        String equation1 = equation.replace(equation.substring(0, equalLocation + 1), "").replace("sin(", " ").replace("x)", "");
        array = equation1.split(" ");
        a = Double.parseDouble(array[0]);
        k = Double.parseDouble(array[1]);
    }

    @Override
    public String getFirstDerivative() {
        return a * k + "cos(" + k + "x)";
    }

    @Override
    public String getSecondDerivative() {
        return -a * k * k + "sin(" + k + "x)";
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        if (functionType == FIRST_DERIVATIVE) {
            return a * k * Math.cos(k * x);
        } else if (functionType == SECOND_DERIVATIVE) {
            return -a * k * k * Math.sin(k * x);
        } else if (functionType == THIRD_DERIVATIVE) {
            return -a * k * k * k * Math.cos(k * x);
        } else {
            return a * Math.sin(k * x);
        }
    }
}
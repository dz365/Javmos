package javmos.components.functions;

import javmos.components.Point;
import java.util.HashSet;
import javmos.enums.FunctionType;
import javmos.JavmosGUI;
import static javmos.enums.FunctionType.FIRST_DERIVATIVE;

public class Logarithmic extends Function {

    public double a, base, k;

    public Logarithmic(JavmosGUI gui, String function) {
        super(gui);

        String equation = function;
        String[] array;

        if (equation.contains("log")) {
            int equalLocation = function.indexOf("=");
            String equation1 = equation.replace(function.substring(0, equalLocation + 1), "").replace(" ", "").replace("/", "").replace("log", " ").replace("(", " ").replace("x", "").replace(")", "");
            array = equation1.split(" ");
            a = Double.parseDouble(array[0]);
            base = Double.parseDouble(array[1]);
            k = Double.parseDouble(array[2]);
        } else {
            int equalLocation = function.indexOf("=");
            String equation1 = equation.replace(function.substring(0, equalLocation + 1), "").replace("x", "").replace(")", "").replace(" ", "").replace("/", "").replace("ln", " ").replace("(", "");
            array = equation1.split(" ");
            a = Double.parseDouble(array[0]);
            k = Double.parseDouble(array[1]);
        }
    }

    @Override
    public String getFirstDerivative() {
        if (gui.getEquationField().contains("log")) {
            return "f'(x) = " + a + "/(xln" + base + ")";
        } else {
            return "f'(x) = " + a + "/x";
        }
    }

    @Override
    public String getSecondDerivative() {
        if (gui.getEquationField().contains("log")) {
            return "f''(x) = " + -a + "/(x^2ln" + base + ")";
        } else {
            return "f''(x) = " + -a + "/x^2";
        }
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        Logarithmic function;
        if (functionType == FIRST_DERIVATIVE) {
            if (gui.getEquationField().contains("log")) {
                return a / x / Math.log(base);
            } else {
                return -a / Math.pow(x, 2);
            }
        } else {
            if (gui.getEquationField().contains("log")) {
                return a * (Math.log(k * x) / Math.log(base));
            } else {
                return a * Math.log(k * x);
            }
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
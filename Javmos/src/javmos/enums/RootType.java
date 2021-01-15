package javmos.enums;

import javmos.components.functions.Function;
import javmos.components.Point;
import java.awt.Color;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashSet;
import javmos.JavmosGUI;
import static javmos.enums.FunctionType.FIRST_DERIVATIVE;
import static javmos.enums.FunctionType.ORIGINAL;
import static javmos.enums.FunctionType.SECOND_DERIVATIVE;
import static javmos.enums.FunctionType.THIRD_DERIVATIVE;

public enum RootType {
    X_INTERCEPT(ORIGINAL, FIRST_DERIVATIVE, Color.GREEN, "X-INT"),
    CRITICAL_POINT(FIRST_DERIVATIVE, SECOND_DERIVATIVE, Color.RED, "CP"),
    INFLECTION_POINT(SECOND_DERIVATIVE, THIRD_DERIVATIVE, Color.BLUE, "POI");

    public final int ATTEMPTS = 20;
    public FunctionType functionOne;
    public FunctionType functionTwo;
    public Color rootColor;
    public String rootName;

    RootType(FunctionType functionOne, FunctionType functionTwo, Color rootColor, String rootName) {
        this.functionOne = functionOne;
        this.functionTwo = functionTwo;
        this.rootColor = rootColor;
        this.rootName = rootName;
    }

    public String getRootName() {
        return this.rootName;
    }

    public Color getRootColor() {
        return this.rootColor;
    }

    public java.util.HashSet<Point> getRoots(JavmosGUI gui, Function function, double minDomain, double maxDomain) {
        HashSet<Point> points = new HashSet<>();
        DecimalFormat formatter = new DecimalFormat("0.000");
        formatter.setRoundingMode(RoundingMode.HALF_DOWN);
        for (double i = minDomain; i < maxDomain; i += 0.05) {
            if (newtonsMethod(function, i, ATTEMPTS) != null) {
                points.add(new Point(gui, this, Double.parseDouble(formatter.format(newtonsMethod(function, i, ATTEMPTS))) + 0.0, Double.parseDouble(formatter.format(function.getValueAt(newtonsMethod(function, i, ATTEMPTS), ORIGINAL))) + 0.0));
            }
        }
        return points;
    }

    private Double newtonsMethod(Function function, double guess, int attempts) {
        double root;
        attempts--;

        root = guess - function.getValueAt(guess, functionOne) / function.getValueAt(guess, functionTwo);
        if (attempts == 0) {
            return null;
        } else if (Math.abs(root - guess) <= 0.000001) {
            return root;
        } else {
            return newtonsMethod(function, root, attempts);
        }
    }
}
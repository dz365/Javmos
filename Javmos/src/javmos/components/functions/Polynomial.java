package javmos.components.functions;

import java.util.logging.Level;
import java.util.logging.Logger;
import javmos.exceptions.PolynomialException;
import javmos.JavmosGUI;
import javmos.enums.FunctionType;
import static javmos.enums.FunctionType.FIRST_DERIVATIVE;
import static javmos.enums.FunctionType.ORIGINAL;
import static javmos.enums.FunctionType.SECOND_DERIVATIVE;
import static javmos.enums.FunctionType.THIRD_DERIVATIVE;

public class Polynomial extends Function {

    public final double[] coefficients;
    public final int[] degrees;

    public Polynomial(JavmosGUI gui, String function) throws PolynomialException {
        super(gui);
        try {
            int equalLocation = function.indexOf("=");
            function = function.replace(function.substring(0, equalLocation + 1), "").replace(" ", "").replace("-", "+-");

            String[] array = function.split("\\+"); // splits the polynomial by '+' first
            String[] array2 = new String[array.length];
            degrees = new int[array2.length];
            coefficients = new double[array2.length];

            for (int i = 0; i < array.length; i++) {
                if (array[i].contains("x")) { // checks to see if term is to nth degree such that n is greater to zero
                    if (array[i].contains("^")) {
                        array[i] = array[i].replace("^", "");
                    } else {
                        if (array[i].substring(array[i].indexOf("x"), array[i].length() - 1).length() > 0) {
                            throw new PolynomialException();
                        } else {
                            array[i] = array[i].replace("x", "x1");
                        }
                    }
                    array2 = array[i].split("x"); // splits by x to sort into coefficients and degrees
                    if (array2[0].length() == 0) { // when lacking a coefficient it is assumed that the coefficient is 1
                        coefficients[i] = 1;
                    } else {
                        if (array2[0].equals("-")) { // checks to see if the coefficient is negative and as well 1
                            coefficients[i] = -1;
                        } else {
                            coefficients[i] = Double.parseDouble(array2[0]);
                        }
                    }
                    if (array2[1].length() == 0) { // when lacking a degree it is assumed that the degree is 1
                        degrees[i] = 1;
                    } else {
                        degrees[i] = Integer.parseInt(array2[1]);
                    }
                } else { //when the degree is 0
                    if (array[i].length() == 0) {
                        coefficients[i] = 0;
                        degrees[i] = 1;
                    } else {
                        coefficients[i] = Double.parseDouble(array[i]);
                        degrees[i] = 0;
                    }
                }
            }

            for (int i = 0; i < this.coefficients.length; i++) {
                this.coefficients[i] = coefficients[i];
                this.degrees[i] = degrees[i];
            }

        } catch (Exception exception) {
            throw new PolynomialException(function + " is not a valid polynomial");
        }
    }

    public String getEquation() {
        String equation = "";
        for (int i = 0; i < degrees.length; i++) {
            if (i != 0) { // adds a plus sign
                if (coefficients[i] > 0) {
                    equation += "+";
                }
            }
            if (Math.abs(coefficients[i]) == 1) { //when the coefficient is 1
                if (degrees[i] != 0) { // when it's not to the power of 0
                    if (coefficients[i] > 0) {
                        equation += "x";
                    } else {
                        equation += "-x";
                    }
                } else { // when to the power of 0
                    if (coefficients[i] > 0) {
                        equation += "1";
                    } else {
                        equation += "-1";
                    }
                }
            } else { //when the coefficient is not 1
                if (degrees[i] != 0) { // when it's not to the power of 0
                    if (coefficients[i] != 0) {
                        equation += ((int) coefficients[i]) + "x";
                    }
                } else { // when to the power of 0
                    if (coefficients[i] != 0) {
                        equation += (int) coefficients[i];
                    }
                }
            }
            if (degrees[i] != 1 && degrees[i] != 0) { // adds the '^' when the degree is not 1 or 0
                equation += "^" + ((int) degrees[i]);
            }
        }
        return equation;
    }

    @Override
    public String getFirstDerivative() {
        if (getDerivative() == null) {
            return "f'(x)= 0.0";
        } else {
            return "f'(x)=" + getDerivative().getEquation();
        }
    }

    @Override
    public String getSecondDerivative() {
        if (getDerivative() != null) {
            return "f''(x)=" + getDerivative().getDerivative().getEquation();
        } else {
            return "f''(x)= 0.0";

        }
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        double calculation = 0;
        Polynomial temp = null;

        try {

            if (functionType == FIRST_DERIVATIVE) {
                temp = new Polynomial(gui, getFirstDerivative());
            } else if (functionType == SECOND_DERIVATIVE) {
                temp = new Polynomial(gui, getSecondDerivative());
            } else if (functionType == THIRD_DERIVATIVE) {
                temp = new Polynomial(gui, getSecondDerivative());
                temp = new Polynomial(gui, temp.getFirstDerivative());
            } else {
                temp = this;
            }
        } catch (Exception e) {
        }
        for (int i = 0; i < temp.coefficients.length; i++) {
            calculation += temp.coefficients[i] * Math.pow(x, temp.degrees[i]);
        }
        
        return calculation;
    }

    private Polynomial getDerivative() {
        try {
            String derivativeEquation = "";

            for (int i = 0; i < coefficients.length; i++) {
                if (degrees[i] != 0) {
                    if (i != 0 && coefficients[i] > 0) {
                        derivativeEquation += "+";
                    }
                    derivativeEquation += coefficients[i] * degrees[i] + "x^" + (degrees[i] - 1);
                }
            }
            Polynomial derivative = new Polynomial(gui, derivativeEquation);
            return derivative;
        } catch (PolynomialException ex) {
            return null;
        }
    }

}

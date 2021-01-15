package javmos.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javmos.JavmosGUI;
import javmos.components.JavmosPanel;
import javmos.components.functions.Cosine;
import javmos.components.functions.Logarithmic;
import javmos.components.functions.Polynomial;
import javmos.components.functions.Sine;
import javmos.components.functions.Tangent;

public class DrawListener implements ActionListener {

    private final JavmosGUI gui;
    private final JavmosPanel panel;

    public DrawListener(JavmosGUI gui, JavmosPanel panel) {
        this.gui = gui;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        panel.components.clear();

        try {
            String equation = gui.getEquationField();
            if (equation.contains("sin")) {
                panel.setFunction(new Sine(gui, equation));
            } else if (equation.contains("cos")) {
                panel.setFunction(new Cosine(gui, equation));
            } else if (equation.contains("tan")) {
                panel.setFunction(new Tangent(gui, equation));
            } else if (equation.contains("log") || equation.contains("ln")) {
                panel.setFunction(new Logarithmic(gui, equation));
            } else {
                panel.setFunction(new Polynomial(gui, equation));
            }
            gui.setFirstDerivativeLabel(panel.getFunction().getFirstDerivative());
            gui.setSecondDerivativeLabel(panel.getFunction().getSecondDerivative());
        } catch (Exception ex) {
        }
        panel.repaint();
    }
}
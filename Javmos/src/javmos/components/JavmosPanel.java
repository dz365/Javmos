package javmos.components;

import javmos.components.functions.Function;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import javax.swing.JPanel;
import javmos.JavmosGUI;
import javmos.listeners.PointClickListener;

public class JavmosPanel extends JPanel {

    private final JavmosGUI gui;
    public final LinkedList<JavmosComponent> components;


    public JavmosPanel(JavmosGUI gui) {
        this.gui = gui;
        this.components = new LinkedList<>();
    }

    public Function getFunction() {
        for (JavmosComponent function : components) {
            if (function instanceof Function) {
                return (Function) function;
            }
        }
        return null;
    }

    public void setPlane(CartesianPlane plane) {
        components.add(plane);
    }

    public void setFunction(Function function) {
        PointClickListener pointClick = new PointClickListener(gui);
        this.addMouseListener(pointClick);
        LinkedList<Point> point = new LinkedList<>();
        point.clear();
              
        if (function != null) {   
            components.add(function);
            if (function.getXIntercepts() != null) {
                components.addAll(function.getXIntercepts());
                point.addAll(getFunction().getXIntercepts());
                pointClick.setPoints(point);               
            }
            if (function.getCriticalPoints() != null) {
                components.addAll(function.getCriticalPoints());
                point.addAll(getFunction().getCriticalPoints());
                pointClick.setPoints(point);
            }
            if (function.getInflectionPoints() != null) {
                components.addAll(function.getInflectionPoints());
                point.addAll(getFunction().getInflectionPoints());
                pointClick.setPoints(point);
            }
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        CartesianPlane plane = new CartesianPlane(gui);
        plane.draw((Graphics2D) graphics);
        
        if (getFunction() != null) {
            getFunction().draw((Graphics2D) graphics);
        }

        for (int i = 1; i < components.size(); i++) {
            components.get(i).draw((Graphics2D) graphics);
        }
    }
}
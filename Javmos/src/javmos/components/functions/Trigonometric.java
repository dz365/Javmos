package javmos.components.functions;

import javmos.JavmosGUI;

public abstract class Trigonometric extends Function {

    protected double a, k;

    public Trigonometric(JavmosGUI gui, String function, String name) {
        super(gui);
    }
}
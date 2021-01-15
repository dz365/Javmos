package javmos.components.functions;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.HashSet;
import javmos.enums.FunctionType;
import javmos.JavmosGUI;
import javmos.components.JavmosComponent;
import javmos.components.Point;
import static javmos.enums.FunctionType.ORIGINAL;
import static javmos.enums.RootType.CRITICAL_POINT;
import static javmos.enums.RootType.INFLECTION_POINT;
import static javmos.enums.RootType.X_INTERCEPT;

public abstract class Function extends JavmosComponent {

    protected Function(JavmosGUI gui) {
        super(gui);
    }

    @Override
    public void draw(java.awt.Graphics2D graphics2D) {
        double width = gui.getPlaneWidth() / 2;
        double height = gui.getPlaneHeight() / 2;
        double zoom = gui.getZoom();
        double domainStep = gui.getDomainStep();
        double rangeStep = gui.getRangeStep();

        graphics2D.setColor(Color.MAGENTA);
        graphics2D.setStroke(new BasicStroke(3));

        double value = gui.getPlaneWidth() / 2 / gui.getZoom() * gui.getDomainStep();

        if (-value > gui.getMinDomain() && value < gui.getMaxDomain()) {
            for (double i = -value; i <= value; i += 0.001) {
                if (getValueAt(i, ORIGINAL) >= gui.getMinRange() && getValueAt(i, ORIGINAL) <= gui.getMaxRange()) {
                    graphics2D.draw(new Line2D.Double(width + i * zoom / domainStep, height - getValueAt(i, ORIGINAL) * zoom / rangeStep, width + (i + 0.001) * zoom / domainStep, height - getValueAt(i + 0.001, ORIGINAL) * zoom / rangeStep));
                }
            }
        } else {
            for (double i = gui.getMinDomain(); i <= gui.getMaxDomain(); i += 0.001) {
                if (getValueAt(i, ORIGINAL) >= gui.getMinRange() && getValueAt(i, ORIGINAL) <= gui.getMaxRange()) {
                    graphics2D.draw(new Line2D.Double(width + i * zoom / domainStep, height - getValueAt(i, ORIGINAL) * zoom / rangeStep, width + (i + 0.001) * zoom / domainStep, height - getValueAt(i + 0.001, ORIGINAL) * zoom / rangeStep));
                }
            }
        }
    }

    public HashSet<Point> getXIntercepts() {
        double i = Math.round(gui.getPlaneWidth() / 2 / gui.getZoom() * gui.getDomainStep() * 1) / 1.00;

        if (-i > gui.getMinDomain() && i < gui.getMaxDomain()) {
            return X_INTERCEPT.getRoots(gui, this, -i, i);
        } else {
            return X_INTERCEPT.getRoots(gui, this, gui.getMinDomain(), gui.getMaxDomain());
        }
    }

    public HashSet<Point> getCriticalPoints() {
        double i = Math.round(gui.getPlaneWidth() / 2 / gui.getZoom() * gui.getDomainStep() * 1) / 1.00;

        if (-i > gui.getMinDomain() && i < gui.getMaxDomain()) {
            return CRITICAL_POINT.getRoots(gui, this, -i, i);
        } else {
            return CRITICAL_POINT.getRoots(gui, this, gui.getMinDomain(), gui.getMaxDomain());
        }
    }

    public HashSet<Point> getInflectionPoints() {
        double i = Math.round(gui.getPlaneWidth() / 2 / gui.getZoom() * gui.getDomainStep() * 1) / 1.00;

        if (-i > gui.getMinDomain() && i < gui.getMaxDomain()) {
            return INFLECTION_POINT.getRoots(gui, this, -i, i);
        } else {
            return INFLECTION_POINT.getRoots(gui, this, gui.getMinDomain(), gui.getMaxDomain());
        }
    }

    public abstract String getFirstDerivative();

    public abstract String getSecondDerivative();

    public abstract double getValueAt(double x, FunctionType functionType);
}
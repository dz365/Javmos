package javmos.components;

import java.awt.geom.Ellipse2D;
import javmos.JavmosGUI;
import javmos.enums.RootType;

public class Point extends JavmosComponent {

    public final int RADIUS = 5;
    public final RootType rootType;
    public double x;
    public double y;
    public Ellipse2D.Double point;

    public Point(JavmosGUI gui, RootType type, double x, double y) {
        super(gui);
        this.rootType = type;
        this.x = x;
        this.y = y;
    }

    public void draw(java.awt.Graphics2D graphics2D) {
        graphics2D.setColor(rootType.getRootColor());
        graphics2D.draw(getPoint());
        graphics2D.fill(getPoint());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Ellipse2D.Double getPoint() {
        double zoom = gui.getZoom();
        double domainStep = gui.getDomainStep();
        double rangeStep = gui.getRangeStep();
        // Returns the pixel pocations of the points
        return new Ellipse2D.Double(400 + getX() * zoom / domainStep - RADIUS, 400 - getY() * zoom / rangeStep - RADIUS, RADIUS * 2, RADIUS * 2);
    }

    public RootType getRootType() {
        return rootType;
    }

    @Override
    public int hashCode() {
        return getPoint().hashCode();
    }

    @Override
    public boolean equals(Object object) {
        return object.hashCode() == hashCode();
    }

    @Override
    public String toString() {
        return getRootType() + "(" + getX() + ", " + getY() + ")";
    }
}
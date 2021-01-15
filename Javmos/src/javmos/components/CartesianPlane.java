package javmos.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.math.BigDecimal;
import javmos.JavmosGUI;

public class CartesianPlane extends JavmosComponent {

    public CartesianPlane(JavmosGUI gui) {
        super(gui);
    }

    @Override
    public void draw(java.awt.Graphics2D graphics2D) {
        int zoom = (int) gui.getZoom();
        BigDecimal domainStep = BigDecimal.valueOf(gui.getDomainStep());
        BigDecimal rangeStep = BigDecimal.valueOf(gui.getRangeStep());
        BigDecimal x = BigDecimal.valueOf(gui.getDomainStep());
        BigDecimal y = BigDecimal.valueOf(gui.getRangeStep());

        Graphics2D graphics = (Graphics2D) graphics2D;
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 800, 800);
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("SansSerif", Font.BOLD, 15));

        for (int i = 0; i < 400; i += zoom) {
            graphics.drawLine(400 - i, 0, 400 - i, 800);    //draws vertical grid lines to the left of the origin
            graphics.drawString(String.valueOf(x.multiply(BigDecimal.valueOf(-1.0)).doubleValue()), 400 - i - zoom, 400);
            graphics.drawLine(400 + i, 0, 400 + i, 800);    // draws vertical grid lines to the right of the origin
            graphics.drawString(String.valueOf(x.doubleValue()), 400 + i + zoom, 400);
            graphics.drawLine(0, 400 - i, 800, 400 - i);    // draws horizontal grid lines above the origin
            graphics.drawString(String.valueOf(y.doubleValue()), 403, 400 - i - zoom);
            graphics.drawLine(0, 400 + i, 800, 400 + i);    //draws horizontal grid lines below the origin
            graphics.drawString(String.valueOf(y.multiply(BigDecimal.valueOf(-1.0)).doubleValue()), 403, 400 + i + zoom);
            x = x.add(domainStep);
            y = y.add(rangeStep);
        }
        //draws the x-axis and y-axis
        graphics.setStroke(new BasicStroke(3));
        graphics.drawLine(400, 0, 400, 800);
        graphics.drawLine(0, 400, 800, 400);
        graphics.drawString(String.valueOf(0), 405, 400);
    }
}

package csci2020u.lab09.components.functions;

import java.awt.Graphics2D;
import java.util.HashSet;
import csci2020u.lab09.GraphGUI;
import csci2020u.lab09.components.GraphComponent;
import csci2020u.lab09.components.Point;
import csci2020u.lab09.enums.FunctionType;
import csci2020u.lab09.enums.RootType;

public abstract class Function extends GraphComponent {

    protected Function(GraphGUI gui) {
        super(gui);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        /*
         * Graph the function by incrementing x at small steps and drawing lines between points.
         * - Increment x at a small step, s (e.g., 0.02)
         * - Call getValueAt() to get f(x). Use `FunctionType.ORIGINAL` as the function type.
         * - Draw a line from (x, f(x)) to (x + s, f(x + s))
         *
         * Optional:
         * - The drawing function responds to zooming and domain/range scales.
         * - The drawing function only draws within the visible domain and range.
         */
        double step = 0.02; // Small step for incrementing x
        double zoom = gui.getZoom(); // Current zoom level
        double domainStep = gui.getDomainStep(); // Domain scaling factor
        double rangeStep = gui.getRangeStep(); // Range scaling factor

        double minX = Math.max(gui.getMinDomain(), -gui.getPlaneWidth() / (2 * zoom * domainStep));
        double maxX = Math.min(gui.getMaxDomain(), gui.getPlaneWidth() / (2 * zoom * domainStep));

        double prevX = minX;
        double prevY = getValueAt(prevX, FunctionType.ORIGINAL);

        int prevScreenX = (int) (prevX * zoom * domainStep + gui.getPlaneWidth() / 2);
        int prevScreenY = (int) (-prevY * zoom * rangeStep + gui.getPlaneHeight() / 2);

        for (double x = minX + step; x <= maxX; x += step) {
            double y = getValueAt(x, FunctionType.ORIGINAL);

            int screenX = (int) (x * zoom * domainStep + gui.getPlaneWidth() / 2);
            int screenY = (int) (-y * zoom * rangeStep + gui.getPlaneHeight() / 2);

            graphics2D.drawLine(prevScreenX, prevScreenY, screenX, screenY);

            prevScreenX = screenX;
            prevScreenY = screenY;
        }
    }

    public HashSet<Point> getXIntercepts() {
        return RootType.X_INTERCEPT.getRoots(gui, this, Math.max(gui.getPlaneWidth() / gui.getZoom() * gui.getDomainStep() / -2, gui.getMinDomain()), Math.min(gui.getPlaneWidth() / gui.getZoom() * gui.getDomainStep() / 2, gui.getMaxDomain()));
    }

    public HashSet<Point> getCriticalPoints() {
        return RootType.CRITICAL_POINT.getRoots(gui, this, Math.max(gui.getPlaneWidth() / gui.getZoom() * gui.getDomainStep() / -2, gui.getMinDomain()), Math.min(gui.getPlaneWidth() / gui.getZoom() * gui.getDomainStep() / 2, gui.getMaxDomain()));
    }

    public HashSet<Point> getInflectionPoints() {
        return RootType.INFLECTION_POINT.getRoots(gui, this, Math.max(gui.getPlaneWidth() / gui.getZoom() * gui.getDomainStep() / -2, gui.getMinDomain()), Math.min(gui.getPlaneWidth() / gui.getZoom() * gui.getDomainStep() / 2, gui.getMaxDomain()));
    }

    public abstract String getFirstDerivative();

    public abstract String getSecondDerivative();

    public abstract double getValueAt(double x, FunctionType functionType);
}
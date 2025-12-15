package csci2020u.lab09.components.functions;

import java.util.HashSet;
import csci2020u.lab09.GraphGUI;
import csci2020u.lab09.components.Point;
import csci2020u.lab09.enums.FunctionType;
import csci2020u.lab09.enums.RootType;

public class Cosine extends Trignometric {

    public Cosine(GraphGUI gui, String function) {
        super(gui, function, "cos");
    }

    @Override
    public String getFirstDerivative() {
        /*
         * The first derivative of a*cos(kx) is -a*k*sin(kx).
         */
        return String.format("%.2f*sin(%.2fx)", -a * k, k);
    }

    @Override
    public String getSecondDerivative() {
        /*
         * The second derivative of a*cos(kx) is -a*k²*cos(kx).
         */
        return String.format("%.2f*cos(%.2fx)", -a * Math.pow(k, 2), k);
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        /*
         * Return the y-value at x given the function type.
         * - ORIGINAL: y = a*cos(kx)
         * - FIRST_DERIVATIVE: y = -a*k*sin(kx)
         * - SECOND_DERIVATIVE: y = -a*k²*cos(kx)
         * - THIRD_DERIVATIVE: y = a*k³*sin(kx)
         */
        double kx = k * x; // Simplify calculations

        switch (functionType) {
            case ORIGINAL:
                return a * Math.cos(kx);
            case FIRST_DERIVATIVE:
                return -a * k * Math.sin(kx);
            case SECOND_DERIVATIVE:
                return -a * Math.pow(k, 2) * Math.cos(kx);
            case THIRD_DERIVATIVE:
                return a * Math.pow(k, 3) * Math.sin(kx);
            default:
                throw new IllegalArgumentException("Invalid function type: " + functionType);
        }
    }

    @Override
    public HashSet<Point> getCriticalPoints() {
        HashSet<Point> criticalPoints = new HashSet<>();
        double x;
        for (int m = -10; m <= 10; m++) { // Adjust range as needed
            x = m * Math.PI / k;
            double y = getValueAt(x, FunctionType.ORIGINAL);
            criticalPoints.add(new Point(gui, RootType.CRITICAL_POINT, x, y));
        }
        return criticalPoints;
    }

    @Override
    public HashSet<Point> getInflectionPoints() {
        HashSet<Point> inflectionPoints = new HashSet<>();
        double x;
        for (int m = -10; m <= 10; m++) { // Adjust range as needed
            x = (2 * m + 1) * Math.PI / (2 * k);
            double y = getValueAt(x, FunctionType.ORIGINAL);
            inflectionPoints.add(new Point(gui, RootType.INFLECTION_POINT, x, y));
        }
        return inflectionPoints;
    }
}

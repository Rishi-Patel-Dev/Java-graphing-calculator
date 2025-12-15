package csci2020u.lab09.components.functions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import csci2020u.lab09.GraphGUI;
import csci2020u.lab09.components.Point;
import csci2020u.lab09.enums.FunctionType;
import csci2020u.lab09.enums.RootType;

public class Tangent extends Trignometric {

    private static final int PRECISION = 20; //number of decimal places for precision

    public Tangent(GraphGUI gui, String function) {
        super(gui, function, "tan");
    }

    @Override
    public String getFirstDerivative() {
        /*
         * The first derivative of a*tan(kx) is a*k*sec²(kx).
         */
        return String.format("%.2f*sec²(%.2fx)", a * k, k);
    }

    @Override
    public String getSecondDerivative() {
        /*
         * The second derivative of a*tan(kx) is 2*a*k²*sec²(kx)*tan(kx).
         */
        return String.format("%.2f*sec²(%.2fx)*tan(%.2fx)", 2 * a * Math.pow(k, 2), k, k);
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        /*
         * Return the y-value at x given the function type.
         * - ORIGINAL: y = a*tan(kx)
         * - FIRST_DERIVATIVE: y = a*k*sec²(kx)
         * - SECOND_DERIVATIVE: y = 2*a*k²*sec²(kx)*tan(kx)
         * - THIRD_DERIVATIVE: y = 2*a*k³*sec²(kx)(2*tan²(kx) + sec²(kx))
         */
        BigDecimal aBig = new BigDecimal(a);
        BigDecimal kBig = new BigDecimal(k);
        BigDecimal xBig = new BigDecimal(x);
        BigDecimal kxBig = kBig.multiply(xBig);

        BigDecimal cosKxBig = new BigDecimal(Math.cos(kxBig.doubleValue()));
        BigDecimal secSquaredKxBig = BigDecimal.ONE.divide(cosKxBig.pow(2), PRECISION, RoundingMode.HALF_UP);

        BigDecimal tanKxBig = new BigDecimal(Math.tan(kxBig.doubleValue()));

        switch (functionType) {
            case ORIGINAL:
                return aBig.multiply(tanKxBig).doubleValue();
            case FIRST_DERIVATIVE:
                return aBig.multiply(kBig).multiply(secSquaredKxBig).doubleValue();
            case SECOND_DERIVATIVE:
                return new BigDecimal(2).multiply(aBig).multiply(kBig.pow(2)).multiply(secSquaredKxBig).multiply(tanKxBig).doubleValue();
            case THIRD_DERIVATIVE:
                BigDecimal tanSquaredKxBig = tanKxBig.pow(2);
                BigDecimal term1 = new BigDecimal(2).multiply(tanSquaredKxBig);
                BigDecimal term2 = secSquaredKxBig;
                return new BigDecimal(2).multiply(aBig).multiply(kBig.pow(3)).multiply(secSquaredKxBig).multiply(term1.add(term2)).doubleValue();
            default:
                throw new IllegalArgumentException("Invalid function type: " + functionType);
        }
    }

    @Override
    public HashSet<Point> getCriticalPoints() {
        HashSet<Point> criticalPoints = new HashSet<>();
        double x;
        for (int m = -10; m <= 10; m++) { // Adjust range as needed
            x = (2 * m + 1) * Math.PI / (2 * k);
            if (!Double.isInfinite(a * Math.tan(k * x))) { // Avoid adding points where tan(kx) is undefined
                criticalPoints.add(new Point(gui, RootType.CRITICAL_POINT, x, a * Math.tan(k * x)));
            }
        }
        return criticalPoints;
    }

    @Override
    public HashSet<Point> getInflectionPoints() {
        HashSet<Point> inflectionPoints = new HashSet<>();
        double x;
        for (int m = -10; m <= 10; m++) { // Adjust range as needed
            x = m * Math.PI / k;
            inflectionPoints.add(new Point(gui, RootType.INFLECTION_POINT, x, a * Math.tan(k * x)));
        }
        return inflectionPoints;
    }
}
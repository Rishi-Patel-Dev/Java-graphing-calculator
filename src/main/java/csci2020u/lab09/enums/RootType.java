package csci2020u.lab09.enums;

import java.awt.Color;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashSet;
import csci2020u.lab09.GraphGUI;
import csci2020u.lab09.components.Point;
import csci2020u.lab09.components.functions.Function;

public enum RootType {

    X_INTERCEPT(Color.decode("#C2BCFF"), "X-INT", FunctionType.ORIGINAL, FunctionType.FIRST_DERIVATIVE),
    CRITICAL_POINT(Color.decode("#6330DB"), "CP", FunctionType.FIRST_DERIVATIVE, FunctionType.SECOND_DERIVATIVE),
    INFLECTION_POINT(Color.decode("#D331E2"), "IP", FunctionType.SECOND_DERIVATIVE, FunctionType.THIRD_DERIVATIVE);

    private final Color color;
    private final String name;
    private final int ATTEMPTS = 100;
    private final FunctionType functionOne;
    private final FunctionType functionTwo;

    RootType(Color color, String name, FunctionType functionOne, FunctionType functionTwo) {
        this.color = color;
        this.name = name;
        this.functionOne = functionOne;
        this.functionTwo = functionTwo;
    }

    public Color getPointColor() {
        return color;
    }

    public String getPointName() {
        return name;
    }

    public HashSet<Point> getRoots(GraphGUI gui, Function function, double minDomain, double maxDomain) {
        DecimalFormat format = new DecimalFormat("0.000");
        format.setRoundingMode(RoundingMode.HALF_EVEN);
        HashSet<Point> addPoint = new HashSet<>();

        for (double i = minDomain - 0.01; i < maxDomain + 0.01; i += 0.01) {
            double x = newtonsMethod(function, i, ATTEMPTS);
            
            if (!Double.isNaN(x) && Double.parseDouble(format.format(function.getValueAt(x, FunctionType.ORIGINAL))) <= gui.getMaxRange() && Double.parseDouble(format.format(function.getValueAt(x, FunctionType.ORIGINAL))) >= gui.getMinRange() && Double.parseDouble(format.format(x)) <= maxDomain && Double.parseDouble(format.format(x)) >= minDomain) {
                Point point = new Point(gui, this, Double.parseDouble(format.format(x)), Double.parseDouble(format.format(function.getValueAt(x, FunctionType.ORIGINAL))));
                addPoint.add(point);
            }
        }
        return addPoint;
    }

    public double newtonsMethod(Function function, double guess, int attempts) {
        /*
         * Implement Newton's method to find roots of the function.
         * - Use functionOne for the type of the current function.
         * - Use functionTwo for the type of the current function's derivative.
         * - Return Double.NaN if no roots can be found.
         */
        double epsilon = 1e-6;
        double tolerance = 1e-6;
        double x = guess;

        for (int i = 0; i < attempts; i++) {
            double f = function.getValueAt(x, functionOne); // f(x)
            double fPrime = function.getValueAt(x, functionTwo); // f'(x)

            // check if the derivative is zero to avoid division by zero
            if (Math.abs(fPrime) < epsilon) {
                return Double.NaN; //no convergence
            }

            double xNext = x - f / fPrime; // newton's update

            //check for convergence
            if (Math.abs(xNext - x) < epsilon) {
                //check if f(xNext) is close to zero
                if (Math.abs(function.getValueAt(xNext, functionOne)) < tolerance) {
                    return xNext; // root found
                } else {
                    return Double.NaN; //no root found
                }
            }

            x = xNext; //update x for the next iteration
        }

        return Double.NaN; // no root found within the maximum number of attempts
    }
}

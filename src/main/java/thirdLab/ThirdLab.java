package thirdLab;

import static java.lang.Math.*;

public class ThirdLab {

    public static double log2(double x) {
        return Math.log(x) / Math.log(2);
    }

    public static double stepTrap(double h, double x, int n, Functional func) {
        double result = 0.0;
        for (int i = 0; i < n; i++) {
            result += ((func.calc(i * h + x) + func.calc((i + 1) * h + x)) / 2) * h;
        }
        return result;
    }

    public static double stepPar(double h, double x, int n, Functional func) {
        double result = 0.0;
        for (int i = 0; i < n; i++) {
            result += (func.calc(i * h + x) + 4 * func.calc((i + 0.5) * h + x) + func.calc((i + 1) * h + x)) * h / 6;
        }
        return result;
    }

    public static double stepSquare(double h, double x, int n, Functional func) {
        double result = 0.0;
        double delta = h / 6;
        for (int i = 0; i < n; i++) {
            result += (func.calc(i * h + x) + 3 * func.calc(2 * delta + i * h + x) +
                    3 * func.calc(4 * delta + i * h + x) + func.calc(6 * delta + i * h + x)) * h / 8;
        }
        return result;
    }


    public static double trapezoid(double x, double y, int n, Functional func) {
        double h = (y - x) / n;
        double result = 0.0;
        for (int i = 0; i < n; i++) {
            result += ((func.calc(i * h + x) + func.calc((i + 1) * h + x)) / 2) * h;
        }
        return result;
    }

    public static double parabola(double x, double y, int n, Functional func) {
        double result = 0.0;
        double h = (y - x) / n;

        for (int i = 0; i < n; i++) {
            result += (func.calc(i * h + x) + 4 * func.calc((i + 0.5) * h + x) + func.calc((i + 1) * h + x)) * h / 6;
        }

        return result;
    }

    public static double squareFormula(double x, double y, int n, Functional func) {
        double result = 0.0;
        double h = (y - x) / n;

        double delta = h / 6;
        for (int i = 0; i < n; i++) {
            result += (func.calc(i * h + x) + 3 * func.calc(2 * delta + i * h + x) +
                    3 * func.calc(4 * delta + i * h + x) + func.calc(6 * delta + i * h + x)) * h / 8;
        }

        return result;
    }

    public static double accuracyTrapezoid(double x, double y, int n, Functional func) {
        double delta1 = (y - x) / n;
        double delta2 = delta1 / 2;
        double delta3 = delta2 / 2;

        double result = log2(
                (stepTrap(delta1, x, n, func) - stepTrap(delta2, x, n * 2, func)) /
                        (stepTrap(delta2, x, n * 2, func) - stepTrap(delta3, x, n * 4, func))
        );

        return result;
    }

    public static double accuracyParabola(double x, double y, int n, Functional func) {
        double delta1 = (y - x) / n;
        double delta2 = delta1 / 2;
        double delta3 = delta2 / 2;

        double result = log2(
                (stepPar(delta1, x, n, func) - stepPar(delta2, x, n * 2, func)) /
                        (stepPar(delta2, x, n * 2, func) - stepPar(delta3, x, n * 4, func))
        );

        return result;
    }

    public static double accuracySquareFormula(double x, double y, int n, Functional func) {
        double delta1 = (y - x) / n;
        double delta2 = delta1 / 2;
        double delta3 = delta2 / 2;

        double result = log2(
                (stepSquare(delta1, x, n, func) - stepSquare(delta2, x, n * 2, func)) /
                        (stepSquare(delta2, x, n * 2, func) - stepSquare(delta3, x, n * 4, func))
        );

        return result;
    }


    public static void main(String[] args) {
        Functional func = (x -> exp(x) * cos(x));

        double result = 0.0;

        result = trapezoid(5.0, 7.0, 40, func);
        System.out.println(result);
        result = parabola(5.0, 7.0, 40, func);
        System.out.println(result);
        result = squareFormula(5.0, 7.0, 40, func);
        System.out.println(result);
        result = accuracyTrapezoid(5.0, 7.0, 50, func);
        System.out.println(result);
        result = accuracyParabola(5.0, 7.0, 50, func);
        System.out.println(result);
        result = accuracySquareFormula(5.0, 7.0, 50, func);

        System.out.println(result);
    }
}

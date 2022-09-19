import java.util.Scanner;

public class FirstLab {
    private static final double negativeEpsilon = -0.01;

    private static final int FIRST_CASE = 1;
    private static final int SECOND_CASE = 2;
    private static final int THIRD_CASE = 3;
    private static final int FOURTH_CASE = 4;

    private static final double INFINITY = 10000000000.0;
    private static final double NEGATIVE_INFINITY = -10000000000.0;

    private static final double ERROR = 12345.6;

    private static double epsilon;
    private static double delta;
    private static double a;
    private static double b;
    private static double c;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type epsilon: ");
        epsilon = scanner.nextDouble();
        System.out.println("Please type delta: ");
        delta = scanner.nextDouble();
        System.out.println("Now type 'a', 'b' and 'c': ");
        a = scanner.nextLong();
        b = scanner.nextLong();
        c = scanner.nextLong();

        double aDif = 3;
        double bDif = 2 * a;
        double cDif = b;

        double discriminant = a*a - aDif*cDif;

        if (discriminant < 0) {
            int result = funcValueCases(0);
            if (result == FIRST_CASE) {
                System.out.println(0);
            }
            else if (result == SECOND_CASE) {
                System.out.println(bisection(0, INFINITY));
            }
            else if (result == THIRD_CASE) {
                System.out.println(bisection(NEGATIVE_INFINITY, 0));
            }
            else {
                System.out.println("Something went wrong. Check input");
            }
        }
        else {
            double alpha = (0 - bDif/2 - Math.sqrt(discriminant))/aDif;
            double betta = (0 - bDif/2 + Math.sqrt(discriminant))/aDif;

            int alphaResult = funcValueCases(alpha);
            int bettaResult = funcValueCases(betta);

            if (alphaResult == FIRST_CASE && bettaResult == FIRST_CASE) {
                System.out.println("The answer is: " + (alpha + betta)/2);
            }
            if (alphaResult == THIRD_CASE && bettaResult == THIRD_CASE) {
                System.out.println("The answer is: " + bisection(NEGATIVE_INFINITY, alpha));
            }
            if (alphaResult == SECOND_CASE && bettaResult == SECOND_CASE) {
                System.out.println("The answer is: " + bisection(betta, INFINITY));
            }
            if (alphaResult == THIRD_CASE && bettaResult == FIRST_CASE) {
                System.out.println("The answers are: " + betta + " and " + bisection(NEGATIVE_INFINITY, alpha));
            }
            if (alphaResult == FIRST_CASE && bettaResult == SECOND_CASE) {
                System.out.println("The answers are: " + alpha + " and " + bisection(betta, INFINITY));
            }
            if (alphaResult == THIRD_CASE && bettaResult == SECOND_CASE) {
                System.out.println("The answers are: " + bisection(NEGATIVE_INFINITY, alpha) +
                        " and " + bisection(alpha, betta) + " and " + bisection(betta, INFINITY));
            }
        }
    }

    private static int funcValueCases(double num) {
        double func = num * num * num + a * num * num + b * num + c;
        if (Math.abs(func) <= epsilon) {
            return FIRST_CASE;
        }
        else if (func >= epsilon) {
            return THIRD_CASE;
        }
        else if (func <= negativeEpsilon) {
            return SECOND_CASE;
        }
        return FOURTH_CASE;
    }

    private static double funcValue(double num) {
        double value = num * num * num + a * num * num + b * num + c;
        return value;
    }

    private static double bisection(double a, double b) {
        if (a == INFINITY && b == INFINITY) {
            return ERROR;
        }
        if (a == NEGATIVE_INFINITY) {
            double border = b - delta;
            int i = 1;

            double value = funcValue(b);
            if (value < 0) {
                while (funcValue(border) < 0) {
                    i++;
                    border = b - i*delta;
                }
                a = border;
                b = b - (i-1)*delta;
            }
            else {
                while (funcValue(border) > 0) {
                    i++;
                    border = b - i*delta;
                }
                a = border;
                b = b - (i-1)*delta;
            }
        }
        else if (b == INFINITY){
            double border = a + delta;
            int i = 1;

            double value = funcValue(a);
            if (value > 0) {
                while (funcValue(border) > 0) {
                    i++;
                    border = a + i*delta;
                }
                b = border;
                a = a + (i-1)*delta;
            }
            else {
                while (funcValue(border) < 0) {
                    i++;
                    border = a + i*delta;
                }
                b = border;
                a = a + (i-1)*delta;
            }
        }
        double c = (a+b)/2;
        int result = funcValueCases(c);
        while (result != FIRST_CASE) {
            if (funcValue(c) * funcValue(b) < 0) {
                a = c;
            }
            else {
                b = c;
            }
            c = (a+b)/2;
            result = funcValueCases(c);

        }
        return c;
    }
}

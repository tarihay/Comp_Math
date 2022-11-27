import java.util.Scanner;

public class SecondLab {
    public static void main(String[] args) {
        int n = 0;
        double epsilon = 0;
        double gamma = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("n,epsilon,gamma");
        n = scanner.nextInt();
        epsilon = scanner.nextDouble();
        gamma = scanner.nextDouble();
        System.out.println("Введенные значения: " + n + ", " + epsilon + ", " + gamma);

        double[][] A = new double[n][n];
        double[] X = new double[n];
//        System.out.println("------------First test---------------");
//        firstTest(A, X, n);
//        System.out.println("------------Second Test-------------------");
//        secondTest(A, n, X, epsilon);
        System.out.println("------------Third Test------------------");
        thirdTest(A, n, X, gamma);

    }


    public static double[] makeVectorF(int n, double value) {
        double[] f = new double[n];
        for (int i = 0; i < n; i++) {
            f[i] = value;
        }
        return f;
    }

    public static void printVector(double[] f) {
        for (double v : f) {
            System.out.println(v);
        }
    }

    public static void firstTest(double[][] A, double[] X, int n) {
        double[] a = new double[n + 1 - 1];
        double[] b = new double[n + 1 - 1];
        for (int i = 2 - 1; i < n + 1 - 1; i++) {
            a[i] = 1;
        }
        for (int i = 1 - 1; i < n - 1; i++) {
            b[i] = 1;
        }


        double[] c = new double[n + 1 - 1];
        for (int i = 1 - 1; i < n + 1 - 1; i++) {
            c[i] = 2;
        }

        System.out.println("A:");
        printVector(a);
        System.out.println("B:");
        printVector(a);
        System.out.println("C:");
        printVector(c);

        System.out.println("-----------------------------");
        double[] f = new double[n + 1 - 1];
        for (int i = 1 - 1; i < n + 1 - 1; i++) {
            f[i] = 2;
        }

        double alpha[] = new double[n + 1 - 1];
        double beta[] = new double[n + 1 - 1];

        alpha[0] = b[0] / c[0];
        beta[0] = f[0] / c[0];

        for (int i = 1; i < n; i++) {
            alpha[i] = b[i] / (c[i] - (a[i] * alpha[i - 1]));
            beta[i] = (f[i] + (a[i] * beta[i - 1])) / (c[i] - (a[i] * alpha[i - 1]));
        }

        System.out.println("Alpha");
        printVector(alpha);

        System.out.println("Beta");
        printVector(beta);
        System.out.println();

        X[n - 1] = beta[n - 1];

        for (int i = n - 2; i > -1; i--) {
            X[i] = (alpha[i] * X[i + 1]) + beta[i];
        }
        System.out.println("X");
        printVector(X);

    }

    public static void secondTest(double[][] A, int n, double[] X, double epsilon) {
        double[] a = new double[n];
        double[] b = new double[n];
        for (int i = 1; i < n; i++) {
            a[i] = 1;
        }
        for (int i = 0; i < n - 1; i++) {
            b[i] = 1;
        }


        double[] c = new double[n];
        for (int i = 0; i < n; i++) {
            c[i] = 2;
        }

        System.out.println("A:");
        printVector(a);
        System.out.println("B:");
        printVector(a);
        System.out.println("C:");
        printVector(c);

        System.out.println("-----------------------------");
        double[] f = new double[n];
        for (int i = 0; i < n; i++) {
            f[i] = 2 + epsilon;
        }

        double alpha[] = new double[n];
        double beta[] = new double[n];

        alpha[0] = b[0] / c[0];
        beta[0] = f[0] / c[0];

        for (int i = 1; i < n; i++) {
            alpha[i] = b[i] / (c[i] - (a[i] * alpha[i - 1]));
            beta[i] = (f[i] + (a[i] * beta[i - 1])) / (c[i] - (a[i] * alpha[i - 1]));
        }

        System.out.println("Alpha");
        printVector(alpha);

        System.out.println("Beta");
        printVector(beta);
        System.out.println();

        X[n - 1] = beta[n - 1];

        for (int i = n - 2; i > -1; i--) {
            X[i] = (alpha[i] * X[i + 1]) + beta[i];
        }
        System.out.println("X");
        printVector(X);

    }

    public static void thirdTest(double[][] A, int n, double[] X, double gamma) {
        double[] a = new double[n];
        double[] b = new double[n];
        for (int i = 1; i < n; i++) {
            a[i] = 1;
        }
        for (int i = 0; i < n - 1; i++) {
            b[i] = 1;
        }


        double[] c = new double[n];
        for (int i = 0; i < n; i++) {
            c[i] = (2 * i) + gamma;
        }

        System.out.println("A:");
        printVector(a);
        System.out.println("B:");
        printVector(a);
        System.out.println("C:");
        printVector(c);

        System.out.println("-----------------------------");
        double[] f = new double[n];
        for (int i = 0; i < n; i++) {
            f[i] = 2 * i + 2 + gamma;
        }

        double alpha[] = new double[n];
        double beta[] = new double[n];

        alpha[0] = b[0] / c[0];
        beta[0] = f[0] / c[0];

        for (int i = 2 - 1; i < n + 1 - 1; i++) {
            alpha[i] = b[i] / (c[i] - (a[i] * alpha[i - 1]));
            beta[i] = (f[i] + (a[i] * beta[i - 1])) / (c[i] - (a[i] * alpha[i - 1]));
        }

        System.out.println("Alpha");
        printVector(alpha);

        System.out.println("Beta");
        printVector(beta);
        System.out.println();

        X[n - 1] = beta[n - 1];

        for (int i = n-2; i > -1; i--) {
            X[i] = (alpha[i] * X[i + 1]) + beta[i];
        }
        System.out.println("X");
        printVector(X);

    }


    public static void matrixPrint(double[][] A, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] >= 0) {
                    System.out.printf(" %5f ", A[i][j]);
                } else {
                    System.out.printf("%5f ", A[i][j]);
                }

            }
            System.out.println();
        }
    }

}

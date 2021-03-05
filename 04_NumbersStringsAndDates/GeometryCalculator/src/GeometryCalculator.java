public class GeometryCalculator {

    private static final double V_SPH_ARG = 4.0d/3.0d;

    // метод должен использовать абсолютное значение radius
    public static double getCircleSquare(double radius) {
        return Math.PI*Math.pow(radius, 2.0);
    }

    // метод должен использовать абсолютное значение radius
    public static double getSphereVolume(double radius) {
        return V_SPH_ARG*Math.PI*Math.pow(radius, 3.0);
    }

    public static boolean isTriangleRightAngled(double a, double b, double c) {
        return ((a+b)>c && (b+c)>a && (c+a)>b);
    }

    // перед расчетом площади проверяем возможен ли такой треугольник
    // методом isTriangleRightAngled, если невозможен, возвращаем -1.0
    public static double getTriangleSquare(double a, double b, double c) {
        if (isTriangleRightAngled(a, b, c)) {
            double p = (a + b + c)/2.0; // полупериметр
            return Math.sqrt(p*(p-a)*(p-b)*(p-c));
        }
        else
            return -1.0;
    }
}

public class Vector {

    private double[] values;
    private int dimension;

    // Constructor
    public Vector(double[] values) throws VectorException {

        if (values.length != 2 && values.length != 3) {
            throw new VectorException("Invalid Vector: Only 2D or 3D vectors allowed.");
        }

        this.values = values;
        this.dimension = values.length;
    }

    // Method to check dimension
    private void checkDimension(Vector v) throws VectorException {

        if (this.dimension != v.dimension) {
            throw new VectorException("Vector dimensions must be the same.");
        }
    }

    // Addition
    public Vector add(Vector v) throws VectorException {

        checkDimension(v);

        double result[] = new double[dimension];

        for (int i = 0; i < dimension; i++) {
            result[i] = this.values[i] + v.values[i];
        }

        return new Vector(result);
    }

    // Subtraction
    public Vector subtract(Vector v) throws VectorException {

        checkDimension(v);

        double result[] = new double[dimension];

        for (int i = 0; i < dimension; i++) {
            result[i] = this.values[i] - v.values[i];
        }

        return new Vector(result);
    }

    // Dot Product
    public double dotProduct(Vector v) throws VectorException {

        checkDimension(v);

        double result = 0;

        for (int i = 0; i < dimension; i++) {
            result += this.values[i] * v.values[i];
        }

        return result;
    }

    // Display method
    public void display() {

        System.out.print("(");

        for (int i = 0; i < dimension; i++) {

            System.out.print(values[i]);

            if (i < dimension - 1) {
                System.out.print(", ");
            }
        }

        System.out.println(")");
    }
}
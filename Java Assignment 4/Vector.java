public class Vector {

    private double[] values;
    private int dimension;

    // Constructor
    public Vector(double[] values) throws VectorException {

        if(values.length != 2 && values.length != 3) {
            throw new VectorException("Only 2D or 3D vectors allowed");
        }

        this.values = values;
        this.dimension = values.length;
    }

    // Check dimension
    private void checkDimension(Vector v) throws VectorException {

        if(this.dimension != v.dimension) {
            throw new VectorException("Vector dimensions must match");
        }
    }

    // Addition
    public Vector add(Vector v) throws VectorException {

        checkDimension(v);

        double result[] = new double[dimension];

        for(int i=0;i<dimension;i++) {
            result[i] = this.values[i] + v.values[i];
        }

        return new Vector(result);
    }

    // Subtraction
    public Vector subtract(Vector v) throws VectorException {

        checkDimension(v);

        double result[] = new double[dimension];

        for(int i=0;i<dimension;i++) {
            result[i] = this.values[i] - v.values[i];
        }

        return new Vector(result);
    }

    // Dot product
    public double dotProduct(Vector v) throws VectorException {

        checkDimension(v);

        double result = 0;

        for(int i=0;i<dimension;i++) {
            result += this.values[i] * v.values[i];
        }

        return result;
    }

    // Display
    public void display() {

        System.out.print("(");

        for(int i=0;i<dimension;i++) {

            System.out.print(values[i]);

            if(i < dimension-1)
                System.out.print(", ");
        }

        System.out.println(")");
    }
}
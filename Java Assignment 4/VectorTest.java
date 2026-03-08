public class VectorTest {

    public static void main(String[] args) {

        // Valid vectors
        try {

            Vector v1 = new Vector(new double[]{1, 2});
            Vector v2 = new Vector(new double[]{3, 4});

            System.out.println("Vector 1:");
            v1.display();

            System.out.println("Vector 2:");
            v2.display();

            Vector sum = v1.add(v2);
            System.out.println("Addition Result:");
            sum.display();

            Vector diff = v1.subtract(v2);
            System.out.println("Subtraction Result:");
            diff.display();

            double dot = v1.dotProduct(v2);
            System.out.println("Dot Product: " + dot);

        }

        catch (VectorException e) {
            e.showError();
        }

        // Exception test: Invalid vector creation
        try {

            Vector invalid = new Vector(new double[]{1,2,3,4});

        }

        catch (VectorException e) {
            e.showError();
        }

        // Exception test: 2D + 3D addition
        try {

            Vector v3 = new Vector(new double[]{1,2});
            Vector v4 = new Vector(new double[]{3,4,5});

            Vector result = v3.add(v4);

        }

        catch (VectorException e) {
            e.showError();
        }
    }
}
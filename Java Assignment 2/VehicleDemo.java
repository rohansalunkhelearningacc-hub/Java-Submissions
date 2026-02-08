public class VehicleDemo {

    public static void main(String[] args) {

        // Creating Vehicle Objects 

        Vehicle v1 = new Vehicle("Toyota", "Camry", 3000000, 15);
        Vehicle v2 = new Vehicle("Honda", "City", 1500000, 18);
        Vehicle v3 = new Vehicle("Tesla", "Model 3", 4500000, 0);

        // Using copy constructor
        Vehicle v4 = new Vehicle(v2);

        // Array of Vehicles 

        Vehicle[] vehicles = { v1, v2, v3, v4 };

        // Tabular Output 

        System.out.println("---------------------------------------------------------------");
       // Printing table heading
        System.out.println("Brand Name\tModel Name\tPrice\t\tMileage");
        System.out.println("---------------------------------------------------------------");

        // Loop to print details of each vehicle
        for (int i = 0; i < vehicles.length; i++) {
            System.out.println(
                vehicles[i].brandName + "\t\t" +
                vehicles[i].modelName + "\t\t" +
                vehicles[i].getPrice() + "\t" +
                vehicles[i].getMileage()
            );
        }

        System.out.println("---------------------------------------------------------------");
    }
}
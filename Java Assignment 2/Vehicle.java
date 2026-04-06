public class Vehicle {

    // Public Data Members
    public String brandName;
    public String modelName;
    public String color;
    public int noOfSeats;
    public int noOfWheels;

    // Private Data Members 
    private double price;
    private double mileage;
    private int currSpeed;

    public char fuelType;
    public float fuelCapacity;
    public java.time.Year yearOfMfg;
    public double weight;

    // Constructors 

    // Default Constructor
    public Vehicle() {
        brandName = "NA";
        modelName = "NA";
        price = 0;
        mileage = 0;
        currSpeed = 0;
    }

    // Parameterized Constructor
    public Vehicle(String brandName, String modelName, double price, double mileage) {
        this.brandName = brandName;
        this.modelName = modelName;
        this.price = price;
        this.mileage = mileage;
        this.currSpeed = 0;
    }

    // Copy Constructor
    public Vehicle(Vehicle v) {
        this.brandName = v.brandName;
        this.modelName = v.modelName;
        this.price = v.price;
        this.mileage = v.mileage;
        this.currSpeed = v.currSpeed;
    }

    // Getter & Setter Methods

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public int getCurrSpeed() {
        return currSpeed;
    }

    public void setCurrSpeed(int currSpeed) {
        this.currSpeed = currSpeed;
    }

    // Methods

    public void start() {
        System.out.println(modelName + " is starting...");
    }

    public void stop() {
        currSpeed = 0;
        System.out.println(modelName + " has stopped.");
    }

    public void drive() {
        System.out.println(modelName + " is driving at " + currSpeed + " km/h");
    }

    public void changeSpeed(int speed) {
        currSpeed = speed;
        System.out.println("Speed changed to " + currSpeed + " km/h");
    }

    public double calcMileage(double distance, double fuelUsed) {
        if (fuelUsed != 0) {
            mileage = distance / fuelUsed;
        }
        return mileage;
    }
}
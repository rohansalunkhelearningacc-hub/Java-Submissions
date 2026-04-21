public class MenuItem {
    private int id;
    private String name;
    private double price;
    private int resId;
    
    public MenuItem() {
    }
    
    public MenuItem(String name, double price, int resId) {
        this.name = name;
        this.price = price;
        this.resId = resId;
    }
    
    public MenuItem(int id, String name, double price, int resId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.resId = resId;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getResId() {
        return resId;
    }
    
    public void setResId(int resId) {
        this.resId = resId;
    }
    
    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", resId=" + resId +
                '}';
    }
}

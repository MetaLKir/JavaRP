package product.model;

public class Product {

    private long barcode;
    private String name;
    private double price;

    // CONSTRUCTORS===============================
    public Product(long barcode, String name, double price) {
        this.barcode = barcode;
        this.name = name;
        this.price = price;
    }

    // GETTERS====================================
    public long getBarcode() { return barcode; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    // SETTERS====================================
    public void setBarcode(long barcode) { this.barcode = barcode; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }

    // UTILITY====================================
    @Override
    public String toString() {
        return "barcode = " + barcode +
                "; name = " + name  +
                "; price = " + price +
                "; ";
    }
}

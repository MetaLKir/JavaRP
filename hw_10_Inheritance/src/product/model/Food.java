package product.model;

public class Food extends Product {

    private boolean isKosher;
    private String expirationDate;

    // CONSTRUCTORS===============================
    public Food(long barcode, String name, double price, boolean isKosher, String expirationDate) {
        super(barcode, name, price);
        this.isKosher = isKosher;
        this.expirationDate = expirationDate;
    }

    // GETTERS====================================
    public boolean isKosher() { return isKosher; }
    public String getExpirationDate() { return expirationDate; }

    // SETTERS====================================
    public void setIsKosher(boolean kosher) { isKosher = kosher; }
    public void setExpirationDate(String expirationDate) { this.expirationDate = expirationDate; }

    // UTILITY====================================
    @Override
    public String toString() {
        return super.toString() +
                "; is kosher = " + isKosher +
                "; exp. date = " + expirationDate;
    }
}

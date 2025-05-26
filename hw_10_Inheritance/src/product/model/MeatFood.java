package product.model;

public class MeatFood extends Food {

    private String meatType;

    // CONSTRUCTORS===============================
    public MeatFood(long barcode, String name, double price, boolean isKosher, String expirationDate, String meatType) {
        super(barcode, name, price, isKosher, expirationDate);
        this.meatType = meatType;
    }

    // GETTERS====================================
    public String getMeatType() { return meatType; }

    // SETTERS====================================
    public void setMeatType(String meatType) { this.meatType = meatType; }

    // UTILITY====================================
    @Override
    public String toString() {
        return super.toString() + "; meat type = " + meatType;
    }
}

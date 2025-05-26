package product.model;

public class MilkFood extends Food {

    private String milkType;
    private int fatPercent;

    // CONSTRUCTORS===============================
    public MilkFood(long barcode, String name, double price, boolean isKosher, String expirationDate, String milkType, int fatPercent) {
        super(barcode, name, price, isKosher, expirationDate);
        this.milkType = milkType;
        this.fatPercent = fatPercent;
    }

    // GETTERS====================================
    public String getMilkType() { return milkType; }
    public int getFatPercent() { return fatPercent; }

    // SETTERS====================================
    public void setMilkType(String milkType) { this.milkType = milkType; }
    public void setFatPercent(int fatPercent) { this.fatPercent = fatPercent; }

    // UTILITY====================================
    @Override
    public String toString() {
        return super.toString() +
                "; milk type = " + milkType +
                "; fat % = " + fatPercent;
    }
}

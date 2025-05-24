package telran.book.model;

public class Comics extends Book {
    private String illustrator;
    private String universe = "Standalone";

    // CONSTRUCTORS=========================
    public Comics(String title, String author, String illustrator, int year, long isbn, String universe) {
        super(title, author, year, isbn);
        this.illustrator = illustrator;
        this.universe = universe;
    }

    public Comics(String title, String author, String illustrator, int year, long isbn) {
        super(title, author, year, isbn);
        this.illustrator = illustrator;
    }

    // GETTERS==============================
    public String getIllustrator() {
        return illustrator;
    }
    public String getUniverse() {
        return universe;
    }
    // SETTERS===============================
    public void setIllustrator(String illustrator) {
        this.illustrator = illustrator;
    }
    public void setUniverse(String universe) {
        this.universe = universe;
    }

    // UTILITY===============================
    @Override
    public String toString() {
        return super.toString() + " | illustrator = " + illustrator + " | universe = " + universe;
    }
}

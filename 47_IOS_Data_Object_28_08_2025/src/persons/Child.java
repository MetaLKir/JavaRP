package persons;

import java.time.LocalDate;

public class Child extends Person {
    private String garten;

    public Child(int id, String name, Address address, LocalDate birthDate, String garten) {
        super(id, name, address, birthDate);
        this.garten = garten;
    }

    public String getGarten() {
        return garten;
    }

    public void setGarten(String garten) {
        this.garten = garten;
    }

    @Override
    public String toString() {
        return "Child :: " +
                "garten='" + garten + '\'' +
                "; " + super.toString();
    }
}

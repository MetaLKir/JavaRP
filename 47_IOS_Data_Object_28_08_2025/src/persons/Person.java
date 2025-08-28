package persons;

import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {
    private int id;
    private String name;
    private Address address;
    private LocalDate birthDate;

    public Person(int id, String name, Address address, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "id=" + id +
                "; name='" + name + '\'' +
                "; address=" + address +
                "; birthDate=" + birthDate +
                ';';
    }
}

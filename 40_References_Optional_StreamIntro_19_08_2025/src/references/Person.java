package references;

public class Person {
    private String name;

    public Person() {
        this.name = "unnamed";
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }

    public void greet() {
        System.out.println("Hello, my name is " + name);
    }
}

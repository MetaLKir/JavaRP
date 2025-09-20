package reflection.pac4;

public class Cat {
    public String name;
    private int age;
    protected long id;

    public Cat() {
    }

    public Cat(String name, int age, long id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}

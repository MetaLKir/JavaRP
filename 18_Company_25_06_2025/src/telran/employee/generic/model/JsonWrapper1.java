package telran.employee.generic.model;

public class JsonWrapper1 {
    private Object value;

    public JsonWrapper1(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "{\"Key\": " + value + '}';
    }
}

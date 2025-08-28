package persons;

import java.io.*;
import java.util.List;

public class RestorePersonAppl {
    public static void main(String[] args) {
        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream("persons.data"))){
            List<Person> persons = (List<Person>) input.readObject();
            persons.forEach(System.out::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

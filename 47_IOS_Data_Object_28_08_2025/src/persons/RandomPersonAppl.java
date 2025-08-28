package persons;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class RandomPersonAppl {
    private static final long Q_PERSONS = 100;
    private static final int PROBABILITY = 30;
    private static final int MIN_CHILD_YEAR = 2018;
    private static final int MAX_CHILD_YEAR = 2022;
    private static final int N_GARTEN = 5;
    private static final int MIN_EMPL_YEAR = 1958;
    private static final int MAX_EMPL_YEAR = 2007;
    private static final int N_COMPANY = 7;
    private static final int MIN_SALARY = 6200;
    private static final int MAX_SALARY = 50_000;
    private static final int N_CITIES = 3;
    private static Random r = new Random();

    public static void main(String[] args) {
        List<Person> persons = getRandomPersons();

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("persons.data"))) {
            out.writeObject(persons);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static List<Person> getRandomPersons() {
        return Stream.generate(RandomPersonAppl::getRandomPerson).limit(Q_PERSONS).toList();
    }

    private static Person getRandomPerson() {
        Person person = getRandomCommonPerson();
        return r.nextInt(1, 100) <= PROBABILITY ? getRandomEmployee(person) : getRandomChild(person);
    }

    private static Person getRandomChild(Person person) {
        LocalDate bd = getRandomDate(MIN_CHILD_YEAR, MAX_CHILD_YEAR);
        String garten = "garten" + r.nextInt(1, N_GARTEN);
        return new Child(person.getId(), person.getName(), person.getAddress(), bd, garten);

    }

    private static Person getRandomEmployee(Person person) {
        String[] jobs = {"QA", "Dev", "Sales Manager", "Manager", "Cleaner", "Wage employee"};
        LocalDate bd = getRandomDate(MIN_EMPL_YEAR, MAX_EMPL_YEAR);
        String company = "company" + r.nextInt(1, N_COMPANY);
        int salary = r.nextInt(MIN_SALARY, MAX_SALARY);
        String title = jobs[r.nextInt(0, jobs.length)];
        return new Employee(person.getId(), person.getName(), person.getAddress(), bd, title, salary, company);
    }

    private static LocalDate getRandomDate(int minYear, int maxYear) {
        int year = r.nextInt(minYear, maxYear);
        int month = r.nextInt(1, 13);
        int day = r.nextInt(1, 29);
        return LocalDate.of(year, month, day);
    }

    private static Person getRandomCommonPerson() {
        int id = r.nextInt(10_000, 100_000);
        String name = "name" + r.nextInt(1, 50);
        Address ad = getRandomAddress();
        return new Person(id, name, ad, null);
    }

    private static Address getRandomAddress() {
        String city = "city" + r.nextInt(1, N_CITIES);
        String street = "street" + r.nextInt(1, 100);
        int building = r.nextInt(1, 60);
        int aprt = r.nextInt(1, 50);
        return new Address(city, street, building, aprt);
    }
}

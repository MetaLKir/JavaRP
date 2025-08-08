package telran.citizens.dao;

import telran.citizens.entity.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CitizensImpl implements Citizens {
    private static Comparator<Person> lastNameComparator;
    private static Comparator<Person> ageComparator;

    static {
        lastNameComparator = (p1, p2) -> {
            int res = p1.getLastName().compareTo(p2.getLastName());
            return res != 0 ? res : Integer.compare(p1.getId(), p2.getId());
        };
        ageComparator = (p1, p2) -> {
            int res = Integer.compare(p1.getAge(), p2.getAge());
            return res != 0 ? res : Integer.compare(p1.getId(), p2.getId());
        };
    }

    private List<Person> idList;
    private List<Person> lastNameList;
    private List<Person> ageList;


    public CitizensImpl() {
        idList = new ArrayList<>();
        lastNameList = new ArrayList<>();
        ageList = new ArrayList<>();
    }

    public CitizensImpl(List<Person> citizens) {
        this();
        citizens.forEach(p -> add(p));
    }

    @Override
    public boolean add(Person person) {
        if (person == null) return false;
        int index = Collections.binarySearch(idList, person);
        if (index >= 0) return false;
        index = -index -1;
        idList.add(index, person);

        //TODO

        return true;
    }

    @Override
    public boolean remove(int id) {

        //TODO
        return false;
    }

    @Override
    public Person find(int id) {
        return null;
    }

    @Override
    public Iterable<Person> find(int minAge, int maxAge) {
        return null;
    }

    @Override
    public Iterable<Person> find(String lastName) {
        return null;
    }

    @Override
    public Iterable<Person> getAllPersonsSortedById() {
        return null;
    }

    @Override
    public Iterable<Person> getAllPersonsSortedByAge() {
        return null;
    }

    @Override
    public Iterable<Person> getAllPersonsSortedByLastName() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}

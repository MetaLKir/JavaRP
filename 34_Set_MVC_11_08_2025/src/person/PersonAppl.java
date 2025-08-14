package person;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import set.MyHashSet;

public class PersonAppl {
	public static void main(String[] args) {
		Person p1 = new Person(1, "name1", "surname1");
		Person p2 = new Person(2, "name1", "surname2");
		Person p3 = new Person(3, "name3", "surname1");

		MyHashSet<Person> persons = new MyHashSet<>();
		persons.add(p1);
		persons.add(p2);
		persons.add(p3);
		display(persons);
//		System.out.println(persons.add(new Person(1, "name1", "surname1")));
//		display(persons);

		Person p4 = new Person(1, "name1", "surename1");
		Person p5 = new Person(1, "name1", "surename1");
//		System.out.println(persons.contains(p4));
//		System.out.println(persons.contains(p4));

//		p1.setLastName("surname5");
//		display(persons);

		Set<Person> personsSet = new HashSet<>();
		personsSet.add(p1);
        System.out.println(personsSet.add(new Person(21, "name1", "surename1")));
		personsSet.add(p2);
        System.out.println(personsSet.add(new Person(5, "name1", "surename1")));
		personsSet.add(p3);
		System.out.println(personsSet);
		System.out.println(personsSet.add(new Person(1, "name1", "surename1")));
		System.out.println(personsSet);
		persons.forEach(p -> System.out.println(p));
        //========================
        System.out.println("====== SORTING ======");
        System.out.println(personsSet);
        Set<Person> set1 = new TreeSet<>(personsSet);
        System.out.println(set1);
	}

	public static void display(MyHashSet<Person> per) {
		for (Person p : per) {
			System.out.print(p + ", ");
		}
		System.out.println();
	}

}

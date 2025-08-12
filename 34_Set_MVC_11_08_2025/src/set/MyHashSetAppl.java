package set;

public class MyHashSetAppl {
	public static void main(String[] args) {
		MyHashSet<String> mySet = new MyHashSet<String>();
		System.out.println(mySet.size());
		mySet.add("sss");
		mySet.add("aaa");
		mySet.add("bbb");
		mySet.add("ccc");
		mySet.add("AAA");
		display(mySet);
		System.out.println(mySet.size());

		System.out.println(mySet.add("ccc"));
		System.out.println(mySet.size());

		System.out.println(mySet.contains("aaa"));
		System.out.println(mySet.contains("Aaa"));
		
		System.out.println(mySet.remove("aaa"));
		System.out.println(mySet.size());
		System.out.println(mySet.contains("aaa"));
		display(mySet);
		System.out.println(mySet.add("aaa"));
		display(mySet);

	}

	public static void display(MyHashSet<String> str) {
		for (String s : str) {
			System.out.print(s + " ");
		}
		System.out.println();
	}
}

package set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SetAppl {
	public static void main(String[] args) {
		Set<Integer> set1 = new HashSet<>();
		set1.add(5);
		set1.add(6);
		set1.add(1);
		set1.add(9);
		set1.add(10);
		set1.add(3);
		System.out.println(set1);

		Set<Integer> set2 = new LinkedHashSet<Integer>();
		set2.add(5);
		set2.add(6);
		set2.add(1);
		set2.add(9);
		set2.add(10);
		set2.add(3);
		System.out.println(set2);

		Integer[] numbers = { 100, 400, 100, 5, 4, 100, 9, 9, 400, 4, 5, 120 };
		// 100, 400, 5, 4, 9, 120
		System.out.println(removeDuplicates(List.of(numbers)));
		System.out.println(removeDuplicatesLinked(List.of(numbers)));
		System.out.println(removeDuplicates2(List.of(numbers)));
	}
	
	// higher complexity, two loops
	private static List<Integer> removeDuplicates(List<Integer> list) {
		List<Integer> res = new ArrayList<Integer>();

		for (Integer num : list)
			if (!res.contains(num))
				res.add(num);

		return res;
	}
	// lowest complexity
	private static List<Integer> removeDuplicatesLinked(List<Integer> list) {
		return new ArrayList<>(new LinkedHashSet<>(list));
	}
	private static List<Integer> removeDuplicates2(List<Integer> list) {
		List<Integer> res = new ArrayList<>(list);
		
		for (int i = 0; i < res.size(); i++) {
			
			for (int j = i + 1; j < res.size(); j++) {
				if(res.get(i).equals(res.get(j))) {
					res.remove(j);
				}
			}
		}
		return res;
	}
}

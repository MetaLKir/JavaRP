package telran.employee.tests;
import java.util.*;
import java.util.function.Predicate;

public class MonthsDemo {

	public static void main(String[] args) {
		// Map<String, Integer> months = new HashMap<>();
//		Map<String, Integer> months = new LinkedHashMap<>();
		Map<String, Integer> months = new TreeMap<>();
		fillMap(months);
		System.out.println(months);
		System.out.println("put jan=100 old value=" + months.put("jan", 100));
		System.out.println(months);
		System.out.println("put may=5 old value=" + months.put("may", 5));
		// putAll
		// putIfAbsent
		System.out.println(months.putIfAbsent("jan", 1));
		System.out.println(months);
		System.out.println(months.putIfAbsent("jun", 6));
		System.out.println(months);
		Map<String, Integer> months1 = new HashMap<>();
		months1.put("jan", 1000);
		months1.put("feb", 20);
		months1.put("jul", 7);
		months1.put("aug", 8);
		System.out.println(months1);
//		months.putAll(months1);
		months1.putAll(months);
		System.out.println(months1);
		System.out.println(months.containsKey("jan"));
		System.out.println(months.containsKey("Jan"));
		//System.out.println(months.containsKey(months));
		Map<Map<String, Integer>, List<Integer>> months11 = new HashMap<>();
		System.out.println(months.containsValue(100));
		System.out.println(months.containsValue(1001));
		System.out.println(months.get("jan"));
		System.out.println(months.get("Jan"));
		System.out.println(months.getOrDefault("jan", 0));
		System.out.println(months.getOrDefault("Jan", 0));
		System.out.println(months.remove("jan"));
		System.out.println(months);
		System.out.println(months.remove("jan"));
		System.out.println(months);
		System.out.println(months.remove("jun", 6));
		System.out.println(months);
		System.out.println(months.remove("jun", 6));
		System.out.println(months);
		months1.clear();
		System.out.println(months1);
		months.put("jan", 1);
		// entrySet()
		// keySet()
		// values()
		iterateEntries(months);
		iterateMonthNames(months);
		iterateMonthNumbers(months);
		// 1-3 2-4 3-7 4-3 5-4 6-1
		months.values().removeIf(new Predicate<Integer>() 
		{

			@Override
			public boolean test(Integer t) {
				
				return t%2==0;
			}
		});
		System.out.println(months);
		
	}

	private static void iterateMonthNumbers(Map<String, Integer> months) {
		System.out.println("============Values Iterate================");
		for (Integer v : months.values()) {
			System.out.println(v);
		}

	}

	private static void iterateMonthNames(Map<String, Integer> months) {
		System.out.println("============Key Iterate================");
		for (String name : months.keySet()) {
			System.out.println(name);
		}

	}

	private static void iterateEntries(Map<String, Integer> months) {
		System.out.println("============Entry Iterate================");
		for (Map.Entry<String, Integer> entry : months.entrySet()) {
			String key = entry.getKey();
			Integer val = entry.getValue();
			System.out.println("Entry " + entry + " key = " + key + " value = " + val);
			System.out.println("Entry " + entry + " " + key + "->" + val);
		}

	}

	private static void fillMap(Map<String, Integer> months) {
		months.put("jan", 1);
		months.put("feb", 2);
		months.put("mar", 3);
		months.put("apr", 4);

	}

}

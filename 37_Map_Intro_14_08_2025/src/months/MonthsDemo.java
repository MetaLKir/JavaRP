package months;

import java.util.*;
import java.util.function.Predicate;

public class MonthsDemo {
    public static void main(String[] args) {
//        Map<String, Integer> months = new HashMap<>();
//        Map<String, Integer> months = new LinkedHashMap<>();
        Map<String, Integer> months = new TreeMap<>();

        fillMap(months);

        //=============== Put | Put if absent ===============
        System.out.println("=".repeat(20) + " " + "put | putIfAbsent" + " " + "=".repeat(20));

        System.out.println(months);
        System.out.println("put jan = 100, old value = " + months.put("jan", 100));
        System.out.println(months);
        System.out.println("put may = 5, old value = " + months.put("may", 5));
        // putAll
        // putIfAbsent
        System.out.println(months.putIfAbsent("jan", 10));
        System.out.println(months);
        System.out.println(months.putIfAbsent("jun", 6));
        System.out.println(months);

        //=============== Put All ===============
        System.out.println("=".repeat(20) + " " + "putAll" + " " + "=".repeat(20));

        Map<String, Integer> months1 = new HashMap<>();
        months1.put("jan", 1000);
        months1.put("feb", 22);
        months1.put("jul", 7);
        months1.put("aug", 8);
        System.out.println(months1);
//        months.putAll(months1);
//        System.out.println(months);
        months1.putAll(months);
        System.out.println(months1);

        //============== Contains Key ================
        System.out.println("=".repeat(20) + " " + "containsKey" + " " + "=".repeat(20));

        System.out.println(months.containsKey("jan"));
        System.out.println(months.containsKey("Jan"));

        //============== Map of key-map and list-value ================
        Map<Map<String, Integer>, List<Integer>> months11 = new HashMap<>();

        //============== Contains value ================
        System.out.println("=".repeat(20) + " " + "containsValue" + " " + "=".repeat(20));

        System.out.println(months.containsValue(100));
        System.out.println(months.containsValue(1001));

        //============== Get value ================
        System.out.println("=".repeat(20) + " " + "getValue" + " " + "=".repeat(20));
        System.out.println(months.get("jan"));
        System.out.println(months.get("Jan"));

        //============== getOrDefault ================
        System.out.println("=".repeat(20) + " " + "getOfDefault" + " " + "=".repeat(20));
        // if no such element, return defaultValue which passed to method
        System.out.println(months.getOrDefault("jan", -666));
        System.out.println(months.getOrDefault("Jan", -666));

        //============== remove ================
        System.out.println("=".repeat(20) + " " + "remove" + " " + "=".repeat(20));
        System.out.println(months);
        System.out.println(months.remove("jan"));
        System.out.println(months);
        System.out.println(months.remove("may", 666)); // removes only if passed value matches
        System.out.println(months);

        //============== clear ================
        System.out.println("=".repeat(20) + " " + "clear" + " " + "=".repeat(20));

        System.out.println(months1);
        months1.clear();
        System.out.println(months1);

        // entrySet
        // keySet
        // values

        iterateEntries(months);
        iterateMonthNames(months);
        iterateMonthValues(months);

        //============== Predicate with Map ================
        System.out.println("=".repeat(20) + " " + "Predicate with Map" + " " + "=".repeat(20));
        System.out.println(months);
        months.values().removeIf(new Predicate<Integer>() { // remove pairs which values are evens
            @Override
            public boolean test(Integer t) {
                return t % 2 == 0;
            }
        });
        System.out.println(months);
    }

    private static void iterateEntries(Map<String, Integer> months) {
        System.out.println("=".repeat(20) + " " + "Iterate over pairs (entries)" + " " + "=".repeat(20));
        for (Map.Entry<String, Integer> entry : months.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("Entry: " + entry + " | key: " + key + " | value: " + value);
        }
    }

    private static void iterateMonthNames(Map<String, Integer> months) {
        System.out.println("=".repeat(20) + " " + "Iterate over names" + " " + "=".repeat(20));
        for(String name : months.keySet()){
            System.out.println("Name: " + name);
        }
    }

    private static void iterateMonthValues(Map<String, Integer> months) {
        System.out.println("=".repeat(20) + " " + "Iterate over values" + " " + "=".repeat(20));
        for(Integer value : months.values()){
            System.out.println("Values: " + value);
        }
    }

    private static void fillMap(Map<String, Integer> months) {
        months.put("jan", 1);
        months.put("feb", 2);
        months.put("mar", 3);
        months.put("apr", 4);
    }
}

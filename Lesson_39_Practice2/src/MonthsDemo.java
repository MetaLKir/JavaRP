
import java.util.*;
import java.util.function.BiFunction;

public class MonthsDemo {

    public static void main(String[] args) {
        Map<String, Integer> months = new HashMap<>();
        fillMap(months);
        System.out.println(months);

        System.out.println("===== compute =====");
        months.compute("jan", new BiFunction<String, Integer, Integer>() {
            @Override
            public Integer apply(String string, Integer integer) {
                return integer * 10;
            }
        });
        System.out.println(months);

        months.compute("jan", (k, v) -> v == null ? 1 : v * 10);
        System.out.println(months);
        months.put("feb", months.getOrDefault("feb", 0) * 10);
        System.out.println(months);

        System.out.println("===== computeIfPresent =====");
        months.computeIfPresent("jan", (k, v) -> v == null ? 10 : v * 10);
        System.out.println(months);
        months.computeIfPresent("Jul", (k, v) -> v == null ? 10 : v * 10);
        System.out.println(months);
        System.out.println("===== computeIfAbsent =====");
        months.computeIfAbsent("jan", (k) -> 10);
        System.out.println(months);
        months.computeIfAbsent("oct", (k) -> 10);
        System.out.println(months);

        System.out.println("===== TASK: monthsReversed =====");
        Map<Integer, List<String>> mapRev = myMonthsReverse(months);
        System.out.println(mapRev);
        Map<Integer, List<String>> mapRev1 = monthsReverse1(months);
        System.out.println(mapRev1);
        Map<Integer, List<String>> mapRev2 = monthsReverse2(months);
        System.out.println(mapRev2);
        Map<Integer, List<String>> mapRev3 = monthsReverse2(months);
        System.out.println(mapRev3);

        System.out.println("===== merge ======");
        System.out.println(months);
        months.merge("dec", 100, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer a, Integer b) {
                return a + b;
            }
        });
        System.out.println(months);
        // if key and value exist, then do BiFunction (in this case a + b) and set as new value
        // if no value, then put value to the key
        // if no key, add new pair
        // if key exists and BiFunction return null, then remove passed key-value
        months.merge("dec", 100, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer a, Integer b) {
                return a + b;
            }
        });
        System.out.println(months);
        months.merge("dec", 100, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer a, Integer b) {
                return null;
            }
        });
        System.out.println(months);
    }

    //=============================================================

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
        months.put("jun", 3);
        months.put("apr", 4);
        months.put("may", 4);
        months.put("sep", 3);

    }

    public static Map<Integer, List<String>> myMonthsReverse(Map<String, Integer> map) {
        Map<Integer, List<String>> res = new HashMap<>();
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            String key = e.getKey();
            Integer value = e.getValue();
            res.compute(value, (k, v) -> {
                if (v == null)
                    v = new ArrayList<>();
                v.add(key);
                return v;
            });
        }
        return res;
    }

    public static Map<Integer, List<String>> monthsReverse1(Map<String, Integer> map) {
        Map<Integer, List<String>> res = new HashMap<>();
        Set<String> keys = map.keySet();
        for (String k : keys) {
            Integer value = map.get(k);
            res.compute(value, new BiFunction<Integer, List<String>, List<String>>() {
                @Override
                public List<String> apply(Integer t, List<String> u) {
                    if (u == null)
                        u = new ArrayList<>();
                    u.add(k);
                    return u;
                }
            });
        }
        return res;
    }

    public static Map<Integer, List<String>> monthsReverse2(Map<String, Integer> map) {
        Map<Integer, List<String>> res = new HashMap<>();
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            String key = e.getKey();
            Integer value = e.getValue();
            res.compute(value, (k,v ) -> {
                if(v == null)
                    v = new ArrayList<>();
                v.add(key);
                return v;
            });
        }
        return res;
    }

    public static Map<Integer, List<String>> monthsReverse3(Map<String, Integer> map) {
        Map<Integer, List<String>> res = new HashMap<>();
        map.forEach((month, number ) -> res.computeIfAbsent(number, k -> new ArrayList<>()).add(month));
        return res;
    }
}

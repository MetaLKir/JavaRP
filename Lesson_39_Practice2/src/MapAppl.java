import java.util.*;

public class MapAppl {

    public static void main(String[] args) {
        Map<Integer, Integer> res = new HashMap<>();
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            int x = r.nextInt(0, 11);
//            res.put(x, res.getOrDefault(x, 0) + 1);
            res.merge(x, 1, (oldValue, newValue) -> oldValue + newValue);
        }
        System.out.println(res);
    }

}

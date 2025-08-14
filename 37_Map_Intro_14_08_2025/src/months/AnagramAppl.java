package months;

import java.util.LinkedHashMap;
import java.util.Map;

public class AnagramAppl {
    public static void main(String[] args) {
        String word = "electricity";

        // true:
        isAnagram(word, "electic");
        isAnagram(word, "city");
        isAnagram(word, "tric");
        isAnagram(word, "tet");
        isAnagram(word, "let");
        isAnagram(word, "tree");
        // false
        isAnagram(word, "ellect");
        isAnagram(word, "tot");
        isAnagram(word, "select");
        isAnagram(word, "tet e");
        isAnagram(word, "teeet");
        isAnagram(word, " tet");
        isAnagram(word, "1tet");
    }

    private static boolean isAnagram(String mainWord, String subWord) {
        Map<Character, Integer> mainWordMap = stringToMap(mainWord);
        Map<Character, Integer> subWordMap = stringToMap(subWord);

        System.out.println("Main word: " + mainWord + " | Sub word: " + subWord);
        System.out.println(mainWordMap);
        System.out.println(subWordMap);


        boolean res = true;
        for (Map.Entry<Character, Integer> entry : subWordMap.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();

            if (!mainWordMap.containsKey(key) || value > mainWordMap.get(key)){
                res = false;
                break;
            }
        }
        System.out.println("Main word contain subword: " + res);
        System.out.println("=".repeat(50));
        return res;
    }

    private static Map<Character, Integer> stringToMap(String word){
        Map<Character, Integer> res = new LinkedHashMap<>();
        for (char c : word.toCharArray()) {
            res.put(c, res.getOrDefault(c, 0) + 1);
        }
        return res;
    }

}

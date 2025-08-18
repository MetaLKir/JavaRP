package hw37;

import java.util.HashMap;
import java.util.Map;

public class Anagramm {
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
        isAnagram(word, "electricityy");
    }

    private static boolean isAnagram(String word, String anagram) {
        if (word == null || anagram == null) return false;
        if (word.isBlank() || anagram.isBlank()) return false;
        if (anagram.length() > word.length()) return false;

        Map<Character, Integer> count = new HashMap<>();
        for (char c : word.toCharArray())
            count.put(c, count.getOrDefault(c, 0) + 1);

        for (char c : anagram.toCharArray()) {
            int amount = count.getOrDefault(c, 0);
            if (c == 0) return false;
            count.put(c, amount - 1);
        }
        return true;
    }
}

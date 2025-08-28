package bulls_and_cows;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 1234 | 1st != 0 (but others can) | no repeat numbers
// 2354 -> 2(2) 2(1) -> cow | 3-> cow | 4-> bull
public class BullsAndCowsGame {
    private String secretNum;

    public BullsAndCowsGame() {
        this.secretNum = generateSecretNum();
        System.out.println(secretNum);
    }

    public String getSecretNum() {
        return secretNum;
    }

    public int[] check(String guess) {
        if (guess == null
                || guess.length() != 4
                || !guess.chars().allMatch(Character::isDigit))
            throw new IllegalArgumentException("Need 4 digits!");
        if (hasRepeat(guess))
            throw new IllegalArgumentException("Digits must be unique");
        if (guess.charAt(0) == '0')
            throw new IllegalArgumentException("First digit shouldn't be zero");

        int bulls = 0, cows = 0;
        for (int i = 0; i < 4; i++) {
            char temp = guess.charAt(i);
            if (temp == secretNum.charAt(i)) bulls++;
            else if (secretNum.indexOf(temp) >= 0) cows++;
        }
        return new int[]{bulls, cows};
    }

    private boolean hasRepeat(String guess) {
        return guess.chars().distinct().count() != guess.length();
    }

    private String generateSecretNum() {
        Random r = new Random();

        int first = r.nextInt(9) + 1;
        IntStream rest = IntStream.generate(() -> r.nextInt(10)).
                filter(d -> d != first).
                distinct().
                limit(3);

        return IntStream.
                concat(IntStream.of(first), rest).
                mapToObj(Integer::toString).
                collect((Collectors.joining()));
    }
}

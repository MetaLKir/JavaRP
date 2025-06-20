package telran.pattern.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternAppl {
    public static void main(String[] args) {
        String str = "Mama, mula ramy! Mama, mula ramy?";
        String regex = "[Mm]ama";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        System.out.println("method .matches: " + matcher.matches());
        System.out.println("method .find: " + matcher.find());
        System.out.println("method .start: " + matcher.start());
        System.out.println("method .group: " + matcher.group());
        System.out.println("method .end: " + matcher.end());
        System.out.println("==================== #2 ======================");
        System.out.println("method .find: " + matcher.find());
        System.out.println("method .start: " + matcher.start());
        System.out.println("method .group: " + matcher.group());
        System.out.println("method .end: " + matcher.end());
        System.out.println("==================== reset ====================");
        matcher.reset("Mama mama mula ramu, mama Mama");
        while(matcher.find()){
            System.out.println("method .start: " + matcher.start());
            System.out.println("method .group: " + matcher.group());
            System.out.println("method .end: " + matcher.end());
        }
    }
}

package telran.regex.controller;

public class RegExAppl {
    public static void main(String[] args) {
        String str = "    karmiel 2024";
        // from 1990 to 2025 -> 2000-2009 -> 2010-2019 -> 2020 -> 2025
        String pattern = " *[Kk]armiel ?(199[0-9]|20[0-1][0-9]|202[0-5])A*";
        boolean check = str.matches(pattern);
        System.out.println("karmiel = " + check);

        String domain = "peter.il";
        pattern = "\\w+\\.(ua|com|il)";
        check = domain.matches(pattern);
        System.out.println("domain = " + check);

        String email = "qwerty@gmail.com";
        pattern = "\\w(\\w|\\.|-)*@\\w+\\.[a-z]{2,6}";
        check = email.matches(pattern);
        System.out.println("email = " + check);
    }
}

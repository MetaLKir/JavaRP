package telran.user.model;

public class User {
    private String email;
    private String password;

    public User(String email, String password) {
        setEmail(email);
        setPassword(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (validateEmail(email)) {
            this.email = email;
        } else {
            System.out.println(email + " is not valid");
        }

    }

    private boolean validateEmail(String email) {
        int indexAt = email.indexOf('@');
        int indexLastDot = email.lastIndexOf('.');
        int minSymbolsAfterDot = 2;
        // is @ exists
        if (indexAt == -1)
            return false;
        // is @ only one
        if(indexAt != email.lastIndexOf('@'))
            return false;
        // dot after @
        if (indexLastDot < indexAt)
            return false;
        // after last dot minimum 2 symbols
        int symbolsAfterDot = email.length() - 1 - indexLastDot;
        if (symbolsAfterDot < minSymbolsAfterDot)
            return false;
        // allowed symbols: alphabetic, digits, underscore, -, dot, comma, @
        if(!email.matches("[a-zA-Z0-9@,._-]{1,}"))
            return false;

        return true;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(validatePassword(password))
            this.password = password;
        else
            System.out.println("Password not valid");
    }

    /*
        1. minimum 8 symbols
        2. min one digit
        3. min one special symbol(!%@*&)
        4. min one symbol uppercase
        5. min one symbol lowercase
     */
    private boolean validatePassword(String password) {
        boolean[] res = new boolean[5];
        int len = password.length();
        if(len >= 8) {
            res[0] = true;
        }
        for (int i = 0; i < len; i++) {
            char c = password.charAt(i);
            if(Character.isDigit(c)) {
                res[1] = true;
            }
            if(isSpecSymbols(c)) {
                res[2] = true;
            }
            if(Character.isUpperCase(c)) {
                res[3] = true;
            }
            if(Character.isLowerCase(c)) {
                res[4] = true;
            }
            if(res[0] && res[1] && res[2] && res[3] && res[4]){
                return true;
            }
        }

        return false;
    }

    private boolean isSpecSymbols(char c) {
        return "!%@*&".indexOf(c) >= 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

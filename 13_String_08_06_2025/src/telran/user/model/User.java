package telran.user.model;

public class User {
    private String email;
    private String password;

    //private final static char[] restrictedSymbols = {' ', '+', '-', '=', '/', '|'}

    public User(String email, String password) {
        setEmail(email);
        this.password = password;
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
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

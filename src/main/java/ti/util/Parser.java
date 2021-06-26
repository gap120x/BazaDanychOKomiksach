package ti.util;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ti.model.User;

public class Parser {

    public static String parse(String input, String pattern)
    {

        String output = "main";
        String[] strony = pattern.split(";");
        if (input==null) input="main";


        for (String matching: strony)
        {
            if (input.equals(matching)) output = input;
        }
        return output;
    }

    // metoda sprawdzająca poprawność emaila
    public static boolean isValidEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }


    // metoda sprawdzajaca poprawnosc wpisanego hasla
    public static boolean isCorrectPasswd(User user, String passwd ){
        return bCryptPasswordEncoder().matches(passwd, user.getPassword());
    }

    // metoda szyfrujaca hasla
    public static BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

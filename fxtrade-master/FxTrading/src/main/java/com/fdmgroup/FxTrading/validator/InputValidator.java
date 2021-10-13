package com.fdmgroup.FxTrading.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {

    public boolean validateEmail(String email) {

        String regex = "^[A-Za-z0-9._%!?+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]+$";
        Pattern p = Pattern.compile(regex);

        if (email == null) {
            return false;
        }

        Matcher m = p.matcher(email);

        return m.matches();
    }

    public boolean validateName(String name) {

        //Name must begin with a capital letter and not include any numbers or special characters except ' and -
        //Maximum 34 characters
        //Ends with a letter or period (in the case of Jr. or Sr.)
        String regex = "^[A-Z][A-Za-z,',\\p{Pd}, ]{1,35}[A-Za-z,.]";
        Pattern p = Pattern.compile(regex);

        if (name == null || name.contains("  ")) {
            return false;
        }

        Matcher m = p.matcher(name);

        return m.matches();
    }
}
package com.user.redex.util;

import java.util.regex.Pattern;
/**
 * @author Nabeel Ahmed
 */
public class ReduxUtil {

    public final static String ERROR = "ERROR";
    public final static String SUCCESS = "SUCCESS";

    /**
     * Method use to verify the email valid or not
     * @param email
     * @return boolean
     * */
    public static boolean isValidEmail(String email) {
        return Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE).matcher(email).find();
    }

    public static boolean isNull(Object payload) {
        return payload == null || payload == "" ? true : false;
    }

    public static boolean isNotBlank(String s) {
        return !isBlank(s);
    }

    public static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static boolean isNull(Long log) {
        if (log == null) {
            return true;
        }
        return false;
    }

    public static boolean isNull(String str) {
        if (str == null || str.trim().isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isNull(Boolean bool) {
        if (bool == null) {
            return true;
        }
        return false;
    }

    public static boolean isNull(Double dou) {
        if (dou == null) {
            return true;
        }
        return false;
    }

}

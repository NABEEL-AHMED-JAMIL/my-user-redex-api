package com.user.redex.util;

/**
 * @author Nabeel Ahmed
 */
public class ReduxUtil {

    public final static String BUCKET =  "ttender-bucket";
    public final static String ERROR = "ERROR";
    public final static String SUCCESS = "SUCCESS";

    public static boolean isNull(Object payload) {
        return payload == null || payload == "" ? true : false;
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

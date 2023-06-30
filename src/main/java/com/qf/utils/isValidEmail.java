package com.qf.utils;

import java.util.regex.Pattern;

/**
 * @auther: sin
 * @Date: 2023/6/13 - 06 - 13 - 22:15
 * @Description: com.qf.utils
 * @version: 1.0
 */
public class isValidEmail {
    public static boolean isValidEmail(String email) {
        if ((email != null) && (!email.isEmpty())) {
            return Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", email);
        }
        return false;
    }
}

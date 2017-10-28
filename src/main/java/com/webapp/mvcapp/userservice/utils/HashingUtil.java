package com.webapp.mvcapp.userservice.utils;

import org.jasypt.util.password.BasicPasswordEncryptor;

public class HashingUtil {
    private HashingUtil() {}

    private static final BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

    public static String encrypt(String password) {
        return passwordEncryptor.encryptPassword(password);
    }

    public static boolean isPasswordCorrect(String encryptedPassword, String inputPassword) {
        return (passwordEncryptor.checkPassword(inputPassword, encryptedPassword));
    }

}

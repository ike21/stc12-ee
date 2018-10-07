package ru.innopolis.stc12.servlets.service.utils;

import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
    static final Logger LOGGER = Logger.getLogger(HashUtil.class);

    private HashUtil() {
    }

    public static String stringToMD5(String password) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(password.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e);
        }
        BigInteger bigInteger = new BigInteger(1, digest);
        StringBuilder res = new StringBuilder();
        res.append(bigInteger.toString(16));
        while (res.length() < 32) {
            res.append("0" + res);
        }
        return res.toString();
    }
}

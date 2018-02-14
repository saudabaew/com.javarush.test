package com.javarush.test.level33.lesson15.big01;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by 1 on 22.09.2017.
 */
public class Helper
{
    public static String generateRandomString()
    {
        SecureRandom secureRandom = new SecureRandom();
        BigInteger bigInteger = new BigInteger(130, secureRandom);
        return bigInteger.toString(32);
    }

    public static void printMessage(String message)
    {
        System.out.println(message);
    }
}

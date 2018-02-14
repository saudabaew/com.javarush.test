package com.javarush.test.level26.lesson15.big01;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 1 on 22.08.2017.
 */
public class CurrencyManipulatorFactory
{
    private CurrencyManipulatorFactory()
    {
    }

    static HashMap<String, CurrencyManipulator> manipulatorHashMap = new HashMap<>();

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode)
    {
        if (manipulatorHashMap.get(currencyCode) == null)
        {
            manipulatorHashMap.put(currencyCode, new CurrencyManipulator(currencyCode));
            return manipulatorHashMap.get(currencyCode);
        }
        else return manipulatorHashMap.get(currencyCode);

    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators()
    {
        return manipulatorHashMap.values();
    }
}

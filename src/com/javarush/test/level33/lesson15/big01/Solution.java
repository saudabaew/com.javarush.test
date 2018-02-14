package com.javarush.test.level33.lesson15.big01;

import com.google.common.collect.HashBiMap;
import com.javarush.test.level33.lesson15.big01.strategies.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 1 on 22.09.2017.
 */
public class Solution
{

    public static void main(String[] args)
    {
        HashMapStorageStrategy hashMapStorageStrategy = new HashMapStorageStrategy();
        OurHashMapStorageStrategy ourHashMapStorageStrategy = new OurHashMapStorageStrategy();
        OurHashBiMapStorageStrategy ourHashBiMapStorageStrategy = new OurHashBiMapStorageStrategy();
        HashBiMapStorageStrategy hashBiMap = new HashBiMapStorageStrategy();
        DualHashBidiMapStorageStrategy dualHashBidiMapStorageStrategy = new DualHashBidiMapStorageStrategy();
        testStrategy(hashMapStorageStrategy, 10000);
        testStrategy(ourHashMapStorageStrategy, 10000);
        testStrategy(ourHashBiMapStorageStrategy, 10000);
        testStrategy(hashBiMap, 10000);
        testStrategy(dualHashBidiMapStorageStrategy, 10000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings)
    {
        Set<Long> set = new HashSet<>();
        for (String entry: strings
             )
        {
            set.add(shortener.getId(entry));
        }
        return set;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys)
    {
        Set<String> set = new HashSet<>();
        for (Long entry: keys
                )
        {
            set.add(shortener.getString(entry));
        }
        return set;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber)
    {
        Helper.printMessage(strategy.getClass().getSimpleName());

        Set<String> setString = new HashSet<>();
        for (long i = 0; i < elementsNumber; i++)
        {
            setString.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);

        Set<Long> setId = new HashSet<>();
        Date currentDate = new Date();
        for (String s: setString)
        {
            setId.add(shortener.getId(s));
        }
        long timeGetId = new Date().getTime() - currentDate.getTime();
        Helper.printMessage(String.valueOf(timeGetId));

        Set<String> setStringNow = new HashSet<>();
        Date currentId = new Date();
        for (Long l: setId)
        {
            setStringNow.add(shortener.getString(l));
        }
        long timeGetString = new Date().getTime() - currentId.getTime();
        Helper.printMessage(String.valueOf(timeGetString));

        if (setString.size() == setStringNow.size()) Helper.printMessage("Тест пройден.");
        else Helper.printMessage("Тест не пройден.");
    }
}

package com.javarush.test.level33.lesson15.big01.tests;

import com.javarush.test.level33.lesson15.big01.Helper;
import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.HashBiMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 1 on 25.09.2017.
 */
public class SpeedTest
{
    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids)
    {
        Date current = new Date();
        for (String s: strings
             )
        {
            ids.add(shortener.getId(s));
        }
        return new Date().getTime() - current.getTime();
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings)
    {
        Date current = new Date();
        for (Long id: ids
                )
        {
            strings.add(shortener.getString(id));
        }
        return new Date().getTime() - current.getTime();
    }

    @Test
    public void testHashMapStorage()
    {
        HashMapStorageStrategy hashMapStorageStrategy = new HashMapStorageStrategy();
        HashBiMapStorageStrategy hashBiMapStorageStrategy = new HashBiMapStorageStrategy();
        Shortener shortener1 = new Shortener(hashMapStorageStrategy);
        Shortener shortener2 = new Shortener(hashBiMapStorageStrategy);

        Set<String> origStrings = new HashSet<>();
        Set<Long> ids = new HashSet<>();
        for (int i = 0; i < 10000; i++)
        {
            origStrings.add(Helper.generateRandomString());
        }

        Long l1 = getTimeForGettingIds(shortener1, origStrings, ids);
        Long l2 = getTimeForGettingIds(shortener2, origStrings, ids);
        Assert.assertTrue(l1 > l2);

        Long l3 = getTimeForGettingStrings(shortener1, ids, origStrings);
        Long l4 = getTimeForGettingStrings(shortener2, ids, origStrings);

        Assert.assertEquals(l3, l4, 5);
    }
}

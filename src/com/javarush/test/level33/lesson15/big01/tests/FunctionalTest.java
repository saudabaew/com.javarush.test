package com.javarush.test.level33.lesson15.big01.tests;

import com.javarush.test.level33.lesson15.big01.Helper;
import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by 1 on 25.09.2017.
 */
public class FunctionalTest
{
    public void testStorage(Shortener shortener)
    {
        String s1 = Helper.generateRandomString();
        String s2 = Helper.generateRandomString();
        String s3 = s1;

        Long l1 = shortener.getId(s1);
        Long l2 = shortener.getId(s2);
        Long l3 = shortener.getId(s3);

        Assert.assertNotEquals(l2, l1);
        Assert.assertNotEquals(l2, l3);
        Assert.assertEquals(l1, l3);

        String s4 = shortener.getString(l1);
        String s5 = shortener.getString(l2);
        String s6 = shortener.getString(l3);

        Assert.assertEquals(s4, s1);
        Assert.assertEquals(s5, s2);
        Assert.assertEquals(s6, s3);
    }

    @Test
    public void testHashMapStorageStrategy()
    {
        HashMapStorageStrategy storageStrategy = new HashMapStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }
    @Test
    public void testOurHashMapStorageStrategy()
    {
        OurHashMapStorageStrategy storageStrategy = new OurHashMapStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }
    @Test
    public void testFileStorageStrategy()
    {
        FileStorageStrategy storageStrategy = new FileStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }
    @Test
    public void testHashBiMapStorageStrategy()
    {
        HashBiMapStorageStrategy storageStrategy = new HashBiMapStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }
    @Test
    public void testDualHashBidiMapStorageStrategy()
    {
        DualHashBidiMapStorageStrategy storageStrategy = new DualHashBidiMapStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }
    @Test
    public void testOurHashBiMapStorageStrategy()
    {
        OurHashBiMapStorageStrategy storageStrategy = new OurHashBiMapStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }
}

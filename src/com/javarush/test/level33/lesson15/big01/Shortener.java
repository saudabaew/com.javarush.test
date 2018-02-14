package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.StorageStrategy;

/**
 * Created by 1 on 22.09.2017.
 */
public class Shortener
{
    private Long lastId = new Long(0);
    private StorageStrategy storageStrategy;

    public Shortener(StorageStrategy storageStrategy)
    {
        this.storageStrategy = storageStrategy;
    }

    public synchronized Long getId(String string)
    {
        if (storageStrategy.containsValue(string)) return storageStrategy.getKey(string);
        else {
            lastId++;
            storageStrategy.put(lastId, string);
        }
        return lastId;
    }

    public synchronized String getString(Long id)
    {
        return storageStrategy.getValue(id);
    }
}

package com.javarush.test.level33.lesson15.big01.strategies;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 1 on 22.09.2017.
 */
public class HashMapStorageStrategy implements StorageStrategy
{
    private HashMap<Long, String> data = new HashMap<>();

    @Override
    public boolean containsKey(Long key)
    {
        for (Map.Entry entry: data.entrySet()
             )
        {
            if (key.equals(entry.getKey())) return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(String value)
    {
        for (Map.Entry entry: data.entrySet()
                )
        {
            if (value.equals(entry.getValue())) return true;
        }
        return false;
    }

    @Override
    public void put(Long key, String value)
    {
        data.put(key, value);
    }

    @Override
    public Long getKey(String value)
    {
        for (Map.Entry<Long, String> entry: data.entrySet()
                )
        {
            if (value.equals(entry.getValue())) return entry.getKey();
        }
        return null;
    }

    @Override
    public String getValue(Long key)
    {
        return data.get(key);
    }
}

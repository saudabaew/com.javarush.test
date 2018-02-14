package com.javarush.test.level33.lesson15.big01.strategies;

/**
 * Created by 1 on 22.09.2017.
 */
public interface StorageStrategy
{
    boolean containsKey(Long key);
    boolean containsValue(String value);
    void put(Long key, String value);
    Long getKey(String value);
    String getValue(Long key);
}

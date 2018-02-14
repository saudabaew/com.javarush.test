package com.javarush.test.level33.lesson15.big01.strategies;

import java.io.Serializable;

/**
 * Created by 1 on 22.09.2017.
 */
public class Entry implements Serializable
{
    Long key;
    String value;
    Entry next;
    int hash;

    public Entry(int hash, Long key, String value, Entry next)
    {
        this.key = key;
        this.value = value;
        this.next = next;
        this.hash = hash;
    }

    @Override
    public int hashCode()
    {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (next != null ? next.hashCode() : 0);
        result = 31 * result + hash;
        return result;
    }

    @Override
    public String toString()
    {
        return "Entry{" +
                "key=" + key +
                ", value='" + value + '\'' +
                ", next=" + next +
                ", hash=" + hash +
                '}';
    }

    public Long getKey()
    {
        return key;
    }

    public String getValue()
    {
        return value;
    }
}

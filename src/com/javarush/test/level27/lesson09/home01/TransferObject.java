package com.javarush.test.level27.lesson09.home01;

public class TransferObject
{
    private int value;
    protected volatile boolean isValuePresent = false; //use this variable

    public synchronized int get()
    {
        System.out.println("Got: " + value);
        isValuePresent = true;
        try
        {
            if (isValuePresent)
            {
                notify();
                wait();
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return value;
    }

    public synchronized void put(int value)
    {
        this.value = value;
        System.out.println("Put: " + value);
        isValuePresent = false;
        try
        {
            if (!isValuePresent)
            {
                notify();
                wait();
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

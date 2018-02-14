package com.javarush.test.level26.lesson10.home02;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 1 on 18.08.2017.
 */
public class Producer implements Runnable
{
    protected ConcurrentHashMap<String, String> map;

    public Producer(ConcurrentHashMap<String, String> map)
    {
        this.map = map;
    }

    @Override
    public void run()
    {
        Thread currentThread = Thread.currentThread();
        int i = 1;
        try
        {
            while (true)
            {
                System.out.println(String.format("Some text for %d", i));
                i++;
                currentThread.sleep(500);
            }
        }
        catch (InterruptedException e)
        {
            System.out.println(String.format("%s thread was terminated", String.format(currentThread.getName())));
        }
    }
}

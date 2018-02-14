package com.javarush.test.level30.lesson06.home01;

import java.util.concurrent.RecursiveTask;

/**
 * Created by 1 on 06.09.2017.
 */
public class BinaryRepresentationTask extends RecursiveTask<String>
{
    private int x;
    public BinaryRepresentationTask(int i)
    {
        this.x = i;
    }

    @Override
    protected String compute()
    {
        int a = x % 2;
        int b = x / 2;
        String result = String.valueOf(a);

        if (b > 0)
        {
            BinaryRepresentationTask task = new BinaryRepresentationTask(b);
            task.fork();
            return task.join() + result;
        }
        return result;
    }
}

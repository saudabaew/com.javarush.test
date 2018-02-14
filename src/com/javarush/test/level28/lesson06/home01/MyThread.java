package com.javarush.test.level28.lesson06.home01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 1 on 29.08.2017.
 */
public class MyThread extends Thread
{
    static AtomicInteger atomicInteger = new AtomicInteger(1);
    public MyThread()
    {
        super();
        atomicInteger.compareAndSet(11,1);
        this.setPriority(atomicInteger.getAndIncrement());
    }

    public MyThread(Runnable target)
    {
        super(target);
        atomicInteger.compareAndSet(11,1);
        this.setPriority(atomicInteger.getAndIncrement());
    }

    public MyThread(ThreadGroup group, Runnable target)
    {
        super(group, target);
        atomicInteger.compareAndSet(11,1);
        this.setPriority(atomicInteger.getAndIncrement());
    }

    public MyThread(String name)
    {
        super(name);
        atomicInteger.compareAndSet(11,1);
        this.setPriority(atomicInteger.getAndIncrement());
    }

    public MyThread(ThreadGroup group, String name)
    {
        super(group, name);
        atomicInteger.compareAndSet(11, 1);
        if (atomicInteger.intValue() < group.getMaxPriority())
        {
            this.setPriority(atomicInteger.getAndIncrement());
        }
        else
        {
            this.setPriority(group.getMaxPriority());
            atomicInteger.getAndIncrement();
        }
    }

    public MyThread(Runnable target, String name)
    {
        super(target, name);
        atomicInteger.compareAndSet(11,1);
        this.setPriority(atomicInteger.getAndIncrement());
    }

    public MyThread(ThreadGroup group, Runnable target, String name)
    {
        super(group, target, name);
        atomicInteger.compareAndSet(11, 1);
        if (atomicInteger.intValue() < group.getMaxPriority())
        {
            this.setPriority(atomicInteger.getAndIncrement());
        }
        else
        {
            this.setPriority(group.getMaxPriority());
            atomicInteger.getAndIncrement();
        }
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize)
    {
        super(group, target, name, stackSize);
        atomicInteger.compareAndSet(11, 1);
        if (atomicInteger.intValue() < group.getMaxPriority())
        {
            this.setPriority(atomicInteger.getAndIncrement());
        }
        else
        {
            this.setPriority(group.getMaxPriority());
            atomicInteger.getAndIncrement();
        }
    }
}

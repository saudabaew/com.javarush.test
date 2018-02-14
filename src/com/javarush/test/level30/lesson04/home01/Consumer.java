package com.javarush.test.level30.lesson04.home01;

import java.util.concurrent.TransferQueue;

/**
 * Created by 1 on 06.09.2017.
 */
public class Consumer implements Runnable
{
    private TransferQueue<ShareItem> queue;

    public Consumer(TransferQueue<ShareItem> queue)
    {
        this.queue = queue;
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(500);
        }
        catch (InterruptedException e)
        {
            return;
        }
        while (true)
        {
            ShareItem item = null;
            try
            {
                item = queue.take();
            }
            catch (InterruptedException e)
            {
                break;
            }
            System.out.println("Processing " + item.toString());
        }
    }
}

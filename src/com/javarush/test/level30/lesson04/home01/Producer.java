package com.javarush.test.level30.lesson04.home01;

import java.util.concurrent.TransferQueue;

/**
 * Created by 1 on 06.09.2017.
 */
public class Producer implements Runnable
{
    private TransferQueue<ShareItem> queue;

    public Producer(TransferQueue<ShareItem> queue)
    {
        this.queue = queue;
    }

    @Override
    public void run()
    {
        for (int i = 1; i < 10; i++)
        {
            try
            {
                ShareItem item = new ShareItem(String.format("ShareItem-%d", i), i);
                System.out.printf("Элемент 'ShareItem-%d' добавлен\n", i);
                queue.offer(item);
                Thread.sleep(100);
                if (queue.hasWaitingConsumer()) System.out.println("Consumer в ожидании!");
            }
            catch (InterruptedException e)
            {
                break;
            }
        }
    }
}

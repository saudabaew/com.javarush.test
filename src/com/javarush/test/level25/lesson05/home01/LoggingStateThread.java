package com.javarush.test.level25.lesson05.home01;

/**
 * Created by 1 on 04.08.2017.
 */
public class LoggingStateThread extends Thread
{
    private Thread target;

    @Override
    public void run()
    {
        State state = target.getState();
        System.out.println(state);
        while (state != State.TERMINATED)
        {
            while (state != State.TERMINATED)
            {
                if (state != target.getState())
                {
                    state = target.getState();
                    System.out.println(state);
                }
            }
        }
    }

    public LoggingStateThread(Thread target)
    {
        super();
        this.target = target;
        setDaemon(true);
    }
}

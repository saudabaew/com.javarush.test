package com.javarush.test.level27.lesson15.big01.kitchen;


import com.javarush.test.level27.lesson15.big01.ad.AdvertisementManager;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by 1 on 25.08.2017.
 */
public class Cook extends Observable implements Observer
{
    private String name;

    public Cook(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }

    @Override
    public void update(Observable o, Object arg)
    {
        Order order = (Order) arg;
        System.out.println("Start cooking - " + arg.toString() + ", cooking time " + ((Order) arg).getTotalCookingTime() + "min");
        new AdvertisementManager(((Order) arg).getTotalCookingTime()*60).processVideos();
        setChanged();
        notifyObservers(arg);
    }
}

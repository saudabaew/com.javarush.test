package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.Tablet;

import java.io.IOException;
import java.util.List;

/**
 * Created by 1 on 25.08.2017.
 */
public class Order
{
    Tablet tablet;
    List<Dish> dishes;

    public Order(Tablet tablet) throws IOException
    {
        this.tablet = tablet;
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public int getTotalCookingTime()
    {
        int time = 0;
        for (int i = 0; i < dishes.size(); i++)
        {
            time += dishes.get(i).getDuration();
        }
        return time;
    }

    @Override
    public String toString()
    {
        if (dishes.isEmpty()) return "";
        else return String.format("Your order: %s of Tablet{number=%d}", dishes.toString(), tablet.getNumber());
    }

    public boolean isEmpty()
    {
        return dishes.isEmpty();
    }
}

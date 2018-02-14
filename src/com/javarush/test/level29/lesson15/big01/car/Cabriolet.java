package com.javarush.test.level29.lesson15.big01.car;

/**
 * Created by 1 on 04.09.2017.
 */
public class Cabriolet extends Car
{
    @Override
    public int getMaxSpeed()
    {
        final int MAX_CABRIOLET_SPEED = 90;
        return MAX_CABRIOLET_SPEED;
    }

    public Cabriolet(int numberOfPassengers)
    {
        super(Car.CABRIOLET, numberOfPassengers);
    }
}

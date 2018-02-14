package com.javarush.test.level21.lesson16.big01;

/**
 * Created by 1 on 14.07.2017.
 */
public class Horse
{
    private String name;
    private double speed;
    private double distance;

    public void move(){
        distance += speed * Math.random();
    }
    public void print(){
        for (int i = 0; i < distance; i++)
        {
            System.out.printf(".");
        }
        System.out.println(getName());
    }

    public Horse(String name, double speed, double distance)
    {
        this.name = name;
        this.speed = speed;
        this.distance = distance;
    }

    public String getName()
    {
        return name;
    }

    public double getSpeed()
    {
        return speed;
    }

    public double getDistance()
    {
        return distance;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    public void setDistance(double distance)
    {
        this.distance = distance;
    }
}

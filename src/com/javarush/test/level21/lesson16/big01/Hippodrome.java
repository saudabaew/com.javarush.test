package com.javarush.test.level21.lesson16.big01;

import java.util.ArrayList;

/**
 * Created by 1 on 14.07.2017.
 */
public class Hippodrome
{
    private ArrayList<Horse> horses = new ArrayList<>();
    public static Hippodrome game;

    public ArrayList<Horse> getHorses()
    {
        return horses;
    }

    public static void main(String[] args)
    {
        game = new Hippodrome();
        Horse h1 = new Horse("Horse_1", 3, 0);
        Horse h2 = new Horse("Horse_2", 3, 0);
        Horse h3 = new Horse("Horse_3", 3, 0);

        game.getHorses().add(h1);
        game.getHorses().add(h2);
        game.getHorses().add(h3);

        game.run();
        game.printWinner();
    }
    public void run(){
        for (int i = 0; i < 100; i++)
        {
            move();
            print();
            try
            {
                Thread.sleep(200);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
    public void move(){
        for (Horse horse: getHorses())
            horse.move();
    }
    public void print(){
        for (Horse horse: getHorses())
            horse.print();
        System.out.println();
        System.out.println();
    }
    public Horse getWinner()
    {
        Horse horse_win = new Horse("", 0, 0);
        double d_max = 0;
        for (Horse horse: getHorses())
        {
            if (horse.getDistance() > d_max) d_max = horse.getDistance();
        }
        for (int i = 0; i < getHorses().size(); i++)
        {
            if (getHorses().get(i).getDistance() == d_max)
            {
                horse_win = getHorses().get(i);
            }
        }
        return horse_win;
    }
    public void printWinner()
    {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
}

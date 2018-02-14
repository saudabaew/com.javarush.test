package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 25.08.2017.
 */
public class ConsoleHelper
{
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message)
    {
        System.out.println(message);
    }

    public static String readString() throws IOException
    {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException
    {
        List<Dish> list = new ArrayList<>();
        writeMessage("Select the dish: " + Dish.allDishesToString());
        String s = "";
        while (true)
        {
            try
            {
                s = readString();
                if (!s.equals("exit")) list.add(Dish.valueOf(s));
                else break;
            }
            catch (IllegalArgumentException ignored)
            {
                ConsoleHelper.writeMessage(s + " is not detected");
            }
        }
        return list;
    }
}

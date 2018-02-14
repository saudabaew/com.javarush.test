package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

import static com.javarush.test.level26.lesson15.big01.Operation.*;

/**
 * Created by 1 on 22.08.2017.
 */
public class ConsoleHelper
{
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static String askCurrencyCode() throws InterruptOperationException
    {
        String s;
        while (true)
        {
            writeMessage(res.getString("choose.currency.code"));
            s = readString();
            if (s.length() != 3)
            {
                writeMessage(res.getString("invalid.data"));
            }
            else
            {
                break;
            }
        }
        return s.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException
    {
        String[] calculation;
        while (true)
        {
            writeMessage(res.getString("choose.denomination.and.count.format"));
                calculation = readString().split(" ");
                if (calculation.length == 2)
                {
                    try
                    {
                        if (Integer.parseInt(calculation[0]) > 0)
                        {
                            if (Integer.parseInt(calculation[1]) > 0) break;
                        }
                    }
                    catch (NumberFormatException e)
                    {
                        writeMessage(res.getString("invalid.data"));
                        continue;
                    }
                }
                else writeMessage(res.getString("invalid.data"));
        }
        return calculation;
    }

    public static void writeMessage(String message)
    {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException
    {
        String s = "";
        try
        {
            s = reader.readLine();
        }
        catch (IOException e)
        {

        }
        if (s.equalsIgnoreCase("exit")) throw new InterruptOperationException();
        return s;
    }

    public static Operation askOperation() throws InterruptOperationException
    {
        writeMessage(res.getString("choose.operation"));
        writeMessage(String.format("%s - %d, %s - %d, %s - %d, %s - %d",
                res.getString("operation.INFO"), Operation.INFO.ordinal(),
                res.getString("operation.DEPOSIT"), Operation.DEPOSIT.ordinal(),
                res.getString("operation.WITHDRAW"), Operation.WITHDRAW.ordinal(),
                res.getString("operation.EXIT"), Operation.EXIT.ordinal()));
        int i;
        while (true)
        {
            System.out.println();
            try
            {
                i = Integer.parseInt(readString());
                if (i < 1 || i > 4) throw new IllegalArgumentException();
                return getAllowableOperationByOrdinal(i);
            }
            catch (IllegalArgumentException e)
            {
                continue;
            }
        }
    }

    public static void printExitMessage()
    {
        writeMessage(res.getString("the.end"));
    }
}

package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory.manipulatorHashMap;

/**
 * Created by 1 on 22.08.2017.
 */
public class CurrencyManipulator
{
    String currencyCode;
    Map<Integer, Integer> denominations;

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException
    {
        Map<Integer, Integer> treeMap = new TreeMap<>(Collections.<Integer>reverseOrder());
        treeMap.putAll(denominations);
        Map<Integer, Integer> map = new TreeMap<>(Collections.<Integer>reverseOrder());
        int sum = 0;
        while (sum != expectedAmount)
        {
            for (Map.Entry<Integer, Integer> entry: treeMap.entrySet()
                 )
            {
                int key = entry.getKey();
                for (int i = 1; i <= entry.getValue(); i++)
                {
                    sum += key;
                    if (sum > expectedAmount)
                    {
                        sum -= key;
                        if (i > 1) map.put(key, i-1);
                        break;
                    }
                    if (sum == expectedAmount)
                    {
                        map.put(key, i);
                    }
                    if (sum < expectedAmount && i <= entry.getValue())
                    {
                        map.put(key, i);
                    }
                }
            }
            if (sum != expectedAmount) throw new NotEnoughMoneyException();
        }

        for (Map.Entry<Integer, Integer> entry: denominations.entrySet()
             )
        {
            for (Map.Entry<Integer, Integer> entry2: map.entrySet()
                 )
            {
                int key = entry.getKey();
                if (key == entry2.getKey()) denominations.put(key, entry.getValue() - entry2.getValue());
            }
        }

        return map;
    }

    public boolean isAmountAvailable(int expectedAmount)
    {
        return expectedAmount <= getTotalAmount();
    }

    public CurrencyManipulator(String currencyCode)
    {
        this.currencyCode = currencyCode;
        this.denominations = new HashMap();
    }

    public String getCurrencyCode()
    {

        return currencyCode;
    }

    public void addAmount(int denomination, int count)
    {
        if (denominations.containsKey(denomination))
        {
            denominations.put(denomination, denominations.get(denomination) + count);
        }
        else denominations.put(denomination, count);
    }

    public int getTotalAmount()
    {
        int sum = 0;
        for (Map.Entry entry: denominations.entrySet()
             )
        {
            sum += Integer.parseInt(entry.getKey().toString()) * Integer.parseInt(entry.getValue().toString());
        }
        return sum;
    }

    public boolean hasMoney()
    {
        boolean result=true;
        if (denominations.isEmpty()) result = false;
        else {
            int zerosCount=0;
            for (Map.Entry<Integer,Integer> pair : denominations.entrySet()){
                if (pair.getValue()==0) zerosCount++;
            }
            if (zerosCount==denominations.size()) result=false;
        }
        return result;
    }
}

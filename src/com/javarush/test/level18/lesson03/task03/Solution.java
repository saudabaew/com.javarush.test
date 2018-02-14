package com.javarush.test.level18.lesson03.task03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* Самые частые байты
Ввести с консоли имя файла
Найти байт или байты с максимальным количеством повторов
Вывести их на экран через пробел
Закрыть поток ввода-вывода
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fileInputStream = new FileInputStream(reader.readLine());
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        while (fileInputStream.available() > 0)
        {
            int key = fileInputStream.read();
            if (!map.containsKey(key))
            {
                map.put(key, 1);
            } else map.put(key, map.get(key) + 1);
        }

        int max = 0;
        for (int value : map.values())
        {
            if (value > max) max = value;
        }

        for (Map.Entry<Integer, Integer> entry: map.entrySet())
        {
            if (entry.getValue().equals(max)) System.out.print(String.valueOf(entry.getKey()) + " ");
        }
        fileInputStream.close();
    }
}

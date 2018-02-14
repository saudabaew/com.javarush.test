package com.javarush.test.level18.lesson10.home05;

/* Округление чисел
Считать с консоли 2 имени файла
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415
Округлить числа до целых и записать через пробел во второй файл
Закрыть потоки. Не использовать try-with-resources
Принцип округления:
3.49 - 3
3.50 - 4
3.51 - 4
-3.49 - -3
-3.50 - -3
-3.51 - -4
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inputStream = new FileInputStream(reader.readLine());
        FileOutputStream outputStream = new FileOutputStream(reader.readLine());
        ArrayList<Float> list = new ArrayList<>();
        String s = "";
        while (inputStream.available() > 0)
        {
            int c = inputStream.read();
            if (c != 32)
            {
                s = s + (char)c;
            } else
            {
                list.add(Float.parseFloat(s));
                System.out.println(s);
                s = "";
            }
        } list.add(Float.parseFloat(s));
        for (int i = 0; i < list.size(); i++)
        {
            int k = Math.round(list.get(i));
            System.out.println(k);
            outputStream.write(Integer.toString(k).getBytes());
            outputStream.write(32);
        }

        inputStream.close();
        outputStream.close();
        reader.close();
    }
}

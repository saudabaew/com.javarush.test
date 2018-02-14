package com.javarush.test.level18.lesson10.home01;

/* Английские буквы
В метод main первым параметром приходит имя файла.
Посчитать количество букв английского алфавита, которое есть в этом файле.
Вывести на экран число (количество букв)
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        FileInputStream inputStream = new FileInputStream(args[0]);
        int count = 0;
        while (inputStream.available() > 0)
        {
            int c = inputStream.read();
            if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122)) count++;
        }
        System.out.println(count);
        inputStream.close();
    }
}

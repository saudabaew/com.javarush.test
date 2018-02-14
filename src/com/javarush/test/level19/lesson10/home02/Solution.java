package com.javarush.test.level19.lesson10.home02;

/* Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Вывести в консоль имена, у которых максимальная сумма
Имена разделять пробелом либо выводить с новой строки
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws Exception {
        Map<String, Double> map = new HashMap<>();
        FileReader reader = new FileReader(args[0]);
        BufferedReader bufferedInputStream = new BufferedReader(reader);
        while (bufferedInputStream.ready())
        {
            String[] s = bufferedInputStream.readLine().split(" ");
            if (s[0].codePointAt(0)==65279) s[0]=s[0].substring(1);
            if (!map.containsKey(s[0]))
            {
                map.put(s[0], Double.parseDouble(s[1]));
            }
            else
            {
                double d = map.get(s[0]);
                map.put(s[0], Double.parseDouble(s[1]) + d);
            }
        }
        double max = 0;
        for (Map.Entry entry : map.entrySet())
        {
            if ((double)entry.getValue() > max) max = (double)entry.getValue();
        }
        for (Map.Entry entry : map.entrySet())
        {
            if ((double)entry.getValue() == max) System.out.println(entry.getKey());
        }
        reader.close();
        bufferedInputStream.close();
    }
}

package com.javarush.test.level19.lesson10.home01;

/* Считаем зарплаты
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Все данные вывести в консоль, предварительно отсортировав в возрастающем порядке по имени
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 2
Сидоров 6
Иванов 1.35
Петров 3.1

Пример вывода:
Иванов 1.35
Петров 5.1
Сидоров 6.0
*/

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws Exception {
        Map<String, Double> map = new TreeMap<>();
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
        for (Map.Entry entry : map.entrySet())
        {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        reader.close();
        bufferedInputStream.close();
    }
}

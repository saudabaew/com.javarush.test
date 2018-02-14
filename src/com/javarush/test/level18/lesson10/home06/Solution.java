package com.javarush.test.level18.lesson10.home06;

/* Встречаемость символов
Программа запускается с одним параметром - именем файла, который содержит английский текст.
Посчитать частоту встречания каждого символа.
Отсортировать результат по возрастанию кода ASCII (почитать в инете). Пример: ','=44, 's'=115, 't'=116
Вывести на консоль отсортированный результат:
[символ1]  частота1
[символ2]  частота2
Закрыть потоки. Не использовать try-with-resources

Пример вывода:
, 19
- 7
f 361
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException {
        Map<Character, Integer> map = new TreeMap<>();
        FileInputStream inputStream = new FileInputStream(args[0]);
        while (inputStream.available() > 0)
        {
            char i = (char) inputStream.read();
            if (!map.containsKey(i)) map.put(i, 1);
            else map.put(i, map.get(i) + 1);
        }
        for (Map.Entry<Character, Integer> entry: map.entrySet())
        {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        inputStream.close();
    }
}

package com.javarush.test.level19.lesson10.bonus01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Отслеживаем изменения
Считать в консоли 2 имени файла - file1, file2.
Файлы содержат строки, file2 является обновленной версией file1, часть строк совпадают.
Нужно создать объединенную версию строк, записать их в список lines
Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME
Пример:
оригинальный   редактированный    общий
file1:         file2:             результат:(lines)

строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
строка4                           REMOVED строка4
строка5        строка5            SAME строка5
строка0                           ADDED строка0
строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
строка5                           ADDED строка5
строка4        строка4            SAME строка4
строка5                           REMOVED строка5
*/

public class Solution
{
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader bufferedReader1 = new BufferedReader(new FileReader(reader.readLine()));
        BufferedReader bufferedReader2 = new BufferedReader(new FileReader(reader.readLine()));

        List<String> list1 = new ArrayList<>();
        while (bufferedReader1.ready())
        {

            list1.add(bufferedReader1.readLine());
        }

        List<String> list2 = new ArrayList<>();
        while (bufferedReader2.ready())
        {
            list2.add(bufferedReader2.readLine());
        }

        for (int i = 0; i < list1.size(); i++)
        {
            //if (list1.get(i).equals(list2.get(i))) lines.add(new LineItem(Type.SAME, list1.get(i)));
            System.out.println(list1.get(i));
            if (list1.get(i).equals(list2.get(i))) System.out.println("!!!");
            if (list1.get(i).isEmpty() || list1.get(i).equals(null)) lines.add(new LineItem(Type.ADDED, list2.get(i)));
            if (list2.isEmpty() || list2.get(i).equals(null)) lines.add(new LineItem(Type.REMOVED, list1.get(i)));
        }

        reader.close();
        bufferedReader1.close();
        bufferedReader2.close();

        for (int i = 0; i < lines.size(); i++)
        {
            System.out.println(lines.get(i).toString());
        }
}


public static enum Type {
    ADDED,        //добавлена новая строка
    REMOVED,      //удалена строка
    SAME          //без изменений
}

public static class LineItem {
    public Type type;
    public String line;

    public LineItem(Type type, String line) {
        this.type = type;
        this.line = line;
    }
}
}

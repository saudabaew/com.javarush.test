package com.javarush.test.level19.lesson10.home03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;

/* Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами

Заполнить список PEOPLE импользуя данные из файла
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader(args[0]);
        BufferedReader bufferedReader = new BufferedReader(reader);
        while (bufferedReader.ready())
        {
            String[] s = bufferedReader.readLine().split(" ");
            String name = "";
            for (int i = 0; i < s.length - 4; i++)
            {
                name = name + s[i] + " ";
            }
            name = name + s[s.length - 4];
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
            Date date = dateFormat.parse(s[s.length-3]+ " " + s[s.length-2]+ " " + s[s.length-1]);
            PEOPLE.add(new Person(name, date));
        }
        reader.close();
        bufferedReader.close();

    }

}

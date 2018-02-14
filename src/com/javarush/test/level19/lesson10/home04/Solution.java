package com.javarush.test.level19.lesson10.home04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* Ищем нужные строки
Считать с консоли имя файла.
Вывести в консоль все строки из файла, которые содержат всего 2 слова из списка words
Закрыть потоки. Не использовать try-with-resources

Пример: words содержит слова А, Б, В
Строки:
В Б А Д  //3 слова из words, не подходит
Д А Д    //1 слово из words, не подходит
Д А Б Д  //2 слова - подходит, выводим
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader bufferedReader = new BufferedReader(new FileReader(reader.readLine()));
        while (bufferedReader.ready())
        {
            HashSet<String> set = new HashSet<>();
            String string = bufferedReader.readLine();
            if (string.isEmpty()) continue;
            if (string.codePointAt(0)==65279) string=string.substring(1);
            String[] w = string.split(" ");
            for (int i = 0; i < w.length; i++)
            {
                for (int j = 0; j < words.size(); j++)
                {
                    if (w[i].equals(words.get(j))) set.add(w[i]);
                }
            }
            if (set.size() == 2) System.out.println(string);
        }
        reader.close();
        bufferedReader.close();
    }
}

package com.javarush.test.level19.lesson10.bonus03;

/* Знакомство с тегами
Считайте с консоли имя файла, который имеет HTML-формат
Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela
</span></b></span><span>Super</span><span>girl</span>
Первым параметром в метод main приходит тег. Например, "span"
Вывести на консоль все теги, которые соответствуют заданному тегу
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле
Количество пробелов, \n, \r не влияют на результат
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нету
Тег может содержать вложенные теги
Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>
<span>Super</span>
<span>girl</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileReader fileReader = new FileReader(reader.readLine());
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        reader.close();
        String s = "", text = "";
        while ((text = bufferedReader.readLine()) != null)
        {
            s = s + text;
        }
        bufferedReader.close();
        char[] html = s.toCharArray();

        while (s.contains("<" + args[0]))
        {
            int open = s.indexOf("<" + args[0]);
            int close = s.indexOf("</" + args[0], open);
            int secondOpen = s.indexOf("<" + args[0], open + args[0].length());
            int flag = 0;
            if (secondOpen < close && secondOpen != -1)
            {
                secondOpen = s.indexOf("<" + args[0], secondOpen + args[0].length());
                flag++;
            }

            for (int i = 0; i < flag; i++)
            {
                close = s.indexOf("</" + args[0], close + args[0].length());
            }

            String d = "";
            for (int i = open; i < close + args[0].length() + 3; i++)
            {
                d = d + html[i];
            }
            System.out.println(d);
            s = s.substring(open + args[0].length());
            html = s.toCharArray();
        }
    }
}

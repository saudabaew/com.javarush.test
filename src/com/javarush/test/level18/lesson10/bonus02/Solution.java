package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
            String maxID = incrementID(fileName);

            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fileName, true), "cp1251");
            if (!maxID.equals("1"))
            {
                writer.write("\r\n");
            }

            writer.write(maxID);
            if (maxID.toCharArray().length < 8)
            {
                for (int i = 0; i < 8 - maxID.toCharArray().length; i++)
                {
                    writer.write(" ");
                }
            }

        if (args[1].length() > 30)
        {
            char[] c = args[1].toCharArray();
            for (int i = 0; i < 30; i++)
            {
                writer.write(c[i]);
            }
        }
        else
        {
            writer.write(args[1]);
            if (args[1].length() < 30)
            {
                for (int i = 0; i < 30 - args[1].toCharArray().length; i++)
                {
                    writer.write(" ");
                }
            }
        }

        if (args[2].length() > 8)
        {
            char[] c = args[2].toCharArray();
            for (int i = 0; i < 8; i++)
            {
                writer.write(c[i]);
            }
        }
        else
        {
            writer.write(args[2]);
            if (args[2].length() < 8)
            {
                for (int i = 0; i < 8 - args[2].toCharArray().length; i++)
                {
                    writer.write(" ");
                }
            }
        }

        if (args[3].length() > 4)
        {
            char[] c = args[3].toCharArray();
            for (int i = 0; i < 4; i++)
            {
                writer.write(c[i]);
            }
        }
        else
        {
            writer.write(args[3]);
            if (args[3].length() < 4)
            {
                for (int i = 0; i < 4 - args[3].toCharArray().length; i++)
                {
                    writer.write(" ");
                }
            }
        }

        writer.close();
    }

    public static String incrementID(String name) throws IOException
    {
        BufferedReader readerID = new BufferedReader(new FileReader(name));
        String s = readerID.readLine();

        if (s == null)
        {
            return "1";
        }
        else
        {
            int max = 1;
            while (s != null)
            {
                if (!s.isEmpty())
                {
                    char[] strings = s.toCharArray();
                    String Id = "";
                    for (int i = 0; i < s.length(); i++)
                    {
                        if (strings[i] == 32) break;
                        if (i == 8) break;
                        Id = Id + strings[i];
                    }
                    if (Integer.parseInt(Id) > max) max = Integer.parseInt(Id);
                }
                s = readerID.readLine();
            }
            readerID.close();
            return String.valueOf(max + 1);
        }
    }
}

package com.javarush.test.level18.lesson10.bonus03;

/* Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u  - обновляет данные товара с заданным id
-d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException{

        //считываем имя файла
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();

        //считываем строки из файла
        BufferedReader readerFile = new BufferedReader(new FileReader(fileName));
        ArrayList<String> arrayList = new ArrayList<>();
        String s = readerFile.readLine();
        if (s.codePointAt(0)==65279) s=s.substring(1);
        while (s != null)
        {
            arrayList.add(s);
            s = readerFile.readLine();
        }
        readerFile.close();

        //обновляем данные товара с заданным id
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8");
        String id = args[1];
        if (args[0].equals("-u"))
        {
            for (int i = 0; i < arrayList.size(); i++)
            {
                char[] chars = arrayList.get(i).toCharArray();
                String idOld = "";
                for (int j = 0; j < chars.length; j++)
                {
                    if (chars[j] == 32) break;
                    if (j == 8) break;
                    idOld = idOld + chars[j];
                }
                if (id.equals(idOld))
                {
                    String idDDD = "";
                    if (args[1].length() > 8) idDDD = args[1].substring(0, 8);
                    if (args[1].length() < 8)
                    {
                        while (args[1].length() < 8) args[1]+=" ";
                        idDDD = args[1];
                    }

                    String productName = "";
                    if (args[2].length() > 30) productName = args[2].substring(0, 30);
                    if (args[2].length() < 30)
                    {
                        while (args[2].length() < 30) args[2]+=" ";
                        productName = args[2];
                    }

                    String price = "";
                    if (args[3].length() > 8) price = args[3].substring(0, 8);
                    if (args[3].length() < 8)
                    {
                        while (args[3].length() < 8) args[3]+=" ";
                        price = args[3];
                    }

                    String quantity = "";
                    if (args[4].length() > 4) quantity = args[4].substring(0, 4);
                    if (args[4].length() < 4)
                    {
                        while (args[4].length() < 4) args[4]+=" ";
                        quantity = args[4];
                    }

                    writer.write(idDDD + productName + price + quantity);
                }
                else writer.write(arrayList.get(i));
                writer.write("\r\n");
            }
        }

        //удаляем заданный товар
        if (args[0].equals("-d"))
        {
            for (int i = 0; i < arrayList.size(); i++)
            {
                char[] chars = arrayList.get(i).toCharArray();
                String idOld = "";
                for (int j = 0; j < 8; j++)
                {
                    if (chars[j] == 32) break;
                    idOld = idOld + chars[j];
                }
                if (id.equals(idOld)) continue;
                else writer.write(arrayList.get(i));
                writer.write("\r\n");
            }
        }
        writer.close();
    }
}

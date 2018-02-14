package com.javarush.test.level18.lesson10.home10;

/* Собираем файл
Собираем файл из кусочков
Считывать с консоли имена файлов
Каждый файл имеет имя: [someName].partN. Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.
Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end"
В папке, где находятся все прочтенные файлы, создать файл без приставки [.partN]. Например, Lion.avi
В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = "";
        ArrayList<String> arrayList = new ArrayList<>();
        while (true)
        {
            fileName = reader.readLine();
            if (fileName.equals("end")) break;
            arrayList.add(fileName);
        }
        reader.close();

        String[] partName = arrayList.get(0).split("\\.");
        String file = "";
        for (int i = 0; i < partName.length - 2; i++)
        {
            file = file + partName[i] + ".";
        }
        for (int i = partName.length - 2; i < partName.length - 1; i++)
        {
            file = file + partName[i];
        }

        File file1 = new File(file);
        FileOutputStream fileOutputStream = new FileOutputStream(file1);

        for (int j = 0; j < arrayList.size(); j++)
        {
            for (int i = 0; i < arrayList.size(); i++)
            {
                String[] mass = arrayList.get(i).split("\\.");
                if (mass[mass.length - 1].contains(String.valueOf(j + 1)))
                {
                    FileInputStream fileInputStream = new FileInputStream(arrayList.get(i) + "." + partName[partName.length - 2]);
                    while (fileInputStream.available() > 0)
                    {
                        fileOutputStream.write(fileInputStream.read());
                    }
                    fileInputStream.close();
                }
                else continue;
            }
        }
        fileOutputStream.close();
    }
}

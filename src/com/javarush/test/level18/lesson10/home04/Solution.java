package com.javarush.test.level18.lesson10.home04;

/* Объединение файлов
Считать с консоли 2 имени файла
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        FileInputStream fis = new FileInputStream(fileName1);
        FileInputStream fis2 = new FileInputStream(fileName2);

        byte[] file1Content = new byte[fis.available()];
        byte[] file2Content = new byte[fis2.available()];

        fis.read(file1Content);
        fis2.read(file2Content);

        FileOutputStream fos = new FileOutputStream(fileName1, false);
        fos.write(file2Content);
        fos = new FileOutputStream(fileName1, true);
        fos.write(file1Content);

        reader.close();
        fis.close();
        fis2.close();
        fos.close();
    }
}

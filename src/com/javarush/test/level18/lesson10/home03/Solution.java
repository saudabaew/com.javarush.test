package com.javarush.test.level18.lesson10.home03;

/* Два в одном
Считать с консоли 3 имени файла
Записать в первый файл содержимого второго файла, а потом дописать в первый файл содержимое третьего файла
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileOutputStream outputStream1 = new FileOutputStream(reader.readLine());
        FileInputStream inputStream2 = new FileInputStream(reader.readLine());
        FileInputStream inputStream3 = new FileInputStream(reader.readLine());

        while (inputStream2.available() > 0)
        {
            outputStream1.write(inputStream2.read());
        }
        while (inputStream3.available() > 0)
        {
            outputStream1.write(inputStream3.read());
        }
        inputStream2.close();
        inputStream3.close();
        outputStream1.close();
        reader.close();
    }
}

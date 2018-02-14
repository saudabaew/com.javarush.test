package com.javarush.test.level18.lesson05.task03;

/* Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать бОльшую часть.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inputStream1 = new FileInputStream(reader.readLine());
        FileOutputStream outputStream2 = new FileOutputStream(reader.readLine());
        FileOutputStream outputStream3 = new FileOutputStream(reader.readLine());
        int x = 0;
        if ((inputStream1.available()%2) > 0) x = inputStream1.available()/2 + 1;
        else x = inputStream1.available()/2;
        byte[] buff = new byte[x];
        int count = inputStream1.read(buff);
        outputStream2.write(buff, 0, count);
        count = inputStream1.read(buff);
        outputStream3.write(buff, 0, count);
        reader.close();
        inputStream1.close();
        outputStream2.close();
        outputStream3.close();
    }
}

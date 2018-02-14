package com.javarush.test.level18.lesson10.bonus01;

/* Шифровка
Придумать механизм шифровки/дешифровки

Программа запускается с одним из следующих наборов параметров:
-e fileName fileOutputName
-d fileName fileOutputName
где
fileName - имя файла, который необходимо зашифровать/расшифровать
fileOutputName - имя файла, куда необходимо записать результат шифрования/дешифрования
-e - ключ указывает, что необходимо зашифровать данные
-d - ключ указывает, что необходимо расшифровать данные
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Solution {
    public static void main(String[] args) throws Exception{
        if (args[0].equals("-e"))
        {
            FileInputStream inputStream = new FileInputStream(args[1]);
            FileOutputStream outputStream = new FileOutputStream(args[2]);
            while (inputStream.available() > 0)
            {
                byte x = 33;
                outputStream.write(inputStream.read());
                outputStream.write(x);
            }
            inputStream.close();
            outputStream.close();
        }
        if (args[0].equals("-d"))
        {
            FileInputStream inputStream = new FileInputStream(args[1]);
            FileOutputStream outputStream = new FileOutputStream(args[2]);
            int i = 1;
            while (inputStream.available() > 0)
            {
                int x = inputStream.read();
                if (i%2 > 0) outputStream.write(x);
                i++;
            }
            inputStream.close();
            outputStream.close();
        }
    }

}

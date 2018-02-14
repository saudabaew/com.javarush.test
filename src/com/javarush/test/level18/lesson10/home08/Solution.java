package com.javarush.test.level18.lesson10.home08;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* Нити и байты
Читайте с консоли имена файлов, пока не будет введено слово "exit"
Передайте имя файла в нить ReadThread
Нить ReadThread должна найти байт, который встречается в файле максимальное число раз, и добавить его в словарь resultMap,
где параметр String - это имя файла, параметр Integer - это искомый байт.
Закрыть потоки. Не использовать try-with-resources
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName;
            while (!((fileName = reader.readLine()).equals("exit")))
            {
                new ReadThread(fileName).start();
            }
            reader.close();
        }
        catch (Exception e) {System.out.println("Ex");}
    }

    public static class ReadThread extends Thread {
        private String filename;
        public ReadThread(String fileName) {
            //implement constructor body
            this.filename = fileName;
        }
        // implement file reading here - реализуйте чтение из файла тут

        @Override
        public void run()
        {
            try
            {
                FileInputStream inputStream = new FileInputStream(filename);
                try
                {
                    if (inputStream.available() > 0)
                    {
                        byte[] buff = new byte[256];
                        while (inputStream.available() > 0)
                        {
                            buff[inputStream.read()]++;
                        }
                        int max = 0;
                        int key = 0;
                        for (int i = 0; i < buff.length; i++)
                        {
                            if (buff[i] > max)
                            {
                                max = buff[i];
                                key = i;
                            }
                        }
                        resultMap.put(filename, key);
                    }
                    else resultMap.put(filename, 0);
                    inputStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
            catch (FileNotFoundException e)
            {
            }
        }
    }
}

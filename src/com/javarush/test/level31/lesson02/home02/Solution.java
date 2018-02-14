package com.javarush.test.level31.lesson02.home02;

import java.io.File;
import java.io.IOException;
import java.util.*;

/* Находим все файлы
Реализовать логику метода getFileTree, который должен в директории root найти список всех файлов включая вложенные.
Используйте очередь, рекурсию не используйте.
Верните список всех путей к найденным файлам, путь к директориям возвращать не надо.
Путь должен быть абсолютный.
*/
public class Solution
{
    public static void main(String[] args) throws IOException
    {
        for (String s : getFileTree("C:\\Users\\1\\Downloads\\JavaRushHomeWork\\JavaRushHomeWork\\test\\")
                )
        {
            System.out.println(s);
        }
    }

    public static List<String> getFileTree(String root) throws IOException
    {
        Queue<File> queue = new LinkedList<>();
        List<String> files = new ArrayList<>();

        queue.add(new File(root));
        while (queue.peek() != null)
        {
            File directory = queue.remove();
            for (File file: directory.listFiles()
                 )
            {
                if (file.isDirectory()) queue.add(file);
                else files.add(file.getAbsolutePath());
            }
        }

        return files;
    }
}

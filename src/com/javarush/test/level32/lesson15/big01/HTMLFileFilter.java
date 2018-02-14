package com.javarush.test.level32.lesson15.big01;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by 1 on 19.09.2017.
 */
public class HTMLFileFilter extends FileFilter
{
    @Override
    public String getDescription()
    {
        return "HTML и HTM файлы";
    }

    @Override
    public boolean accept(File f)
    {
        if (f.isDirectory()) {
            return true;
        }
        else if (!f.isDirectory()) {
            String fileName = f.getName().toLowerCase();
            return fileName.endsWith(".html") || fileName.endsWith(".htm");
        }

        return false;
    }
}

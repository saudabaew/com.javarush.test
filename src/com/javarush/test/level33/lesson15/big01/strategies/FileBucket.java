package com.javarush.test.level33.lesson15.big01.strategies;

import com.javarush.test.level33.lesson15.big01.ExceptionHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by 1 on 22.09.2017.
 */
public class FileBucket
{
    private Path path;

    public FileBucket()
    {
        try
        {
            this.path = Files.createTempFile(null, null);
        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }
        File file = new File(path.toString());
        file.deleteOnExit();
    }

    public long getFileSize()
    {
        return path.toFile().length();
    }

    public void putEntry(Entry entry)
    {
        try
        {
            FileOutputStream outputStream = new FileOutputStream(path.toFile());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(entry);
            objectOutputStream.close();
            outputStream.close();
        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }
    }

    public Entry getEntry()
    {
        if (path.toFile().length() == 0) return null;
        Entry entry = null;
        try
        {
            FileInputStream inputStream = new FileInputStream(path.toFile());
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            entry = (Entry) objectInputStream.readObject();
            inputStream.close();
            objectInputStream.close();
        }
        catch (Exception e)
        {
            ExceptionHandler.log(e);
        }
        return entry;
    }

    public void remove()
    {
        path.toFile().delete();
    }
}

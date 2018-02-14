package com.javarush.test.level19.lesson10.bonus02;

/* Свой FileWriter
Реализовать логику FileConsoleWriter
Должен наследоваться от FileWriter
При записи данных в файл, должен дублировать эти данные на консоль
*/

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileConsoleWriter extends FileWriter {

    private FileWriter fileWriter;

    public static void main(String[] args) throws Exception
    {
        FileConsoleWriter fileConsoleWriter = new FileConsoleWriter("C:/1.txt");
        fileConsoleWriter.write("123456789".toCharArray(), 2, 5);
        fileConsoleWriter.write("123456789");
        fileConsoleWriter.write(9999);
        fileConsoleWriter.write("qwertyu", 2, 5);
        fileConsoleWriter.write("dfghj".toCharArray());
        fileConsoleWriter.close();
    }

    public FileConsoleWriter(String fileName) throws IOException
    {
        super(fileName);
        this.fileWriter = new FileWriter(fileName);
    }

    @Override
    public void write(int c) throws IOException
    {
        System.out.print((char) c);
        super.write(c);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException
    {
        for (int i = off; i < off+len; i++)
        {
            System.out.print(cbuf[i]);
        }
        super.write(cbuf, off, len);
    }

    @Override
    public void write(char[] cbuf) throws IOException
    {
        super.write(cbuf);
    }

    @Override
    public void write(String str) throws IOException
    {
        super.write(str);
    }

    @Override
    public void write(String str, int off, int len) throws IOException
    {
        char[] b = str.toCharArray();
        for (int i = off; i < off+len; i++)
        {
            System.out.print(b[i]);
        }
        super.write(str, off, len);
    }
}

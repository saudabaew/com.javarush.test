package com.javarush.test.level31.lesson06.home01;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* Добавление файла в архив
В метод main приходит список аргументов.
Первый аргумент - полный путь к файлу fileName.
Второй аргумент - путь к zip-архиву.
Добавить файл (fileName) внутрь архива в директорию 'new'.
Если в архиве есть файл с таким именем, то заменить его.

Пример входных данных:
C:/result.mp3
C:/pathToTest/test.zip

Файлы внутри test.zip:
a.txt
b.txt

После запуска Solution.main архив test.zip должен иметь такое содержимое:
new/result.mp3
a.txt
b.txt

Подсказка: нужно сначала куда-то сохранить содержимое всех энтри,
а потом записать в архив все энтри вместе с добавленным файлом.
Пользоваться файловой системой нельзя.
*/
public class Solution {
    public static void main(String[] args) throws IOException {

        Map<ZipEntry, byte[]> zipEntryMap = new HashMap<>();
        ZipInputStream inputStream = new ZipInputStream(new FileInputStream(args[1]));
        ZipEntry entry = inputStream.getNextEntry();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int count;

        while (entry != null){
            byte[] buffer = new byte[4096];
            while ((count = inputStream.read(buffer)) != -1){
                baos.write(buffer, 0, count);
            }
            zipEntryMap.put(entry, baos.toByteArray());
            inputStream.closeEntry();
            baos.reset();
            entry = inputStream.getNextEntry();
        }
        inputStream.close();

        FileOutputStream fos = new FileOutputStream(args[1]);
        ZipOutputStream zos = new ZipOutputStream(fos);

        for (Map.Entry<ZipEntry, byte[]> map: zipEntryMap.entrySet()
             )
        {

            ZipEntry zipEntry = new ZipEntry(map.getKey().getName());
            zos.putNextEntry(zipEntry);
            zos.write(map.getValue());
            zos.closeEntry();
        }
        zos.close();
    }
}

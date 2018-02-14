package com.javarush.test.level33.lesson05.home03;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

/* Десериализация JSON объекта
НЕОБХОДИМО: подключенные библиотеки Jackson Core, Bind и Annotation версии 2.6.1

В метод convertFromJsonToNormal первым параметром приходит имя файла, который содержит один ДЖЕЙСОН объект.
Вторым параметром приходит имя класса, объект которого находится в файле.
Метод convertFromJsonToNormal должен вычитать объект из файла, преобразовать его из JSON и вернуть его.
*/
public class Solution {

    public static void main(String[] args) throws IOException
    {
        System.out.println(convertFromJsonToNormal("C:\\Users\\1\\Downloads\\JavaRushHomeWork\\JavaRushHomeWork\\testFile.txt", Cat.class));
    }

    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(new File(fileName), clazz);
    }

    @JsonAutoDetect
    public static class Cat
    {
        public int age;
        public int weight;

        @Override
        public String toString()
        {
            return "Cat{" +
                    "age=" + age +
                    ", weight=" + weight +
                    '}';
        }
    }
}

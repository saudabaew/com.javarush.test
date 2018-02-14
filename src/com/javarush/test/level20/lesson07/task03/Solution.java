package com.javarush.test.level20.lesson07.task03;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* Externalizable Person
Класс Person должен сериализоваться с помощью интерфейса Externalizable.
Подумайте, какие поля не нужно сериализовать.
Исправьте ошибку сериализации.
Сигнатуры методов менять нельзя.
*/
public class Solution {
    public static void main(String[] args) throws Exception
    {
        Person person = new Person("Ivan", "Ivanov", 27);
        person.setMother(new Person("Mama", "MMM", 50));
        person.setFather(new Person("Papa", "PPP", 60));
        List<Person> c = new ArrayList<>();
        c.add(new Person("Syn", "SSS", 2));
        person.setChildren(c);

        FileOutputStream fileOutput = new FileOutputStream("1.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);
        person.writeExternal(outputStream);
        outputStream.close();
        fileOutput.close();

        FileInputStream fiStream = new FileInputStream("1.txt");
        ObjectInputStream objectStream = new ObjectInputStream(fiStream);
        Person person1 = new Person();
        person1.readExternal(objectStream);
        fiStream.close();
        objectStream.close();

        System.out.println(person1.firstName);
        System.out.println(person1.lastName);
        System.out.println(person1.mother);
        System.out.println(person1.father);
        for (int i = 0; i < person1.children.size(); i++)
        {
            System.out.println(person1.children.get(i));
        }
    }

    public static class Person implements Externalizable {
        private String firstName;
        private String lastName;
        private int age;
        private Person mother;
        private Person father;
        private List<Person> children;
        public Person(){}

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public void setMother(Person mother) {
            this.mother = mother;
        }

        public void setFather(Person father) {
            this.father = father;
        }

        public void setChildren(List<Person> children) {
            this.children = children;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(firstName);
            out.writeObject(lastName);
            out.writeObject(father);
            out.writeObject(mother);
            out.writeInt(age);
            out.writeObject(children);
            out.flush();
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            firstName = (String) in.readObject();
            lastName = (String) in.readObject();
            father = (Person)in.readObject();
            mother = (Person)in.readObject();
            age = in.readInt();
            children = (List<Person>) in.readObject();
        }
    }
}

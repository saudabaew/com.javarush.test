package com.javarush.test.level20.lesson02.task02;

import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* Читаем и пишем в файл: JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush
В файле your_file_name.tmp может быть несколько объектов JavaRush
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy", Locale.ENGLISH);
            javaRush.users.add(0, new User());
            javaRush.users.get(0).setFirstName("Ivanov");
            javaRush.users.get(0).setLastName("Ivan");
            javaRush.users.get(0).setBirthDate(sdf.parse("11 03 1998"));
            javaRush.users.get(0).setMale(true);
            javaRush.users.get(0).setCountry(User.Country.RUSSIA);

            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintWriter writer = new PrintWriter(outputStream);
            SimpleDateFormat f = new SimpleDateFormat("dd MM yyyy");
            for (int i = 0; i < users.size(); i++)
            {
                writer.println(users.get(i).getFirstName());
                writer.println(users.get(i).getLastName());
                writer.println(f.format(users.get(i).getBirthDate()));
                writer.println(users.get(i).isMale());
                writer.println(users.get(i).getCountry().getDisplayedName());
            }
            writer.close();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while (reader.ready())
            {
                User user = new User();
                user.setFirstName(reader.readLine());
                System.out.println(user.getFirstName());
                user.setLastName(reader.readLine());
                System.out.println(user.getLastName());

                SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy", Locale.ENGLISH);
                Date date = sdf.parse(reader.readLine());
                user.setBirthDate(date);
                System.out.println(user.getBirthDate().toString());

                user.setMale(Boolean.parseBoolean(reader.readLine()));
                System.out.println(user.isMale());
                String s = reader.readLine();
                if (s.equals("Ukraine")) user.setCountry(User.Country.UKRAINE);
                if (s.equals("Russia")) user.setCountry(User.Country.RUSSIA);
                if (s.equals("Other")) user.setCountry(User.Country.OTHER);
                System.out.println(user.getCountry().getDisplayedName());
                users.add(user);
            }
            reader.close();
        }
    }
}

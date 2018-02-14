package com.javarush.test.level32.lesson06.task01;

import java.io.ByteArrayOutputStream;

/* Генератор паролей
Реализуйте логику метода getPassword, который должен возвращать ByteArrayOutputStream, в котором будут байты пароля.
Требования к паролю:
1) 8 символов
2) только цифры и латинские буквы разного регистра
3) обязательно должны присутствовать цифры, и буквы разного регистра
Все сгенерированные пароли должны быть уникальные.
Пример правильного пароля:
wMh7SmNu
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        char[] password = new char[8];
        for (int i = 0; i < password.length - 5; i++)
        {
            int min = 48;
            int max = 57 - min;
            password[i] = (char) ((Math.random()*++max)+min);
        }

        for (int i = 3; i < password.length - 2; i++)
        {
            int min = 65;
            int max = 90 - min;
            password[i] = (char) ((Math.random()*++max)+min);
        }

        for (int i = 6; i < password.length; i++)
        {
            int min = 97;
            int max = 122 - min;
            password[i] = (char) ((Math.random()*++max)+min);
        }

        for (int i = 0; i < password.length; i++)
        {
            outputStream.write(password[i]);
        }
        return outputStream;
    }
}

package com.javarush.test.level19.lesson03.task04;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/* И еще один адаптер
Адаптировать Scanner к PersonScanner.
Классом-адаптером является PersonScannerAdapter.
Данные в файле хранятся в следующем виде:
Иванов Иван Иванович 31 12 1950

В файле хранится большое количество людей, данные одного человека находятся в одной строке. Метод read() должен читать данные одного человека.
*/

public class Solution {
    public static class PersonScannerAdapter implements PersonScanner{
        private Scanner scanner;

        public PersonScannerAdapter(Scanner scanner)
        {
            this.scanner = scanner;
        }

        @Override
        public void close() throws IOException
        {
            scanner.close();
        }

        @Override
        public Person read() throws IOException
        {
            String[] s = scanner.nextLine().split(" ");
            Date date = (new GregorianCalendar(Integer.parseInt(s[5]), Integer.parseInt(s[4]) - 1, Integer.parseInt(s[3]))).getTime();
            return new Person(s[1], s[2], s[0], date);
        }
    }
}

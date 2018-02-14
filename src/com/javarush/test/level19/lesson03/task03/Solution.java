package com.javarush.test.level19.lesson03.task03;

/* Адаптация нескольких интерфейсов
Адаптировать IncomeData к Customer и Contact.
Классом-адаптером является IncomeDataAdapter.
Инициализируйте countries перед началом выполнения программы. Соответствие кода страны и названия:
UA Ukraine
RU Russia
CA Canada
Дополнить телефонный номер нулями до 10 цифр при необходимости (смотри примеры)
Обратите внимание на формат вывода фамилии и имени человека
*/

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static Map<String, String> countries = new HashMap<String, String>();
    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }
    public static class IncomeDataAdapter implements Customer, Contact {
        private IncomeData incomeData;

        public IncomeDataAdapter(IncomeData incomeData)
        {
            this.incomeData = incomeData;
        }

        @Override
        public String getCompanyName()
        {
            return this.incomeData.getCompany();
        }

        @Override
        public String getCountryName()
        {
            return countries.get(incomeData.getCountryCode());
        }

        @Override
        public String getName()
        {
            return incomeData.getContactLastName() + ", " + incomeData.getContactFirstName();
        }

        @Override
        public String getPhoneNumber()
        {
            char[] chars = String.valueOf(incomeData.getPhoneNumber()).toCharArray();
            String s = "";
            if (chars.length < 10)
            {
                int mass = 10 - chars.length;
                for (int i = 0; i < mass; i++)
                {
                    s = s + "0";
                }
            }
            s = s + String.valueOf(incomeData.getPhoneNumber());
            char[] number = s.toCharArray();
            String n1 = "";
            for (int i = 0; i < 3; i++)
            {
                n1 = n1 + number[i];
            }
            String n2 = "";
            for (int i = 3; i < 6; i++)
            {
                n2 = n2 + number[i];
            }
            String n3 = "";
            for (int i = 6; i < 8; i++)
            {
                n3 = n3 + number[i];
            }
            String n4 = "";
            for (int i = 8; i < 10; i++)
            {
                n4 = n4 + number[i];
            }
            return "+" + incomeData.getCountryPhoneCode() + "(" + n1 + ")" + n2 + "-" + n3 + "-" + n4;
        }
    }

    public static interface IncomeData {
        String getCountryCode();        //example UA

        String getCompany();            //example JavaRush Ltd.

        String getContactFirstName();   //example Ivan

        String getContactLastName();    //example Ivanov

        int getCountryPhoneCode();      //example 38

        int getPhoneNumber();           //example 501234567
    }

    public static interface Customer {
        String getCompanyName();        //example JavaRush Ltd.

        String getCountryName();        //example Ukraine
    }

    public static interface Contact {
        String getName();               //example Ivanov, Ivan

        String getPhoneNumber();        //example +38(050)123-45-67
    }
}
package com.javarush.test.level22.lesson13.task03;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/* Проверка номера телефона
Метод checkTelNumber должен проверять, является ли аргумент telNumber валидным номером телефона.
Критерии валидности:
1) если номер начинается с '+', то он содержит 12 цифр
2) если номер начинается с цифры или открывающей скобки, то он содержит 10 цифр
3) может содержать 0-2 знаков '-', которые не могут идти подряд
4) может содержать 1 пару скобок '(' и ')'  , причем если она есть, то она расположена левее знаков '-'
5) скобки внутри содержат четко 3 цифры
6) номер не содержит букв
7) номер заканчивается на цифру

Примеры:
+380501234567 - true
+38(050)1234567 - true
+38050123-45-67 - true
050123-4567 - true

+38)050(1234567 - false
+38(050)1-23-45-6-7 - false
050ххх4567 - false
050123456 - false
(0)501234567 - false
*/
public class Solution {
    public static void main(String[] args)
    {
        HashMap<String, Boolean> map = new HashMap<>();

        map.put("38xx501A34567", false);
        map.put("3805012345", true);
        map.put("+380501234567", true);
        map.put("++380501234567", false);
        map.put("+38(0501234567", false);
        map.put("+38)050(1234567", false);
        map.put("+38(050)1234567", true);
        map.put("+38(05)1234567", false);
        map.put("(3)80501234567", false);
        map.put("(380)501234567", false);
        map.put("380-50123-45", true);
        map.put("(380)501-234567", false);
        map.put("(38-0)501234567", false);
        map.put("380-(501)234567", false);
        map.put("380(50-1)234567", false);
        map.put("380(501)(23)4567", false);
        map.put("+38050123(456)7", true);
        map.put("+38050123(456)76", false);
        map.put("+380501234(567)", false);
        map.put("3-805-0123-45", false);
        map.put("-3805-012345", false);
        map.put("3805-012345-", false);
        map.put("+380501234567", true);
        map.put("+38(050)1234567", true);
        map.put("+38(05)01234567", false);
        map.put("+38(0501)234567", false);
        map.put("+38050123-45-67", true);
        map.put("050123-4567", true);
        map.put("+38)050(1234567", false);
        map.put("+38(050)1-23-45-6-7", false);
        map.put("050ххх4567", false);
        map.put("050123456", false);
        map.put("+38-(050)1234567", false);
        map.put("+38((050)1234567", false);
        map.put("+5(0--5)1234567", false);
        map.put("7-4-4123689-5", false);
        map.put("1-23456789-0", true);
        map.put("+38051202(345)7", true);
        map.put("+313450--531202", false);
        map.put("", false);
        map.put("(050)34(125)6-7", false);
        map.put("+38)050(1234567", false);
        map.put("+3+8(050)1234567", false);
        map.put("+313450531202-", false);
        map.put("(380)(050)3567", false);
        map.put("050123456", false);
        map.put("+38(050)1-23-45-6-7", false);
        map.put("+38050123-45-67", true);
        map.put("(345)0512027", true);
        map.put("+38050123-45-6789", false);
        map.put("050123-45678", false);
        map.put("+38)050(1234567", false);
        map.put("050123456", false);
        map.put("+38-(050)1234567", false);
        map.put("(380)(050)3567", false);
        map.put("+38((050)1234567", false);
        map.put("+5(0--5)1234567", false);
        map.put("7-4-4123689-5", false);
        map.put("+(012)--123456789", false);
        map.put("7-4-4123689-5", false);
        map.put("-38-(050)34567", false);
        map.put("+38(050)1-23-45-6-7", false);
        map.put("(050)34125)67", false);
        map.put("050123456", false);
        map.put("050123-4567", true);
        map.put("+38((050)1234567", false);
        map.put("-12345678910", false);
        map.put("+38(050)1234567-", false);
        map.put("+38050(123)(456)7", false);
        map.put("050С…С…С…4567", false);
        map.put("0-50123-4567", true);
        map.put("+38(050)12-34-567", true);
        map.put(null, false);
        map.put("", false);
        map.put("+380501234567", true);
        map.put("+3805012345q67", false);
        map.put("+3805012345 67", false);
        map.put("+3805012345.67", false);
        map.put("+3805012345,67", false);
        map.put("1-23456789-0", true);
        map.put("1-23(456)789-0", false);
        map.put("1-234567(89-0)", false);
        map.put("1-2345678(9-0)", false);
        map.put("(1-2)3456789-0", false);
        map.put("(345)0512027", true);
        map.put("+-313450531202-", false);
        map.put("+380501212334567", false);
        map.put("+3805012asd34567", false);
        map.put("+38(050)1234567", true);
        map.put("+38(150)1234567", true);
        map.put("+3+8(050)1234567", false);
        map.put("+38(050)12-34-567", true);
        map.put("+38(050)12-34567", true);
        map.put("+38(050)112-34567", false);
        map.put("+38(050)12-34(5)67", false);
        map.put("+3(8)(050)12-34567", false);
        map.put("+38050123-45-67", true);
        map.put("+38050123-45-6789", false);
        map.put("050123-4567", true);
        map.put("050123-45678", false);
        map.put("+38)050(1234567", false);
        map.put("+38(050)1-23-45-6-7", false);
        map.put("050ххх4567", false);
        map.put("050123456", false);
        map.put("(0)501234567", false);
        map.put("+38-(050)1234567", false);
        map.put("38-(050)34567", false);
        map.put("-38-(050)34567", false);
        map.put("38-(050)34567-", false);
        map.put("38(050)3(45)67", false);
        map.put("(380)(050)3567", false);
        map.put("+38(380)(050)3567", false);
        map.put("8(380)(050)367", false);
        map.put("8(380)4(050)67", false);
        map.put("+38((050)1234567", false);
        map.put("+5(0--5)1234567", false);
        map.put("7-4-4123689-5", false);
        map.put("+(012)1234567", false);
        map.put("+(01-2)123456789", false);
        map.put("+(012)12345678--9", false);
        map.put("+(012)--123456789", false);
        map.put("+38050(123)(456)7", false);
        map.put("+38051202(345)7", true);
        map.put("+380501234(567)", false);
        map.put("+38050123425-1", true);
        map.put("+380501234251", true);


        for (Map.Entry pair : map.entrySet()) {
            boolean result = checkTelNumber(pair.getKey() == null ? null : pair.getKey().toString());
            if (result != (Boolean) pair.getValue())
                System.out.println(pair.getKey().toString() + " (need: " + pair.getValue() + ", have: " + result + ")");
        }
    }

    public static boolean checkTelNumber(String telNumber) {
        if (telNumber != null && telNumber.length() != 0)
        {
            if(telNumber.matches("^\\+.*"))
            {
                String buf = telNumber;
                if (buf.replaceAll("\\D", "").length() == 12)
                {
                    if (telNumber.matches("^\\+\\d*")) return true;
                    if (telNumber.matches("^\\+\\d*-\\d+-\\d+")) return true;
                    if (telNumber.matches("^\\+-\\d+-\\d+")) return true;
                    if (telNumber.matches("^\\+\\d*-\\d+")) return true;
                    if (telNumber.matches("^\\+\\d*-\\d+")) return true;
                    if (telNumber.matches("^\\+-\\d*")) return true;
                    if (telNumber.matches("^\\+\\d*\\(\\d{3}\\)\\d+")) return true;

                    if (telNumber.matches("^\\+\\d*\\(\\d{3}\\)\\d*-\\d+-\\d+")) return true;
                    if (telNumber.matches("^\\+\\d*\\(\\d{3}\\)\\d*-\\d+")) return true;
                    if (telNumber.matches("^\\+\\d*\\(\\d{3}\\)-\\d+-\\d+")) return true;
                    if (telNumber.matches("^\\+\\d*\\(\\d{3}\\)-\\d+")) return true;
                }
            }
            if (telNumber.matches("^\\(.*") || telNumber.matches("^\\d.*"))
            {
                String buf = telNumber;
                if (buf.replaceAll("\\D", "").length() == 10)
                {
                    if (telNumber.matches("^\\d*")) return true;
                    if (telNumber.matches("^\\d\\d*-\\d+-\\d+")) return true;
                    if (telNumber.matches("^\\d-\\d+-\\d*")) return true;
                    if (telNumber.matches("^\\d\\d*-\\d+")) return true;
                    if (telNumber.matches("^\\d\\d*-\\d+")) return true;
                    if (telNumber.matches("^\\d-\\\\d*")) return true;
                    if (telNumber.matches("^\\d\\([\\d]{3}\\)")) return true;

                    if (telNumber.matches("^\\([\\d]{3}\\)\\d*")) return true;
                    if (telNumber.matches("^\\([\\d]{3}\\)\\d*-\\d+-\\d+")) return true;
                    if (telNumber.matches("^\\([\\d]{3}\\)-\\d+-\\d+")) return true;
                    if (telNumber.matches("^\\([\\d]{3}\\)\\d*-\\d+")) return true;
                    if (telNumber.matches("^\\([\\d]{3}\\)\\d*-\\d+")) return true;
                    if (telNumber.matches("^\\([\\d]{3}\\)-\\d+")) return true;
                }
            }
        }
        return false;
    }
}

package com.javarush.test.level24.lesson02.home01;
import java.lang.*;

import static com.javarush.test.level24.lesson02.home01.Util.testClass;

/* Создание своего интерфейса-маркера
1. Создайте интерфейс-маркер SelfInterfaceMarker
2. Создайте класс SelfInterfaceMarkerImpl, который реализует SelfInterfaceMarker
3. Добавьте в SelfInterfaceMarkerImpl минимум 2 любых различных public метода (дефолтовый public конструктор должен присутствовать)
4. Создайте исключение UnsupportedInterfaceMarkerException
5. В методе testClass класса Util: если параметр == null, то выбросьте UnsupportedInterfaceMarkerException
*/
public class Solution
{
    public static void main(String[] args) throws UnsupportedInterfaceMarkerException
    {
        SelfInterfaceMarkerImpl obj = new SelfInterfaceMarkerImpl();
        testClass(obj);
    }
}



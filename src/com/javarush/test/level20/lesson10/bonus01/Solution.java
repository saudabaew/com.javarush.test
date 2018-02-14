package com.javarush.test.level20.lesson10.bonus01;

import java.util.ArrayList;
import java.util.List;

/* Алгоритмы-числа
Число S состоит из M чисел, например, S=370 и M(количество цифр)=3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.
*/
public class Solution {
    public static void main(String[] s)
    {
        long memoryStart = Runtime.getRuntime().freeMemory();
        Long t0 = System.currentTimeMillis();
        getNumbers(146511208);
        Long t1 = System.currentTimeMillis();
        long memoryEnd = Runtime.getRuntime().freeMemory();
        System.out.println("Mb need to create the arrray = " + (memoryStart - memoryEnd)/(1024*1024d));
        System.out.println("Time need to create the arrray = " + (t1 - t0)/1000d);
    }
    public static int[] getNumbers(int N) {
        int[] result = null;

        List<Integer> list = new ArrayList<>();

        for (int i = 1; i < N; i++) {
            int degree = getDegree(i);
            int input = i;
            int sum = 0;
            int k;

            do {
                k = input % 10;
                sum += Math.pow(k, degree);
            } while ( (input /= 10) > 0);

            if (sum == i) {
                list.add(i);
            }
        }
        result = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
            System.out.println(result[i]);
        }

        return result;
    }

    public static int getDegree(int i) {
        int degree = 0;

        do {
            degree += 1;
        } while ( (i /= 10) > 0);

        return degree;
    }
}

package com.javarush.test.level34.lesson02.task03;

/* Разложение на множители с помощью рекурсии
Разложить целое число n > 1 на простые множители.
Вывести в консоль через пробел все множители в порядке возрастания.
Написать рекуррентный метод для вычисления простых множителей.
Не создавайте статические переменные и поля класса.
Пример:
132
Вывод на консоль:
2 2 3 11
*/
public class Solution {
    public static void main(String[] args)
    {
        new Solution().recursion(132);
    }
    public void recursion(int n) {
        if (n == 1) return;
        int i = 2;
        while (true)
        {
            if (n % i == 0)
            {
                int nextN = n/i;
                if (nextN > 1)
                {
                    System.out.print(i + " ");
                    recursion(nextN);
                    return;
                }
                else if (nextN == 1)
                {
                    System.out.print(i);
                    return;
                }
            }
            i++;
        }
    }
}

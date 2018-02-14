package com.javarush.test.level26.lesson02.task01;

import java.util.Arrays;
import java.util.Comparator;

/* Почитать в инете про медиану выборки
Реализовать логику метода sort, который должен сортировать данные в массиве по удаленности от его медианы
Вернуть отсортированный массив от минимального расстояния до максимального
Если удаленность одинаковая у нескольких чисел, то выводить их в порядке возрастания
*/
public class Solution {
    public static Integer[] sort(Integer[] array) {
        //implement logic here
        final int mediana;
        Arrays.sort(array);
        if (array.length%2 == 0)        {
            mediana = (array[array.length/2-1] + array[array.length/2])/2;
        }

        else        {
            mediana = array[array.length/2];
        }

        Comparator<Integer> integerComparator = new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2)
            {
                int rezult = Math.abs(o1 - mediana);
                int rezult2 = Math.abs(o2 - mediana);
                int add = (rezult == rezult2) ? ((o1 > o2) ? 1 : -1) : 0;

                return (rezult - rezult2)*10 + add;
            }
        };
        System.out.println(mediana);
        Arrays.sort(array, integerComparator);
        return array;
    }

    public static void main(String[] args)
    {
        Integer[] arr = {3, 4, 7, -1, 5, 2, 1, -18, 11, 5, 48, 49, -23, 92, 94};
        sort(arr);
        for (int i = 0; i < arr.length; i++)
        {
            System.out.print(arr[i] + " ");
        }
    }
}

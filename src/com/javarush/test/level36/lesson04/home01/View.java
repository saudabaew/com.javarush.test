package com.javarush.test.level36.lesson04.home01;

import java.util.List;

/**
 * Created by 1 on 02.10.2017.
 */
public class View
{
    private Controller controller = new Controller();
    public void fireEventShowData() {
        System.out.println(controller.onDataListShow());
    }
}

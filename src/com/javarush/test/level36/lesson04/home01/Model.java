package com.javarush.test.level36.lesson04.home01;

import java.util.List;

/**
 * Created by 1 on 02.10.2017.
 */
public class Model
{
    Service service = new Service();
    public List<String> getStringDataList() {
        return service.getData();
    }
}

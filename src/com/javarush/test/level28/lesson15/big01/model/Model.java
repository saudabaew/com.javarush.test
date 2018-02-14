package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.view.View;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 30.08.2017.
 */
public class Model
{
    private View view;
    private Provider[] providers;

    public Model(View view, Provider[] providers)
    {
        if (view == null || providers.length == 0) throw new IllegalArgumentException();
        this.view = view;
        this.providers = providers;
    }

    public void selectCity(String city) throws IOException
    {
        List<Vacancy> list = new ArrayList<>();
        for (Provider p: providers
             )
        {
            list.addAll(p.getJavaVacancies(city));
        }
        view.update(list);
    }
}

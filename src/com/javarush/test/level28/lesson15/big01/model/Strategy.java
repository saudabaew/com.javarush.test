package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.io.IOException;
import java.util.List;

/**
 * Created by 1 on 30.08.2017.
 */
public interface Strategy
{
   List<Vacancy> getVacancies(String searchString) throws IOException;
}

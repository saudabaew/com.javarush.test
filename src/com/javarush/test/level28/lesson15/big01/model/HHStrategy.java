package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by 1 on 30.08.2017.
 */
public class HHStrategy implements Strategy
{
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";
    private static final int timeout = 5 * 1000;
    private static final String userAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36";
    private static final String referrer = "http://hh.ua/search/vacancy?text=java+%D0%BA%D0%B8%D0%B5%D0%B2";

    @Override
    public List<Vacancy> getVacancies(String searchString) throws IOException
    {
        ArrayList<Vacancy> list = new ArrayList<>();
        Document document;
        for (int i = 0; true; i++)
        {
            document = getDocument(searchString, i);
            if (document == null) break;
            Elements vac = document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
            if (vac.size() == 0) break;
            for (Element v : vac
                    )
            {
                Vacancy vacancy = new Vacancy();
                vacancy.setTitle(v.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").text());

                vacancy.setCity(v.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").text());
                vacancy.setCompanyName(v.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text());
                vacancy.setSiteName(document.title());
                vacancy.setUrl(v.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").attr("href"));

                String salary = v.select("[data-qa=\"vacancy-serp__vacancy-compensation\"]").text();
                vacancy.setSalary(salary != null ? salary : "");

                list.add(vacancy);
            }
        }


        return list;
    }

    protected Document getDocument(String searchString, int page) throws IOException
    {
        Document document = Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .userAgent(userAgent)
                .referrer(referrer)
                .timeout(timeout)
                .get();
        return document;
    }
}

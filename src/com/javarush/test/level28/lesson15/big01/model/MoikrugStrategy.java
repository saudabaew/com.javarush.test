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
 * Created by 1 on 31.08.2017.
 */
public class MoikrugStrategy implements Strategy
{
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";
    private static final String userAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36";
    private static final String referrer = "https://moikrug.ru/vacancies?q=java+Dnepropetrovsk";

    @Override
    public List<Vacancy> getVacancies(String searchString) throws IOException
    {
        ArrayList<Vacancy> list = new ArrayList<>();
        Document document;
        for (int i = 0; true; i++)
        {
            document = getDocument(searchString, i);
            if (document == null) break;
            Elements vac = document.getElementsByClass("job");
            if (vac.size() == 0) break;
            for (Element v : vac
                    )
            {
                Vacancy vacancy = new Vacancy();
                vacancy.setTitle(v.getElementsByAttributeValue("class", "title").text());

                String city = v.select("[class=\"location\"]").text();
                vacancy.setCity(city != null ? city : "");

                vacancy.setCompanyName(v.getElementsByAttributeValue("class", "company_name").text());
                vacancy.setSiteName(document.title());

                vacancy.setUrl(v.getElementsByClass("title").first().child(0).attr("abs:href") );

                String salary = v.select("[class=\"salary\"]").text();
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
                .get();
        return document;
    }
}

package com.javarush.test.level28.lesson15.big01.view;

import com.javarush.test.level28.lesson15.big01.Controller;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by 1 on 30.08.2017.
 */
public class HtmlView implements View
{
    private Controller controller;
    private final String filePath = "./src/" + this.getClass().getPackage().getName().replaceAll("\\.", "/") + "/vacancies.html";

    protected Document getDocument() throws IOException
    {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }

    private String getUpdatedFileContent(List<Vacancy> list) throws IOException
    {
        Document document = getDocument();
        Element element = document.getElementsByClass("template").first();
        Element cloneElement = element.clone();
        cloneElement.removeClass("template");
        cloneElement.removeAttr("style");

        document.getElementsByAttributeValue("class", "vacancy").remove();

        for (Vacancy vacancy: list
             )
        {
            Element vac = cloneElement.clone();
            vac.getElementsByAttributeValue("class", "city").get(0).text(vacancy.getCity());
            vac.getElementsByAttributeValue("class", "companyName").get(0).text(vacancy.getCompanyName());
            vac.getElementsByAttributeValue("class", "salary").get(0).text(vacancy.getSalary());
            Element link = vac.getElementsByTag("a").get(0);
            link.text(vacancy.getTitle());
            link.attr("href", vacancy.getUrl());
            element.before(vac.outerHtml());

        }
        return document.html();
    }

    private void updateFile(String s) throws IOException
    {
        FileWriter writer = new FileWriter(filePath);
        writer.write(s);
        writer.close();
    }

    public void userCitySelectEmulationMethod() throws IOException
    {
        controller.onCitySelect("Odessa");
    }

    @Override
    public void update(List<Vacancy> vacancies)
    {
        try
        {
            updateFile(getUpdatedFileContent(vacancies));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Some exception occurred");
        }
    }

    @Override
    public void setController(Controller controller)
    {
        this.controller = controller;
    }
}

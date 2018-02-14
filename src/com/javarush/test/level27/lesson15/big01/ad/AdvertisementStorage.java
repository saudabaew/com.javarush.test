package com.javarush.test.level27.lesson15.big01.ad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 25.08.2017.
 */
class AdvertisementStorage
{
    private final List<Advertisement> videos = new ArrayList<>();

    public List<Advertisement> list()
    {
        return videos;
    }

    public AdvertisementStorage()
    {
        Object someContent = new Object();
        videos.add(new Advertisement(someContent, "First Video", 5000, 100, 3 * 60));
        videos.add(new Advertisement(someContent, "Second Video", 100, 10, 15 * 60));
        videos.add(new Advertisement(someContent, "Third Video", 400, 2, 10 * 60));
    }

    public void add(Advertisement advertisement)
    {
        videos.add(advertisement);
    }

    private static AdvertisementStorage ourInstance = new AdvertisementStorage();

    public static AdvertisementStorage getInstance()
    {
        return ourInstance;
    }
}

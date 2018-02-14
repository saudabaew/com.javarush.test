package com.javarush.test.level27.lesson15.big01.ad;


import com.javarush.test.level27.lesson15.big01.ConsoleHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by 1 on 25.08.2017.
 */
public class AdvertisementManager
{
    int timeSeconds;
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();

    public AdvertisementManager(int timeSeconds)
    {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos()
    {
        List<Advertisement> advertisementList = storage.list();
        if (advertisementList.isEmpty()) throw new NoVideoAvailableException();
        Collections.sort(advertisementList, new Comparator<Advertisement>()
        {
            @Override
            public int compare(Advertisement o1, Advertisement o2)
            {
                return Long.compare(o2.getAmountPerOneDisplaying(), o1.getAmountPerOneDisplaying());
            }
        });

        List<Advertisement> toPlay = new ArrayList<>();
        for (Advertisement entry : advertisementList)
        {
            if (timeSeconds >= entry.getDuration())
            {
                toPlay.add(entry);
                timeSeconds = timeSeconds - entry.getDuration();
            } else continue;
        }

        for (Advertisement entry : toPlay
                )
        {
            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d", entry.getName(), entry.getAmountPerOneDisplaying(), entry.getAmountPerOneDisplaying()*1000 / entry.getDuration()));
            entry.revalidate();
        }
    }
}

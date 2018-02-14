package com.javarush.test.level34.lesson15.big01.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import static com.javarush.test.level34.lesson15.big01.model.Model.FIELD_SELL_SIZE;

/**
 * Created by 1 on 29.09.2017.
 */
public class LevelLoader
{
    private Path levels;

    public LevelLoader(Path levels)
    {
        this.levels = levels;
    }

    public GameObjects getLevel(int level)
    {
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;

        if (level > 60)
        {
            if (level%60 == 0) level = 1;
            else level = level%60;
        }
        String search = String.format("Maze: %d", level);

        try
        {
            FileInputStream inputStream = new FileInputStream(levels.toFile());
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String buf = "";
            while (!buf.equals(search))
            {
                buf = reader.readLine();
            }
            reader.readLine();

            String[] x = reader.readLine().split(" ");
            int sizeX = Integer.parseInt(x[x.length-1]);
            String[] y = reader.readLine().split(" ");
            int sizeY = Integer.parseInt(y[y.length-1]);

            for (int i = 0; i < 3; i++)
            {
                reader.readLine();
            }

            String[] field = new String[sizeY];
            for (int i = 0; i < sizeY; i++)
            {
                field[i] = reader.readLine();
            }

            int xStart = FIELD_SELL_SIZE / 2;
            int yStart = FIELD_SELL_SIZE / 2;
            for (int i = 0; i < field.length; i++)
            {
                char[] chars = field[i].toCharArray();
                for (int j = 0; j < chars.length; j++)
                {
                    if (chars[j] == 'X') walls.add(new Wall(xStart + j*FIELD_SELL_SIZE, yStart + i*FIELD_SELL_SIZE));
                    if (chars[j] == '*') boxes.add(new Box(xStart + j*FIELD_SELL_SIZE, yStart + i*FIELD_SELL_SIZE));
                    if (chars[j] == '.') homes.add(new Home(xStart + j*FIELD_SELL_SIZE, yStart + i*FIELD_SELL_SIZE));
                    if (chars[j] == '@') player = new Player(xStart + j*FIELD_SELL_SIZE, yStart + i*FIELD_SELL_SIZE);
                    if (chars[j] == '&')
                    {
                        homes.add(new Home(xStart + j*FIELD_SELL_SIZE, yStart + i*FIELD_SELL_SIZE));
                        boxes.add(new Box(xStart + j*FIELD_SELL_SIZE, yStart + i*FIELD_SELL_SIZE));
                    }
                }
            }

            inputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return new GameObjects(walls, boxes, homes, player);
    }
}

package com.javarush.test.level34.lesson15.big01.model;

import com.javarush.test.level34.lesson15.big01.controller.EventListener;

import java.nio.file.Paths;
import java.util.Set;

/**
 * Created by 1 on 28.09.2017.
 */
public class Model
{
    public static int FIELD_SELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("C:\\Users\\1\\Downloads\\JavaRushHomeWork\\JavaRushHomeWork\\src\\com\\javarush\\test\\level34\\lesson15\\big01\\res\\levels.txt"));

    public GameObjects getGameObjects()
    {
        return gameObjects;
    }

    public void restartLevel(int level)
    {
        this.gameObjects = levelLoader.getLevel(level);
    }

    public void restart()
    {
        restartLevel(currentLevel);
    }

    public void startNextLevel()
    {
        currentLevel++;
        restartLevel(currentLevel);
    }

    public void setEventListener(EventListener eventListener)
    {
        this.eventListener = eventListener;
    }

    public void move(Direction direction)
    {
        Player player = getGameObjects().getPlayer();
        if (checkWallCollision(player, direction)) return;
        if (checkBoxCollision(direction)) return;
        switch (direction)
        {
            case RIGHT:
                player.move(FIELD_SELL_SIZE, 0);
                break;
            case LEFT:
                player.move(-FIELD_SELL_SIZE, 0);
                break;
            case DOWN:
                player.move(0, FIELD_SELL_SIZE);
                break;
            case UP:
                player.move(0, - FIELD_SELL_SIZE);
                break;
        }
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction)
    {
        Set<Wall> walls = getGameObjects().getWalls();
        for (Wall w: walls
             )
        {
            if (gameObject.isCollision(w, direction)) return true;
        }
        return false;
    }

    public boolean checkBoxCollision(Direction direction)
    {
        Player player = getGameObjects().getPlayer();
        Set<Box> boxes = getGameObjects().getBoxes();
        for (Box b: boxes
             )
        {
            if (player.isCollision(b, direction) && checkWallCollision(b, direction)) return true;
            if (player.isCollision(b, direction))
            {
                for (Box b2: boxes
                     )
                {
                    if (b == b2) continue;
                    if (b.isCollision(b2, direction)) return true;
                }
            }
            if (player.isCollision(b, direction))
            {
                switch (direction)
                {
                    case LEFT:
                        b.move(-FIELD_SELL_SIZE, 0);
                        return false;
                    case RIGHT:
                        b.move(FIELD_SELL_SIZE, 0);
                        return false;
                    case UP:
                        b.move(0, -FIELD_SELL_SIZE);
                        return false;
                    case DOWN:
                        b.move(0, FIELD_SELL_SIZE);
                        return false;
                }
            }
        }

        return false;
    }

    public void checkCompletion()
    {
        Set<Home> homes = getGameObjects().getHomes();
        Set<Box> boxes = getGameObjects().getBoxes();
        int count = 0;
        for (Home h: homes
             )
        {
            for (Box b: boxes
                 )
            {
                if (h.getX() == b.getX() && h.getY() == b.getY()) count++;
            }
        }
        if (count == homes.size()) eventListener.levelCompleted(currentLevel);
    }
}

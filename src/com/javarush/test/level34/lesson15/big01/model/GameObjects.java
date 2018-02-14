package com.javarush.test.level34.lesson15.big01.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 1 on 29.09.2017.
 */
public class GameObjects
{
    private Set<Wall> walls;
    private Set<Box> boxes;
    private Set<Home> homes;
    private Player player;

    public Set<GameObject> getAll()
    {
        Set<GameObject> gameObjectSet = new HashSet<>();
        gameObjectSet.add(player);
        for (Wall w: walls
             )
        {
            gameObjectSet.add(w);
        }
        for (Box b: boxes
             )
        {
            gameObjectSet.add(b);
        }
        for (Home h: homes
             )
        {
            gameObjectSet.add(h);
        }
        return gameObjectSet;
    }

    public Set<Wall> getWalls()
    {
        return walls;
    }

    public Set<Box> getBoxes()
    {
        return boxes;
    }

    public Set<Home> getHomes()
    {
        return homes;
    }

    public Player getPlayer()
    {
        return player;
    }

    public GameObjects(Set<Wall> walls, Set<Box> boxes, Set<Home> homes, Player player)
    {
        this.walls = walls;
        this.boxes = boxes;
        this.homes = homes;
        this.player = player;
    }
}

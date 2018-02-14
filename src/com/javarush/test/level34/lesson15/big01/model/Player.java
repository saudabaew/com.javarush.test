package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by 1 on 28.09.2017.
 */
public class Player extends CollisionObject implements Movable
{
    @Override
    public void move(int x, int y)
    {
        setX(getX() + x);
        setY(getY() + y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.YELLOW);
        int leftUpperCornerX = getX() - getWidth() / 2;
        int leftUpperCornerY = getY() - getHeight() / 2;
        graphics.drawOval(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
        graphics.fillOval(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
    }

    public Player(int x, int y)
    {
        super(x, y);
    }
}

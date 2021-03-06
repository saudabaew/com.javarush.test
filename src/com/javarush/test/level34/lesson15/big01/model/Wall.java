package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by 1 on 29.09.2017.
 */
public class Wall extends CollisionObject
{
    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.WHITE);
        int leftUpperCornerX = getX() - getWidth() / 2;
        int leftUpperCornerY = getY() - getHeight() / 2;
        int rightUpperCornerX = getX() + getWidth() / 2;
        int rightUpperCornerY = leftUpperCornerY;
        int leftLowerCornerX = leftUpperCornerX;
        int leftLowerCornerY = getY() + getHeight() / 2;
        int rightLowerCornerX = rightUpperCornerX;
        int rightLowerCornerY = leftLowerCornerY;
        graphics.drawRect(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
        graphics.fillRect(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
    }

    public Wall(int x, int y)
    {
        super(x, y);
    }
}

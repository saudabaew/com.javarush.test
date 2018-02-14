package com.javarush.test.level34.lesson15.big01.model;

import static com.javarush.test.level34.lesson15.big01.model.Model.FIELD_SELL_SIZE;

/**
 * Created by 1 on 28.09.2017.
 */
public abstract class CollisionObject extends GameObject
{
    public boolean isCollision(GameObject gameObject, Direction direction)
    {
        switch (direction)
        {
            case DOWN:
                return getY() + FIELD_SELL_SIZE == gameObject.getY() && getX() == gameObject.getX();
            case UP:
                return getY() - FIELD_SELL_SIZE == gameObject.getY() && getX() == gameObject.getX();
            case LEFT:
                return getX() - FIELD_SELL_SIZE == gameObject.getX() && getY() == gameObject.getY();
            case RIGHT:
                return getX() + FIELD_SELL_SIZE == gameObject.getX() && getY() == gameObject.getY();
                default: return false;
        }
    }
    public CollisionObject(int x, int y)
    {
        super(x, y);
    }
}

package com.javarush.test.level32.lesson15.big01.listeners;

import com.javarush.test.level32.lesson15.big01.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

/**
 * Created by 1 on 18.09.2017.
 */
public class TextEditMenuListener implements MenuListener
{
    private View view;

    @Override
    public void menuSelected(MenuEvent e)
    {
        JMenu menu = (JMenu) e.getSource();
        for (Component c: menu.getMenuComponents()
             )
        {
            c.setEnabled(view.isHtmlTabSelected());
        }
    }

    @Override
    public void menuDeselected(MenuEvent e)
    {

    }

    @Override
    public void menuCanceled(MenuEvent e)
    {

    }

    public TextEditMenuListener(View view)
    {
        this.view = view;
    }
}

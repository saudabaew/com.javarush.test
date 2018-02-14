package com.javarush.test.level32.lesson15.big01.listeners;

import com.javarush.test.level32.lesson15.big01.View;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by 1 on 18.09.2017.
 */
public class TabbedPaneChangeListener implements ChangeListener
{
    private View view;

    @Override
    public void stateChanged(ChangeEvent e)
    {
        view.selectedTabChanged();
    }

    public TabbedPaneChangeListener(View view)
    {
        this.view = view;
    }
}

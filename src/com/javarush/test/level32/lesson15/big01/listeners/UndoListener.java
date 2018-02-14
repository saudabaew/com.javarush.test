package com.javarush.test.level32.lesson15.big01.listeners;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

/**
 * Created by 1 on 19.09.2017.
 */
public class UndoListener implements UndoableEditListener
{
    private UndoManager undoManager;

    public UndoListener(UndoManager undoManager)
    {
        this.undoManager = undoManager;
    }

    public void undoableEditHappened(UndoableEditEvent e)
    {
        undoManager.addEdit(e.getEdit());
    }
}

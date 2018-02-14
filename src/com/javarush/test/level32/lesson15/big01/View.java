package com.javarush.test.level32.lesson15.big01;

import com.javarush.test.level32.lesson15.big01.listeners.FrameListener;
import com.javarush.test.level32.lesson15.big01.listeners.TabbedPaneChangeListener;
import com.javarush.test.level32.lesson15.big01.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 1 on 18.09.2017.
 */
public class View extends JFrame implements ActionListener
{
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);

    public void update()
    {
        htmlTextPane.setDocument(controller.getDocument());
    }

    public void showAbout()
    {
        JOptionPane.showMessageDialog(this, "HTML Editor", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    public void selectHtmlTab()
    {
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    public boolean isHtmlTabSelected()
    {
       return tabbedPane.getSelectedIndex() == 0;
    }

    public void undo()
    {
        try
        {
            undoManager.undo();
        }
        catch (CannotUndoException e)
        {
            ExceptionHandler.log(e);
        }
    }

    public void redo()
    {
        try
        {
            undoManager.redo();
        }
        catch (CannotRedoException e)
        {
            ExceptionHandler.log(e);
        }
    }

    public boolean canUndo()
    {
       return undoManager.canUndo();
    }

    public boolean canRedo()
    {
        return undoManager.canRedo();
    }

    public UndoListener getUndoListener()
    {
        return undoListener;
    }

    public void resetUndo()
    {
        undoManager.discardAllEdits();
    }

    public View()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            ExceptionHandler.log(e);
        }
    }

    public void selectedTabChanged()
    {
        if (isHtmlTabSelected()) controller.setPlainText(plainTextPane.getText());
        else plainTextPane.setText(controller.getPlainText());
        resetUndo();
    }

    public void initGui()
    {
        initMenuBar();
        initEditor();
        pack();
    }

    public void initMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, menuBar);
        MenuHelper.initEditMenu(this, menuBar);
        MenuHelper.initStyleMenu(this, menuBar);
        MenuHelper.initAlignMenu(this, menuBar);
        MenuHelper.initColorMenu(this, menuBar);
        MenuHelper.initFontMenu(this, menuBar);
        MenuHelper.initHelpMenu(this, menuBar);
        getContentPane().add(menuBar, BorderLayout.NORTH);
    }

    public void initEditor()
    {
        htmlTextPane.setContentType("text/html");
        tabbedPane.add("HTML", new JScrollPane(htmlTextPane));
        tabbedPane.add("Текст", new JScrollPane(plainTextPane));
        tabbedPane.setPreferredSize(new Dimension(1903, 950));
        TabbedPaneChangeListener tabbedPaneChangeListener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(tabbedPaneChangeListener);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    public void exit()
    {
        controller.exit();
    }

    public void init()
    {
        initGui();
        addWindowListener(new FrameListener(this));
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("Новый")) controller.createNewDocument();
        if (e.getActionCommand().equals("Открыть")) controller.openDocument();
        if (e.getActionCommand().equals("Сохранить")) controller.saveDocument();
        if (e.getActionCommand().equals("Сохранить как...")) controller.saveDocumentAs();
        if (e.getActionCommand().equals("Выход")) controller.exit();
        if (e.getActionCommand().equals("О программе")) showAbout();
    }

    public Controller getController()
    {
        return controller;
    }

    public void setController(Controller controller)
    {
        this.controller = controller;
    }
}

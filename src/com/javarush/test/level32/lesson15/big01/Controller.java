package com.javarush.test.level32.lesson15.big01;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

/**
 * Created by 1 on 18.09.2017.
 */
public class Controller
{
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public static void main(String[] args)
    {
        View view = new View();
        Controller controller = new Controller(view);
        view.init();
        controller.init();
        view.setController(controller);
    }

    public void createNewDocument()
    {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        view.resetUndo();
        currentFile = null;
    }

    public void openDocument()
    {
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        int n = fileChooser.showOpenDialog(view);
        if (n == JFileChooser.APPROVE_OPTION)
        {
            currentFile = fileChooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            try(FileReader reader = new FileReader(currentFile))
            {
                new HTMLEditorKit().read(reader, document, 0);
                view.resetUndo();
            }
            catch (Exception e)
            {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocument()
    {
        if (currentFile == null) saveDocumentAs();
        else
        {
            view.selectHtmlTab();
            try(FileWriter writer = new FileWriter(currentFile))
            {
                new HTMLEditorKit().write(writer, document, 0, document.getLength());
            }
            catch (Exception e)
            {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocumentAs()
    {
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        int n = fileChooser.showSaveDialog(view);
        if (n == JFileChooser.APPROVE_OPTION)
        {
            currentFile = fileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try(FileWriter writer = new FileWriter(currentFile))
            {
                new HTMLEditorKit().write(writer, document, 0, document.getLength());
            }
            catch (Exception e)
            {
                ExceptionHandler.log(e);
            }
        }
    }

    public void resetDocument()
    {
        if (document != null)
        {
            document.removeUndoableEditListener(view.getUndoListener());
        }
        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }

    public void setPlainText(String text)
    {
        resetDocument();
        StringReader reader = new StringReader(text);
        try {
            //Вызови метод read() из класса HTMLEditorKit, который вычитает данные из реадера в документ document
            new HTMLEditorKit().read(reader, document, 0);

        } catch (Exception e) {
            //Проследи, чтобы метод не кидал исключения. Их необходимо просто логировать
            ExceptionHandler.log(e);
        }
    }

    public String getPlainText()
    {
        StringWriter writer = new StringWriter();
        try
        {
            new HTMLEditorKit().write(writer, document, 0, document.getLength());
        }
        catch (Exception e)
        {
            ExceptionHandler.log(e);
        }
        return writer.toString();
    }

    public HTMLDocument getDocument()
    {
        return document;
    }

    public void exit()
    {
        System.exit(0);
    }

    public void init()
    {
        createNewDocument();
    }

    public Controller(View view)
    {
        this.view = view;
    }
}

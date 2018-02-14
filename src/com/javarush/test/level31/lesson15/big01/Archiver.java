package com.javarush.test.level31.lesson15.big01;

import com.javarush.test.level31.lesson15.big01.command.ExitCommand;
import com.javarush.test.level31.lesson15.big01.exception.WrongZipFileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

/**
 * Created by 1 on 13.09.2017.
 */
public class Archiver
{
    public static void main(String[] args) throws Exception
    {
        Operation operation = null;
        while (operation != Operation.EXIT){
            try
            {
                operation = askOperation();
                CommandExecutor.execute(operation);
            }
            catch (WrongZipFileException e)
            {
                ConsoleHelper.writeMessage("Вы не выбрали файл архива или выбрали неверный файл.");
            }
            catch (Exception e)
            {
                ConsoleHelper.writeMessage("Произошла ошибка. Проверьте введенные данные.");
            }
        }
    }

    public static Operation askOperation() throws IOException {
        ConsoleHelper.writeMessage(String.format("Выберите операцию:\n" +
                "\t %d - упаковать файлы в архив\n" +
                "\t %d - добавить файл в архив\n" +
                "\t %d - удалить файл из архива\n" +
                "\t %d - распаковать архив\n" +
                "\t %d - просмотреть содержимое архива\n" +
                "\t %d – выход", Operation.CREATE.ordinal(),
                Operation.ADD.ordinal(),
                Operation.REMOVE.ordinal(),
                Operation.EXTRACT.ordinal(),
                Operation.CONTENT.ordinal(),
                Operation.EXIT.ordinal()));
        Operation[] operations = Operation.values();
        return operations[ConsoleHelper.readInt()];
    }
}

package com.javarush.test.level30.lesson15.big01.client;

import com.javarush.test.level30.lesson15.big01.ConsoleHelper;
import com.javarush.test.level30.lesson15.big01.Message;
import com.javarush.test.level30.lesson15.big01.MessageType;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 1 on 08.09.2017.
 */
public class BotClient extends Client
{
    public static void main(String[] args)
    {
        new BotClient().run();
    }

    public class BotSocketThread extends SocketThread
    {
        @Override
        protected void processIncomingMessage(String message)
        {
            ConsoleHelper.writeMessage(message);
            if (message.contains(": ") && message.split(": ").length == 2)
            {
                String[] textOfMessage = message.split(": ");
                Calendar calendar = Calendar.getInstance();
                switch (textOfMessage[1])
                {
                    case "дата":
                        SimpleDateFormat dateFormat = new SimpleDateFormat("d.MM.YYYY");
                        String answer = "Информация для " + textOfMessage[0] + ": " + dateFormat.format(calendar.getTime());
                        sendTextMessage(answer);
                        break;
                    case "день":
                        dateFormat = new SimpleDateFormat("d");
                        answer = "Информация для " + textOfMessage[0] + ": " + dateFormat.format(calendar.getTime());
                        sendTextMessage(answer);
                        break;
                    case "месяц":
                        dateFormat = new SimpleDateFormat("MMMM");
                        answer = "Информация для " + textOfMessage[0] + ": " + dateFormat.format(calendar.getTime());
                        sendTextMessage(answer);
                        break;
                    case "год":
                        dateFormat = new SimpleDateFormat("YYYY");
                        answer = "Информация для " + textOfMessage[0] + ": " + dateFormat.format(calendar.getTime());
                        sendTextMessage(answer);
                        break;
                    case "время":
                        dateFormat = new SimpleDateFormat("H:mm:ss");
                        answer = "Информация для " + textOfMessage[0] + ": " + dateFormat.format(calendar.getTime());
                        sendTextMessage(answer);
                        break;
                    case "час":
                        dateFormat = new SimpleDateFormat("H");
                        answer = "Информация для " + textOfMessage[0] + ": " + dateFormat.format(calendar.getTime());
                        sendTextMessage(answer);
                        break;
                    case "минуты":
                        dateFormat = new SimpleDateFormat("m");
                        answer = "Информация для " + textOfMessage[0] + ": " + dateFormat.format(calendar.getTime());
                        sendTextMessage(answer);
                        break;
                    case "секунды":
                        dateFormat = new SimpleDateFormat("s");
                        answer = "Информация для " + textOfMessage[0] + ": " + dateFormat.format(calendar.getTime());
                        sendTextMessage(answer);
                        break;
                }
            }
        }

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException
        {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }
    }

    @Override
    protected String getUserName()
    {
        String boteName = String.format("date_bot_%d", (int) (Math.random() * 99));
        return boteName;
    }

    @Override
    protected boolean shouldSentTextFromConsole()
    {
        return false;
    }

    @Override
    protected SocketThread getSocketThread()
    {
        return new BotSocketThread();
    }
}

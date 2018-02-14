package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by 1 on 23.08.2017.
 */
class LoginCommand implements Command
{
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle("com.javarush.test.level26.lesson15.big01.resources.login_en");
    @Override
    public void execute() throws InterruptOperationException
    {
        while (true)
        {
            ConsoleHelper.writeMessage(res.getString("before"));
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String[] s = new String[2];
            s[0] = ConsoleHelper.readString();
            s[1] = ConsoleHelper.readString();
            if (s[0].matches("\\d{12}") && s[1].matches("\\d{4}"))
            {
                if (validCreditCards.containsKey(s[0]) && s[1].equals(validCreditCards.getString(s[0])))
                {
                    ConsoleHelper.writeMessage(res.getString("success.format"));
                    break;
                }
                else
                {
                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), s[0]));
                    break;
                }
            }
            else
            {
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                continue;
            }
        }
    }
}

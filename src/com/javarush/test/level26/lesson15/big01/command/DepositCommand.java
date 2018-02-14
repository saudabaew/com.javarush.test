package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;

/**
 * Created by 1 on 22.08.2017.
 */
class DepositCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit_en");
    @Override
    public void execute() throws InterruptOperationException
    {
        while (true)
        {
            try
            {
                ConsoleHelper.writeMessage(res.getString("before"));
                String valuta = ConsoleHelper.askCurrencyCode();

                String[] d = ConsoleHelper.getValidTwoDigits(valuta);
                CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(valuta);
                manipulator.addAmount(Integer.parseInt(d[0]), Integer.parseInt(d[1]));
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), Integer.parseInt(d[0])*Integer.parseInt(d[1]), valuta));
            }
            catch (NumberFormatException e)
            {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
                continue;
            }
        }
    }
}

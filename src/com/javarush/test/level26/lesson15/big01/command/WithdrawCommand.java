package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by 1 on 22.08.2017.
 */
class WithdrawCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");
    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(ConsoleHelper.askCurrencyCode());
        int draw = 0;
        while (true)
        {
            try
            {
                ConsoleHelper.writeMessage(res.getString("specify.amount"));
                draw = Integer.parseInt(ConsoleHelper.readString());
                if (draw <= 0)
                {
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                    continue;
                }

                if (!manipulator.isAmountAvailable(draw))
                {
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                    continue;
                }

                if (draw > 0 && manipulator.isAmountAvailable(draw))
                {
                    for (Map.Entry<Integer, Integer> entry: manipulator.withdrawAmount(draw).entrySet()
                            )
                    {
                        ConsoleHelper.writeMessage("\t" + entry.getKey().toString() + " - " + entry.getValue().toString());
                    }
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), draw, manipulator.getCurrencyCode()));
                    break;
                }

            }
            catch (NumberFormatException e)
            {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            }
            catch (NotEnoughMoneyException e)
            {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                continue;
            }
        }
    }
}

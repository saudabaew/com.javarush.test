package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;

import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Created by 1 on 22.08.2017.
 */
class InfoCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");
    @Override
    public void execute()
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        Collection<CurrencyManipulator> currencyManipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (currencyManipulators.isEmpty()) ConsoleHelper.writeMessage(res.getString("no.money"));
        for (CurrencyManipulator currencyManipulator: currencyManipulators
                )
        {
            if (!currencyManipulator.hasMoney()) ConsoleHelper.writeMessage(res.getString("no.money"));
            else ConsoleHelper.writeMessage(currencyManipulator.getCurrencyCode() + " - " + currencyManipulator.getTotalAmount());
        }
    }
}

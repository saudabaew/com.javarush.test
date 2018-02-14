package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.Operation;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.HashMap;
import java.util.Map;

import static com.javarush.test.level26.lesson15.big01.Operation.*;

/**
 * Created by 1 on 22.08.2017.
 */
public class CommandExecutor
{
    private CommandExecutor()
    {
    }

    private static Map<Operation, Command> map;
    static {
        map = new HashMap<>();
        map.put(LOGIN, new LoginCommand());
        map.put(INFO, new InfoCommand());
        map.put(DEPOSIT, new DepositCommand());
        map.put(WITHDRAW, new WithdrawCommand());
        map.put(EXIT, new ExitCommand());
    }

    public static final void execute(Operation operation) throws InterruptOperationException
    {
        map.get(operation).execute();
    }
}

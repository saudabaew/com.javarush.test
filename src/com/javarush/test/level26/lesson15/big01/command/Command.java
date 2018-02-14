package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

/**
 * Created by 1 on 22.08.2017.
 */
interface Command
{
    void execute() throws InterruptOperationException;
}

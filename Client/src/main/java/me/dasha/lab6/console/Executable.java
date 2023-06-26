package me.dasha.lab6.console;


import me.dasha.lab6.exp.CommandRuntimeExp;
import me.dasha.lab6.exp.ExitExp;
import me.dasha.lab6.exp.IllegalArgumentExp;

/**
 * Интерфейс для исполняемых команд
 */
public interface Executable {
    void execute(String args) throws CommandRuntimeExp, ExitExp, IllegalArgumentExp;
}

package me.dasha.lab6.commands;

import me.dasha.lab6.dtp.*;
import me.dasha.lab6.exp.IllegalArgumentExp;

/**
 * this abstract class for creation commands
 */
public abstract class Command {
    private final String name;
    private final String info;
    public Command(String name, String info) {
        this.name = name;
        this.info = info;
    }
    /**
     * output information about command
     */

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    /**
     * execute command
     * @param request - command
     */


    public abstract Response execute(Request request) throws IllegalArgumentExp;

}
package me.dasha.lab6.commands.concreteCommands;

import me.dasha.lab6.commands.Command;
import me.dasha.lab6.dtp.Request;
import me.dasha.lab6.dtp.Response;
import me.dasha.lab6.dtp.ResponseStatus;
import me.dasha.lab6.exp.IllegalArgumentExp;
import me.dasha.lab6.utility.Parser;

/**
 * this class ends the program
 */
public class Exit extends Command {

    public Exit() {
        super("exit", "exit: завершить программу (без сохранения в файл)");
    }
    /**
     * execute command
     * @param request - command
     */
    @Override
    public Response execute(Request request) throws IllegalArgumentExp{
        if (!request.getArgs().isBlank()) throw new IllegalArgumentExp();
        Parser.collectionToJson();
        return new Response(ResponseStatus.EXIT);
    }
}
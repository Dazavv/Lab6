package me.dasha.lab6.commands.concreteCommands;

import me.dasha.lab6.commands.Command;
import me.dasha.lab6.dtp.Request;
import me.dasha.lab6.dtp.Response;
import me.dasha.lab6.dtp.ResponseStatus;
import me.dasha.lab6.exp.IllegalArgumentExp;
import me.dasha.lab6.utility.CollectionManager;

import java.util.Objects;

/**
 * this class clear the collection
 */
public class Clear extends Command {
    private final CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        super("clear", "clear: Очистить коллекцию");
        this.collectionManager = collectionManager;
    }

    /**
     * execute command
     *
     * @param request - command
     */
    @Override
    public Response execute(Request request) throws IllegalArgumentExp {
        if (!request.getArgs().isBlank()) throw new IllegalArgumentExp();
        CollectionManager.clear();
        return new Response(ResponseStatus.OK, "Коллекция успешно очищена");
    }
}
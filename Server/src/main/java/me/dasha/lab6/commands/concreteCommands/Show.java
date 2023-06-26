package me.dasha.lab6.commands.concreteCommands;

import me.dasha.lab6.commands.Command;
import me.dasha.lab6.dtp.Request;
import me.dasha.lab6.dtp.Response;
import me.dasha.lab6.dtp.ResponseStatus;
import me.dasha.lab6.exp.IllegalArgumentExp;
import me.dasha.lab6.utility.CollectionManager;

/**
 * this class output all elements of the collection in string representation
 */
public class Show extends Command {
    private final CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        super("show", "show: вывести все элементы коллекции");
        this.collectionManager = collectionManager;
    }

    /**
     * execute command
     * @param request - command
     */
    @Override
    public Response execute(Request request) throws IllegalArgumentExp {
        if (!request.getArgs().isBlank()) throw new IllegalArgumentExp();
        if (CollectionManager.getStack().isEmpty()) {
            return new Response(ResponseStatus.ERROR, "В коллекции нет элементов");
        }
        return new Response(ResponseStatus.OK, CollectionManager.show());
    }
}
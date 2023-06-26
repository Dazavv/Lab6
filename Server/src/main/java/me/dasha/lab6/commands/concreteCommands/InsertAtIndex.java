package me.dasha.lab6.commands.concreteCommands;

import me.dasha.lab6.commands.Command;
import me.dasha.lab6.dtp.Request;
import me.dasha.lab6.dtp.Response;
import me.dasha.lab6.dtp.ResponseStatus;
import me.dasha.lab6.exp.IllegalArgumentExp;
import me.dasha.lab6.utility.CollectionManager;

/**
 * this class adds b new element to b given position
 */
public class InsertAtIndex extends Command {
    private final CollectionManager collectionManager;

    public InsertAtIndex(CollectionManager collectionManager) {
        super("insert_at_index", "insert_at_index {element}: добавить новый элемент в заданную позицию");
        this.collectionManager = collectionManager;
    }
    /**
     * execute command
     * @param request - command
     */
    @Override
    public Response execute(Request request) throws IllegalArgumentExp {
        if (!request.getArgs().isBlank()) throw new IllegalArgumentExp();
        Integer index;
        try {
            index = Integer.parseInt(request.getArgs());
            CollectionManager.insertAt(index, request.getObject());
            return new Response(ResponseStatus.OK,"Команда выполнена");
        } catch (NumberFormatException e) {
            return new Response(ResponseStatus.ASK_OBJECT,"Команда не выполнена. Вы ввели некорректный аргумент");
        }
    }
}


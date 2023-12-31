package me.dasha.lab6.commands.concreteCommands;


import me.dasha.lab6.commands.Command;
import me.dasha.lab6.dtp.Request;
import me.dasha.lab6.dtp.Response;
import me.dasha.lab6.dtp.ResponseStatus;
import me.dasha.lab6.exp.IllegalArgumentExp;
import me.dasha.lab6.utility.CollectionManager;

/**
 * this class sorts items in natural order
 */
public class Sort extends Command {
    private final CollectionManager collectionManager;

    public Sort(CollectionManager collectionManager) {
        super("sort", "sort: отсортировать коллекцию в естественном порядке");
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
            return new Response(ResponseStatus.OK, "Коллекция пустая. Сортировка невозможна");
        }
        CollectionManager.sort();
        return new Response(ResponseStatus.OK, "Коллекция отсортирована");
    }
}

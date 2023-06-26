package me.dasha.lab6.commands.concreteCommands;

import me.dasha.lab6.commands.Command;
import me.dasha.lab6.dtp.Request;
import me.dasha.lab6.dtp.Response;
import me.dasha.lab6.dtp.ResponseStatus;
import me.dasha.lab6.exp.IllegalArgumentExp;
import me.dasha.lab6.exp.NoSuchIDExp;
import me.dasha.lab6.utility.CollectionManager;

/**
 * this class update the id of the element whose value is equal to the given one
 */
public class Update extends Command {
    private final CollectionManager collectionManager;

    public Update(CollectionManager collectionManager) {
        super("update", "update id {element}: обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
    }
    /**
     * execute command
     * @param request - command
     */
    @Override
    public Response execute(Request request) throws IllegalArgumentExp {
        if (request.getArgs().isBlank()) throw new IllegalArgumentExp();
        Integer id;
        try {
            id = Integer.parseInt(request.getArgs().trim());
            if (!collectionManager.checkExist(id)) throw new NoSuchIDExp();
            collectionManager.update(request.getObject(), id);
            return new Response(ResponseStatus.OK, "Элемент с id " + id + " обновлён");
        } catch (NumberFormatException e) {
            return new Response(ResponseStatus.WRONG_ARGUMENTS, "Команда не выполнена. Вы ввели некорректный аргумент");
        } catch (NoSuchIDExp e) {
            return new Response(ResponseStatus.ERROR, "Элемента с таким id нет в коллекции");
        }
    }
}
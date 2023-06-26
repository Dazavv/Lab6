package me.dasha.lab6.commands.concreteCommands;

import me.dasha.lab6.commands.Command;
import me.dasha.lab6.dtp.Request;
import me.dasha.lab6.dtp.Response;
import me.dasha.lab6.dtp.ResponseStatus;
import me.dasha.lab6.exp.IllegalArgumentExp;
import me.dasha.lab6.exp.NoSuchIDExp;
import me.dasha.lab6.utility.CollectionManager;

/**
 * this class remove an item from the collection by id
 */
public class RemoveByID extends Command {
    private final CollectionManager collectionManager;

    public RemoveByID(CollectionManager collectionManager) {
        super("remove_by_id", "remove_by_id id: удалить элемент из коллекции по его id");
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
            id = Integer.parseInt(request.getArgs());
            if (!collectionManager.checkExist(id)) throw new NoSuchIDExp();
            collectionManager.removeById(id);
            return new Response(ResponseStatus.OK, "Объект успешно удален");
        } catch (NumberFormatException e) {
            return new Response(ResponseStatus.WRONG_ARGUMENTS,"Команда не выполнена. Вы ввели некорректный аргумент");
        } catch (NoSuchIDExp e) {
            return new Response(ResponseStatus.ERROR,"Элемента с таким id нет в коллекции");
        }
    }
}
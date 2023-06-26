package me.dasha.lab6.commands.concreteCommands;


import me.dasha.lab6.commands.Command;
import me.dasha.lab6.dtp.Request;
import me.dasha.lab6.dtp.Response;
import me.dasha.lab6.dtp.ResponseStatus;
import me.dasha.lab6.exp.IllegalArgumentExp;
import me.dasha.lab6.exp.NoSuchAchievementsExp;
import me.dasha.lab6.utility.CollectionManager;

import java.util.Objects;

/**
 * this class removes from the collection one item whose achievements field value is equivalent to the given one
 */
public class RemoveByAchievements extends Command {
    private final CollectionManager collectionManager;

    public RemoveByAchievements(CollectionManager collectionManager) {
        super("remove_any_by_achievements", "remove_any_by_achievements achievements: удалить из коллекции один элемент, значение поля achievements которого эквивалентно заданному");
        this.collectionManager = collectionManager;
    }
    /**
     * execute command
     * @param request - command
     */
    @Override
    public Response execute(Request request) throws IllegalArgumentExp {
        if (request.getArgs().isBlank()) throw new IllegalArgumentExp();
        try {
            String str = request.getArgs();
            if (!collectionManager.checkExistAchievements(str)) throw new NoSuchAchievementsExp();
            CollectionManager.removeAnyByAchievements(str);
            return new Response(ResponseStatus.OK, "Элемент удален");
        } catch (NoSuchAchievementsExp e) {
            return new Response(ResponseStatus.ERROR, "Элемента с таким достижением нет в коллекции");
        }
    }
}
package me.dasha.lab6.commands.concreteCommands;

import me.dasha.lab6.commands.Command;
import me.dasha.lab6.commands.CommandManager;
import me.dasha.lab6.dtp.Request;
import me.dasha.lab6.dtp.Response;
import me.dasha.lab6.dtp.ResponseStatus;
import me.dasha.lab6.exp.IllegalArgumentExp;
import me.dasha.lab6.utility.CollectionManager;

/**
 * this class output command information
 */
public class Help extends Command {
    private final CommandManager commandManager;

    public Help(CommandManager commandManager) {
        super("help", "help: вывести список всех команд");
        this.commandManager = commandManager;
    }

    /**
     * execute command
     * @param request - command
     */
    public Response execute(Request request) throws IllegalArgumentExp {
        if (!request.getArgs().isBlank()) throw new IllegalArgumentExp();
        return new Response(ResponseStatus.OK,
                String.join("\n",
                        commandManager.getCommands()
                                .stream().map(Command::getInfo).toList()) + "\n");
    }
}

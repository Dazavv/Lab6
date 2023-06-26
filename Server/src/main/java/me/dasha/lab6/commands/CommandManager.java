package me.dasha.lab6.commands;

import me.dasha.lab6.dtp.*;
import me.dasha.lab6.exp.*;
import me.dasha.lab6.utility.Parser;
import me.dasha.lab6.utility.CollectionEditor;
import me.dasha.lab6.utility.CollectionManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * this class stores the registered commands and calls them
 */
public class CommandManager {
    private final CollectionManager collectionManager;
    private HashMap<String, Command> commands = new HashMap<>();
    private List<String> history = new ArrayList<String>();
    static final Logger commandManagerLogger = Logger.getLogger(CommandManager.class.getName());

    public CommandManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /**
     * add command
     * @param commands
     */
    public void addCommand(Collection<Command> commands) {
        this.commands.putAll(commands.stream()
                .collect(Collectors.toMap(Command::getName, с -> с)));
    }
    public Collection<Command> getCommands() {
        return commands.values();
    }
    /**
     * command input
     * @param request
     */


    public Response executeCommand(Request request) throws IllegalArgumentExp, NoSuchCommandExp, CommandRuntimeExp, ExitExp {
        Command command = commands.get(request.getCommandName());
        if (command == null) {
            commandManagerLogger.info("Команда отсутствует");
            throw new NoSuchCommandExp();
        }
        Response response = command.execute(request);
        if (command instanceof CollectionEditor) {
            Parser.collectionToJson();
        }
        return response;
    }

}

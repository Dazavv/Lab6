package me.dasha.lab6;

import me.dasha.lab6.commands.CommandManager;
import me.dasha.lab6.commands.concreteCommands.*;
import me.dasha.lab6.utility.Parser;
import me.dasha.lab6.utility.*;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

public class MainServer {
    public static int port = 6090;
    public static final int connection_timeout = 60 * 1000;
    private static final Writer console = new Console();
    static final Logger logger = Logger.getLogger(MainServer.class.getName());

    public static void main(String[] args) {
        if (args.length != 1) {
            MainServer.logger.info("Отсутствует файл для работы");
            return;
        }

        CollectionManager collectionManager = new CollectionManager();
        Parser parser = new Parser();

        String link = args[0];
        File file = new File(link);
        if (file.exists() && !file.isDirectory()) {
            Parser.fromJsonToCollection(args[0]);
            MainServer.logger.info("Создание объектов успешно завершено");
        }else {
            MainServer.logger.info("Файла с таким именем не существует");
            return;
        }
        collectionManager.validateAll();
        CommandManager commandManager = new CommandManager(collectionManager);
        commandManager.addCommand(List.of(
                new Add(collectionManager),
                new AddIfMin(collectionManager),
                new Clear(collectionManager),
                new ExecuteScript(),
                new Exit(),
                new InsertAtIndex(collectionManager),
                new Sort(collectionManager),
                new RemoveByAchievements(collectionManager),
                new Help(commandManager),
                new Info(collectionManager),
                new RemoveByID(collectionManager),
                new GroupByCoordinates(collectionManager),
                new Show(collectionManager),
                new Update(collectionManager)
        ));

        RequestHandler requestHandler = new RequestHandler(commandManager);
        Server server = new Server(port, connection_timeout, requestHandler, parser, collectionManager);
        server.runServer();
    }
}

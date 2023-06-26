package me.dasha.lab6.commands.concreteCommands;


import me.dasha.lab6.commands.Command;
import me.dasha.lab6.dtp.Request;
import me.dasha.lab6.dtp.Response;
import me.dasha.lab6.dtp.ResponseStatus;
import me.dasha.lab6.exp.IllegalArgumentExp;
/**
 * this class reads and executes b script from b specified file
 */
public class ExecuteScript extends Command {
    static boolean flag = false;

    public static boolean isFlag() {
        return flag;
    }

    public ExecuteScript() {
        super("execute_script", "execute_script: выполнить скрипт");
    }

    private static String path;

    /**
     * execute command
     * @param request - command
     */
    @Override
    public Response execute(Request request) throws IllegalArgumentExp {
        flag = true;
        if (request.getArgs().isBlank()) throw new IllegalArgumentExp();
        return new Response(ResponseStatus.EXECUTE_SCRIPT, request.getArgs());
    }
//    public Response execute(Request request) throws IllegalArgumentExp {
//        if (request.getArgs() == null || request.getArgs().isBlank()) {
//            return new Response(ResponseStatus.ERROR, "Файл со скриптом отсутствует");
//        }
//        String fileName = (request.getArgs().trim());
//        executeManager.executeScript(fileName);
//        return new Response(ResponseStatus.OK);
//    }


    public static String getPath() {
        return path;
    }
}
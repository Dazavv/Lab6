package me.dasha.lab6;

import me.dasha.lab6.console.Console;
import me.dasha.lab6.exp.IllegalArgumentExp;
import me.dasha.lab6.utility.Client;
import me.dasha.lab6.utility.RuntimeManager;

import java.util.Scanner;

public class MainClient {
    private static String host;
    private static int port;
    private static Console console = new Console();


    public static void main(String[] args) {
        if (!parseHostNPort(args)) return;
        console.write("Для получения справки о доступных командах введите help\n");

        Console console = new Console();
        Client client = new Client(host, port, 15000, 5, console);
        new RuntimeManager(console, new Scanner(System.in), client).interactiveMode();
    }

    public static boolean parseHostNPort(String[] args) {
        try {
            if (args.length != 2) throw new IllegalArgumentExp("Хост и порт должны быть переданы в виде аргумента командной строки");
            host = args[0];
            port = Integer.parseInt(args[1]);
            if (port < 0) throw new IllegalArgumentExp("Порт должен быть положительным числом");
            return true;
        } catch (IllegalArgumentExp e) {
            console.printError(e.getMessage());
        } catch (NumberFormatException e) {
            console.printError("Порт должен быть целым положительным числом");
        }
        return false;
    }
}
